package com.theonlytails.doddlebot

import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.commands.Command
import dev.minn.jda.ktx.messages.InlineEmbed
import io.github.jan.supabase.postgrest.query.PostgrestBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction
import java.awt.Color
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property

typealias CommandAction = suspend GenericCommandInteractionEvent.() -> Unit

suspend fun <T : Any> PostgrestBuilder.getBy(columnName: String, expectedValue: T) =
    select { eq(columnName, expectedValue) }.decodeList<User>().singleOrNull()

suspend fun <T : Any, V> PostgrestBuilder.getBy(columnName: KProperty<V>, expectedValue: T) =
    select { eq(columnName.name, expectedValue) }.decodeList<User>().singleOrNull()

fun String.bold() = "**$this**"
fun String.italic() = "*$this*"
fun String.code() = "`$this`"
fun String.underline() = "__${this}__"

fun InlineEmbed.dodieYellow() {
    color = Color(0xFEF65B).rgb
}
