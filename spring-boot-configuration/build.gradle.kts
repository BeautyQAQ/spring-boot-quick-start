import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")

    /*这里，引入 spring-boot-configuration-processor 依赖的原因是，
    编译项目时，会扫描 @ConfigurationProperties 注解，
    生成 spring-configuration-metadata.json 配置元数据文件给 IDEA。这样在 IDEA 中，可以带来两个好处：
    在 application.yaml 配置文件，添加配置项时，IDEA 会给出提示。
    点击配置项时，可以跳转到对应的 @ConfigurationProperties 注解的配置类
    编译后，可以在 target/META-INF 目录下，看到该文件 --> spring-configuration-metadata.json。*/
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.7.0")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
