/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityService.java 2018/11/22 19:53 MessiahJK
 */
public interface ActivityService {

    /**
     * 创建活动
     *
     * @param request
     * @param context
     * @return
     */
    ActivityBO create(ActivityManagerRequest request, OperateContext context);

    /**
     * 查找活动
     *
     * @param request
     * @param context
     * @return
     */
    List<ActivityBO> findAll(ActivityManagerRequest request, OperateContext context);

    /**
     * 更新活动
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO update(ActivityManagerRequest request, OperateContext operateContext);

    /**
     * 操作活动
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO operate(ActivityManagerRequest request, OperateContext operateContext);

    /**
     * 活动添加盖章员
     *
     * @param request
     * @param context
     */
    void bindStamper(ActivityManagerRequest request, OperateContext context);
}
