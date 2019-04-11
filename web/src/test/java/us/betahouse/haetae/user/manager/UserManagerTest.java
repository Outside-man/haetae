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

    @Test
    public void create() {
        System.out.println(userManager.create(genUserCreateRequest(1)));
    }

    @Test
    public void createManager() {
        for (int i = 1; i <= 10; i++) {
            CommonUser commonUser = userManager.create(genUserCreateRequest(i));
            System.out.println(commonUser);
            UserManageRequest request = new UserManageRequest();
            request.setUserId(commonUser.getUserId());
            request.setRoleCode(UserRoleCode.ACTIVITY_MANAGER);
            userManager.batchBindRolByCode(request);
            request.setRoleCode(UserRoleCode.NOT_STUDENT);
            userManager.batchBindRolByCode(request);
        }
    }

    @Test
    public void batchBindRole() {
        List<String> userIds = Arrays.asList("201811302141304426900001201888");
        for (String userId : userIds) {
            UserManageRequest request = new UserManageRequest();
            request.setUserId(userId);
            request.setRoleCode(UserRoleCode.ORGANIZATION_MANAGER);
            userManager.batchBindRolByCode(request);
        }
    }

    @Test
    public void batchBindRolByCode() {

    }

    @Test
    public void batchUnbindRole() {
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
                .withRealName("beta测试账号_全权限_" + index)
                .withSex("女")
                .withStuId("0" + index);
        request.setUsername("beta" + index);
        request.setPassword("a111111");
        request.setUserInfoBO(userInfoBOBuilder.build());
        return request;
    }

}