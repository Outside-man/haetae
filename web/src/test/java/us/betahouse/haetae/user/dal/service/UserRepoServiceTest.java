package us.betahouse.haetae.user.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.utils.EncryptUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoServiceTest {

    @Autowired
    private UserRepoService userRepoService;

    @Test
    public void queryByUserName() {
    }

    @Test
    public void createUser() {
        UserBO userBO = userRepoService.createUser(buildUserBO());
        System.out.println(userBO);
    }

    @Test
    public void updateUser() {
        String stuId="189200630";
        UserBO userBO = userRepoService.queryByUserName(stuId);
        userBO.setPassword(EncryptUtil.encryptPassword("Hziee"+stuId, userBO.getSalt()));
        userRepoService.updateUserByUserId(userBO);
        System.out.println(userBO);
    }

    private UserBO buildUserBO() {
        UserBO userBO = new UserBO();
        userBO.setUserId("123456789");
        userBO.setUserName("aaa");
        userBO.setPassword("aaa");
        userBO.setSalt("aaa");
        userBO.setOpenId("aaa");
        userBO.setLastLoginIP("192.168.1.1");
        return userBO;
    }
}