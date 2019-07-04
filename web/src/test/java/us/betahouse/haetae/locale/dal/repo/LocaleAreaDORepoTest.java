package us.betahouse.haetae.locale.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocaleAreaDORepoTest {
    @Autowired
    LocaleAreaDORepo localeAreaDORepo;

    @Test
    public void test() {
        System.out.println(localeAreaDORepo.findByLocaleIdAndTimeDateAndStatusNot("201907021741500068564001201964", "2019-07-02", "CANCEL"));
    }


}
