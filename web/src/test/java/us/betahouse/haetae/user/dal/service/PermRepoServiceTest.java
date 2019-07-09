/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.user.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.model.basic.perm.PermBO;

/**
 * @author NathanDai
 * @version :  2019-07-09 20:41 NathanDai
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermRepoServiceTest {
    @Autowired
    PermRepoService permRepoService;

    @Test
    public void createPerm() {
        PermBO permBO = new PermBO();
        permBO.setPermName("场地申请审核通过");
        permBO.setPermType("APPLY_CHECK");
//        roleBO.setRoleDesc("测试权限");
        permRepoService.createPerm(permBO);
    }
}
