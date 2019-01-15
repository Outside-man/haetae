/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 组织实体
 *
 * @author MessiahJK
 * @version : OrganizationDO.java 2018/11/17 1:43 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_organization",
        indexes = {
                @Index(name = "uk_organization_id", columnList = "organization_id", unique = true),
                @Index(name = "uk_organization_name", columnList = "organization_name", unique = true)
        })
public class OrganizationDO extends BaseDO {

    private static final long serialVersionUID = 4653981489828967152L;

    /**
     * 组织id
     */
    @Column(name = "organization_id", length = 32, updatable = false)
    private String organizationId;

    /**
     * 组织名
     */
    @Column(name = "organization_name")
    private String organizationName;

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
}
