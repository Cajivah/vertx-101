package com.playground.vertx

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.playground.vertx.verticle.WebServerVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.Json

fun main() {
    println("Entered main function")
    setUpJackson()
    val vertx = Vertx.vertx()
    vertx.deployVerticle(WebServerVerticle()) {
        if (it.succeeded()) println("Verticle deployed, id: ${it.result()}")
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
