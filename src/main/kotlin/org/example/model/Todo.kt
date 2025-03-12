package org.example.model

import java.time.LocalDate
import kotlin.random.Random

class Todo(
    var title: String,
    var description: String = "",
    var done: Boolean = false,
    var dueDate: LocalDate? = null,
) {
    val id: Long = Random.nextLong()
    var createdAt: LocalDate = LocalDate.now()
    var updatedAt: LocalDate? = null

    init {
        require(title.isNotBlank()) { "제목을 필수로 입력해주세요" }
    }

    fun update(
        title: String? = null,
        description: String? = null,
        dueDate: LocalDate? = null,
    ): Todo {
        val hasChanges = title != null || description != null || dueDate != null

        if (!hasChanges) {
            return this
        }

        this.title = title ?: this.title
        this.description = description ?: this.description
        this.dueDate = dueDate ?: this.dueDate
        updatedAt = LocalDate.now()

        return this
    }

    fun toggleDone() {
        done = !done
        updateDueDate()
    }

    private fun updateDueDate() {
        dueDate =
            if (!done) {
                null
            } else {
                LocalDate.now()
            }
    }
}
