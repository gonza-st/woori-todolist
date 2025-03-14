package org.example.model

import TodoList
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class TodoListTest {
    @Test
    @DisplayName("id로 단일 todo 항목을 조회할 수 있다")
    fun getTodoById() {
        val eatApple = Todo(title = "사과 먹기")
        val eatBread = Todo(title = "빵 먹기")
        val eatMilk = Todo(title = "우유 먹기")

        val todoList = TodoList(listOf(eatApple, eatBread, eatMilk))

        val inquiredTodo: Todo? = todoList.getTodoById(eatApple.id)

        assertNotNull(inquiredTodo)
        assertEquals(inquiredTodo?.id, eatApple.id)
    }
}
