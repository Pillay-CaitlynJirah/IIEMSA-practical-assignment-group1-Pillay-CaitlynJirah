/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tvseriesmanager;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.lang.reflect.Field;

//PROG6112 Assignment 1
//Appendix A - Section B (Unit Test)
//ST10478658
//Group 1
//Caitlyn Jirah Pillay

public class SeriesUnitTest 
{
    
    @Test
    
    public void TestSearchSeries() 
    {
        Series series = new Series();
        addTestSeries(series, "101", "Extreme Sports", "12", "10");
        
        
        SeriesModel foundSeries = searchSeriesById(series, "101");
        assertNotNull("Series should be found", foundSeries);
        assertEquals("Extreme Sports", foundSeries.seriesName);
        assertEquals("12", foundSeries.seriesAge);
        assertEquals("10", foundSeries.seriesNumberOfEpisodes);
        
        System.out.println("Series data successfully found");
    }
    
    @Test
    public void TestSearchSeries_SeriesNotFound() 
    {
        Series series = new Series();
        
        // Test passes if series is not found
        SeriesModel foundSeries = searchSeriesById(series, "999");
        assertNull("Series should not be found", foundSeries);
        
        System.out.println("Series not found");
    }
    
    @Test
    public void TestUpdateSeries() 
    {
        Series series = new Series();
        addTestSeries(series, "101", "Old Town", "12", "10");
        
        // Series updated
        boolean updateSuccess = updateSeries(series, "101", "Tree jump", "14", "15");
       
        assertEquals(true, updateSuccess);
        
        // Verify the updates
        SeriesModel updatedSeries = searchSeriesById(series, "101");
        assertEquals("Tree jump", updatedSeries.seriesName);
        assertEquals("14", updatedSeries.seriesAge);
        assertEquals("15", updatedSeries.seriesNumberOfEpisodes);
        
        System.out.println("Series successfully updated");
    }
    
    @Test
    public void TestDeleteSeries() 
    {
        Series series = new Series();
        addTestSeries(series, "101", "Test Series", "12", "10");
        
        // Delete the series
        boolean deleteSuccess = deleteSeries(series, "101", "y");
        
        assertEquals(true, deleteSuccess);
        
        
        SeriesModel foundSeries = searchSeriesById(series, "101");
        assertNull("Series should be deleted", foundSeries);
        
        System.out.println("Series successfully deleted");
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound() 
    {
        Series series = new Series();
        
        boolean deleteSuccess = deleteSeries(series, "999", "y");
        
        // Test passes if delete failed (series didn't exist)
        assertEquals(false, deleteSuccess);
        
        System.out.println("Series has not been deleted");
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid() 
    {
        assertEquals(true, isAgeValid("2"));
        assertEquals(true, isAgeValid("5"));
        assertEquals(true, isAgeValid("12"));
        assertEquals(true, isAgeValid("18"));
        
        System.out.println("Valid age restrictions accepted");
    }
    
    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() 
    {
        assertEquals(false, isAgeValid("1"));
        assertEquals(false, isAgeValid("19"));
        assertEquals(false, isAgeValid("0"));
        assertEquals(false, isAgeValid("twenty"));
        assertEquals(false, isAgeValid(""));
        
        System.out.println("Invalid age restrictions");
    }
    
    // Helper method to add test series
    private void addTestSeries(Series series, String id, String name, String age, String episodes) {
        try {
            Field field = Series.class.getDeclaredField("seriesList");
            field.setAccessible(true);
            ArrayList<SeriesModel> seriesList = (ArrayList<SeriesModel>) field.get(series);
            
            SeriesModel testSeries = new SeriesModel();
            testSeries.seriesId = id;
            testSeries.seriesName = name;
            testSeries.seriesAge = age;
            testSeries.seriesNumberOfEpisodes = episodes;
            
            seriesList.add(testSeries);
        } catch (Exception e) {
            fail("Could not add test series: " + e.getMessage());
        }
    }
    
    // Helper method to search for series
    private SeriesModel searchSeriesById(Series series, String id) {
        try {
            Field field = Series.class.getDeclaredField("seriesList");
            field.setAccessible(true);
            ArrayList<SeriesModel> seriesList = (ArrayList<SeriesModel>) field.get(series);
            
            for (SeriesModel s : seriesList) {
                if (s.seriesId.equals(id)) {
                    return s;
                }
            }
            return null;
        } catch (Exception e) {
            fail("Could not search series: " + e.getMessage());
            return null;
        }
    }
    
    // Helper method to update series
    private boolean updateSeries(Series series, String id, String name, String age, String episodes) 
    {
        try 
        {
            Field field = Series.class.getDeclaredField("seriesList");
            field.setAccessible(true);
            ArrayList<SeriesModel> seriesList = (ArrayList<SeriesModel>) field.get(series);
            
            for (SeriesModel s : seriesList) 
            {
                if (s.seriesId.equals(id)) 
                {
                    s.seriesName = name;
                    s.seriesAge = age;
                    s.seriesNumberOfEpisodes = episodes;
                    return true;
                }
            }
            return false;
        } catch (Exception e) 
        {
            fail("Could not update series: " + e.getMessage());
            return false;
        }
    }
    
    // Helper method to delete series
    private boolean deleteSeries(Series series, String id, String confirm) 
    {
        try 
        {
            Field field = Series.class.getDeclaredField("seriesList");
            field.setAccessible(true);
            ArrayList<SeriesModel> seriesList = (ArrayList<SeriesModel>) field.get(series);
            
            for (int i = 0; i < seriesList.size(); i++) 
            {
                if (seriesList.get(i).seriesId.equals(id)) 
                {
                    if (confirm.equalsIgnoreCase("y")) 
                    {
                        seriesList.remove(i);
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            fail("Could not delete series: " + e.getMessage());
            return false;
        }
    }
    
    // Helper method to validate age
    private boolean isAgeValid(String ageInput) 
    {
        try 
        {
            int age = Integer.parseInt(ageInput);
            return age >= 2 && age <= 18;
        } catch (NumberFormatException e) 
        {
            return false;
        }
    }
}
