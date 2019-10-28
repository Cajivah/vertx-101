package com.playground.vertx.verticle

import com.playground.vertx.domain.greeting.model.CreateGreetingDto
import com.playground.vertx.domain.greeting.model.Greeting
import com.playground.vertx.domain.greeting.model.GreetingDto
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx

class GreetingsServiceVerticle : AbstractVerticle() {

    override fun start(promise: Promise<Void>) {
        vertx.eventBus()
                .consumer<CreateGreetingDto>(Address.CREATE_GREETING) { request -> create(request.body(), vertx) { a -> request.reply(a) } }
        vertx.eventBus()
                .consumer<Any>(Address.GET_ALL_GREETINGS) { getAll(vertx) {a -> it.reply(a)} }
        promise.complete()
    }

    private fun getAll(vertx: Vertx, callback: (Any) -> Unit) {
        vertx.eventBus().request<List<Greeting>>(Address.GET_ALL_GREETINGS_DB, "") { result ->
            if (result.succeeded()) {
                val greetings = result.result().body()
                callback.invoke(greetings.map { GreetingDto(it) })
            }
        }
    }

    private fun create(body: CreateGreetingDto, vertx: Vertx, callback: (GreetingDto) -> Unit) {
        vertx.eventBus().request<Greeting>(Address.CREATE_GREETING_DB, body) { result ->
            if (result.succeeded()) {
                val greeting = result.result().body()
                callback.invoke( GreetingDto(greeting) )
            }
        }
    }
}