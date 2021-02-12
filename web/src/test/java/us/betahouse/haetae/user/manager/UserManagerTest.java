package us.betahouse.haetae.user.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.builder.PastActivityBOBuilder;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.builder.UserInfoBOBuilder;
import us.betahouse.util.utils.CsvUtil;

import java.util.*;


/**
 * 用户管理器
 *
 * @author dango.yxm
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserManagerTest {

    @Autowired
    private UserManager userManager;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityManager activityManager;

    @Test
    public void create() {
        System.out.println(userManager.create(genUserCreateRequest(1)));
    }

    @Test
    public void createManager() {
        for (int i = 1; i <= 90; i++) {
            CommonUser commonUser = userManager.create(genUserCreateRequest(i));
            System.out.println(commonUser);
            UserManageRequest request = new UserManageRequest();
            request.setUserId(commonUser.getUserId());
//            request.setRoleCode(UserRoleCode.ACTIVITY_MANAGER);
//            userManager.batchBindRolByCode(request);
            request.setRoleCode(UserRoleCode.NOT_STUDENT);
            userManager.batchBindRolByCode(request);
        }
    }

    @Test
    public void batchBindRole() {
            String stuId="189050105";
//        String url = "C:\\Users\\j10k\\Desktop\\苏.csv";
//        String[][] csv = CsvUtil.getWithHeader(url);
//        for (int i = 1; i < csv.length; i++) {
//            String[] acsv = csv[i];

//            String stuId = acsv[0];
            System.out.println(stuId);
            System.out.println("________");
            String userId = (userInfoRepoService.queryUserInfoByStuId(stuId).getUserId());
            UserManageRequest request = new UserManageRequest();
            request.setUserId(userId);
            request.setRoleCode(UserRoleCode.LOCALE_MEMBER);
            userManager.batchBindRolByCode(request);
//        }
//            request.setRoleCode(UserRoleCode.LEARNING_MANAGER);
//            userManager.batchBindRolByCode(request);
//            request.setRoleCode(UserRoleCode.LOCALE_MEMBER);
//            userManager.batchBindRolByCode(request);
//        for(int i=1;i<=10;i++) {
//            String stuId=0+""+i;
//            List<String> userIds = Arrays.asList((userInfoRepoService.queryUserInfoByStuId(stuId).getUserId()));
//            //List<String> userIds =Arrays.asList("201811302142187096510001201817");
//            for (String userId : userIds) {
//                UserManageRequest request = new UserManageRequest();
//                request.setUserId(userId);
//                request.setRoleCode(UserRoleCode.ACTIVITY_MANAGER);
//                userManager.batchBindRolByCode(request);
//            }
//        }
    }

    @Test
    public void batchBindRolByCode() {

    }

    @Test
    public void batchUnbindRole() {
        List<String> userIds = Arrays.asList("201811302141428469790001201838");
        List<String> roleIds= Arrays.asList("201904120142474335061300020412");

        for (String userId : userIds) {
            UserManageRequest request = new UserManageRequest();
            request.setUserId(userId);
            request.setRoleIds(roleIds);
            userManager.batchUnbindRole(request);
        }
    }

    @Test
    public void batchBindPerm() {
        UserManageRequest request=new UserManageRequest();
        request.setUserId("201904032054129780650001201994");
        List<String> list= new ArrayList<>();
        list.add("202002221910514810655300040222");
        request.setPermIds(list);
        userManager.batchBindPerm(request);
    }

    @Test
    public void batchUnbindPerm() {
    }

    private UserManageRequest genUserCreateRequest(int index) {
        UserManageRequest request = new UserManageRequest();
        request.setRequestId(UUID.randomUUID().toString());
        UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                .withEnrollDate(new Date())
                .withRealName("湖畔青年" + index)
                .withSex("女")
                .withStuId("Hziee" + index);
        request.setUsername("Hziee" + index);
        request.setPassword("a00000");
        request.setUserInfoBO(userInfoBOBuilder.build());
        return request;
    }

    @Test
    public void importUser(){
        PastActivityBOBuilder boBuilder=PastActivityBOBuilder.aPastActivityBO()
                .withPastLectureActivity(0L)
                .withPastPracticeActivity(0L)
                .withPastSchoolActivity(0L)
                .withPastVolunteerActivityTime(0L)
                .withUndistributedStamp(0L)
                ;
        ActivityRequest activityRequest=new ActivityRequest();
        String csvs[][]=CsvUtil.getWithHeader("C:\\Users\\j10k\\Desktop\\111.csv");
        for(int i=1;i< csvs.length;i++){
            try {
            UserManageRequest request = new UserManageRequest();
            request.setRequestId(UUID.randomUUID().toString());
            UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                    .withEnrollDate(new Date())
                    .withRealName(csvs[i][1])
                    .withStuId(csvs[i][0])
//                    .withMajorId("教职工");
                    .withMajorId(csvs[i][4])
                    .withSex(csvs[i][2])
                    .withGrade("2019")
                    .withClassId(csvs[i][5]);
            request.setUsername(csvs[i][0]);
            request.setPassword("Hziee"+csvs[i][0]);
            request.setUserInfoBO(userInfoBOBuilder.build());
            System.out.println(request);
            CommonUser commonUser = userManager.create(request);
//            UserManageRequest request1 = new UserManageRequest();
//            request1.setUserId(commonUser.getUserId());
//            request1.setRoleCode(UserRoleCode.NOT_STUDENT);
//            userManager.batchBindRolByCode(request1);
            activityRequest.setUserId(commonUser.getUserId());
                activityManager.createPast(boBuilder.withStuId(commonUser.getUserInfo().getStuId()).withUserId(commonUser.getUserId()).build());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            }

        }
//        activityService.initPastActivity();


    @Test
    public void initTest(){
        String csvs[][]=CsvUtil.getWithHeader("C:\\Users\\j10k\\Desktop\\2015级.csv");
        for(int i=1;i< csvs.length;i++){
            System.out.println(csvs[i][0]);
            UserInfoBO userInfoBO=userInfoRepoService.queryUserInfoByStuId(csvs[i][0]);
                userInfoBO.setGrade(csvs[i][5]);
                userInfoBO.setMajor(csvs[i][3]);
                userInfoBO.setClassId(csvs[i][4]);
                userInfoRepoService.modifyUserInfoByUserId(userInfoBO.getUserId(), userInfoBO);
        }
    }
    @Test
    public void check() {
        String url = "C:\\Users\\j10k\\Desktop\\【16级小绿本1】.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        List<String> notStampStuIds = new ArrayList<>();
        for (int i = 1; i < csv.length; i++) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(csv[i][4]);
            if (userInfoBO == null) {
                System.out.println(i + " " + csv[i][4] + " " + null);
                notStampStuIds.add(csv[i][4]);
            } else if (!userInfoBO.getRealName().equals(csv[i][3])) {
                System.out.println(i + " " + csv[i][4] + " name " + userInfoBO.getRealName() + " " + csv[i][3]);
                notStampStuIds.add(csv[i][4]);
            }
        }
        System.out.println(notStampStuIds.size());
    }



}