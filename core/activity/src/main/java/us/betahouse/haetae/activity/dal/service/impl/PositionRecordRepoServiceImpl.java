/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.PositionRecordDO;
import us.betahouse.haetae.activity.dal.repo.PositionRecordDORepo;
import us.betahouse.haetae.activity.dal.service.PositionRecordRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.basic.PositionRecordBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 履历仓储服务实现
 *
 * @author MessiahJK
 * @version : PositionRecordRepoServiceImpl.java 2018/11/18 19:38 MessiahJK
 */
@Service
public class PositionRecordRepoServiceImpl implements PositionRecordRepoService {

    @Autowired
    private PositionRecordDORepo positionRecordDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory activityBizFactory;

    @Override
    public PositionRecordBO createPositionRecord(PositionRecordBO positionRecordBO) {
        if (StringUtils.isBlank(positionRecordBO.getPositionRecordId())) {
            positionRecordBO.setPositionRecordId(activityBizFactory.getPositionRecordId());
        }
        return convert(positionRecordDORepo.save(convert(positionRecordBO)));
    }

    /**
     * 通过用户id查询履历记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<PositionRecordBO> queryPositionRecordByUserId(String userId) {
        List<PositionRecordDO> positionRecordDOList = positionRecordDORepo.findByUserId(userId);
        return CollectionUtils.toStream(positionRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 通过组织id查询履历记录
     *
     * @param organizationId
     * @return
     */
    @Override
    public List<PositionRecordBO> queryPositionRecordByOrganizationId(String organizationId) {
        List<PositionRecordDO> positionRecordDOList = positionRecordDORepo.findByOrganizationId(organizationId);
        return CollectionUtils.toStream(positionRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 履历BO2DO
     *
     * @param positionRecordBO
     * @return
     */
    private PositionRecordDO convert(PositionRecordBO positionRecordBO) {
        if (positionRecordBO == null) {
            return null;
        }
        PositionRecordDO positionRecordDO = new PositionRecordDO();
        positionRecordDO.setPositionRecordId(positionRecordBO.getPositionRecordId());
        positionRecordDO.setUserId(positionRecordBO.getUserId());
        positionRecordDO.setOrganizationId(positionRecordBO.getOrganizationId());
        positionRecordDO.setPosition(positionRecordBO.getPosition());
        positionRecordDO.setTerm(positionRecordBO.getTerm());
        positionRecordDO.setStatus(positionRecordBO.getStatus());
        positionRecordDO.setExtInfo(JSON.toJSONString(positionRecordBO.getExtInfo()));
        return positionRecordDO;
    }

    /**
     * 履历DO2BO
     *
     * @param positionRecordDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private PositionRecordBO convert(PositionRecordDO positionRecordDO) {
        if (positionRecordDO == null) {
            return null;
        }
        PositionRecordBO positionRecordBO = new PositionRecordBO();
        positionRecordBO.setPositionRecordId(positionRecordDO.getPositionRecordId());
        positionRecordBO.setUserId(positionRecordDO.getUserId());
        positionRecordBO.setOrganizationId(positionRecordDO.getOrganizationId());
        positionRecordBO.setPosition(positionRecordDO.getPosition());
        positionRecordBO.setTerm(positionRecordDO.getTerm());
        positionRecordBO.setStatus(positionRecordDO.getStatus());
        positionRecordBO.setExtInfo(JSON.parseObject(positionRecordDO.getExtInfo(), Map.class));
        return positionRecordBO;
    }
}
