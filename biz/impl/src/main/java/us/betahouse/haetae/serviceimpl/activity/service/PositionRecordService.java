/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.basic.PositionRecordBO;
import us.betahouse.haetae.serviceimpl.activity.request.PositionRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

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
    List<PositionRecordBO> findByUserId(PositionRecordManagerRequest request, OperateContext context);

    /**
     * 通过组织id 查找组织内成员
     * @param request
     * @param context
     * @return
     */
    List<PositionRecordBO> findByOrganizationId(PositionRecordManagerRequest request,OperateContext context);


}
