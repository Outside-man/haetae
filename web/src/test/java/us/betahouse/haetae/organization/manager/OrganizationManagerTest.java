package us.betahouse.haetae.organization.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;
import us.betahouse.util.utils.CsvUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationManagerTest {

    @Autowired
    private OrganizationManager organizationManager;

    @Test
    public void createOrganization(){
        OrganizationManageRequest request = new OrganizationManageRequest();
        request.setOrganizationName("c社团");
        request.setMemberDesc("社长");
        request.setMemberId("11111");
        organizationManager.createOrganization(request);
    }


    @Test
    public void manageMember(){
        OrganizationManageRequest request = new OrganizationManageRequest();
        request.setOrganizationId("201903260031563848110011201940");
        request.setMemberType(MemberType.PRINCIPAL);
        request.setMemberDesc("部长");
        request.setMemberId("11111");
        organizationManager.manageMember(request);
    }

}