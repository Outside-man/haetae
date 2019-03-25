/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.model;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织模型
 *
 * @author dango.yxm
 * @version : OrganizationBO.java 2019/03/25 13:27 dango.yxm
 */
public class OrganizationBO extends ToString {

    private static final long serialVersionUID = -1913147204620513388L;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织类型
     */
    private String organizationType;

    private Map<String, String> extInfo = new HashMap<>();

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}