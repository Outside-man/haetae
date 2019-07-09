package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityEntryDO;
import us.betahouse.haetae.activity.dal.repo.ActivityEntryDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRepoService;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.util.utils.CollectionUtils;

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
    public PageList<ActivityEntryBO> queryActivityEntryByTermAndStateAndTypePagerASC(String term, String status, String type, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        return new PageList<>(activityEntryDORepo.findAllByTermContainsAndStateContainsAndTypeContains(pageable, term, status, type), this::convert);
    }


    private Object convert(Object o) {
        if(o instanceof ActivityEntryDO){
            return convert((ActivityEntryDO)o);
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
}
