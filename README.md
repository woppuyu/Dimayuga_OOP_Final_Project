# Money Manager

A Java-based personal finance tracking application that helps users manage their income and expenses.

## Features

- Add income and expense transactions
- View transaction history
- Edit existing transactions (description and amount)
- Delete transactions
- Automatic saving of transactions to file
- Persistent data storage between sessions
- Clear screen support for both Windows and Unix-based systems
- Error handling and data validation

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Running the Application

1. Compile the Java files:
```bash
javac Main.java
```

2. Run the program:
```bash
java Main
```

## Usage

The application presents a menu-driven interface with the following options:

1. **Add Income**: Record new income transactions
2. **Add Expense**: Record new expense transactions
3. **Show Transactions**: View all recorded transactions
4. **Edit Transaction**: Modify or delete existing transactions
   - Edit Description
   - Edit Amount
   - Delete Transaction
5. **Exit**: Close the application

## Data Storage

- Transactions are automatically saved to a file named `transactions.dat`
- The program creates a backup of corrupted data files with `.backup` extension
- Data persists between program sessions

## Implementation Details

### Classes

1. **Transaction**
   - Stores individual transaction details
   - Properties: description, amount, isIncome
   - Implements Serializable for data persistence

2. **MoneyTracker**
   - Manages transaction operations
   - Handles file I/O
   - Maintains current balance

3. **Main**
   - Contains the user interface
   - Handles user input and program flow

### Error Handling

- Input validation for numeric values
- File operation error handling
- Data corruption protection
- Backup creation for corrupted files

## Contributing

This project was created as part of an OOP course final project. Contributions and suggestions are welcome.

## License

This project is for educational purposes.
