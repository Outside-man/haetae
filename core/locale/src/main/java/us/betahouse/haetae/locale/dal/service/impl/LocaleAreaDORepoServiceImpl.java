package us.betahouse.haetae.locale.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.service.LocaleAreaDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;

@Service
public class LocaleAreaDORepoServiceImpl implements LocaleAreaDORepoService {
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;
}
