package com.adcampaign.entity;


import jakarta.persistence.*;

@Entity // 核心注解：标记这个类是一个JPA实体，它将与数据库表映射
@Table(name = "ad_advertisement") // 指定映射的表名，如果不加，表名默认为类名ad_advertisement
@Access(AccessType.FIELD) // 添加这行明确指定字段访问
/**
 * 广告活动数据模型类
 * 用于存储广告活动的核心数据，并提供数据计算和分析功能
 * 每个实例代表一天的广告活动数据
 */
public class AdAdvertisement {
    @Id // 主键注解：标记为主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键注解：指定主键生成策略
    private Long id; // 主键ID（自动生成）

    // 以下为实体属性
    @Column(name = "date") // 指定映射的列名，如果不加，列名默认为属性名
    private String date;// 日期（例如："2023-10-01"）

    @Column(name = "cost") // 指定映射的列名，如果不加，列名默认为属性名
    private double cost;// 广告花费（单位：元）

    @Column(name = "lead_count") // 指定映射的列名，如果不加，列名默认为属性名
    private int leadCount;// 线索提交个数（用户提交的潜在客户信息数量）

    @Column(name = "message_count") // 指定映射的列名，如果不加，列名默认为属性名
    private int messageCount;// 私信消息数（收到的用户私信数量）

    /**
     * 构造方法：创建广告活动数据对象
     * @param date 日期字符串
     * @param cost 广告花费金额
     * @param leadCount 线索数量
     * @param messageCount 私信消息数量
     */
    public AdAdvertisement(String date, double cost, int leadCount, int messageCount) {
        // 调用无参构造方法初始化主键ID
        super();
        this.date = date;
        this.cost = cost;
        this.leadCount = leadCount;
        this.messageCount = messageCount;
    }

    public AdAdvertisement() {

    }

    // Getter方法：获取私有属性的值
    public Long getId() { return id; }
    public String getDate() { return date; }
    public double getCost() { return cost; }
    public int getLeadCount() { return leadCount; }
    public int getMessageCount() { return messageCount; }

    // Setter方法：修改私有属性的值
    public void setId(Long id) { this.id = id; }
    public void setDate(String date) { this.date = date; }
    public void setCost(double cost) { this.cost = cost; }
    public void setLeadCount(int leadCount) { this.leadCount = leadCount; }
    public void setMessageCount(int messageCount) { this.messageCount = messageCount; }

    /**
     * 计算单个线索成本
     * 公式：单个线索成本 = 广告花费 ÷ 线索数量
     * @return 单个线索成本（元/个），如果线索数量为0则返回0
     */
    public double calculateCostPerLead() {
        // 防止除以零错误：如果线索数为0，返回0
        return (leadCount > 0) ? cost / leadCount : 0;
    }

    /**
     * 计算私信消息转化成本
     * 公式：私信转化成本 = 广告花费 ÷ 私信消息数
     * @return 私信转化成本（元/条），如果私信数为0则返回0
     */
    public double calculateCostPerMessage() {
        // 防止除以零错误：如果私信数为0，返回0
        return (messageCount > 0) ? cost / messageCount : 0;
    }

    /**
     * 分析广告花费随日期的变动情况
     * @param previousCampaign 上一个日期的广告活动数据（用于比较）
     * @return 变动情况描述，包含变动金额和百分比
     */
    public String analyzeCostChange(AdAdvertisement previousCampaign) {
        // 检查是否有历史数据可供比较
        if (previousCampaign == null) return "无历史数据可供比较";

        // 计算变动金额：当前花费 - 上一期花费
        double changeAmount = this.cost - previousCampaign.getCost();
        // 计算变动百分比：(变动金额 ÷ 上一期花费) × 100%
        double changeRate = (changeAmount / previousCampaign.getCost()) * 100;

        // 格式化输出：保留两位小数，显示变动金额和百分比
        return String.format("花费变动: %.2f元 (%.2f%%)", changeAmount, changeRate);
    }

    /**
     * 分析单个线索成本随日期的变动情况
     * @param previousCampaign 上一个日期的广告活动数据（用于比较）
     * @return 变动情况描述，包含变动金额和百分比
     */
    public String analyzeCostPerLeadChange(AdAdvertisement previousCampaign) {
        if (previousCampaign == null) return "无历史数据可供比较";

        // 获取当前和上一期的单个线索成本（调用已有的计算方法）
        double currentCpl = this.calculateCostPerLead();
        double previousCpl = previousCampaign.calculateCostPerLead();

        // 防止除以零错误
        if (previousCpl <= 0) return "历史单个线索成本为0，无法计算变动率";

        double changeAmount = currentCpl - previousCpl;
        double changeRate = (changeAmount / previousCpl) * 100;

        return String.format("单个线索成本变动: %.2f元 (%.2f%%)", changeAmount, changeRate);
    }

    /**
     * 分析私信消息转化成本随日期的变动情况
     * @param previousCampaign 上一个日期的广告活动数据（用于比较）
     * @return 变动情况描述，包含变动金额和百分比
     */
    public String analyzeMessageCostChange(AdAdvertisement previousCampaign) {
        if (previousCampaign == null) return "无历史数据可供比较";

        // 获取当前和上一期的私信转化成本（调用已有的计算方法）
        double currentCpm = this.calculateCostPerMessage();
        double previousCpm = previousCampaign.calculateCostPerMessage();

        // 防止除以零错误
        if (previousCpm <= 0) return "历史私信转化成本为0，无法计算变动率";

        double changeAmount = currentCpm - previousCpm;
        double changeRate = (changeAmount / previousCpm) * 100;

        return String.format("私信转化成本变动: %.2f元 (%.2f%%)", changeAmount, changeRate);
    }
}
