# Mall 商城

该项目为[mall](http://www.macrozheng.com) 的学习项目。

# 部署完成进度

- [X] 整合SpringBoot + MyBatis
- [X] Swagger-UI 实现在线API文档
- [X] 整合Redis实现缓存
- [X] SpringSecurity和JWT实现认证和授权
- [X] SpringTask实现定时任务
- [X] Elasticsearch实现商品搜索
- [X] Mongodb实现文档操作
- [X] RabbitMQ实现延迟消息X
- [ ] OSS实现文件上传

启动redis docker命令：
```sh

docker run -p 6379:6379 --name hx-redis -v $PWD/conf/redis.conf:/etc/redis/redis.conf -v redis-data:/data -d redis redis-server  /etc/redis/redis.conf --bind 0.0.0.0 --appendonly yes

```
# 1. 介绍
## 1.1 功能概览

- 商品模块
    - 商品管理
    - 商品分类管理
    - 品牌管理
- 订单模块
    - 订单管理
    - 订单设置
    - 退货申请处理
    - 退货原因设置
- 营销模块
    - 秒杀活动管理
    - 优惠价管理
    - 品牌推荐管理
    - 新品推荐管理
    - 人气推荐管理
    - 专题推荐管理
    - 首页广告管理

## 1.2 技术简介

| 技术             | 版本    | 说明                |
| ---------------- | ------- | ------------------- |
| Spring Boot      | 2.3.0   | 容器+MVC框架        |是
| Spring Security  | 5.1.4   | 认证和授权框架      |
| MyBatis          | 3.4.6   | ORM框架             |
| MyBatisGenerator | 1.3.3   | 数据层代码生成      |
| PageHelper       | 5.1.8   | MyBatis物理分页插件 |
| Swagger-UI       | 2.9.2   | 文档生产工具        |
| Elasticsearch    | 7.6.2   | 搜索引擎            |
| RabbitMq         | 3.7.14  | 消息队列            |
| Redis            | 5.0     | 分布式缓存          |
| MongoDb          | 4.2.5   | NoSql数据库         |
| Docker           | 18.09.0 | 应用容器引擎        |
| Druid            | 1.1.10  | 数据库连接池        |
| OSS              | 2.5.0   | 对象存储            |
| JWT              | 0.9.0   | JWT登录支持         |
| Lombok           | 1.18.6  | 简化对象封装工具    |

# 2. 环境搭建

```shell script
项目结构目录：
─src
│  ├─main
│  │  ├─java
│  │  │  └─hx
│  │  │      └─mall
│  │  │          ├─common # 通用模块
│  │  │          │  ├─api
│  │  │          │  └─utils
│  │  │          ├─component # 存放组件
│  │  │          ├─config    # Java配置
│  │  │          ├─controller # 控制器
│  │  │          ├─dao       # 自定义mapper接口
│  │  │          ├─dto       # 存放自定义传输对象（请求参数，返回结果集等）
│  │  │          ├─mbg       # mbg生成的代码
│  │  │          │  ├─mapper # Mapper接口
│  │  │          │  └─model  # 模型
│  │  │          ├─nosql     # 其他NOSQL工具（mongodb、elasticsearch）
│  │  │          │  ├─elasticsearch
│  │  │          │  │  ├─document
│  │  │          │  │  └─repository
│  │  │          │  └─mongodb
│  │  │          │      ├─document
│  │  │          │      └─repository
│  │  │          └─service # 存放服务类
│  │  │              └─impl #服务接口的实现
│  │  └─resources 资源
│  │      ├─hx
│  │      │  └─mall
│  │      │      └─mbg
│  │      │          └─mapper # mbg生成的mapper.xml
│  │      ├─mapper # 自定义的maaper.xml
│  │      └─META-INF

```
## 2.1 整合SpringBoot + MyBatis
## 2.2 Swagger-UI 实现在线API文档
## 2.3 整合Redis实现缓存
## 2.4 SpringSecurity和JWT实现认证和授权
## 2.5 SpringTask实现定时任务
## 2.6 Elasticsearch实现商品搜索
1. 运行 `elasticasearch`
```
1. 下载下面三个包
搜索引擎：
https://www.elastic.co/cn/downloads/past-releases/elasticsearch-6-2-2
分词模块：
https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.2.2/elasticsearch-analysis-ik-6.2.2.zip
将分词模块放到 D:\Software\elasticsearch-6.2.2\plugins\ik 下
Kibana(搜索引擎客户端):
https://artifacts.elastic.co/downloads/kibana/kibana-6.2.2-windows-x86_64.zip
将分词模块解压到：D:\Software\elasticsearch-6.2.2\plugins\ik
2. 运行 D:\Software\elasticsearch-6.2.2\bin\elasticsearch.bat
3. 运行 D:\Software\kibana-6.2.2-windows-x86_64\bin\kibana.bat
然后可以通过：http://localhost:5601 访问 Kibana


```

2. Spring Data Elasticsearch编写基础



## 2.7 Mongodb实现文档操作
## 2.8 RabbitMQ实现延迟消息
- 生产者
- 消费者
- 交换机
- 队列
> 生产者生产消息，放进交换机中，交换机根据消息中的路由键。将消息发送到不同的队列中。消费者从自己监听的队列中获取消息。

## 2.9 OSS实现文件上传

# 3. 数据库设计

## 3.1 数据库表前缀说明
- cms_*: 内容管理模块相关表(Content Manager Service)
- oms_*: 订单管理模块相关表(Order Manager Service)
- pms_*: 商品模块相关表(Product Manager Service)
- sms_*: 营销模块相关表(Sell manager Service)
- ums_*: 会员模块相关表(User manager Service)

# n. 注意

## 1. `nested exception is org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML.`错误
在低版本的`mybatis-generator-core`依赖中，每次逆向工程生成的表都是追加，而不是删除，这样会造成错误。所以请使用`1.3.7`以后的版本。
并且在generatorConfig.xml中添加插件`<plugin type="org.mybatis.generator.plugins.UnmergeableXmlMappersPlugin"/>`

## 2. 使用wsl2中的docker部署服务，需要执行下面两句设置
---

# 开发完成进度

- [ ] 商品模块
- [ ] 内容模块
- [ ] 订单模块
- [ ] 营销模块
- [ ] 会员模块

## 1. 商品模块
- [X] 商品分类表
- [X] 商品品牌表
- [ ] 商品属性分类表
- [ ] 商品属性表
- [ ] 商品属性值表
- [ ] 商品表
- [ ] 商品SKU表
- [ ] 商品阶梯价格表
- [ ] 商品满减表
- [ ] 商品会员价格表
- [ ] 商品评价表
- [ ] 商品评价回复表
- [ ] 商品审核记录表
- [ ] 商品操作记录表

> (SKU(Stock Keeping Unit)是指库存量单位，SPU(Standard Product Unit)是指标准产品单位。举个例子：iphone xs是一个SPU，而iphone xs 公开版 64G 银色是一个SKU。)

## 2. 内容模块

## 3. 订单模块
## 4. 营销模块
## 5. 会员模块