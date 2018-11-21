/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.request.PermCreateRequest;
import us.betahouse.haetae.user.model.basic.perm.PermBO;

/**
 * 权限管理器实现
 *
 * @author dango.yxm
 * @version : PermManagerImpl.java 2018/11/19 下午4:07 dango.yxm
 */
public class PermManagerImpl implements PermManager {

    @Autowired
    private PermRepoService permRepoService;

    @Override
    public PermBO createPerm(PermCreateRequest request) {
        return permRepoService.createPerm(request.getPermBO());
    }
}
