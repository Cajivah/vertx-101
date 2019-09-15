package com.playground.vertx

import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.playground.vertx.verticle.MongoVerticle
import com.playground.vertx.verticle.WebServerVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.file.FileSystem
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject

fun main() {
    val vertx = Vertx.vertx()

    setUpJackson()
    val deploymentOptions = getDeploymentConfig(vertx)
    vertx.deployVerticle(WebServerVerticle(), deploymentOptions) {
        if (it.succeeded()) println("WebServerVerticle deployed, id: ${it.result()}")
    }
    vertx.deployVerticle(MongoVerticle(), deploymentOptions) {
        if (it.succeeded()) println("MongoVerticle deployed, id: ${it.result()}")
    }
}

private fun getDeploymentConfig(vertx: Vertx): DeploymentOptions? {
    val config: JsonObject = vertx.fileSystem().readFileBlocking("src/main/conf/conf.json").toJsonObject()
    val deploymentOptions = DeploymentOptions().setConfig(config)
    return deploymentOptions
}

private fun setUpJackson() {
    Json.mapper.apply {
        registerKotlinModule()
    }

    Json.prettyMapper.apply {
        registerKotlinModule()
    }
}
