package com.theonlytails.doddlebot

import com.theonlytails.doddlebot.commands.rules
import dev.minn.jda.ktx.SLF4J
import dev.minn.jda.ktx.light
import io.github.cdimascio.dotenv.dotenv

val jda = light(dotenv()["DODDLEBOT_TOKEN"], enableCoroutines = true)
val logger by SLF4J

fun main() {
    jda.apply {
        command("rules", "The server rules") called rules
    }
}