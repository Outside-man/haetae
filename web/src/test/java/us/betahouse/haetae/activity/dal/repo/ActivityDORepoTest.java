package us.betahouse.haetae.activity.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.HaetaeWebApplication;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.serviceimpl.activity.request.builder.ActivityManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.util.utils.CsvUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HaetaeWebApplication.class)
public class ActivityDORepoTest {

    @Autowired
    ActivityDORepo activityDORepo;

    @Autowired
    private BizIdFactory activityBizFactory;

    @Autowired
    private ActivityService activityService;

    @Test
    public void importit() throws ParseException {
        String filepath = "C:\\Users\\j10k\\Desktop\\活动.csv";
        String[][] csv = CsvUtil.getWithHeader(filepath);
        for (int i = 1; i < csv.length; i++) {
            //22/11/2018 10:43:09.463
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse(csv[i][1]);
            Date end = sdf.parse(csv[i][2]);
            //Date d=new Date();
            ActivityManagerRequestBuilder builder = ActivityManagerRequestBuilder.getInstance()
                    .withUserId("201812010040554783180001201835")
                    .withActivityName(csv[i][0])
                    .withOrganizationMessage("青年志愿者协会")
                    .withStart(date.getTime())
                    .withEnd(end.getTime())
                    .withTerm(TermUtil.getNowTerm())
                    // 以下是可选参数
                    // 描述
                    .withDescription("2019暑期社会实践")
                    .withType(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode());
            activityService.create(builder.build(), new OperateContext());
        }
    }
    @Test
    public void showTest() {
        List<ActivityDO> activityDOList=activityDORepo.findAllByState(ActivityStateEnum.RESTARTED.getCode());
        activityDOList.forEach(System.out::println);
        System.out.println();
        activityDOList=activityDORepo.findAllByStateOrderByActivityIdDesc(ActivityStateEnum.RESTARTED.getCode());
        activityDOList.forEach(System.out::println);
    }
    @Test
    public void pageTest(){
        int page=0;
        int limit=10;
        Pageable pageable = PageRequest.of(page, limit);
        Page<ActivityDO> activityDOPage=activityDORepo.findAllByTermContainsAndStateContainsAndTypeContainsOrderByActivityIdDesc(pageable, "", "","");
        activityDOPage.forEach(System.out::println);
        System.out.println(activityDOPage.getTotalElements());
        System.out.println(activityDOPage.getTotalPages());
        System.out.println(activityDOPage.getNumber());
        System.out.println(activityDOPage.getNumberOfElements());
        System.out.println(activityDOPage.getSize());
        System.out.println(activityDOPage.isFirst());
        System.out.println(activityDOPage.isEmpty());
        System.out.println(activityDOPage.isLast());
        System.out.println("-------------------------------------");
    }
}