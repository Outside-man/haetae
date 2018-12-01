package us.betahouse.haetae.activity.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.request.builder.ActivityManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.util.utils.CsvUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityDORepoTest {

    @Autowired
    ActivityDORepo activityDORepo;

    @Autowired
    private BizIdFactory activityBizFactory;

    @Autowired
    private ActivityService activityService;

    @Test
    public void importit(){
        List<ActivityDO> list=new ArrayList<>();
        String filepath="C:\\Users\\j10k\\Desktop\\11\\sc_activity.csv";
        String[][] csv=CsvUtil.getWithoutHeader(filepath);
        for(int i=0;i<csv.length;i++){
            //22/11/2018 10:43:09.463
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            Date date= null;
            try {
                date = sdf.parse(csv[i][12]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 2);
            Date end=calendar.getTime();
            ActivityManagerRequestBuilder builder= ActivityManagerRequestBuilder.getInstance()
                    .withUserId("201812010040554783180001201835")
                    .withActivityName(csv[i][1])
                    .withOrganizationMessage(csv[i][5])
                    .withStart(date.getTime())
                    .withEnd(end.getTime())
                    .withTerm(TermUtil.getNowTerm())
                    // 以下是可选参数
                    // 描述
                    .withDescription(csv[i][9])
                    // 分数
                    .withScore(Long.valueOf(csv[i][2]));
            if(csv[i][7].equals("xyhd")){
                builder.withType(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode());
            }else{
                builder.withType(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode());
            }
            activityService.create(builder.build(), new OperateContext());
        }
    }
}