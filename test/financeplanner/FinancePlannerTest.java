package financeplanner;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//PROG6112 Assignment 1
//Appendix B - Unit Tests
//ST10478658
//Group 1
//Caitlyn Jirah Pillay
//mmmm


public class FinancePlannerTest 
{
    @Test
    public void testTransactionCreation() 
    {
        Transaction transaction = new ExpenseTransaction(LocalDate.of(2025, 9, 3), "Test Expense", "Test Description", 100.0, "Test Category");
    
        assertNotNull(transaction);
        assertEquals("Test Expense", transaction.getTitle());
        assertEquals(100.0, transaction.getAmount(), 0.001);
        assertEquals("Test Category", transaction.getCategory());
        
    }
    
    @Test
    public void testIncomeTransactionInheritance() 
    {
        IncomeTransaction income = new IncomeTransaction(LocalDate.of(2025, 9, 3),"Salary","Monthly salary",15000.0, "Salary");
        
        assertTrue(income instanceof Transaction);
        assertEquals("Salary", income.getCategory());
        assertEquals(15000.0, income.getAmount(), 0.001);
    }
    
    @Test
    public void testExpenseTransactionInheritance() 
    {
        ExpenseTransaction expense = new ExpenseTransaction(LocalDate.of(2025, 9, 3),"Rent","Monthly rent",5000.0,"Rent");
        
        assertTrue(expense instanceof Transaction);
        assertEquals("Rent", expense.getCategory());
        assertEquals(5000.0, expense.getAmount(), 0.001);
    }
    
    @Test
    public void testBudgetCategory() 
    {
        BudgetCategory category = new BudgetCategory("Expense", "Groceries", 2000.0);
        
        assertEquals("Expense", category.getType());
        assertEquals("Groceries", category.getName());
        assertEquals(2000.0, category.getBudget(), 0.001);
        
        // Test setter
        category.setBudget(2500.0);
        assertEquals(2500.0, category.getBudget(), 0.001);
    }
    
       @Test
    public void testTransactionDate() 
    {
        LocalDate testDate = LocalDate.of(2025, 9, 3);
        Transaction transaction = new ExpenseTransaction(testDate, "Test", "Test", 100.0, "Test");
        
        assertEquals(testDate, transaction.getDate());
    }
    
}
