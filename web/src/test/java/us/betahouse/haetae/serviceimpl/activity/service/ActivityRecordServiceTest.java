package us.betahouse.haetae.serviceimpl.activity.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRecordServiceTest {

    @Autowired
    ActivityRecordService activityRecordService;
    @Test
    public void importStamp() {
        //C:\Users\j10k\Documents\Tencent Files\1033161038\FileRecv\第一届课达杯手绘大赛-数据导入.csv
        String url="C:\\Users\\j10k\\Documents\\Tencent Files\\1033161038\\FileRecv\\第一届课达杯手绘大赛-数据导入.csv";
        List<String> ls=activityRecordService.importStamp(url);
        for(String str:ls) {
            System.out.println(str);
        }
        System.out.println();
        System.out.println(ls.size());
    }
}