/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.asset.model.common.PageList;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.organization.OrganizationVO;
import us.betahouse.haetae.model.user.request.UserRequest;
import us.betahouse.haetae.serviceimpl.user.service.MajorService;
import us.betahouse.haetae.user.model.basic.MajorBO;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author MessiahJK
 * @version : MajorController.java 2019/04/21 16:50 MessiahJK
 */
@RestController
@RequestMapping(value = "/major")
public class MajorController {
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(MajorController.class);
    @Autowired
    MajorService majorService;

    @CheckLogin
    @GetMapping("/all")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<MajorBO>> getAllOrganization(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取组织列表", request, new RestOperateCallBack<List<MajorBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<MajorBO>> execute() {
                return RestResultUtil.buildSuccessResult(majorService.getAllMajor(), "获取专业列表成功");
            }
        });
    }
}
