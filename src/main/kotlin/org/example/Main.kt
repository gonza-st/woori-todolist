package org.example

import org.example.config.AppConfig

fun main() {
    val appConfig = AppConfig()
    appConfig.todoController().start()
}
