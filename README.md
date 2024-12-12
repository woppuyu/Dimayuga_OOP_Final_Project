# Money Manager

## Project Overview
Money Manager is a comprehensive Java-based financial tracking application designed to promote responsible financial management and economic sustainability. The application enables users to track their income and expenses, helping them make informed financial decisions through a simple yet powerful interface.

## Object-Oriented Programming Principles Applied

### 1. Encapsulation
- **Transaction Class**: Private fields (description, amount, isIncome) with public getters ensure data integrity
- **MoneyTracker Class**: Encapsulates the transaction list and balance, controlling access through specific methods
- All data modifications are handled through well-defined public interfaces

### 2. Inheritance
- Implementation of `Serializable` interface in Transaction class for object persistence
- Utilization of Java's built-in class hierarchies (ArrayList, Scanner)

### 3. Abstraction
- **MoneyTracker Class**: Abstracts complex file I/O operations and transaction management
- Clear separation between data management (MoneyTracker), data structure (Transaction), and user interface (Main)

### 4. Polymorphism
- Method overriding: Custom implementation of `toString()` in Transaction class
- Type polymorphism through interface implementation (Serializable)

## Sustainable Development Goal Integration
This project primarily aligns with **SDG 12: Responsible Consumption and Production**, while also supporting aspects of SDG 8: Decent Work and Economic Growth.

### Primary Focus: SDG 12 - Responsible Consumption and Production
Our Money Manager application supports sustainable consumption patterns through:

1. **Consumption Awareness**
   - Detailed tracking of spending patterns
   - Categorization of expenses
   - Visual representation of consumption habits

2. **Sustainable Decision Making**
   - Helps users identify unnecessary expenses
   - Promotes mindful spending
   - Enables better budget planning

3. **Waste Reduction**
   - Identifies areas of financial waste
   - Helps optimize resource allocation
   - Encourages sustainable financial habits

4. **Environmental Impact**
   - Paperless transaction tracking
   - Digital receipt management
   - Reduced need for physical financial documentation

### Secondary Connection: SDG 8
The project also supports economic growth through:
- Financial literacy promotion
- Personal banking skills development
- Economic decision-making support

## Features
- Add and track income/expense transactions
- View comprehensive transaction history
- Edit transaction details (description and amount)
- Delete transactions
- Automatic data persistence
- Clear screen support for better user experience
- Robust error handling and data validation

## Running the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Installation and Execution

#### Method 1: Direct Download (Recommended)
1. Download the `Main.java` file from the repository
2. Open terminal/command prompt and navigate to the download location:
```bash
cd path/to/download/folder
```
3. Compile and run:
```bash
javac Main.java
java Main
```

#### Method 2: Clone Repository
1. Clone the repository (if you want access to full project history):
```bash
git clone [repository-url]
cd [repository-name]
```
2. Follow the same compilation and execution steps as above

## Technical Implementation

### Class Structure
1. **Transaction Class**
   - Represents individual financial transactions
   - Properties:
     - description (String)
     - amount (double)
     - isIncome (boolean)
   - Implements Serializable for data persistence

2. **MoneyTracker Class**
   - Core business logic handler
   - Manages:
     - Transaction storage
     - File operations
     - Balance calculations
     - Data validation

3. **Main Class**
   - User interface implementation
   - Input handling
   - Menu system
   - Screen management

### Data Persistence
- Automatic saving to `transactions.dat`
- Backup system for data protection
- Error recovery mechanisms

## Error Handling
- Input validation for numerical values
- File operation error management
- Corrupt data protection
- Automatic backup creation

## Contributing
Contributions are welcome. Please follow these steps:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is created for educational purposes as part of an OOP course final project.
