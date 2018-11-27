/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.enums.GradesEnum;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 实践章服务
 *
 * @author dango.yxm
 * @version : SchoolActivityStampService.java 2018/11/27 1:50 AM dango.yxm
 */
public class PracticeActivityStampService implements StampService {

    @Autowired
    protected ActivityRecordManager activityRecordManager;

    @Autowired
    protected ActivityRecordRepoService activityRecordRepoService;

    @Override
    public void batchStamp(ActivityStampRequest request, List<String> userIds) {
        GradesEnum grades = GradesEnum.getByCode(request.getGrades());
        AssertUtil.assertNotNull(grades, "实践等第不能为空");

        // 获取参加过的活动记录
        List<ActivityRecordBO> joinedActivityRecords = activityRecordRepoService.parseJoinUserIds(request.getActivityId(), userIds);
        // 获取参加过的userId
        List<String> joinedUserIds = CollectionUtils.toStream(joinedActivityRecords)
                .filter(Objects::nonNull).map(ActivityRecordBO::getUserId)
                .collect(Collectors.toList());

        // 去除参加过的userId
        CollectionUtils.removeDuplicate(userIds, joinedUserIds, String::toString);

        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
    }
}
