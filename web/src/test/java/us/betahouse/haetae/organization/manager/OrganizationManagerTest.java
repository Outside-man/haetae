package us.betahouse.haetae.organization.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationManagerTest {

    @Autowired
    private OrganizationManager organizationManager;

    @Test
    public void createOrganization(){
        OrganizationManageRequest request = new OrganizationManageRequest();
        request.setOrganizationName("a社团");
        request.setMemberDesc("社长");
        request.setPrinipalId("11111");
        organizationManager.createOrganization(request);
    }


}