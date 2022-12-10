import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    application
}

group = "com.theonlytails"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation("net.dv8tion:JDA:5.0.0-beta.1")
    implementation("com.github.minndevelopment:jda-ktx:0.10.0-beta.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.0")

    implementation("io.github.jan-tennert.supabase:postgrest-kt:0.7.0-beta-1")
    implementation("io.ktor:ktor-client-okhttp:2.2.1")

    implementation("ch.qos.logback:logback-classic:1.4.5")
}

application {
    mainClass.set("com.theonlytails.doddlebot.DoddlebotKt")
}

tasks {
    val fatJar = register<Jar>("fatJar") {
        dependsOn.addAll(listOf("compileJava", "compileKotlin", "processResources")) // We need this for Gradle optimization to work
        archiveClassifier.set("standalone") // Naming the jar
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest { attributes(mapOf("Main-Class" to application.mainClass)) } // Provided we set it up in the application plugin configuration
        val sourcesMain = sourceSets.main.get()
        val contents = configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) } +
                sourcesMain.output
        from(contents)
    }
    build {
        dependsOn(fatJar) // Trigger fat jar creation during build
    }
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.apply {
    jvmTarget = "18"
    freeCompilerArgs = listOf("-Xcontext-receivers")
}
