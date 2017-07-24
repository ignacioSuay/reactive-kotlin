package com.ignaciosuay.reactiveserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveServerApplication::class.java, *args)
}
