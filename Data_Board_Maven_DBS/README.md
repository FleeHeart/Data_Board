# Data_Board_Maven_DBS

## 项目概述
Data_Board_Maven_DBS 是一个基于 Spring Boot 的广告活动数据管理和分析系统，旨在帮助用户跟踪、存储和分析广告投放效果数据。该系统提供了数据的增删改查功能，并内置了多种数据分析方法，便于用户评估广告投放的 ROI (投资回报率)。

## 功能特性
- 广告数据管理 ：支持广告活动数据的添加、查询等基本操作
- 数据计算 ：自动计算单个线索成本、私信转化成本等关键指标
- 数据分析 ：提供广告花费变动分析、线索成本变动分析、私信转化成本变动分析等功能
- RESTful API ：通过 HTTP 接口提供数据服务，便于与其他系统集成

## 技术栈
- 后端框架 ：Spring Boot 3.2.10
- 编程语言 ：Java 17
- 数据访问 ：Spring Data JPA
- 数据库 ：MySQL
- 构建工具 ：Maven

## 项目结构
项目采用标准的 Spring Boot 分层架构，主要包含以下模块：
src/main/java/com/adcampaign/
├── DataBoardMavenDbsApplication.java  # 应用程序入口
├── controller/                        # 控制器层，处理HTTP请求
│   └── AdController.java              # 广告数据控制器
├── entity/                            # 实体层，映射数据库表
│   └── AdAdvertisement.java           # 广告活动实体类
└── repository/                        # 数据访问层，提供数据操作接口
    └── AdRepository.java              # 广告数据仓库接口

## 安装步骤
### 前提条件
- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 5.7 或更高版本

1. 克隆项目
   ```bash
   git clone <项目仓库地址>
   cd Data_Board_Maven_DBS
   ```
### . 配置数据库
1. 创建 MySQL 数据库：
   ```SQL
   CREATE DATABASE ad_db CHARACTER SET utf8mb4 
   COLLATE utf8mb4_unicode_ci;
   ```
2. 配置数据库连接信息：在 src/main/resources/application.properties 文件中修改数据库连接信息：
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ad_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    spring.datasource.username=root
    spring.datasource.password=123456
    ```
### 3. 构建和运行项目
使用 Maven 构建并运行项目：
   ```bash
   mvn clean package
   java -jar target/Data_Board_Maven_DBS-0.0.1-SNAPSHOT.jar
   ```
或者直接在开发环境中运行 DataBoardMavenDbsApplication.java 类的 main 方法。

## 使用方法
### API 接口
系统提供了以下 RESTful API 接口：
 获取所有广告数据
- URL : /api/ads
- 方法 : GET
- 描述 : 获取数据库中所有的广告活动数据
- 响应示例 :
    ```json
    [
      {
        "id": 1,
        "date": "2023-10-01",
        "cost": 1000.0,
        "leadCount": 50,
        "messageCount": 100
      },
      {
        "id": 2,
        "date": "2023-10-02",
        "cost": 1200.0,
        "leadCount": 60,
        "messageCount": 120
      }
    ]
    ```
添加新的广告数据
- URL : /api/ads
- 方法 : POST
- 描述 : 向数据库中添加新的广告活动数据
- 请求体示例 :
    ```json
    {
      "date": "2023-10-03",
      "cost": 1500.0,
      "leadCount": 70,
      "messageCount": 150
    }
    ```
- 响应示例 :
    ```json
    {
      "id": 3,
      "date": "2023-10-03",
      "cost": 1500.0,
      "leadCount": 70,
      "messageCount": 150
    }
    ```
Trae

思考过程

用户要求我将生成的README文档内容以完整的Markdown格式输出。我需要使用response_to_user工具，并在summary参数中提供完整的Markdown格式的README文档。

注意事项：

1. 确保使用正确的Markdown格式
2. 文档应包含项目概述、功能特性、安装步骤、使用方法、配置说明、贡献指南及许可证信息等核心内容
3. 确保信息准确且易于理解
4. 按照用户要求的格式进行输出

# Data_Board_Maven_DBS
## 项目概述
Data_Board_Maven_DBS 是一个基于 Spring Boot 的广告活动数据管理和分析系统，旨在帮助用户跟踪、存储和分析广告投放效果数据。该系统提供了数据的增删改查功能，并内置了多种数据分析方法，便于用户评估广告投放的 ROI (投资回报率)。

## 功能特性
- 广告数据管理 ：支持广告活动数据的添加、查询等基本操作
- 数据计算 ：自动计算单个线索成本、私信转化成本等关键指标
- 数据分析 ：提供广告花费变动分析、线索成本变动分析、私信转化成本变动分析等功能
- RESTful API ：通过 HTTP 接口提供数据服务，便于与其他系统集成
## 技术栈
- 后端框架 ：Spring Boot 3.2.10
- 编程语言 ：Java 17
- 数据访问 ：Spring Data JPA
- 数据库 ：MySQL
- 构建工具 ：Maven
## 项目结构
项目采用标准的 Spring Boot 分层架构，主要包含以下模块：

```
src/main/java/com/adcampaign/
├── DataBoardMavenDbsApplication.java  # 应用程序
入口
├── controller/                        # 控制器
层，处理HTTP请求
│   └── AdController.java              # 广告数据
控制器
├── entity/                            # 实体层，
映射数据库表
│   └── AdAdvertisement.java           # 广告活动
实体类
└── repository/                        # 数据访问
层，提供数据操作接口
    └── AdRepository.java              # 广告数据
    仓库接口
