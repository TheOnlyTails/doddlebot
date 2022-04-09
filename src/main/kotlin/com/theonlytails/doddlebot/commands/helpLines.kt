package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.Embed
import dev.minn.jda.ktx.messages.reply_

val helpLines: CommandAction = {
    reply_(embeds = listOf(
        Embed {
            title = "Helplines"
            field {
                name = "US Helplines"
            }
            field {
                name = "UK Helplines"
            }
            dodieYellow()
        }
    )).queue()
}