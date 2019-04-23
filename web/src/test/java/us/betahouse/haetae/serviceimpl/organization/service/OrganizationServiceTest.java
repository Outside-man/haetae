package us.betahouse.haetae.serviceimpl.organization.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.util.utils.CsvUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void create(){
        OrganizationRequest request = new OrganizationRequest();
        request.setStuId("15905631");
        request.setOrganizationName("beta");
        organizationService.create(request);
    }
    @Test
    public void createOrganizations(){
        OrganizationRequest request = new OrganizationRequest();
        String filepath = "C:\\Users\\j10k\\Desktop\\activity_organization.csv";
        String[][] csv = CsvUtil.getWithoutHeader(filepath);
        request.setStuId("system");
        for (String[] aCsv : csv) {
            request.setOrganizationName(aCsv[1]);
            request.setOrganizationId(aCsv[0]);
            organizationService.create(request);
        }
    }
    @Test
    public void memberManage(){
        OrganizationRequest request = new OrganizationRequest();
        request.setOrganizationId("201903271603313910560011201916");
        request.setStuId("15905626");
        request.setMemberType(MemberType.PRINCIPAL);
        organizationService.memberManage(request);
    }

    @Test
    public void disband(){
        OrganizationRequest request = new OrganizationRequest();
        request.setOrganizationId("201903271603313910560011201916");
        organizationService.disband(request);
    }

}