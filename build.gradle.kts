plugins {
    kotlin("jvm") version "2.0.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
    `maven-publish`
    signing
}

group = "com.lotsofpixelsstudios"
version = System.getenv("GITHUB_REF")?.removePrefix("refs/tags/") ?: "local"   //use tag name as version

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.9")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.12")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.13")

    //test
    testImplementation(kotlin("test"))
    implementation(group = "org.skyscreamer", name = "jsonassert", version = "1.5.1")

    testImplementation("org.jsoup:jsoup:1.17.2")
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

//set the version in the resource files to access it from projects
gradle.taskGraph.whenReady {
    layout
        .projectDirectory
        .asFile
        .resolve("src")
        .resolve("main")
        .resolve("resources")
        .resolve("monstera_version")
        .writeText(version.toString())
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

nexusPublishing {
    repositories {
        sonatype {  //only for users registered in Sonatype after 24 Feb 2021
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username = System.getenv("MAVEN_USER_NAME")
            password = System.getenv("MAVEN_USER_PASSWORD")
        }
    }
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
}
