#include <iostream>
#include <vector>
#include <fstream>
#include <string>
#include <algorithm>

using namespace std;

class Book {
public:
    string title;
    string author;
    int year;
    string isbn;

    Book() {}

    Book(string t, string a, int y, string i)
        : title(t), author(a), year(y), isbn(i) {}

    void display() const {
        cout << "Title : " << title << "\nAuthor: " << author <<
            "\nYear  : " << year << "\nISBN  : " << isbn << "\n\n";
    }
};

class Library {
    vector<Book> books;

public:
    void addBook(const Book& book) {
        books.push_back(book);
        cout << "Book added successfully.\n";
    }

    void showBooks() const {
        if (books.empty()) {
            cout << "No books in library.\n";
            return;
        }
        for (const auto& book : books) {
            book.display();
        }
    }

    void searchByTitle(const string& title) const {
        bool found = false;
        for (const auto& book : books) {
            if (book.title.find(title) != string::npos) {
                book.display();
                found = true;
            }
        }
        if (!found)
            cout << "No books found with title containing '" << title << "'.\n";
    }

    void searchByAuthor(const string& author) const {
        bool found = false;
        for (const auto& book : books) {
            if (book.author.find(author) != string::npos) {
                book.display();
                found = true;
            }
        }
        if (!found)
            cout << "No books found with author containing '" << author << "'.\n";
    }

    void saveToFile(const string& filename) const {
        ofstream ofs(filename);
        if (!ofs) {
            cout << "Error opening file for writing.\n";
            return;
        }
        for (const auto& book : books) {
            ofs << book.title << "\n" << book.author << "\n"
                << book.year << "\n" << book.isbn << "\n";
        }
        ofs.close();
        cout << "Library saved to " << filename << "\n";
    }

    void loadFromFile(const string& filename) {
        ifstream ifs(filename);
        if (!ifs) {
            cout << "No saved library found.\n";
            return;
        }
        books.clear();
        string title, author, isbn, line;
        int year;
        while (getline(ifs, title)) {
            getline(ifs, author);
            ifs >> year;
            ifs.ignore();  // Ignore newline after year
            getline(ifs, isbn);
            books.emplace_back(title, author, year, isbn);
        }
        ifs.close();
        cout << "Library loaded from " << filename << "\n";
    }
};

// Template function to print any type with a message
template <typename T>
void printMessage(const string& msg, const T& value) {
    cout << msg << ": " << value << "\n";
}

int main() {
    Library lib;
    lib.loadFromFile("library.txt");

    bool running = true;
    while (running) {
        cout << "\nLibrary Management System Menu:\n";
        cout << "1. Add Book\n2. Show All Books\n3. Search by Title\n"
             << "4. Search by Author\n5. Save & Exit\n6. Exit without Saving\n";
        cout << "Enter your choice: ";
        int choice;
        cin >> choice;
        cin.ignore();

        switch (choice) {
            case 1: {
                string title, author, isbn;
                int year;
                cout << "Enter title: ";
                getline(cin, title);
                cout << "Enter author: ";
                getline(cin, author);
                cout << "Enter year: ";
                cin >> year;
                cin.ignore();
                cout << "Enter ISBN: ";
                getline(cin, isbn);
                lib.addBook(Book(title, author, year, isbn));
                break;
            }
            case 2:
                lib.showBooks();
                break;
            case 3: {
                string title;
                cout << "Enter title to search: ";
                getline(cin, title);
                lib.searchByTitle(title);
                break;
            }
            case 4: {
                string author;
                cout << "Enter author to search: ";
                getline(cin, author);
                lib.searchByAuthor(author);
                break;
            }
            case 5:
                lib.saveToFile("library.txt");
                running = false;
                break;
            case 6:
                running = false;
                break;
            default:
                cout << "Invalid choice, try again.\n";
        }
    }

    printMessage("Thank you for using the Library Management System", "");

    return 0;
}
