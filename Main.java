import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Transaction implements Serializable {
    private String description;
    private double amount;
    private boolean isIncome;

    public Transaction(String description, double amount, boolean isIncome) {
        this.description = description;
        this.amount = amount;
        this.isIncome = isIncome;
    }

    @Override
    public String toString() {
        return String.format("%s$%.2f - %s", 
            isIncome ? "+" : "-", 
            amount,
            description);
    }

    public boolean isIncome() {
        return isIncome;
    }
    
    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class MoneyTracker {
    private ArrayList<Transaction> transactions;
    private double balance;
    private static final String SAVE_FILE = "transactions.dat";

    public MoneyTracker() {
        transactions = new ArrayList<>();
        balance = 0;
        loadTransactions();
    }

    @SuppressWarnings("unchecked")  // Add this annotation
    private void loadTransactions() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("\nWelcome to Money Manager!");
            System.out.println("A new transaction database will be created at: " + file.getAbsolutePath());
            System.out.println("Your transactions will be automatically saved there.\n");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> list = (ArrayList<?>) obj;
                if (list.isEmpty() || list.get(0) instanceof Transaction) {
                    transactions = (ArrayList<Transaction>) list;
                    recalculateBalance();
                } else {
                    throw new ClassNotFoundException("Stored data is not of type Transaction");
                }
            } else {
                throw new ClassNotFoundException("Stored data is not an ArrayList");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Starting with a fresh transaction history.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading previous transactions: " + e.getMessage());
            System.out.println("Starting with a fresh transaction history.");
            // Backup the corrupted file
            File backup = new File(SAVE_FILE + ".backup");
            file.renameTo(backup);
            System.out.println("Previous data file has been backed up to: " + backup.getAbsolutePath());
        }
    }

    private void saveTransactions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(transactions);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    private void recalculateBalance() {
        balance = 0;
        for (Transaction t : transactions) {
            if (t.isIncome()) {
                balance += t.getAmount();
            } else {
                balance -= t.getAmount();
            }
        }
    }

    public void addIncome(String description, double amount) {
        transactions.add(new Transaction(description, amount, true));
        balance += amount;
        saveTransactions();
    }

    public void addExpense(String description, double amount) {
        transactions.add(new Transaction(description, amount, false));
        balance -= amount;
        saveTransactions();
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            Transaction t = transactions.get(index);
            if (t.isIncome()) {
                balance -= t.getAmount();  // Use getter instead of direct field access
            } else {
                balance += t.getAmount();  // Use getter instead of direct field access
            }
            transactions.remove(index);
            saveTransactions();
        }
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to show.");
            return;
        }
        System.out.println("Transaction History:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, transactions.get(i));
        }
    }

    public void editDescription(int index, String newDescription) {
        if (index >= 0 && index < transactions.size()) {
            Transaction old = transactions.get(index);
            transactions.set(index, new Transaction(newDescription, old.getAmount(), old.isIncome()));
            saveTransactions();
        }
    }

    public void editAmount(int index, double newAmount) {
        if (index >= 0 && index < transactions.size()) {
            Transaction old = transactions.get(index);
            if (old.isIncome()) {
                balance = balance - old.getAmount() + newAmount;
            } else {
                balance = balance + old.getAmount() - newAmount;
            }
            transactions.set(index, new Transaction(old.getDescription(), newAmount, old.isIncome()));
            saveTransactions();
        }
    }
}

