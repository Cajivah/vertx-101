package com.playground.vertx.verticies

import io.vertx.core.AbstractVerticle
import io.vertx.core.AsyncResult
import io.vertx.core.Promise
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router

class HelloWorld : AbstractVerticle() {

    private var helloWorldController: HelloWorldController = HelloWorldController()

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
        router.mountSubRouter("/hello", helloWorldController.router(vertx))
        return router
    }
}