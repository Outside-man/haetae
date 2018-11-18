package us.betahouse.haetae.user.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.request.UserCreateRequest;
import us.betahouse.haetae.user.user.builder.UserBOBuilder;
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

    private UserCreateRequest genUserCreateRequest() {
        UserCreateRequest request = new UserCreateRequest();
        request.setRequestId(UUID.randomUUID().toString());
        UserBOBuilder userBOBuilder = UserBOBuilder.getInstance("yxm", "123dango123");
        UserInfoBOBuilder userInfoBOBuilder = UserInfoBOBuilder.getInstance()
                .withEnrollDate(201801)
                .withRealName("哈哈")
                .withSex("男")
                .withStuId("11111");
        request.setUserBO(userBOBuilder.build());
        request.setUserInfoBO(userInfoBOBuilder.build());
        return request;
    }
}