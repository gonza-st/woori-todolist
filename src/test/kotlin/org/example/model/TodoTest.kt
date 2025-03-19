@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class TodoTest {
    companion object {
        const val DEFAULT_TITLE = "장보기"
        private lateinit var defaultTodo: Todo
    }

    @Before
    fun setUp() {
        defaultTodo = Todo(title = DEFAULT_TITLE)
    }

    @Test
    @DisplayName("제목이 빈 값이 아니면 todo가 정상적으로 생성된다")
    fun creatTodo() {
        assertNotNull(defaultTodo.title)
        assertEquals(DEFAULT_TITLE, defaultTodo.title)

        val otherTodo = Todo(title = "$DEFAULT_TITLE 를 하지 않기")
        assertNotEquals(defaultTodo.id, otherTodo.id)
    }

    @Test
    @DisplayName("todo가 제목을 가지지 않으면 예외가 발생한다")
    fun mustHaveTitle() {
        assertThrows<IllegalArgumentException> {
            Todo(title = "")
        }

        assertThrows<IllegalArgumentException> {
            Todo(title = "   ")
        }
    }

    @Test
    @DisplayName("todo가 생성될 때 생성 날짜가 자동으로 생성 당시의 날짜로 부여된다")
    fun mustSetCreationTimeAutomatically() {
        val creationTodoDate: LocalDate? = LocalDate.now()

        assertNotNull(defaultTodo.createdDate)
        assertEquals(creationTodoDate, defaultTodo.createdDate)
    }

    @Test
    @DisplayName("todo가 생성될 때 id가 자동으로 부여된다")
    fun mustSetIdAutomatically() {
        assertNotNull(defaultTodo.id)
    }

    @Test
    @DisplayName("todo 생성 시 완료 여부는 false로 부여된다")
    fun initDoneIsFalse() {
        assertFalse(defaultTodo.done)
    }

    @Test
    @DisplayName("완료 여부를 변경할 수 있다")
    fun canUpdateDone() {
        val initDone = defaultTodo.done

        defaultTodo.updateDone()

        assertNotEquals(initDone, defaultTodo.done)
    }

    @Test
    @DisplayName("완료 여부를 변경하면 현재 '완료' 상태인 경우 '미완료'로, '미완료' 상태인 경우 '완료'로 변경된다")
    fun toggleDone() {
        val doneWhenCreated = defaultTodo.done
        assertFalse(doneWhenCreated)

        defaultTodo.updateDone()
        assertTrue(defaultTodo.done)

        defaultTodo.updateDone()
        assertFalse(defaultTodo.done)
    }

    @Test
    @DisplayName("제목을 변경할 수 있다")
    fun canUpdateTitle() {
        val initTitle = defaultTodo.title
        val newTitle = "출근하기"

        defaultTodo.updateTitle(newTitle)

        assertNotEquals(initTitle, defaultTodo.title)
        assertEquals(newTitle, defaultTodo.title)
    }

    @Test
    fun `내용을 변경할 수 있다`() {
        val initDescription = defaultTodo.description
        val newDescription = "우유 유통기한은 일주일 이상 남은 걸로 사기"

        defaultTodo.updateDescription(newDescription)

        assertNotEquals(initDescription, defaultTodo.description)
    }

    @Test
    fun `마감일을 변경할 수 있다`() {
        val initDueDate = defaultTodo.dueDate
        val newDueDate: LocalDate = LocalDate.of(2028, 3, 13)

        defaultTodo.updateDueDate(newDueDate)
        val updatedDueDate = defaultTodo.dueDate

        assertNotEquals(initDueDate, updatedDueDate)
    }
}
