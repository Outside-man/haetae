/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 组织实体
 *
 * @author dango.yxm
 * @version : OrganizationDO.java 2019/03/25 10:19 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "organization",
        indexes = {
                @Index(name = "uk_organization_id", columnList = "organization_id", unique = true),
                @Index(name = "uk_organization_name_id", columnList = "organization_name", unique = true)
        })
public class OrganizationDO extends BaseDO {

    private static final long serialVersionUID = -3229352071333851690L;

    /**
     * 组织id
     */
    @Column(name = "organization_id", nullable = false)
    private String organizationId;

    /**
     * 组织名称
     */
    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    /**
     * 组织类型
     */
    @Column(name = "organization_type", nullable = false)
    private String organizationType;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }
}