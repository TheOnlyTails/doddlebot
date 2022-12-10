package com.theonlytails.doddlebot

import com.theonlytails.doddlebot.commands.*
import com.theonlytails.doddlebot.events.newMember
import com.theonlytails.doddlebot.events.updateScore
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.option
import dev.minn.jda.ktx.interactions.commands.slash
import dev.minn.jda.ktx.interactions.commands.subcommand
import dev.minn.jda.ktx.interactions.commands.updateCommands
import dev.minn.jda.ktx.jdabuilder.light
import dev.minn.jda.ktx.messages.SendDefaults
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.session.ReadyEvent
import com.theonlytails.doddlebot.commands.roles as rolesCmd

val dotenv = dotenv {
    ignoreIfMissing = true
}

val jda = light(dotenv["DODDLEBOT_TOKEN"], enableCoroutines = true)

val commands = mapOf(
    "rules" to rules,
    "helplines" to helpLines,
    "leaderboard" to leaderboard,
    "score" to score,
    "roles" to rolesCmd,
    "serious" to serious,
    "serverinfo" to serverInfo
)

fun main() {
    SendDefaults.ephemeral = true

    jda.apply {
        listener<ReadyEvent> {
            updateCommands {
                slash("rules", "the server rules")

                slash("helplines", "A list of helpful numbers in times of need.")

                slash("leaderboard", "A list of the most active users.") {
                    option<Int>("amount", "how many places to show") {
                        setMinValue(1)
                        setMaxValue(25)
                    }
                }

                slash("score", "Shows your current score.") {
                    option<User>("member", "the member") {
                        isRequired = false
                    }
                }

                slash("roles", "Commands related to roles") {
                    subcommand("add", "Open the roles menu and give yourself some roles!")
                    subcommand("remove", "Open the roles menu and remove roles you don't want.")
                }

                slash("serious", "Gain/remove access to the #serious channel.")

                slash("serverinfo", "Get some info about the server")
            }
        }

        commands.forEach { (name, action) ->
            onCommand(name) { it.action() }
        }
        updateScore()
        newMember()
    }
}