public class Main {
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
                // Alternative method if ANSI escape codes don't work
                // new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // If everything fails, just print new lines
            System.out.println("\n".repeat(50));
        }
    }

    public static void showBalance(double balance) {
        System.out.printf("Current Balance: $%.2f\n", balance);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoneyTracker tracker = new MoneyTracker();

        while (true) {
            clearScreen();
            System.out.println("=== Money Manager ===");
            System.out.println();
            showBalance(tracker.getBalance());
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Show Transactions");
            System.out.println("4. Edit Transaction");
            System.out.println("5. Exit");
            System.out.println();  // Add empty line
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        clearScreen();
                        showBalance(tracker.getBalance());
                        System.out.print("Enter amount: $");
                        double incomeAmount = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter income description: ");
                        String incomeDesc = scanner.nextLine();
                        tracker.addIncome(incomeDesc, incomeAmount);
                        System.out.print("Press Enter to continue...");
                        scanner.nextLine();
                        break;

                    case 2:
                        clearScreen();
                        showBalance(tracker.getBalance());
                        System.out.print("Enter amount: $");
                        double expenseAmount = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter expense description: ");
                        String expenseDesc = scanner.nextLine();
                        tracker.addExpense(expenseDesc, expenseAmount);
                        System.out.print("Press Enter to continue...");
                        scanner.nextLine();
                        break;

                    case 3:
                        clearScreen();  // Moved clearScreen here
                        showBalance(tracker.getBalance());
                        tracker.showTransactions();
                        System.out.println();  // Add empty line
                        System.out.print("Press Enter to continue...");
                        scanner.nextLine();
                        break;

                    case 4:
                        while (true) {
                            clearScreen();
                            showBalance(tracker.getBalance());
                            System.out.println("Edit Transaction Menu:");
                            System.out.println("1. Edit Description");
                            System.out.println("2. Edit Amount");
                            System.out.println("3. Delete Transaction");
                            System.out.println("4. Back to Main Menu");
                            System.out.print("\nChoose an option: ");

                            try {
                                int editChoice = Integer.parseInt(scanner.nextLine());
                                if (editChoice == 4) break;

                                clearScreen();
                                showBalance(tracker.getBalance());
                                tracker.showTransactions();
                                
                                if (tracker.getTransactions().isEmpty()) {
                                    System.out.println();
                                    System.out.print("Press Enter to continue...");
                                    scanner.nextLine();
                                    continue;
                                }

                                System.out.print("\nEnter transaction number: ");
                                int index = Integer.parseInt(scanner.nextLine()) - 1;
                                
                                if (index < 0 || index >= tracker.getTransactions().size()) {
                                    System.out.println("Invalid transaction number!");
                                } else {
                                    clearScreen();
                                    showBalance(tracker.getBalance());
                                    System.out.println("Selected Transaction:");
                                    System.out.println(tracker.getTransactions().get(index));
                                    System.out.println();

                                    switch (editChoice) {
                                        case 1:
                                            System.out.print("Enter new description: ");
                                            String newDesc = scanner.nextLine();
                                            tracker.editDescription(index, newDesc);
                                            System.out.println("Description updated successfully.");
                                            break;
                                        case 2:
                                            System.out.print("Enter new amount: $");
                                            double newAmount = Double.parseDouble(scanner.nextLine());
                                            tracker.editAmount(index, newAmount);
                                            System.out.println("Amount updated successfully.");
                                            break;
                                        case 3:
                                            System.out.print("Are you sure you want to delete this transaction? (y/n): ");
                                            if (scanner.nextLine().trim().toLowerCase().equals("y")) {
                                                tracker.deleteTransaction(index);
                                                System.out.println("Transaction deleted successfully.");
                                            } else {
                                                System.out.println("Deletion cancelled.");
                                            }
                                            break;
                                    }
                                }
                                System.out.println();
                                System.out.print("Press Enter to continue...");
                                scanner.nextLine();
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input!");
                                System.out.println();
                                System.out.print("Press Enter to continue...");
                                scanner.nextLine();
                            }
                        }
                        break;

                    case 5:
                        scanner.close();
                        return;

                    default:
                        System.out.println();  // Add empty line
                        System.out.println("Invalid option!");
                        System.out.print("Press Enter to continue...");
                        scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println();  // Add empty line
                System.out.println("Please enter a valid number!");
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }
}