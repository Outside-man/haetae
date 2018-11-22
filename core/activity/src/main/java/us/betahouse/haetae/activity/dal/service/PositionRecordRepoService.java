/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.PositionRecordBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PositionRecordRepoService.java 2018/11/17 20:02 MessiahJK
 */
public interface PositionRecordRepoService {

    /**
     * 创建履历
     *
     * @param positionRecordBO
     * @return
     */
    PositionRecordBO createPositionRecord(PositionRecordBO positionRecordBO);

    /**
     * 通过学号查询履历
     *
     * @param userId
     * @return
     */
    List<PositionRecordBO> queryPositionRecordByUserId(String userId);

    /**
     * 通过组织id查询履历
     * @param organizationId
     * @return
     */
    List<PositionRecordBO> queryPositionRecordByOrganizationId(String organizationId);


}
