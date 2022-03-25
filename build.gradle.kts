import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
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
}

application {
    mainClass.set("MainKt")
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.apply {
    jvmTarget = "16"
}