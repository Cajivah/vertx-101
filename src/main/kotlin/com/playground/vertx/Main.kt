package com.playground.vertx

import com.playground.vertx.verticle.WebServerVerticle
import io.vertx.core.Vertx

fun main() {
    println("Entered main function")

    val vertx = Vertx.vertx()
    vertx.deployVerticle(WebServerVerticle())
}
