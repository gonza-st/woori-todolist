package org.example.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class TodoListTest {
    companion object {
        private var todo1Title = "신나게 출근"
        private var todo2Title = "기가 막힌 점심 식사"
        private var todo3Title = "빠르게 퇴근"

        var todo1 = Todo(todo1Title)
        var todo2 = Todo(todo2Title)
        var todo3 = Todo(todo3Title)

        var todos = listOf(todo1, todo2, todo3)
        private lateinit var todoList: TodoList
    }

    @Before
    fun setUp() {
        todoList = TodoList(todos)
    }

    @Test
    fun `todoList가 정상적으로 생성되고, 전체 todo 항목을 조회할 수 있다`() {
        val firstIndex = 0
        val inquiredTodoList = todoList.getTodoList()

        assertNotNull(inquiredTodoList)

        assertEquals(inquiredTodoList.size, todos.size)
        assertEquals(todo1, todoList.getTodoList()[firstIndex])
        assertEquals(todo2, todoList.getTodoList()[firstIndex + 1])
        assertEquals(todo3, todoList.getTodoList()[firstIndex + 2])

        assertEquals(inquiredTodoList[firstIndex].title, todos[firstIndex].title)
        assertEquals(inquiredTodoList[firstIndex + 1].title, todos[firstIndex + 1].title)
        assertEquals(inquiredTodoList[firstIndex + 2].title, todos[firstIndex + 2].title)
    }

    @Test
    fun `id로 단일 todo 항목을 조회할 수 있다`() {
        val inquiredTodoId = todo1.id
        val inquiredTodo: Todo? = todoList.getTodoById(inquiredTodoId)

        assertNotNull(inquiredTodo)
        assertEquals(inquiredTodoId, inquiredTodo?.id)
        assertNotEquals(todo2.id, inquiredTodo?.id)
        assertNotEquals(todo3.id, inquiredTodo?.id)
    }

    @Test
    fun `존재하지않는 id 로 조회하면 예외가 발생한다`() {
        val invalidId = -1L
        val inquiredTodo: Todo? = todoList.getTodoById(invalidId)

        assertNull(inquiredTodo)
    }

    @Test
    fun `todo 항목을 추가할 수 있다`() {
        val initialTodoSize = todoList.getTodoList().size
        val newTodo = Todo("헬스장 가기")

        todoList.addTodo(newTodo)

        val addedTodoList: List<Todo> = todoList.getTodoList()
        val findTodoId = TodoList(addedTodoList).getTodoById(newTodo.id)?.id

        assertEquals(initialTodoSize + 1, addedTodoList.size)
        assertEquals(newTodo.id, findTodoId)
    }

    @Test
    fun `완료된 todo 항목만 삭제할 수 있다`() {
        todo1.updateDone()
        todo2.updateDone()
        val expectedRemainingSize = todoList.size() - 2

        todoList.deleteCompletedTodo()

        val remainingTodos = todoList.getTodoList()

        assertEquals(expectedRemainingSize, remainingTodos.size)
        assertEquals(todo3.id, remainingTodos[0].id)
        assertEquals(true, todo1.done)
        assertEquals(true, todo2.done)
        assertEquals(false, todo3.done)
    }

    @Test
    fun `todo 항목을 삭제할 수 있다`() {
        val initialTodoListSize = todoList.size()

        todoList.deleteTodoById(todo1.id)
        todoList.deleteTodoById(todo2.id)

        val expectedRemainingSize = initialTodoListSize - 2
        val remainingTodos = todoList.getTodoList()

        assertEquals(remainingTodos.size, expectedRemainingSize)
        assertEquals(todo3.id, remainingTodos[0].id)
        assertNull(todoList.getTodoById(todo1.id))
        assertNull(todoList.getTodoById(todo2.id))
    }

    @Test
    fun `선택한 여러 항목을 일괄 삭제한다`() {
        val expectedRemainingSize = todoList.size() - 2
        todoList.deleteSelectedTodos(listOf(todo1.id, todo2.id))

        val remainingTodos = todoList.getTodoList()

        assertEquals(expectedRemainingSize, remainingTodos.size)
        assertEquals(todo3.id, remainingTodos[0].id)
        assertNull(todoList.getTodoById(todo1.id))
        assertNull(todoList.getTodoById(todo2.id))
    }

    @Test
    fun `todo 항목을 수정할 수 있다`() {
        val newTitle = "반차 쓰고 집 가기"

        todo1.updateTitle(newTitle)
        todo2.updateDone()

        todoList.updateTodo(todo1)
        todoList.updateTodo(todo2)

        assertEquals(todoList.getTodoById(todo1.id)?.title, newTitle)
        assertEquals(todoList.getTodoById(todo2.id)?.done, true)
    }
}
