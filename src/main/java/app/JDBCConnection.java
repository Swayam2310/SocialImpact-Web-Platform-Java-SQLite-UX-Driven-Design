package app;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

      public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the year to select: ");
        int yearToSelect = scanner.nextInt();

        System.out.print("Enter the city name to select: ");
        String cityNameToSelectFor = scanner.next();

        scanner.close();

        JDBCConnection jdbcConnection = new JDBCConnection();
        jdbcConnection.getListOfCityTemp(yearToSelect, cityNameToSelectFor);
    }

    // Name of database file (contained in database folder)
    //public static final String DATABASE = "jdbc:sqlite:database/ctg.db";
     public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    static int startTempYear;

    public static int getStartTempYear(){
        Connection connection = null;
        
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Year FROM GlobalTempObservation ORDER BY ROWID ASC LIMIT 1";
            
            //

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
                 while (results.next()) {
                //     // Lookup the columns we need
                     startTempYear  = results.getInt("Year");
                    

            // Close the statement because we are done with it
            statement.close();
              } 
             
            }
              catch (SQLException e) {

            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {

            // Safety code to cleanup

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return startTempYear;
    }

    static int endTempYear;

    public static int getEndTempYear(){
        Connection connection = null;
        
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT Year FROM GlobalTempObservation ORDER BY ROWID DESC LIMIT 1";
            

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
                 while (results.next()) {
                //     // Lookup the columns we need
                     endTempYear  = results.getInt("Year");
                     

            // Close the statement because we are done with it
            statement.close();
              } 
             
            }
              catch (SQLException e) {

            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {

            // Safety code to cleanup

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return endTempYear;
    }
    // Method to get the first and last year's temps from entire temperature dataset
    static float startTotalTempRange;
    static float endTotalTempRange;
    static String totalTempRange;

    public static String getTotalTempRange(){
        Connection connection = null;
        
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AvgTemp FROM GlobalTempObservation ORDER BY ROWID ASC LIMIT 1";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
                 while (results.next()) {
                   // Lookup the columns we need
                     startTotalTempRange  = results.getFloat("AvgTemp");
                     

                 }

            // The Query 2
            query = "SELECT AvgTemp FROM GlobalTempObservation ORDER BY ROWID DESC LIMIT 1";
            
            // Get Result 2
            results = statement.executeQuery(query);

            // Process all of the results 2
                 while (results.next()) {
                   // Lookup the columns we need 2
                     endTotalTempRange  = results.getFloat("AvgTemp");
                     

            // Close the statement because we are done with it 2
            statement.close();
              } 
             
            }
              catch (SQLException e) {

            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {

            // Safety code to cleanup

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        totalTempRange = startTotalTempRange + " - " + endTotalTempRange; 

        return totalTempRange;
    }
    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<CityTemp> getListOfCityTemp(int yearToSelect, String cityNameToSelectFor) {
        // Create the ArrayList of LGA objects to return
        ArrayList<CityTemp> listOfCityTemp = new ArrayList<CityTemp>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            System.out.println("connection status, closed?:"+ connection.isClosed());

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AverageTemperature, city,Year FROM CITY WHERE year="+yearToSelect +" and City=\""+cityNameToSelectFor+"\"";
            System.out.println(query);
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                double aveTemp = results.getDouble("AverageTemperature");
                String cityName  = results.getString("city");
                int yearOfTemp  = results.getInt("year");

                System.out.println(aveTemp);
                System.out.println("City:"+cityName);
                System.out.println(yearOfTemp);

                String cityCode = cityName;


                // Create a CityTemp Object
                CityTemp cityData = new CityTemp(cityCode, cityName, yearOfTemp, aveTemp);

                // Add the lga object to the array
                listOfCityTemp.add(cityData);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return listOfCityTemp;
    }
        
    // TODO: Add your required methods here
}
