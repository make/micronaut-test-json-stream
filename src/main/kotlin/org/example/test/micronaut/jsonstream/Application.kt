package org.example.test.micronaut.jsonstream

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("org.example.test.micronaut.jsonstream")
                .mainClass(Application.javaClass)
                .start()
    }
}