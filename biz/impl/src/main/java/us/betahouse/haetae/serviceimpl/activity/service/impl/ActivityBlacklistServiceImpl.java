package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.ActivityBlacklistRepoService;
import us.betahouse.haetae.activity.model.basic.ActivityBlacklistBO;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityBlacklistService;

import java.util.List;

/**
 * 活动黑名单服务实现
 *
 * @author zjb
 * @version : ActivityBlacklistServiceImpl.java 2019/8/3 15:25 zjb
 */
@Service
public class ActivityBlacklistServiceImpl implements ActivityBlacklistService {

    //初始信用分3分
    private static final Long InitCreditScore = 3L;

    @Autowired
    ActivityBlacklistRepoService activityBlacklistRepoService;

    @Override
    public Long countByUserId(String userId) {
        return activityBlacklistRepoService.countByUserId(userId);
    }

    @Override
    public Long getCreditScoreByUserIdAndTerm(String userId, String term) {
        return InitCreditScore - activityBlacklistRepoService.countByUserIdAndTerm(userId, term);
    }

    @Override
    public List<ActivityBlacklistBO> queryAllByUserId(String userId) {
        return activityBlacklistRepoService.findAllByUserId(userId);
    }

    @Override
    public List<ActivityBlacklistBO> queryAllByUserIdAndTerm(String userId, String term) {
        return activityBlacklistRepoService.findAllByUserIdAndTerm(userId, term);
    }
}
