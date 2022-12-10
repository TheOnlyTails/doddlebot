package com.theonlytails.doddlebot.events

import com.theonlytails.doddlebot.User
import com.theonlytails.doddlebot.client
import com.theonlytails.doddlebot.getBy
import dev.minn.jda.ktx.events.listener
import io.github.jan.supabase.postgrest.postgrest
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.MessageType
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

fun JDA.updateScore() = listener<MessageReceivedEvent> {
    val usersTable = client.postgrest["users"]

    val user = usersTable.getBy(User::discordId, it.author.idLong)

    if (!it.author.isBot &&
        it.member?.roles?.contains(it.guild.getRoleById(membersRoleId)) == true &&
        it.message.type == MessageType.DEFAULT
    ) {
        if (user == null) {
            usersTable.insert(User(name = it.author.name, discordId = it.author.idLong))
        }
    }

    usersTable.update(update = {
        User::score setTo (user?.score ?: 0) + 1
    }, filter = { User::discordId eq it.author.idLong })
}
