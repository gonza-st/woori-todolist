package org.example.model

class TodoList(
    initialList: List<Todo>,
) {
    private val todoList: MutableList<Todo> = initialList.toMutableList()

    fun getTodoList() = todoList.toList()

    fun size() = todoList.size

    fun getTodoById(id: Long): Todo? = todoList.find { it.id == id }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
    }

    fun deleteCompletedTodo() {
        todoList.removeIf { it.done }
    }

    fun deleteTodoById(id: Long) {
        todoList.removeIf { it.id == id }
    }

    fun deleteSelectedTodos(selectedTodos: List<Long>) {
        val selectedTodosSet = selectedTodos.toSet()
        todoList.removeIf { todo -> selectedTodosSet.contains(todo.id) }
    }

    // TODO 수정 필요
    fun updateTodo(updatedTodo: Todo) {
        val index = todoList.indexOfFirst { it.id == updatedTodo.id }

        if (index != -1) {
            todoList[index] = updatedTodo
        }
    }
}
