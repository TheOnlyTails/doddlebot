package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import dev.minn.jda.ktx.interactions.button
import dev.minn.jda.ktx.messages.editMessage
import dev.minn.jda.ktx.messages.editMessage_
import dev.minn.jda.ktx.messages.into
import dev.minn.jda.ktx.messages.reply_
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle

val serious: CommandAction = {
    if (member?.roles?.none { it.name == "Serious chat access" } == true) {
        reply_("Click the button to get access to the <#390287088601399299> channel.",
            components = jda.button(style = ButtonStyle.SUCCESS, label = "Get access") {
                it.guild!!.addRoleToMember(user.id, it.guild!!.getRoleById("962425260026757140")!!).queue()
                it.editMessage_("Your access to the <#390287088601399299> channel has been granted.")
                    .queue()
            }.into()
        ).queue()
    } else {
        reply_("Click the button to revoke your access to the <#390287088601399299> channel.",
            components = jda.button(style = ButtonStyle.DANGER, label = "Revoke access") {
                it.guild!!.removeRoleFromMember(user.id, it.guild!!.getRoleById("962425260026757140")!!).queue()
                it.editMessage_("Your access to the <#390287088601399299> channel has been revoked.").queue()
            }.into()
        ).queue()
    }
}