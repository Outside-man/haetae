/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.Management;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.RoleUserPermRestRequest;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author guofan.cp
 * @version : ManagementController.java 2019/07/04 15:15 guofan.cp
 * <p>
 * 后台管理端
 */
@RestController
@RequestMapping(value = "/management")
public class ManagementController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    @Autowired
    RoleManager roleManager;

    @CheckLogin
    @GetMapping("/roles")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<RoleBO>> getAllUserRole(RoleUserPermRestRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "获取角色列表", request, new RestOperateCallBack<List<RoleBO>>() {
            @Override
            public void before() {
//               AssertUtil.assertNotNull(request,"请求体不能为空");
//               AssertUtil.assertNotNull(request.getUserId(),"用户id不能为空");
            }

            @Override
            public Result<List<RoleBO>> execute() {
                return RestResultUtil.buildSuccessResult(roleManager.findAllRole(), "获取角色列表成功");
            }
        });
    }

    @PostMapping("/test")
    public String  test(){
        return " sdf";
    }

}
