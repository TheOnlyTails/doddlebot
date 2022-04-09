package com.theonlytails.doddlebot

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo

data class User(val id: Long, val name: String, val score: Int)

val client = KMongo.createClient(dotenv["MONGO_URI"])
val doddlebotDatabase: MongoDatabase = client.getDatabase("doddlebot")