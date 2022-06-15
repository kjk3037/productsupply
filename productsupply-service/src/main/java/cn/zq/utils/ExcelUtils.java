package cn.zq.utils;

import cn.zq.pojo.BillOfMaterial;
import cn.zq.pojo.DataField;
import cn.zq.pojo.MaterialInfo;
import cn.zq.service.BillOfMaterialService;
import cn.zq.service.DataFieldService;
import cn.zq.service.MaterialInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;


import javax.servlet.Servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ExcelUtils {
    //设置字体
    private static HSSFFont getExportFont(HSSFWorkbook wb) {
        HSSFFont blackFont = wb.createFont();
        blackFont.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        blackFont.setFontName("微软雅⿊");   // 设置字体颜⾊
        blackFont.setFontHeightInPoints((short) 12);
        return blackFont;
    }
    //设置样式
    private static HSSFCellStyle getStyle(HSSFWorkbook wb){
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }
    //输出bom文件
    public static void outputBOM(String parentCode, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, IOException {
        BillOfMaterialService bomService = (BillOfMaterialService) BeanUtils.getBean(BillOfMaterialService.class);
        DataFieldService dataFieldService= (DataFieldService) BeanUtils.getBean(DataFieldService.class);
        MaterialInfoService materialInfoService = (MaterialInfoService) BeanUtils.getBean(MaterialInfoService.class);
        MaterialInfo byCode = materialInfoService.getByCode(parentCode);
        List<BillOfMaterial> completeBOM = bomService.getCompleteBOM(parentCode,"0");
        HSSFWorkbook excel=new HSSFWorkbook();
        //获取雅黑字体样式
        HSSFFont simpleFont = getExportFont(excel);
        simpleFont.setBold(true);
        //获取居中样式
        HSSFCellStyle style = getStyle(excel);
        style.setFont(simpleFont);
        HSSFSheet sheet = excel.createSheet("BOM");
        sheet.setDefaultColumnWidth(20);
        HSSFRow titleRow = sheet.createRow(0);
        HSSFRow fieldRow= sheet.createRow(1);
        HSSFCell titleCell = titleRow.createCell(0);
        //设置标题样式
        titleCell.setCellValue(parentCode+" "+byCode.getMaterialName()+" "+byCode.getStandard()+" "+byCode.getModel());
        titleCell.setCellStyle(style);
        QueryWrapper<DataField> dataFieldQueryWrapper = new QueryWrapper<>();
        dataFieldQueryWrapper.eq("parent_id","68");
        List<DataField> dataFields = dataFieldService.list(dataFieldQueryWrapper);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, dataFields.size() - 1);
        sheet.addMergedRegion(cellAddresses);
        //设置表头
        for(int i=0;i<dataFields.size();i++){
            HSSFCell cell = fieldRow.createCell(i);
            cell.setCellValue(dataFields.get(i).getLabel());
            cell.setCellStyle(style);
        }
        //设置数值
        for(int o=0;o<completeBOM.size();o++){
            HSSFRow row=sheet.createRow(o+2);
            for (int p=0;p<dataFields.size();p++){
                //字段名称获取
                String prop = dataFields.get(p).getProp();
                Class<BillOfMaterial> billOfMaterialClass = BillOfMaterial.class;
                Field field = billOfMaterialClass.getDeclaredField(prop);
                //首字母大写  用以通过反射匹配field相应get方法
                prop=prop.substring(0,1).toUpperCase()+prop.substring(1);
                Method method=billOfMaterialClass.getMethod("get"+prop);
                HSSFCell cell=row.createCell(p);
                cell.setCellStyle(style);
                if (method.invoke(completeBOM.get(o))!=null){
                    if (field.getType()==Integer.class){
                        cell.setCellValue(new Double((Integer) method.invoke(completeBOM.get(o))));
                    }else if(field.getType()==String.class) {
                        cell.setCellValue((String) method.invoke(completeBOM.get(o)));
                    }else if (field.getType()==Float.class){
                        cell.setCellValue(new Double((float) method.invoke(completeBOM.get(o))));
                    }
                    else if (field.getType()== BigDecimal.class){
                        cell.setCellValue(new BigDecimal((String) method.invoke(completeBOM.get(o)).toString()).doubleValue());
                    }
                }
            }
        }
        //FileOutputStream fos = new FileOutputStream("C:\\MyDocument\\myJavaProject\\productsupply\\file\\"+parentCode+" "+materialInfoService.getByCode(parentCode).getMaterialName()+" BOM.xlsx");
        //返回excel文件字节流
        ServletOutputStream sos=response.getOutputStream();
        excel.write(sos);
        sos.close();
    }

    //输出数据excel文件
    public static void outputData(Class targetClazz,String businessKey, HttpServletResponse response) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, IOException {
        //获取数据信息
        Object obj=BeanUtils.getBean(targetClazz);
        String targetClassName = obj.getClass().getName();
        String targetServiceClassName=targetClassName.substring(0,1).toLowerCase()+targetClassName.substring(1)+"Service";
        Object objService=BeanUtils.getBean(targetServiceClassName);
        Method getListMethod = objService.getClass().getMethod("getList");
        DataFieldService dataFieldService= (DataFieldService) BeanUtils.getBean(DataFieldService.class);
        List data = (List) getListMethod.invoke(objService);
        HSSFWorkbook excel=new HSSFWorkbook();
        //获取雅黑字体样式
        HSSFFont simpleFont = getExportFont(excel);
        simpleFont.setBold(true);
        //获取居中样式
        HSSFCellStyle style = getStyle(excel);
        style.setFont(simpleFont);
        HSSFSheet sheet = excel.createSheet("BOM");
        sheet.setDefaultColumnWidth(20);
        HSSFRow titleRow = sheet.createRow(0);
        HSSFRow fieldRow= sheet.createRow(1);
        HSSFCell titleCell = titleRow.createCell(0);
        //设置标题样式
        titleCell.setCellValue("导出数据");
        titleCell.setCellStyle(style);
        QueryWrapper<DataField> dataFieldQueryWrapper = new QueryWrapper<>();
        dataFieldQueryWrapper.eq("parent_id","68");
        List<DataField> dataFields = dataFieldService.list(dataFieldQueryWrapper);
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, dataFields.size() - 1);
        sheet.addMergedRegion(cellAddresses);
        //设置表头
        for(int i=0;i<dataFields.size();i++){
            HSSFCell cell = fieldRow.createCell(i);
            cell.setCellValue(dataFields.get(i).getLabel());
            cell.setCellStyle(style);
        }
        //设置数值
        for(int o=0;o<data.size();o++){
            HSSFRow row=sheet.createRow(o+2);
            for (int p=0;p<dataFields.size();p++){
                //字段名称获取
                String prop = dataFields.get(p).getProp();
                Field field = obj.getClass().getDeclaredField(prop);
                //首字母大写  用以通过反射匹配field相应get方法
                prop=prop.substring(0,1).toUpperCase()+prop.substring(1);
                Method method=obj.getClass().getMethod("get"+prop);
                HSSFCell cell=row.createCell(p);
                cell.setCellStyle(style);
                if (method.invoke(data.get(o))!=null){
                    if (field.getType()==Integer.class){
                        cell.setCellValue(new Double((Integer) method.invoke(data.get(o))));
                    }else if(field.getType()==String.class) {
                        cell.setCellValue((String) method.invoke(data.get(o)));
                    }else if (field.getType()==Float.class){
                        cell.setCellValue(new Double((float) method.invoke(data.get(o))));
                    }
                    else if (field.getType()== BigDecimal.class){
                        cell.setCellValue(new BigDecimal( method.invoke(data.get(o)).toString()).doubleValue());
                    }
                }
            }
        }
        //FileOutputStream fos = new FileOutputStream("C:\\MyDocument\\myJavaProject\\productsupply\\file\\"+parentCode+" "+materialInfoService.getByCode(parentCode).getMaterialName()+" BOM.xlsx");
        //返回excel文件字节流
        ServletOutputStream sos=response.getOutputStream();
        excel.write(sos);
        sos.close();
    }
}
