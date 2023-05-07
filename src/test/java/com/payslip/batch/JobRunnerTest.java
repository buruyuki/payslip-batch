package com.payslip.batch;

import java.util.function.Supplier;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class JobRunnerTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @After
    public void cleanup() {
        System.clearProperty(JobRunner.EXECUTION_PARAMETER_NAME);
        TestJobRunner.exceptionSupplier = null;
    }

    @Test
    public void test01() {
        exit.expectSystemExitWithStatus(0);
        System.setProperty(JobRunner.EXECUTION_PARAMETER_NAME, "test-job");
        JobRunner.main(new String[] { "--spring.profiles.active=without-init-db-test" });
    }

    @Ignore
    @Test
    public void test02() {
        exit.expectSystemExitWithStatus(1);
        JobRunner.main(new String[] { "--spring.profiles.active=without-init-db-test" });
    }

    static class TestJobRunner implements CommandLineRunner, ExitCodeGenerator, DisposableBean {
        private static Supplier<Exception> exceptionSupplier;
        private static int exitCode = 0;

        @Override
        public void run(String... args) throws Exception {
            if (exceptionSupplier != null) {
                throw exceptionSupplier.get();
            }
        }

        @Override
        public int getExitCode() {
            return exitCode;
        }

        @Override
        public void destroy() throws Exception {
            exceptionSupplier = null;
            exitCode = 0;
        }

        @Configuration
        public static class TestJobRunnerConfiguration {
            @Bean
            @ConditionalOnProperty(value = JobRunner.EXECUTION_PARAMETER_NAME, havingValue = "test-job")
            public TestJobRunner testJobRunner() {
                return new TestJobRunner();
            }
        }

    }
}
