plugins {
    kotlin("jvm") version "1.9.20"
    `maven-publish`
    signing
}

group = "com.lotsofpixelsstudios"
version = System.getenv("GITHUB_REF")?.removePrefix("refs/tags/") ?: "local"   //use tag name as version

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.9")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.31")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.9")

    //test
    testImplementation(kotlin("test"))
    implementation(group = "org.skyscreamer", name = "jsonassert", version = "1.5.1")
}

java {
    withJavadocJar()
    withSourcesJar()
}

signing {
    gradle.taskGraph.whenReady {
        isRequired = allTasks.any { it is PublishToMavenRepository }
    }

    useInMemoryPgpKeys(
        System.getenv("ORG_GRADLE_PROJECT_signingKey"),
        System.getenv("ORG_GRADLE_PROJECT_signingPassword")
    )

    sign(publishing.publications)
}

kotlin {
    jvmToolchain(11)
}

tasks.test {
    useJUnitPlatform()
    enabled = true
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        register("release", MavenPublication::class) {
            from(components["java"])
            pom {
                name = "monstera"
                description = "A library and environment designed to streamline the development of Minecraft addons."
                url = "https://github.com/LotsOfPixelsStudios/Monstera"
                developers {
                    developer {
                        id = "12rcu"
                        name = "Matthias Klenz"
                    }
                }
                licenses {
                    license {
                        name = "GPL-3.0"
                        url = "https://www.gnu.org/licenses/gpl-3.0.html"
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com:LotsOfPixelsStudios/Monstera.git")
                    developerConnection.set("scm:git:ssh://github.com:LotsOfPixelsStudios/Monstera.git")
                    url.set("https://github.com/LotsOfPixelsStudios/Monstera")
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/LotsOfPixelsStudios/Monstera")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        //maven {
        //    name = "central"
        //    url = uri("https://repo1.maven.org/maven2/")
        //    credentials {
        //        username = System.getenv("MAVEN_USER_NAME")
        //        password = System.getenv("MAVEN_USER_PASSWORD")
        //    }
        //}
    }
}
