package com.kotlin.arsgsg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ArsgsgApplication

fun main(args: Array<String>) {
	runApplication<ArsgsgApplication>(*args)
}
