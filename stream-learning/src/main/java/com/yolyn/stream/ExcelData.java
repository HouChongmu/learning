package com.yolyn.stream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Yolyn
 * @version 1.0
 * @date 2021/1/22 12:47 PM
 * @project stream-learning
 */
public class ExcelData {

    public static Map<String, ArrayList<String>> readInsuredTxt() {
        try {
            FileReader fileReader = new FileReader(new File("/Users/yolyn/Desktop/t_policy_yiqing_0121_tmp2 3.txt"));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Map<String, ArrayList<String>> map = new TreeMap<>();

            for (; ; ) {
                String content = bufferedReader.readLine();
                if (content != null && !content.contains("policy_no")) {
                    String[] split = content.split(",");
                    String idNo = split[13];
                    String insureDate = split[2].substring(0, 10);
                    String key = idNo + "&" + insureDate;
                    ArrayList<String> list=map.get(key);
                    if (null==list){
                        list=new ArrayList<>();
                        map.put(key,list);
                    }
                    list.add(split[0]);

                } else if (content == null) {
                    break;
                }
            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //测试方法
    public static void main(String[] args) {
        Map<String, ArrayList<String>> txtDataMap = readInsuredTxt();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("/Users/yolyn/Desktop/疫情险3月份退保信息汇总清单(1).xlsx");
            File file = new File("/Users/yolyn/Desktop/test.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //获取sheet
            Sheet sheet = sheets.getSheet("Sheet2");
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < numberOfRows; i++) {
                XSSFRow row = (XSSFRow) sheet.getRow(i);
                XSSFCell idNo = row.getCell(2);
                String insureDate = row.getCell(12).toString().substring(0, 10);
                ArrayList<String> s = txtDataMap.get(idNo+"&"+insureDate);
                if (s != null) {
                    String content = row.getCell(1).toString() + "," + idNo.toString() + "," + String.join("、", s);
                    bufferedWriter.write(content, 0, content.length());
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    System.out.println("write content:" + content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        ExcelData sheet1 = new ExcelData("resource/FirstTests.xlsx", "username");
//        //获取第二行第4列
//        String cell2 = sheet1.getExcelDateByIndex(1, 3);
//        //根据第3列值为“customer23”的这一行，来获取该行第2列的值
//        String cell3 = sheet1.getCellByCaseName("customer23", 2, 1);
//        System.out.println(cell2);
//        System.out.println(cell3);
    }
}