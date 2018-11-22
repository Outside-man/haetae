/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.OrganizationBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * 组织服务
 *
 * @author MessiahJK
 * @version : OrganizationService.java 2018/11/22 16:55 MessiahJK
 */
public interface OrganizationService {

    /**
     * 查询所有组织信息
     * @param context
     * @return
     */
    List<OrganizationBO> findAll(OperateContext context);
}
