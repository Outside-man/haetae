package us.betahouse.haetae.user.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.model.RoleBO;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepoServiceTest {

    @Autowired
    private RoleRepoService roleRepoService;

    @Test
    public void createRole() {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName("测试权限");
        roleBO.setRoleDesc("测试权限");
        System.out.println(roleRepoService.createRole(roleBO));
    }

    @Test
    public void queryRolesByRoleIds() {
    }

    @Test
    public void queryRolesByUserId() {
    }

    @Test
    public void userBindRoles() {
        System.out.println(roleRepoService.userBindRoles("123456789", Collections.singletonList("201811171435334621498500021117")));

    }
}