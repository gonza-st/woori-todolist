import org.example.model.Todo
import java.time.LocalDate

class TodoList(
    private val initialList: List<Todo>,
) {
    private val todoList: MutableList<Todo> = initialList.toMutableList()

    fun getTodoList() = todoList.toList()

    fun getTodoById(id: Long): Todo? = todoList.find { it.id == id }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
    }

    fun deleteCompletedTodo() {
        todoList.removeIf { it.done }
    }

    fun deleteTodo(id: Long) {
        todoList.removeIf { it.id == id }
    }

    fun deleteSelectedTodos(selectedTodos: List<Long>) {
        todoList.removeIf { todo -> selectedTodos.contains(todo.id) }
    }

    fun updateTodo(
        id: Long,
        title: String?,
        description: String?,
        dueDate: LocalDate?,
    ) {
    }
}
