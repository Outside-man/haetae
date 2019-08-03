package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityBlacklistBOBuilder;
import us.betahouse.haetae.activity.dal.model.ActivityBlacklistDO;
import us.betahouse.haetae.activity.dal.repo.ActivityBlacklistDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityBlacklistRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.enums.ActivityBlacklistReasonEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.basic.ActivityBlacklistBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 活动黑名单仓储服务实现
 *
 * @author zjb
 * @version : ActivityBlacklistRepoServiceImpl.java 2019/8/3 15:00 zjb
 */
@Service
public class ActivityBlacklistRepoServiceImpl implements ActivityBlacklistRepoService {

    @Autowired
    ActivityBlacklistDORepo activityBlacklistDORepo;

    @Autowired
    private ActivityEntryRepoService activityEntryRepoService;

    @Autowired
    private ActivityEntryRecordRepoService activityEntryRecordRepoService;

    @Autowired
    private ActivityBlacklistRepoService activityBlacklistRepoService;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory activityBizFactory;

    @Override
    public Long countByUserId(String userId) {
        return activityBlacklistDORepo.countByUserId(userId);
    }

    @Override
    public Long countByUserIdAndTerm(String userId, String term) {
        return activityBlacklistDORepo.countByUserIdAndTerm(userId, term);
    }

    @Override
    public List<ActivityBlacklistBO> findAllByUserId(String userId) {
        List<ActivityBlacklistDO> activityBlacklistDOList = activityBlacklistDORepo.findAllByUserId(userId);
        return CollectionUtils.toStream(activityBlacklistDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityBlacklistBO> findAllByUserIdAndTerm(String userId, String term) {
        List<ActivityBlacklistDO> activityBlacklistDOList = activityBlacklistDORepo.findAllByUserIdAndTerm(userId, term);
        return CollectionUtils.toStream(activityBlacklistDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityBlacklistBO createActivityBlacklist(ActivityBlacklistBO activityBlacklistBO) {
        if (StringUtils.isBlank(activityBlacklistBO.getActivityBlacklistId())) {
            activityBlacklistBO.setActivityBlacklistId(activityBizFactory.getActivityBlacklistId());
        }
        return convert(activityBlacklistDORepo.save(convert(activityBlacklistBO)));
    }

    /**
     * 按活动id扫描缺席人员，加入黑名单
     *
     * @param activityId
     * @return
     */
    @Override
    public List<ActivityBlacklistBO> addBlacklistByActivityId(String activityId) {
        List<ActivityBlacklistBO> activityBlacklistBOList = new ArrayList<>();
        for(ActivityEntryBO activityEntryBO:activityEntryRepoService.findAllByActivityId(activityId)){
            for(ActivityEntryRecordBO activityEntryRecordBO:activityEntryRecordRepoService.findAllByActivityEntryId(activityEntryBO.getActivityEntryId())){
                if(activityEntryRecordBO.getAttend()){
                    ActivityBlacklistBO activityBlacklistBO =  ActivityBlacklistBOBuilder.anActivityBlacklistBOBuilder()
                            .withActivityEntryId(activityEntryRecordBO.getActivityEntryId())
                            .withReason(ActivityBlacklistReasonEnum.ABSENCE.getCode())
                            .withTerm(activityEntryBO.getTerm())
                            .withUserId(activityEntryRecordBO.getUserId())
                            .build();
                    activityBlacklistBOList.add(activityBlacklistRepoService.createActivityBlacklist(activityBlacklistBO));
                }
            }
        }
        return activityBlacklistBOList;
    }


    /**
     * 活动黑名单DO2BO
     *
     * @param activityBlacklistDO
     * @return
     */
    private ActivityBlacklistBO convert(ActivityBlacklistDO activityBlacklistDO) {
        if (activityBlacklistDO == null) {
            return null;
        }
        ActivityBlacklistBO activityBlacklistBO = new ActivityBlacklistBO();
        activityBlacklistBO.setActivityBlacklistId(activityBlacklistDO.getActivityBlacklistId());
        activityBlacklistBO.setActivityEntryId(activityBlacklistDO.getActivityEntryId());
        activityBlacklistBO.setReason(activityBlacklistDO.getReason());
        activityBlacklistBO.setTerm(activityBlacklistDO.getTerm());
        activityBlacklistBO.setUserId(activityBlacklistDO.getUserId());
        activityBlacklistBO.setExtInfo(JSON.parseObject(activityBlacklistDO.getExtInfo(), Map.class));
        return activityBlacklistBO;
    }

    /**
     * 活动黑名单BO2DO
     *
     * @param activityBlacklistBO
     * @return
     */
    private ActivityBlacklistDO convert(ActivityBlacklistBO activityBlacklistBO) {
        if (activityBlacklistBO == null) {
            return null;
        }
        ActivityBlacklistDO activityBlacklistDO = new ActivityBlacklistDO();
        activityBlacklistDO.setActivityBlacklistId(activityBlacklistBO.getActivityBlacklistId());
        activityBlacklistDO.setActivityEntryId(activityBlacklistBO.getActivityEntryId());
        activityBlacklistDO.setReason(activityBlacklistBO.getReason());
        activityBlacklistDO.setTerm(activityBlacklistBO.getTerm());
        activityBlacklistDO.setUserId(activityBlacklistBO.getUserId());
        activityBlacklistDO.setExtInfo(JSON.toJSONString(activityBlacklistBO.getExtInfo()));
        return activityBlacklistDO;
    }
}
