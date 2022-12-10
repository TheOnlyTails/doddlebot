package com.theonlytails.doddlebot.events

import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.reply_
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.MessageType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

const val membersRoleId = 337015244050399242

fun JDA.newMember() {
    listener<MessageReceivedEvent> {
        if (
            !it.author.isBot &&
            it.message.type == MessageType.DEFAULT &&
            it.member?.roles?.contains(it.guild.getRoleById(membersRoleId)) == false
        ) {
            it.guild.addRoleToMember(
                it.author,
                it.guild.getRoleById(membersRoleId) ?: throw NullPointerException("No such role exists")
            ).queue()

            it.message.reply_(embeds = listOf(Embed {
                dodieYellow()

                title = "Welcome to doddlecord!"

                description = """
                    I've made you a member!
                    Please don't forget to read the <#342823617333166080>!
                """.trimIndent()
            })).queue()
        }
    }
}
