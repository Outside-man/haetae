package us.betahouse.haetae.serviceimpl.activity.service;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.model.PastActivityDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.dal.repo.PastActivityDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;
import us.betahouse.haetae.serviceimpl.activity.manager.StampManager;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityRecordStatistics;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.CsvUtil;
import us.betahouse.util.utils.DateUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

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
    @Autowired
    private ActivityRecordManager activityRecordManager;
    @Autowired
    private ActivityRepoService activityRepoService;
    @Autowired
    private PastActivityDORepo pastActivityDORepo;
    @Test
    public void importStamp() {
        String url = "C:\\Users\\j10k\\Desktop\\第四届课外体育三项赛录入名单.csv";
        List<String> ls = activityRecordService.importStamp(url);
        for (String str : ls) {
            System.out.println(str);
        }
        System.out.println();
        System.out.println(ls.size());
    }

    @Test
    public void updateScannerName(){
        List<ActivityRecordBO> activityRecordBOList=activityRecordManager.findAll();
        Map<String,String> map=new HashMap<>();
        for (ActivityRecordBO record : activityRecordBOList) {
            if (StringUtils.isBlank(record.getScannerName())) {
                String scannerName;
                if(map.containsKey(record.getScannerUserId())){
                    scannerName=map.get(record.getScannerUserId());
                }else {
                    scannerName = userInfoRepoService.queryUserInfoByUserId(record.getScannerUserId()).getRealName();
                    map.put(record.getScannerUserId(), scannerName);
                }
                activityRecordManager.updateScannerName(record.getActivityRecordId(), scannerName);
                record.setScannerName(scannerName);
            }
        }
    }
    @Test
    public void importVolunteerWork(){
        String url = "C:\\Users\\j10k\\Desktop\\2019云栖大会志愿活动录入名单(2).csv";
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
    public void importShenQi(){
        String url = "C:\\Users\\j10k\\Desktop\\2019五四升旗仪式名单.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            request.setActivityId("201904151547343710112210012019");
            request.setScannerUserId("201812010040554783180001201835");
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    @Test
    public void importCXJC(){
        String url = "C:\\Users\\j10k\\Desktop\\初心剧场考核章导入.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            request.setActivityId("201904151545269174388510012019");
            request.setScannerUserId("201812010040554783180001201835");
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    @Test
    public void importLastPartyRecord(){
        String url =  "C:\\Users\\j10k\\Desktop\\2017B、2018A、2018B考核卡(1).csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][2]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setType(ActivityTypeEnum.PARTY_ACTIVITY.getCode());
            activityRecordDO.setTime(0);
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(csv[i][3]);
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }
    @Test
    public void importOneHour(){
        String url =  "C:\\Users\\j10k\\Desktop\\党建活动(1).csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId("201904151539461439186510012019");
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime((int)(Double.valueOf(csv[i][3])*10));
            activityRecordDO.setType("partyTimeActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }


    @Test
    public void importVolunteerActivity(){
        String url = "C:\\Users\\j10k\\Desktop\\2019云栖大会志愿活动录入名单(2).csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            System.out.println(csv[i][3]);
            System.out.println(activityDORepo.findByActivityName(csv[i][3]));
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][3]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime((int)(Double.valueOf(csv[i][2])*10));
            activityRecordDO.setType("volunteerActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }
    @Test
    public void importPracticeActivity() {
        String url = "C:\\Users\\j10k\\Desktop\\社会实践-模板.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
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
    public void importPracticeActivity2() {
        String url = "C:\\Users\\j10k\\Desktop\\2019暑期社会实践考核成绩第二课堂导入名单（补）.csv";
        String[][] csv = CsvUtil.getWithHeader(url);

        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][2]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime(0);
            activityRecordDO.setType("practiceActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            switch (csv[i][5]) {
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
        String url = "C:\\Users\\j10k\\Desktop\\2019暑期社会实践考核成绩第二课堂导入名单（补）.csv";
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
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出12.csv", ',', Charset.forName("GBK"));
        String[] headers = {"学号", "姓名", "校园活动次数", "讲座活动次数", "社会实践次数", "志愿活动次数", "志愿活动时长", "义工活动次数", "义工活动时长","尚未分配活动章","年级","专业","班级"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        for (UserInfoBO userInfoBO : userInfoBOList) {
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[13];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            content[3] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[4] =String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[5] = map.get(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode()).toString();
            content[6] = String.format("%.1f", (double) (map.get("volunteerActivityTime") + pastActivityBO.getPastVolunteerActivityTime()) / 10.0);
            content[7] = map.get(ActivityTypeEnum.VOLUNTEER_WORK.getCode()).toString();
            content[8] = String.format("%.1f", Double.valueOf(map.get("volunteerWorkTime")) / 10.0);
            content[9] = pastActivityBO.getUndistributedStamp().toString();
            content[10]=userInfoBO.getGrade();
            content[11]=userInfoBO.getMajor();
            content[12]=userInfoBO.getClassId();
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    @Test
    public void fetchUserRecordStatistics2() throws IOException {
        String temp1="=IF(J{0}>=1,G{0}+4,G{0})";
        String temp2="=I{0}+J{0}*4";
        String temp3="=F{0}+I{0}+J{0}*4+K{0}";
        String temp4="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((H{0}>7),(F{0}+K{0})<8),AND((F{0}<8),(H{0}<8),(F{0}+K{0})<8,(H{0}+K{0})>7),AND((H{0}+K{0})<8,(F{0}+K{0})<8)),8-F{0}-K{0},0))";
        String temp5="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((F{0}>7),(H{0}+K{0})<8),AND((H{0}<8),(F{0}<8),(F{0}+K{0})<8,(F{0}+K{0})>7),AND((F{0}+K{0})<8,(H{0}+K{0})<8)),8-H{0}-K{0},0))";
        String temp6="=IF((M{0}+N{0})<(16-L{0}),(16-L{0}),(M{0}+N{0}))";
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出"+DateUtil.getYearMonthDay(new Date())+".csv", ',', Charset.forName("GBK"));
        String[] headers ={"学号", "姓名","专业","年级","班级","讲座(实际)","讲座活动次数","校园活动(实际)","校园活动次数","社会实践次数","尚未分配活动章","总章数","最少讲座章","最少活动章","最少总章数"};


        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        int i=1;
        for (UserInfoBO userInfoBO : userInfoBOList) {
            i++;
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[15];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = userInfoBO.getMajor();
            content[3] = userInfoBO.getGrade();
            content[4] = userInfoBO.getClassId();
            content[5] = MessageFormat.format(temp1, String.valueOf(i));
            content[6] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[7] = MessageFormat.format(temp2, String.valueOf(i));
            content[8] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            content[9] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[10] = pastActivityBO.getUndistributedStamp().toString();
            content[11] = MessageFormat.format(temp3, String.valueOf(i));
            content[12] = MessageFormat.format(temp4, String.valueOf(i));
            content[13] = MessageFormat.format(temp5, String.valueOf(i));
            content[14] = MessageFormat.format(temp6, String.valueOf(i));
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }
    @Test
    public void fetchUserRecordStatistics3() throws IOException {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出12.csv", ',', Charset.forName("GBK"));
        String[] headers = {"学号", "姓名", "校园活动次数", "讲座活动次数", "社会实践次数", "志愿活动次数", "志愿活动时长", "义工活动次数", "义工活动时长","年级","专业","班级"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        for (UserInfoBO userInfoBO : userInfoBOList) {
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId(),"2018B");
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[12];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))));
            content[3] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode())));
            content[4] =String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode())));
            content[5] = map.get(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode()).toString();
            content[6] = String.format("%.1f", (double) (map.get("volunteerActivityTime")) / 10.0);
            content[7] = map.get(ActivityTypeEnum.VOLUNTEER_WORK.getCode()).toString();
            content[8] = String.format("%.1f", Double.valueOf(map.get("volunteerWorkTime")) / 10.0);
            content[9]=userInfoBO.getGrade();
            content[10]=userInfoBO.getMajor();
            content[11]=userInfoBO.getClassId();
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    @Test
    public void t11()throws IOException{
        Map<Object,Object[]> t1=new HashMap<>();//LECTURE_ACTIVITY
        Map<Object,Object[]> t2=new HashMap<>();//SCHOOL_ACTIVITY
        Map<Object,Object[]> t3=new HashMap<>();//PRACTICE_ACTIVITY
            for (Object[] objects : activityRecordDORepo.findGroupByActivityTypeAndUserId()) {
                if(objects[3].equals(ActivityTypeEnum.LECTURE_ACTIVITY.getCode())){
                    t1.put(objects[2],objects );
                }
                if(objects[3].equals(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode())){
                    t2.put(objects[2],objects );
                }
                if(objects[3].equals(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode())){
                    t3.put(objects[2],objects );
                }
                System.out.println(JSON.toJSONString(objects));
            }
        CsvWriter csvWriter = new CsvWriter("/Users/rade/Documents/dev-temp/haetae/1导出"+DateUtil.getYearMonthDay(new Date())+".csv", ',', Charset.forName("GBK"));
        String[] headers ={"学号", "姓名","专业","年级","班级","讲座(实际)","讲座活动次数","校园活动(实际)","校园活动次数","社会实践次数","尚未分配活动章","总章数","最少讲座章","最少活动章","最少总章数"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        List<PastActivityDO> pastActivityDOList=pastActivityDORepo.findAll();
        Map<Object,PastActivityDO> t4=new HashMap<>();
        for (PastActivityDO pastActivityDO : pastActivityDOList) {
            t4.put(pastActivityDO.getUserId(), pastActivityDO);
        }
        String temp1="=IF(J{0}>=1,G{0}+4,G{0})";
        String temp2="=I{0}+J{0}*4";
        String temp3="=F{0}+I{0}+J{0}*4+K{0}";
        String temp4="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((H{0}>7),(F{0}+K{0})<8),AND((F{0}<8),(H{0}<8),(F{0}+K{0})<8,(H{0}+K{0})>7),AND((H{0}+K{0})<8,(F{0}+K{0})<8)),8-F{0}-K{0},0))";
        String temp5="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((F{0}>7),(H{0}+K{0})<8),AND((H{0}<8),(F{0}<8),(F{0}+K{0})<8,(F{0}+K{0})>7),AND((F{0}+K{0})<8,(H{0}+K{0})<8)),8-H{0}-K{0},0))";
        String temp6="=IF((M{0}+N{0})<(16-L{0}),(16-L{0}),(M{0}+N{0}))";




        int i=1;
        for (UserInfoBO userInfoBO : userInfoBOList) {
            i++;
//            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
//            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
//            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            PastActivityDO pastActivityDO=t4.get(userInfoBO.getUserId());
            String[] content = new String[15];
            content[0] = userInfoBO.getStuId();
            content[1] = userInfoBO.getRealName();
            content[2] = userInfoBO.getMajor();
            content[3] = userInfoBO.getGrade();
            content[4] = userInfoBO.getClassId();
            content[5] = MessageFormat.format(temp1, String.valueOf(i));
            content[6] = String.valueOf(getLongValue(t1, userInfoBO.getUserId(), 0)+pastActivityDO.getPastLectureActivity());
            //content[6] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[7] = MessageFormat.format(temp2, String.valueOf(i));
            content[8] = String.valueOf((getLongValue(t2, userInfoBO.getUserId(), 0)+pastActivityDO.getPastSchoolActivity()));
            content[9] = String.valueOf(getLongValue(t3, userInfoBO.getUserId(), 0)+pastActivityDO.getPastPracticeActivity());
            //content[8] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            //content[9] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[10] = pastActivityDO.getUndistributedStamp().toString();
            content[11] = MessageFormat.format(temp3, String.valueOf(i));
            content[12] = MessageFormat.format(temp4, String.valueOf(i));
            content[13] = MessageFormat.format(temp5, String.valueOf(i));
            content[14] = MessageFormat.format(temp6, String.valueOf(i));
//            System.out.println(activityRecordStatistics);
//            System.out.println(JSON.toJSONString(content));
            System.out.println(userInfoBO.getUserId());
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    private Long getLongValue(Map<Object, Object[]> map, Object key, int No){
        if(map.get(key)==null)
            return 0L;
        else{
//            System.out.println(map.get(key)[No]);
            BigDecimal ans=(BigDecimal)map.get(key)[No];
            return ans.longValue();
        }
    }
}