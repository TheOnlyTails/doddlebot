package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.client
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.interactions.components.getOption
import dev.minn.jda.ktx.messages.Embed
import dev.minn.jda.ktx.messages.reply_
import io.github.jan.supabase.postgrest.postgrest
import net.dv8tion.jda.api.entities.User as JDAUser

val score: CommandAction = {
    val userOption = getOption<JDAUser>("member") ?: user

    val score = client.postgrest[User.table]
        .select { User::discordId eq userOption.idLong }
        .decodeList<User>()
        .first()
        .score

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
