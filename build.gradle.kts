
plugins {
    id("java")
    id("war")
}


group = "ru.kpfu.itis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:${properties["springVersion"]}")
    implementation("org.springframework:spring-jdbc:${properties["springVersion"]}")
    implementation("org.springframework:spring-orm:${properties["springVersion"]}")
    implementation("org.springframework:spring-context-support:${properties["springVersion"]}")
    implementation("org.springframework.security:spring-security-core:${properties["springSecurityVersion"]}")
    implementation("org.springframework.security:spring-security-web:${properties["springSecurityVersion"]}")
    implementation("org.springframework.security:spring-security-config:${properties["springSecurityVersion"]}")
    implementation("org.springframework.security:spring-security-taglibs:${properties["springSecurityVersion"]}")
    implementation ("org.springframework.data:spring-data-jpa:2.7.18")
    implementation("org.hibernate:hibernate-core:${properties["hibernateVersion"]}")
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:${properties["hibernateVersion"]}")
    implementation("org.hibernate:hibernate-entitymanager:${properties["hibernateVersion"]}")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:${properties["tomcatVersion"]}")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.freemarker:freemarker:2.3.32")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("com.zaxxer:HikariCP:5.0.1")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    implementation("com.cloudinary:cloudinary-core:1.34.0")
    implementation("com.mchange:c3p0:0.9.5.2")
    implementation("commons-fileupload:commons-fileupload:1.5")
    implementation("com.cloudinary:cloudinary-http44:1.34.0")
}