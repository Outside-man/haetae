/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.certificate.builder.CertificateBOBuilder;
import us.betahouse.haetae.certificate.dal.model.SkillDO;
import us.betahouse.haetae.certificate.dal.repo.SkillDORepo;
import us.betahouse.haetae.certificate.dal.service.SkillRepoService;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.idfactory.BizIdFactory;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guofan.cp
 * @version : SkillRepoServiceImpl.java 2019/04/05 12:43 guofan.cp
 */
@Service
public class SkillRepoServiceImpl implements SkillRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(SkillRepoServiceImpl.class);

    @Autowired
    private SkillDORepo skillDORepo;
    @Autowired
    private BizIdFactory bizIdFactory;

    @Override
    public CertificateBO create(CertificateBO certificateBO) {
        if (StringUtils.isBlank(certificateBO.getCertificateId())) {
            certificateBO.setCertificateId(bizIdFactory.getSkillId());
        }
        return convert(skillDORepo.save(convert(certificateBO)));
    }

    @Override
    public void delete(String certificateId) {
        skillDORepo.deleteByCertificateId(certificateId);
    }

    @Override
    public void deleteByCertificateIdAndUserId(String certificateId, String userId) {
        skillDORepo.deleteByCertificateIdAndUserId(certificateId,userId);
    }

    @Override
    public CertificateBO modify(CertificateBO certificateBO) {
        return null;
    }

    @Override
    public CertificateBO queryByCertificateId(String certificateId) {
        return convert(skillDORepo.findByCertificateId(certificateId));
    }

    @Override
    public List<CertificateBO> queryByUserId(String userId) {
        return CollectionUtils.toStream(skillDORepo.findByUserId(userId))
                .filter(Objects::isNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateBO> queryByCertificateName(String certificateName) {
        return CollectionUtils.toStream(skillDORepo.findByCertificateName(certificateName))
                .filter(Objects::isNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateBO queryByCertificateIdAndUserId(String certificateId, String userId) {
        return convert(skillDORepo.findByCertificateIdAndUserId(certificateId,userId));
    }

    /**
     * 资格证书DO2BO
     *
     * @param skillDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public CertificateBO convert(SkillDO skillDO) {
        if (skillDO == null) {
            return null;
        }
        CertificateBOBuilder builder = CertificateBOBuilder.getInstance();
        builder.withCertificateId(skillDO.getCertificateId())
                .withCertificateName(skillDO.getCertificateName())
                .withCertificatePublishTime(skillDO.getCertificatePublishTime())
                .withCertificateType(CertificateTypeEnum.SKILL.getCode())
                .withCertificateNumber(skillDO.getCertificateNumber())
                .withStatus(skillDO.getStatus())
                .withRank(skillDO.getRank())
                .withExpirationTime(skillDO.getExpirationTime())
                .withExtInfo(JSONObject.parseObject(skillDO.getExtInfo(), Map.class));
        return builder.build();
    }

    /**
     * 资格证书BO2DO
     *
     * @param certificateBO
     * @return
     */
    public SkillDO convert(CertificateBO certificateBO) {
        if (certificateBO == null) {
            return null;
        }
        SkillDO skillDO = new SkillDO();
        skillDO.setCertificateId(certificateBO.getCertificateId());
        skillDO.setUserId(certificateBO.getUserId());
        skillDO.setCertificateName(certificateBO.getCertificateName());
        skillDO.setCertificateNumber(certificateBO.getCertificateNumber());
        skillDO.setCertificatePublishTime(certificateBO.getCertificatePublishTime());
        skillDO.setExpirationTime(certificateBO.getExpirationTime());
        skillDO.setExtInfo(JSON.toJSONString(certificateBO.getExtInfo()));
        skillDO.setStatus(certificateBO.getStatus());
        skillDO.setRank(certificateBO.getRank());
        return skillDO;
    }
}
