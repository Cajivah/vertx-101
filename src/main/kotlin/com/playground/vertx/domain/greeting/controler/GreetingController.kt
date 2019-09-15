package com.playground.vertx.domain.greeting.controler

import com.playground.vertx.domain.greeting.model.CreateGreetingDTO
import com.playground.vertx.domain.greeting.model.Greeting
import com.playground.vertx.domain.greeting.service.GreetingService
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router

class GreetingController {

    private var greetingService: GreetingService = GreetingService()

    fun router(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.get("/test").handler { it.response().end("Hello world") }
        router.get().handler { it.response().end(Json.encodePrettily(greetingService.getGreetings())) }
        router.post().handler { greetingService.addGreeting(it.bodyAsJson.mapTo(CreateGreetingDTO::class.java)) }
        return router
    }

}