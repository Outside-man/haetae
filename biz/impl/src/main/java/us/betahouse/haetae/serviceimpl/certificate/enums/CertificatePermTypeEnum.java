/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.certificate.constant.CertificatePermType;
import us.betahouse.haetae.user.enums.PermType;

/**
 * 证书权限类型
 *
 * @author guofan.cp
 * @version : CertificatePermTypeEnum.java 2019/04/26 17:55 guofan.cp
 */
public enum CertificatePermTypeEnum implements PermType {
    //=================总管理权限 start=================
    /**
     * 分配审核员
     */
    ASSIGN_CONFIRM(CertificatePermType.ASSIGN_CONFIRM, "分配审核员", true),
    /**
     * 删除审核员
     */
    DELETE_CONFIRM(CertificatePermType.DELETE_CONFIRM, "删除扫描员", true),
    /**
     * 批量导入证书 （直接通过）
     */
    IMPORT_CERTIFICATE(CertificatePermType.IMPORT_CERTIFICATE, "批量导入证书", true),
    /**
     * 修改证书信息
     */
    MODIFY_CERTIFICATE(CertificatePermType.MODIFY_CERTIFICATE, "修改证书信息", true),
    /**
     * 删除有误证书
     */
    DELETE_CERTIFICATE(CertificatePermType.DELETE_CERTIFICATE, "删除有误证书", true),
    //=================总管理权限 end===================
    /**
     * 审核证书
     */
    CONFIRM_CERTIFICATE(CertificatePermType.CONFIRM_CERTIFICATE,"审核证书",true),
    /**
     * 获取未通过证书
     */
    GET_CERTIFICATE(CertificatePermType.GET_CERTIFICATES,"获取未审核证书",true);
    private String code;

    private String desc;

    private boolean init;

    public static CertificatePermTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (CertificatePermTypeEnum permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    CertificatePermTypeEnum(String code, String desc, boolean init) {
        this.code = code;
        this.desc = desc;
        this.init = init;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    @Override
    public boolean isInit() {
        return false;
    }
}
