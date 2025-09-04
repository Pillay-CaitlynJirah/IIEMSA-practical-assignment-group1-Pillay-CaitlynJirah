/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tvseriesmanager;

import java.util.Scanner;

//PROG6112 Assignment 1
//Appendix A - Section A
//ST10478658
//Group 1
//Caitlyn Jirah Pillay

public class TVSeriesManager 
{

       public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Series seriesManager = new Series();
        
        //Welcome Screen
        System.out.println("LATEST SERIES - 2025");
        System.out.println("*************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        
        String input = scanner.nextLine();
        if(!input.equals("1"))
        {
            System.out.println("Goodbye!");
            return;
        }
        
        // Main menu loop
        while (true) 
        {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter your choice: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) 
            {
                case "1":
                    seriesManager.CaptureSeries();
                    break;
                case "2":
                    seriesManager.SearchSeries();
                    break;
                case "3":
                    seriesManager.UpdateSeries();
                    break;
                case "4":
                    seriesManager.DeleteSeries();
                    break;
                case "5":
                    seriesManager.SeriesReport();
                    break;
                case "6":
                    seriesManager.ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
        //Ask if user wants to continue
        System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
        input = scanner.nextLine();
        
        if(!input.equals("1"))
        {
            System.out.println("Goodbye!!");
            break;
        }
        }
        
scanner.close();

    }
}
        

        
        
        
    
    

       
