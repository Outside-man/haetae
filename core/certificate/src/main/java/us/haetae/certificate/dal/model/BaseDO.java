/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.haetae.certificate.dal.model;

import org.springframework.data.annotation.CreatedDate;
import us.betahouse.util.common.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author guofan.cp
 * @version : BaseDO.java 2019/03/30 9:47 guofan.cp
 */
@MappedSuperclass
public abstract class BaseDO extends ToString {

    private static final long serialVersionUID = 4458888499111634795L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create",nullable = false)
    private Date gmtCreate;


}
