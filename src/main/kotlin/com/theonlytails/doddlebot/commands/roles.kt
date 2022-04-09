package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.Role
import com.theonlytails.doddlebot.roleList
import dev.minn.jda.ktx.interactions.SelectMenu
import dev.minn.jda.ktx.interactions.row
import dev.minn.jda.ktx.listener
import dev.minn.jda.ktx.messages.reply_
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent
import net.dv8tion.jda.api.interactions.components.selections.SelectOption

// build the selection menu for each role type
fun createRoleSelectMenus(interactionId: String) = roleList
    .groupBy(Role::type)
    .map { (type, roles) ->
        row(
            SelectMenu(
                "menu:${type.name.lowercase()}-roles:$interactionId",
                placeholder = "${type.title} Roles",
                options = roles.map { SelectOption.of(it.name, it.id.toString()) },
                valueRange = 0..roles.size,
            )
        )
    }

val roles: CommandAction = {
    val guild = guild
    val member = member

    if (guild != null && member != null) {
        // build the selection menu for each role type

        reply_(
            """Please select which roles to add from each category (you can select multiple).
                |The roles are added automatically when closing each menu.
                |""".trimMargin(),
            components = createRoleSelectMenus(interaction.id)
        ).queue()

        jda.listener<SelectMenuInteractionEvent> { menuEvent ->
            if (menuEvent.componentId.startsWith("menu:") && menuEvent.componentId.endsWith("-roles:$id")) {
                menuEvent.selectedOptions.forEach { role ->
                    // get the role's id and add it to the user.
                    val (roleName, _, roleId) = roleList.find { it.name == role.value }!!

                    guild.addRoleToMember(member, guild.getRoleById(roleId)!!).queue()
                }
            }
        }
    }
}

val rolesRemove: CommandAction = {
    val guild = guild
    val member = member

    if (guild != null && member != null) {
        reply_("""Please select which roles to remove from each category (you can select multiple).
                |The roles are removed automatically when closing each menu.
                |""".trimMargin(),
            components = createRoleSelectMenus(interaction.id)
        ).queue()

        jda.listener<SelectMenuInteractionEvent> { menuEvent ->
            if (menuEvent.componentId.startsWith("menu:") && menuEvent.componentId.endsWith("-roles:$id")) {
                menuEvent.selectedOptions.forEach { role ->
                    // get the role's id and add it to the user.
                    val (_, _, roleId) = roleList.find { it.name == role.value }!!

                    guild.removeRoleFromMember(member, guild.getRoleById(roleId)!!).queue()
                }
            }
        }
    }
}