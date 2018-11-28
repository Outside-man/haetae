package us.betahouse.haetae.user.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.builder.UserInfoBOBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;


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

    @Test
    public void create() {
        System.out.println(userManager.create(genUserCreateRequest()));
    }

    @Test
    public void createManager() {
        CommonUser commonUser = userManager.create(genUserCreateRequest());
        System.out.println(commonUser);
        UserManageRequest request = new UserManageRequest();
        request.setUserId(commonUser.getUserId());
        request.setRoleCode(UserRoleCode.ACTIVITY_MANAGER);
        userManager.batchBindRolByCode(request);
        request.setRoleCode(UserRoleCode.NOT_STUDENT);
        userManager.batchBindRolByCode(request);
    }

    @Test
    public void batchBindRole() {
        UserManageRequest request = new UserManageRequest();
        request.setUserId("201811251917084430680001201864");
        request.setRoleIds(Collections.singletonList("201811251934333309365400021125"));
        userManager.batchBindRole(request);
    }

    @Test
    public void batchBindRolByCode(){

    }

    @Test
    public void batchUnbindRole() {
    }

    @Test
    public void batchBindPerm() {
    }

    @Test
    public void batchUnbindPerm() {
    }

    private UserManageRequest genUserCreateRequest() {
        UserManageRequest request = new UserManageRequest();
        request.setRequestId(UUID.randomUUID().toString());
        UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                .withEnrollDate(new Date())
                .withRealName("beta测试账号_全权限_5")
                .withSex("女")
                .withStuId("05");
        request.setUsername("beta5");
        request.setPassword("a111111");
        request.setUserInfoBO(userInfoBOBuilder.build());
        return request;
    }

}