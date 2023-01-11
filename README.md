# spring-boot-quick-start

关于Java后端开发以及spring boot各个组件的学习记录仓库

## 通过gradle命令创建module

- 首先`mkdir spring-boot-security`创建文件夹
- 进入创建好的目录
- gradle创建module需要一个`settings.gradle.kts`文件，使用`touch settings.gradle.kts`创建(windows powershell命令为`New-Item settings.gradle.kts`)
- 通过`gradle wrapper`将gradle包一层，避免多台机器gradle版本不一致的问题，这是一个比较好的实践
- 通过IDEA选择新建模块->来自现有源代码的模块，导入刚刚创建的module
- spring组件的统一版本控制由`id("org.springframework.boot") version "2.x.x"`插件控制

**这里并未使用上述机制，还是使用了多模块的方案**

### TDD-测试驱动开发

**测试先行**

- 第一步，先引入测试
- jupiter就是junit5
- org.junit.jupiter:junit-jupiter-api  --------------->  编译期间提供的api
- org.junit.jupiter:junit-jupiter-engine  ------------>  api具体的实现
- 进行**冒烟测试**

**git hooks**

- 在项目中创建`githooks`文件夹，然后创建`pre-commit`文件，键入提交前置检查
- 给`pre-commit`文件赋予`chmod 755`权限，变成可执行文件
- 有两种方式安装这个hooks，一：`cp githooks/pre-commit .git/hooks`，但是每次修改了hooks脚本之后需要重新执行这个命令
- 二：使用`git config core.hooksPath githooks`命令

**这里并没有使用上述hooks机制**

### spring-boot-ddd

- 使用CQRS-命令查询职责分离的模式下，面向领域的逻辑代码应该使用JPA这样的ORM框架，更有利于代码的建模
- 对于面向数据的查询代码，则使用MyBatis，更有利于SQL编写和性能优化
- 对于一个大型应用，这两者应该是可以同时存在的，他们分别负责提供不同场景下的持久化支持
- jpa在反序列化时需要一个空的构造函数
- 测试不应依赖我们本地的数据库环境，两个方案，其一：使用spring的H2数据库，这是个in memory的数据库，好处是比较简单不需要额外依赖，缺点是它毕竟是另一种数据库，和生产不一致，语法支持也不一样
- 其二：使用test container框架，它会帮助我们测试时启动一个本地的docker对应的容器，测试期间直接连接这个容器
- spring自动注入时推荐使用构造器注入，使用final修饰需要注入的bean，防止忘记添加到构造器中
- jpa的open in view属性需要设置为false，它会为每个请求开启一个事务，会覆盖我们声明式事务的配置
- 无需给应用服务编写完整的单元测试，通常来说application service会是非常薄的一层，业务逻辑不多，业务变复杂要么会放入聚合根中，要么引入另外一个领域服务，并且这一层测试编写起来很麻烦，需要mock很多上下游的bean，并且意义不大

### spring-boot-security


### gradle

- 刷新依赖`./gradlew build --refresh-dependencies`

### 链接远程docker测试

修改用户文件夹下`.testcontainers.properties`文件，内容为
```properties
#Modified by Testcontainers
#Fri Jun 03 22:07:27 CST 2022
docker.client.strategy=org.testcontainers.dockerclient.NpipeSocketClientProviderStrategy
docker.host=tcp://111.111.111.111:2375
```

