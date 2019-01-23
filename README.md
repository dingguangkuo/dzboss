# DekServer



## 项目设计

```

.
├── db  -- sql文件
├── mvnw
├── mvnw.cmd
├── pom.xml  -- 项目依赖
└── src
    ├── main
    │   ├── java
    │   │   └── cn
    │   │       └── tycoding
    │   │           ├── SpringbootApplication.java  -- Spring Boot启动类
    │   │           ├── controller  -- MVC-WEB层
    │   │           ├── entity  -- 实体类
    │   │           ├── interceptor  -- 自定义拦截器
    │   │           ├── mapper  -- mybatis-Mapper层接口
    │   │           └── service  -- service业务层
    │   └── resources  -- Spring Boot资源文件
    │       ├── application.yml  -- Spring Boot核心配置文件
    │       ├── mapper  -- Mybatis Mapper层配置文件
    │       ├── static  -- 前端静态文件
    │       └── templates  -- Thymeleaf模板引擎识别的HTML页面目录
    └── test  -- 测试文件
```



## Spring Boot应用启动器

​	Spring Boot提供了很多应用启动器，分别用来支持不同的功能，说白了就是pom.xml中的依赖配置，因为Spring Boot的自动化配置特性，我们并不需再考虑项目依赖版本问题，使用Spring Boot的应用启动器，它能自动帮我们将相关的依赖全部导入到项目中。

我们这里介绍几个常见的应用启动器：

- spring-boot-starter: Spring Boot的核心启动器，包含了自动配置、日志和YAML
- spring-boot-starter-aop: 支持AOP面向切面编程的功能，包括spring-aop和AspecJ
- spring-boot-starter-cache: 支持Spring的Cache抽象
- spring-boot-starter-artermis: 通过Apache Artemis支持JMS（Java Message Service）的API
- spring-boot-starter-data-jpa: 支持JPA
- spring-boot-starter-data-solr: 支持Apache Solr搜索平台，包括spring-data-solr
- spring-boot-starter-freemarker: 支持FreeMarker模板引擎
- spring-boot-starter-jdbc: 支持JDBC数据库
- spring-boot-starter-Redis: 支持Redis键值储存数据库，包括spring-redis
- spring-boot-starter-security: 支持spring-security
- spring-boot-starter-thymeleaf: 支持Thymeleaf模板引擎，包括与Spring的集成
- spring-boot-starter-web: 支持全栈式web开发，包括tomcat和Spring-WebMVC
- spring-boot-starter-log4j: 支持Log4J日志框架
- spring-boot-starter-logging: 引入Spring Boot默认的日志框架Logback



## Spring Boot项目结构设计

Spring Boot项目（即Maven项目），当然拥有最基础的Maven项目结构。除此之外：

1. Spring Boot项目中不包含webapp(webroot)目录。
2. Spring Boot默认提供的静态资源目录需要置于classpath下，且其下的目录名称要符合一定规定。
3. Spring Boot默认不提倡用XML配置文件，主张使用YML作为配置文件格式，YML有更简洁的语法。当然也可以使用.properties作为配置文件格式。
4. Spring Boot官方推荐使用Thymeleaf作为前端模板引擎，并且Thymeleaf默认将templates作为静态页面的存放目录（由配置文件指定）。
5. Spring Boot默认将`resources`作为静态资源的存放目录，存放前端静态文件、项目配置文件。
6. Spring Boot规定`resources`下的子级目录名要符合一定规则，一般我们设置`resources/static`为前端静态（JS,CSS）的存放目录；设置`resources/templates`作为HTML页面的存放目录。
7. Spring Boot指定的Thymeleaf模板引擎文件目录`/resources/templates`是受保护的目录，想当与之前的WEB-INF文件夹，里面的静态资源不能直接访问，一般我们通过Controller映射访问。
8. 建议将Mybatis-Mapper的XML映射文件放于`resources/`目录下，我这里设为`resources/mapper`目录，且`src/main/java/Mapper`下的Mapper层接口要使用`@Mapper`注解标识，不然mybatis找不到接口对应的XML映射文件。
9. `SpringBootApplication.java`为项目的启动器类，项目不需要部署到Tomcat上，由SpringBoot提供的服务器部署项目（运行启动器类即可）；且SpringBoot会自动扫描该启动器同级和子级下用注解标识的Bean。
10. Spring Boot不建议使用JSP页面，如果想使用，请自行百度解决办法。
11. 上面说了Spring Boot提供的存放HTML静态页面的目录`resources/templates`是受保护的目录，访问其中的HTML页面要通过Controller映射，这就间接规定了你需要配置Spring的视图解析器，且Controller类不能使用`@RestController`标识。



## 读取配置文件信息

```
@Autowired
private Environment environment;
String url = environment.getProperty("url");
```

