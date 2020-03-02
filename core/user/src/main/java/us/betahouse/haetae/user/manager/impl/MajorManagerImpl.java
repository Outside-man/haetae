/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.service.MajorRepoService;
import us.betahouse.haetae.user.manager.MajorManager;
import us.betahouse.haetae.user.model.basic.MajorBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : MajorManagerImpl.java 2019/04/18 16:13 MessiahJK
 */
@Service
public class MajorManagerImpl implements MajorManager {
    @Autowired
    MajorRepoService majorRepoService;
    @Override
    public MajorBO getMajor(String majorId) {
        return majorRepoService.findMajor(majorId);
    }

    @Override
    public List<MajorBO> getAllMajor() {
        return majorRepoService.findAll();
    }
}
