/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 组织关系实体
 *
 * @author dango.yxm
 * @version : OrganizationDO.java 2019/03/25 10:19 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "organization_relation",
        indexes = {
                @Index(name = "uk_organization_relation_id", columnList = "organization_relation_id", unique = true)
        })
public class OrganizationRelationDO extends BaseDO {

    private static final long serialVersionUID = -4039081925324693824L;

    /**
     * 组织关系id
     */
    @Column(name = "organization_relation_id", nullable = false)
    private String organizationRelationId;

    /**
     * 主组织id
     */
    @Column(name = "primary_organization_id", nullable = false)
    private String primaryOrganizationId;

    /**
     * 子组织id
     */
    @Column(name = "sub_organization_id", nullable = false)
    private String subOrganizationId;

    public String getOrganizationRelationId() {
        return organizationRelationId;
    }

    public void setOrganizationRelationId(String organizationRelationId) {
        this.organizationRelationId = organizationRelationId;
    }

    public String getPrimaryOrganizationId() {
        return primaryOrganizationId;
    }

    public void setPrimaryOrganizationId(String primaryOrganizationId) {
        this.primaryOrganizationId = primaryOrganizationId;
    }

    public String getSubOrganizationId() {
        return subOrganizationId;
    }

    public void setSubOrganizationId(String subOrganizationId) {
        this.subOrganizationId = subOrganizationId;
    }
}