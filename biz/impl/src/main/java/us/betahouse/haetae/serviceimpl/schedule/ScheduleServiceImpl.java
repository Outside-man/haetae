/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleApplyService;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;

/**
 * 定时任务实现
 *
 * @author dango.yxm
 * @version : ScheduleServiceImpl.java 2018/11/30 12:39 PM dango.yxm
 */
@Component
public class ScheduleServiceImpl implements ScheduleService {

    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LocaleApplyService localeApplyService;

    @Autowired
    private ActivityEntryService activityEntryService;

    /**
     * 结束活动
     */
    @Override
    @Scheduled(cron = ScheduleConstant.AM_TWO_OF_THE_CLOCK)
    public void finishActivity() {
        List<ActivityBO> finishActivities = activityService.systemFinishActivity();
        if (!CollectionUtils.isEmpty(finishActivities)) {
            LoggerUtil.info(LOGGER, "系统结束活动, activities={0}", finishActivities);
        }
        List<LocaleApplyBO> finishLocaleApplies = localeApplyService.systemFinishLocaleApply();
        if (!CollectionUtils.isEmpty(finishLocaleApplies)) {
            LoggerUtil.info(LOGGER, "系统结束场地申请, localeApplies={0}", finishLocaleApplies);
        }
    }


    /**
     * 结束报名
     */
    @Override
    @Scheduled(cron = ScheduleConstant.AM_TWO_OF_THE_CLOCK)
    public void finishActivityEntries() {
        List<ActivityEntryBO> finishActivityEntryList = activityEntryService.systemFinishActivityEntry();
        if (!CollectionUtils.isEmpty(finishActivityEntryList)) {
            LoggerUtil.info(LOGGER, "系统结束报名, activityEntries={0}", finishActivityEntryList);
        }
    }
}
