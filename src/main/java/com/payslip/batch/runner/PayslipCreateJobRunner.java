package com.payslip.batch.runner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.payslip.batch.JobContext;
import com.payslip.batch.JobRunner;
import com.payslip.batch.service.PayslipCreateService;

public class PayslipCreateJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PayslipCreateJobRunner.class);

    @Autowired
    PayslipCreateService payslipCreateService;

    @Autowired
    JobContext jobContext;

    @Override
    public void run(String... args) throws Exception {

        // バッチ実行に使用する日時
        LocalDate argDate = null;
        // Timestampフォーマット
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        if (args == null || args.length == 0) {
            argDate = LocalDate.now();
        } else {
            // 引数（yyyyMMdd形式）のフォーマットが異なる場合、エラー
            argDate = LocalDate.parse(args[0], formatDate);
        }
        final String jobName = jobContext.getJobName();
        logger.info("Start JobRunner, JobName: {}", jobName);
        payslipCreateService.execute(argDate);
        logger.info("End JobRunner, JobName : {}", jobName);
    }

    @Configuration
    public static class PayslipCreateConfiguration {
        @Bean
        @ConditionalOnProperty(value = JobRunner.EXECUTION_PARAMETER_NAME, havingValue = "output-payslip-default")
        public PayslipCreateJobRunner payslipCreateJobRunner() {
            return new PayslipCreateJobRunner();
        }
    }

}
