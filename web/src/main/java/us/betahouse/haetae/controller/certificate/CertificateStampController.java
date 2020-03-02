/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.ConfirmRequest;
import us.betahouse.haetae.serviceimpl.certificate.builder.CertificateConfirmRequestBuilder;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateConfirmRequest;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateManagerService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 证书审核员 接口
 *
 * @author guofan.cp
 * @version : CertificateStampController.java 2019/04/22 22:16 guofan.cp
 */
@RestController
@RequestMapping(value = "/certificateStamp")
public class CertificateStampController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    @Autowired
    private CertificateManagerService certificateManagerService;

    /**
     * 审核员 获取用户所有证书信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "certificates")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<List<CertificateBO>> findCertificatesByUserId(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取该生全部记录", request, new RestOperateCallBack<List<CertificateBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getStuId(), "学生id不能为空");
            }

            @Override
            public Result<List<CertificateBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateConfirmRequestBuilder builder = CertificateConfirmRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withConfirmStuId(request.getStuId());
                List<CertificateBO> certificateBOList = certificateManagerService.fetchAllCertificate(builder.build(), context);
                return RestResultUtil.buildSuccessResult(certificateBOList, "获取该用户所有证书信息成功");
            }
        });
    }

    /**
     * 审核员 通过证书
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> confirmCertificate(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "证书审核通过", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getCertificateId(), "通过证书id不能为空");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateConfirmRequestBuilder builder = CertificateConfirmRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withCertificateType(request.getCertificateType())
                        .withCertificateId(request.getCertificateId());
                CertificateBO certificateBO = certificateManagerService.confirmCertificate(builder.build(), context);
                return RestResultUtil.buildSuccessResult(certificateBO, "证书审核通过");
            }
        });
    }

    /**
     * 获取所有证书审核员信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "confirmUsers")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<List<UserInfoBO>> getAllConfirms(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "证书审核通过", request, new RestOperateCallBack<List<UserInfoBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, "请求体不能为空");
            }

            @Override
            public Result<List<UserInfoBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateConfirmRequest request1=new CertificateConfirmRequest();
                request1.setUserId(request.getUserId());
                List<UserInfoBO> userInfoBOS = certificateManagerService.getConfirmUser(request1,context);
                return RestResultUtil.buildSuccessResult(userInfoBOS, "获取所有证书审核员信息成功");
            }
        });
    }

    /**
     * 添加证书审核员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "confirmUser")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> addConfirms(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "证书审核通过", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getStuId(), "添加证书审核员学号不能为空");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateConfirmRequestBuilder builder = CertificateConfirmRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withConfirmStuId(request.getStuId());
                certificateManagerService.bindConfirmUser(builder.build(), context);
                return RestResultUtil.buildSuccessResult("绑定审核员成功");
            }
        });
    }


    /**
     * 删除证书审核员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping(value = "confirmUser")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> deleteConfirms(ConfirmRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "证书审核通过", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getStuId(), "删除证书审核员学号不能为空");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateConfirmRequestBuilder builder = CertificateConfirmRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withConfirmStuId(request.getStuId());
                certificateManagerService.delteConfirmUser(builder.build(), context);
                return RestResultUtil.buildSuccessResult("删除证书审核员成功");
            }
        });
    }
}
