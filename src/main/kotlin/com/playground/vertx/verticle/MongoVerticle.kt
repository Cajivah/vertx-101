package com.playground.vertx.verticle

import com.playground.vertx.domain.greeting.model.MongoMessage
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.mongo.MongoClient

class MongoVerticle : AbstractVerticle() {

    private lateinit var mongo: MongoClient

    override fun start(promise: Promise<Void>) {
        mongo = MongoClient.createShared(vertx, config())
        vertx.eventBus().consumer<MongoMessage>(ADDRESS) { msg -> println("Msg received $msg")}
        promise.complete()
    }

    companion object {
        const val ADDRESS = "mongo"
    }
}