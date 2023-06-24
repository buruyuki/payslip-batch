package com.payslip.batch.dto;

public class PayslipSummaryDto {
    private String userId;
    private String issueDate;
    private String payslipType;
    private int kijyunnnai;
    private int kijyunngai;
    private int sonotasikyuu;
    private int syakaihokenryou;
    private int syotokuzei;
    private int jyuuminnzei;
    private int ippannkoujyo;
    private int hurikomisikyuugaku;
    private String createJobId;
    private String updateJobId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getPayslipType() {
        return payslipType;
    }

    public void setPayslipType(String payslipType) {
        this.payslipType = payslipType;
    }

    public int getKijyunnnai() {
        return kijyunnnai;
    }

    public void setKijyunnnai(int kijyunnnai) {
        this.kijyunnnai = kijyunnnai;
    }

    public int getKijyunngai() {
        return kijyunngai;
    }

    public void setKijyunngai(int kijyunngai) {
        this.kijyunngai = kijyunngai;
    }

    public int getSonotasikyuu() {
        return sonotasikyuu;
    }

    public void setSonotasikyuu(int sonotasikyuu) {
        this.sonotasikyuu = sonotasikyuu;
    }

    public int getSyakaihokenryou() {
        return syakaihokenryou;
    }

    public void setSyakaihokenryou(int syakaihokenryou) {
        this.syakaihokenryou = syakaihokenryou;
    }

    public int getSyotokuzei() {
        return syotokuzei;
    }

    public void setSyotokuzei(int syotokuzei) {
        this.syotokuzei = syotokuzei;
    }

    public int getJyuuminnzei() {
        return jyuuminnzei;
    }

    public void setJyuuminnzei(int jyuuminnzei) {
        this.jyuuminnzei = jyuuminnzei;
    }

    public int getIppannkoujyo() {
        return ippannkoujyo;
    }

    public void setIppannkoujyo(int ippannkoujyo) {
        this.ippannkoujyo = ippannkoujyo;
    }

    public int getHurikomisikyuugaku() {
        return hurikomisikyuugaku;
    }

    public void setHurikomisikyuugaku(int hurikomisikyuugaku) {
        this.hurikomisikyuugaku = hurikomisikyuugaku;
    }

    public String getCreateJobId() {
        return createJobId;
    }

    public void setCreateJobId(String createJobId) {
        this.createJobId = createJobId;
    }

    public String getUpdateJobId() {
        return updateJobId;
    }

    public void setUpdateJobId(String updateJobId) {
        this.updateJobId = updateJobId;
    }

}
