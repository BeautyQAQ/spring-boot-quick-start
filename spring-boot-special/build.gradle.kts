import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.session:spring-session-data-redis:2.7.0")
//    implementation("io.springfox:springfox-swagger2:3.0.0")
//    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
