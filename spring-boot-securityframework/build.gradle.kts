import org.springframework.boot.gradle.plugin.SpringBootPlugin

dependencies{
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-devtools")

    implementation("mysql:mysql-connector-java:8.0.29")
    // 阿里数据库连接池
    implementation("com.alibaba:druid-spring-boot-starter:1.2.11")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.2")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("com.alibaba:fastjson:2.0.9")
    // 验证码
    implementation("com.github.whvcse:easy-captcha:1.6.2")
    // 浏览器信息工具类
    implementation("eu.bitwalker:UserAgentUtils:1.21")
    // IP
    implementation("org.lionsoul:ip2region:2.6.5")
    // 获取系统信息
    implementation("com.github.oshi:oshi-core:6.2.2")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
