package org.example.repository

import org.example.model.Todo

interface TodoRepository {
    fun findAll(): List<Todo>

    fun findById(id: Int): Todo?

    fun save(todo: Todo): Todo

    fun update(todo: Todo): Todo?

    fun delete(id: Int): Boolean

    fun deleteAll()

    fun deleteCompleted(): Int

    fun count(): Int
}
