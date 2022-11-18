package com.theonlytails.doddlebot.events

import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.User.Table.name
import com.theonlytails.doddlebot.User.Table.score
import com.theonlytails.doddlebot.jda
import com.theonlytails.doddlebot.logger
import dev.minn.jda.ktx.events.listener
import net.dv8tion.jda.api.entities.MessageType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

fun updateScore() {
    jda.listener<MessageReceivedEvent> {
        if (!it.author.isBot &&
            it.member?.roles?.contains(it.guild.getRoleById(membersRoleId)) == true &&
            it.message.type == MessageType.DEFAULT
        ) {
            transaction {
                if (User.find(User.Table.discordId eq it.author.idLong).empty()) {
                    User.new {
                        name = it.author.name
                        discordId = it.author.idLong
                    }
                }
            }

            transaction {
                User.find(User.Table.discordId eq it.author.idLong).forEach { user ->
                    user.score++
                }
            }
        }
    }
}
