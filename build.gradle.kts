val baseGroup: String by project
val baseVersion: String by project

group = baseGroup
version = baseVersion

plugins {
    kotlin("jvm")
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    withSourcesJar()
}

gradlePlugin {
    plugins {
        create("dark-maven") {
            id = "$baseGroup.dark-maven"
            displayName = "dark-maven"
            implementationClass = "net.darkmeow.dark_maven.DarkMavenPlugin"
        }
    }
}

publishing {
    repositories {
        mavenLocal()
        mavenDarkMeow()
    }
}

fun RepositoryHandler.mavenDarkMeow() {
    arrayOf(System.getenv("MAVEN_USERNAME"), System.getenv("MAVEN_PASSWORD"))
        .filterNotNull()
        .filter { it.isNotEmpty() }
        .takeIf { it.size == 2 }
        ?.also { auth ->
            maven {
                setUrl("https://nekocurit.asia/repository/release/")

                credentials {
                    username = auth[0]
                    password = auth[1]
                }
            }
        }
        ?: run {
            Logging.getLogger("MavenDarkMeow").error("[MavenDarkMeow] Skipping: MAVEN_USERNAME or MAVEN_PASSWORD is not set.")
        }
}