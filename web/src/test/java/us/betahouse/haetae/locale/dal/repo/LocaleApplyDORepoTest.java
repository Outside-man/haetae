package us.betahouse.haetae.locale.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.serviceimpl.locale.service.impl.LocaleApplyServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocaleApplyDORepoTest {
    @Autowired
    LocaleApplyDORepo localeApplyDORepo;
    @Autowired
    BizIdFactory localBizFactory;
    @Autowired
    LocaleApplyDORepoService localeApplyDORepoService;
    @Autowired
    LocaleApplyServiceImpl localeApplyService;


    @Test
    public void test() {
        List<LocaleApplyBO> systemFinishLocaleApplies = new ArrayList<>();
        systemFinishLocaleApplies = localeApplyService.systemFinishLocaleApply();
        for (LocaleApplyBO localeApplyBO : systemFinishLocaleApplies) {
            System.out.println(localeApplyBO);
        }
    }
}
