/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.dal.model.perm.PermDO;
import us.betahouse.haetae.user.dal.repo.perm.PermDORepo;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.UserPermRelationBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PermDORepo permDORepo;

    @Override
    public PermBO createPerm(PermManageRequest request) {
        return permRepoService.createPerm(request.getPermBO());
    }

    @Override
    public void batchUsersBindPerms(PermManageRequest request) {
        AssertUtil.assertNotNull(request);
        AssertUtil.assertNotNull(request.getPermBO());
        AssertUtil.assertStringNotBlank(request.getPermBO().getPermId());
        AssertUtil.assertNotNull(request.getUserIds());
        permRepoService.usersBindPerm(request.getUserIds(), request.getPermBO().getPermId());
    }

    @Override
    public void batchUsersUnbindPerms(PermManageRequest request) {
        AssertUtil.assertNotNull(request);
        AssertUtil.assertNotNull(request.getPermBO());
        AssertUtil.assertNotNull(request.getUserIds());
        AssertUtil.assertStringNotBlank(request.getPermBO().getPermId());
        permRepoService.usersUnbindPerm(request.getUserIds(), request.getPermBO().getPermId());
    }

    @Override
    public void detachAllUsers(String permId) {
        permRepoService.detachAllUsers(permId);
    }

    @Override
    public List<String> getPermUsers(String permId) {
        return CollectionUtils.toStream(permRepoService.getUserPermRelationsOrderByCreate(permId))
                .map(UserPermRelationBO::getUserId).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PermBO updateStartAndEndTimeByPermID(Date start, Date end, String permId) {
        permDORepo.updateStartAndEndByPermId(start,end,permId);
        PermBO permBO=EntityConverter.convert(permDORepo.findByPermId(permId));
        return permBO;
    }

}