### 跟随芋道源码学习
- [x] [芋道 Spring Boot 快速入门](https://www.iocoder.cn/Spring-Boot/quick-start/?github) --> [跳转到对应模块](./spring-boot-quick-introduction)
- [x] [Maven 最全教程，看了必懂](https://www.iocoder.cn/Fight/Maven-most-complete-tutorial-read-must-understand/?self)
- [x] [芋道 Spring Boot 配置文件入门](https://www.iocoder.cn/Spring-Boot/config-file/?self) --> [跳转到对应模块](./spring-boot-configuration/README.md)
- [x] [芋道 Spring Boot SpringMVC 入门](https://www.iocoder.cn/Spring-Boot/SpringMVC/?self) --> [跳转到对应模块](./spring-boot-springmvc)
- [x] [芋道 Spring Boot 自动配置原理](https://www.iocoder.cn/Spring-Boot/autoconfigure/?self)
- [x] [芋道 Spring Boot 单元测试 Test 入门](https://www.iocoder.cn/Spring-Boot/Unit-Test/?self)
- [ ] [芋道 Spring Boot 持续交付 Jenkins 入门](https://www.iocoder.cn/Spring-Boot/Jenkins/?self)
- [ ] [跟着 Github 学习 Restful HTTP API 的优雅设计](https://www.iocoder.cn/Fight/Learn-Restful-HTTP-API-design-from-Github/)
- [ ] [芋道 Spring Boot 数据库连接池入门](https://www.iocoder.cn/Spring-Boot/datasource-pool/)
- [x] [芋道 Spring Boot MyBatis 入门](https://www.iocoder.cn/Spring-Boot/MyBatis/) --> [跳转到对应模块](./spring-boot-mybatis/README.md)
- [x] [芋道 Spring Boot MyBatis-Plus 入门](https://www.iocoder.cn/Spring-Boot/MyBatis/) --> [跳转到对应模块](./spring-boot-mybatis-plus/README.md)
- [x] [芋道 Spring Boot TkMyBatis 入门（不好用）](https://www.iocoder.cn/Spring-Boot/MyBatis/) --> [跳转到对应模块](./spring-boot-tkmybatis)
- [x] [小谈 Java 单元测试](https://www.iocoder.cn/Fight/A-little-bit-about-Java-unit-testing/?self)
- [x] [谈谈单元测试](https://www.iocoder.cn/Architecture/talk-about-java-unit-test/?self)
- [x] [springboot test](https://www.iocoder.cn/Fight/72b19e24a602/?self)
- [ ] [JAVA 拾遗 —— 关于 SPI 机制](https://www.iocoder.cn/Fight/xuma/spi/?self)
- [ ] [Spring Boot 中 SPI 机制](https://www.iocoder.cn/Fight/SPI-mechanism-in-Spring-Boot/?self)
- [ ] [Spring Boot 知识清单](https://www.iocoder.cn/Fight/Give-you-a-list-of-Spring-Boot-knowledge/?self)
- [ ] [JUnit4 单元测试入门教程](https://www.iocoder.cn/Fight/JUnit4-unit-testing-tutorial/?self)
- [ ] [JUnit assertThat 用法](https://www.iocoder.cn/Fight/JUnit-assertThat-usage/?self)
- [ ] [芋道 Spring Boot 响应式 WebFlux 入门](https://www.iocoder.cn/Spring-Boot/WebFlux/?self)
- [ ] [注解处理器是干嘛的](https://www.iocoder.cn/Fight/What-does-the-annotation-handler-do/?self)
- [ ] [JSR269 插件化注解API](https://blog.whatakitty.com/JSR269%E6%8F%92%E4%BB%B6%E5%8C%96%E6%B3%A8%E8%A7%A3API.html)
- [x] [芋道 Spring Boot 消除冗余代码 Lombok 入门](https://www.iocoder.cn/Spring-Boot/Lombok/?github)
- [x] [芋道 Spring Boot 对象转换 MapStruct 入门](https://www.iocoder.cn/Spring-Boot/MapStruct/?github)
- [ ] [芋道 Spring Boot 响应式 WebFlux 入门](https://www.iocoder.cn/Spring-Boot/WebFlux/?github)
- [ ] [使用 Reactor 进行反应式编程](https://www.iocoder.cn/Spring-Boot/WebFlux/?github)
- [ ] [芋道 Spring Boot MongoDB 入门](https://www.iocoder.cn/Spring-Boot/MongoDB/?self) --> [跳转到对应模块](./spring-boot-middleware)
- [ ] [芋道 Spring Boot JPA 入门](https://www.iocoder.cn/Spring-Boot/JPA/?self)
- [ ] [芋道 Spring Boot Redis 入门](https://www.iocoder.cn/Spring-Boot/Redis/?self)
- [ ] [芋道 Spring Boot Elasticsearch 入门](https://www.iocoder.cn/Spring-Boot/Elasticsearch/?self)
