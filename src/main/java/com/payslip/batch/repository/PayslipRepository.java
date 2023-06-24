package com.payslip.batch.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.payslip.batch.dto.PayslipSummaryDto;

@Mapper
public interface PayslipRepository {

    List<PayslipSummaryDto> findAllByUserId(String userId);

}
