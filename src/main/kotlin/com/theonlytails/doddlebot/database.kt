package com.theonlytails.doddlebot

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

val client = createSupabaseClient("https://rbvnxftktiwfwzlhqbvj.supabase.co", dotenv["SUPABASE_PASSWORD"]) {
    install(Postgrest)
}

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class) val id: UUID = UUID.randomUUID(),
    val name: String,
    val score: Int = 0,
    val discordId: Long
)

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())

}
