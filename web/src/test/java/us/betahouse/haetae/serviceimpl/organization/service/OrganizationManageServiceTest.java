package us.betahouse.haetae.serviceimpl.organization.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationManageServiceTest {

    @Autowired
    private OrganizationManageService organizationManageService;

    @Test
    public void create(){
        OrganizationRequest request = new OrganizationRequest();
        request.setStuId("15905631");
        request.setOrganizationName("beta");
        organizationManageService.create(request);
    }

}