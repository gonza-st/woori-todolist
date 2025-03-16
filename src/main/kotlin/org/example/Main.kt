package org.example

import org.example.model.Todo
import org.example.model.TodoList
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    println("=== Todo 애플리케이션 시작 ===")

    // 1. Todo 생성
    println("\n1. Todo 항목 생성하기")
    val todo1 = Todo("오후 스터디 준비").apply {
        updateDescription("스터디 자료 검토")
        updateDueDate(LocalDate.now().plusDays(1))
    }
    println("생성된 Todo: ${todo1.title} (ID: ${todo1.id})")
    println("내용: ${todo1.description}")
    println("마감일: ${todo1.dueDate?.format(DateTimeFormatter.ISO_LOCAL_DATE)}")
    println("완료 여부: ${if (todo1.done) "완료" else "미완료"}")

    val todo2 = Todo("점심 약속").apply {
        updateDescription("마루와 감자탕 먹기")
    }

    val todo3 = Todo("이메일 확인").apply {
        updateDone() // 완료 처리
    }

    val todo4 = Todo("저녁 운동").apply {
        updateDescription("천국의 계단 40분")
        updateDueDate(LocalDate.now())
    }

    // 2. TodoList 생성 및 항목 추가
    println("\n2. TodoList 생성 및 항목 추가")
    val initialTodos = listOf(todo1, todo2)
    val todoList = TodoList(initialTodos)
    println("초기 TodoList 크기: ${todoList.size()}")

    todoList.addTodo(todo3)
    todoList.addTodo(todo4)
    println("Todo 2개 추가 후 TodoList 크기: ${todoList.size()}")

    // 3. TodoList 전체 항목 조회
    println("\n3. TodoList 전체 항목 조회")
    todoList.getTodoList().forEachIndexed { index, todo ->
        println("${index + 1}. ${todo.title} - ${if (todo.done) "완료" else "미완료"}")
    }

    // 4. ID로 특정 Todo 조회
    println("\n4. ID로 특정 Todo 조회")
    val todoToFind = todo2.id
    val foundTodo = todoList.getTodoById(todoToFind)
    if (foundTodo != null) {
        println("조회 결과: ${foundTodo.title} (ID: ${foundTodo.id})")
    } else {
        println("해당 ID의 Todo를 찾을 수 없습니다: $todoToFind")
    }

    // 5. Todo 수정
    println("\n5. Todo 수정")
    val todoToUpdate = todoList.getTodoById(todo1.id)
    if (todoToUpdate != null) {
        println("수정 전: ${todoToUpdate.title}, 완료 여부: ${if (todoToUpdate.done) "완료" else "미완료"}")
        todoToUpdate.updateTitle("중요! 오전 미팅 준비")
        todoToUpdate.updateDone()
        todoList.updateTodo(todoToUpdate)

        val updatedTodo = todoList.getTodoById(todoToUpdate.id)
        println("수정 후: ${updatedTodo?.title}, 완료 여부: ${if (updatedTodo?.done == true) "완료" else "미완료"}")
    }

    // 6. 완료된 항목 삭제
    println("\n6. 완료된 항목 삭제")
    println("삭제 전 TodoList 크기: ${todoList.size()}")
    todoList.deleteCompletedTodo()
    println("완료된 항목 삭제 후 TodoList 크기: ${todoList.size()}")

    // 7. 남은 Todo 항목 출력
    println("\n7. 남은 Todo 항목 출력")
    todoList.getTodoList().forEachIndexed { index, todo ->
        println("${index + 1}. ${todo.title} - ${if (todo.done) "완료" else "미완료"}")
    }

    // 8. ID로 특정 Todo 삭제
    println("\n8. ID로 특정 Todo 삭제")
    val todoToDelete = todoList.getTodoList().firstOrNull()?.id
    if (todoToDelete != null) {
        println("삭제할 Todo ID: $todoToDelete")
        todoList.deleteTodoById(todoToDelete)
        println("삭제 후 TodoList 크기: ${todoList.size()}")
    }

    // 9. 선택 삭제
    println("\n9. 여러 항목 선택 삭제")
    val todosToDelete = todoList.getTodoList().map { it.id }
    if (todosToDelete.isNotEmpty()) {
        println("선택 삭제할 Todo 개수: ${todosToDelete.size}")
        todoList.deleteSelectedTodos(todosToDelete)
        println("선택 삭제 후 TodoList 크기: ${todoList.size()}")
    }

    println("\n=== Todo 애플리케이션 종료 ===")

}
