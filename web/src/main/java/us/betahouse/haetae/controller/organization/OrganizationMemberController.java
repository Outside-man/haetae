/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.organization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.organization.OrganizationRestRequest;
import us.betahouse.haetae.serviceimpl.organization.builder.OrganizationRequestBuilder;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationManagerService;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 组织成员接口
 *
 * @author dango.yxm
 * @version : OrganizationMemberController.java 2019/03/27 22:58 dango.yxm
 */
@RestController
@RequestMapping("/organization/member")
public class OrganizationMemberController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationManagerService organizationManagerService;

    /**
     * 添加成员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result addMember(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "添加成员", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "组织Id不能为空");
                AssertUtil.assertStringNotBlank(request.getStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "成员学号不能为空");
                AssertUtil.assertStringNotBlank(request.getMemberType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "成员类型不能为空");
            }

            @Override
            public Result execute() {
                OrganizationRequestBuilder builder = OrganizationRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withOrganizationId(request.getOrganizationId())
                        .withStuId(request.getStuId())
                        .withMemberType(request.getMemberType())
                        .withMemberDesc(request.getMemberDesc());
                organizationManagerService.addMember(builder.build());
                return RestResultUtil.buildSuccessResult(null, "添加成员成功");
            }
        });
    }

    /**
     * 修改成员身份
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result manageMemberType(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "修改成员身份", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "组织Id不能为空");
                AssertUtil.assertStringNotBlank(request.getStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "成员学号不能为空");
                AssertUtil.assertStringNotBlank(request.getMemberType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "成员类型");
            }

            @Override
            public Result execute() {
                OrganizationRequestBuilder builder = OrganizationRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withOrganizationId(request.getOrganizationId())
                        .withStuId(request.getStuId())
                        .withMemberType(request.getMemberType())
                        .withMemberDesc(request.getMemberDesc());
                organizationManagerService.changeMemberType(builder.build());
                return RestResultUtil.buildSuccessResult(null, "修改成员身份成功");
            }
        });
    }

    /**
     * 移出成员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @DeleteMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result removeMember(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "移除成员", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "组织Id不能为空");
                AssertUtil.assertStringNotBlank(request.getStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "成员学号不能为空");
            }

            @Override
            public Result execute() {
                OrganizationRequestBuilder builder = OrganizationRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withOrganizationId(request.getOrganizationId())
                        .withStuId(request.getStuId());
                organizationManagerService.removeMember(builder.build());
                return RestResultUtil.buildSuccessResult(null, "移除成员成功");
            }
        });
    }


}