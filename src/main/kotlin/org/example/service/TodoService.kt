package org.example.service

import org.example.model.Todo
import org.example.repository.TodoRepository
import java.time.LocalDate

class TodoService(
    private val repository: TodoRepository,
) {
    fun getAllTodos(): List<Todo>? {
        if (count() == 0) {
            return null
        }
        return repository.findAll()
    }

    fun getTodoById(id: Int): Todo? = repository.findById(id)

    fun createTodo(
        title: String,
        description: String?,
        dueDate: LocalDate?,
    ): Todo =
        Todo(
            id = 0,
            title = title,
            description = description,
            dueDate = dueDate,
        )

    fun addTodo(todo: Todo): Todo = repository.save(todo)

    fun deleteCompletedTodos(): Int = repository.deleteCompleted()

    fun deleteTodoById(id: Int) {
        repository.delete(id)
    }

    fun updateTodo(
        id: Int,
        title: String?,
        description: String? = null,
        dueDate: LocalDate? = null,
        done: Boolean = false,
    ): Todo? {
        val foundTodo: Todo = repository.findById(id) ?: return null

        val updatedTodo =
            foundTodo.copy(
                title = title ?: foundTodo.title,
                description = description ?: foundTodo.description,
                dueDate = dueDate ?: foundTodo.dueDate,
                done = done,
                updatedBy = LocalDate.now(),
            )

        return repository.update(updatedTodo)
    }

    fun count() = repository.count()
}
