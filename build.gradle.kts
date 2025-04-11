plugins {
    kotlin("jvm") version "2.1.20"
    `maven-publish`
    `java-library`
    id("org.jreleaser") version "1.17.0"
}

group = "com.lotsofpixelsstudios"
version = System.getenv("GITHUB_REF")?.removePrefix("refs/tags/") ?: "0.1-local"   //use tag name as version

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation(group = "com.google.code.gson", name = "gson", version = "2.12.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.18")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-api
    implementation("org.slf4j:slf4j-api:2.0.17")

    //test
    testImplementation(kotlin("test"))
    implementation(group = "org.skyscreamer", name = "jsonassert", version = "1.5.3")

    testImplementation("org.jsoup:jsoup:1.19.1")
}

java {
    withJavadocJar()
    withSourcesJar()
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

tasks.test {
    useJUnitPlatform()
    enabled = true
}

repositories {
    mavenCentral()
}

jreleaser {
    release {
        github {
            skipTag = true
            skipRelease = true
        }
    }
    signing {
        setActive("ALWAYS")
        armored = true
    }
    deploy {
        maven {
            //Portal Publisher API
            mavenCentral {
                register("sonatype") {
                    setActive("ALWAYS")
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository(layout.buildDirectory.dir("staging-deploy").get().asFile.path)
                }
            }
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
                        name = "Apache 2.0"
                        url = "http://www.apache.org/licenses/"
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
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }
}
