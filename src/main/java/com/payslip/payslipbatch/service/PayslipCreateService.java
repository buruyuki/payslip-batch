package com.payslip.payslipbatch.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PayslipCreateService {

    private static final Logger logger = LoggerFactory.getLogger(PayslipCreateService.class);

    public void execute(LocalDate argDate) {

        logger.info(argDate.toString());
    }

}
