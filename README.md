![logo](https://minio.pigx.vip/oss/2022/08/T4LHAz.svg)
##  🔥项目介绍
    由FxzCloud提供基础服务

FCloud 全端代码开源，支持RBAC 动态权限、SaaS多租户系统。致力于打造成一款具有影响力的微服务开发平台。


## 🪜项目结构
```lua
fxz-mall 
    ├── mall-member -- 会员模块
    ├── mall-order -- 订单模块
    ├── mall-product -- 商品模块
    ├── mall-promotion -- 营销活动模块
    ├── mall-search -- es检索模块
    ├── mall-xxlJob -- xxlJob定时任务
    ├── support -- 多用户体系支持

```
```lua
fxz-cloud 
    ├── fxz-auth -- 认证服务器
    ├── fxz-common -- 通用工具封装
       ├── fxz-common-canal -- 针对canal封装
       ├── fxz-common-captcha -- 验证码封装
       ├── fxz-common-core -- web基础配置
       ├── fxz-common-data-permission -- 数据权限封装
       ├── fxz-common-database -- 多数据源
       ├── fxz-common-dependencies -- 依赖管理
       ├── fxz-common-es -- es配置
       ├── fxz-common-file -- 文件管理模块
       ├── fxz-common-gateway -- 动态网关
       ├── fxz-common-idempotency -- 幂等控制组件
       ├── fxz-common-jackson -- Json序列化配置
       ├── fxz-common-lock -- 分布式锁模块
       ├── fxz-common-log -- 日志配置
       ├── fxz-common-mp -- mybatis-plus配置
       ├── fxz-common-mq -- 消息队列封装
          ├── fxz-common-mq-rabbit -- rabbitMq消息队列
          ├── fxz-common-mq-redis -- redis轻量级消息队列
       ├── fxz-common-redis -- 多级缓存、分布式锁模块
       ├── fxz-common-seata -- 分布式事务
       ├── fxz-common-security -- 安全模块
       ├── fxz-common-sequence -- 发号器（序列号生成器）
       ├── fxz-common-sms -- 阿里云短信服务
       ├── fxz-common-tenant -- SaaS多租户支持
       ├── fxz-common-websocket  -- websocket
       ├── fxz-common-xxl-job  -- xxl-job配置
    ├── fxz-gateway -- 网关模块
    ├── fxz-generate -- 代码生成器
    ├── fxz-scheduled -- quartz定时任务模块
    ├── fxz-system -- 系统服务
       ├── fxz-system-api -- feign接口
       ├── fxz-system-biz -- 业务模块
    ├── fxz-z-laboratory -- 实验性模块
    ├──_other -- 项目相关文件
       ├── db -- 数据库相关文件
       ├── docker -- docker环境
       ├── es -- es索引信息   
       ├── middleware -- 一些本地启动的中间件
       ├── ui -- 前端代码
          ├── admin-vue2 -- 管理员界面
          ├── mall-uni   -- 商城小程序界面
```

## 🍬说明文档
[🍓🍓🍓配套文档 fxzcloud.gitee.io/docs](https://fxzcloud.gitee.io/docs/)


## 💻系统应用
RBAC&数据权限，角色支持多种数据权限设定，支持自定义进行拓展。
![](https://minio.pigx.vip/oss/2022/09/2f0YJk.png)
SaaS多租户，自定义租户套餐.
![](https://minio.pigx.vip/oss/2022/10/vpuDaz.png)
![](https://minio.pigx.vip/oss/2022/10/TivOOd.png)
![](https://minio.pigx.vip/oss/2022/10/OmxgtF.png)
动态网关，网关支持通过前端动态配置，不需要在nacos中维护路由信息，修改自动加载，无需重启服务。
![](https://minio.pigx.vip/oss/2022/09/PxMBNC.png)

动态数据源&代码生成,支持动态添加数据源与项目基础crud代码生成。
![](https://minio.pigx.vip/oss/2022/09/LpRdJs.png)
![](https://minio.pigx.vip/oss/2022/09/aMfUqv.png)
支持多种登录方式,可自定义进行拓展。
![](https://minio.pigx.vip/oss/2022/09/76RYJu.png)
![](https://minio.pigx.vip/oss/2022/10/DseL2p.png)字典管理
![](https://minio.pigx.vip/oss/2022/09/sj2R4y.png)强退用户
![](https://minio.pigx.vip/oss/2022/09/FKsSGc.png)审计日志

## ![](https://minio.pigx.vip/oss/2022/09/kBIASc.png)🎁商城应用

秒杀活动
![](https://minio.pigx.vip/oss/2022/09/qAFJUh.png)
![](https://minio.pigx.vip/oss/2022/09/89RNFg.png)
优惠券活动
![](https://minio.pigx.vip/oss/2022/09/bHjYjU.png)
![](https://minio.pigx.vip/oss/2022/09/bGSyKP.png)
![](https://minio.pigx.vip/oss/2022/09/tpMtYV.png)
商品检索

![](https://minio.pigx.vip/oss/2022/09/BSJsJU.png)订单管理

![](https://minio.pigx.vip/oss/2022/09/d0EG2h.png)会员管理

## ![](https://minio.pigx.vip/oss/2022/09/S7DRyz.png)
📱移动端展示

支持多种登录方式
![](https://minio.pigx.vip/oss/2022/09/7POcE0.png)
![](https://minio.pigx.vip/oss/2022/09/I86DRb.png)
![](https://minio.pigx.vip/oss/2022/09/I28W9e.png)
![](https://minio.pigx.vip/oss/2022/09/QPGuiC.png)
![](https://minio.pigx.vip/oss/2022/09/DswHAG.png)
![](https://minio.pigx.vip/oss/2022/09/y04bpB.png)
## 🔍说明
脚手架与商城为两个单独项目，功能可按需引入。如仅需脚手架功能，可轻松去除。<br/>
低耦合，秒上手，稳得很！
## ❓其他功能
过于先进，不便展示。欢迎下载源码研究🧐欢迎star
## 🍺加入我们
qq群:932249645
<p>
<img src="https://minio.pigx.vip/oss/2022/07/FcAxsd.jpg" width = "330" height = "500"/>
</p>

## 依赖版本

| 依赖                   | 版本         |
| ---------------------- |------------|
| Spring Boot            | 2.7.0      |
| Spring Cloud           | 2021.0.1   |
| Spring Cloud Alibaba   | 2021.0.1.0 |
| Mybatis Plus           | 3.5.1      |
| hutool                 | 5.8.6      |