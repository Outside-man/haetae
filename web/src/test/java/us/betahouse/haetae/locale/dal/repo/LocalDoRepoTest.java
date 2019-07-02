package us.betahouse.haetae.locale.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.locale.idfactory.BizIdFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalDoRepoTest {
    @Autowired
    LocaleDORepo localeDORepo;
    @Autowired
    BizIdFactory localBizFactoryp;

    @Test
    public void test() {
        System.out.println(localeDORepo.findAll());
    }
}
