package com.ignaciosuay.reactiveclient

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import org.springframework.web.reactive.function.client.bodyToMono
import java.time.LocalDateTime

@JsonDeserialize
data class Event(val id: Int, val time: LocalDateTime)

@SpringBootApplication
class ReactiveClientApplication{


//    @Bean
//    fun client(): WebClient {
//        println("hola")
//        return  WebClient.create("http:://localhost:8082")
//    }

    @Bean
    fun init(): CommandLineRunner = CommandLineRunner {
        val client = WebClient.create("http://localhost:8082")
//        client
//                .get()
//                .uri("/events/22")
//                .retrieve()
//                .bodyToMono<Event>()
//                .subscribe({ e -> println("my event" + e) })
//    }
        client
                .get()
                .uri("/events")
                .accept(TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux<Event>()
                .subscribe({ e -> println("my event" + e) })
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(ReactiveClientApplication::class.java, *args)
}





