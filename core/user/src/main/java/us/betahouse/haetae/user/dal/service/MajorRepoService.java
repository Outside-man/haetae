/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.basic.MajorBO;

import java.util.List;

/**
 * 专业仓储服务
 *
 * @author dango.yxm
 * @version : MajorRepoService.java 2018/11/30 8:47 PM dango.yxm
 */

public interface MajorRepoService {

    /**
     * 创建专业实体
     *
     * @param majorBO
     */
    void create(MajorBO majorBO);

    /**
     * 查询所有专业
     *
     * @return
     */
    List<MajorBO> findAll();

    /**
     * 查询专业
     *
     * @param majorId
     * @return
     */
    MajorBO findMajor(String majorId);
}
