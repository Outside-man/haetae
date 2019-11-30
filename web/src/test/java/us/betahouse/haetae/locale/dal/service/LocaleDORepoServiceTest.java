package us.betahouse.haetae.locale.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.locale.model.basic.LocaleBO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocaleDORepoServiceTest {

    @Autowired
    LocaleDORepoService localeDORepoService;
    @Test
    public void createLocale() {
        LocaleBO localeBO=new LocaleBO();
        localeBO.setLocaleCode("gz527");
        localeBO.setLocaleName("格致楼527");
        localeBO.setStatus("USABLE");
        localeDORepoService.createLocale(localeBO);
    }
}