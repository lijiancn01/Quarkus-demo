# Quarkus CRM Demo

一个基于 Quarkus 框架的简单 CRM 客户管理系统，采用前后端不分离架构，使用 H2 内存数据库（MySQL 模式）。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 17 | 开发语言 |
| Quarkus | 3.6.4 | Java 云原生框架 |
| Hibernate ORM + Panache | - | 数据持久化层 |
| H2 Database | - | 内存数据库（MySQL 模式） |
| JAX-RS (RESTEasy Reactive) | - | REST API 实现 |
| Bootstrap 5 | - | 前端 UI 框架 |
| jQuery | - | 前端交互库 |
| Maven | 3.x | 构建工具 |

## 系统架构

```
crm-demo/
├── src/main/java/com/crm/demo/
│   ├── entity/              # 数据实体
│   │   ├── Customer.java    # 客户实体
│   │   └── CustomerStatus.java # 客户状态枚举
│   ├── repository/          # 数据访问层
│   │   └── CustomerRepository.java
│   ├── service/             # 业务逻辑层
│   │   └── CustomerService.java
│   ├── resource/            # REST API 层
│   │   └── CustomerResource.java
│   └── config/              # 配置和初始化
│       └── DataInitializer.java
├── src/main/resources/
│   ├── META-INF/resources/  # 静态资源
│   │   └── index.html       # 前端页面
│   ├── application.properties # 应用配置
│   └── import.sql           # 初始化数据脚本（已弃用，使用代码初始化）
└── pom.xml                  # Maven 项目配置
```

### 分层架构说明

1. **Entity 层（实体层）**
   - 定义数据库表结构
   - 使用 Panache 提供的简化 ORM 操作

2. **Repository 层（数据访问层）**
   - 实现 PanacheRepository 接口
   - 提供自定义查询方法

3. **Service 层（业务逻辑层）**
   - 处理业务逻辑
   - 提供事务支持

4. **Resource 层（REST API 层）**
   - 提供 RESTful API 接口
   - 处理 HTTP 请求和响应

5. **前端层**
   - 使用 jQuery + Bootstrap 5
   - 事件委托机制，避免页面跳转问题

## 功能特性

- ✅ 客户列表展示
- ✅ 添加新客户
- ✅ 编辑客户信息
- ✅ 删除客户
- ✅ 客户状态切换（活跃/非活跃）
- ✅ 按姓名搜索客户
- ✅ 客户统计（总数、活跃数、非活跃数）

## 快速开始

### 前置要求

- JDK 17+
- Maven 3.8+

### 启动项目

```bash
# 进入项目目录
cd crm-demo

# 编译并运行（开发模式，支持热加载）
mvn clean compile quarkus:dev

# 或者直接打包运行
mvn package
java -jar target/quarkus-app/quarkus-run.jar
```

### 访问应用

- **应用首页**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui
- **OpenAPI 文档**: http://localhost:8080/openapi

## REST API 文档

### 客户管理 API

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/customers` | 获取所有客户 |
| GET | `/api/customers/{id}` | 根据 ID 获取客户 |
| GET | `/api/customers/search?keyword=xxx` | 搜索客户 |
| GET | `/api/customers/active` | 获取活跃客户 |
| GET | `/api/customers/stats` | 获取客户统计数据 |
| POST | `/api/customers` | 创建新客户 |
| PUT | `/api/customers/{id}` | 更新客户信息 |
| DELETE | `/api/customers/{id}` | 删除客户 |
| PATCH | `/api/customers/{id}/toggle-status` | 切换客户状态 |

### 请求示例

```bash
# 获取所有客户
curl http://localhost:8080/api/customers

# 创建客户
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"name":"张三","email":"zhangsan@example.com","phone":"13800138000","company":"北京科技有限公司","status":"ACTIVE"}'
```

## 数据库配置

应用使用 H2 内存数据库，配置在 `application.properties` 中：

```properties
# H2 数据库（MySQL 模式）
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.url=jdbc:h2:mem:crm;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE

# Hibernate 配置
quarkus.hibernate-orm.database.generation=drop-and-create
```

## 开发模式特性

在开发模式（`quarkus:dev`）下，你可以享受以下特性：

- 热加载：代码修改后自动重新加载
- Dev UI：http://localhost:8080/q/dev
- 实时编译和错误提示
- 数据库管理界面

## 许可证

MIT License
