# Data Board Spring Boot项目

## 项目概述

Data Board是一个基于Spring Boot开发的广告数据管理和分析平台，旨在帮助广告投放者跟踪、分析和优化广告投放效果。该项目提供了简洁的REST API接口，支持广告数据的查询和录入，并内置了基础的数据分析功能。

### 核心功能
- 广告活动数据的管理（查询和创建）
- 广告效果分析（单线索成本计算、私信转化成本计算）
- 数据变动趋势分析（成本变动、效果指标变动等）
- 提供JSON格式的API接口，便于与前端或其他系统集成

## 环境要求

- **JDK版本**: 21或更高版本
- **构建工具**: Gradle 8.14.3或兼容版本
- **Spring Boot版本**: 3.5.5
- **开发工具**: 推荐IntelliJ IDEA、Eclipse或VS Code（配置Java开发环境）

## 安装步骤

### 1. 克隆项目（假设项目已在代码仓库中）
```bash
# 如果项目在Git仓库中，使用以下命令克隆
# git clone [仓库地址]

# 或者直接访问项目目录
cd d:\software\code\Java code\Data_Board_Spring_Boot
```

### 2. 使用Gradle构建项目
```bash
# Windows系统
gradlew build

# Unix/Linux/Mac系统
./gradlew build
```

### 3. 运行项目
```bash
# Windows系统
gradlew bootRun

# Unix/Linux/Mac系统
./gradlew bootRun
```

## 配置说明

项目的主要配置文件位于`src/main/resources/application.properties`，目前配置较为简单：

```properties
# 应用程序名称
spring.application.name=demo
```

如需添加更多配置（如数据库连接、服务器端口等），可以在该文件中进行设置。例如：

```properties
# 服务器端口（默认8080）
# server.port=8080

# 数据库配置（如需连接数据库）
# spring.datasource.url=jdbc:mysql://localhost:3306/data_board
# spring.datasource.username=root
# spring.datasource.password=password
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 使用指南

### API接口使用

项目提供了以下REST API接口：

#### 1. 获取所有广告数据
- **请求方式**: GET
- **请求URL**: `http://localhost:8080/api/ads`
- **请求参数**: 无
- **返回示例**: 
```json
[
  {
    "date": "2025-9-28",
    "cost": 5000.0,
    "leadCount": 10,
    "messageCount": 50
  },
  {
    "date": "2025-9-29",
    "cost": 6000.0,
    "leadCount": 15,
    "messageCount": 60
  }
]
```

#### 2. 创建新的广告数据
- **请求方式**: POST
- **请求URL**: `http://localhost:8080/api/ads`
- **请求体示例**: 
```json
{
  "date": "2025-9-30",
  "cost": 7000.0,
  "leadCount": 20,
  "messageCount": 70
}
```
- **返回示例**: 
广告创建成功！2025-9-30


### 数据分析功能使用

AdAdvertisement实体类提供了以下数据分析方法：

1. **calculateCostPerLead()**: 计算单个线索成本
2. **calculateCostPerMessage()**: 计算私信消息转化成本
3. **analyzeCostChange(AdAdvertisement previous)**: 分析广告花费随日期的变动情况
4. **analyzeCostPerLeadChange(AdAdvertisement previous)**: 分析单个线索成本变动情况
5. **analyzeMessageCostChange(AdAdvertisement previous)**: 分析私信转化成本变动情况

## 项目结构
项目采用标准的Spring Boot项目结构，主要包含以下组件：
src/main/java/com/adcampaign/
├── Data_Board_Appliction.java  # 应用程序入口类
├── controller/                 # 控制器目录
│   └── AdController.java       # 广告数据控制器
└── entity/                     # 实体类目录
└── AdAdvertisement.java    # 广告活动数据模型


### 主要组件说明

1. **Data_Board_Appliction.java**: 应用程序的主入口，使用`@SpringBootApplication`和`@RestController`注解标记

2. **AdController.java**: REST API控制器，提供广告数据的查询和创建功能
   - 包含GET接口：获取所有广告数据
   - 包含POST接口：创建新的广告数据
   - 目前使用内存中的静态列表存储模拟数据

3. **AdAdvertisement.java**: 广告活动数据模型类
   - 包含日期、花费、线索数、私信数等基础数据字段
   - 提供数据计算和分析方法

## 常见问题

### 1. 如何修改服务器端口？
在`application.properties`文件中添加`server.port=您想要的端口号`配置。

### 2. 为什么POST请求后数据没有被保存？
目前项目使用的是内存中的静态列表存储数据，应用重启后数据会丢失。如需持久化存储，需要集成数据库。

### 3. 如何集成数据库？
1. 在`build.gradle`中添加数据库驱动依赖
2. 在`application.properties`中配置数据库连接信息
3. 添加数据访问层（Repository）组件

### 4. 如何进行单元测试？
可以使用Spring Boot提供的测试框架进行测试。示例测试代码结构如下：
```java
@SpringBootTest
class DataBoardApplicationTests {
    
    @Autowired
    private AdController adController;
    
    @Test
    void contextLoads() {
        // 测试上下文加载
        assertNotNull(adController);
    }
    
    // 其他测试方法...
}
```

## 扩展建议

1. **数据持久化**: 集成MySQL、MongoDB等数据库，实现数据的持久化存储
2. **用户认证**: 添加Spring Security实现用户认证和授权
3. **前端界面**: 开发Web前端界面，提供可视化的数据展示和分析功能
4. **数据导出**: 添加数据导出功能，支持CSV、Excel等格式
5. **定时任务**: 添加定时任务，自动收集和处理广告数据

## 联系方式

如有问题或建议，请联系项目维护人员。