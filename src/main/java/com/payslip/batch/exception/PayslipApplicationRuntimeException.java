package com.payslip.batch.exception;

public class PayslipApplicationRuntimeException extends RuntimeException {
    public PayslipApplicationRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
