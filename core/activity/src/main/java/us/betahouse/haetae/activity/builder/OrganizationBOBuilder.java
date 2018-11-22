/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.OrganizationBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织构建者
 *
 * @author MessiahJK
 * @version : OrganizationBOBuilder.java 2018/11/22 21:36 MessiahJK
 */
public final class OrganizationBOBuilder {
    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名
     */
    private String organizationName;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();


    private OrganizationBOBuilder() {
    }

    public static OrganizationBOBuilder anOrganizationBO() {
        return new OrganizationBOBuilder();
    }

    public OrganizationBOBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public OrganizationBOBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public OrganizationBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public OrganizationBO build() {
        OrganizationBO organizationBO = new OrganizationBO();
        organizationBO.setOrganizationId(organizationId);
        organizationBO.setOrganizationName(organizationName);
        organizationBO.setExtInfo(extInfo);
        return organizationBO;
    }
}
