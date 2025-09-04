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

 class IncomeTransaction extends Transaction
{
    
    public IncomeTransaction(LocalDate date, String title, String description, double amount, String category) 
    {
        super(date, title, description, amount, category);
    }
    
}
