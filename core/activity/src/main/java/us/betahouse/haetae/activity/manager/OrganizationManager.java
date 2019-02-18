/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.model.basic.OrganizationBO;

import java.util.List;

/**
 * 组织管理器
 *
 * @author MessiahJK
 * @version : OrganizationManager.java 2018/11/22 23:43 MessiahJK
 */
public interface OrganizationManager {
    /**
     * 查询所有组织信息
     *
     * @return 组织实体List
     */
    List<OrganizationBO> findAll();

    /**
     * 通过组织id查询组织实体
     *
     * @param organizationId 组织id
     * @return 组织实体
     */
    OrganizationBO findOrganizationByOrganizationId(String organizationId);

    /**
     * 通过组织名查询组织实体
     *
     * @param organizationName 组织名
     * @return 组织实体
     */
    OrganizationBO findOrganizationByOrganizationName(String organizationName);
}
