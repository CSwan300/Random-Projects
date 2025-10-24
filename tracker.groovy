class Expense {
    String category
    double amount
    String description
    Date date

    Expense(String category, double amount, String description) {
        this.category = category
        this.amount = amount
        this.description = description
        this.date = new Date()
    }

    String toString() {
        return "${date.format('yyyy-MM-dd')} | ${category.padRight(10)} | \$${amount.toFixed(2)} | ${description}"
    }
}

class ExpenseTracker {
    List<Expense> expenses = []

    void addExpense() {
        print "Enter category: "
        def category = System.console().readLine()
        print "Enter amount: "
        def amount = System.console().readLine() as double
        print "Enter description: "
        def description = System.console().readLine()

        def expense = new Expense(category, amount, description)
        expenses << expense
        println "Expense added: $expense"
    }

    void viewExpenses() {
        if (expenses.isEmpty()) {
            println "No recorded expenses."
            return
        }
        println "\nDate         | Category   | Amount   | Description"
        println "-----------------------------------------------"
        expenses.each { println it }
    }

    void totalByCategory() {
        if (expenses.isEmpty()) {
            println "No recorded expenses."
            return
        }
        def totals = expenses.groupBy { it.category }
                             .collectEntries { k, v -> [k, v.sum { it.amount }] }

        println "\nTotal spent by category:"
        totals.each { k, v -> println "${k.padRight(10)} : \$${v.toFixed(2)}" }
    }
}

def tracker = new ExpenseTracker()
def running = true

println "Welcome to Groovy Expense Tracker!"

while (running) {
    println "\nChoose an option:"
    println "1. Add Expense"
    println "2. View Expenses"
    println "3. Total by Category"
    println "4. Exit"

    def choice = System.console().readLine()
    switch (choice) {
        case "1": tracker.addExpense(); break
        case "2": tracker.viewExpenses(); break
        case "3": tracker.totalByCategory(); break
        case "4": running = false; println "Goodbye!"; break
        default: println "Invalid option."
    }
}
