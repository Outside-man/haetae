/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.model;

import us.betahouse.util.common.ToString;

import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;

/**
 * 组织成员模型
 *
 * @author dango.yxm
 * @version : OrganizationMemberBO.java 2019/03/25 13:30 dango.yxm
 */
public class OrganizationMemberBO extends ToString {

    private static final long serialVersionUID = 4211389080370687090L;

    /**
     * 组织成员id
     */
    private String organizationMemberId;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 成员id
     */
    private String memberId;

    /**
     * 成员类型
     */
    private String memberType;


    /**
     * 组织名称
     */
    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    /**
     * 成员描述
     */
    private String memberDescription;

    private Map<String, String> extInfo = new HashMap<>();

    public String getOrganizationMemberId() {
        return organizationMemberId;
    }

    public void setOrganizationMemberId(String organizationMemberId) {
        this.organizationMemberId = organizationMemberId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberDescription() {
        return memberDescription;
    }

    public void setMemberDescription(String memberDescription) {
        this.memberDescription = memberDescription;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String findJob(){
        return this.organizationName+this.memberDescription;
    }
}