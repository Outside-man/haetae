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

import java.util.ArrayList;
import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-09 20:41 NathanDai
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermRepoServiceTest {
    @Autowired
    PermRepoService permRepoService;
    @Autowired
    RoleRepoService roleRepoService;

    @Test
    public void createPerm() {
        PermBO permBO = new PermBO();
        permBO.setPermName("场地申请审核通过");
        permBO.setPermType("APPLY_CHECK");
        permRepoService.createPerm(permBO);
    }

    @Test
    public void roleBindPerms() {
        List<String> roleIds = new ArrayList<>();
        roleIds.add("201907092038360678461500020709");
        roleRepoService.userBindRoles("201811302141486240790001201814", roleIds);

//        roleIds.add("201907092037530476772500020709");
//        roleIds.add("201907092038360678461500020709");

    }

}
