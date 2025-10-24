data class Contact(var name: String, var phone: String, var email: String)

fun main() {
    val contacts = mutableListOf<Contact>()
    var running = true

    println("Welcome to the Kotlin Contact Manager!")

    while (running) {
        println(
            """
            |Choose an option:
            |1. View all contacts
            |2. Add contact
            |3. Update contact
            |4. Delete contact
            |5. Search contacts
            |6. Exit
            """.trimMargin()
        )

        when (readLine()?.trim()) {
            "1" -> viewContacts(contacts)
            "2" -> addContact(contacts)
            "3" -> updateContact(contacts)
            "4" -> deleteContact(contacts)
            "5" -> searchContacts(contacts)
            "6" -> {
                println("Exiting. Goodbye!")
                running = false
            }
            else -> println("Invalid option, please try again.")
        }
    }
}

fun viewContacts(contacts: MutableList<Contact>) {
    if (contacts.isEmpty()) {
        println("No contacts found.")
    } else {
        println("Contacts List:")
        contacts.forEachIndexed { idx, contact ->
            println("${idx + 1}. ${contact.name}, Phone: ${contact.phone}, Email: ${contact.email}")
        }
    }
}

fun addContact(contacts: MutableList<Contact>) {
    println("Enter name:")
    val name = readLine()?.trim()
    println("Enter phone number:")
    val phone = readLine()?.trim()
    println("Enter email:")
    val email = readLine()?.trim()

    if (name.isNullOrEmpty() || phone.isNullOrEmpty() || email.isNullOrEmpty()) {
        println("All fields must be provided.")
        return
    }

    contacts.add(Contact(name, phone, email))
    println("Contact added.")
}

fun updateContact(contacts: MutableList<Contact>) {
    if (contacts.isEmpty()) {
        println("No contacts to update.")
        return
    }

    viewContacts(contacts)
    println("Enter the number of the contact to update:")
    val input = readLine()?.toIntOrNull()
    if (input == null || input !in 1..contacts.size) {
        println("Invalid contact number.")
        return
    }

    val contact = contacts[input - 1]
    println("Updating contact: ${contact.name}")
    println("Enter new name (leave blank to keep '${contact.name}'):")
    val newName = readLine()?.trim()
    println("Enter new phone (leave blank to keep '${contact.phone}'):")
    val newPhone = readLine()?.trim()
    println("Enter new email (leave blank to keep '${contact.email}'):")
    val newEmail = readLine()?.trim()

    if (!newName.isNullOrEmpty()) contact.name = newName
    if (!newPhone.isNullOrEmpty()) contact.phone = newPhone
    if (!newEmail.isNullOrEmpty()) contact.email = newEmail

    println("Contact updated.")
}

fun deleteContact(contacts: MutableList<Contact>) {
    if (contacts.isEmpty()) {
        println("No contacts to delete.")
        return
    }

    viewContacts(contacts)
    println("Enter the number of the contact to delete:")
    val input = readLine()?.toIntOrNull()
    if (input == null || input !in 1..contacts.size) {
        println("Invalid contact number.")
        return
    }

    contacts.removeAt(input - 1)
    println("Contact removed.")
}

fun searchContacts(contacts: MutableList<Contact>) {
    println("Enter name or part of name to search:")
    val query = readLine()?.trim()?.lowercase()
    if (query.isNullOrEmpty()) {
        println("Search query cannot be empty.")
        return
    }

    val results = contacts.filter { it.name.lowercase().contains(query) }
    if (results.isEmpty()) {
        println("No contacts matched the search.")
    } else {
        println("Search Results:")
        results.forEach { println("${it.name}, Phone: ${it.phone}, Email: ${it.email}") }
    }
}
