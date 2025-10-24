fun main() {
    val tasks = mutableListOf<String>()
    var running = true

    println("Welcome to the Kotlin ToDo List App!")

    while (running) {
        println("\nChoose an option:")
        println("1. View tasks")
        println("2. Add task")
        println("3. Remove task")
        println("4. Exit")

        when (readLine()?.trim()) {
            "1" -> viewTasks(tasks)
            "2" -> addTask(tasks)
            "3" -> removeTask(tasks)
            "4" -> {
                println("Exiting. Goodbye!")
                running = false
            }
            else -> println("Invalid choice, please try again.")
        }
    }
}

fun viewTasks(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("No tasks found.")
    } else {
        println("Your Tasks:")
        tasks.forEachIndexed { index, task ->
            println("${index + 1}. $task")
        }
    }
}

fun addTask(tasks: MutableList<String>) {
    println("Enter the task description:")
    val task = readLine()
    if (!task.isNullOrBlank()) {
        tasks.add(task)
        println("Task added.")
    } else {
        println("Task cannot be empty.")
    }
}

fun removeTask(tasks: MutableList<String>) {
    if (tasks.isEmpty()) {
        println("No tasks to remove.")
        return
    }
    println("Enter the number of the task to remove:")
    viewTasks(tasks)
    val input = readLine()
    val taskNum = input?.toIntOrNull()
    if (taskNum == null || taskNum !in 1..tasks.size) {
        println("Invalid task number.")
    } else {
        tasks.removeAt(taskNum - 1)
        println("Task removed.")
    }
}
