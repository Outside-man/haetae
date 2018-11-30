/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.model.PositionRecordBO;

import java.util.List;

/**
 * 履历管理器
 *
 * @author MessiahJK
 * @version : PositionRecordManager.java 2018/11/22 23:43 MessiahJK
 */
public interface PositionRecordManager {
    /**
     * 通过用户id 查找履历
     * @param userId
     * @return
     */
    List<PositionRecordBO> findByUserId(String userId);

    /**
     * 通过组织id 查找组织内成员
     * @param organizationId
     * @return
     */
    List<PositionRecordBO> findByOrganizationId(String organizationId);
}
