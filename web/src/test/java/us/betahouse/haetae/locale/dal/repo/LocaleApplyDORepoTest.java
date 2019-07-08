package us.betahouse.haetae.locale.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.locale.dal.model.LocaleApplyDO;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocaleApplyDORepoTest {
    @Autowired
    LocaleApplyDORepo localeApplyDORepo;
    @Autowired
    BizIdFactory localBizFactory;
    @Autowired
    LocaleApplyDORepoService localeApplyDORepoService;

    @Test
    public void test() {
        int page = 0;
        int limit = 10;
        Pageable pageable = PageRequest.of(page, limit);
        Page<LocaleApplyDO> localeApplyDOPage = localeApplyDORepo.findAllByStatusContainsOrderByLocaleApplyIdDesc(pageable, "CANCEL");
        localeApplyDOPage.forEach(System.out::println);
        System.out.println(localeApplyDOPage.getTotalElements());
        System.out.println(localeApplyDOPage.getTotalPages());
        System.out.println(localeApplyDOPage.getNumber());
        System.out.println(localeApplyDOPage.getNumberOfElements());
        System.out.println(localeApplyDOPage.getSize());
        System.out.println(localeApplyDOPage.isFirst());
        System.out.println(localeApplyDOPage.isEmpty());
        System.out.println(localeApplyDOPage.isLast());
        System.out.println("-------------------------------------");

    }
}
