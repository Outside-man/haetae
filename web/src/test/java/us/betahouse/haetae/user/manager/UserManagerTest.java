package us.betahouse.haetae.user.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.builder.UserInfoBOBuilder;

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
    public void addRole() {
    }

    @Test
    public void addPerm() {
    }

    private UserManageRequest genUserCreateRequest() {
        UserManageRequest request = new UserManageRequest();
        request.setRequestId(UUID.randomUUID().toString());
        UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                .withEnrollDate(201801)
                .withRealName("哈哈")
                .withSex("男")
                .withStuId("11111");
        request.setUsername("yxm0110");
        request.setPassword("yxm123");
        request.setUserInfoBO(userInfoBOBuilder.build());
        return request;
    }
}