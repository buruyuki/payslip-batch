package com.payslip.payslipbatch;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.payslip.payslipbatch.common.config.JobExitStatus;
import com.payslip.payslipbatch.exception.PayslipApplicationBussinessRuntimeException;
import com.payslip.payslipbatch.exception.PayslipBussinessException;

@SpringBootApplication
public class JobRunner {
    private static final Logger logger = LoggerFactory.getLogger(JobRunner.class);
    public static final String EXECUTION_PARAMETER_NAME = "job.name";

    public static void main(String[] args) {
        // 計測開始
        Instant start = Instant.now();

        int exitCode = JobExitStatus.NORMAL.getExitCode();
        try (ConfigurableApplicationContext appContext = new SpringApplicationBuilder(JobRunner.class)
                .bannerMode(Banner.Mode.OFF).build().run(args)) {
            exitCode = SpringApplication.exit(appContext);
        } catch (Throwable e) {
            exitCode = (e.getCause() instanceof PayslipBussinessException
                    || e.getCause() instanceof PayslipApplicationBussinessRuntimeException)
                            ? JobExitStatus.WARNING.getExitCode()
                            : JobExitStatus.ERROR.getExitCode();
        } finally {
            JobExitStatus exitStatus = JobExitStatus.valueOf(exitCode);
            // 計測終了
            Instant end = Instant.now();
            logger.info("Finished JobRunner, ExitStatus: {}({}), Execution time : {}ms", exitStatus, exitCode,
                    Duration.between(start, end).toMillis());
            System.exit(exitCode);
        }
    }

}
