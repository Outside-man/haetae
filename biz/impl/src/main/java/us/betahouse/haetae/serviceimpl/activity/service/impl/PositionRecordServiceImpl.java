/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.manager.PositionRecordManager;
import us.betahouse.haetae.activity.model.PositionRecordBO;
import us.betahouse.haetae.activity.request.PositionRecordRequest;
import us.betahouse.haetae.serviceimpl.activity.service.PositionRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PositionRecordServiceImpl.java 2018/11/22 20:58 MessiahJK
 */
public class PositionRecordServiceImpl implements PositionRecordService {
    @Autowired
    PositionRecordManager positionRecordManager;

    @Override
    public List<PositionRecordBO> findByUserId(PositionRecordRequest request, OperateContext context) {
        return positionRecordManager.findByUserId(request.getUserId());
    }

    @Override
    public List<PositionRecordBO> findByOrganizationId(PositionRecordRequest request, OperateContext context) {
        return positionRecordManager.findByOrganizationId(request.getOrganizationId());
    }
}
