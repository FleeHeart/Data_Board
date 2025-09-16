# Data_Board_Maven_UI
## 项目概述
Data_Board_Maven_UI 是一个基于 Spring Boot 的广告活动数据管理系统，旨在帮助用户跟踪、存储和分析广告投放效果数据。该系统提供了数据的添加、查询等基本操作，并内置了多种数据分析方法，便于用户评估广告投放效果。

## 功能特性
- 广告数据管理：支持广告活动数据的添加、查询等基本操作
- 数据计算：自动计算单个线索成本、私信转化成本等关键指标
- 数据分析：提供广告花费变动分析等功能
- RESTful API：通过 HTTP 接口提供数据服务
- Web界面：提供简易的前端界面，方便用户操作和查看数据

## 技术栈
- 后端框架：Spring Boot 3.2.10
- 编程语言：Java 17
- 数据访问：Spring Data JPA
- 数据库：MySQL
- 构建工具：Maven
- 前端技术：HTML, Bootstrap, jQuery

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

前端资源位于：
src/main/resources/static/
├── index.html  # 主页面
└── js/main.js  # 前端JavaScript代码

## 安装步骤

### 前提条件
- JDK 17 或更高版本
- Maven 3.6 或更高版本
- MySQL 5.7 或更高版本

### 1. 克隆项目
```bash
git clone <项目仓库地址>
cd Data_Board_Maven_UI
```

### 2. 配置数据库
1. 创建 MySQL 数据库：
   ```sql
   CREATE DATABASE ad_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. 配置数据库连接信息：在 src/main/resources/application.properties 文件中修改数据库连接信息：
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ad_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
   spring.datasource.username=root
   spring.datasource.password=123456
   
   # JPA 配置
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   ```

### 3. 构建和运行项目
使用 Maven 构建并运行项目：
```bash
mvn clean package
java -jar target/Data_Board_Maven_DBS-0.0.1-SNAPSHOT.jar
```
或者直接在开发环境中运行 DataBoardMavenDbsApplication.java 类的 main 方法。

## 使用方法

### Web界面访问
启动项目后，打开浏览器访问：http://localhost:8080/

界面功能：
- 查看所有广告数据列表
- 添加新的广告数据

### API 接口
系统提供了以下 RESTful API 接口：

#### 获取所有广告数据
- URL：/api/ads
- 方法：GET
- 描述：获取数据库中所有的广告活动数据
- 响应示例：
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

#### 添加新的广告数据
- URL：/api/ads
- 方法：POST
- 描述：向数据库中添加新的广告活动数据
- 请求体示例：
  ```json
  {
    "date": "2023-10-03",
    "cost": 1500.0,
    "leadCount": 75,
    "messageCount": 150
  }
  ```
- 响应示例：
  ```json
  {
    "id": 3,
    "date": "2023-10-03",
    "cost": 1500.0,
    "leadCount": 75,
    "messageCount": 150
  }
  ```

## 数据模型

### AdAdvertisement 实体类
广告活动数据模型类，用于存储广告活动的核心数据，并提供数据计算和分析功能。每个实例代表一天的广告活动数据。

主要属性：
- `id`: 主键ID（自动生成）
- `date`: 日期（例如："2023-10-01"）
- `cost`: 广告花费（单位：元）
- `leadCount`: 线索提交个数
- `messageCount`: 私信消息数

## 数据分析功能
AdAdvertisement 实体类提供了数据分析方法，如分析广告花费随日期的变动情况。

## 常见问题解决

### 数据显示异常问题
如果遇到数据显示为"undefined"或"null"的问题，通常是由于前端JavaScript代码中的字段名与后端API返回的JSON格式不匹配导致的。请确保：
1. 前端代码中的字段名与后端实体类的属性名一致
2. AJAX请求中使用正确的参数名（data而不是date）
3. 表单提交的字段名与后端期望的字段名一致

## 贡献指南
1. Fork 项目仓库
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

