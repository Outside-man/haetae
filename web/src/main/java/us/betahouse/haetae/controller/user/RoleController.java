/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.RoleUserPermRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.RoleUserPermRequest;
import us.betahouse.haetae.serviceimpl.user.service.RoleService;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色接口
 *
 * @author guofan.cp
 * @version : RoleController.java 2019/08/16 9:26 guofan.cp
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService roleService;

    @Autowired
    RoleManager roleManager;

    /**
     * 创建角色
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<RoleBO> createRole(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "创建角色", request, new RestOperateCallBack<RoleBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertNotNull(request.getRoleName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "创建角色名字不存在");
            }

            @Override
            public Result<RoleBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleRequest = new RoleUserPermRequest();
                roleRequest.setRoleName(request.getRoleName());

                if (request.getDescribe() != null) {
                    roleRequest.setRoleDescribe(request.getDescribe());
                }
                if (request.getStuIds() != null) {
                    roleRequest.setStuIds(request.getStuIds());
                }
                if (request.getPermIds() != null) {
                    roleRequest.setPermIds(request.getPermIds());
                }

                return RestResultUtil.buildSuccessResult(roleService.createRole(roleRequest, context), "创建角色成功");
            }
        });
    }


    /**
     * 获取所有角色信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping("/roles")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<RoleBO>> getAllUserRole(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取角色列表", request, new RestOperateCallBack<List<RoleBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
            }

            @Override
            public Result<List<RoleBO>> execute() {
                return RestResultUtil.buildSuccessResult(roleService.findAllRole(), "获取角色列表成功");
            }
        });
    }


    /**
     * 用户绑定角色
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "userBatchRole")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<RoleBO> userBatchRole(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户绑定角色", request, new RestOperateCallBack<RoleBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertNotNull(request.getStuIds(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "赋权角色学号不能为空");
                AssertUtil.assertNotNull(request.getRoleId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "赋权角色id不能为空");
            }

            @Override
            public Result<RoleBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleRequest = new RoleUserPermRequest();
                roleRequest.setRoleId(request.getRoleId());
                roleRequest.setStuIds(request.getStuIds());
                roleService.bindUsers(roleRequest, context);
                return RestResultUtil.buildSuccessResult("绑定用户角色成功");
            }
        });
    }

    /**
     * 获取所有用户以及所有用户的角色
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "roleUserRelation")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<UserRoleRelationBO>> getUserRoleRelation(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取所有用户以及所有用户的角色", request, new RestOperateCallBack<List<UserRoleRelationBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
            }

            @Override
            public Result<List<UserRoleRelationBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<UserRoleRelationBO> userRoleRelationBOList = roleService.findAllUserRelationRole();
                return RestResultUtil.buildSuccessResult(userRoleRelationBOList, "获取所有用户以及所有用户的角色");
            }
        });
    }

    /**
     * 解绑用户和角色关系
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping(value = "roleUserRelation")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserRoleRelationBO> unbatchUserRoleRelation(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户绑定角色", request, new RestOperateCallBack<UserRoleRelationBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertNotNull(request.getRoleId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "解绑角色id不能为空");
            }

            @Override
            public Result<UserRoleRelationBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleRequest = new RoleUserPermRequest();
                roleRequest.setUserIds(request.getUserIds());
                roleRequest.setRoleId(request.getRoleId());
                roleRequest.setStuIds(request.getStuIds());
                roleService.unbindUsers(roleRequest, context);
                return RestResultUtil.buildSuccessResult("解绑用户角色关系");
            }
        });
    }

}
