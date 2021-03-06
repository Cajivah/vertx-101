package com.playground.vertx.domain.greeting.model

import com.playground.vertx.domain.greeting.model.enumeration.GreetingTag
import java.util.*

data class CreateGreetingDto(val content: String, val tags: Set<GreetingTag>)