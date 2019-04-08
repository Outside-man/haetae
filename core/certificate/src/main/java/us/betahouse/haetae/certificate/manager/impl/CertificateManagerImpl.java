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
import us.betahouse.haetae.certificate.enums.CertificateExtInfoKey;
import us.betahouse.haetae.certificate.enums.CertificateTypeEnum;
import us.betahouse.haetae.certificate.manager.CertificateManager;
import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.Date;

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

    @Override
    public CertificateBO createQualifications(CertificateManagerRequest request) {
        //资格证书类型判断
        String type = request.getType();
        CertificateTypeEnum typeEnum = CertificateTypeEnum.getByCode(type);
        CertificateBO certificateBO = new CertificateBO();
        //资格证书 必须属性
        certificateBO.setCertificateType(CertificateTypeEnum.QUALIFICATIONS.getCode());
        certificateBO.setCertificateName(request.getCertificateName());
        certificateBO.setType(request.getType());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.putExtInfo(CertificateExtInfoKey.DESCRIPTION.getCode(), request.getDescription());
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
        //比赛类型
        String type = request.getType();
        CertificateTypeEnum typeEnum = CertificateTypeEnum.getByCode(type);
        CertificateBO certificateBO = new CertificateBO();
        if (request.getTeacher() != null) {
            //TODO 这边要验证一下 list<map<String,String>> 是否可以放进去
            certificateBO.setTeacher(request.getTeacher());
        }
        certificateBO.setCertificateType(CertificateTypeEnum.COMPETITION.getCode());
        certificateBO.setCompetitionName(request.getCompetitionName());
        certificateBO.setRank(request.getRank());
        certificateBO.setTeamName(request.getTeamName());
        certificateBO.setUserId(request.getUserId());
        certificateBO.setType(request.getType());
        certificateBO.setCertificateOrganization(request.getCertificateOrganization());
        certificateBO.setCertificatePublishTime(new Date(request.getCertificatePublishTime()));
        certificateBO.putExtInfo(CertificateExtInfoKey.DESCRIPTION.getCode(), request.getDescription());
        switch (typeEnum) {
            case PERSONAL_COMPETITION: {
                certificateBO = competitionRepoService.create(certificateBO);
                return certificateBO;
            }
            case TEAM_COMPETITION: {
                AssertUtil.assertNotNull(request.getWorkUserId(), "队友学号不能为空");
                //目前团队成员人数限制不能超过五个人,把自己的学号也要填进去 方便多条记录队友属性统一字段
                AssertUtil.assertTrue(request.getWorkUserId().size() <= 5, "队友人数不能超过五个人");
                CertificateBO thisCertificate=new CertificateBO();
                //TODO 返回单个BO  创建成员等多条记录(每个记录中)
                for (String userid : request.getWorkUserId()) {
                    //更改每条记录中学生id 传参引用证书id会被修改
                    certificateBO.setUserId(userid);
                    certificateBO.setCertificateId(null);
                    competitionRepoService.create(certificateBO);
                    //判断当前用户id与创建记录的学生id是否相同  返回当前对象记录
                    if ( certificateBO.getUserId().equals(request.getUserId())) {
                        thisCertificate=certificateBO;
                    }
                }
                return thisCertificate;
            }
            default: {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS, "竞赛证书归属类别不存在");
            }
        }
    }

    @Override
    public CertificateBO modifyQualifications(CertificateManagerRequest request) {
        return null;
    }

    @Override
    public CertificateBO modifyCompetition(CertificateManagerRequest request) {
        return null;
    }
}
