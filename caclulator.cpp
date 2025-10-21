#include <iostream>

// Function to add two numbers
double add(double a, double b) {
    return a + b;
}

// Function to subtract two numbers
double subtract(double a, double b) {
    return a - b;
}

// Function to multiply two numbers
double multiply(double a, double b) {
    return a * b;
}

// Function to divide two numbers
double divide(double a, double b) {
    if (b != 0)
        return a / b;
    else {
        std::cout << "Error: Division by zero" << std::endl;
        return 0;
    }
}

int main() {
  // code body
    double num1, num2;
    char operation;

    std::cout << "Basic Calculator\n";
    std::cout << "Enter first number: ";
    std::cin >> num1;
    std::cout << "Enter an operation (+, -, *, /): ";
    std::cin >> operation;
    std::cout << "Enter second number: ";
    std::cin >> num2;

    double result;

    switch(operation) {
      //addition
        case '+':
            result = add(num1, num2);
            break;
      //subtraction
        case '-':
            result = subtract(num1, num2);
            break;
      //multiplication
        case '*':
            result = multiply(num1, num2);
            break;
      //division
        case '/':
            result = divide(num1, num2);
            break;
      //error handling
        default:
            std::cout << "Invalid operation entered." << std::endl;
            return 1;
    }
  //print the output
    std::cout << "Result: " << result << std::endl;

    return 0;
}
