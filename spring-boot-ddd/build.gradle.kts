import org.springframework.boot.gradle.plugin.SpringBootPlugin

dependencies{
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator") // 提供了健康接检查节点

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
