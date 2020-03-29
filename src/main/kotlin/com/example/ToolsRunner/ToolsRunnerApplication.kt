package com.example.ToolsRunner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ToolsRunnerApplication

fun main(args: Array<String>) {
	runApplication<ToolsRunnerApplication>(*args)
}
