package com.playground.vertx.domain.greeting.controler

import com.playground.vertx.domain.greeting.model.CreateGreetingDto
import com.playground.vertx.domain.greeting.model.GreetingDto
import com.playground.vertx.verticle.Address
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

class GreetingController {

    fun router(vertx: Vertx): Router {
        val router = Router.router(vertx)
        router.get().handler {
            getAllGreetings(vertx, it)
        }
        router.post().handler {
            createGreeting(vertx, it)
        }
        return router
    }

    private fun getAllGreetings(vertx: Vertx, routingContext: RoutingContext) {
        vertx.eventBus().request<List<CreateGreetingDto>>(Address.GET_ALL_GREETINGS, "") {
            if (it.succeeded()) {
                val greetings = it.result().body()
                routingContext.response()
                        .putHeader("content-type", "application/json")
                        .end(Json.encodePrettily(greetings))
            }
        }
    }

    private fun createGreeting(vertx: Vertx, routingContext: RoutingContext) {
        val greeting: CreateGreetingDto =
                Json.decodeValue(routingContext.bodyAsString, CreateGreetingDto::class.java)
        vertx.eventBus().request<GreetingDto>(Address.CREATE_GREETING, greeting) {
            if (it.succeeded()) {
                val createdGreeting = it.result().body()
                routingContext.response()
                        .putHeader("content-type", "application/json")
                        .setStatusCode(201)
                        .end(Json.encodePrettily(createdGreeting))
            }
        }
    }
}