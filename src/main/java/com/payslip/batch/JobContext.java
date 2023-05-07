package com.payslip.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JobContext {

    @Autowired
    Environment environment;

    /**
     * JOB名を取得する
     * 
     * @return JOB名
     */
    public String getJobName() {
        return environment.getProperty(JobRunner.EXECUTION_PARAMETER_NAME);
    }

}
