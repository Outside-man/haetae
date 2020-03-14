package us.betahouse.haetae.activity.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.repo.UserInfoDORepo;
import us.betahouse.util.utils.CsvUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRecordDORepoTest {

    @Autowired
    ActivityRecordDORepo activityRecordDORepo;
    @Autowired
    ActivityDORepo activityDORepo;
    @Autowired
    UserInfoDORepo userInfoDORepo;
    @Autowired
    private BizIdFactory activityBizFactory;

    @Test
    public void importit() {
        String filepath = "C:\\Users\\j10k\\Desktop\\11\\sc_user_activity.csv";
        String csv[][] = CsvUtil.getWithoutHeader(filepath);
        for (int i = 0; i < csv.length; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(csv[i][8]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][4]).getActivityId());
            System.out.println(i + " " + csv[i][1]);
            activityRecordDO.setUserId(userInfoDORepo.findByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId(userInfoDORepo.findByStuId(csv[i][2]).getUserId());
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDO.setType(activityDORepo.findByActivityName(csv[i][4]).getType());
            activityRecordDO.setTime(Double.valueOf(csv[i][6]).intValue());
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setGmtCreate(date);
            activityRecordDORepo.save(activityRecordDO);
        }
    }


}