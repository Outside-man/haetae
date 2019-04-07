/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service.impl;

import org.springframework.stereotype.Service;
import us.betahouse.haetae.certificate.dal.service.SkillRepoService;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;

import java.util.List;

/**
 * @author guofan.cp
 * @version : SkillRepoServiceImpl.java 2019/04/05 12:43 guofan.cp
 */
@Service
public class SkillRepoServiceImpl implements SkillRepoService {
    @Override
    public CertificateBO create(CertificateBO certificateBO) {
        return null;
    }

    @Override
    public void delete(String certificateId) {

    }

    @Override
    public CertificateBO modify(CertificateBO certificateBO) {
        return null;
    }

    @Override
    public CertificateBO queryByCertificateId(String certificateId) {
        return null;
    }

    @Override
    public List<CertificateBO> queryByUserid(String userid) {
        return null;
    }

    @Override
    public List<CertificateBO> queryByCertificateName(String certificateName) {
        return null;
    }
}
