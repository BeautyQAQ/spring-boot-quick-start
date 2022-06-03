# spring-boot-quick-start

## 通过gradle命令创建module

- 首先`mkdir spring-boot-security`创建文件夹
- 进入创建好的目录
- gradle创建module需要一个`settings.gradle.kts`文件，使用`touch settings.gradle.kts`创建(windows powershell命令为`New-Item settings.gradle.kts`)
- 通过`gradle wrapper`将gradle包一层，避免多台机器gradle版本不一致的问题，这是一个比较好的实践
- 通过IDEA选择新建模块->来自现有源代码的模块，导入刚刚创建的module

**这里并未使用上述机制，还是使用了多模块的方案**

- spring组件的统一版本控制由`id("org.springframework.boot") version "2.6.4"`插件控制

### TDD-测试驱动开发

**测试先行**

- 第一步，先引入测试
- jupiter就是junit5
- org.junit.jupiter:junit-jupiter-api--------------->编译期间提供的api
- org.junit.jupiter:junit-jupiter-engine------------>api具体的实现
- 进行**冒烟测试**

### spring-boot-security
