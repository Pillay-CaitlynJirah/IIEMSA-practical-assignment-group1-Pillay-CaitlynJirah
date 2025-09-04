/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package financeplanner;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//PROG6112 Assignment 1
//Appendix B
//ST10478658
//Group 1
//Caitlyn Jirah Pillay


public class FinancePlanner 
{
    // Arrays to store financial data
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<BudgetCategory> budgetCategories = new ArrayList<>();
    
    // Scanner for user input
    private static Scanner scanner = new Scanner(System.in);
    
    // Date formatter
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) 
    {
        initializeData();
        displayWelcomeMessage();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) 
            {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    generateFinancialReport();
                    break;
                case 4:
                    manageBudgetCategories();
                    break;
                case 5:
                    running = false;
                    System.out.println("\nThank you for using Personal Finance Planner!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    
    private static void initializeData() 
    {
        //Budget categories
        budgetCategories.add(new BudgetCategory("Income", "Salary", 0.0));
        budgetCategories.add(new BudgetCategory("Income", "Freelance", 0.0));
        budgetCategories.add(new BudgetCategory("Expense", "Rent", 5000.0));
        budgetCategories.add(new BudgetCategory("Expense", "Groceries", 2000.0));
        budgetCategories.add(new BudgetCategory("Expense", "Entertainment", 1000.0));
        budgetCategories.add(new BudgetCategory("Expense", "Transport", 800.0));
        
        // Add some sample transactions
        transactions.add(new IncomeTransaction(LocalDate.now().minusDays(5), "Salary", "Monthly salary", 15000.0, "Salary"));
        transactions.add(new IncomeTransaction(LocalDate.now().minusDays(10), "Freelance", "Web development project", 2500.0, "Freelance"));
        transactions.add(new ExpenseTransaction(LocalDate.now().minusDays(2), "Rent", "Monthly rent payment", 5000.0, "Rent"));
        transactions.add(new ExpenseTransaction(LocalDate.now().minusDays(1), "Groceries", "Weekly shopping", 1800.0, "Groceries"));
        transactions.add(new ExpenseTransaction(LocalDate.now(), "Entertainment", "Movie tickets", 350.0, "Entertainment"));
    }
    
    //Display welcome message
    private static void displayWelcomeMessage() 
    {
        System.out.println("==============================================");
        System.out.println("      PERSONAL FINANCE PLANNER APPLICATION");
        System.out.println("==============================================");
        System.out.println("Track your income and expenses efficiently!");
        System.out.println("Generate detailed financial reports.");
        System.out.println("Stay on top of your budgeting goals.");
        System.out.println("==============================================");
    }
    
    //Display main menu
    private static void displayMainMenu() 
    {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Add New Transaction");
        System.out.println("2. View All Transactions");
        System.out.println("3. Generate Financial Report");
        System.out.println("4. Manage Budget Categories");
        System.out.println("5. Exit");
        System.out.println("=====================");
    }
    
    //Add new transaction (Income OR Expense)
    private static void addTransaction() 
    {
        System.out.println("\n===== ADD NEW TRANSACTION =====");
        System.out.println("1. Income");
        System.out.println("2. Expense");
        int typeChoice = getIntInput("Select transaction type: ");
        
        if (typeChoice != 1 && typeChoice != 2) 
        {
            System.out.println("Invalid choice. Returning to main menu.");
            return;
        }
        
        // Get transaction details
        LocalDate date = getDateInput("Enter date (YYYY-MM-DD): ");
        String title = getStringInput("Enter title: ");
        String description = getStringInput("Enter description: ");
        double amount = getDoubleInput("Enter amount: ");
        
        // Display categories for selection
        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < budgetCategories.size(); i++) 
        {
            BudgetCategory category = budgetCategories.get(i);
            if ((typeChoice == 1 && category.getType().equals("Income")) || (typeChoice == 2 && category.getType().equals("Expense"))) 
            {
                System.out.println((i+1) + ". " + category.getName());
            }
        }
        
        int categoryIndex = getIntInput("Select category number: ") - 1;
        
        if (categoryIndex < 0 || categoryIndex >= budgetCategories.size()) 
        {
            System.out.println("Invalid category selection.");
            return;
        }
        
        BudgetCategory selectedCategory = budgetCategories.get(categoryIndex);
        
        // Appropriate transaction
        Transaction transaction;
        if (typeChoice == 1) 
        {
            transaction = new IncomeTransaction(date, title, description, amount, selectedCategory.getName());
        } 
        else 
        {
            transaction = new ExpenseTransaction(date, title, description, amount, selectedCategory.getName());
        }
        
        transactions.add(transaction);
        System.out.println("Transaction added successfully!");
    }
    
    //View all transactions
    private static void viewTransactions() 
    {
        System.out.println("\n===== ALL TRANSACTIONS =====");
        
        if (transactions.isEmpty()) 
        {
            System.out.println("No transactions found.");
            return;
        }
        
        // Sort transactions by date (newest first)
        transactions.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));
        
        System.out.printf("%-12s %-10s %-20s %-15s %-10s%n","Date", "Type", "Title", "Amount", "Category");
        System.out.println("----------------------------------------------------------------");
        
        for (Transaction transaction : transactions) 
        {
            String type = transaction instanceof IncomeTransaction ? "Income" : "Expense";
            System.out.printf("%-12s %-10s %-20s R%-14.2f %-10s%n",
                             transaction.getDate().format(dateFormatter), type, transaction.getTitle(), transaction.getAmount(), transaction.getCategory());
        }
    }
    
    //Generate financial report for specific month
    private static void generateFinancialReport() 
    {
        System.out.println("\n===== GENERATE FINANCIAL REPORT =====");
        int year = getIntInput("Enter year (e.g., 2025): ");
        int month = getIntInput("Enter month (1-12): ");
        
        // Filter transactions for the selected month and year
        ArrayList<Transaction> monthlyTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) 
        {
            LocalDate date = transaction.getDate();
            if (date.getYear() == year && date.getMonthValue() == month) 
            {
                monthlyTransactions.add(transaction);
            }
        }
        
        if (monthlyTransactions.isEmpty()) 
        {
            System.out.println("No transactions found for " + month + "/" + year);
            return;
        }
        
        // Calculate totals
        double totalIncome = 0;
        double totalExpenses = 0;
        
        Map<String, Double> incomeByCategory = new HashMap<>();
        Map<String, Double> expensesByCategory = new HashMap<>();
        
        for (Transaction transaction : monthlyTransactions) 
        {
            if (transaction instanceof IncomeTransaction) 
            {
                totalIncome += transaction.getAmount();
                incomeByCategory.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
            } 
            else 
            {
                totalExpenses += transaction.getAmount();
                expensesByCategory.merge(transaction.getCategory(), transaction.getAmount(), Double::sum);
            }
        }
        
        double netSavings = totalIncome - totalExpenses;
        
        // Display the report
        System.out.println("\n==============================================");
        System.out.println("        FINANCIAL REPORT - " + month + "/" + year);
        System.out.println("==============================================");
        
        System.out.println("\nINCOME");
        System.out.println("----------------------------------------------");
        for (Map.Entry<String, Double> entry : incomeByCategory.entrySet()) 
        {
            System.out.printf("%-20s R%.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("----------------------------------------------");
        System.out.printf("Total Income:         R%.2f%n", totalIncome);
        
        System.out.println("\nEXPENSES");
        System.out.println("----------------------------------------------");
        for (Map.Entry<String, Double> entry : expensesByCategory.entrySet()) 
        {
            String category = entry.getKey();
            double spent = entry.getValue();
            double budget = getBudgetForCategory(category);
            
            System.out.printf("%-20s R%.2f", category, spent);
            if (budget > 0) 
            {
                System.out.printf(" (Budget: R%.2f)", budget);
                if (spent > budget) 
                {
                    System.out.print(" **OVER BUDGET**");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------");
        System.out.printf("Total Expenses:       R%.2f%n", totalExpenses);
        
        System.out.println("\nSUMMARY");
        System.out.println("----------------------------------------------");
        System.out.printf("Net Savings:          R%.2f%n", netSavings);
        System.out.println("==============================================");
    }
    
    //Get budget for specific category
    private static double getBudgetForCategory(String categoryName) 
    {
        for (BudgetCategory category : budgetCategories) 
        {
            if (category.getName().equals(categoryName)) 
            {
                return category.getBudget();
            }
        }
        return 0.0;
    }
    
    //Manage Budget categories
    private static void manageBudgetCategories() 
    {
        boolean managing = true;
        while (managing) 
        {
            System.out.println("\n===== MANAGE BUDGET CATEGORIES =====");
            System.out.println("1. View Categories");
            System.out.println("2. Add Category");
            System.out.println("3. Edit Category Budget");
            System.out.println("4. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) 
            {
                case 1:
                    viewCategories();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    editCategoryBudget();
                    break;
                case 4:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    //View all Budget categories
    private static void viewCategories() 
    {
        System.out.println("\n===== BUDGET CATEGORIES =====");
        System.out.printf("%-10s %-20s %-10s%n", "Type", "Name", "Budget");
        System.out.println("----------------------------------------");
        
        for (BudgetCategory category : budgetCategories) 
        {
            System.out.printf("%-10s %-20s R%-9.2f%n", category.getType(), category.getName(), category.getBudget());
        }
    }
    
    //Add a new budget category
    private static void addCategory() 
    {
        System.out.println("\n===== ADD NEW CATEGORY =====");
        System.out.println("1. Income Category");
        System.out.println("2. Expense Category");
        int typeChoice = getIntInput("Select category type: ");
        
        if (typeChoice != 1 && typeChoice != 2) 
        {
            System.out.println("Invalid choice.");
            return;
        }
        
        String type = (typeChoice == 1) ? "Income" : "Expense";
        String name = getStringInput("Enter category name: ");
        double budget = getDoubleInput("Enter budget amount (0 for no budget): ");
        
        budgetCategories.add(new BudgetCategory(type, name, budget));
        System.out.println("Category added successfully!");
    }
    
    //Edit category budget
    private static void editCategoryBudget() 
    {
        viewCategories();
        int categoryIndex = getIntInput("Enter the number of the category to edit: ") - 1;
        
        if (categoryIndex < 0 || categoryIndex >= budgetCategories.size()) 
        {
            System.out.println("Invalid category selection.");
            return;
        }
        
        BudgetCategory category = budgetCategories.get(categoryIndex);
        double newBudget = getDoubleInput("Enter new budget amount for " + category.getName() + ": ");
        
        category.setBudget(newBudget);
        System.out.println("Budget updated successfully!");
    }
    
    // Utility methods for input handling
    
    private static String getStringInput(String prompt) 
    {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) 
    {
        while (true) 
        {
            try 
            {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } 
            catch (NumberFormatException e) 
            {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) 
    {
        while (true) 
        {
            try 
            {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) 
            {
                System.out.println("Please enter a valid amount.");
            }
        }
    }
    
    private static LocalDate getDateInput(String prompt) 
    {
        while (true) 
        {
            try 
            {
                System.out.print(prompt);
                String dateString = scanner.nextLine();
                return LocalDate.parse(dateString, dateFormatter);
            } 
            catch (Exception e) 
            {
                System.out.println("Please enter a valid date in YYYY-MM-DD format.");
            }
        }
    }
}
