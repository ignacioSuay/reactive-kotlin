package com.ignaciosuay.reactiveclient

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import java.time.LocalDateTime

data class Event(val id: Int, val time: LocalDateTime)

@SpringBootApplication
class ReactiveClientApplication {

    @Bean
    fun init(): CommandLineRunner = CommandLineRunner {
        val client = WebClient.create("http://localhost:8082")

        client
                .get()
                .uri("/events")
                .retrieve()
                .bodyToFlux<Event>()
                .subscribe({ e -> println(e) })
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveClientApplication::class.java, *args)
}






