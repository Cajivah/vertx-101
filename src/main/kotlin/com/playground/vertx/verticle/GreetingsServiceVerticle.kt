package com.playground.vertx.verticle

import com.playground.vertx.domain.greeting.model.CreateGreetingDto
import com.playground.vertx.domain.greeting.model.GreetingDto
import com.playground.vertx.domain.greeting.model.enumeration.GreetingTag
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import java.util.*

class GreetingsServiceVerticle : AbstractVerticle() {

    override fun start(promise: Promise<Void>) {
        vertx.eventBus()
                .consumer<CreateGreetingDto>(Address.CREATE_GREETING) { it.reply(create(it.body())) }
        vertx.eventBus()
                .consumer<Any>(Address.GET_ALL_GREETINGS) { it.reply(getAll()) }
        promise.complete()
    }

    private fun getAll(): List<GreetingDto> {
        return listOf()
    }

    private fun create(body: CreateGreetingDto): GreetingDto {
        println(body)
        return GreetingDto(UUID.randomUUID(), "", setOf(GreetingTag.CASUAL))
    }
}