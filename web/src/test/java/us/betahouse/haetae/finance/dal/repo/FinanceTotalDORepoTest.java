package us.betahouse.haetae.finance.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinanceTotalDORepoTest {

    @Autowired
    FinanceTotalDORepo financeTotalDORepo;

    @Test
    public void test(){
        System.out.println(financeTotalDORepo.findAll());
    }

}