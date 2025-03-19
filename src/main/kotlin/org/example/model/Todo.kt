@file:Suppress("ktlint:standard:no-wildcard-imports")

package org.example.model

import java.time.LocalDate
import java.util.*

class Todo(
    title: String,
    description: String? = null,
    dueDate: LocalDate? = null,
) {
    companion object {
        const val TITLE_REQUIRED_MESSAGE = "제목을 필수로 입력해주세요"
    }

    val id: String = UUID.randomUUID().toString()
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
