package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.*
import dev.minn.jda.ktx.Embed
import dev.minn.jda.ktx.interactions.getOption
import dev.minn.jda.ktx.messages.reply_
import org.litote.kmongo.descending
import org.litote.kmongo.getCollection

val leaderboard: CommandAction = {
    val amount = getOption<Int>("amount") ?: 10

    val scores = doddlebotDatabase.getCollection<User>("scores")
    val users = scores.find().sort(descending(User::score)).take(amount).toList()

    reply_(embed = Embed {
        title = "doddlecord leaderboard"
        dodieYellow()

        users.forEachIndexed { index, user ->
            field {
                name = "${index + 1}. ${user.name}"
                value = "${user.score} points"
            }
        }
    }).queue()
}