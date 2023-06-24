package com.payslip.batch.exception;

public class PayslipApplicationBussinessRuntimeException extends RuntimeException {
    public PayslipApplicationBussinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
