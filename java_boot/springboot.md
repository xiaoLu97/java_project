# 依赖

springboot 2.x区别

- spring-boot-starter-web，非默认的spring-boot-starter-webmvc
- lombok要给版本号

# 业务配置

在控制台注入mapper，需要在Application类上添加@MapperScan，指定mapper接口的包路径
```java
@Autowired
private NewsMapper newsMapper;
```