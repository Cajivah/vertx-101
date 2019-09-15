package com.playground.vertx.verticle

import com.playground.vertx.domain.greeting.model.Greeting
import com.playground.vertx.domain.greeting.model.enumeration.GreetingTag
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import java.util.*

class MongoVerticle : AbstractVerticle() {

    private lateinit var mongo: MongoClient

    override fun start(promise: Promise<Void>) {
        mongo = MongoClient.createShared(vertx, config())
        testInit()
        promise.complete()
    }

    private fun testInit() {
        val greeting = Greeting("Hello world", setOf(GreetingTag.CASUAL), UUID.randomUUID())
        mongo.insert("greetings", JsonObject.mapFrom(greeting))
        {
            if (it.succeeded()) {
                println(it.result())
            } else {
                println(it.cause())
            }
        }
    }
}