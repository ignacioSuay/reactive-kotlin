package com.ignaciosuay.reactiveclient

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ReactiveClientApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReactiveClientApplication::class.java, *args)
}
