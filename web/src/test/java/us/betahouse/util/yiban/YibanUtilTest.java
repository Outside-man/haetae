package us.betahouse.util.yiban;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YibanUtilTest {

    @Autowired
    private YibanUtil yibanUtil;

    @Test
    public void getAccessToken() {
        YiModel yiModel=yibanUtil.getAccessToken("ce59b884d08ce4caa8999448581f4133e59fb4c6");
        System.out.println(yibanUtil.getStuId(yiModel));
    }
}