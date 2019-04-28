/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.constant;

/**
 * @author guofan.cp
 * @version : CertificatePermType.java 2019/04/26 17:30 guofan.cp
 */
public class CertificatePermType {
    //=================总管理权限 start=================
    /**
     * 分配审核员
     */
    public final static String ASSIGN_CONFIRM = "ASSIGN_CONFIRM";
    /**
     * 删除审核员
     */
    public final static String DELETE_CONFIRM = "DELETE_CONFIRM";
    /**
     * 批量导入证书 （直接通过）
     */
    public final static String IMPORT_CERTIFICATE = "IMPORT_CERTIFICATE";
    /**
     * 修改证书信息
     */
    public final static String MODIFY_CERTIFICATE = "MODIFY_CERTIFICATE";
    /**
     * 删除有误证书
     */
    public final static String DELETE_CERTIFICATE = "DELETE_CERTIFICATE";

    //=================总管理权限 end===================

    //=================审核员权限 start==================
    /**
     * 证书审核
     */
    public final  static  String CONFIRM_CERTIFICATE="CONFIRM_CERTIFICATE";
    /**
     * 用户未审核证书获取
     */
    public final  static String GET_CERTIFICATES="GET_CERTIFICATE";
    //=================审核员权限 end===================
}
