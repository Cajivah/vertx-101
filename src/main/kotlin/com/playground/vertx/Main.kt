package com.playground.vertx

import com.playground.vertx.verticies.HelloWorld
import io.vertx.core.Vertx


    fun main(args: Array<String>) {
        println("Entered main function")

        val vertx = Vertx.vertx()
        vertx.deployVerticle(HelloWorld())
    }
