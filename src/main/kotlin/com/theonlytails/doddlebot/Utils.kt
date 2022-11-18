package com.theonlytails.doddlebot

import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.Command
import dev.minn.jda.ktx.messages.InlineEmbed
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import java.awt.Color

typealias CommandAction = GenericCommandInteractionEvent.() -> Unit

val commandsQueue = mutableMapOf<SlashCommandData, SlashCommandData.() -> Unit>()

fun command(name: String, description: String, builder: SlashCommandData.() -> Unit = {}): SlashCommandData {
    val command = Command(name, description)
    commandsQueue += command to builder
    return command
}

fun JDA.registerCommands() {
    commandsQueue.forEach { (command, action) ->
        upsertCommand(
            Commands.slash(command.name, command.description).apply(action)
        ).queue()
    }
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
