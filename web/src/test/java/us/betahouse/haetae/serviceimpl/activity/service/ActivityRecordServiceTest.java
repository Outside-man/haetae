package us.betahouse.haetae.serviceimpl.activity.service;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.model.PastActivityDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.dal.repo.PastActivityDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.certificate.dal.model.QualificationsDO;
import us.betahouse.haetae.certificate.dal.repo.QualificationsDORepo;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;
import us.betahouse.haetae.serviceimpl.activity.manager.StampManager;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityRecordStatistics;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.CsvUtil;
import us.betahouse.util.utils.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRecordServiceTest {

    @Autowired
    private ActivityRecordService activityRecordService;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private ActivityRecordDORepo activityRecordDORepo;
    @Autowired
    private ActivityDORepo activityDORepo;
    @Autowired
    private BizIdFactory activityBizFactory;
    @Autowired
    private StampManager stampManager;
    @Autowired
    private ActivityRecordManager activityRecordManager;
    @Autowired
    private ActivityRepoService activityRepoService;
    @Autowired
    private PastActivityDORepo pastActivityDORepo;
    @Autowired
    private QualificationsDORepo qualificationsDORepo;
    
    @Test
    public void delete() {
        Map<String, String[]> realNameAndStuIdMap = getRealNameAndStuIdMapFromExcel("C:\\Users\\86181\\Desktop\\国防教育动员大会.xlsx");
        for (String key : realNameAndStuIdMap.keySet()) {
            System.out.println(realNameAndStuIdMap.get(key)[1]);
            System.out.println(userInfoRepoService.queryUserInfoByStuId(realNameAndStuIdMap.get(key)[1]).getUserId());
            // activityRecordDORepo.deleteAllByActivityIdAndUserId("202106171240408008385310012021", );
        }
    }
    
    @Test
    public void tmpExport() {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\86181\\Desktop\\国防教育动员大会.csv", ',', StandardCharsets.UTF_8);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<ActivityRecordDO> activityRecordDos = activityRecordDORepo.findAllByActivityId("202106171240408008385310012021");
        try {
            csvWriter.writeRecord(new String[]{"被签章人", "被签章人学号", "签章人", "时间"});
            for (ActivityRecordDO activityRecordDO : activityRecordDos) {
                String[] res = new String[4];
                UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByUserId(activityRecordDO.getUserId());
                res[0] = userInfoBO.getRealName();
                res[1] = userInfoBO.getStuId();
                res[2] = userInfoRepoService.queryUserInfoByUserId(activityRecordDO.getScannerUserId()).getRealName();
                res[3] = simpleDateFormat.format(activityRecordDO.getGmtCreate());
                csvWriter.writeRecord(res);
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void superExport() {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\86181\\Desktop\\export.csv", ',', StandardCharsets.UTF_8);
        Map<String, String[]> realNameAndStuIdMap = getRealNameAndStuIdMapFromExcel("C:\\Users\\86181\\Desktop\\6.10.xlsx");
        try {
            csvWriter.writeRecord(new String[]{"学号", "姓名", "讲座次数", "校园活动次数", "社会实践次数", "资格证书个数"});
            for (String key : realNameAndStuIdMap.keySet()) {
                String[] res = new String[6];
                String stuId = realNameAndStuIdMap.get(key)[0];
                UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
                PastActivityDO pastActivityDO = pastActivityDORepo.findByUserId(userInfoBO.getUserId());
                res[0] = stuId;
                res[1] = userInfoBO.getRealName();
                res[2] = (activityRecordDORepo.countAllByUserIdAndType(userInfoBO.getUserId(), "lectureActivity") + pastActivityDO.getPastLectureActivity()) + "";
                res[3] = (activityRecordDORepo.countAllByUserIdAndType(userInfoBO.getUserId(), "schoolActivity") + pastActivityDO.getPastSchoolActivity()) + "";
                res[4] = (activityRecordDORepo.countAllByUserIdAndType(userInfoBO.getUserId(), "practiceActivity") + pastActivityDO.getPastPracticeActivity()) + "";
                res[5] = qualificationsDORepo.countAllByUserId(userInfoBO.getUserId()) + "";
                csvWriter.writeRecord(res);
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void importStamp() {
        String url = "C:\\Users\\86181\\Desktop\\7.1\\国防教育动员大会.csv";
        List<String> ls = activityRecordService.importStamp(url);
        for (String str : ls) {
            System.out.println(str);
        }
        System.out.println();
        System.out.println(ls.size());
    }
    
    /**
     * 超级校验器，指定 excel 文件目录自动遍历校验并转换为 csv 文件
     */
    @Test
    public void superChecker() {
        String folderPath = "/Users/lyl/Desktop/" + getTodayString();
        String resultPath = folderPath + "/result.txt";
        if (getFileSize(new File(folderPath)) == 0) {
            System.out.println("文件夹为空");
            return;
        }
        Map<String, String[]> realNameAndStuIdMap = getRealNameAndStuIdMapFromExcels(folderPath);
        List<String> result = new ArrayList<>();
        int index = 1;
        // 遍历 map 中的所有姓名与学号数组
        for (String key : realNameAndStuIdMap.keySet()) {
            String[] realNameAndStuId = realNameAndStuIdMap.get(key);
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(realNameAndStuId[1]);
            if (userInfoBO == null) {
                result.add(index + ". " + key + "：【" + realNameAndStuId[0] + "】的学号不存在");
                index++;
            } else if (!userInfoBO.getRealName().equals(realNameAndStuId[0])) {
                result.add(index + ". " + key + "：学号为 " + realNameAndStuId[1] + " 的用户姓名【" + realNameAndStuId[0] + "】异常，实际仅存在姓名【" + userInfoBO.getRealName() + "】");
                index++;
            }
        }
        // 若不存在异常则写入 sheet 到 csv 中并删除原 excel 文件
        if (result.size() == 0) {
            write2Csv(folderPath);
            deleteOriginExcels(folderPath);
            System.out.println("正常");
        } else {
            result.add("共存在【" + result.size() + "】条异常记录");
            writeResult(resultPath, result);
            System.out.println("错误");
        }
    }

    @Test
    public void updateScannerName(){
        List<ActivityRecordBO> activityRecordBOList=activityRecordManager.findAll();
        Map<String,String> map=new HashMap<>();
        for (ActivityRecordBO record : activityRecordBOList) {
            if (StringUtils.isBlank(record.getScannerName())) {
                String scannerName;
                if(map.containsKey(record.getScannerUserId())){
                    scannerName=map.get(record.getScannerUserId());
                }else {
                    scannerName = userInfoRepoService.queryUserInfoByUserId(record.getScannerUserId()).getRealName();
                    map.put(record.getScannerUserId(), scannerName);
                }
                activityRecordManager.updateScannerName(record.getActivityRecordId(), scannerName);
                record.setScannerName(scannerName);
            }
        }
    }
    // 义工时长
    @Test
    public void importVolunteerWork(){
        String url = "C:\\Users\\j10k\\Desktop\\2019云栖大会志愿活动录入名单(2).csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            request.setActivityId("201812032305164919790210012018");
            request.setScannerUserId("201812010040554783180001201835");
            request.setVolunteerWorkName(csv[i][2]);
            request.setTime(Double.valueOf(csv[i][3]));
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    @Test
    public void importShenQi(){
        String url = "C:\\Users\\j10k\\Desktop\\2019五四升旗仪式名单.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            request.setActivityId("201904151547343710112210012019");
            request.setScannerUserId("201812010040554783180001201835");
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    // 初心剧场和早课查勤导入
    @Test
    public void importCXJC(){
        String url = "/Users/lyl/Desktop/7.23/电影节导入考核章人员名单 - Sheet1.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityStampRequest request=new ActivityStampRequest();
            // 早课查勤 ID
//            request.setActivityId("201904151546362400821310012019");
            // 初心剧场 ID
            request.setActivityId("201904151545269174388510012019");
            request.setScannerUserId("201812010040554783180001201835");
            request.setStatus("ENABLE");
            request.setTerm(TermUtil.getNowTerm());
            List<String> stuIdList=new ArrayList<>();
            stuIdList.add(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            request.setStuIds(stuIdList);
            stampManager.batchStamp(request, stuIdList);
        }
    }
    // 考核章
    @Test
    public void importLastPartyRecord(){
        String url =  "C:\\Users\\86181\\Desktop\\考核章（2）\\第三届田径运动会开幕式活动工作人员考核章 - Sheet1.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][2]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setType(ActivityTypeEnum.PARTY_ACTIVITY.getCode());
            activityRecordDO.setTime(0);
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(csv[i][3]);
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }
    @Test
    public void importOneHour(){
        String url =  "C:\\Users\\86181\\Desktop\\5.10\\交换一小时\\第三届田径运动会开幕式活动红旗队交换一小时 - 第三届田径运动会开幕式活动红旗队交换一小时.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId("201904151539461439186510012019");
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime((int)(Double.valueOf(csv[i][2])*10));
            activityRecordDO.setType("partyTimeActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }

    // 志愿时长
    @Test
    public void importVolunteerActivity(){
        String url = "C:\\Users\\86181\\Desktop\\7.8\\【志愿时长】2020-2021学年第二学期返校志愿者 - Sheet2.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            System.out.println(csv[i][3]);
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][3]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime((int)(Double.valueOf(csv[i][2])*10));
            activityRecordDO.setType("volunteerActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            activityRecordDORepo.save(activityRecordDO);
            System.out.println(i+" "+activityRecordDO);
        }
    }

    // 社会实践
    @Test
    public void importPracticeActivity2() {
        String url = "/Users/kagantuya/Desktop/暑期社会——竞赛团队.csv";
        String[][] csv = CsvUtil.getWithHeader(url);

        for (int i = 1; i < csv.length; i++) {
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setActivityId(activityDORepo.findByActivityName(csv[i][2]).getActivityId());
            activityRecordDO.setUserId(userInfoRepoService.queryUserInfoByStuId(csv[i][1]).getUserId());
            activityRecordDO.setScannerUserId("201812010040554783180001201835");
            activityRecordDO.setTime(0);
            activityRecordDO.setType("practiceActivity");
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            switch (csv[i][5]) {
                case "优秀":
                    activityRecordDO.setGrades(GradesConstant.EXCELLENT);
                    break;
                case "不合格":
                    activityRecordDO.setGrades(GradesConstant.FAIL);
                    break;
                case "合格":
                    activityRecordDO.setGrades(GradesConstant.PASS);
                    break;
                default:
                    System.out.println(i);
                    assert false;
            }
            activityRecordDORepo.save(activityRecordDO);
        }
    }
    @Test
    public void check() {
        String url = "C:\\Users\\86181\\Desktop\\a\\1.csv";
        String[][] csv = CsvUtil.getWithHeader(url);
        List<String> notStampStuIds = new ArrayList<>();
        for (int i = 1; i < csv.length; i++) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(csv[i][1]);
            if (userInfoBO == null) {
                System.out.println(i + " " + csv[i][1] + " " + null);
                notStampStuIds.add(csv[i][1]);
            } else if (!userInfoBO.getRealName().equals(csv[i][0])) {
                System.out.println(i + " " + csv[i][1] + " name " + userInfoBO.getRealName() + " " + csv[i][0]);
                notStampStuIds.add(csv[i][1]);
            }
        }
        System.out.println(notStampStuIds.size());
    }
    
    /**
     * 获取今日日期
     *
     * @return 例如：6.9
     */
    private String getTodayString() {
        String today = new SimpleDateFormat("M.dd").format(new Date());
        String[] split = today.split("\\.");
        if (split[1].charAt(0) == '0') {
            return split[0] + "." + split[1].substring(1);
        }
        return today;
    }
    
    /**
     * 遍历文件夹获取内部所有 excel 文件
     *
     * @param folderPath 文件夹路径
     * @param excelPaths excel 文件路径列表
     */
    private void getExcelPaths(String folderPath, List<String> excelPaths) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            File[] lists = folder.listFiles();
            if (null != lists && lists.length != 0) {
                for (File file : lists) {
                    String absolutePath = file.getAbsolutePath();
                    if (file.isDirectory()) {
                        getExcelPaths(absolutePath, excelPaths);
                    } else if (absolutePath.endsWith("xlsx") || absolutePath.endsWith("xls")) {
                        excelPaths.add(absolutePath);
                    }
                }
            }
        }
    }
    
    /**
     * 获取单个 excel 文件中的所有 sheet 中的姓名和学号 map
     *
     * @param excelPath excel 文件路径
     * @return 所有 sheet 中的姓名和学号 map
     */
    private Map<String, String[]> getRealNameAndStuIdMapFromExcel(String excelPath) {
        DataFormatter dataForMatter = new DataFormatter();
        // 以【文件名 - sheet 名 - sheet 中序号】为键，单个 sheet 中的姓名与学号数组为值（sheet 不可重名，因此此处不必担心键重名）
        Map<String, String[]> realNameAndStuIdMap = new HashMap<>();
        File excel = new File(excelPath);
        Workbook workbook = null;
        try {
            if (excelPath.endsWith("xls")) {
                workbook = new HSSFWorkbook(new FileInputStream(excel));
            } else {
                workbook = new XSSFWorkbook(new FileInputStream(excel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (workbook == null) {
            return realNameAndStuIdMap;
        }
        // 遍历 sheet
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            int rows = sheet.getPhysicalNumberOfRows();
            // 遍历行，略过表头
            for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                // 略过空行
                if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().length() == 0) {
                    continue;
                }
                String[] realNameAndStuId = new String[2];
                realNameAndStuId[0] = row.getCell(0).getStringCellValue();
                realNameAndStuId[1] = dataForMatter.formatCellValue(row.getCell(1));
                realNameAndStuIdMap.put(excel.getName() + " - " + sheet.getSheetName() + " - 序号【" + (rowIndex + 1) + "】", realNameAndStuId);
            }
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realNameAndStuIdMap;
    }
    
    /**
     * 获取文件夹中所有 excel 文件所有 sheet 中的姓名与学号 map
     *
     * @param folderPath 文件夹路径
     * @return 文件夹中所有 excel 文件所有 sheet 中的姓名与学号 map
     */
    private Map<String, String[]> getRealNameAndStuIdMapFromExcels(String folderPath) {
        Map<String, String[]> realNameAndStuIdMap = new HashMap<>();
        List<String> excelPaths = new ArrayList<>();
        getExcelPaths(folderPath, excelPaths);
        for (String excelPath : excelPaths) {
            realNameAndStuIdMap.putAll(getRealNameAndStuIdMapFromExcel(excelPath));
        }
        return realNameAndStuIdMap;
    }
    
    /**
     * 替换文件后缀
     *
     * @param filePath 文件路径
     * @param from 原后缀
     * @param to 替换后缀
     * @return 新文件路径
     */
    private String replaceLast(String filePath, String from, String to) {
        return filePath.replaceFirst( "(?s)" + from + "(?!.*?" + from + ")", to);
    }
    
    /**
     * 文件夹中所有 excel 的所有 sheet 单独写入到 csv 中
     *
     * @param folderPath 文件夹路径
     */
    private void write2Csv(String folderPath) {
        DataFormatter dataForMatter = new DataFormatter();
        CsvWriter csvWriter;
        List<String> excelPaths = new ArrayList<>();
        getExcelPaths(folderPath, excelPaths);
        try {
            for (String excelPath : excelPaths) {
                File excel = new File(excelPath);
                Workbook workbook = null;
                try {
                    if (excelPath.endsWith("xls")) {
                        workbook = new HSSFWorkbook(new FileInputStream(excel));
                    } else {
                        workbook = new XSSFWorkbook(new FileInputStream(excel));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (workbook == null) {
                    System.out.println("文件 " + excelPath + " 打开失败");
                    return;
                }
                if (excelPath.endsWith("xls")) {
                    excelPath = replaceLast(excelPath, ".xls", "");
                } else {
                    excelPath = replaceLast(excelPath, ".xlsx", "");
                }
                // 遍历 sheet
                for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                    Sheet sheet = workbook.getSheetAt(sheetIndex);
                    // 在文件当前目录写入 csv 文件
                    csvWriter = new CsvWriter(excelPath + " - " + sheet.getSheetName() + ".csv", ',', StandardCharsets.UTF_8);
                    int rows = sheet.getPhysicalNumberOfRows();
                    // 略过空 sheet
                    if (rows == 0) {
                        continue;
                    }
                    int cols = sheet.getRow(0).getPhysicalNumberOfCells();
                    // 遍历行，略过表头
                    for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
                        Row row = sheet.getRow(rowIndex);
                        // 略过空行
                        if (row == null || row.getCell(0) == null || row.getCell(0).getStringCellValue().length() == 0) {
                            continue;
                        }
                        String[] record = new String[cols];
                        for (int colIndex = 0; colIndex < cols; colIndex++) {
                            record[colIndex] = dataForMatter.formatCellValue(row.getCell(colIndex));
                        }
                        csvWriter.writeRecord(record);
                    }
                    csvWriter.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 写入结果到文件
     *
     * @param path 路径
     * @param list 结果列表
     */
    private void writeResult(String path, List<String> list) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(path);
            for (String s : list) {
                fileWriter.write(s + "\r\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除文件夹中所有 excel 文件
     *
     * @param folderPath 文件夹路径
     */
    private void deleteOriginExcels(String folderPath) {
        List<String> excelPaths = new ArrayList<>();
        getExcelPaths(folderPath, excelPaths);
        for (String excelPath : excelPaths) {
            File excel = new File(excelPath);
            excel.delete();
        }
    }
    
    /**
     * 获取文件夹内文件个数
     *
     * @param dir 目录文件
     * @return 文件夹内文件个数
     */
    private static long getFileSize(File dir) {
        long size = 0;
        File[] fileList = dir.listFiles();
        for (File file : fileList) {
            if (file.isDirectory()) {
                size = size + getFileSize(file);
            } else {
                size = size + file.length();
            }
        }
        return size;
    }
    
    @Test
    public void fetchUserRecordStatistics() {
        ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics("201811302142241446120001201817");
        System.out.println(activityRecordStatistics);
    }

    @Test
    public void fetchUserRecordStatistics1() throws IOException {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出12.csv", ',', Charset.forName("GBK"));
        String[] headers = {"学号", "姓名", "校园活动次数", "讲座活动次数", "社会实践次数", "志愿活动次数", "志愿活动时长", "义工活动次数", "义工活动时长","尚未分配活动章","年级","专业","班级"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        for (UserInfoBO userInfoBO : userInfoBOList) {
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[13];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            content[3] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[4] =String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[5] = map.get(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode()).toString();
            content[6] = String.format("%.1f", (double) (map.get("volunteerActivityTime") + pastActivityBO.getPastVolunteerActivityTime()) / 10.0);
            content[7] = map.get(ActivityTypeEnum.VOLUNTEER_WORK.getCode()).toString();
            content[8] = String.format("%.1f", Double.valueOf(map.get("volunteerWorkTime")) / 10.0);
            content[9] = pastActivityBO.getUndistributedStamp().toString();
            content[10]=userInfoBO.getGrade();
            content[11]=userInfoBO.getMajor();
            content[12]=userInfoBO.getClassId();
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    @Test
    public void fetchUserRecordStatistics2() throws IOException {
        String temp1="=IF(J{0}>=1,G{0}+4,G{0})";
        String temp2="=I{0}+J{0}*4";
        String temp3="=F{0}+I{0}+J{0}*4+K{0}";
        String temp4="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((H{0}>7),(F{0}+K{0})<8),AND((F{0}<8),(H{0}<8),(F{0}+K{0})<8,(H{0}+K{0})>7),AND((H{0}+K{0})<8,(F{0}+K{0})<8)),8-F{0}-K{0},0))";
        String temp5="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((F{0}>7),(H{0}+K{0})<8),AND((H{0}<8),(F{0}<8),(F{0}+K{0})<8,(F{0}+K{0})>7),AND((F{0}+K{0})<8,(H{0}+K{0})<8)),8-H{0}-K{0},0))";
        String temp6="=IF((M{0}+N{0})<(16-L{0}),(16-L{0}),(M{0}+N{0}))";
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\j10k\\Desktop\\导出"+DateUtil.getYearMonthDay(new Date())+".csv", ',', Charset.forName("GBK"));
        String[] headers ={"学号", "姓名","专业","年级","班级","讲座(实际)","讲座活动次数","校园活动(实际)","校园活动次数","社会实践次数","尚未分配活动章","总章数","最少讲座章","最少活动章","最少总章数"};


        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        int i=1;
        for (UserInfoBO userInfoBO : userInfoBOList) {
            i++;
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[15];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = userInfoBO.getMajor();
            content[3] = userInfoBO.getGrade();
            content[4] = userInfoBO.getClassId();
            content[5] = MessageFormat.format(temp1, String.valueOf(i));
            content[6] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[7] = MessageFormat.format(temp2, String.valueOf(i));
            content[8] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            content[9] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[10] = pastActivityBO.getUndistributedStamp().toString();
            content[11] = MessageFormat.format(temp3, String.valueOf(i));
            content[12] = MessageFormat.format(temp4, String.valueOf(i));
            content[13] = MessageFormat.format(temp5, String.valueOf(i));
            content[14] = MessageFormat.format(temp6, String.valueOf(i));
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }
    @Test
    public void fetchUserRecordStatistics3() throws IOException {
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\86181\\Desktop\\导出12.csv", ',', Charset.forName("GBK"));
        String[] headers = {"学号", "姓名", "校园活动次数", "讲座活动次数", "社会实践次数", "志愿活动次数", "志愿活动时长", "义工活动次数", "义工活动时长","年级","专业","班级"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        for (UserInfoBO userInfoBO : userInfoBOList) {
            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId(),"2018B");
            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            String[] content = new String[12];
            content[0] = activityRecordStatistics.getStuId();
            content[1] = activityRecordStatistics.getRealName();
            content[2] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))));
            content[3] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode())));
            content[4] =String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode())));
            content[5] = map.get(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode()).toString();
            content[6] = String.format("%.1f", (double) (map.get("volunteerActivityTime")) / 10.0);
            content[7] = map.get(ActivityTypeEnum.VOLUNTEER_WORK.getCode()).toString();
            content[8] = String.format("%.1f", Double.valueOf(map.get("volunteerWorkTime")) / 10.0);
            content[9]=userInfoBO.getGrade();
            content[10]=userInfoBO.getMajor();
            content[11]=userInfoBO.getClassId();
            System.out.println(activityRecordStatistics);
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    @Test
    public void t11()throws IOException{
        Map<Object,Object[]> t1=new HashMap<>();//LECTURE_ACTIVITY
        Map<Object,Object[]> t2=new HashMap<>();//SCHOOL_ACTIVITY
        Map<Object,Object[]> t3=new HashMap<>();//PRACTICE_ACTIVITY
        Map<Object,Object[]> t5=new HashMap<>();
            for (Object[] objects : activityRecordDORepo.findGroupByActivityTypeAndUserId()) {
                if(objects[3].equals(ActivityTypeEnum.LECTURE_ACTIVITY.getCode())){
                    t1.put(objects[2],objects );
                }
                if(objects[3].equals(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode())){
                    t2.put(objects[2],objects );
                }
                if(objects[3].equals(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode())){
                    t3.put(objects[2],objects );
                }
                if(objects[3].equals(ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode())){
                    t5.put(objects[2],objects );
                }
//                System.out.println(JSON.toJSONString(objects));
            }
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\86181\\Desktop\\"+DateUtil.getYearMonthDay(new Date())+".csv", ',', Charset.forName("GBK"));
        String[] headers ={"学号", "姓名","专业","年级","班级","讲座(实际)","讲座活动次数","校园活动(实际)","校园活动次数","社会实践次数","尚未分配活动章","总章数","最少讲座章","最少活动章","最少总章数","志愿时长"};
        csvWriter.writeRecord(headers);
        List<UserInfoBO> userInfoBOList = userInfoRepoService.queryAllUser();
        List<PastActivityDO> pastActivityDOList=pastActivityDORepo.findAll();
        Map<Object,PastActivityDO> t4=new HashMap<>();
        for (PastActivityDO pastActivityDO : pastActivityDOList) {
            t4.put(pastActivityDO.getUserId(), pastActivityDO);
        }
        String temp1="=IF(J{0}>=1,G{0}+4,G{0})";
        String temp2="=I{0}+J{0}*4";
        String temp3="=F{0}+I{0}+J{0}*4+K{0}";
        String temp4="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((H{0}>7),(F{0}+K{0})<8),AND((F{0}<8),(H{0}<8),(F{0}+K{0})<8,(H{0}+K{0})>7),AND((H{0}+K{0})<8,(F{0}+K{0})<8)),8-F{0}-K{0},0))";
        String temp5="=IF(IF(OR(AND((F{0}>7),(H{0}>7)),(K{0}>16),AND((F{0}>7),(H{0}+K{0})>7),AND((H{0}>7),((F{0}+K{0})>7)),AND((F{0}<8),(H{0}<8),(F{0}+H{0}+K{0})>15)),0,1)=0,0,IF(OR(AND((F{0}>7),(H{0}+K{0})<8),AND((H{0}<8),(F{0}<8),(F{0}+K{0})<8,(F{0}+K{0})>7),AND((F{0}+K{0})<8,(H{0}+K{0})<8)),8-H{0}-K{0},0))";
        String temp6="=IF((M{0}+N{0})<(16-L{0}),(16-L{0}),(M{0}+N{0}))";




        int i=1;
        for (UserInfoBO userInfoBO : userInfoBOList) {
            i++;
//            ActivityRecordStatistics activityRecordStatistics = activityRecordService.fetchUserRecordStatistics(userInfoBO.getUserId());
//            PastActivityBO pastActivityBO=activityRepoService.getPastByUserId(userInfoBO.getUserId());
//            Map<String, Integer> map = activityRecordStatistics.getStatistics();
            PastActivityDO pastActivityDO=t4.get(userInfoBO.getUserId());
            String[] content = new String[16];
            content[0] = userInfoBO.getStuId();
            content[1] = userInfoBO.getRealName();
            content[2] = userInfoBO.getMajor();
            content[3] = userInfoBO.getGrade();
            content[4] = userInfoBO.getClassId();
            content[5] = MessageFormat.format(temp1, String.valueOf(i));
            content[6] = String.valueOf(getLongValue(t1, userInfoBO.getUserId(), 0)+pastActivityDO.getPastLectureActivity());
            //content[6] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.LECTURE_ACTIVITY.getCode()))+pastActivityBO.getPastLectureActivity());
            content[7] = MessageFormat.format(temp2, String.valueOf(i));
            content[8] = String.valueOf((getLongValue(t2, userInfoBO.getUserId(), 0)+pastActivityDO.getPastSchoolActivity()));
            content[9] = String.valueOf(getLongValue(t3, userInfoBO.getUserId(), 0)+pastActivityDO.getPastPracticeActivity());
            //content[8] = String.valueOf((Long.valueOf(map.get(ActivityTypeEnum.SCHOOL_ACTIVITY.getCode()))+pastActivityBO.getPastSchoolActivity()));
            //content[9] = String.valueOf(Long.valueOf( map.get(ActivityTypeEnum.PRACTICE_ACTIVITY.getCode()))+pastActivityBO.getPastPracticeActivity());
            content[10] = pastActivityDO.getUndistributedStamp().toString();
            content[11] = MessageFormat.format(temp3, String.valueOf(i));
            content[12] = MessageFormat.format(temp4, String.valueOf(i));
            content[13] = MessageFormat.format(temp5, String.valueOf(i));
            content[14] = MessageFormat.format(temp6, String.valueOf(i));
            content[15] = String.valueOf(getLongValue(t5, userInfoBO.getUserId(), 1)+pastActivityDO.getPastVolunteerActivityTime());
            //            System.out.println(activityRecordStatistics);
//            System.out.println(JSON.toJSONString(content));
//            System.out.println(userInfoBO.getUserId());
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }
    
    @Test
    public void exportStraightUp()throws IOException{
        CsvWriter csvWriter = new CsvWriter("C:\\Users\\86181\\Desktop\\"+DateUtil.getYearMonthDay(new Date())+".csv", ',', Charset.forName("GBK"));
        String[] headers ={"学号", "姓名","性别","讲座活动","社会实践","校园活动"};
        csvWriter.writeRecord(headers);
        String[] stuIds = {"189090228"};
        for (String stuId : stuIds) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
            
            List<ActivityRecordDO> activityRecordDos = activityRecordDORepo.findByUserId(userInfoBO.getUserId());
            StringBuilder l = new StringBuilder();
            StringBuilder p = new StringBuilder();
            StringBuilder s = new StringBuilder();
            for (ActivityRecordDO activityRecordDo : activityRecordDos) {
                String activityName = activityDORepo.findByActivityId(activityRecordDo.getActivityId()).getActivityName();
                switch (activityRecordDo.getType()) {
                    case "lectureActivity":
                        l.append(activityName).append(",");
                        break;
                    case "practiceActivity":
                        p.append(activityName).append(",");
                        break;
                    case "schoolActivity":
                        s.append(activityName).append(",");
                        break;
                    default:
                        break;
                }
            }
    
            String[] content = new String[6];
            content[0] = userInfoBO.getStuId();
            content[1] = userInfoBO.getRealName();
            content[2] = userInfoBO.getSex();
            content[3] = l.toString();
            content[4] = p.toString();
            content[5] = s.toString();
            csvWriter.writeRecord(content);
        }
        csvWriter.close();
    }

    private Long getLongValue(Map<Object, Object[]> map, Object key, int No){
        if(map.get(key)==null)
            return 0L;
        else{
//            System.out.println(map.get(key)[No]);
            BigDecimal ans=(BigDecimal)map.get(key)[No];
            return ans.longValue();
        }
    }
}
