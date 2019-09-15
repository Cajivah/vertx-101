package com.playground.vertx.verticle

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.mongo.MongoClient

class MongoVerticle : AbstractVerticle() {

    private lateinit var mongo: MongoClient

    override fun start(startPromise: Promise<Void>?) {
        mongo = MongoClient.createShared(vertx, config())
        println("Started mongo verticle")

    }
}