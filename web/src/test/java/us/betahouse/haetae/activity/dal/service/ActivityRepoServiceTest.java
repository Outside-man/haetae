package us.betahouse.haetae.activity.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRepoServiceTest {
    @Autowired
    private ActivityRepoService activityRepoService;

    @Test
    public void queryAllActivity() {
    }

    @Test
    public void queryActivityByType() {
    }

    @Test
    public void createActivity() {
        ActivityBO activityBO = ActivityBOBuilder.getInstance()
                .withActivityName("校园活动")
                .withType("xyhd")
                .withDescription("test")
                .withLocation("格致楼")
                .withEnd(new Date())
                .withStart(new Date())
                .withOrganizationMessage("o")
                .withScore(12L)
                .withCreatorId("17905219")
                .withTerm("2018A")
                .build();
        System.out.println(activityRepoService.createActivity(activityBO));
    }

    @Test
    public void updateActivity() {
    }

    @Test
    public void queryActivityByActivityId() {
    }

    @Test
    public void queryActivityByTermAndStateAndTypePager() {
        String term="2018A";
        String status=ActivityStateEnum.RESTARTED.getCode();
        String type=ActivityTypeEnum.SCHOOL_ACTIVITY.getCode();
        Integer page=0;
        Integer limit=10;
        PageList<ActivityBO> activityBOPageList=activityRepoService.queryActivityByTermAndStateAndTypePagerDESC(term, status, type, page, limit);
        for(ActivityBO activityBO:activityBOPageList.getContent()){
            System.out.println(activityBO);
        }
        System.out.println(activityBOPageList.getNumber());
        System.out.println(activityBOPageList.getNumberOfElements());
        System.out.println(activityBOPageList.getSize());
        System.out.println(activityBOPageList.getTotalElements());
        System.out.println(activityBOPageList.getTotalPages());
        System.out.println(activityBOPageList.isEnd());
        System.out.println(activityBOPageList.isFirst());
    }
}