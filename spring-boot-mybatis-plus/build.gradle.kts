import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.31")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
