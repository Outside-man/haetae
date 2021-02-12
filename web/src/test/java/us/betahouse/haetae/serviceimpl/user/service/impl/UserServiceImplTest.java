package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.common.utils.SubscribeUtil;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

import java.util.*;


/**
  * @Author kana-cr
  * @Date  2020/8/28 16:29
  **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    ActivityManager activityManager;
    @Autowired
    UserService userService;
    @Autowired
    UserManager userManager;
    @Autowired
    UserRepoService userRepoService;
    @Autowired
    ActivityEntryService activityEntryService;
    @Autowired
    ActivityEntryRecordRepoService activityEntryRecordRepoService;

    //作为定时器 早晨七点定时发布订阅消息
    @Test
    public void GetOpenIdByUserId(){

        //查询当天开始的活动的报名信息
       /* List<String> activityIdList;
        activityIdList = CollectionUtils.toStream(activityManager.findAll())
                        .filter(Objects::nonNull)
                        .filter(ActivityBO::canPublish).map(ActivityBO::getActivityId)
                        .collect(Collectors.collectingAndThen(Collectors.toList(),
                                Collections::unmodifiableList));
        System.out.println(activityIdList);*/

       //获取所有 可能有订阅用户的活动ID
        List<String> activityEntryIdList =new ArrayList<>();
        //查询填充 ...
        activityEntryIdList.add("202008241401218227825410062020");
        //根据活动id集 来查询当前活动下的报名人
        for (String id: activityEntryIdList) {
            List<ActivityEntryRecordBO> list = activityEntryRecordRepoService.findAllByActivityEntryId(id);
            for (ActivityEntryRecordBO activityEntryRecordBO:list ) {
                //sub字段 或者别的方式判断没有订阅 就不推送

        //根据userid查询openid
      UserBO userBO = userRepoService.queryByUserId(activityEntryRecordBO.getUserId());
      String openid = userBO.getOpenId();
      userBO=null;
        ActivityEntryPublish activityEntryPublish=new ActivityEntryPublish();
       // BeanUtils.copyProperties(new ActivityEntryBO(),activityEntryPublish);
        //手动装配对象
      //根据openid 预先的templateId 去发布订阅的消息
   //    Boolean ifTrue = SubscribeUtil.publishActivityByOpenId(openid,"appid","secret",activityEntryPublish);
      //false的话 retry一定次数（5-10次）

                activityEntryRecordBO=null;
        }
            list=null;
    }
        activityEntryIdList=null;

    }
}
