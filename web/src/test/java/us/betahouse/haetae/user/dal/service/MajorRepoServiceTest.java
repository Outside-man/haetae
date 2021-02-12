package us.betahouse.haetae.user.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.user.model.basic.MajorBO;
import us.betahouse.util.utils.CsvUtil;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MajorRepoServiceTest {

    @Autowired
    MajorRepoService majorRepoService;


    //    @Transactional
    @Test
    public void create() {
        String csv[][] = CsvUtil.getWithHeader("C:\\Users\\j10k\\Desktop\\专业列表.csv");
        MajorBO majorBO = new MajorBO();
        for (int i = 1; i < csv.length; i++) {
            majorBO.setMajorName(csv[i][0]);
            majorRepoService.create(majorBO);
        }
        List<MajorBO> majorBOList = majorRepoService.findAll();
        majorBOList.forEach(System.out::println);
    }

}