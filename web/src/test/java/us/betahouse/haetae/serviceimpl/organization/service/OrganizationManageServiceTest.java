package us.betahouse.haetae.serviceimpl.organization.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.enums.MemberType;
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

    @Test
    public void memberManage(){
        OrganizationRequest request = new OrganizationRequest();
        request.setOrganizationId("201903271603313910560011201916");
        request.setStuId("15905626");
        request.setMemberType(MemberType.PRINCIPAL);
        organizationManageService.memberManage(request);
    }

    @Test
    public void disband(){
        OrganizationRequest request = new OrganizationRequest();
        request.setOrganizationId("201903271603313910560011201916");
        organizationManageService.disband(request);
    }

}