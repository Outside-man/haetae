/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.manager.PositionRecordManager;
import us.betahouse.haetae.activity.model.basic.PositionRecordBO;
import us.betahouse.haetae.serviceimpl.activity.request.PositionRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.service.PositionRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PositionRecordServiceImpl.java 2018/11/22 20:58 MessiahJK
 */
@Service
public class PositionRecordServiceImpl implements PositionRecordService {
    @Autowired
    PositionRecordManager positionRecordManager;

    @Override
    public List<PositionRecordBO> findByUserId(PositionRecordManagerRequest request, OperateContext context) {
        return positionRecordManager.findByUserId(request.getUserId());
    }

    @Override
    public List<PositionRecordBO> findByOrganizationId(PositionRecordManagerRequest request, OperateContext context) {
        return positionRecordManager.findByOrganizationId(request.getOrganizationId());
    }
}
