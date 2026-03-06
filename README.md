# LIS 智能检验结果审核系统

基于 Spring Boot 3 + LangChain4j + PostgreSQL 的智能医学检验结果审核系统。

## 技术栈

- JDK 17
- Spring Boot 3.2.0
- MyBatis 3.0.3
- 阿里千问 DashScope SDK 2.22.9
- PostgreSQL
- Lombok

## 快速开始

### 1. 数据库配置

创建 PostgreSQL 数据库：
```sql
CREATE DATABASE lis_review;
```

### 2. 配置文件

修改 `src/main/resources/application.yml`：
- 数据库连接信息
- 阿里千问 API Key（从阿里云 DashScope 控制台获取）

或设置环境变量：
```bash
export DASHSCOPE_API_KEY=your-api-key
```

### 3. 运行项目

```bash
mvn spring-boot:run
```

## API 接口

### 提交检验结果
```
POST /api/review/submit
Content-Type: application/json

{
  "patientId": "P001",
  "patientName": "张三",
  "testItem": "白细胞计数",
  "testValue": 12.5,
  "unit": "×10^9/L",
  "referenceRange": "3.5-9.5",
  "testDate": "2026-03-02T10:30:00"
}
```

### 审核检验结果
```
POST /api/review/{id}
```

### 获取待审核列表
```
GET /api/review/pending
```

## 项目结构

```
src/main/java/com/lis/review/
├── LisAiReviewApplication.java    # 启动类
├── config/
│   └── LangChainConfig.java       # LangChain4j 配置
├── controller/
│   └── ReviewController.java      # REST API 控制器（含详细注释）
├── service/
│   └── ReviewService.java         # 业务逻辑
├── model/
│   └── TestResult.java            # 实体类
└── mapper/
    └── TestResultMapper.java      # MyBatis Mapper 接口

src/main/resources/
├── mapper/
│   └── TestResultMapper.xml       # MyBatis XML 映射文件
├── application.yml                # 应用配置
└── schema.sql                     # 数据库表结构
```

## 功能特性

- ✅ 检验结果录入
- ✅ AI 智能审核
- ✅ 待审核列表查询
- ✅ PostgreSQL 数据持久化
- ✅ RESTful API 接口

## 后续扩展

- 批量审核功能
- 审核历史记录
- 异常结果预警
- 审核报告导出
- 用户权限管理
