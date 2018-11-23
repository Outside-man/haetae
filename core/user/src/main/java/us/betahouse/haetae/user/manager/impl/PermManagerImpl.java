/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.util.utils.AssertUtil;

/**
 * 权限管理器实现
 *
 * @author dango.yxm
 * @version : PermManagerImpl.java 2018/11/19 下午4:07 dango.yxm
 */
@Service
public class PermManagerImpl implements PermManager {

    @Autowired
    private PermRepoService permRepoService;

    @Override
    public PermBO createPerm(PermManageRequest request) {
        return permRepoService.createPerm(request.getPermBO());
    }

    @Override
    public void batchUsersBindPerms(PermManageRequest request) {
        AssertUtil.assertNotNull(request);
        AssertUtil.assertNotNull(request.getPermBO());
        AssertUtil.assertNotNull(request.getRoleIds());
        AssertUtil.assertStringNotBlank(request.getPermBO().getPermId());
        permRepoService.usersBindPerm(request.getUserId(), request.getPermBO().getPermId());
    }

    @Override
    public void batchUsersUnbindPerms(PermManageRequest request) {
        AssertUtil.assertNotNull(request);
        AssertUtil.assertNotNull(request.getPermBO());
        AssertUtil.assertNotNull(request.getRoleIds());
        AssertUtil.assertStringNotBlank(request.getPermBO().getPermId());
        permRepoService.usersUnbindPerm(request.getUserId(), request.getPermBO().getPermId());
    }
}
