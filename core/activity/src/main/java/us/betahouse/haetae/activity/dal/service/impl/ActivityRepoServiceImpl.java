/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.model.PastActivityDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.PastActivityDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 活动仓储服务实现
 *
 * @author MessiahJK
 * @version : ActivityRepoServiceImpl.java 2018/11/17 20:28 MessiahJK
 */
@Service
public class ActivityRepoServiceImpl implements ActivityRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityRepoServiceImpl.class);
    @Autowired
    private ActivityDORepo activityDORepo;

    @Autowired
    private PastActivityDORepo pastActivityDORepo;
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory activityBizFactory;

    /**
     * 查询所有活动
     *
     * @return
     */
    @Override
    public List<ActivityBO> queryAllActivity() {
        List<ActivityDO> activityDOList = activityDORepo.findAll();
        return CollectionUtils.toStream(activityDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityBO> queryActivitiesByState(String state) {
        List<ActivityDO> activityDOList = activityDORepo.findAllByState(state);
        return CollectionUtils.toStream(activityDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActivityBO> findFirst10OrOrderByStart() {
        List<ActivityDO> activityDOList =activityDORepo.findFirst10OrOrderByStart();
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
        List<ActivityDO> activityDOList = activityDORepo.findAllByType(type);
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
        if (StringUtils.isBlank(activityBO.getActivityId())) {
            activityBO.setActivityId(activityBizFactory.getActivityId());
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
        if (StringUtils.isBlank(activityBO.getActivityId()) && !activityDORepo.existsActivityDOByActivityId(activityBO.getActivityId())) {
            LoggerUtil.error(LOGGER, "更新的活动不存在 ActivityBO={0}", activityBO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的活动不存在");
        }
        ActivityDO activityDO = activityDORepo.findByActivityId(activityBO.getActivityId());
        ActivityDO newActivityDO = convert(activityBO);
        if (newActivityDO.getActivityName() != null) {
            activityDO.setActivityName(newActivityDO.getActivityName());
        }
        if (newActivityDO.getDescription() != null) {
            activityDO.setDescription(newActivityDO.getDescription());
        }
        if (newActivityDO.getEnd() != null) {
            activityDO.setEnd(newActivityDO.getEnd());
        }
        if (newActivityDO.getLocation() != null) {
            activityDO.setLocation(newActivityDO.getLocation());
        }
        if (newActivityDO.getOrganizationMessage() != null) {
            activityDO.setOrganizationMessage(newActivityDO.getOrganizationMessage());
        }
        if (newActivityDO.getScore() != null) {
            activityDO.setScore(newActivityDO.getScore());
        }
        if (newActivityDO.getStart() != null) {
            activityDO.setStart(newActivityDO.getStart());
        }
        if (newActivityDO.getState() != null) {
            activityDO.setState(newActivityDO.getState());
        }
        if (newActivityDO.getTerm() != null) {
            activityDO.setTerm(newActivityDO.getTerm());
        }
        if (newActivityDO.getType() != null) {
            activityDO.setType(newActivityDO.getType());
        }
        if (newActivityDO.getUserId() != null) {
            activityDO.setUserId(newActivityDO.getUserId());
        }
        if (newActivityDO.getExtInfo() != null) {
            activityDO.setExtInfo(newActivityDO.getExtInfo());
        }
        return convert(activityDORepo.save(activityDO));
    }

    @Override
    public ActivityBO queryActivityByActivityId(String activityId) {
        return convert(activityDORepo.findByActivityId(activityId));
    }

    @Override
    public List<ActivityBO> queryActivityByActivityIds(List<String> activityIds) {
        List<ActivityDO> activityDOList = activityDORepo.findAllByActivityIdIn(activityIds);
        return CollectionUtils.toStream(activityDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ActivityBO queryActivityByActivityName(String name) {
        return convert(activityDORepo.findByActivityName(name));
    }

    @Override
    public PageList<ActivityBO> queryActivityByTermAndStateAndTypePagerDESC(String term, String status, String type, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return new PageList<>(activityDORepo.findAllByTermContainsAndStateContainsAndTypeContainsOrderByActivityIdDesc(pageable, term, status, type), this::convert);
    }

    @Override
    public PageList<ActivityBO> queryActivityByTermAndStateAndTypePagerASC(String term, String status, String type, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return new PageList<>(activityDORepo.findAllByTermContainsAndStateContainsAndTypeContains(pageable, term, status, type), this::convert);
    }

    @Override
    public PastActivityBO getPastByUserId(String userId) {
        return convert(pastActivityDORepo.findByUserId(userId));
    }

    @Override
    public PastActivityBO getPastByStuId(String stuId) {
        return convert(pastActivityDORepo.findByStuId(stuId));
    }

    @Override
    public PastActivityBO updatePastActivity(String userId, PastActivityBO pastActivityBO) {
        PastActivityDO pastActivityDO=pastActivityDORepo.findByUserId(userId);
        if(pastActivityDO==null){
            return null;
        }
        if (Objects.nonNull((pastActivityBO.getPastLectureActivity()))) {
            pastActivityDO.setPastLectureActivity(pastActivityBO.getPastLectureActivity());
        }
        if (Objects.nonNull(pastActivityBO.getPastPracticeActivity())){
            pastActivityDO.setPastPracticeActivity(pastActivityBO.getPastPracticeActivity());
        }
        if(Objects.nonNull(pastActivityBO.getPastSchoolActivity())){
            pastActivityDO.setPastSchoolActivity(pastActivityBO.getPastSchoolActivity());
        }
        if(Objects.nonNull(pastActivityBO.getPastVolunteerActivityTime())){
            pastActivityDO.setPastVolunteerActivityTime(pastActivityBO.getPastVolunteerActivityTime());
        }
        if(Objects.nonNull(pastActivityBO.getUndistributedStamp())){
            pastActivityDO.setUndistributedStamp(pastActivityBO.getUndistributedStamp());
        }
        return convert(pastActivityDORepo.save(pastActivityDO));
    }

    @Override
    public PastActivityBO createPastActivity(PastActivityBO pastActivityBO) {
        PastActivityDO pastActivityDO=convert(pastActivityBO);
        if(StringUtils.isBlank(pastActivityDO.getPastActivityId())){
            pastActivityDO.setPastActivityId(activityBizFactory.getPastActivityId());
        }
        return convert(pastActivityDORepo.save(pastActivityDO));
    }

    private Object convert(Object o) {
        if(o instanceof ActivityDO){
            return convert((ActivityDO)o);
        }else if(o instanceof ActivityBO){
            return convert((ActivityBO)o);
        }else if(o instanceof PastActivityBO) {
            return convert((PastActivityBO) o);
        }else if(o instanceof PastActivityDO) {
            return convert((PastActivityDO) o);
        }else{
            return null;
        }
    }

    /**
     * 活动DO2BO
     *
     * @param activityDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private ActivityBO convert(ActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }
        ActivityBO activityBO = new ActivityBO();
        activityBO.setActivityId(activityDO.getActivityId());
        activityBO.setActivityName(activityDO.getActivityName());
        activityBO.setType(activityDO.getType());
        activityBO.setOrganizationMessage(activityDO.getOrganizationMessage());
        activityBO.setLocation(activityDO.getLocation());
        activityBO.setStart(activityDO.getStart());
        activityBO.setEnd(activityDO.getEnd());
        activityBO.setScore(activityDO.getScore());
        activityBO.setDescription(activityDO.getDescription());
        activityBO.setCreatorId(activityDO.getUserId());
        activityBO.setState(activityDO.getState());
        activityBO.setTerm(activityDO.getTerm());
        activityBO.setExtInfo(JSON.parseObject(activityDO.getExtInfo(), Map.class));
        return activityBO;
    }

    /**
     * 活动BO2DO
     *
     * @param activityBO
     * @return
     */
    private ActivityDO convert(ActivityBO activityBO) {
        if (activityBO == null) {
            return null;
        }
        ActivityDO activityDO = new ActivityDO();
        activityDO.setActivityId(activityBO.getActivityId());
        activityDO.setActivityName(activityBO.getActivityName());
        activityDO.setType(activityBO.getType());
        activityDO.setOrganizationMessage(activityBO.getOrganizationMessage());
        activityDO.setLocation(activityBO.getLocation());
        activityDO.setStart(activityBO.getStart());
        activityDO.setEnd(activityBO.getEnd());
        activityDO.setScore(activityBO.getScore());
        activityDO.setDescription(activityBO.getDescription());
        activityDO.setUserId(activityBO.getCreatorId());
        activityDO.setState(activityBO.getState());
        activityDO.setTerm(activityBO.getTerm());
        activityDO.setExtInfo(JSON.toJSONString(activityBO.getExtInfo()));
        return activityDO;
    }

    private PastActivityBO convert(PastActivityDO pastActivityDO){
        if(pastActivityDO==null){
            return null;
        }
        PastActivityBO pastActivityBO = new PastActivityBO();
        pastActivityBO.setPastActivityId(pastActivityDO.getPastActivityId());
        pastActivityBO.setUserId(pastActivityDO.getUserId());
        pastActivityBO.setStuId(pastActivityDO.getStuId());
        pastActivityBO.setUndistributedStamp(pastActivityDO.getUndistributedStamp());
        pastActivityBO.setPastSchoolActivity(pastActivityDO.getPastSchoolActivity());
        pastActivityBO.setPastLectureActivity(pastActivityDO.getPastLectureActivity());
        pastActivityBO.setPastVolunteerActivityTime(pastActivityDO.getPastVolunteerActivityTime());
        pastActivityBO.setPastPracticeActivity(pastActivityDO.getPastPracticeActivity());
        return pastActivityBO;
    }
    private PastActivityDO convert(PastActivityBO pastActivityBO){
        if(pastActivityBO==null){
            return null;
        }
        PastActivityDO pastActivityDO = new PastActivityDO();
        pastActivityDO.setPastActivityId(pastActivityBO.getPastActivityId());
        pastActivityDO.setUserId(pastActivityBO.getUserId());
        pastActivityDO.setStuId(pastActivityBO.getStuId());
        pastActivityDO.setUndistributedStamp(pastActivityBO.getUndistributedStamp());
        pastActivityDO.setPastSchoolActivity(pastActivityBO.getPastSchoolActivity());
        pastActivityDO.setPastLectureActivity(pastActivityBO.getPastLectureActivity());
        pastActivityDO.setPastVolunteerActivityTime(pastActivityBO.getPastVolunteerActivityTime());
        pastActivityDO.setPastPracticeActivity(pastActivityBO.getPastPracticeActivity());
        return pastActivityDO;

    }
}
