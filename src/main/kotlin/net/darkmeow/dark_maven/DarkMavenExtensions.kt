package net.darkmeow.dark_maven

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.logging.Logging

var skipWarn = false

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
            if (!skipWarn) {
                Logging.getLogger("MavenDarkMeow").error("[MavenDarkMeow] Skipping: MAVEN_USERNAME or MAVEN_PASSWORD is not set.")
                skipWarn = true
            }
        }
}