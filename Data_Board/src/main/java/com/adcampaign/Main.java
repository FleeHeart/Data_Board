package com.adcampaign;

import com.adcampaign.model.AdCampaign;
import com.adcampaign.util.ExcelDataReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 程序主类（入口类）
 * 简单说：这是程序的"总指挥"，负责协调各个模块工作
 * 主要功能：
 * 1. 读取Excel数据
 * 2. 整理和排序数据
 * 3. 在控制台展示数据
 * 4. 分析数据趋势并展示结果
 */
public class Main {
    /**
     * 程序入口方法（Java程序从这里开始执行）
     * @param args 命令行参数（本程序未使用）
     */
    public static void main(String[] args) {
        try {
            // 创建Excel数据读取器对象
            ExcelDataReader reader = new ExcelDataReader();
            
            // 读取Excel文件："data.xlsx"（这个文件必须放在src/main/resources目录下）
            // 读取成功后，数据会保存在campaigns列表中
            List<AdCampaign> campaigns = reader.readAdCampaignData("data.xlsx");
            
            // 按日期对数据进行排序
            // 为什么要排序？因为Excel中的数据可能不是按日期顺序排列的
            Collections.sort(campaigns, Comparator.comparing(AdCampaign::getDate));
            
            // 在控制台展示广告数据
            displayCampaignData(campaigns);
            
            // 分析广告数据趋势（比如花费是增加还是减少）
            analyzeCampaignTrends(campaigns);
        } catch (IOException e) {
            // 如果读取文件时出错，这里会捕获异常并显示错误信息
            System.err.println("无法读取Excel文件: " + e.getMessage());
            e.printStackTrace(); // 打印详细错误信息（调试时很有用）
        }
    }
    
    /**
     * 在控制台展示广告活动数据（表格形式）
     * @param campaigns 广告活动数据列表（包含多天的数据）
     */
    private static void displayCampaignData(List<AdCampaign> campaigns) {
        // 打印表头
        System.out.println("\n广告活动数据汇总");
        System.out.println("-------------------------------------------------");
        // 使用printf格式化输出，让数据对齐
        // %-10s 表示左对齐，占10个字符位置（s表示字符串）
        // %-10.2f 表示左对齐，占10个字符位置，保留2位小数（f表示浮点数）
        System.out.printf("%-10s %-10s %-15s %-20s %-25s%n", 
                "日期", "花费", "线索数", "单个线索成本", "私信转化成本");
        System.out.println("-------------------------------------------------");
        
        // 循环遍历每一条广告数据，打印到控制台
        for (AdCampaign campaign : campaigns) {
            System.out.printf("%-10s %-10.2f %-15d %-20.2f %-25.2f%n",
                    campaign.getDate(),          // 日期
                    campaign.getCost(),          // 花费
                    campaign.getLeadCount(),     // 线索数
                    campaign.calculateCostPerLead(),  // 单个线索成本（调用AdCampaign类的计算方法）
                    campaign.calculateCostPerMessage()); // 私信转化成本（调用AdCampaign类的计算方法）
        }
        System.out.println("-------------------------------------------------");
    }
    
    /**
     * 分析广告活动趋势（比较相邻日期的数据变化）
     * @param campaigns 广告活动数据列表（需要至少2条数据才能进行比较）
     */
    private static void analyzeCampaignTrends(List<AdCampaign> campaigns) {
        // 检查数据是否足够：至少需要2天的数据才能进行趋势分析
        if (campaigns.size() < 2) {
            System.out.println("\n数据不足，无法进行趋势分析（至少需要2天的数据）");
            return; // 结束方法执行
        }
        
        // 打印趋势分析标题
        System.out.println("\n广告活动趋势分析");
        System.out.println("-------------------------------------------------");
        
        // 循环比较相邻日期的数据（从第2天开始，和前一天比较）
        for (int i = 1; i < campaigns.size(); i++) {
            AdCampaign current = campaigns.get(i);       // 当前日期的广告数据
            AdCampaign previous = campaigns.get(i-1);    // 前一天的广告数据
            
            // 打印比较结果标题
            System.out.println("\n从" + previous.getDate() + "到" + current.getDate() + "的变动:");
            
            // 调用AdCampaign类的分析方法，获取变动情况
            System.out.println(current.analyzeCostChange(previous));
            System.out.println(current.analyzeCostPerLeadChange(previous));
            System.out.println(current.analyzeMessageCostChange(previous));
        }
        System.out.println("-------------------------------------------------");
    }
}