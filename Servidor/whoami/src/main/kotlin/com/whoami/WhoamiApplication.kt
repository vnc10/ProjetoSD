package com.whoami

import com.whoami.controller.Controller
import com.whoami.repository.ScoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WhoamiApplication @Autowired constructor(final val scoreRepository: ScoreRepository) {

	@Bean
	fun init() = CommandLineRunner()

	private fun CommandLineRunner() {

	}

	init {
		val novo = Controller(scoreRepository)
		novo.initializeServer()
	}
}

fun main(args: Array<String>) {
	runApplication<WhoamiApplication>(*args)
}
