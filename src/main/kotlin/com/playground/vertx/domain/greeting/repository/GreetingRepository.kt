package com.playground.vertx.domain.greeting.repository

import com.playground.vertx.domain.greeting.model.Greeting

class GreetingRepository {
    private val greetings: MutableSet<Greeting> = mutableSetOf()

    fun addGreeting(greeting: Greeting): Greeting {
        vertx
        return greeting
    }

    fun getGreetings(): Set<Greeting> {
        return greetings
    }
}

