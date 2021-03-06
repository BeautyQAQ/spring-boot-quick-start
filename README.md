# spring-boot-quick-start

## 通过gradle命令创建module

- 首先`mkdir spring-boot-security`创建文件夹
- 进入创建好的目录
- gradle创建module需要一个`settings.gradle.kts`文件，使用`touch settings.gradle.kts`创建(windows powershell命令为`New-Item settings.gradle.kts`)
- 通过`gradle wrapper`将gradle包一层，避免多台机器gradle版本不一致的问题，这是一个比较好的实践
- 通过IDEA选择新建模块->来自现有源代码的模块，导入刚刚创建的module
- spring组件的统一版本控制由`id("org.springframework.boot") version "2.6.4"`插件控制

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
