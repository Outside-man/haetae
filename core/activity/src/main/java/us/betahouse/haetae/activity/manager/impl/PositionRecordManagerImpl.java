/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.PositionRecordRepoService;
import us.betahouse.haetae.activity.manager.PositionRecordManager;
import us.betahouse.haetae.activity.model.PositionRecordBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PositionRecordManagerImpl.java 2018/11/23 0:37 MessiahJK
 */
public class PositionRecordManagerImpl implements PositionRecordManager {

    @Autowired
    PositionRecordRepoService positionRecordRepoService;

    @Override
    public List<PositionRecordBO> findByUserId(String userId) {
        return positionRecordRepoService.queryPositionRecordByUserId(userId);
    }

    @Override
    public List<PositionRecordBO> findByOrganizationId(String organizationId) {
        return positionRecordRepoService.queryPositionRecordByOrganizationId(organizationId);
    }
}
