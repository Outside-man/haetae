package us.betahouse.haetae.serviceimpl.activity.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityRecordStatistics;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.CsvUtil;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRecordServiceTest {

    @Autowired
    private ActivityRecordService activityRecordService;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private ActivityRecordDORepo activityRecordDORepo;
    @Autowired
    private ActivityDORepo activityDORepo;
    @Autowired
    private BizIdFactory activityBizFactory;

    @Test
    public void importStamp() {
        //C:\Users\j10k\Documents\Tencent Files\1033161038\FileRecv\第一届课达杯手绘大赛-数据导入.csv
        String url="C:\\Users\\j10k\\Desktop\\第三届“话天下”演讲比赛-数据导入.csv";
        List<String> ls=activityRecordService.importStamp(url);
        for(String str:ls) {
            System.out.println(str);
        }
        System.out.println();
        System.out.println(ls.size());
    }
    @Test
    public void importPracticeActivity(){
        String url="C:\\Users\\j10k\\Desktop\\第二课堂_社会实践导入3.0.csv";
        String[][] csv=CsvUtil.getWithHeader(url);
        for(int i=1;i<csv.length;i++){
            ActivityRecordDO activityRecordDO=new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][3]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime(0);
            activityRecordDO.setType("practiceActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            switch (csv[i][7]){
                case "优秀":
                    activityRecordDO.setGrades(GradesConstant.EXCELLENT);
                    break;
                case "不合格":
                    activityRecordDO.setGrades(GradesConstant.FAIL);
                    break;
                case "合格":
                    activityRecordDO.setGrades(GradesConstant.PASS);
                    break;
                    default:
                        System.out.println(i);
                        assert false;
            }
            activityRecordDORepo.save(activityRecordDO);
        }
    }
    @Test
    public void check(){
        String url="C:\\Users\\j10k\\Desktop\\第三届“话天下”演讲比赛-数据导入.csv";
        String[][] csv=CsvUtil.getWithHeader(url);
        List<String> notStampStuIds = new ArrayList<>();
        for(int i=1;i<csv.length;i++){
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(csv[i][1]);
            if (userInfoBO == null) {
                System.out.println(i+" "+csv[i][1]+" "+null);
                notStampStuIds.add(csv[i][1]);
            }else if(!userInfoBO.getRealName().equals(csv[i][0])){
                System.out.println(i+" "+csv[i][1]+" name "+userInfoBO.getRealName()+" "+csv[i][0]);
                notStampStuIds.add(csv[i][1]);
            }
        }
        System.out.println(notStampStuIds.size());
    }

    @Test
    public void fetchUserRecordStatistics() {
        ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics("201811302142241446120001201817");
        System.out.println(activityRecordStatistics);
    }

    @Test
    public void fetchUserRecordStatistics1() {
    }
}