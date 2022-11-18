package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.interactions.components.getOption
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.reply_
import org.jetbrains.exposed.sql.transactions.transaction

val leaderboard: CommandAction = {
    val amount = getOption<Int>("amount") ?: 10

    transaction {
        val users = User.all().take(amount).sortedBy { it.score }

        reply_(embeds = listOf(Embed {
            title = "doddlecord leaderboard"
            dodieYellow()

            users.forEachIndexed { index, user ->
                field {
                    name = "${index + 1}. ${user.name}"
                    value = "${user.score} points"
                }
            }
        })).queue()
    }
}
