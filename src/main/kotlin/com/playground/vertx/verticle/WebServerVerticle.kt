package com.playground.vertx.verticle

import com.playground.vertx.domain.greeting.controler.GreetingController
import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

class WebServerVerticle : AbstractVerticle() {

    private var greetingController: GreetingController = GreetingController()

    override fun start(future: Promise<Void>) {
        vertx.createHttpServer()
                .requestHandler(createRouter()).listen(8080) { event: AsyncResult<HttpServer> ->
                    if (event.succeeded()) {
                        future.complete()
                    } else {
                        future.fail(event.cause())
                    }
                }
    }

    private fun createRouter(): Router {
        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.mountSubRouter("/hello", greetingController.router(vertx))
        return router
    }
}