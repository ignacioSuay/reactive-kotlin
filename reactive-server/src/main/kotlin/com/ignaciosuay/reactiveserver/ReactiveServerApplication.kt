package com.ignaciosuay.reactiveserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDateTime
import kotlin.streams.asStream


@SpringBootApplication
class ReactiveServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveServerApplication::class.java, *args)
}

data class Event(val id: Int, val time: LocalDateTime)

@RestController
class ReactiveServer {

    @GetMapping("/events/{id}")
    fun eventById(@PathVariable id: Int): Mono<Event> = Mono.just(Event(id, LocalDateTime.now()))


    @GetMapping(produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE), value = "/events")
    fun events(): Flux<Event> {

        val stream = generateSequence(1) { it + 1 }
                .map { Event(it, LocalDateTime.now()) }
                .asStream()

        val eventFlux = Flux.fromStream(stream)
                .delayElements(Duration.ofSeconds(1))

        return eventFlux
    }


}