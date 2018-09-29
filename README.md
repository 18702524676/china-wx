技术选型

1.后端
•1.SpringBoot-2.0.3
•2.Fluent-Validator-1.0.5、HibernateValidator-6.0.10(Java的业务逻辑验证框架) 
•3.Mybatis-1.3.2
•4.Redis
•5.Mysql
•6.Jdk1.8

2.前端
•1.Bootstrap
•2.Mustache
•3.zTree

项目结构
• wx-lib ◦ wx-commons-公共包 ◾ com.wx.commons ◾constant-常量包
◾enums-枚举包
◾tools-分页工具包
◾utils-工具包


◦ wx-core -核心包 ◾ com.wx.core ◾constant-常量包
◾enums-枚举包
◾exception-异常包
◾response-返回对象包
◾utils-工具包
◾validator-验证器包


◦ wx-reids -redis ◾ com.wx.redis ◾cache-缓存包
◾constant-常量包
◾enums-枚举包



• wx-permission ◦ wx-permission-api-权限系统api ◾dto-参数包
◾fallback-微服务回调包（目前不会使用）
◾qo-查询参数包
◾service-暴露接口（目前不会使用）
◾vo-返回视图包

◦ wx-permission-crm-权限系统 ◾ com.wx.permission ◾annotations-自定义注解包
◾aspect-AOP切面包
◾config-配置包
◾constant-常量包
◾controller-controller
◾dao-dao
◾enums-枚举包
◾filter-过滤器包
◾initialize-初始化包
◾Interceptor拦截器包
◾model-持久化对象
◾scheduler-定时任务包
◾service-service
◾utils-工具包
◾validator-验证器包




说明
•1.初始化的用户密码全都是123456
•2.超级管理员账号admin@163.com，密码:123456
•3.访问路径http://xxxx:xxx/permission/signin.jsp
