package us.betahouse.haetae.serviceimpl.activity.service;

import com.csvreader.CsvWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;
import us.betahouse.haetae.serviceimpl.activity.manager.StampManager;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityRecordStatistics;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.model.UserInfoDO;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.CsvUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private StampManager stampManager;

    @Test
    public void importStamp() {
        //C:\Users\j10k\Documents\Tencent Files\1033161038\FileRecv\第一届课达杯手绘大赛-数据导入.csv
        String url = "C:\\Users\\j10k\\Desktop\\校园活动（第四日）.csv";
        List<String> ls = activityRecordService.importStamp(url);
        for (String str : ls) {
            System.out.println(str);
        }
        System.out.println();
        System.out.println(ls.size());
    }

    @Test
    public void importVolunteerWork(){
        String url = "C:\\Users\\j10k\\Desktop\\义工活动（第四日）.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            request.setActivityId("201812032305164919790210012018");
            request.setScannerUserId("201812010040554783180001201835");
            request.setVolunteerWorkName(csv[i][2]);
            request.setTime(Double.valueOf(csv[i][3]));
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    @Test
    public void importVolunteerActivity(){
        String url = "C:\\Users\\j10k\\Desktop\\志愿活动（第四日）.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[1][3]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime((int)(Double.valueOf(csv[i][2])*10));
            activityRecordDO.setType("volunteerActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDORepo.save(activityRecordDO);
        }
    }
    @Test
    public void importPracticeActivity() {
        String url = "C:\\Users\\j10k\\Desktop\\社会实践（第四日）.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            System.out.println(csv[i][4]);
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][2]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime(0);
            activityRecordDO.setType("practiceActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            switch (csv[i][6]) {
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
    public void check() {
        String url = "C:\\Users\\j10k\\Desktop\\2018级新生体检-数据导入.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        List<String> notStampStuIds = new ArrayList<>();
        for (int i = 1; i < csv.length; i++) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(csv[i][1]);
            if (userInfoBO == null) {
                System.out.println(i + " " + csv[i][1] + " " + null);
                notStampStuIds.add(csv[i][1]);
            } else if (!userInfoBO.getRealName().equals(csv[i][0])) {
                System.out.println(i + " " + csv[i][1] + " name " + userInfoBO.getRealName() + " " + csv[i][0]);
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
    public void fetchUserRecordStatistics1() throws IOException {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出1.csv", ',', Charset.forName("GBK"));
        String[] headers = {"学号", "姓名", "校园活动次数", "讲座活动次数", "社会实践次数", "志愿活动次数", "志愿活动时长", "义工活动次数", "义工活动时长"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        for (UserInfoBO userInfoBO : userInfoBOList) {
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId(),"2018A");
            System.out.println(activityRecordStatistics);
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[9];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()).toString();
            content[3] = map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()).toString();
            content[4] = map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()).toString();
            content[5] = map.get(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode()).toString();
            content[6] = String.format("%.1f", Double.valueOf(map.get("volunteerActivityTime")) / 10.0);
            content[7] = map.get(ActivityTypeEnum.VOLUNTEER_WORK.getCode()).toString();
            content[8] = String.format("%.1f", Double.valueOf(map.get("volunteerWorkTime")) / 10.0);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }
}