```
## 安装步骤
### 前提条件
- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 5.7 或更高版本
### 1. 克隆项目
```
git clone <项目仓库地址>
cd Data_Board_Maven_DBS
```
### 2. 配置数据库
1. 1.
   创建 MySQL 数据库：
   
   ```
   CREATE DATABASE ad_db CHARACTER SET utf8mb4 
   COLLATE utf8mb4_unicode_ci;
   ```
2. 2.
   配置数据库连接信息：
   在 src/main/resources/application.properties 文件中修改数据库连接信息：
   
   ```
   spring.datasource.url=jdbc:mysql://
   localhost:3306/ad_db?useUnicode=true&
   characterEncoding=UTF-8&serverTimezone=Asia/
   Shanghai
   spring.datasource.username=root
   spring.datasource.password=123456
   ```
### 3. 构建和运行项目
使用 Maven 构建并运行项目：

```
mvn clean package
java -jar target/Data_Board_Maven_DBS-0.0.
1-SNAPSHOT.jar
```
或者直接在开发环境中运行 DataBoardMavenDbsApplication.java 类的 main 方法。

## 使用方法
### API 接口
系统提供了以下 RESTful API 接口：
 获取所有广告数据
- URL : /api/ads
- 方法 : GET
- 描述 : 获取数据库中所有的广告活动数据
- 响应示例 :
  ```
  [
    {
      "id": 1,
      "date": "2023-10-01",
      "cost": 1000.0,
      "leadCount": 50,
      "messageCount": 100
    },
    {
      "id": 2,
      "date": "2023-10-02",
      "cost": 1200.0,
      "leadCount": 60,
      "messageCount": 120
    }
  ]
  ``` 添加新的广告数据
- URL : /api/ads
- 方法 : POST
- 描述 : 向数据库中添加新的广告活动数据
- 请求体示例 :
  ```
  {
    "date": "2023-10-03",
    "cost": 1500.0,
    "leadCount": 75,
    "messageCount": 150
  }
  ```
- 响应示例 :
  ```
  {
    "id": 3,
    "date": "2023-10-03",
    "cost": 1500.0,
    "leadCount": 75,
    "messageCount": 150
  }
  ```
### 数据计算和分析功能
AdAdvertisement 实体类提供了以下数据计算和分析方法：

1. 1.
   calculateCostPerLead() : 计算单个线索成本
2. 2.
   calculateCostPerMessage() : 计算私信消息转化成本
3. 3.
   analyzeCostChange(AdAdvertisement previousCampaign) : 分析广告花费随日期的变动情况
4. 4.
   analyzeCostPerLeadChange(AdAdvertisement previousCampaign) : 分析单个线索成本随日期的变动情况
5. 5.
   analyzeMessageCostChange(AdAdvertisement previousCampaign) : 分析私信消息转化成本随日期的变动情况
## 配置说明
项目主要配置文件为 src/main/resources/application.properties ，包含以下主要配置项：
    ```properties
    # 数据库连接配置
    spring.datasource.url=jdbc:mysql://localhost:3306/ad_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    spring.datasource.username=root
    spring.datasource.password=123456

    # JPA 配置
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

配置项说明：

- spring.datasource.url : 数据库连接 URL
- spring.datasource.username : 数据库用户名
- spring.datasource.password : 数据库密码
- spring.jpa.hibernate.ddl-auto=update : 自动更新数据库表结构
- spring.jpa.show-sql=true : 显示 SQL 语句
- spring.jpa.properties.hibernate.dialect : 指定 Hibernate 方言
## 贡献指南
1. 1.
   Fork 项目仓库
2. 2.
   创建功能分支 ( git checkout -b feature/AmazingFeature )
3. 3.
   提交更改 ( git commit -m 'Add some AmazingFeature' )
4. 4.
   推送到分支 ( git push origin feature/AmazingFeature )
5. 5.
   开启 Pull Request