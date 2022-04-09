package com.theonlytails.doddlebot

import com.theonlytails.doddlebot.commands.*
import dev.minn.jda.ktx.SLF4J
import dev.minn.jda.ktx.interactions.choice
import dev.minn.jda.ktx.interactions.option
import dev.minn.jda.ktx.interactions.subcommand
import dev.minn.jda.ktx.light
import dev.minn.jda.ktx.listener
import dev.minn.jda.ktx.messages.SendDefaults
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.interactions.commands.Command
import com.theonlytails.doddlebot.commands.roles as rolesCmd

val dotenv = dotenv()

fun main() {
    val logger by SLF4J

    val jda = light(dotenv["DODDLEBOT_TOKEN"], enableCoroutines = true)
    SendDefaults.ephemeral = true

    jda.listener<ReadyEvent> {
        it.jda.apply {
            command("rules", "the server rules") calls rules

            command("helplines", "A list of helpful numbers in times of need.") calls helpLines

            command("leaderboard", "A list of the most active users.") {
                option<Int>("amount", "how many places to show") {
                    setMinValue(1)
                    setMaxValue(25)
                }
            } calls leaderboard

            command("roles", "Open the roles menu and add yourself some roles!") {
                subcommand("remove", "Open the roles menu and remove roles you don't want.") calls rolesRemove
            } calls rolesCmd

            command("serious", "Gain/remove access to the #serious channel.") calls serious

            command("serverinfo", "Get some info about the server") calls serverInfo

            registerCommands()
        }
    }
}