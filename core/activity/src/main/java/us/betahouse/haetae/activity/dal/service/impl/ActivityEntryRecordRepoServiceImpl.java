package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.model.ActivityEntryRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityEntryRecordDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.enums.ActivityEntryRecordStateEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 活动报名记录仓储服务实现
 *
 * @author zjb
 * @version : ActivityEntryRecordRepoServiceImpl.java 2019/7/7 15:43 zjb
 */
@Service
public class ActivityEntryRecordRepoServiceImpl implements ActivityEntryRecordRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityEntryRecordRepoServiceImpl.class);

    @Autowired
    private ActivityEntryRepoService activityEntryRepoService;

    @Autowired
    private ActivityEntryRecordDORepo activityEntryRecordDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory activityBizFactory;

    /**
     * 通过用户id查找报名记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<ActivityEntryRecordBO> findAllByUserId(String userId) {
        List<ActivityEntryRecordDO> activityEntryRecordDOList = activityEntryRecordDORepo.findAllByUserId(userId);
        return CollectionUtils.toStream(activityEntryRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 通过报名信息id查找报名记录
     *
     * @param activityEntryId
     * @return
     */
    @Override
    public List<ActivityEntryRecordBO> findAllByActivityEntryId(String activityEntryId) {
        List<ActivityEntryRecordDO> activityEntryRecordDOList = activityEntryRecordDORepo.findAllByActivityEntryId(activityEntryId);
        return CollectionUtils.toStream(activityEntryRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 通过报名信息id和用户id获取报名记录
     * @param activityEntryId
     * @param userId
     * @return
     */
    @Override
    public ActivityEntryRecordBO findByActivityEntryIdAndUserId(String activityEntryId, String userId) {
        return convert(activityEntryRecordDORepo.findByActivityEntryIdAndUserId(activityEntryId,userId));
    }


    /**
     * 更新报名记录
     * @param activityEntryRecordBO
     * @return
     */
    @Override
    public ActivityEntryRecordBO updateActivityEntryRecord(ActivityEntryRecordBO activityEntryRecordBO) {
        if (StringUtils.isBlank(activityEntryRecordBO.getActivityEntryId()) && StringUtils.isBlank(activityEntryRecordBO.getUserId())
                &&!activityEntryRecordDORepo.existsActivityEntryRecordDOByActivityEntryIdAndUserId(activityEntryRecordBO.getActivityEntryId(),activityEntryRecordBO.getUserId())) {
            LoggerUtil.error(LOGGER, "更新的报名记录不存在 ActivityEntryRecordBO={0}", activityEntryRecordBO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的报名记录不存在");
        }
        ActivityEntryRecordDO activityEntryRecordDO = activityEntryRecordDORepo.findByActivityEntryIdAndUserId(activityEntryRecordBO.getActivityEntryId(),activityEntryRecordBO.getUserId());
        ActivityEntryRecordDO newActivityEntryRecordDO = convert(activityEntryRecordBO);
        if (newActivityEntryRecordDO.getActivityEntryRecordId() != null) {
            activityEntryRecordDO.setActivityEntryRecordId(newActivityEntryRecordDO.getActivityEntryRecordId());
        }
        if (newActivityEntryRecordDO.getActivityEntryId() != null) {
            activityEntryRecordDO.setActivityEntryId(newActivityEntryRecordDO.getActivityEntryId());
        }
        if (newActivityEntryRecordDO.getUserId() != null) {
            activityEntryRecordDO.setUserId(newActivityEntryRecordDO.getUserId());
        }
        if (newActivityEntryRecordDO.getAttend() != null) {
            activityEntryRecordDO.setAttend(newActivityEntryRecordDO.getAttend());
        }
        if (newActivityEntryRecordDO.getState() != null) {
            activityEntryRecordDO.setState(newActivityEntryRecordDO.getState());
        }
        if (newActivityEntryRecordDO.getNote() != null) {
            activityEntryRecordDO.setNote(newActivityEntryRecordDO.getNote());
        }
        if (newActivityEntryRecordDO.getChoose() != null) {
            activityEntryRecordDO.setChoose(newActivityEntryRecordDO.getChoose());
        }
        if (newActivityEntryRecordDO.getExtInfo() != null) {
            activityEntryRecordDO.setExtInfo(newActivityEntryRecordDO.getExtInfo());
        }
        return convert(activityEntryRecordDORepo.save(activityEntryRecordDO));
    }

    /**
     * 新增报名记录
     *
     * @param activityEntryRecordBO
     * @return
     */
    @Override
    public ActivityEntryRecordBO createActivityEntryRecord(ActivityEntryRecordBO activityEntryRecordBO) {
        if (StringUtils.isBlank(activityEntryRecordBO.getActivityEntryRecordId())) {
            activityEntryRecordBO.setActivityEntryRecordId(activityBizFactory.getActivityEntryRecordId());
        }
        activityEntryRecordBO.setState(ActivityEntryRecordStateEnum.SIGN_UP.getCode());
        return convert(activityEntryRecordDORepo.save(convert(activityEntryRecordBO)));
    }


    /**
     * 标记出席
     *
     * @param activityId
     * @return
     */
    @Override
    @Transactional
    public List<ActivityEntryRecordBO> attend(String userId,String activityId) {
        List<ActivityEntryRecordBO> activityEntryRecordBOList = new ArrayList<>();
        for(ActivityEntryBO activityEntryBO:activityEntryRepoService.findAllByActivityId(activityId)){
            ActivityEntryRecordDO activityEntryRecordDO =activityEntryRecordDORepo.findByActivityEntryIdAndUserId(activityEntryBO.getActivityEntryId(),userId);
            if (activityEntryRecordDO != null) {
                activityEntryRecordDO.setAttend(true);
                activityEntryRecordBOList.add(convert(activityEntryRecordDORepo.save(activityEntryRecordDO)));
            }

        }
        return activityEntryRecordBOList;
    }


    /**
     * 通过报名信息id查找报名记录数量
     * @param activityEntryId
     * @return
     */
    @Override
    public Long countByActivityEntryIdAndState(String activityEntryId,String state) {
        return activityEntryRecordDORepo.countByActivityEntryIdAndState(activityEntryId, state);
    }

    /**
     * 报名记录DO2BO
     *
     * @param activityEntryRecordDO
     * @return
     */
    private ActivityEntryRecordBO convert(ActivityEntryRecordDO activityEntryRecordDO) {
        if (activityEntryRecordDO == null) {
            return null;
        }
        ActivityEntryRecordBO activityEntryRecordBO = new ActivityEntryRecordBO();
        activityEntryRecordBO.setActivityEntryRecordId(activityEntryRecordDO.getActivityEntryRecordId());
        activityEntryRecordBO.setActivityEntryId(activityEntryRecordDO.getActivityEntryId());
        activityEntryRecordBO.setUserId(activityEntryRecordDO.getUserId());
        activityEntryRecordBO.setAttend(activityEntryRecordDO.getAttend());
        activityEntryRecordBO.setState(activityEntryRecordDO.getState());
        activityEntryRecordBO.setNote(activityEntryRecordDO.getNote());
        activityEntryRecordBO.setChoose(activityEntryRecordDO.getChoose());
        activityEntryRecordBO.setExtInfo(JSON.parseObject(activityEntryRecordDO.getExtInfo(), Map.class));
        return activityEntryRecordBO;
    }

    /**
     * 报名记录BO2DO
     *
     * @param activityEntryRecordBO
     * @return
     */
    private ActivityEntryRecordDO convert(ActivityEntryRecordBO activityEntryRecordBO) {
        if (activityEntryRecordBO == null) {
            return null;
        }
        ActivityEntryRecordDO activityEntryRecordDO = new ActivityEntryRecordDO();
        activityEntryRecordDO.setActivityEntryRecordId(activityEntryRecordBO.getActivityEntryRecordId());
        activityEntryRecordDO.setActivityEntryId(activityEntryRecordBO.getActivityEntryId());
        activityEntryRecordDO.setUserId(activityEntryRecordBO.getUserId());
        activityEntryRecordDO.setAttend(activityEntryRecordBO.getAttend());
        activityEntryRecordDO.setNote(activityEntryRecordBO.getNote());
        activityEntryRecordDO.setState(activityEntryRecordBO.getState());
        activityEntryRecordDO.setChoose(activityEntryRecordBO.getChoose());
        activityEntryRecordDO.setExtInfo(JSON.toJSONString(activityEntryRecordBO.getExtInfo()));
        return activityEntryRecordDO;
    }
}
