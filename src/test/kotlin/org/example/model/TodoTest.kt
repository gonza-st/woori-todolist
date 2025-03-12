package org.example.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class TodoTest {
    @Test
    @DisplayName("todo가 정상적으로 생성된다")
    fun creatTodo() {
        val todo = Todo(title = "장보기")
        assertNotNull(todo.title)
        assertEquals("장보기", todo.title)
    }

    @Test
    @DisplayName("todo는 제목을 필수로 가져야한다")
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
        val todo = Todo(title = "장보기")

        assertNotNull(todo.createdAt)
        assertEquals(creationTodoDate, todo.createdAt)
    }

    @Test
    @DisplayName("todo가 생성될 때 id가 자동으로 부여된다")
    fun mustSetIdAutomatically() {
        val todo = Todo(title = "장보기")
        assertNotNull(todo.id)
    }

    @Test
    @DisplayName("완료 여부를 변경할 수 있다")
    fun canUpdateDone() {
        val todo = Todo(title = "장보기")
        val initDone = todo.done

        todo.toggleDone()
        val updatedDone = todo.done

        assertEquals(initDone, !updatedDone)
    }

    @Test
    @DisplayName("todo 생성 시 완료 여부는 false로 부여된다")
    fun initDoneIsTrue() {
        val todo = Todo(title = "장보기")
        val doneWhenCreated = todo.done

        assertEquals(doneWhenCreated, false)
    }

    @Test
    @DisplayName("'완료' 상태일 때 실행하면 '미완료'로 변경되고 '미완료' 상태일 때 실행하면 '완료'로 변경된다")
    fun toggleDone() {
        val todo = Todo(title = "장보기")
        val doneWhenCreated = todo.done

        todo.toggleDone()
        val doneWhenCompleted = todo.done

        todo.toggleDone()
        val doneWhenUncompleted = todo.done

        assertEquals(doneWhenCreated, false)
        assertEquals(doneWhenCompleted, true)
        assertEquals(doneWhenUncompleted, false)
    }

    @Test
    @DisplayName("제목, 내용, 마감일 중 하나라도 수정되면 수정 시간에 수정된 시점의 날짜로 부여한다")
    fun mustSetUpdatedAtAutomatically() {
        val todo = Todo(title = "장보기")
        val updatedAtWhenCreated = todo.updatedAt

        todo.update(description = "감자 사기")
        val updatedAtWhenUpdate = todo.updatedAt

        assertEquals(updatedAtWhenCreated, null)
        assertEquals(updatedAtWhenUpdate, LocalDate.now())
    }
}
