/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.stream.Collectors;

/**
 * @author MessiahJK
 * @version : ActivityRecordRepoServiceImpl.java 2018/11/18 12:06 MessiahJK
 */
@Service
public class ActivityRecordRepoServiceImpl implements ActivityRecordRepoService {

    @Autowired
    private ActivityRecordDORepo activityRecordDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory bizIdFactory;

    /**
     * 新增活动记录
     *
     * @param activityRecordBO
     * @return
     */
    @Override
    public ActivityRecordBO createActivityRecord(ActivityRecordBO activityRecordBO) {
        if(StringUtils.isBlank(activityRecordBO.getActivityRecordId())){
            activityRecordBO.setActivityRecordId(bizIdFactory.getActivityRecordId());
        }
        return convert(activityRecordDORepo.save(convert(activityRecordBO)));
    }

    /**
     * 通过用户id获取活动记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<ActivityRecordBO> queryActivityRecordByUserId(String userId) {
        List<ActivityRecordDO> activityRecordDOList=activityRecordDORepo.findByUserId(userId);
        return CollectionUtils.toStream(activityRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 通过用户id和活动类型获取活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<ActivityRecordBO> queryActivityRecordByUserIdAndType(String userId, String type) {
        List<ActivityRecordDO> activityRecordDOList=activityRecordDORepo.findByUserIdAndType(userId, type);
        return CollectionUtils.toStream(activityRecordDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 通过活动id获取活动记录数
     *
     * @param activityId
     * @return
     */
    @Override
    public Long countActivityRecordByActivityId(String activityId) {
        return activityRecordDORepo.countAllByActivityIdEquals(activityId);
    }

    /**
     * 活动记录DO2BO
     *
     * @param activityRecordDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private ActivityRecordBO convert(ActivityRecordDO activityRecordDO){
        if(activityRecordDO==null){
            return null;
        }
        ActivityRecordBO activityRecordBO=new ActivityRecordBO();
        activityRecordBO.setActivityRecordId(activityRecordDO.getActivityRecordId());
        activityRecordBO.setActivityId(activityRecordDO.getActivityId());
        activityRecordBO.setUserId(activityRecordDO.getUserId());
        activityRecordBO.setScannerUserId(activityRecordDO.getScannerUserId());
        activityRecordBO.setExtInfo(JSON.parseObject(activityRecordDO.getExtInfo(),Map.class));
        activityRecordBO.setTime(activityRecordDO.getTime());
        activityRecordBO.setType(activityRecordDO.getType());
        activityRecordBO.setStatus(activityRecordDO.getStatus());
        activityRecordBO.setTeam(activityRecordDO.getTeam());
        activityRecordBO.setGrades(activityRecordDO.getGrades());
        return activityRecordBO;
    }
    private ActivityRecordDO convert(ActivityRecordBO activityRecordBO){
        if(activityRecordBO==null){
            return null;
        }
        ActivityRecordDO activityRecordDO=new ActivityRecordDO();
        activityRecordDO.setActivityRecordId(activityRecordBO.getActivityRecordId());
        activityRecordDO.setActivityId(activityRecordBO.getActivityId());
        activityRecordDO.setUserId(activityRecordBO.getUserId());
        activityRecordDO.setScannerUserId(activityRecordBO.getScannerUserId());
        activityRecordDO.setTime(activityRecordBO.getTime());
        activityRecordDO.setType(activityRecordBO.getType());
        activityRecordDO.setStatus(activityRecordBO.getStatus());
        activityRecordDO.setTeam(activityRecordBO.getTeam());
        activityRecordDO.setGrades(activityRecordBO.getGrades());
        activityRecordDO.setExtInfo(JSON.toJSONString(activityRecordBO.getExtInfo()));
        return activityRecordDO;
    }
}
