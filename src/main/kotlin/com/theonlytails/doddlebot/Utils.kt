package com.theonlytails.doddlebot

import dev.minn.jda.ktx.interactions.Command
import dev.minn.jda.ktx.onCommand
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData

typealias CommandAction = GenericCommandInteractionEvent.() -> Unit

fun JDA.command(name: String, description: String, builder: SlashCommandData.() -> Unit = {}) =
    Command(name, description, builder).apply {
        // for test server
        this@command.getGuildById(758284094827266048L)?.upsertCommand(this)
        this@command.upsertCommand(this)
    }

infix fun SlashCommandData.called(action: CommandAction) = jda.onCommand(this.name) { it.action() }

fun String.bold() = "**$this**"
fun String.italic() = "*$this*"
fun String.code() = "`$this`"
fun String.underline() = "__${this}__"
