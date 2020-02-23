package us.betahouse.haetae.certificate.dal.service;


import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.certificate.builder.CertificateBOBuilder;
import us.betahouse.haetae.certificate.enums.CertificateStateEnum;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.CsvUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 证书导入测试类
 *
 * @author Rade
 * @date 2020-02-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QualificationsRepoServiceTest {

    @Autowired
    private QualificationsRepoService qualificationsRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private SkillRepoService skillRepoService;

    @Autowired
    private CompetitionRepoService competitionRepoService;

//    /**
//     * 导入英语四六级证书
//     */
//    @Test
//    public void importEnglishCertificate() throws ParseException {
//        String filepath = "C:\\Users\\caopeng\\Desktop\\四六级证书模板.csv";
//        String[][] csv = CsvUtil.getWithHeader(filepath);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
////        if(csv[0][0].equals("学号")&&csv[0][1].equals("发证时间")&&csv[0][2].equals("分数")&&csv[0][3].equals("级别")){
////            System.out.println("继续");
////        }else{
////            return;
////        }
//        for (int i = 1; i < csv.length; i++) {
//            UserInfoBO userInfoBO1 = userInfoRepoService.queryUserInfoByStuId(csv[i][0]);
//            String userid = userInfoBO1.getUserId();
//            Date publishTime = sdf.parse(csv[i][1]);
//            CertificateBOBuilder requestBuilder = CertificateBOBuilder.getInstance()
//                    .withConfirmUserId("审核员id")
//                    .withUserID(userid)
//                    .withCertificatePublishTime(publishTime)
//                    .withStatus(CertificateStateEnum.APPROVED.getCode())
//                    .withCertificateName("英语四六级证书")
//                    .withCertificateOrganization("教育部高等教育司")
//                    .withType(CertificateTypeEnum.CET_4_6.getCode())
//                    .withCertificateGrade(csv[i][2])
//                    .withRank(csv[i][3]);
//            CertificateBO certificateBO = qualificationsRepoService.create(requestBuilder.build());
//            System.out.println(certificateBO.getCertificateId());
//        }
//    }

    /**
     * 后台管理员导入四六级证书
     */
    @Test
    public void importCET_4_6Certificate() {

        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/rade/Documents/学校/Betahouse/证书录入/证书导入模板/四六级-学生证书导入模板.xlsx"));
        List<Map<String, Object>> readAll = reader.readAll();
        for (Map<String, Object> map : readAll) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(String.valueOf(map.get("学号")));
            String userid = userInfoBO.getUserId();
            CertificateBOBuilder requestBuilder = CertificateBOBuilder.getInstance()
//                    .withConfirmUserId("审核员id")
                    .withUserID(userid)
                    .withCertificatePublishTime((Date) map.get("证书颁发时间"))
                    .withCertificateNumber(String.valueOf(map.get("准考证号")))
                    .withStatus(CertificateStateEnum.APPROVED.getCode())
                    .withCertificateName("英语四六级证书")
                    .withCertificateOrganization("教育部高等教育司")
                    .withType(CertificateTypeEnum.CET_4_6.getCode())
                    .withCertificateGrade(String.valueOf(map.get("成绩")))
                    .withRank(String.valueOf(map.get("证书等级")));
            CertificateBO certificateBO = qualificationsRepoService.create(requestBuilder.build());
            System.out.println(certificateBO.toString());
        }
    }

    /**
     * 后台管理员导入技能证书
     */
    @Test
    public void importSkillCertificate() {

        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/rade/Documents/学校/Betahouse/证书录入/证书导入模板/技能证书-学生证书导入模板.xlsx"));
        List<Map<String, Object>> readAll = reader.readAll();
        for (Map<String, Object> map : readAll) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(String.valueOf(map.get("学号")));
            String userid = userInfoBO.getUserId();
            CertificateBOBuilder requestBuilder = CertificateBOBuilder.getInstance()
//                    .withConfirmUserId("审核员id")
                    .withUserID(userid)
                    .withCertificatePublishTime((Date) map.get("证书颁发时间"))
                    .withExpirationTime((Date) map.get("证书有效截止时间"))
                    .withCertificateNumber(String.valueOf(map.get("证书编号")))
                    .withStatus(CertificateStateEnum.APPROVED.getCode())
                    .withCertificateName((String) map.get("证书名称"))
                    .withType(CertificateTypeEnum.SKILL.getCode())
                    .withRank(String.valueOf(map.get("技能证书等级")));
            CertificateBO certificateBO = skillRepoService.create(requestBuilder.build());
            System.out.println(certificateBO.toString());
        }
    }

    /**
     * 后台管理员导入竞赛证书
     */
    @Test
    public void importCompetitionCertificate() {

        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/rade/Documents/学校/Betahouse/证书录入/证书导入模板/学科竞赛-学生证书导入模板.xlsx"));
        List<Map<String, Object>> readAll = reader.readAll();
        for (Map<String, Object> map : readAll) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(String.valueOf(map.get("队长学号")));
            String userid = userInfoBO.getUserId();

            List<Map<String,String>> teacherList = new ArrayList<>();
            List<String> workerList = new ArrayList<>();
            for (String teacher:String.valueOf(map.get("指导老师")).split(",")){
                String[] teacherInfo = teacher.split(":");
                Map<String,String> map1 = new HashMap<>();
                map1.put("teacherNumber",teacherInfo[0]);
                map1.put("teacherName",teacherInfo[1]);
                teacherList.add(map1);
            }

            for (String stuId:String.valueOf(map.get("队员学号")).split(",")){
                workerList.add(userInfoRepoService.queryUserInfoByStuId(stuId).getUserId());
            }

            CertificateBOBuilder requestBuilder = CertificateBOBuilder.getInstance()
//                    .withConfirmUserId("审核员id")
                    .withUserID(userid)
                    .withCertificatePublishTime((Date) map.get("获奖时间"))
                    .withCertificateName((String) map.get("竞赛证书名称"))
                    .withCompetitionName((String) map.get("比赛名字"))
                    .withStatus(CertificateStateEnum.APPROVED.getCode())
                    .withTeacher(teacherList)
                    .withWorkUserId(workerList)
                    .withTeamName(String.valueOf(map.get("团队名称")))
                    .withType(CertificateTypeEnum.COMPETITION.getCode())
                    .withRank(String.valueOf(map.get("竞赛等级")));
            CertificateBO certificateBO = competitionRepoService.create(requestBuilder.build());
            System.out.println(certificateBO.toString());

        }
    }
}