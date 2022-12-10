package com.theonlytails.doddlebot.commands

import com.theonlytails.doddlebot.CommandAction
import com.theonlytails.doddlebot.Role
import com.theonlytails.doddlebot.dodieYellow
import dev.minn.jda.ktx.events.listener
import dev.minn.jda.ktx.interactions.components.SelectOption
import dev.minn.jda.ktx.interactions.components.row
import dev.minn.jda.ktx.messages.*
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu

// build the selection menu for each role type
fun createRoleSelectMenu(roles: List<Role>, interactionId: String) = roles
    .groupBy(Role::type)
    .map { (roleType, rolesOfType) ->
        StringSelectMenu.create("menu:roles-$roleType:$interactionId")
            .setPlaceholder("${roleType.title} Roles")
            .addOptions(rolesOfType.map { r -> SelectOption(r.roleName, r.id) })
            .build()
    }
    .map(::listOf)
    .map(List<StringSelectMenu>::row)


val roles: CommandAction = {
    val guild = guild
    val member = member

    if (guild != null && member != null) {
        val roleList = when (subcommandName) {
            "add" -> {
                Role.values().asSequence().filter { !member.roles.contains(guild.getRoleById(it.id)) }
            }

            "remove" -> {
                Role.values().asSequence().filter { member.roles.contains(guild.getRoleById(it.id)) }
            }

            else -> emptySequence()
        }.toList()

        reply_(
            embeds = listOf(Embed {
                when (subcommandName) {
                    "add" -> {
                        title = "Add Roles"
                        description = """
                                    Please select which roles to add from each category (you can select multiple).
                                    The roles are added automatically when closing each menu.
                                    """.trimIndent()
                    }

                    "remove" -> {
                        title = "Remove Roles"
                        description = """
                                    Please select which roles to remove from each category (you can select multiple).
                                    The roles are removed automatically when closing each menu.
                                    """.trimIndent()
                    }
                }
                dodieYellow()
            }),
            components = createRoleSelectMenu(roleList, interaction.id)
        ).queue()

        jda.listener<StringSelectInteractionEvent> { menuEvent ->
            if (menuEvent.componentId.startsWith("menu:roles") && menuEvent.componentId.endsWith(interaction.id)) {
                menuEvent.values.forEach { roleId ->
                    val guildRole = guild.getRoleById(roleId)
                        ?: throw IllegalArgumentException("Could not find a role with this ID: $roleId")

                    val actionVerb = when (subcommandName) {
                        "add" -> "Added"
                        "remove" -> "Removed"
                        else -> throw IllegalArgumentException("Got an unexpected subcommand for /roles: $subcommandName")
                    }
                    menuEvent.editMessage_(
                        embeds = menuEvent.message.embeds + listOf(Embed {
                            dodieYellow()
                            title = "$actionVerb a Role!"
                            field(value = "$actionVerb <@&$roleId>!")
                        })
                    ).queue()

                    when (subcommandName) {
                        "add" -> guild.addRoleToMember(member, guildRole).queue()
                        "remove" -> guild.removeRoleFromMember(member, guildRole).queue()
                    }
                }
            }
        }
    }
}
