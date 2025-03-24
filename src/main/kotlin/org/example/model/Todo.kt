package org.example.model

import java.time.LocalDate

data class Todo(
    val id: Int,
    var title: String,
    var description: String? = null,
    var done: Boolean = false,
    var dueDate: LocalDate? = null,
    val createdBy: LocalDate = LocalDate.now(),
    var updatedBy: LocalDate? = null,
)
