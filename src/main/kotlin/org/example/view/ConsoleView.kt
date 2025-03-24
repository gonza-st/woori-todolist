package org.example.view

import org.example.model.Todo
import java.time.format.DateTimeFormatter

class ConsoleView {
    fun showMenuAndGetChoice(): Int {
        printLines(menuItems)
        return readlnOrNull()?.toIntOrNull() ?: 0
    }

    private val menuItems =
        listOf(
            "===== Todo 애플리케이션 메뉴 =====",
            "1. Todo 추가하기",
            "2. 모든 Todo 항목 보기",
            "3. ID로 Todo 찾기",
            "4. Todo 수정하기",
            "5. 완료된 Todo 삭제하기",
            "6. ID로 Todo 삭제하기",
            "0. 종료하기",
            "메뉴를 선택하세요: ",
        )

    fun showCreateTodoHeader() {
        header("Todo 추가")
    }

    fun showGetAllTodosHeader() {
        header("모든 Todo 항목")
    }

    fun showFindTodoByIdHeader() {
        header("ID로 Todo 찾기")
        print("찾을 Todo의 ID를 입력하세요: ")
    }

    fun showUpdateTodoHeader() {
        header("Todo 수정")
        print("수정할 Todo의 ID를 입력하세요: ")
    }

    fun showDeleteCompletedTodoHeader() {
        header("완료된 Todo 삭제")
        print("정말 모든 완료된 Todo를 삭제하시겠습니까? (Y/N): ")
    }

    fun showConfirmDeletion(target: String) {
        print("정말 ${target}을(를) 삭제하시겠습니까? (Y/N): ")
    }

    fun showDeleteByIdTodoHeader() {
        header("ID로 Todo 삭제")
        print("삭제할 Todo의 ID를 입력하세요: ")
    }

    fun showApplicationExit() {
        println("\n=== Todo 애플리케이션 종료 ===")
    }

    fun showInvalidChoice() {
        println("선택지 중 선택해 주세요.")
    }

    fun showNewTitleGuide() {
        print("새 제목: ")
    }

    fun showNewDesciptionGuide() {
        print("새 설명: ")
    }

    fun showNewDueDateGuide() {
        print("새 마감일 (YYYY-MM-DD 형식): ")
    }

    fun showConfirmUpdating() {
        print("완료 상태 변경 (Y/N): ")
    }

    fun showEmptyTodo() {
        println("Todo 항목이 없습니다.")
    }

    fun showInputTitleGuide() {
        println("제목을 입력하세요")
    }

    fun showInputDueDateGuide() {
        println("마감일을 입력하세요 (YYYY-MM-DD 형식, 없으면 Enter): ")
    }

    fun showInputDescriptionGuide() {
        println("설명을 입력하세요 (없으면 Enter): ")
    }

    fun showIdNotFound() {
        println("해당 ID의 Todo를 찾을 수 없습니다")
    }

    private fun showDetailTodo(todo: Todo) {
        println("제목: ${todo.title} (ID: ${todo.id})")
        println("설명: ${todo.description ?: "없음"}")
        println("마감일: ${todo.dueDate?.format(DateTimeFormatter.ISO_LOCAL_DATE) ?: "없음"}")
        println("상태: ${if (todo.done) "완료" else "미완료"}")
    }

    fun showAllTodo(todoList: List<Todo>) {
        todoList.forEachIndexed { index, todo ->
            println("${index + 1}.")
            showDetailTodo(todo)
        }
    }

    fun showFindingTodo(todo: Todo) {
        println("찾은 Todo:")
        showDetailTodo(todo)
    }

    fun showBeforeUpdateTodo(todo: Todo) {
        println("현재 Todo 정보:")
        showDetailTodo(todo)

        println("\n수정할 정보를 입력하세요 (변경하지 않으려면 Enter):")
    }

    private fun printLines(items: List<String>) {
        for (item in items) {
            println(item)
        }
    }

    private fun header(header: String) {
        println("\n----- $header -----")
    }

    fun showInvalidId() {
        println("유효한 ID 형식이 아닙니다. 숫자를 입력해주세요.")
    }

    fun showErrorInputDueDateForm() {
        println("날짜 형식이 잘못되었습니다. 마감일은 설정되지 않았습니다.")
    }

    fun showIsPastDueDate(dateStirng: String) {
        println("입력한 날짜 $dateStirng 는 오늘보다 이전 날짜입니다.")
    }

    fun showSuccessTodoAddition(todo: Todo) {
        println("Todo가 추가되었습니다: ${todo.title} (ID: ${todo.id})")
    }

    fun showSuccessTodoUpdating() {
        println("Todo가 성공적으로 수정되었습니다.")
    }

    fun showSuccessTodoDeletion() {
        println("Todo가 성공적으로 삭제되었습니다.")
    }

    fun showCountTodoDeletion(count: Int) {
        println("$count 개의 Todo가 삭제되었습니다.")
    }

    fun showCancellation() {
        println("취소되었습니다.")
    }
}
