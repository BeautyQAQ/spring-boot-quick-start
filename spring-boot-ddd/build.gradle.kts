import org.springframework.boot.gradle.plugin.SpringBootPlugin

dependencies{
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    testImplementation(platform("org.testcontainers:testcontainers-bom:1.17.2"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator") // 提供了健康接检查节点
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.flywaydb:flyway-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation("org.testcontainers:postgresql")
}
