package org.example.controller

import org.example.service.TodoService
import org.example.view.ConsoleView
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class TodoController(
    private val todoService: TodoService,
    private val consoleView: ConsoleView,
) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun start() {
        var running = true

        while (running) {
            val choice = consoleView.showMenuAndGetChoice()

            when (choice) {
                1 -> createTodo()
                2 -> getAllTodos()
                3 -> findTodoById()
                4 -> updateTodo()
                5 -> deleteCompletedTodos()
                6 -> deleteTodoById()
                0 -> {
                    running = false
                    consoleView.showApplicationExit()
                }

                else -> consoleView.showInvalidChoice()
            }
        }
    }

    private fun isDateAfterToday(dateString: String): Boolean {
        try {
            val today = LocalDate.now()
            val inputDate = LocalDate.parse(dateString, formatter)

            if (inputDate.isBefore(today)) {
                consoleView.showIsPastDueDate(dateString)
                return false
            }

            return true
        } catch (e: DateTimeParseException) {
            consoleView.showErrorInputDueDateForm()
            return false
        }
    }

    private fun createTodo() {
        consoleView.showCreateTodoHeader()

        consoleView.showInputTitleGuide()
        var title = readlnOrNull()

        consoleView.showInputDescriptionGuide()
        val description = readlnOrNull()

        consoleView.showInputDueDateGuide()
        val dueDateString = readlnOrNull()
        var dueDate: LocalDate? = null

        if (!dueDateString.isNullOrBlank() && isDateAfterToday(dueDateString)) {
            dueDate = LocalDate.parse(dueDateString, formatter)
        }

        while (title.isNullOrBlank()) {
            consoleView.showInputTitleGuide()
            title = readlnOrNull()
        }

        var newTodo = todoService.createTodo(title, description, dueDate)
        newTodo = todoService.addTodo(newTodo)

        consoleView.showSuccessTodoAddition(newTodo)
    }

    private fun getAllTodos() {
        consoleView.showGetAllTodosHeader()
        val allTodos = todoService.getAllTodos() ?: return consoleView.showEmptyTodo()

        consoleView.showAllTodo(allTodos)
    }

    private fun findTodoById() {
        consoleView.showFindTodoByIdHeader()
        val idInput = readlnOrNull() ?: ""

        try {
            val todo = todoService.getTodoById(idInput.toInt()) ?: return consoleView.showIdNotFound()
            consoleView.showFindingTodo(todo)
        } catch (e: NumberFormatException) {
            consoleView.showInvalidId()
        }
    }

    private fun updateTodo() {
        consoleView.showUpdateTodoHeader()
        val idInput = readlnOrNull() ?: ""

        try {
            val todo = todoService.getTodoById(idInput.toInt())
            if (todo != null) {
                val id = todo.id
                var title = todo.title
                var description = todo.description
                var dueDate = todo.dueDate
                var done = todo.done

                consoleView.showBeforeUpdateTodo(todo)

                consoleView.showNewTitleGuide()
                val newTitle = readlnOrNull()
                if (!newTitle.isNullOrBlank()) {
                    title = newTitle
                }

                consoleView.showNewDesciptionGuide()
                val newDescription = readlnOrNull()
                if (!newDescription.isNullOrBlank()) {
                    description = newDescription
                }

                consoleView.showNewDueDateGuide()
                val newDueDateString = readlnOrNull()
                if (!newDueDateString.isNullOrBlank()) {
                    try {
                        val newDueDate = LocalDate.parse(newDueDateString)
                        dueDate = newDueDate
                    } catch (e: Exception) {
                        consoleView.showErrorInputDueDateForm()
                    }
                }

                consoleView.showConfirmUpdating()
                val isUpdated = readlnOrNull()?.uppercase() == "Y"
                if (isUpdated) {
                    done = !done
                }

                todoService.updateTodo(
                    id = id,
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    done = done,
                )

                consoleView.showSuccessTodoUpdating()
            } else {
                consoleView.showIdNotFound()
            }
        } catch (e: NumberFormatException) {
            consoleView.showInvalidId()
        }
    }

    private fun deleteCompletedTodos() {
        consoleView.showDeleteCompletedTodoHeader()
        val confirm = readlnOrNull()?.uppercase() == "Y"

        if (confirm) {
            val deletedTodoCount = todoService.deleteCompletedTodos()
            consoleView.showCountTodoDeletion(deletedTodoCount)
        } else {
            consoleView.showCancellation()
        }
    }

    private fun deleteTodoById() {
        consoleView.showDeleteByIdTodoHeader()
        val idInput = readlnOrNull() ?: ""

        try {
            val todoToDelete = todoService.getTodoById(idInput.toInt())
            if (todoToDelete != null) {
                consoleView.showConfirmDeletion(todoToDelete.title)
                val confirm = readlnOrNull()?.uppercase() == "Y"

                if (!confirm) {
                    return consoleView.showCancellation()
                }

                todoService.deleteTodoById(idInput.toInt())
                consoleView.showSuccessTodoDeletion()
            } else {
                consoleView.showIdNotFound()
            }
        } catch (e: NumberFormatException) {
            consoleView.showInvalidId()
        }
    }
}
