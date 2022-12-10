package com.theonlytails.doddlebot

import dev.minn.jda.ktx.messages.InlineEmbed
import io.github.jan.supabase.postgrest.query.PostgrestBuilder
import net.dv8tion.jda.api.events.interaction.command.GenericCommandInteractionEvent
import java.awt.Color
import kotlin.reflect.KProperty

typealias CommandAction = suspend GenericCommandInteractionEvent.() -> Unit

suspend fun <T : Any> PostgrestBuilder.getBy(columnName: String, expectedValue: T) =
    select { eq(columnName, expectedValue) }.decodeList<User>().singleOrNull()

suspend fun <T : Any, V> PostgrestBuilder.getBy(columnName: KProperty<V>, expectedValue: T) =
    getBy(columnName.name, expectedValue)

fun String.bold() = "**$this**"
fun String.italic() = "*$this*"
fun String.code() = "`$this`"
fun String.underline() = "__${this}__"

fun InlineEmbed.dodieYellow() {
    color = Color(0xFEF65B).rgb
}
