/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.certificate.dal.service.CompetitionRepoService;
import us.betahouse.haetae.certificate.dal.service.QualificationsRepoService;
import us.betahouse.haetae.certificate.dal.service.SkillRepoService;
import us.betahouse.haetae.certificate.enums.CertificateExtInfoKey;
import us.betahouse.haetae.certificate.enums.CertificateStateEnum;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.manager.CertificateManager;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资格证书管理器实现
 *
 * @author guofan.cp
 * @version : QualificationsManagerImpl.java 2019/04/05 19:54 guofan.cp
 */
@Service
public class CertificateManagerImpl implements CertificateManager {

    @Autowired
    private QualificationsRepoService qualificationsRepoService;
    @Autowired
    private CompetitionRepoService competitionRepoService;
    @Autowired
    private SkillRepoService skillRepoService;

    @Override
    public CertificateBO createQualifications(CertificateManagerRequest request) {
        //资格证书类型判断
        String type = request.getType();
        CertificateTypeEnum typeEnum = CertificateTypeEnum.getByCode(type);
        AssertUtil.assertNotNull(typeEnum, "资格证书类型不存在");
        CertificateBO certificateBO = new CertificateBO();
        //资格证书 必须属性
        certificateBO.setCertificateType(CertificateTypeEnum.QUALIFICATIONS.getCode());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setType(request.getType());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        certificateBO.putExtInfo(CertificateExtInfoKey.DESCRIPTION.getCode(), request.getDescription());
        //设置证书状态  待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        certificateBO = setExtInfos(certificateBO, request);
        switch (typeEnum) {
            //国际资格证书  参数同普通证书相同
            case INTERNATIONAL_QUALIFICATIONS:
                //普通资格证书
            case NORMAL_QUALIFICATIONS: {
                certificateBO = qualificationsRepoService.create(certificateBO);
                return certificateBO;
            }
            //教师资格证书
            case TEACHER_QUALIFICATIONS: {
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()), "教师资格证,学科不能为空");
                AssertUtil.assertNotNull(request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()), "教师资格证,教师等级不能为空");
                //教师资格证书额外属性添加教师资格等级/学科
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_LEVEL.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()));
                certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_SUBJECT.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()));
                certificateBO = qualificationsRepoService.create(certificateBO);
                return certificateBO;
            }
            //异常
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS, "资格证书归属类别不存在");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CertificateBO createCompetition(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        if (request.getTeacher() != null) {
            certificateBO.setTeacher(request.getTeacher());
        }
        certificateBO.setCertificateType(CertificateTypeEnum.COMPETITION.getCode());
        certificateBO.setCompetitionName(request.getCompetitionName());
        certificateBO.setRank(request.getRank());
        certificateBO.setTeamName(request.getTeamName());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setWorkUserId(request.getWorkUserId());
        //设置证书状态 待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        //放入拓展信息
        certificateBO = setExtInfos(certificateBO, request);
        //当前用户是否是队员
        boolean includeUserId = false;
        AssertUtil.assertNotNull(request.getWorkUserId(), "队友学号不能为空");
        //目前团队成员人数限制不能超过五个人,把自己的学号也要填进去 方便多条记录队友属性统一字段
        AssertUtil.assertTrue(request.getWorkUserId().size() <= 5, "队友人数不能超过五个人");
        CertificateBO thisCertificate = new CertificateBO();
        //返回单个BO  创建成员等多条记录(每个记录中)
        for (String userid : request.getWorkUserId()) {
            //更改每条记录中学生id 传参引用证书id会被修改
            certificateBO.setUserId(userid);
            certificateBO.setCertificateId(null);
            competitionRepoService.create(certificateBO);
            //判断当前用户id与创建记录的学生id是否相同  返回当前对象记录
            if (certificateBO.getUserId().equals(request.getUserId())) {
                thisCertificate = certificateBO;
                includeUserId = true;
            }
        }
        AssertUtil.assertTrue(includeUserId, "只能由队员创建记录");
        return thisCertificate;
    }

    @Override
    public CertificateBO createSkill(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCertificateType(CertificateTypeEnum.SKILL.getCode());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setExpirationTime(new Date(request.getExpirationTime()));
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setRank(request.getRank());
        certificateBO.setCertificateNumber(request.getCertificateNumber());
        //设置状态 待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        //额外信息插入extinfo
        certificateBO = setExtInfos(certificateBO, request);
        certificateBO = skillRepoService.create(certificateBO);
        return certificateBO;
    }

    @Override
    public CertificateBO modifyQualifications(CertificateManagerRequest request) {
        return null;
    }

    @Override
    public CertificateBO modifyCompetition(CertificateManagerRequest request) {
        CertificateBO certificateBO = new CertificateBO();
        if (request.getTeacher() != null) {
            certificateBO.setTeacher(request.getTeacher());
        }
        certificateBO.setCertificateType(CertificateTypeEnum.COMPETITION.getCode());
        certificateBO.setCompetitionName(request.getCompetitionName());
        certificateBO.setRank(request.getRank());
        certificateBO.setTeamName(request.getTeamName());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.setWorkUserId(request.getWorkUserId());
        //设置证书状态 待审核
        certificateBO.setStatus(CertificateStateEnum.UNREVIEWED.getCode());
        //放入拓展信息
        certificateBO = setExtInfos(certificateBO, request);
        //当前用户是否是队员
        boolean includeUserId = false;
        AssertUtil.assertNotNull(request.getWorkUserId(), "队友学号不能为空");
        //目前团队成员人数限制不能超过五个人,把自己的学号也要填进去 方便多条记录队友属性统一字段
        AssertUtil.assertTrue(request.getWorkUserId().size() <= 5, "队友人数不能超过五个人");
        CertificateBO thisCertificate = new CertificateBO();
        //返回单个BO  创建成员等多条记录(每个记录中)
        //删掉原来存在而现在不存在的队友
        List<CertificateBO> certificateBOS = competitionRepoService.queryByTeamId(request.getTeamId());
        for (CertificateBO certificateBO1 : certificateBOS) {
            boolean flat = false;
            for (String userid : request.getWorkUserId()) {
                if (certificateBO1.getUserId().equals(userid)) {
                    flat = true;
                    break;
                }
            }
            if (!flat) {
                competitionRepoService.delete(certificateBO1.getCertificateId());
            }
        }
        for (String userid : request.getWorkUserId()) {
            if (competitionRepoService.queryByCertificateIdAndUserId(request.getCertificateId(), userid) == null) {
                certificateBO.setUserId(userid);
                certificateBO.setCertificateId(null);
                competitionRepoService.create(certificateBO);
            } else {
                certificateBO.setUserId(userid);
                certificateBO.setCertificateId(request.getCertificateId());
                competitionRepoService.modify(certificateBO);
            }
            if (certificateBO.getUserId().equals(request.getUserId())) {
                thisCertificate = certificateBO;
                includeUserId = true;
            }
        }
        AssertUtil.assertTrue(includeUserId, "只能由队员创建记录");
        return thisCertificate;
    }

    @Override
    public CertificateBO modifySkill(CertificateManagerRequest request) {
        return null;
    }

    static CertificateBO setExtInfos(CertificateBO certificateBO, CertificateManagerRequest request) {
        //放入描述信息
        certificateBO.putExtInfo(CertificateExtInfoKey.DESCRIPTION.getCode(), request.getExtInfo().get(CertificateExtInfoKey.DESCRIPTION.getCode()));
        //放入教师资格
        if (request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()) != null) {
            certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_LEVEL.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_LEVEL.getCode()));
        }
        //放入教师学科
        if (request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()) != null) {
            certificateBO.putExtInfo(CertificateExtInfoKey.TEACHER_SUBJECT.getCode(), request.getExtInfo().get(CertificateExtInfoKey.TEACHER_SUBJECT.getCode()));
        }
        return certificateBO;
    }
}
