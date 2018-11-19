/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service.impl;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.model.PositionRecordDO;
import us.betahouse.haetae.activity.dal.repo.PositionRecordDORepo;
import us.betahouse.haetae.activity.dal.service.PositionRecordRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.PositionRecordBO;
import utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author MessiahJK
 * @version : PositionRecordRepoServiceImpl.java 2018/11/18 19:38 MessiahJK
 */
public class PositionRecordRepoServiceImpl implements PositionRecordRepoService {

    @Autowired
    PositionRecordDORepo positionRecordDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory bizIdFactory;
    @Override
    public PositionRecordBO createPositionRecord(PositionRecordBO positionRecordBO) {
        if(StringUtils.isBlank(positionRecordBO.getPositionRecordId())){
            positionRecordBO.setPositionRecordId(bizIdFactory.getPositionRecordId());
        }
        return convert(positionRecordDORepo.save(convert(positionRecordBO)));
    }

    /**
     * 通过学号查询履历记录
     *
     * @param stuId
     * @return
     */
    @Override
    public List<PositionRecordBO> queryPositionRecordByStuId(String stuId) {
        List<PositionRecordDO> positionRecordDOList=positionRecordDORepo.findByStuId(stuId);
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
        List<PositionRecordDO> positionRecordDOList=positionRecordDORepo.findByOrganizationId(organizationId);
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
    private PositionRecordDO convert(PositionRecordBO positionRecordBO){
        if (positionRecordBO == null) {
            return null;
        }
        PositionRecordDO positionRecordDO=new PositionRecordDO();
        positionRecordDO.setPositionRecordId(positionRecordBO.getPositionRecordId());
        positionRecordDO.setStuId(positionRecordBO.getStuId());
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
    private PositionRecordBO convert(PositionRecordDO positionRecordDO){
        if (positionRecordDO == null) {
            return null;
        }
        PositionRecordBO positionRecordBO=new PositionRecordBO();
        positionRecordBO.setPositionRecordId(positionRecordDO.getPositionRecordId());
        positionRecordBO.setStuId(positionRecordDO.getStuId());
        positionRecordBO.setOrganizationId(positionRecordDO.getOrganizationId());
        positionRecordBO.setPosition(positionRecordDO.getPosition());
        positionRecordBO.setTerm(positionRecordDO.getTerm());
        positionRecordBO.setStatus(positionRecordDO.getStatus());
        positionRecordBO.setExtInfo(JSON.parseObject(positionRecordDO.getExtInfo(),Map.class));
        return positionRecordBO;
    }
}
