import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
