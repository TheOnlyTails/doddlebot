package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.bold
import com.theonlytails.doddlebot.dodieYellow
import com.theonlytails.doddlebot.underline
import dev.minn.jda.ktx.Embed
import dev.minn.jda.ktx.messages.reply_

val serverInfo: CommandAction = {
    val guild = guild
    if (guild != null) {
        reply_(embed = Embed {
            title = "${guild.name} Details".bold().underline()
            thumbnail = guild.iconUrl
            dodieYellow()

            field("Members", "${guild.members.count { !it.user.isBot }} Members")
            field("Bots", "${guild.members.count { it.user.isBot }} Bots")
            field(
                "Channels", """${guild.channels.count { it.type.isAudio }} Voice & Stage 
                |${guild.channels.count { it.type.isMessage }} Text
                |${guild.channels.count { it.type.isThread }} Threads
                |""".trimMargin()
            )
            field {
                name = "Mods"
                value = guild.members
                    .filter { it.roles.any { role -> role.name == "Mods" } }
                    .joinToString(", ") { "<@${it.user.id}>" }
            }
            field {
                name = "Manager Joshes"
                value = guild.members
                    .filter { it.roles.any { role -> role.name == "Manager joshes" } }
                    .joinToString(", ") { "<@${it.user.id}>" }
            }
        }).queue()
    }
}