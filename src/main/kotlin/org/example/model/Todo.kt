package org.example.model

import java.time.LocalDate
import kotlin.random.Random

class Todo(
    title: String,
    description: String? = null,
    dueDate: LocalDate? = null,
) {
    companion object {
        const val TITLE_REQUIRED_MESSAGE = "제목을 필수로 입력해주세요"

        // 10자리 양수 ID 범위
        private const val MIN_ID = 1000000000L // 10자리 시작 (10억)
        private const val MAX_ID = 9999999999L // 10자리 끝 (100억 - 1)
    }

    val id: Long = Random.nextLong(MIN_ID, MAX_ID + 1)
    val createdDate: LocalDate = LocalDate.now()
    private var updatedDate: LocalDate? = null
    var title: String = title
        private set
    var description: String? = description
        private set
    var done: Boolean = false
        private set
    var dueDate: LocalDate? = dueDate
        private set

    init {
        require(title.isNotBlank()) { TITLE_REQUIRED_MESSAGE }
    }

    fun updateTitle(newTitle: String) {
        require(newTitle.isNotBlank()) { TITLE_REQUIRED_MESSAGE }
        this.title = newTitle
    }

    fun updateDescription(newDescription: String?) {
        this.description = newDescription
    }

    fun updateDueDate(newDueDate: LocalDate?) {
        this.dueDate = newDueDate
    }

    fun updateDone() {
        done = !done
    }

    private fun recordUpdatedDate() {
        updatedDate = LocalDate.now()
    }
}
