import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

group = "com.theonlytails"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation("net.dv8tion:JDA:latest.release")
    implementation("com.github.minndevelopment:jda-ktx:0.9.6-alpha.22")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.github.cdimascio:dotenv-kotlin:6.3.1")

    implementation("org.postgresql", "postgresql", "42.2.2")
    implementation("org.jetbrains.exposed", "exposed-core", "0.39.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.39.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.39.1")

    implementation("ch.qos.logback:logback-classic:1.4.4")
}

application {
    mainClass.set("com.theonlytails.doddlebot.DoddlebotKt")
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.apply {
    jvmTarget = "18"
    freeCompilerArgs = listOf("-Xcontext-receivers")
}
