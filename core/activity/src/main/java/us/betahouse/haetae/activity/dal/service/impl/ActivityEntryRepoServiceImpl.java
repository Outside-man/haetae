package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityEntryDO;
import us.betahouse.haetae.activity.dal.repo.ActivityEntryDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 活动报名信息仓储服务实现
 *
 * @author zjb
 * @version : ActivityEntryRepoServiceImpl.java 2019/7/7 15:54 zjb
 */
@Service
public class ActivityEntryRepoServiceImpl implements ActivityEntryRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityEntryRepoServiceImpl.class);

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory bizIdFactory;

    @Autowired
    ActivityEntryDORepo activityEntryDORepo;


    /**
     * 通过活动id获取报名信息
     *
     * @param activityId
     * @return
     */
    @Override
    public ActivityEntryBO findByActivityId(String activityId) {
        ActivityEntryDO activityEntryDO = activityEntryDORepo.findByActivityId(activityId);
        return convert(activityEntryDO);
    }

    @Override
    public ActivityEntryBO findByActivityEntryId(String activityEntryId) {
        ActivityEntryDO activityEntryDO = activityEntryDORepo.findByActivityEntryId(activityEntryId);
        return convert(activityEntryDO);
    }

    /**
     * 通过活动id查找报名信息
     *
     * @param activityId
     * @return
     */
    @Override
    public List<ActivityEntryBO> findAllByActivityId(String activityId) {
        List<ActivityEntryDO> activityEntryDOList = activityEntryDORepo.findAllByActivityId(activityId);
        return CollectionUtils.toStream(activityEntryDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * 通过报名状态查找报名信息
     *
     * @param state
     * @return
     */
    @Override
    public List<ActivityEntryBO> findAllByState(String state) {
        List<ActivityEntryDO> activityEntryDOList = activityEntryDORepo.findAllByState(state);
        return CollectionUtils.toStream(activityEntryDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    /**
     * 通过报名状态查找报名信息并按活动id逆序
     *
     * @param state
     * @return
     */
    @Override
    public List<ActivityEntryBO> findAllByStateOrderByActivityIdDesc(String state) {
        List<ActivityEntryDO> activityEntryDOList = activityEntryDORepo.findAllByStateOrderByActivityIdDesc(state);
        return CollectionUtils.toStream(activityEntryDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    @Override
    public PageList<ActivityEntryBO> queryActivityEntryByTermAndStateAndTypeOrderByStartPager(String term, String status, String type, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return new PageList<>(activityEntryDORepo.findAllByTermContainsAndStateContainsAndTypeContainsOrderByStart(pageable, term, status, type), this::convert);
    }

    @Override
    public ActivityEntryBO createActivityEntry(ActivityEntryBO activityEntryBO) {
        if (StringUtils.isBlank(activityEntryBO.getActivityEntryId())) {
            activityEntryBO.setActivityEntryId(bizIdFactory.getActivityEntryId());
        }
        return convert(activityEntryDORepo.save(convert(activityEntryBO)));
    }

    @Override
    public ActivityEntryBO updateActivityEntryByActivityEntryId(ActivityEntryBO activityEntryBO) {
        boolean validateTime = activityEntryBO.getStart().before(activityEntryBO.getEnd());
        AssertUtil.assertTrue(validateTime, "报名开始时间必须早于结束时间");
        ActivityEntryDO activityEntryDO = activityEntryDORepo.findByActivityEntryId(activityEntryBO.getActivityEntryId());
        if (activityEntryDO == null) {
            LoggerUtil.error(LOGGER, "更新的报名信息不存在 ActivityEntryBO={0}", activityEntryDO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的报名信息不存在");
        }
        ActivityEntryDO newActivityEntryDO = convert(activityEntryBO);
        if (newActivityEntryDO.getLinkman() != null) {
            activityEntryDO.setLinkman(newActivityEntryDO.getLinkman());
        }
        if (newActivityEntryDO.getContact() != null) {
            activityEntryDO.setContact(newActivityEntryDO.getContact());
        }
        if (newActivityEntryDO.getStart() != null) {
            activityEntryDO.setStart(newActivityEntryDO.getStart());
        }
        if (newActivityEntryDO.getEnd() != null) {
            activityEntryDO.setEnd(newActivityEntryDO.getEnd());
        }
        if (newActivityEntryDO.getState() != null) {
            activityEntryDO.setState(newActivityEntryDO.getState());
        }
        if (newActivityEntryDO.getNumber() != null) {
            activityEntryDO.setNumber(newActivityEntryDO.getNumber());
        }
        if (newActivityEntryDO.getChoose() != null) {
            activityEntryDO.setChoose(newActivityEntryDO.getChoose());
        }
        if (newActivityEntryDO.getNote() != null) {
            activityEntryDO.setNote(newActivityEntryDO.getNote());
        }
        if (newActivityEntryDO.getTitle() != null) {
            activityEntryDO.setTitle(newActivityEntryDO.getTitle());
        }
        if (newActivityEntryDO.getTop() != null) {
            activityEntryDO.setTop(newActivityEntryDO.getTop());
        }
        if (newActivityEntryDO.getTerm() != null) {
            activityEntryDO.setTerm(newActivityEntryDO.getTerm());
        }
        return convert(activityEntryDORepo.save(activityEntryDO));
    }


    private Object convert(Object o) {
        if(o instanceof ActivityEntryDO){
            return convert((ActivityEntryDO)o);
        }else if(o instanceof ActivityEntryBO){
            return convert((ActivityEntryBO)o);
        }else{
            return null;
        }
    }

    /**
     * 报名信息DO2BO
     * @param activityEntryDO
     * @return
     */
    private ActivityEntryBO convert(ActivityEntryDO activityEntryDO) {
        if (activityEntryDO == null) {
            return null;
        }
        ActivityEntryBO activityEntryBO = new ActivityEntryBO();
        activityEntryBO.setActivityEntryId(activityEntryDO.getActivityEntryId());
        activityEntryBO.setActivityId(activityEntryDO.getActivityId());
        activityEntryBO.setType(activityEntryDO.getType());
        activityEntryBO.setTitle(activityEntryDO.getTitle());
        activityEntryBO.setTerm(activityEntryDO.getTerm());
        activityEntryBO.setState(activityEntryDO.getState());
        activityEntryBO.setNumber(activityEntryDO.getNumber());
        activityEntryBO.setLinkman(activityEntryDO.getLinkman());
        activityEntryBO.setContact(activityEntryDO.getContact());
        activityEntryBO.setStart(activityEntryDO.getStart());
        activityEntryBO.setEnd(activityEntryDO.getEnd());
        activityEntryBO.setChoose(activityEntryDO.getChoose());
        activityEntryBO.setTop(activityEntryDO.getTop());
        activityEntryBO.setNote(activityEntryDO.getNote());
        activityEntryBO.setExtInfo(JSON.parseObject(activityEntryDO.getExtInfo(), Map.class));
        return activityEntryBO;
    }


    /**
     * 报名信息BO2DO
     * @param activityEntryBO
     * @return
     */
    private ActivityEntryDO convert(ActivityEntryBO activityEntryBO) {
        if (activityEntryBO == null) {
            return null;
        }
        ActivityEntryDO activityEntryDO = new ActivityEntryDO();
        activityEntryDO.setActivityEntryId(activityEntryBO.getActivityEntryId());
        activityEntryDO.setActivityId(activityEntryBO.getActivityId());
        activityEntryDO.setType(activityEntryBO.getType());
        activityEntryDO.setTitle(activityEntryBO.getTitle());
        activityEntryDO.setTerm(activityEntryBO.getTerm());
        activityEntryDO.setState(activityEntryBO.getState());
        activityEntryDO.setNumber(activityEntryBO.getNumber());
        activityEntryDO.setLinkman(activityEntryBO.getLinkman());
        activityEntryDO.setContact(activityEntryBO.getContact());
        activityEntryDO.setStart(activityEntryBO.getStart());
        activityEntryDO.setEnd(activityEntryBO.getEnd());
        activityEntryDO.setChoose(activityEntryBO.getChoose());
        activityEntryDO.setTop(activityEntryBO.getTop());
        activityEntryDO.setNote(activityEntryBO.getNote());
        activityEntryDO.setExtInfo(JSON.toJSONString(activityEntryBO.getExtInfo()));
        return activityEntryDO;
    }
}
