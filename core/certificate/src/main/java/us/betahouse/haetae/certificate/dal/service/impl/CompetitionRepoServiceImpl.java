/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.certificate.builder.CertificateBOBuilder;
import us.betahouse.haetae.certificate.dal.model.CompetitionDO;
import us.betahouse.haetae.certificate.dal.repo.CompetitionDORepo;
import us.betahouse.haetae.certificate.dal.service.CompetitionRepoService;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.idfactory.BizIdFactory;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author guofan.cp
 * @version : CompetitionServiceImpl.java 2019/04/05 12:43 guofan.cp
 */
@Service
public class CompetitionRepoServiceImpl implements CompetitionRepoService {
    private final Logger LOGGER = LoggerFactory.getLogger(CompetitionRepoServiceImpl.class);

    @Autowired
    private CompetitionDORepo competitionDORepo;

    @Autowired
    private BizIdFactory bizIdFactory;

    @Override
    public CertificateBO create(CertificateBO certificateBO) {
        //设置证书id 各人唯一
        if (StringUtils.isBlank(certificateBO.getCertificateId())) {
            certificateBO.setCertificateId(bizIdFactory.getCompetitionId());
        }
        //团队id 团队唯一
        if (StringUtils.isBlank(certificateBO.getTeamId())) {
            certificateBO.setTeamId(bizIdFactory.getCompetitionTeamId());
        }
        return convert(competitionDORepo.save(convert(certificateBO)));
    }

    @Override
    public void delete(String certificateId) {
        competitionDORepo.deleteByCertificateId(certificateId);
    }

    @Override
    public void deleteAllByTeamId(String teamId) {
        competitionDORepo.deleteAllByTeamId(teamId);
    }


    @Override
    public CertificateBO modify(CertificateBO certificateBO) {
        CompetitionDO competitionDO = competitionDORepo.findByCertificateId(certificateBO.getCertificateId());
        if (competitionDO == null) {
            LoggerUtil.error(LOGGER, "更新竞赛证书不存在 certificate={0}", certificateBO.getCertificateId());
        }
        CompetitionDO newCompetitionDO = convert(certificateBO);
        if (newCompetitionDO.getCertificateId() != null) {
            competitionDO.setCertificateId(newCompetitionDO.getCertificateId());
        }
        //更新名称
        if (newCompetitionDO.getCompetitionName() != null) {
            competitionDO.setCompetitionName(newCompetitionDO.getCompetitionName());
        }
        //等级
        if (newCompetitionDO.getRank() != null) {
            competitionDO.setRank(newCompetitionDO.getRank());
        }
        //更新证书发布时间
        if (newCompetitionDO.getCertificatePublishTime() != null) {
            competitionDO.setCertificatePublishTime(newCompetitionDO.getCertificatePublishTime());
        }
        //队名
        if (newCompetitionDO.getTeamName() != null) {
            competitionDO.setTeamName(newCompetitionDO.getTeamName());
        }
        //指导老师
        if (newCompetitionDO.getTeacher() != null) {
            competitionDO.setTeacher(newCompetitionDO.getTeacher());
        }
        //队友学号
        if (newCompetitionDO.getWorkersUserId() != null) {
            competitionDO.setWorkersUserId(newCompetitionDO.getWorkersUserId());
        }
        //更新额外信息
        if (newCompetitionDO.getExtInfo() != null) {
            competitionDO.setExtInfo(newCompetitionDO.getExtInfo());
        }
        //更新证书状态
        if (newCompetitionDO.getStatus() != null) {
            competitionDO.setStatus(newCompetitionDO.getStatus());
        }
        //更新审核员id
        if (newCompetitionDO.getConfirmUserId() != null) {
            competitionDO.setConfirmUserId(newCompetitionDO.getConfirmUserId());
        }
        //更新图片路径
        if(newCompetitionDO.getPictureUrl()!=null){
            competitionDO.setPictureUrl(newCompetitionDO.getPictureUrl());
        }
        //更新修改时间
        competitionDO.setGmtModified(new Date());
        return convert(competitionDORepo.save(competitionDO));
    }

