/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.PositionRecordBO;
import us.betahouse.haetae.activity.request.PositionRecordRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

/**
 * @author MessiahJK
 * @version : PositionRecordService.java 2018/11/22 20:17 MessiahJK
 */
public interface PositionRecordService {
    /**
     * 通过用户id 查找履历
     * @param request
     * @param context
     * @return
     */
    PositionRecordBO findByUserId(PositionRecordRequest request, OperateContext context);

    /**
     * 通过组织id 查找组织内成员
     * @param request
     * @param context
     * @return
     */
    PositionRecordBO findByOrganizationId(PositionRecordRequest request,OperateContext context);


}
