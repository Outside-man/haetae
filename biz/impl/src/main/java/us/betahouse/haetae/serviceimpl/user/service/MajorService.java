/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.user.model.basic.MajorBO;
import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : MajorService.java 2019/04/21 16:37 MessiahJK
 */
public interface MajorService {

    List<MajorBO> getAllMajor();

    void t1();
    /**
     * 获取所有的专业和年级
     * @return
     */
    List<UserInfoBO> queryAllMajorAndGrade();

}
