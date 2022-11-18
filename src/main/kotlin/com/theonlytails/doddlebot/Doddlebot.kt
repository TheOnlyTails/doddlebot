package com.theonlytails.doddlebot

import com.theonlytails.doddlebot.commands.*
import com.theonlytails.doddlebot.events.newMember
import com.theonlytails.doddlebot.events.updateScore
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.interactions.commands.option
import dev.minn.jda.ktx.interactions.commands.subcommand
import dev.minn.jda.ktx.jdabuilder.light
import dev.minn.jda.ktx.messages.SendDefaults
import dev.minn.jda.ktx.util.SLF4J
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.session.ReadyEvent
import org.jetbrains.exposed.sql.Database
import com.theonlytails.doddlebot.commands.roles as rolesCmd

val dotenv = dotenv {
    ignoreIfMissing = true
}

val jda = light(dotenv["DODDLEBOT_TOKEN"], enableCoroutines = true)
val logger by SLF4J

fun main() {
    SendDefaults.ephemeral = true

    Database.connect(
        url = "jdbc:postgresql://db.ktsiennzyfninrpbbyzm.supabase.co:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = dotenv["SUPABASE_PASSWORD"]
    )

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

            command("score", "Shows your current score.") {
                option<User>("member", "the member") {
                    isRequired = false
                }
            } calls score

            command("roles", "Commands related to roles") {
                subcommand("add", "Open the roles menu and give yourself some roles!")
                subcommand("remove", "Open the roles menu and remove roles you don't want.")
            } calls rolesCmd

            command("serious", "Gain/remove access to the #serious channel.") calls serious

            command("serverinfo", "Get some info about the server") calls serverInfo

            registerCommands()

            newMember()
            updateScore()
        }
    }
}
