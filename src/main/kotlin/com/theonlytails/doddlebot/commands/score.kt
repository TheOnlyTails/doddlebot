package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.interactions.components.getOption
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.reply_
import net.dv8tion.jda.api.entities.Member
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import net.dv8tion.jda.api.entities.User as JDAUser
import org.jetbrains.exposed.sql.transactions.transaction

val score: CommandAction = {
    val userOption = getOption<JDAUser>("member") ?: user

    val score = transaction {
        User.find(User.Table.discordId eq userOption.idLong).first().score
    }

    reply_(embeds = listOf(
        Embed {
            title = "${userOption.name}'s Score"
            dodieYellow()

            field {
                name = "$score"
            }
            image = userOption.avatarUrl
        }
    ))
}
