/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.certificate.dal.convert.EntityConverter;
import us.betahouse.haetae.certificate.dal.model.QualificationsDO;
import us.betahouse.haetae.certificate.dal.repo.QualificationsDORepo;
import us.betahouse.haetae.certificate.dal.service.QualificationsRepoService;
import us.betahouse.haetae.certificate.idfactory.BizIdFactory;
import us.betahouse.haetae.certificate.model.basic.QualificationsBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
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
    private BizIdFactory bizIdFactoryl;

    @Override
    public QualificationsBO create(QualificationsBO qualificationsBO) {
        if (StringUtils.isBlank(qualificationsBO.getCertificateId())) {
            qualificationsBO.setCertificateId(bizIdFactoryl.getCertificateId());
        }
        return EntityConverter.convert(qualificationsDORepo.save(EntityConverter.convert(qualificationsBO)));
    }

    @Override
    public void delete(String certificateId) {
        qualificationsDORepo.deleteByCertificateId(certificateId);
    }

    @Override
    public QualificationsBO modify(QualificationsBO qualificationsBO) {
        QualificationsDO qualificationsDO = qualificationsDORepo.findByCertificateId(qualificationsBO.getCertificateId());
        if (qualificationsDO == null) {
            LoggerUtil.error(LOGGER, "更新资格证书不存在 certificateId={0}", qualificationsBO.getCertificateId());
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新证书不存在");
        }
        QualificationsDO newQualificationDO = EntityConverter.convert(qualificationsBO);
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
            qualificationsDO.setType(qualificationsDO.getType());
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
        return EntityConverter.convert(qualificationsDORepo.save(qualificationsDO));
    }

    @Override
    public QualificationsBO queryByCertificateId(String certificateId) {
        return EntityConverter.convert(qualificationsDORepo.findByCertificateId(certificateId));
    }

    @Override
    public List<QualificationsBO> queryByUserid(String userid) {
        return CollectionUtils.toStream(qualificationsDORepo.findByUserId(userid))
                .filter(Objects::isNull)
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<QualificationsBO> queryByCertificateName(String certificateName) {
        return CollectionUtils.toStream(qualificationsDORepo.findByCertificateName(certificateName))
                .filter(Objects::isNull)
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }
}
