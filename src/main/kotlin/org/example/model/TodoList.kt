package org.example.model

class TodoList(
    initialList: List<Todo>,
) {
    private val todoList: MutableList<Todo> = initialList.toMutableList()

    fun getTodoList() = todoList.toList()

    fun size() = todoList.size

    fun getTodoById(id: String): Todo? = todoList.find { it.id == id }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
    }

    fun deleteCompletedTodo() {
        todoList.removeIf { it.done }
    }

    fun deleteTodoById(id: String) {
        todoList.removeIf { it.id == id }
    }

    fun deleteSelectedTodos(selectedTodos: List<String>) {
        val selectedTodosSet = selectedTodos.toSet()
        todoList.removeIf { todo -> selectedTodosSet.contains(todo.id) }
    }

    fun updateTodo(updatedTodo: Todo) {
        val index = todoList.indexOfFirst { it.id == updatedTodo.id }

        if (index != -1) {
            todoList[index] = updatedTodo
        }
    }
}
