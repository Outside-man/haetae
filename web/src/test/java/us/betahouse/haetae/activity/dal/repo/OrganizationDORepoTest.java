package us.betahouse.haetae.activity.dal.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.dal.model.OrganizationDO;
import us.betahouse.haetae.organization.dal.repo.OrganizationRepo;
import us.betahouse.haetae.organization.idfactory.BizIdFactory;
import us.betahouse.util.utils.CsvUtil;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationDORepoTest {

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private BizIdFactory organizationBizIdFactory;

    @Test
    public void importit() {
        String filepath = "C:\\Users\\j10k\\Desktop\\11\\name.csv";
        String[][] csv = CsvUtil.getWithoutHeader(filepath);
        List<OrganizationDO> list = new ArrayList<>();
        for (int i = 0; i < csv.length; i++) {
            OrganizationDO organizationDO = new OrganizationDO();
            organizationDO.setOrganizationName(csv[i][0]);
            organizationDO.setOrganizationId(organizationBizIdFactory.getOrganizationId());
            list.add(organizationDO);
        }
        organizationRepo.saveAll(list);
    }

    @Test
    public void save() {
        OrganizationDO organizationDO = new OrganizationDO();
        organizationDO.setOrganizationName("团学办公室");
        organizationDO.setOrganizationId(organizationBizIdFactory.getOrganizationId());
        organizationRepo.save(organizationDO);
    }
}