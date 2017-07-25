package com.ignaciosuay.reactiveclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient


@SpringBootApplication
class ReactiveClientApplication


fun main(args: Array<String>) {
    SpringApplication.run(ReactiveClientApplication::class.java, *args)
}

@Bean
fun client(): WebClient = WebClient.create("http:://localhost:8080")

@Bean
fun testClient() {
    println("hey starting")

    client()
            .get()
            .uri("hola")
            .exchange()
            .flatMap { cr -> 1 }
            .subscribe(println())

}