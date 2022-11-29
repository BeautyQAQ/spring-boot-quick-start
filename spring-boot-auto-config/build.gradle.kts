import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
    id("maven-publish")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")
}

/**
 * 发布到本地maven仓库
 * 需要在前面添加 maven-publish 插件
 */
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.quick.start"
            artifactId = "spring-boot-auto-config"
            version = "unspecified"

            from(components["java"])
        }
    }
}
