/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service.impl;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.ActivityBO;
import utils.CollectionUtils;

/**
 * @author MessiahJK
 * @version : ActivityRepoServiceImpl.java 2018/11/17 20:28 MessiahJK
 */
@Service
public class ActivityRepoServiceImpl implements ActivityRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityRepoServiceImpl.class);
    @Autowired
    private ActivityDORepo activityDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory bizIdFactory;

    /**
     * 查询所有活动
     *
     * @return
     */
    @Override
    public List<ActivityBO> queryAllActivity() {
        List<ActivityDO> activityDOList=activityDORepo.findAll();
        return CollectionUtils.toStream(activityDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 通过类型查询活动
     *
     * @param type
     * @return
     */
    @Override
    public List<ActivityBO> queryActivityByType(String type) {
        List<ActivityDO> activityDOList=activityDORepo.findByType(type);
        return CollectionUtils.toStream(activityDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 新增活动
     *
     * @param activityBO
     * @return
     */
    @Override
    public ActivityBO createActivity(ActivityBO activityBO) {
        if(StringUtils.isBlank(activityBO.getActivityId())){
            activityBO.setActivityId(bizIdFactory.getActivityId());
        }
        return convert(activityDORepo.save(convert(activityBO)));
    }

    /**
     * 更新活动
     *
     * @param activityBO
     * @return
     */
    @Override
    public ActivityBO updateActivity(ActivityBO activityBO) {
        if(StringUtils.isBlank(activityBO.getActivityId())){
            return null;
        }
        return convert(activityDORepo.save(convert(activityBO)));
    }

    /**
     * 活动DO2BO
     *
     * @param activityDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private ActivityBO convert(ActivityDO activityDO){
        if(activityDO==null){
            return null;
        }
        ActivityBO activityBO=new ActivityBO();
        activityBO.setActivityId(activityDO.getActivityId());
        activityBO.setActivityName(activityDO.getActivityName());
        activityBO.setType(activityDO.getType());
        activityBO.setOrganizationMessage(activityDO.getOrganizationMessage());
        activityBO.setLocation(activityDO.getLocation());
        activityBO.setDefaultTime(activityDO.getDefaultTime());
        activityBO.setStart(activityDO.getStart());
        activityBO.setEnd(activityDO.getEnd());
        activityBO.setScore(activityDO.getScore());
        activityBO.setDescription(activityDO.getDescription());
        activityBO.setUserId(activityDO.getUserId());
        activityBO.setState(activityDO.getState());
        activityBO.setTeam(activityDO.getTeam());
        activityBO.setExtInfo(JSON.parseObject(activityDO.getExtInfo(),Map.class));
        return activityBO;
    }

    /**
     * 活动BO2DO
     * @param activityBO
     * @return
     */
    private ActivityDO convert(ActivityBO activityBO){
        if(activityBO==null){
            return null;
        }
        ActivityDO activityDO=new ActivityDO();
        activityDO.setActivityId(activityBO.getActivityId());
        activityDO.setActivityName(activityBO.getActivityName());
        activityDO.setType(activityBO.getType());
        activityDO.setOrganizationMessage(activityBO.getOrganizationMessage());
        activityDO.setLocation(activityBO.getLocation());
        activityDO.setDefaultTime(activityBO.getDefaultTime());
        activityDO.setStart(activityBO.getStart());
        activityDO.setEnd(activityBO.getEnd());
        activityDO.setScore(activityBO.getScore());
        activityDO.setDescription(activityBO.getDescription());
        activityDO.setUserId(activityBO.getUserId());
        activityDO.setState(activityBO.getState());
        activityDO.setTeam(activityBO.getTeam());
        activityDO.setExtInfo(JSON.toJSONString(activityBO.getExtInfo()));
        return null;
    }
}
