package com.adcampaign.util;

import com.adcampaign.model.AdCampaign;
import org.apache.poi.ss.usermodel.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Excel数据读取工具类
 * 简单说：这个类专门负责从Excel文件中读取广告数据
 * 功能：
 * 1. 读取Excel文件内容
 * 2. 将表格数据转换为我们程序能使用的广告活动对象
 * 3. 处理不同类型的单元格数据（数字、文本等）
 */
public class ExcelDataReader {
    /**
     * 从Excel文件读取广告活动数据
     * @param fileName Excel文件名（注意：文件必须放在src/main/resources目录下）
     * @return 广告活动数据列表，每个元素代表一天的广告数据
     * @throws IOException 如果文件找不到或读取失败，会抛出这个异常
     */
    public List<AdCampaign> readAdCampaignData(String fileName) throws IOException {
        // 创建一个空列表，用于存储读取到的广告数据
        List<AdCampaign> campaigns = new ArrayList<>();
        
        // 打开Excel文件（使用try-with-resources语法，会自动关闭文件，不用担心忘记关闭）
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            // 创建工作簿对象（代表整个Excel文件）
            Workbook workbook = WorkbookFactory.create(is);
            
            // 获取第一个工作表（Excel文件可以有多个标签页，这里我们读取第一个）
            Sheet sheet = workbook.getSheetAt(0);
            
            // 创建行迭代器，用于一行一行地读取数据
            Iterator<Row> rowIterator = sheet.iterator();
        
            // 跳过表头行（Excel中第一行通常是标题，如"日期"、"花费"，不需要作为数据读取）
            if (rowIterator.hasNext()) {
                rowIterator.next(); // 移动到下一行（跳过表头）
            }
        
            // 循环读取每一行数据
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next(); // 获取当前行
                
                // 如果第一列（日期列）为空，说明数据已经读完，停止循环
                if (row.getCell(0) == null) break;
        
                // 读取当前行的每一列数据（使用辅助方法安全读取）
                String date = getCellStringValue(row.getCell(0)); // 第1列：日期
                double cost = getCellNumericValue(row.getCell(1)); // 第2列：花费
                int leadCount = (int) getCellNumericValue(row.getCell(2)); // 第3列：线索数
                int messageCount = (int) getCellNumericValue(row.getCell(3)); // 第4列：私信数
        
                // 创建广告活动对象，并添加到列表中
                campaigns.add(new AdCampaign(date, cost, leadCount, messageCount));
            }
        
            // 关闭工作簿（释放内存）
            workbook.close();
            
            // 返回读取到的广告数据列表
            return campaigns;
        }
    }
    
    /**
     * 辅助方法：安全获取单元格的字符串值
     * 为什么需要这个方法？因为Excel单元格有不同类型（文本、数字、日期等），直接读取可能出错
     * @param cell Excel单元格对象
     * @return 单元格内容的字符串形式
     */
    private String getCellStringValue(Cell cell) {
        if (cell == null) return ""; // 如果单元格为空，返回空字符串
        
        // 根据单元格类型进行不同处理
        switch (cell.getCellType()) {
            case STRING:
                // 文本类型：直接获取文本
                return cell.getStringCellValue();
            case NUMERIC:
                // 数字类型：判断是否是日期格式
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // 普通数字：转换为字符串（如123 -> "123"）
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                // 布尔类型（true/false）：转换为字符串
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // 公式类型：返回公式本身
                return cell.getCellFormula();
            default:
                // 其他类型：返回空字符串
                return "";
        }
    }
    
    /**
     * 辅助方法：安全获取单元格的数值型值
     * 为什么需要这个方法？因为有时候数字会被Excel格式化为文本，直接读取会出错
     * @param cell Excel单元格对象
     * @return 单元格的数值（如果无法转换为数字，返回0）
     */
    private double getCellNumericValue(Cell cell) {
        if (cell == null) return 0; // 如果单元格为空，返回0
        
        // 根据单元格类型进行不同处理
        switch (cell.getCellType()) {
            case NUMERIC:
                // 数字类型：直接获取数值
                return cell.getNumericCellValue();
            case STRING:
                // 文本类型：尝试转换为数字
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    // 如果转换失败（比如文本内容是"abc"），返回0
                    return 0;
                }
            default:
                // 其他类型：返回0
                return 0;
        }
    }
}