package com.theonlytails.doddlebot.events

import com.theonlytails.doddlebot.Role
import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.dodieYellow
import com.theonlytails.doddlebot.jda
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.SendDefaults.embeds
import dev.minn.jda.ktx.messages.reply_
import dev.minn.jda.ktx.messages.send
import net.dv8tion.jda.api.entities.MessageType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.jetbrains.exposed.sql.transactions.transaction

const val membersRoleId = 337015244050399242

fun newMember() {
    jda.listener<MessageReceivedEvent> {
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
