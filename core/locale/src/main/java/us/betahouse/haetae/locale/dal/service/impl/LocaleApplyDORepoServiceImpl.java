package us.betahouse.haetae.locale.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;

@Service
public class LocaleApplyDORepoServiceImpl implements LocaleApplyDORepoService {
    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory localBizFactory;
}
