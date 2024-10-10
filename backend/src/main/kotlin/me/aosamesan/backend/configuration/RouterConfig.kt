package me.aosamesan.backend.configuration

import enums.MessageType
import models.ApiMessage
import models.ComplexData
import models.GuGuDan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import parameters.parseQueryMap

@Configuration
class RouterConfig {
    @Bean
    fun routes(): RouterFunction<ServerResponse> = router {
        path("/api").nest {
            GET("/echo") { request ->
                val message = request.queryParam("message").orElse("Hello World!")
                ServerResponse.ok().bodyValue(ApiMessage(message, MessageType.INFO))
            }
            GET("/gugudan") {
                val guGuDan = GuGuDan(
                    title = "GuGu Class",
                    table = createGuGuDan()
                )
                ServerResponse.ok().bodyValue(guGuDan)
            }
            GET("/complex") {
                ServerResponse.ok().bodyValue(ComplexData(
                    title = "title",
                    simple = ComplexData.SimpleData("Message")
                ))
            }
            GET("/echoParameter") {
                ServerResponse.ok().bodyValue(
                    parseQueryMap(it.queryParams().toSingleValueMap())
                )
            }
        }
    }


    private fun createGuGuDan(): Map<Int, Map<Int, Int>> {
        return (2..9).associateWith { num ->
            (1..9).associateWith { prod ->
                num * prod
            }
        }
    }
}