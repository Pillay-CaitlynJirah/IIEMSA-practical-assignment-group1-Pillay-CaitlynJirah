/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tvseriesmanager;

import java.util.ArrayList;
import java.util.Scanner;

//PROG6112 Assignment 1
//Appendix A - Section A
//ST10478658
//Group 1
//Caitlyn Jirah Pillay 

public class Series 
{
    private ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    //CaptureSeries Method
    public void CaptureSeries()
    {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("***************************");
        
        SeriesModel newSeries = new SeriesModel();
        
        //Get series ID
        System.out.print("Enter the series ID: ");
        newSeries.seriesId = scanner.nextLine();
        
        //Get series name
        System.out.print("Enter the series name: ");
        newSeries.seriesName = scanner.nextLine();
        
        //Get age restriction
        while(true)
        {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();
            
            //Age restriction must be between 2 - 18, otherwise it is incorrect
            try
            {
                int age = Integer.parseInt(ageInput);
                if (age>= 2 && age<= 18)
                {
                    newSeries.seriesAge = ageInput;
                    break;
                }
                else
                {
                    System.out.println("You have entered an incorrect series age!!");
                    System.out.print("Please re-enter the series age >>>");
                }
            }
            catch (NumberFormatException e)
                    {
                    System.out.println("You have entered an incorrect series age!!");
                    System.out.print("Please re-enter the series age >>>");
                    }  
        }
        
        //Get the number of episodes for series
        System.out.print("Enter the number of episodes for " + newSeries.seriesName + " : ");
        newSeries.seriesNumberOfEpisodes = scanner.nextLine();
        
        seriesList.add(newSeries);
        System.out.println("Series processed successfully!!! ");
    }
    
    //SearchSeries Method
    public void SearchSeries()
    {
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.seriesId.equals(searchId)) {
                System.out.println("---");
                System.out.println("SERIES ID: " + series.seriesId);
                System.out.println("SERIES NAME: " + series.seriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.seriesAge);
                System.out.println("SERIES NUMBER OF EPISODES: " + series.seriesNumberOfEpisodes);
                System.out.println("---");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("---");
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("---");
        }
    }
    
    //UpdateSeries Method
    public void UpdateSeries() 
            {
        System.out.print("\nEnter the series id to update: ");
        String updateId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) 
        {
            if (series.seriesId.equals(updateId)) 
            {
                System.out.print("Enter the series name: ");
                series.seriesName = scanner.nextLine();
                
                // Keep asking for age until we get a valid one
                while (true) 
                {
                    System.out.print("Enter the age restriction: ");
                    String ageInput = scanner.nextLine();
                    
                    try {
                        int age = Integer.parseInt(ageInput);
                        if (age >= 2 && age <= 18) 
                        {
                            series.seriesAge = ageInput;
                            break;
                        } 
                        else 
                        {
                            System.out.println("You have entered an incorrect series age!!!");
                            System.out.print("Please re-enter the series age >> ");
                        }
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.out.println("You have entered an incorrect series age!!!");
                        System.out.print("Please re-enter the series age >> ");
                    }
                }
                
                System.out.print("Enter the number of episodes: ");
                series.seriesNumberOfEpisodes = scanner.nextLine();
                
                found = true;
                System.out.println("Series updated successfully!");
                break;
            }
        }
        
        if (!found) 
        {
            System.out.println("Series with Series Id: " + updateId + " was not found!");
        }
    }

    // DeletSeries Method
    public void DeleteSeries() 
    {
        System.out.print("\nEnter the series id to delete: ");
        String deleteId = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < seriesList.size(); i++) 
        {
            if (seriesList.get(i).seriesId.equals(deleteId)) 
            {
                System.out.print("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete: ");
                String confirm = scanner.nextLine();
                
                if (confirm.equalsIgnoreCase("y")) 
                {
                    seriesList.remove(i);
                    System.out.println("---");
                    System.out.println("Series with Series Id: " + deleteId + " WAS deleted!");
                    System.out.println("---");
                } 
                else 
                {
                    System.out.println("Delete cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) 
        {
            System.out.println("Series with Series Id: " + deleteId + " was not found!");
        }
    }

    // SeriesReport Method
    public void SeriesReport() 
    {
        System.out.println("\nSERIES REPORT - 2025");
        System.out.println("====================");
        
        if (seriesList.isEmpty()) 
        {
            System.out.println("No series found in the system.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) 
        {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("---");
            System.out.println("SERIES ID: " + series.seriesId);
            System.out.println("SERIES NAME: " + series.seriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.seriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.seriesNumberOfEpisodes);
            System.out.println("---");
        }
    }
    
    //ExitSeriesApplication Method
    public void ExitSeriesApplication()
    {
        System.out.println("Thank you for using TV series manager. Goodbye!!");
        System.exit(0);
    }
}
