package org.example.repository

import org.example.model.Todo

class TodoRepositoryImpl : TodoRepository {
    companion object {
        const val INITIAL_NEXT_ID = 1
    }

    private val todos = mutableMapOf<Int, Todo>()
    private var nextId = INITIAL_NEXT_ID

    override fun findAll(): List<Todo> = todos.values.toList()

    override fun findById(id: Int): Todo? = todos[id]

    override fun save(todo: Todo): Todo {
        val newTodo = todo.copy(id = nextId)
        todos[nextId] = newTodo
        nextId++
        return newTodo
    }

    override fun update(todo: Todo): Todo? {
        if (!todos.containsKey(todo.id)) {
            return null
        }

        todos[todo.id] = todo
        return todo
    }

    override fun delete(id: Int): Boolean = todos.remove(id) != null

    override fun deleteAll() {
        todos.clear()
        nextId = INITIAL_NEXT_ID
    }

    override fun deleteCompleted(): Int {
        val completedTodos: Map<Int, Todo> = todos.filter { (_, todo) -> todo.done }

        completedTodos.forEach { (_, todo) -> todos.remove(todo.id) }

        return completedTodos.size
    }

    override fun count(): Int = todos.count()
}
