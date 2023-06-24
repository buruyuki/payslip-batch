package com.payslip.batch.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payslip.batch.dto.PayslipDetailDto;
import com.payslip.batch.dto.PayslipSummaryDto;
import com.payslip.batch.repository.PayslipRepository;
import com.payslip.batch.service.shared.PdfCreateSharedService;
import com.payslip.batch.util.NumericUtil;

@Service
public class PayslipCreateService {

    @Autowired
    PdfCreateSharedService pdfCreateSharedService;

    @Autowired
    PayslipRepository payslipRepository;

    private static final Logger logger = LoggerFactory.getLogger(PayslipCreateService.class);

    public void execute(LocalDate argDate) {

        logger.info(argDate.toString());

        List<PayslipSummaryDto> list = payslipRepository.findAllByUserId("3526");
        if (list.size() == 0) {
            return;
        }
        List<PayslipDetailDto> details = new ArrayList<>();
        for (PayslipSummaryDto dto : list) {
            PayslipDetailDto detail = new PayslipDetailDto();
            detail.setUserId(dto.getUserId());
            detail.setIssueDate(dto.getIssueDate().substring(0, 4) + "年" + dto.getIssueDate().substring(4, 6) + "月");
            detail.setPayslipType("1".equals(dto.getPayslipType()) ? "給与" : "賞与");
            detail.setKijyunnnai(NumericUtil.intToCommaStringConverter(dto.getKijyunnnai()));
            detail.setKijyunngai(NumericUtil.intToCommaStringConverter(dto.getKijyunngai()));
            detail.setSonotasikyuu(NumericUtil.intToCommaStringConverter(dto.getSonotasikyuu()));
            detail.setSyakaihokenryou(NumericUtil.intToCommaStringConverter(dto.getSyakaihokenryou()));
            detail.setSyotokuzei(NumericUtil.intToCommaStringConverter(dto.getSyotokuzei()));
            detail.setJyuuminnzei(NumericUtil.intToCommaStringConverter(dto.getJyuuminnzei()));
            detail.setIppannkoujyo(NumericUtil.intToCommaStringConverter(dto.getIppannkoujyo()));
            detail.setHurikomisikyuugaku(NumericUtil.intToCommaStringConverter(dto.getHurikomisikyuugaku()));
            details.add(detail);
        }
        pdfCreateSharedService.createPdf(details);
    }

}
