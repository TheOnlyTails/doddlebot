import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
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
    implementation("com.github.minndevelopment:jda-ktx:master-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
    implementation("org.litote.kmongo:kmongo:4.5.1")
    implementation("ch.qos.logback:logback-classic:1.2.11")
}

application {
    mainClass.set("com.theonlytails.doddlebot.DoddlebotKt")
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.apply {
    jvmTarget = "17"
    freeCompilerArgs = listOf("-Xcontext-receivers")
}
