package com.example.demo.utils;

import com.example.demo.domain.TBILModel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class OperatorExcel {

    public static String writeExcel(String version, ArrayList<TBILModel> list, String path) {
        //定义表头
        String[] title = {"企业名称", "企业注册号"};
        //创建excel工作簿
        Workbook workbook = getWorkbok(version);
        //创建工作表sheet
        Sheet sheet = workbook.createSheet();
        //创建第一行
        Row row = sheet.createRow(0);
        Cell cell = null;
        //插入第一行数据的表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //写入数据
        for (int i = 1; i <= list.size(); i++) {
            Row nrow = sheet.createRow(i);

            Cell ncell = nrow.createCell(0);
            ncell.setCellValue(list.get(i-1).getCompanyName());

            ncell = nrow.createCell(1);
            ncell.setCellValue(list.get(i-1).getERN());
        }
        File file = null;
        //创建excel文件
        if( workbook instanceof HSSFWorkbook) {
            file = new File(path+".xls");
            path+=".xls";
        } else {
           file = new File(path+".xlsx");
           path+=".xlsx";
        }

        try {
            file.createNewFile();
            //将excel写入
            FileOutputStream stream =  new FileOutputStream(file);//FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param version excel版本
     * @return Workbook
     * @throws IOException
     */
    public static Workbook getWorkbok(String version){
        Workbook wb = null;
        if("2003".equals(version)){     //Excel2003
            wb = new HSSFWorkbook();
        }else if("2007".equals(version)){    // Excel 2007以上
            wb = new XSSFWorkbook();
        }
        return wb;
    }

}
