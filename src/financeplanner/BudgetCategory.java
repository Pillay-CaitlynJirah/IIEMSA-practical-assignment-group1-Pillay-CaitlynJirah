/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package financeplanner;

//PROG6112 Assignment 1
//Appendix B
//ST10478658
//Group 1
//Caitlyn Jirah Pillay

public class BudgetCategory 
{
    private String type;
    private String name;
    private double budget;

    public BudgetCategory(String type, String name, double budget) 
    {
        this.type = type;
        this.name = name;
        this.budget = budget;
    }

    public String getType() 
    {
        return type;
    }

    public String getName() 
    {
        return name;
    }

    public double getBudget() 
    {
        return budget;
    }

    public void setBudget(double budget) 
    {
        this.budget = budget;
    }
    
    
    
    
    
    
    
}
