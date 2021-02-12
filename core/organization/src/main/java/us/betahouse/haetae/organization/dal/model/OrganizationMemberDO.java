/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 组织成员关系实体
 *
 * @author dango.yxm
 * @version : OrganizationMemberDO.java 2019/03/25 10:28 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "organization_member",
        indexes = {
                @Index(name = "uk_organization_member_id", columnList = "organization_member_id", unique = true),
                @Index(name = "uk_organization_id_member_id", columnList = "organization_id, member_id", unique = true)
        })
public class OrganizationMemberDO extends BaseDO {

    private static final long serialVersionUID = 3671533761726508679L;

    /**
     * 组织成员关系id
     */
    @Column(name = "organization_member_id", nullable = false)
    private String organizationMemberId;

    /**
     * 组织id
     */
    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    /**
     * 成员id
     */
    @Column(name = "member_id", nullable = false)
    private String memberId;

    /**
     * 成员类型
     */
    @Column(name = "member_type", nullable = false)
    private String memberType;

    /**
     * 成员描述
     */
    @Column(name = "member_description")
    private String memberDescription;

    /**
     * 组织名称
     */
    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

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

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}