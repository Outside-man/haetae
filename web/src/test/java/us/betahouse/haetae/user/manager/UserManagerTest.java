package us.betahouse.haetae.user.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.CommonUser;
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
        //List<String> userIds =Arrays.asList((userInfoRepoService.queryUserInfoByStuId("17922236" ).getUserId()));
        //201811302141390794090001201864
        List<String> userIds =Arrays.asList("201812032211299963730001201815");
        for (String userId : userIds) {
            UserManageRequest request = new UserManageRequest();
            request.setUserId(userId);
            request.setRoleCode(UserRoleCode.VOLUNTEER_WORK_MANAGER);

            userManager.batchBindRolByCode(request);
        }
    }

    @Test
    public void batchBindRolByCode() {

    }

    @Test
    public void batchUnbindRole() {
        List<String> userIds = Arrays.asList("201811302141437813470001201835");
        List<String> roleIds= Arrays.asList("201811302151309605429200021130");

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
        request.setUserId("201811302142043529750001201886");
        List<String> list= new ArrayList<>();
        list.add("201903121918214750212900040312");
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
        String csvs[][]=CsvUtil.getWithHeader("C:\\Users\\j10k\\Desktop\\学工工号.csv");
        for(int i=1;i< csvs.length;i++){
            UserManageRequest request = new UserManageRequest();
            request.setRequestId(UUID.randomUUID().toString());
            UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                    .withEnrollDate(new Date())
                    .withRealName(csvs[i][2])
                    .withStuId(csvs[i][1]);
            request.setUsername(csvs[i][1]);
            request.setPassword("Hziee"+csvs[i][1]);
            request.setUserInfoBO(userInfoBOBuilder.build());
            CommonUser commonUser = userManager.create(request);
            UserManageRequest request1 = new UserManageRequest();
            request1.setUserId(commonUser.getUserId());
            request1.setRoleCode(UserRoleCode.NOT_STUDENT);
            userManager.batchBindRolByCode(request1);
        }
    }
}