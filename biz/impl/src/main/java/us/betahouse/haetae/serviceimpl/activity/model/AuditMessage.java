package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

public class AuditMessage extends ToString {

    /**
     * 审核结果
     */
    private String result;

    /**
     * 审核内容
     */
    private String detail;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 备注
     */
    private String note;

    public String getResult() { return result; }

    public void setResult(String result) { this.result = result; }

    public String getDetail() { return detail; }

    public void setDetail(String detail) { this.detail = detail; }

    public String getAuditTime() { return auditTime; }

    public void setAuditTime(String auditTime) { this.auditTime = auditTime; }

    public String getApplicant() { return applicant; }

    public void setApplicant(String applicant) { this.applicant = applicant; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}
