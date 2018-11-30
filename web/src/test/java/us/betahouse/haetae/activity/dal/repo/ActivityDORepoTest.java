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
    @Test
    public void importit(){
        List<ActivityDO> list=new ArrayList<>();
        String filepath="C:\\Users\\j10k\\Desktop\\11\\sc_activity.csv";
        String[][] csv=CsvUtil.getWithoutHeader(filepath);
        for(int i=0;i<csv.length;i++){
            ActivityDO activityDO=new ActivityDO();
            if(csv[i][7].equals("xyhd")){
                activityDO.setType(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode());
            }else{
                activityDO.setType(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode());
            }
            if(csv[i][11].equals("Running")){
                activityDO.setState(ActivityStateEnum.PUBLISHED.getCode());
            }else if(csv[i][11].equals("Pause")){
                activityDO.setState(ActivityStateEnum.FINISHED.getCode());
            }
            activityDO.setScore(Long.valueOf(csv[i][2]));
            activityDO.setDescription(csv[i][9]);
            activityDO.setTerm(TermUtil.getNowTerm());
            activityDO.setActivityName(csv[i][1]);
            //22/11/2018 10:43:09.463
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
            Date date= null;
            try {
                date = sdf.parse(csv[i][12]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            activityDO.setStart(date);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 2);
            date=calendar.getTime();
            activityDO.setEnd(date);
            activityDO.setActivityId(activityBizFactory.getActivityId());
            //activityDO.setOrganizationMessage();
            list.add(activityDO);
        }
        activityDORepo.saveAll(list);
    }
}