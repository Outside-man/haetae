package us.betahouse.haetae.locale.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.locale.dal.service.LocaleDORepoService;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalDoRepoTest {
    @Autowired
    LocaleDORepo localeDORepo;
    @Autowired
    BizIdFactory localBizFactory;
    @Autowired
    LocaleDORepoService localeDORepoService;

    @Test
    public void test() {
//        LocaleBO localeBO = new LocaleBO();
        System.out.println(localeDORepo.findByLocaleCode("DUXING501"));
    }

    @Test
    public void t1(){

    }
}
