/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.converter.UserVOConverter;
import us.betahouse.haetae.model.user.request.UserRequest;
import us.betahouse.haetae.model.user.vo.UserVO;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.constant.UserRequestExtInfoKey;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationService;
import us.betahouse.haetae.serviceimpl.user.builder.CommonUserRequestBuilder;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author dango.yxm
 * @version : UserController.java 2018/11/21 6:19 PM dango.yxm
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;
    /**
     * 登陆
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/openId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> wxLogin(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户微信登录", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUsername(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户名不能为空");
                AssertUtil.assertStringNotBlank(request.getPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "密码不能为空");
                AssertUtil.assertStringNotBlank(request.getCode(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "微信code不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUsername(request.getUsername()).withPassword(request.getPassword())
                        .withCode(request.getCode());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                UserVO userVO = UserVOConverter.convert(userService.login(builder.build(), context));
                OrganizationRequest organizationRequest=new OrganizationRequest();
                organizationRequest.setMemberId(userVO.getUserId());
                List<String> list= CollectionUtils.toStream(organizationService.queryOrganizationMemberByMemberId(organizationRequest)).map(OrganizationMemberBO::findJob).distinct().collect(Collectors.toList());
                userVO.setJobInfo(list);

                return RestResultUtil.buildSuccessResult(userVO, "登陆成功");
            }
        });
    }

    /**
     * 易班登陆
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/yiLogin")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> yiLogin(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户易班登录", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getCode(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "易班code不能为空");
            }
            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withCode(request.getCode());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                UserVO userVO = UserVOConverter.convert(userService.yiLogin(builder.build(), context));
                OrganizationRequest organizationRequest=new OrganizationRequest();
                organizationRequest.setMemberId(userVO.getUserId());
                List<String> list= CollectionUtils.toStream(organizationService.queryOrganizationMemberByMemberId(organizationRequest)).map(OrganizationMemberBO::findJob).distinct().collect(Collectors.toList());
                userVO.setJobInfo(list);
                return RestResultUtil.buildSuccessResult(userVO, "登陆成功");
            }
        });
    }
    @DeleteMapping(value = "/openId")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> wxLogout(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户微信登出", request, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                userService.wxLogout(builder.build(), context);
                return RestResultUtil.buildSuccessResult("微信登出成功");
            }
        });
    }

    /**
     * 登陆
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/token")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> normalLogin(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户普通登录", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUsername(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户名不能为空");
                AssertUtil.assertStringNotBlank(request.getPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "密码不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUsername(request.getUsername()).withPassword(request.getPassword());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                UserVO userVO = UserVOConverter.convert(userService.login(builder.build(), context));
                OrganizationRequest organizationRequest=new OrganizationRequest();
                organizationRequest.setMemberId(userVO.getUserId());
                List<String> list= CollectionUtils.toStream(organizationService.queryOrganizationMemberByMemberId(organizationRequest)).map(OrganizationMemberBO::findJob).distinct().collect(Collectors.toList());
                userVO.setJobInfo(list);
                return RestResultUtil.buildSuccessResult(userVO, "登陆成功");
            }
        });
    }

    /**
     * 登陆
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> checkLogin(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户登录维持", request, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                UserVO userVO = UserVOConverter.convert(userService.fetchUser(builder.build(), context));
                OrganizationRequest organizationRequest=new OrganizationRequest();
                organizationRequest.setMemberId(userVO.getUserId());
                List<String> list= CollectionUtils.toStream(organizationService.queryOrganizationMemberByMemberId(organizationRequest)).map(OrganizationMemberBO::findJob).distinct().collect(Collectors.toList());
                userVO.setJobInfo(list);
                return RestResultUtil.buildSuccessResult(userVO, "登陆成功");
            }
        });
    }

    /**
     * 用户登出
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CrossOrigin
    @DeleteMapping(value = "/token")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> logout(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户普通登出", request, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                userService.logout(builder.build(), context);
                return RestResultUtil.buildSuccessResult("登出成功");
            }
        });
    }

    /**
     * 修改密码
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PutMapping(value = "/pwd")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> modifyPassword(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "修改密码", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "原密码不能为空");
                AssertUtil.assertStringNotBlank(request.getNewPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "新密码不能为空");
                boolean oldNewNotEqual = !StringUtils.equals(request.getPassword(), request.getNewPassword());
                AssertUtil.assertTrue(oldNewNotEqual, "新旧密码不能一致");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withPassword(request.getPassword())
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                CommonUserRequest commonUserRequest = builder.build();
                commonUserRequest.putExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD, request.getNewPassword());

                userService.modifyUser(commonUserRequest, context);
                return RestResultUtil.buildSuccessResult("密码修改成功");
            }
        });
    }

    /**
     * 根据学号修改密码
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CrossOrigin
    @PutMapping(value = "/pwdByStuId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> modifyPasswordByStuId(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "根据学号修改密码", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "学号不能为空");
                AssertUtil.assertStringNotBlank(request.getNewPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "新密码不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withStuId(request.getStuId())
                        .withPassword(request.getPassword())
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                CommonUserRequest commonUserRequest = builder.build();
                commonUserRequest.putExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD, request.getNewPassword());

                userService.modifyPwdByStuId(commonUserRequest, context);
                return RestResultUtil.buildSuccessResult("密码修改成功");
            }
        });
    }

    /**
     * 修改班级、年级、专业
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PutMapping(value = "/message")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<UserVO> modifyMessage(UserRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "修改班级、年级、专业", null, new RestOperateCallBack<UserVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getGrade(), "年级不能为空");
                AssertUtil.assertNotNull(request.getClassId(), "班级不能为空");
                AssertUtil.assertNotNull(request.getMajor(), "专业不能为空");
            }

            @Override
            public Result<UserVO> execute() {
                UserInfoBO userInfoBO = new UserInfoBO();
                userInfoBO.setGrade(request.getGrade());
                userInfoBO.setClassId(request.getClassId());
                userInfoBO.setMajor(request.getMajor());
                userInfoBO.setUserId(request.getUserId());
                CommonUserRequestBuilder builder = CommonUserRequestBuilder.getInstance()
                        .withUserInfoBO(userInfoBO)
                        .withUserId(request.getUserId());
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                CommonUserRequest commonUserRequest = builder.build();
                userService.modifyUserMajorAndClassAndGrade(commonUserRequest, context);
                return RestResultUtil.buildSuccessResult("信息登记成功");
            }
        });
    }
}
