/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.organization;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.converter.OrganizationVOConverter;
import us.betahouse.haetae.model.organization.OrganizationRestRequest;
import us.betahouse.haetae.model.organization.OrganizationVO;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.serviceimpl.organization.builder.OrganizationRequestBuilder;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationManagerService;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 组织接口
 *
 * @author MessiahJK
 * @version : OrganizationController.java 2018/11/25 15:05 MessiahJK
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private OrganizationManagerService organizationManagerService;

    /**
     * 获取组织
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<OrganizationVO>> getOrganization(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取组织", request, new RestOperateCallBack<List<OrganizationVO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            }

            @Override
            public Result<List<OrganizationVO>> execute() {
                List<OrganizationVO> organizationVOList;
                if (StringUtils.isNotBlank(request.getOrganizationId())) {
                    organizationVOList = Collections.singletonList(OrganizationVOConverter.convert(organizationRepoService.queryByOrganizationId(request.getOrganizationId())));
                } else {
                    organizationVOList = CollectionUtils.toStream(organizationRepoService.queryAllOrganization())
                            .filter(Objects::nonNull)
                            .map(OrganizationVOConverter::convert)
                            .sorted(Comparator.comparingInt(o -> o.getFirstAlpha().charAt(0)))
                            .collect(Collectors.toList());
                }
                return RestResultUtil.buildSuccessResult(organizationVOList, "获取组织成功");
            }
        });
    }

    /**
     * 创建组织
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<OrganizationVO> createOrganization(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "创建组织", request, new RestOperateCallBack<OrganizationVO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "组织名不能为空");
            }

            @Override
            public Result<OrganizationVO> execute() {
                OrganizationRequestBuilder builder = OrganizationRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withOrganizationName(request.getOrganizationName())
                        // 可选入参
                        // 主管学号
                        .withStuId(request.getStuId())
                        .withMemberDesc(request.getMemberDesc())
                        .withOrganizationType(request.getOrganizationType())
                        .withPrimaryOrganizationId(request.getPrimaryOrganizationId());
                return RestResultUtil.buildSuccessResult(OrganizationVOConverter.convert(organizationManagerService.create(builder.build())),
                        "创建组织成功");
            }
        });
    }

    /**
     * 解散组织
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @DeleteMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result disbandOrganization(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "解散组织", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "组织id不能为空");
            }

            @Override
            public Result execute() {
                OrganizationRequestBuilder builder = OrganizationRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withOrganizationId(request.getOrganizationId());
                organizationManagerService.disband(builder.build());
                return RestResultUtil.buildSuccessResult(null, "解散组织成功");
            }
        });
    }

    @CheckLogin
    @GetMapping("/all")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<OrganizationVO>> getAllOrganization(OrganizationRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取组织列表", request, new RestOperateCallBack<List<OrganizationVO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<OrganizationVO>> execute() {
                List<OrganizationVO> organizationVOList = CollectionUtils.toStream(organizationRepoService.queryAllOrganization())
                        .filter(Objects::nonNull)
                        .map(OrganizationVOConverter::convert)
                        .sorted(Comparator.comparingInt(o -> o.getFirstAlpha().charAt(0)))
                        .collect(Collectors.toList());
                return RestResultUtil.buildSuccessResult(organizationVOList, "获取组织列表成功");
            }
        });
    }




}
