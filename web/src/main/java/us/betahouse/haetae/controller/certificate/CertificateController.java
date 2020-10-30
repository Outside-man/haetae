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
import us.betahouse.haetae.model.certificate.request.CertificateRestRequest;
import us.betahouse.haetae.serviceimpl.certificate.builder.CertificateRequestBuilder;
import us.betahouse.haetae.serviceimpl.certificate.service.CertificateService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证书接口
 *
 * @author guofan.cp
 * @version : CertificateController.java 2019/04/04 20:30 guofan.cp
 */
@RestController
@RequestMapping(value = "/certificate")
public class CertificateController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(CertificateController.class);

    /**
     * 证书详细信息
     */
    private final String DESCRIPTION = "description";

    @Autowired
    private CertificateService certificateService;

    /**
     * 创建证书
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "create")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> createCertificate(@RequestBody CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        //post提交json数据
        return RestOperateTemplate.operate(LOGGER, "新增证书记录", request, new RestOperateCallBack<CertificateBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), "用户id不能为空");
                AssertUtil.assertStringNotBlank(request.getCertificateType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不能为空");
                AssertUtil.assertNotNull(request.getCertificatePublishTime(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书发布时间不能为空");
                //获取 Extinfo 中 description信息
                AssertUtil.assertNotNull(request.getExtInfo().get(DESCRIPTION), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书详细信息不能为空");
                boolean validateTime = new Date(request.getCertificatePublishTime()).before(new Date());
                AssertUtil.assertTrue(validateTime, "证书发布时间必须早于当前时间");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateRequestBuilder builder = CertificateRequestBuilder.getInstance()
                        .withCertificateName(request.getCertificateName())
                        .withCompetitionName(request.getCompetitionName())
                        .withUserID(request.getUserId())
                        .withCertificateOrganization(request.getCertificateOrganization())
                        .withCertificatePublishTime(request.getCertificatePublishTime())
                        .withCertificateType(request.getCertificateType())
                        .withType(request.getType())
                        .withRank(request.getRank())
                        .withWorkUserId(request.getWorkUserId())
                        .withExpirationTime(request.getExpirationTime())
                        .withTeamName(request.getTeamName())
                        .withTeacher(request.getTeacher())
                        .withCertificateNumber(request.getCertificateNumber())
                        .withCertificateGrade(request.getCertificateGrade())
                        .withPictureUrl(request.getPictureUrl())
                        .withExtInfo(request.getExtInfo());
                return RestResultUtil.buildSuccessResult(certificateService.create(builder.build(), context), "创建证书记录成功");
            }
        });
    }

    /**
     * 修改证书信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PutMapping(value = "modify")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> modifyCertificate(@RequestBody CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更改证书记录", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getCertificateId(), "证书id不能为空");
                AssertUtil.assertStringNotBlank(request.getCertificateType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书类型不能为空");
                AssertUtil.assertNotNull(request.getCertificatePublishTime(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书发布时间不能为空");
                //获取 Extinfo 中 description信息
                AssertUtil.assertNotNull(request.getExtInfo().get(DESCRIPTION), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "证书详细信息不能为空");
                boolean validateTime = new Date(request.getCertificatePublishTime()).before(new Date());
                AssertUtil.assertTrue(validateTime, "证书发布时间必须早于当前时间");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateRequestBuilder builder = CertificateRequestBuilder.getInstance()
                        .withCertificateId(request.getCertificateId())
                        .withCertificateName(request.getCertificateName())
                        .withCompetitionName(request.getCompetitionName())
                        .withUserID(request.getUserId())
                        .withCertificateOrganization(request.getCertificateOrganization())
                        .withCertificatePublishTime(request.getCertificatePublishTime())
                        .withCertificateType(request.getCertificateType())
                        .withType(request.getType())
                        .withConfirmUserId(request.getConfirmUserId())
                        .withRank(request.getRank())
                        .withWorkUserId(request.getWorkUserId())
                        .withExpirationTime(request.getExpirationTime())
                        .withTeamName(request.getTeamName())
                        .withTeamId(request.getTeamId())
                        .withTeacher(request.getTeacher())
                        .withCertificateNumber(request.getCertificateNumber())
                        .withCertificateGrade(request.getCertificateGrade())
                        .withExpirationTime(request.getExpirationTime())
                        .withPictureUrl(request.getPictureUrl())
                        .withExtInfo(request.getExtInfo());
                return RestResultUtil.buildSuccessResult(certificateService.update(builder.build(), context), "修改记录成功");
            }
        });
    }

    /**
     * 删除证书
     */
    @DeleteMapping(value = "delete")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result deleteCertificate(CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "删除证书记录", request, new RestOperateCallBack<CertificateBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getCertificateId(), "证书id不能为空");
                AssertUtil.assertNotNull(request.getCertificateType(), "证书类型不能为空");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateRequestBuilder builder = CertificateRequestBuilder.getInstance()
                        .withCertificateId(request.getCertificateId())
                        .withTeamId(request.getTeamId())
                        .withUserID(request.getUserId())
                        .withCertificateType(request.getCertificateType());
                certificateService.deleteByStudent(builder.build(), context);
                return RestResultUtil.buildSuccessResult("删除记录成功");
            }
        });
    }

    /**
     * 获取证书详细信息
     */
    @GetMapping(value = "details")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<CertificateBO> getCertificateByCertificateId(CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "根据证书id获取该证书详细信息", request, new RestOperateCallBack<CertificateBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, "请求体不能为空");
                AssertUtil.assertNotNull(request.getCertificateId(), "证书id不能为空");
            }

            @Override
            public Result<CertificateBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateBO certificateBO;
                certificateBO = certificateService.findByCertificateId(request.getCertificateId());
                return RestResultUtil.buildSuccessResult(certificateBO, "获取详细记录信息成功");
            }
        });
    }

    /**
     * 获取多条证书记录
     */
    @GetMapping(value = "certificates")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    @CheckLogin
    public Result<Map<String, List<CertificateBO>>> getCertificatesByUserId(CertificateRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "查找所有类型证书记录", request, new RestOperateCallBack<Map<String, List<CertificateBO>>>() {
            @Override
            public void before() {
            }

            @Override
            public Result<Map<String, List<CertificateBO>>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                CertificateRequestBuilder builder = CertificateRequestBuilder.getInstance()
                        .withUserID(request.getUserId())
                        .withCertificateType("QUALIFICATIONS");
                List<CertificateBO> qualifications = certificateService.findAllByCertificateTypeAndUserId(builder.build(), context);
                
                builder.withCertificateType("SKILL");
                List<CertificateBO> skill = certificateService.findAllByCertificateTypeAndUserId(builder.build(), context);
                
                builder.withCertificateType("COMPETITION");
                List<CertificateBO> competition = certificateService.findAllByCertificateTypeAndUserId(builder.build(), context);
    
                builder.withCertificateType("CET_4_6");
                List<CertificateBO> cet = certificateService.findAllByCertificateTypeAndUserId(builder.build(), context);
    
                Map<String, List<CertificateBO>> certificates = new HashMap<>(1);
                certificates.put("QUALIFICATIONS", qualifications);
                certificates.put("SKILL", skill);
                certificates.put("COMPETITION", competition);
                certificates.put("CET_4_6", cet);
                return RestResultUtil.buildSuccessResult(certificates, "获取用户所有类型证书成功");
            }
        });
    }
}
