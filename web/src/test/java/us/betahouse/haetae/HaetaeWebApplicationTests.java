package us.betahouse.haetae;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HaetaeWebApplicationTests {

    @Autowired
    private UserDORepo userDORepo;

    @Test
    public void test() {
        UserDO userDO = new UserDO();
        userDORepo.save(userDO);
    }

}
