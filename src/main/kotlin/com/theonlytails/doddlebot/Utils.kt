package com.theonlytails.doddlebot

import dev.minn.jda.ktx.InlineEmbed
import dev.minn.jda.ktx.interactions.Command
import dev.minn.jda.ktx.interactions.slash
import dev.minn.jda.ktx.interactions.updateCommands
import dev.minn.jda.ktx.onCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.Command
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction
import java.awt.Color

typealias CommandAction = GenericCommandInteractionEvent.() -> Unit

val commandsQueue = mutableMapOf<SlashCommandData, SlashCommandData.() -> Unit>()

fun command(name: String, description: String, builder: SlashCommandData.() -> Unit = {}): SlashCommandData {
    val command = Command(name, description)
    commandsQueue += command to builder
    return command
}

fun JDA.registerCommands() {
    val setup: CommandListUpdateAction.() -> Unit = {
        commandsQueue.forEach { (command, action) ->
            slash(command.name, command.description, action)
        }
    }

    getGuildById(758284094827266048)!!.updateCommands(setup).queue()
//    updateCommands(setup).queue()
}

context(JDA)
infix fun SlashCommandData.calls(action: CommandAction) = onCommand(this.name) { it.action() }


fun String.bold() = "**$this**"
fun String.italic() = "*$this*"
fun String.code() = "`$this`"
fun String.underline() = "__${this}__"

fun InlineEmbed.dodieYellow() {
    color = Color(0xFEF65B).rgb
}