package com.playground.vertx.domain.greeting.service

import com.playground.vertx.domain.greeting.model.CreateGreetingDTO
import com.playground.vertx.domain.greeting.model.Greeting
import com.playground.vertx.domain.greeting.repository.GreetingRepository
import java.util.*

class GreetingService {

    private var greetingRepository: GreetingRepository = GreetingRepository()

    fun addGreeting(greeting: CreateGreetingDTO) {
        val greetingEntity = Greeting(greeting, UUID.randomUUID())
        return greetingRepository.addGreeting(greetingEntity)
    }

    fun getGreetings(): Set<Greeting> {
        return greetingRepository.getGreetings()
    }
}