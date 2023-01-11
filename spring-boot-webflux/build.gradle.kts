import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    id("java")
}

group = "com.quick.start"
version = "unspecified"

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    // 因为 Jedis 暂时提供非阻塞的异步 API ，具体可以看看 ISSUE#713 。所以，如果我们使用 Spring Data Redis 提供的 Redis 响应式操作，
    // 只能暂时使用 Lettuce 或 Redisson 作为 Redis 的客户端。
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
    // 自 spring-data-elasticsearch 的 3.2.0.RELEASE 版本开始，其基于响应式的 WebClient ，封装了请求 Elasticsearch HTTP API 的 DefaultReactiveElasticsearchClient 客户端。
    // 这意味着什么？Spring Data Elasticsearch 开始支持响应式，并且开始使用 Elasticsearch HTTP API。 Spring Data Elasticsearch 之前的版本，使用的是 Elasticsearch TCP API 。
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
