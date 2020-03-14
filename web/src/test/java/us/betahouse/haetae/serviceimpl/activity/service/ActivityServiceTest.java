package us.betahouse.haetae.serviceimpl.activity.service;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.util.utils.CsvUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    ActivityService activityService;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    ActivityManager activityManager;
    @Autowired
    ActivityRepoService activityRepoService;

    @Test
    public void findAllTest(){
        System.out.println(JSON.toJSONString(activityService.findAll(new ActivityManagerRequest(), new OperateContext())));
    }

    @Test
    public void initPastActivity() {
        activityService.initPastActivity();
    }

    //@Transactional
    @Test
    public void initPastActivityRecord(){
        String url = "C:\\Users\\j10k\\Desktop\\17级数据修改.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        PastActivityBO pastActivityBO;
        ActivityRequest activityRequest=new ActivityRequest();
        for (int i = 1; i < csv.length; i++) {
            String[] acsv = csv[i];
            System.out.println(acsv[4]);
            activityRequest.setStuId(acsv[4]);
            pastActivityBO = activityManager.findPast(activityRequest);
            pastActivityBO.setPastLectureActivity(0L);
            pastActivityBO.setPastVolunteerActivityTime(Long.valueOf(acsv[6]));
            pastActivityBO.setPastPracticeActivity(Long.valueOf(acsv[7]));
            pastActivityBO.setPastSchoolActivity(0L);
            pastActivityBO.setUndistributedStamp(Long.valueOf(acsv[5]));
            //System.out.println(pastActivityBO);
            activityRepoService.updatePastActivity(pastActivityBO.getUserId(), pastActivityBO);
        }
    }
}