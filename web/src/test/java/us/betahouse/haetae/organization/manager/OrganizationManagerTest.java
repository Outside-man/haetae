package us.betahouse.haetae.organization.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.CsvUtil;

import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationManagerTest {

    @Autowired
    private OrganizationManager organizationManager;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private OrganizationService organizationService;

    @Test
    public void createOrganization(){
        OrganizationManageRequest request = new OrganizationManageRequest();
        request.setOrganizationName("c社团");
        request.setMemberDesc("社长");
        request.setMemberId("11111");
        organizationManager.createOrganization(request);
    }
    @Test
    public void createOrganizations(){

        String filepath = "C:\\Users\\j10k\\Desktop\\1.csv";
        String[][] csv = CsvUtil.getWithoutHeader(filepath);
        for (String[] aCsv : csv) {
            OrganizationManageRequest request = new OrganizationManageRequest();
            request.setMemberId(userInfoRepoService.queryUserInfoByStuId("system").getUserId());
            request.setOrganizationName(aCsv[0]);
            organizationManager.createOrganization(request);
        }
    }

    @Test
    public void manageMember(){
        OrganizationManageRequest request = new OrganizationManageRequest();
        request.setOrganizationId("201811302159427109255010032018");
        request.setMemberType(MemberType.MEMBER);
        request.setMemberDesc("社员");
        request.setMemberId("201811302142079964900001201864");
        organizationManager.manageMember(request);
    }

    @Test
    public void queryOrganizationByMemberId() {
        organizationManager.queryOrganizationByMemberId("201811302142079964900001201864").forEach(System.out::println);
    }

    @Test
    public void queryOrganizationMemberByMemberId() {
        organizationManager.queryOrganizationMemberByMemberId("201811302142079964900001201864").forEach(System.out::println);

    }

    @Test
    public void t1(){
        OrganizationRequest organizationRequest=new OrganizationRequest();
        organizationRequest.setMemberId("201811302142079964900001201864");
        System.out.println("_________________");
        List list= CollectionUtils.toStream(organizationService.queryOrganizationMemberByMemberId(organizationRequest)).map(OrganizationMemberBO::findJob).distinct().collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}