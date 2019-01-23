/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.constant.PermExInfokey;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.serviceimpl.asset.manager.AssetRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;
import us.betahouse.util.utils.AssertUtil;

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.Collections;

/**
 * 物资业务实现
 * @author guofan.cp
 * @version : AssetServiceImpl.java 2019/01/20 23:58 guofan.cp
 */
@Service
public class AssetServiceImpl implements AssetService {
    /**
     * 系统结束标志
     */
    private final static String SYSTEM_FINISH_SIGN = "systemFinish";

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private PermManager permManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private RoleRepoService roleRepoService;

    //@Autowired
    //private ActivityOperateManager activityOperateManager;

    @Autowired
    private OrganizationRepoService organizationRepoService;


    @Override
    public AssetBO create(AssetRequestBuilder request, OperateContext context) {
        return null;
    }
}
