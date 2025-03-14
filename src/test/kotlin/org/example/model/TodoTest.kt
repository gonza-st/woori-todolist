package org.example.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNotEquals
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
    fun initDoneIsTrue() {
        val doneWhenCreated = defaultTodo.done

        assertEquals(doneWhenCreated, false)
    }

    @Test
    @DisplayName("완료 여부를 변경할 수 있다")
    fun canUpdateDone() {
        val initDone = defaultTodo.done

        defaultTodo.updateDone()
        val updatedDone = defaultTodo.done

        assertEquals(initDone, !updatedDone)
    }

    @Test
    @DisplayName("'완료' 상태일 때 실행하면 '미완료'로 변경되고 '미완료' 상태일 때 실행하면 '완료'로 변경된다")
    fun toggleDone() {
        val doneWhenCreated = defaultTodo.done

        defaultTodo.updateDone()
        val doneWhenCompleted = defaultTodo.done

        defaultTodo.updateDone()
        val doneWhenUncompleted = defaultTodo.done

        assertEquals(doneWhenCreated, false)
        assertEquals(doneWhenCompleted, !doneWhenCreated)
        assertEquals(doneWhenUncompleted, !doneWhenCompleted)
    }

    @Test
    @DisplayName("제목을 변경할 수 있다")
    fun canUpdateTitle() {
        val initTitle = defaultTodo.title
        val newTitle = "출근하기"

        defaultTodo.updateTitle(newTitle)
        val updatedTitle = defaultTodo.title

        assertNotEquals(initTitle, updatedTitle)
    }

    @Test
    fun `내용을 변경할 수 있다`() {
        val initDescription = defaultTodo.description
        val newDescription = "우유 유통기한은 일주일 이상 남은 걸로 사기"

        defaultTodo.updateDescription(newDescription)
        val updatedDescription = defaultTodo.description

        assertNotEquals(initDescription, updatedDescription)
    }

    @Test
    fun `마감일을 변경할 수 있다`() {
        val initDueDate = defaultTodo.dueDate
        val newDueDate: LocalDate? = LocalDate.of(2028, 3, 13)

        defaultTodo.updateDueDate(newDueDate)
        val updatedDueDate = defaultTodo.dueDate

        assertNotEquals(initDueDate, updatedDueDate)
    }
}
