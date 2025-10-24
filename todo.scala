object ToDoApp {
  var tasks: List[String] = List()

  def main(args: Array[String]): Unit = {
    var continue = true
    println("Welcome to my Scala ToDo List App!")

    while (continue) {
      println("\nChoose an option:")
      println("1. View tasks")
      println("2. Add task")
      println("3. Remove task")
      println("4. Exit")

      val choice = scala.io.StdIn.readLine()

      choice match {
        case "1" => viewTasks()
        case "2" => addTask()
        case "3" => removeTask()
        case "4" =>
          println("Exiting. Goodbye!")
          continue = false
        case _ => println("Invalid choice, please try again.")
      }
    }
  }

  def viewTasks(): Unit = {
    if (tasks.isEmpty)
      println("No tasks found.")
    else {
      println("Your Tasks:")
      tasks.zipWithIndex.foreach { case (task, idx) =>
        println(s"${idx + 1}. $task")
      }
    }
  }

  def addTask(): Unit = {
    println("Enter the task description:")
    val task = scala.io.StdIn.readLine()
    tasks = tasks :+ task
    println("Task added.")
  }

  def removeTask(): Unit = {
    println("Enter the number of the task to remove:")
    viewTasks()
    val input = scala.io.StdIn.readLine()
    val taskNum = try {
      input.toInt
    } catch {
      case _: NumberFormatException => 0
    }
    if (taskNum > 0 && taskNum <= tasks.length) {
      tasks = tasks.patch(taskNum - 1, Nil, 1)
      println("Task removed.")
    } else {
      println("Invalid task number.")
    }
  }
}
