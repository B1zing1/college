package com.util;

import com.bean.Books;
import com.bean.Class;
import com.bean.Menu;
import com.bean.UserTb;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {

    //创建一个excel对象
    private static HSSFWorkbook excel = new HSSFWorkbook();
    //创建一个sheet文件
    private static HSSFSheet sheet = excel.createSheet("sheet1");
    //创建保存文件头目录的数组
    public static String[] headers;
    //保存sheet表中的列数
    private static int cellCount;
    //创建第一行
    public static void createHead(String[] headers) {
        cellCount = headers.length;
        HSSFRow row = sheet.createRow(0);
        for(int i=0; i<cellCount; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }
    //创建其他行
    public static void createOtherRow(List list) {
        for(int i=0; i<list.size(); i++) {
            String name = list.get(i).getClass().getName();
            String[] names = name.split("\\.");
            if(names[names.length-1].equals("Class")) {
                Class class1 = (Class) list.get(i);
                HSSFRow row= sheet.createRow(i+1);
                HSSFCell c1= row.createCell(0);
                HSSFCell c2= row.createCell(1);
                HSSFCell c3= row.createCell(2);
                HSSFCell c4= row.createCell(3);
                HSSFCell c5= row.createCell(4);
                HSSFCell c6= row.createCell(5);

                c1.setCellValue(class1.getDepartment().getDepartname());
                c2.setCellValue(class1.getClassnum());
                c3.setCellValue(class1.getClassname());
                c4.setCellValue(class1.getClassteacher());
                c5.setCellValue(class1.getPeoplecount());
                c6.setCellValue(class1.getClassstate());
            } else if(names[names.length-1].equals("Menu")) {
                Menu menu = (Menu) list.get(i);
                HSSFRow row= sheet.createRow(i+1);
                HSSFCell c1= row.createCell(0);
                HSSFCell c2= row.createCell(1);
                HSSFCell c3= row.createCell(2);

                c1.setCellValue(menu.getMenuname());
                c2.setCellValue(menu.getMenupath());
                c3.setCellValue(menu.getMenustate());
            } else if(names[names.length-1].equals("UserTb")) {
                UserTb userTb = (UserTb) list.get(i);
                HSSFRow row= sheet.createRow(i+1);
                HSSFCell c1= row.createCell(0);
                HSSFCell c2= row.createCell(1);
                HSSFCell c3= row.createCell(2);

                c1.setCellValue(userTb.getUserName());
                c2.setCellValue(userTb.getUserRealname());
                c3.setCellValue(userTb.getRole().getRolename());
            } else if(names[names.length-1].equals("Books")) {
                Books books = (Books) list.get(i);
                HSSFRow row= sheet.createRow(i+1);
                HSSFCell c1= row.createCell(0);
                HSSFCell c2= row.createCell(1);
                HSSFCell c3= row.createCell(2);

                c1.setCellValue(books.getBookid());
                c2.setCellValue(books.getBookname());
                c3.setCellValue(books.getBookstate());
            }
        }
    }
    //设置io流和excel的关系
    public static void export(OutputStream outputStream){
        try {
            //1.设置以表格的方式输出
            sheet.setGridsPrinted(true);
            //2.建立io的关系
            excel.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}














