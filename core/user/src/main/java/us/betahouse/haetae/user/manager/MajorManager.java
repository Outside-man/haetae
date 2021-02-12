/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.model.basic.MajorBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : MajorManager.java 2019/04/18 16:10 MessiahJK
 */
public interface MajorManager {

    /**
     * 获取专业
     *
     * @param majorId
     * @return
     */
    MajorBO getMajor(String majorId);

    /**
     *
     * @return
     */
    List<MajorBO> getAllMajor();
}
