package us.betahouse.haetae.serviceimpl.organization.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.dal.model.OrganizationDO;
import us.betahouse.haetae.organization.dal.repo.OrganizationRepo;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.util.utils.CsvUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationServiceTest {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationRepo organizationRepo;

    @Test
    public void create(){
        OrganizationRequest request = new OrganizationRequest();
        request.setStuId("15905631");
        request.setOrganizationName("beta");
        organizationService.create(request);
    }
    @Test
    public void createOrganizations(){
//        String filepath = "C:\\Users\\j10k\\Desktop\\organization.csv";
//        String[][] csv = CsvUtil.getWithoutHeader(filepath);
//        for (String[] aCsv : csv) {
            OrganizationRequest request = new OrganizationRequest();
            request.setOrganizationName("我相信");
            request.setStuId("system");
//            request.setOrganizationId(aCsv[4]);
            organizationService.create(request);
//        }
    }
    @Test
    public void memberManage(){
        OrganizationRequest request = new OrganizationRequest();
        OrganizationDO byOrganizationName = organizationRepo.findByOrganizationName("");
        request.setOrganizationId("201903271603313910560011201916");
        request.setStuId("15905626");
        request.setMemberType(MemberType.PRINCIPAL);
        organizationService.memberManage(request);
    }
    @Test
    public void membersManage(){
        String url = "C:\\Users\\j10k\\Desktop\\common_user_info.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            OrganizationDO byOrganizationName = organizationRepo.findByOrganizationName("我相信");
            if(byOrganizationName==null) System.out.println(i);
            OrganizationRequest request = new OrganizationRequest();
            request.setOrganizationId(byOrganizationName.getOrganizationId());
            request.setStuId(csv[i][9]);
            System.out.println(i+" "+csv[i][9]);
            request.setMemberType(MemberType.MEMBER);
            request.setMemberDesc(",技术可以改变世界");
            organizationService.memberManage(request);
        }
    }

    @Test
    public void disband(){
        OrganizationRequest request = new OrganizationRequest();
        OrganizationDO byOrganizationName = organizationRepo.findByOrganizationName("我相信");
        request.setOrganizationId(byOrganizationName.getOrganizationId());
        organizationService.disband(request);
    }
    @Test
    public void t1(){
        String url = "C:\\Users\\j10k\\Desktop\\第二课堂社长信息1.0.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            OrganizationDO byOrganizationName = organizationRepo.findByOrganizationName(csv[i][2]);
            if(byOrganizationName==null) System.out.println(i);
        }
    }

}