/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeplanner;

import java.time.LocalDate;

//PROG6112 Assignment 1
//Appendix B
//ST10478658
//Group 1
//Caitlyn Jirah Pillay 
public class Transaction 
{
    private LocalDate date;
    private String title;
    private String description;
    private double amount;
    private String category;
    
    
    public Transaction(LocalDate date, String title, String description, double amount, String category) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public LocalDate getDate() 
    {
        return date;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getDescription() 
    {
        return description;
    }

    public double getAmount() 
    {
        return amount;
    }

    public String getCategory() 
    {
        return category;
    }
    
    
    
}
