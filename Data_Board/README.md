# 广告数据展示程序 (OOP重构设计)

## 项目概述
这是一个用Java编写的广告数据展示程序，采用面向对象设计思想重构，能够从Excel文件读取广告数据，展示广告花费、线索数量等关键指标，并提供多日期数据对比分析功能。程序支持数据自动计算（单个线索成本、私信转化成本）和趋势分析，具备良好的可扩展性和维护性。

## OOP重构思考过程

### 1. 需求分析与实体识别
原始程序采用过程式编程，所有逻辑都集中在main方法中。重构前需要明确：
- **核心实体**：广告活动数据（包含日期、花费、线索数等属性）
- **核心行为**：数据计算（单个线索成本、消息转化成本）和变动分析
- **数据关系**：不同日期的广告活动数据需要进行对比分析
- **数据来源**：从Excel文件读取数据而非硬编码

### 2. 类设计思路
#### 2.1 确定实体类
识别到三个核心类：
- **AdCampaign**：封装广告活动数据及业务逻辑
- **ExcelDataReader**：处理Excel文件读取与数据解析
- **Main**：程序入口，协调数据读取、处理与展示

#### 2.2 封装数据与行为
遵循OOP封装原则：
- 使用`private`修饰属性，通过getter/setter方法控制访问
- 将计算逻辑（单个线索成本、消息转化成本）封装为AdCampaign类的方法
- 将Excel读取逻辑独立为ExcelDataReader工具类，遵循单一职责原则
- 主程序逻辑在Main类中实现，负责流程控制

### 3. 重构步骤
#### 3.1 实现数据持久化
添加Excel数据读取功能，创建ExcelDataReader类：
```java
public class ExcelDataReader {
    public List<AdCampaign> readAdCampaignData(String fileName) throws IOException {
        // 实现从Excel文件读取数据并转换为AdCampaign对象列表
    }
    
    // 辅助方法：安全获取单元格字符串值和数值型值
    private String getCellStringValue(Cell cell) { ... }
    private double getCellNumericValue(Cell cell) { ... }
}
```

## 类结构说明
### AdCampaign类
| 属性 | 类型 | 说明 |
|------|------|------|
| date | String | 日期 |
| cost | double | 广告花费 |
| leadCount | int | 线索提交个数 |
| messageCount | int | 私信消息数 |

| 方法 | 说明 |
|------|------|
| calculateCostPerLead() | 计算单个线索成本 |
| calculateCostPerMessage() | 计算私信消息转化成本 |
| analyzeCostChange(AdCampaign) | 分析花费变动情况 |
| analyzeCostPerLeadChange(AdCampaign) | 分析线索成本变动情况 |
| analyzeMessageCostChange(AdCampaign) | 分析消息转化成本变动情况 |

### ExcelDataReader类
| 方法 | 说明 |
|------|------|
| readAdCampaignData(String fileName) | 读取Excel文件并返回AdCampaign对象列表 |
| getCellStringValue(Cell cell) | 安全获取单元格字符串值 |
| getCellNumericValue(Cell cell) | 安全获取单元格数值型值 |

### Main类
| 方法 | 说明 |
|------|------|
| main(String[] args) | 程序入口点，协调数据读取、处理与展示 |
| displayCampaignData(List<AdCampaign>) | 格式化展示广告活动数据 |
| analyzeCampaignTrends(List<AdCampaign>) | 分析广告活动数据趋势 |

## 运行说明
### 环境准备
1. **开发环境**：JDK 8+、Maven 3.6+、IntelliJ IDEA（推荐）
2. **依赖配置**：项目使用Maven管理依赖，关键依赖包括：
   ```xml
   <dependencies>
       <!-- Apache POI - Excel文件处理 -->
       <dependency>
           <groupId>org.apache.poi</groupId>
           <artifactId>poi</artifactId>
           <version>5.2.3</version>
       </dependency>
       <dependency>
           <groupId>org.apache.poi</groupId>
           <artifactId>poi-ooxml</artifactId>
           <version>5.2.3</version>
       </dependency>
   </dependencies>
   ```

### 数据准备
1. 在`src/main/resources`目录下创建`data.xlsx`文件
2. 按以下列顺序组织数据（第一行为表头）：
   | 日期       | 花费   | 线索提交个数 | 私信消息数 |
   |------------|--------|--------------|------------|
   | 2023-10-01 | 1500.5 | 30           | 120        |
   | 2023-10-02 | 1800.0 | 36           | 145        |

### 运行步骤
1. 克隆或下载项目到本地
2. 使用IntelliJ IDEA打开项目
3. 确保Maven依赖已正确导入
4. 准备好`data.xlsx`数据文件
5. 运行`Main.java`类的main方法
6. 在控制台查看数据展示和趋势分析结果

## 故障排除
### 常见问题及解决方法
1. **Excel文件读取错误**
   - 错误信息：`Cannot get a NUMERIC value from a STRING cell`
   - 解决方法：确保Excel中数值列（花费、线索数、私信数）格式为数字类型而非文本类型

2. **文件找不到异常**
   - 错误信息：`FileNotFoundException`
   - 解决方法：确认`data.xlsx`文件已放置在`src/main/resources`目录下

3. **依赖缺失**
   - 错误信息：`ClassNotFoundException`
   - 解决方法：在IntelliJ IDEA中右键点击`pom.xml` -> `Maven` -> `Reload project`

## 扩展性考虑
- **数据可视化**：可集成JFreeChart或JavaFX添加图表展示功能
- **多数据源支持**：扩展ExcelDataReader为通用DataReader接口，实现CSV、数据库等多数据源读取
- **数据过滤功能**：添加日期范围选择、指标筛选等交互功能
- **报告生成**：实现数据导出为PDF或HTML格式报告的功能

## 总结
通过面向对象重构，程序实现了：
- **关注点分离**：数据模型(AdCampaign)、数据访问(ExcelDataReader)和业务逻辑(Main)分离
- **可维护性**：单一职责原则使代码更易于理解和修改
- **可扩展性**：新功能可通过添加新类或方法实现，无需修改现有核心逻辑
- **健壮性**：添加异常处理和数据验证，提高程序稳定性