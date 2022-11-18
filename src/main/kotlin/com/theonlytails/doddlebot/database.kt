package com.theonlytails.doddlebot

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.UUID

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<User>(Table)

    var name by Table.name
    var score by Table.score
    var discordId by Table.discordId

    object Table : UUIDTable("users") {
        val name = varchar("name", 40).default("")
        val score = integer("score").default(0)
        val discordId = long("discord_id")
    }
}
