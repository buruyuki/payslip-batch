package com.payslip.payslipbatch.runner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.payslip.payslipbatch.JobContext;
import com.payslip.payslipbatch.JobRunner;
import com.payslip.payslipbatch.service.PayslipCreateService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { JobRunner.class }, args = { "20230101",
        "--spring.config.location=classpath:application-test.yml" }, properties = "job.name=payslip-create-test")
@Transactional
public class PayslipCreateJobRunnerTest {

    @Autowired
    PayslipCreateService payslipCreateService;

    @Autowired
    JobContext jobContext;

    PayslipCreateJobRunner jobRunner = new PayslipCreateJobRunner.PayslipCreateConfiguration().payslipCreateJobRunner();

    @Before
    public void setuo() {
        jobRunner.payslipCreateService = payslipCreateService;
        jobRunner.jobContext = jobContext;
    }

    @Test
    public void run_01() throws Exception {
        jobRunner.run("20230501");

    }

}
