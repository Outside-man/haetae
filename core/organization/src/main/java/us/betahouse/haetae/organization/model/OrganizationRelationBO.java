/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.model;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织关系模型
 *
 * @author dango.yxm
 * @version : OrganizationRelationBO.java 2019/03/25 13:32 dango.yxm
 */
public class OrganizationRelationBO extends ToString {

    private static final long serialVersionUID = 8405655697545480593L;

    /**
     * 组织关系id
     */
    private String organizationRelationId;

    /**
     * 主组织id
     */
    private String primaryOrganizationId;

    /**
     * 子组织id
     */
    private String subOrganizationId;

    private Map<String, String> extInfo = new HashMap<>();

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}