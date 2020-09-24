package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.common.RestRequest;

public class AuditRestRequest extends RestRequest {

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

    /**
     * 发布消息后的跳转页面
     */
    private String page;

    /**
     *
     * 订阅消息人的userid
     */
    private String auditId;

    public String getAuditId() { return auditId; }

    public void setAuditId(String auditId) { this.auditId = auditId; }


    public String getPage() { return page; }

    public void setPage(String page) { this.page = page; }

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
