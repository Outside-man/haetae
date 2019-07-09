package us.betahouse.haetae.activity.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.model.ActivityEntryRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityEntryRecordDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.util.utils.CollectionUtils;

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

    @Autowired
    private ActivityEntryRecordDORepo activityEntryRecordDORepo;

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


    @Override
    public ActivityEntryRecordBO findByActivityEntryIdAndUserId(String activityEntryId, String userId) {
        return convert(activityEntryRecordDORepo.findByActivityEntryIdAndUserId(activityEntryId,userId));
    }



    /**
     * 通过报名信息id查找报名记录数量
     * @param activityEntryId
     * @return
     */
    @Override
    public Long countByActivityEntryId(String activityEntryId) {
        return activityEntryRecordDORepo.countByActivityEntryId(activityEntryId);
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
        activityEntryRecordBO.setNote(activityEntryRecordDO.getNote());
        activityEntryRecordBO.setChoose(activityEntryRecordDO.getChoose());
        activityEntryRecordBO.setExtInfo(JSON.parseObject(activityEntryRecordDO.getExtInfo(), Map.class));
        return activityEntryRecordBO;
    }
}
