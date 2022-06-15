import org.springframework.boot.gradle.plugin.SpringBootPlugin

dependencies{
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("mysql:mysql-connector-java:8.0.29")
    // 阿里数据库连接池
    implementation("com.alibaba:druid-spring-boot-starter:1.2.11")
    // 解析客户端操作系统、浏览器等
    implementation("eu.bitwalker:UserAgentUtils:1.21")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.2")
    // 获取系统信息
    implementation("com.github.oshi:oshi-core:6.1.6")
    // velocity代码生成使用模板
    implementation("org.apache.velocity:velocity-engine-core:2.3")
    implementation("com.alibaba.fastjson2:fastjson2:2.0.7")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    // 验证码
    implementation("com.github.penggle:kaptcha:2.3.2")
    // implementation("com.github.hc621311:springboot-swagger3-starter:3.0.0")



    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
