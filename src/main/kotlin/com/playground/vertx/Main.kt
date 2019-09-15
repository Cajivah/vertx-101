package com.playground.vertx

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.playground.vertx.verticle.MongoVerticle
import com.playground.vertx.verticle.WebServerVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.Json

fun main() {
    println("Entered main function")
    setUpJackson()
    val vertx = Vertx.vertx()

    vertx.deployVerticle(WebServerVerticle()) {
        if (it.succeeded()) println("WebServerVerticle deployed, id: ${it.result()}")
    }
    vertx.deployVerticle(MongoVerticle()) {
        if (it.succeeded()) println("MongoVerticle deployed, id: ${it.result()}")
    }
}

private fun setUpJackson() {
    Json.mapper.apply {
        registerKotlinModule()
    }

    Json.prettyMapper.apply {
        registerKotlinModule()
    }
}
