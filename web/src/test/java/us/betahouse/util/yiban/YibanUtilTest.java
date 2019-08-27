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
        yibanUtil.getAccessToken("cf5d95407670c9b969f2ff3c4970cf1ba411feaf");
    }
}