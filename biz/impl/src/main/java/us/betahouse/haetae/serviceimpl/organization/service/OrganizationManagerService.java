/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.service;

import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;

/**
 * 组织管理器
 * 鉴权逻辑
 *
 * @author dango.yxm
 * @version : OrganizationManagerService.java 2019/03/27 21:56 dango.yxm
 */
public interface OrganizationManagerService {

    /**
     * 创建组织服务
     *
     * @param request
     * @return
     */
    OrganizationBO create(OrganizationRequest request);

    /**
     * 解散组织
     *
     * @param request
     */
    void disband(OrganizationRequest request);

    /**
     * 总管管理成员
     *
     * @param request
     */
    void manageMember(OrganizationRequest request);

    /**
     * 主管 管理成员身份
     *
     * @param request
     */
    void changeMemberType(OrganizationRequest request);

    /**
     * 添加成员
     *
     * @param request
     */
    void addMember(OrganizationRequest request);

    /**
     * 移除成员 管理员只能移除member
     *
     * @param request
     */
    void removeMember(OrganizationRequest request);
}