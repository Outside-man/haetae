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
import us.betahouse.haetae.certificate.dal.model.QualificationsDO;
import us.betahouse.haetae.certificate.dal.repo.QualificationsDORepo;
import us.betahouse.haetae.certificate.dal.service.QualificationsRepoService;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.idfactory.BizIdFactory;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guofan.cp
 * @version : QualificationsRepoServiceImpl.java 2019/04/05 12:43 guofan.cp
 */
@Service
public class QualificationsRepoServiceImpl implements QualificationsRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(QualificationsRepoServiceImpl.class);

    @Autowired
    private QualificationsDORepo qualificationsDORepo;
    @Autowired
    private BizIdFactory bizIdFactory;

    @Override
    public CertificateBO create(CertificateBO certificateBO) {
        if (StringUtils.isBlank(certificateBO.getCertificateId())) {
            certificateBO.setCertificateId(bizIdFactory.getCertificateId());
        }
        return convert(qualificationsDORepo.save(convert(certificateBO)));
    }

    @Override
    public void deleteByCertificateIdAndUserId(String certificateId, String userId) {
        qualificationsDORepo.deleteByCertificateIdAndUserId(certificateId, userId);
    }


    @Override
    public CertificateBO modify(CertificateBO certificateBO) {
        QualificationsDO qualificationsDO = qualificationsDORepo.findByCertificateId(certificateBO.getCertificateId());
        if (qualificationsDO == null) {
            LoggerUtil.error(LOGGER, "更新资格证书不存在 certificateId={0}", certificateBO.getCertificateId());
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新证书不存在");
        }
        QualificationsDO newQualificationDO = convert(certificateBO);
        //更新名称
        if (newQualificationDO.getCertificateName() != null) {
            qualificationsDO.setCertificateName(newQualificationDO.getCertificateName());
        }
        //更新证书单位
        if (newQualificationDO.getCertificateOrganization() != null) {
            qualificationsDO.setCertificateOrganization(newQualificationDO.getCertificateOrganization());
        }
        //更新证书类型
        if (newQualificationDO.getType() != null) {
            qualificationsDO.setType(newQualificationDO.getType());
        }
        //更新证书编号
        if (newQualificationDO.getCertificateNumber() != null) {
            qualificationsDO.setCertificateNumber(newQualificationDO.getCertificateNumber());
        }
        //更新证书发布时间
        if (newQualificationDO.getCertificatePublishTime() != null) {
            qualificationsDO.setCertificatePublishTime(newQualificationDO.getCertificatePublishTime());
        }
        //更新额外信息
        if (newQualificationDO.getExtInfo() != null) {
            qualificationsDO.setExtInfo(newQualificationDO.getExtInfo());
        }
        //更新证书状态
        if (newQualificationDO.getStatus() != null) {
            qualificationsDO.setStatus(newQualificationDO.getStatus());
        }
        //更新修改时间
        if (newQualificationDO.getGmtModified() != null) {
            qualificationsDO.setGmtModified(newQualificationDO.getGmtModified());
        }
        return convert(qualificationsDORepo.save(qualificationsDO));
    }

    @Override
    public CertificateBO queryByCertificateId(String certificateId) {
        return convert(qualificationsDORepo.findByCertificateId(certificateId));
    }

    @Override
    public List<CertificateBO> queryByUserId(String userId) {
        return CollectionUtils.toStream(qualificationsDORepo.findByUserId(userId))
                .filter(Objects::isNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateBO> queryByCertificateName(String certificateName) {
        return CollectionUtils.toStream(qualificationsDORepo.findByCertificateName(certificateName))
                .filter(Objects::isNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateBO queryByUserIdAndCertificateId(String userId, String certificateId) {
        return convert(qualificationsDORepo.findByCertificateIdAndUserId(certificateId, userId));
    }

    /**
     * 资格证书DO2BO
     *
     * @param qualificationsDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public CertificateBO convert(QualificationsDO qualificationsDO) {
        if (qualificationsDO == null) {
            return null;
        }
        CertificateBOBuilder builder = CertificateBOBuilder.getInstance();
        builder.withCertificateId(qualificationsDO.getCertificateId())
                .withCertificateName(qualificationsDO.getCertificateName())
                .withCertificateOrganization(qualificationsDO.getCertificateOrganization())
                .withCertificatePublishTime(qualificationsDO.getCertificatePublishTime())
                .withCertificateType(CertificateTypeEnum.QUALIFICATIONS.getCode())
                .withCertificateNumber(qualificationsDO.getCertificateNumber())
                .withStatus(qualificationsDO.getStatus())
                .withType(qualificationsDO.getType())
                .withExtInfo(JSONObject.parseObject(qualificationsDO.getExtInfo(), Map.class));
        return builder.build();
    }

    /**
     * 资格证书BO2DO
     *
     * @param certificateBO
     * @return
     */
    public QualificationsDO convert(CertificateBO certificateBO) {
        if (certificateBO == null) {
            return null;
        }
        QualificationsDO qualificationsDO = new QualificationsDO();
        qualificationsDO.setUserId(certificateBO.getUserId());
        qualificationsDO.setCertificateId(certificateBO.getCertificateId());
        qualificationsDO.setCertificateName(certificateBO.getCertificateName());
        qualificationsDO.setCertificateNumber(certificateBO.getCertificateNumber());
        qualificationsDO.setCertificateOrganization(certificateBO.getCertificateOrganization());
        qualificationsDO.setCertificatePublishTime(certificateBO.getCertificatePublishTime());
        qualificationsDO.setExtInfo(JSON.toJSONString(certificateBO.getExtInfo()));
        qualificationsDO.setType(certificateBO.getType());
        qualificationsDO.setStatus(certificateBO.getStatus());
        return qualificationsDO;
    }
}
