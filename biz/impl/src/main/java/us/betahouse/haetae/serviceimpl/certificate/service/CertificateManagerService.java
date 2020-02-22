/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.service;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;
import us.betahouse.haetae.certificate.request.CertificateManagerRequest;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateConfirmRequest;
import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.List;

/**
 * 证书管理器
 * 管理鉴权
 * @author guofan.cp
 * @version : CertificateManagerService.java 2019/04/06 8:26 guofan.cp
 */
public interface CertificateManagerService {


    /**
     * 删除证书记录
     *
     * @param request
     * @param context
     * @return
     */
    void delete(CertificateRequest request, OperateContext context);


    /**
     * 通过证书id获取详细信息
     *
     * @param request
     * @param context
     * @return
     */
    CertificateBO findByCertificateId(CertificateRequest request,OperateContext context);
    /**
     * 绑定审核员
     *
     * @param request
     * @param context
     * @return
     */
    void bindConfirmUser(CertificateConfirmRequest request,OperateContext context);
    /**
     * 取绑审核员
     *
     * @param request
     * @param context
     * @return
     */
    void delteConfirmUser(CertificateConfirmRequest request,OperateContext context);
    /**
     * 获取所有审核员信息
     *
     * @param
     * @param
     * @return
     */
    List<UserInfoBO> getConfirmUser(CertificateConfirmRequest request,OperateContext context);
    /**
     * 导入证书
     *
     * @param url
     * @return  未导入证书的学生id
     */
    List<String> importCertificate(String url);

    /**
     * 获取全部证书
     *
     * @param request
     * @param context
     * @return
     */
    List<CertificateBO> fetchAllCertificateList(CertificateManagerRequest request, OperateContext context);

    /**
     * 获取用户全部证书
     *
     * @param request
     * @param context
     * @return
     */
    List<CertificateBO> fetchAllCertificate(CertificateConfirmRequest request,OperateContext context);
    /**
     * 获取用户未过审证书
     *
     * @param request
     * @param context
     * @return
     */
    List<CertificateBO> fetchUnreviedCertificate(CertificateConfirmRequest request,OperateContext context);

    /**
     * 证书审核通过
     *
     * @param request
     * @param context
     * @return
     */
    CertificateBO confirmCertificate(CertificateConfirmRequest request,OperateContext context);
    //批量创建证书

}
