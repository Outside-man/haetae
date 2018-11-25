package us.betahouse.haetae.activity.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.model.ActivityBO;

import java.util.Date;

import static org.junit.Assert.*;

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
                .withDefaultTime(12)
                .withDescription("test")
                .withLocation("格致楼")
                .withEnd(new Date())
                .withStart(new Date())
                .withOrganizationMessage("o")
                .withScore(12L)
                .withUserId("17905219")
                .withTeam("2018A")
                .build();
        System.out.println(activityRepoService.createActivity(activityBO));
    }

    @Test
    public void updateActivity() {
    }

    @Test
    public void queryActivityByActivityId() {
    }
}