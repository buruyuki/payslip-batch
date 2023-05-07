package com.payslip.payslipbatch.common.config;

public enum JobExitStatus {

    // -1:未定義、0:正常、1:システム異常、2:業務異常終了
    UNDEFINED("Undefined", -1), NORMAL("Normal", 0), WARNING("warning", 2), ERROR("Error", 1);

    private String exitStatus;
    private int exitCode;

    JobExitStatus(String exitStatus, int exitCode) {
        this.exitStatus = exitStatus;
        this.exitCode = exitCode;
    }

    public String getExitStatus() {
        return exitStatus;
    }

    public int getExitCode() {
        return exitCode;
    }

    /**
     * 終了コードからEnumインスタンスを取得する.
     * 
     * @param exitCode終了コード
     * @return 終了コードに対応するEnumインスタンス
     */
    public static JobExitStatus valueOf(int exitCode) {
        for (JobExitStatus e : JobExitStatus.values()) {
            if (e.getExitCode() == exitCode) {
                return e;
            }
        }
        return JobExitStatus.UNDEFINED;
    }

}