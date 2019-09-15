package com.playground.vertx.model

import com.playground.vertx.model.enumeration.GreetingTag

data class Greeting (val content: String, val tags: Set<GreetingTag>)