    @Override
    public CertificateBO queryByCertificateId(String certificateId) {
        return convert(competitionDORepo.findByCertificateId(certificateId));
    }

    @Override
    public List<CertificateBO> queryByUserId(String userId) {
        return CollectionUtils.toStream(competitionDORepo.findByUserId(userId))
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateBO> queryAll() {
        return CollectionUtils.toStream(competitionDORepo.findAll())
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateBO> queryByCompetitionName(String competitionName) {
        return CollectionUtils.toStream(competitionDORepo.findByCompetitionName(competitionName))
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CertificateBO> queryByTeamId(String teamId) {
        return CollectionUtils.toStream(competitionDORepo.findByTeamId(teamId))
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateBO queryByCertificateIdAndUserId(String certificateId, String userId) {
        return convert(competitionDORepo.findByCertificateIdAndUserId(certificateId, userId));
    }

    @Override
    public CertificateBO queryByTeamIdAndUserId(String teamId, String userId) {
        return convert(competitionDORepo.findByUserIdAndTeamId(userId, teamId));
    }

    @Override
    public List<CertificateBO> queryByCertificateNameAndUserId(String certificateName, String userId) {
        return CollectionUtils.toStream(competitionDORepo.findByCompetitionNameAndUserId(certificateName, userId))
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateBO queryByUserIdAndTeamId(String userId, String teamId) {
        return convert(competitionDORepo.findByUserIdAndTeamId(userId, teamId));
    }

    /**
     * 竞赛证书DO2B0
     *
     * @param competitionDO
     * @return
     */
    @SuppressWarnings("unchecked")
    public CertificateBO convert(CompetitionDO competitionDO) {
        if (competitionDO == null) {
            return null;
        }
        CertificateBOBuilder builder = CertificateBOBuilder.getInstance();
        builder.withCertificateId(competitionDO.getCertificateId())
                .withCompetitionName(competitionDO.getCompetitionName())
                .withCertificateType(CertificateTypeEnum.COMPETITION.getCode())
                .withRank(competitionDO.getRank())
                .withTeamID(competitionDO.getTeamId())
                .withCertificatePublishTime(competitionDO.getCertificatePublishTime())
                .withTeamName(competitionDO.getTeamName())
                .withStatus(competitionDO.getStatus())
                .withConfirmUserId(competitionDO.getConfirmUserId())
                .withUserID(competitionDO.getUserId())
                .withCertificatePictureUrl(competitionDO.getPictureUrl())
                .withWorkUserId(JSON.parseObject(competitionDO.getWorkersUserId(), List.class))
                .withExtInfo(JSON.parseObject(competitionDO.getExtInfo(), Map.class))
                .withTeacher(JSON.parseObject(competitionDO.getTeacher(), List.class));
        return builder.build();
    }

    /**
     * 竞赛证书B02DO
     *
     * @param certificateBO
     * @return
     */
    public CompetitionDO convert(CertificateBO certificateBO) {
        if (certificateBO == null) {
            return null;
        }
        CompetitionDO competitionDO = new CompetitionDO();
        competitionDO.setCompetitionName(certificateBO.getCompetitionName());
        competitionDO.setCertificateId(certificateBO.getCertificateId());
        competitionDO.setUserId(certificateBO.getUserId());
        competitionDO.setRank(certificateBO.getRank());
        competitionDO.setCertificatePublishTime(certificateBO.getCertificatePublishTime());
        competitionDO.setTeamName(certificateBO.getTeamName());
        competitionDO.setConfirmUserId(certificateBO.getConfirmUserId());
        competitionDO.setTeamId(certificateBO.getTeamId());
        competitionDO.setStatus(certificateBO.getStatus());
        competitionDO.setPictureUrl(certificateBO.getPictureUrl());
        competitionDO.setWorkersUserId(JSON.toJSONString(certificateBO.getWorkUserId()));
        competitionDO.setExtInfo(JSON.toJSONString(certificateBO.getExtInfo()));
        competitionDO.setTeacher(JSON.toJSONString(certificateBO.getTeacher()));
        return competitionDO;
    }
}
