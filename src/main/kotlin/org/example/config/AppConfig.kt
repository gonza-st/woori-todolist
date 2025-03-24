package org.example.config

import org.example.controller.TodoController
import org.example.repository.TodoRepository
import org.example.repository.TodoRepositoryImpl
import org.example.service.TodoService
import org.example.view.ConsoleView

class AppConfig {
    fun todoRepository(): TodoRepository = TodoRepositoryImpl()

    fun todoService(): TodoService = TodoService(todoRepository())

    fun consoleView(): ConsoleView = ConsoleView()

    fun todoController() = TodoController(todoService(), consoleView())
}
