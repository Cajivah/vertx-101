package com.playground.vertx.domain.greeting.model

import io.vertx.core.json.JsonObject

data class MongoMessage(val collection: String, val document: JsonObject)