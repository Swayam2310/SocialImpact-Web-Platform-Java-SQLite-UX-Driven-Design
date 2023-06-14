package app;

import java.util.ArrayList;

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

        JDBCConnection jdbcConnection = new JDBCConnection();
        jdbcConnection.getListOfCityTemp(2012, "Tirana");
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
                System.out.println(cityName);
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
