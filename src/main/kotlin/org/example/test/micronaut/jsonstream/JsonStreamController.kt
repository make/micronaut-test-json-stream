package org.example.test.micronaut.jsonstream

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory

@Controller("/jsonStream")
class JsonStreamController {

    private val log = LoggerFactory.getLogger(javaClass)!!

    @Post("/", consumes = [MediaType.APPLICATION_JSON_STREAM])
    fun load(@Body data: Flowable<Map<Any,Any>>): Single<HttpResponse<Map<String, Any>>> {
        return data.subscribeOn(Schedulers.io())
                .map { map: Map<Any, Any> ->
                    log.info("$map")
                    1L
                }
                .count()
                .map {
                    log.info("Handled $it json objects successfully.")
                    HttpResponse.ok(mapOf("success" to true, "handled" to it))
                }
    }

}