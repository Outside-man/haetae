/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.organization.dal.convert.EntityConverter;
import us.betahouse.haetae.organization.dal.model.OrganizationDO;
import us.betahouse.haetae.organization.dal.repo.OrganizationRepo;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.idfactory.BizIdFactory;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 组织仓储服务实现
 *
 * @author dango.yxm
 * @version : OrganizationRepoServiceImpl.java 2019/03/25 14:36 dango.yxm
 */
@Service
public class OrganizationRepoServiceImpl implements OrganizationRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationRepoServiceImpl.class);

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private BizIdFactory organizationBizIdFactory;

    @Override
    public OrganizationBO create(OrganizationBO organizationBO) {
        AssertUtil.assertStringNotBlank(organizationBO.getOrganizationName(), "组织名称不能为空");
        OrganizationDO organizationDO = organizationRepo.findByOrganizationName(organizationBO.getOrganizationName());
        AssertUtil.assertNull(organizationDO, "组织已存在");

        if (StringUtils.isBlank(organizationBO.getOrganizationId())) {
            organizationBO.setOrganizationId(organizationBizIdFactory.getOrganizationId());
        }
        return EntityConverter.convert(organizationRepo.save(EntityConverter.convert(organizationBO)));
    }

    @Override
    public OrganizationBO modify(String organizationId, OrganizationBO organizationBO) {
        OrganizationDO organizationDO = organizationRepo.findByOrganizationId(organizationId);
        if (organizationDO == null) {
            LoggerUtil.error(LOGGER, "更新的组织不存在 organizationId={0}", organizationId);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的组织不存在");
        }

        OrganizationDO newOrganizationDO = EntityConverter.convert(organizationBO);

        if (newOrganizationDO.getOrganizationName() != null) {
            organizationDO.setOrganizationName(newOrganizationDO.getOrganizationName());
        }
        if (newOrganizationDO.getOrganizationType() != null) {
            organizationDO.setOrganizationType(newOrganizationDO.getOrganizationType());
        }
        // 特殊逻辑 只有extInfo == null 时认为主动清空
        if (organizationBO.getExtInfo() == null) {
            organizationDO.setExtInfo(JSON.toJSONString(new HashMap<>()));
        } else if (!organizationBO.getExtInfo().isEmpty()) {
            organizationDO.setExtInfo(newOrganizationDO.getExtInfo());
        }
        return EntityConverter.convert(organizationRepo.save(organizationDO));
    }

    @Override
    public List<OrganizationBO> queryAllOrganization() {
        return CollectionUtils.toStream(organizationRepo.findAll())
                .filter(Objects::nonNull)
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationBO queryByOrganizationId(String organizationId) {
        return EntityConverter.convert(organizationRepo.findByOrganizationId(organizationId));
    }

    @Override
    public OrganizationBO queryByOrganizationName(String organizationName) {
        return EntityConverter.convert(organizationRepo.findByOrganizationName(organizationName));
    }
}