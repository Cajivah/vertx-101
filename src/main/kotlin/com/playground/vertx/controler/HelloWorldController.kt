package com.playground.vertx.controler

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class HelloWorldController {

    private val greetings: MutableSet<String> = mutableSetOf()

    fun router(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.get("/world").handler { it.response().end("Hello world") }
        router.get().handler {it.response().end(greetings.toString())}
        router.post().handler(this::sayHello).consumes("application/json")
        return router
    }

    private fun sayHello(event: RoutingContext) {
        println(event)
        greetings.add(event.bodyAsString)
    }
}