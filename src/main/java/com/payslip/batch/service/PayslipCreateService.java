package com.payslip.batch.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payslip.batch.dto.PayslipDetailDto;
import com.payslip.batch.service.shared.PdfCreateSharedService;

@Service
public class PayslipCreateService {

    @Autowired
    PdfCreateSharedService pdfCreateSharedService;

    private static final Logger logger = LoggerFactory.getLogger(PayslipCreateService.class);

    public void execute(LocalDate argDate) {

        logger.info(argDate.toString());

        List<PayslipDetailDto> details = new ArrayList<>();
        pdfCreateSharedService.createPdf(details);
    }

}
