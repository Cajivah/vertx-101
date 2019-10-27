package com.playground.vertx.domain.greeting.model

import com.playground.vertx.domain.greeting.model.enumeration.GreetingTag
import java.util.*

data class GreetingDto(val uuid: UUID, val content: String, val tags: Set<GreetingTag>)