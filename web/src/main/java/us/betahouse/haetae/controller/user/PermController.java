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
import us.betahouse.haetae.serviceimpl.user.service.PermService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guofan.cp
 * @version : PermController.java 2019/08/23 8:42 guofan.cp
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/perm")
public class PermController {
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(us.betahouse.haetae.controller.user.MajorController.class);

    @Autowired
    PermManager permManager;
    @Autowired
    PermService permService;

    /**
     * 创建权限
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PermBO> createPerm(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "创建权限", request, new RestOperateCallBack<PermBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPermName(),RestResultCode.ILLEGAL_PARAMETERS.getCode(),"权限名字不存在");
                AssertUtil.assertNotNull(request.getDescribe(),RestResultCode.ILLEGAL_PARAMETERS.getCode(), "权限描述不存在");
            }

            @Override
            public Result<PermBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleUserPermRequest=new RoleUserPermRequest();
                roleUserPermRequest.setPermName(request.getPermName());
                roleUserPermRequest.setPermDescribe(request.getDescribe());
                roleUserPermRequest.setExtInfo(request.getExtInfo());
                return RestResultUtil.buildSuccessResult(permService.createPerm(roleUserPermRequest,context), "创建权限成功");
            }
        });
    }





    /**
     * 获取所有的权限(不包括活动盖章员和社团证书审核员)
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/perms")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<PermBO>> getAllPerms(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取全部权限", request, new RestOperateCallBack<List<PermBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<PermBO>> execute() {
                return RestResultUtil.buildSuccessResult(permService.findAllNotContainStamperAndFinance(), "获取专业列表成功");
            }
        });
    }


    /**
     * 获取权限的所有用户
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping("/users")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<UserInfoBO>> getPermUsers(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取权限的所有用户", request, new RestOperateCallBack<List<UserInfoBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPermId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "权限id不存在");
            }

            @Override
            public Result<List<UserInfoBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleUserPermRequest = new RoleUserPermRequest();
                roleUserPermRequest.setPermId(request.getPermId());
                permService.getPermUsers(roleUserPermRequest, context);
                return RestResultUtil.buildSuccessResult(permService.getPermUsers(roleUserPermRequest, context), "获取该权限所有用户成功");
            }
        });
    }

    /**
     * 批量用户解绑权限
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @DeleteMapping("/permUsers")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result batchUsersUnbindPerms(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "批量用户解绑权限", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPermId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "权限id不存在");
                AssertUtil.assertNotNull(request.getStuIds(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "解绑用户学号不存在");
            }

            @Override
            public Result execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleUserPermRequest = new RoleUserPermRequest();
                roleUserPermRequest.setPermId(request.getPermId());
                roleUserPermRequest.setStuIds(request.getStuIds());
                permService.batchUsersUnbindPerms(roleUserPermRequest, context);
                return RestResultUtil.buildSuccessResult("解绑用户成功");
            }
        });
    }

    /**
     * 批量用户绑定权限
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/permUsers")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<UserInfoBO>> batchUsersBindPerms(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "批量用户绑定权限", request, new RestOperateCallBack<List<UserInfoBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPermId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "权限id不存在");
                AssertUtil.assertNotNull(request.getStuIds(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的权限用户学号不能为空");
            }

            @Override
            public Result<List<UserInfoBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleUserPermRequest = new RoleUserPermRequest();
                roleUserPermRequest.setPermId(request.getPermId());
                roleUserPermRequest.setStuIds(request.getStuIds());
                return RestResultUtil.buildSuccessResult(permService.batchUsersBindPerms(roleUserPermRequest, context), "绑定权限成功");
            }
        });
    }


    /**
     * 权限和所有用户解绑
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @DeleteMapping("/permAllUsers")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result detachAllUsers(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "解绑权限所有用户", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPermId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "权限id不存在");
            }

            @Override
            public Result execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                RoleUserPermRequest roleUserPermRequest = new RoleUserPermRequest();
                roleUserPermRequest.setPermId(request.getPermId());
                permService.detachAllUsers(roleUserPermRequest, context);
                return RestResultUtil.buildSuccessResult("解绑权限所有用户成功");
            }
        });
    }
}
