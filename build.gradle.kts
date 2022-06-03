plugins {
    java
    checkstyle
    id("org.springframework.boot") version "2.7.0"
}

subprojects{
    apply{
        plugin("org.springframework.boot")
    }
}

allprojects{

    group = "com.quick.start"
    version = "unspecified"

    apply{
        plugin("java")
        plugin("checkstyle")
    }

    repositories {
        // 依赖使用阿里云 maven 源
        maven {
            setUrl("https://maven.aliyun.com/repository/public/")
        }
        maven {
            setUrl("https://maven.aliyun.com/repository/spring/")
        }
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.8.2"))
        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    // 指定Java的构建版本，由Java插件提供
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    checkstyle {
        maxWarnings = 0
        toolVersion = "10.3"
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }
}
