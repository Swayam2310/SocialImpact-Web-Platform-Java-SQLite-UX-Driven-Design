package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>Sub Task 2.A</a>
                <a href='page2B.html'>Sub Task 2.B</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 3.A</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """


    <title>Temperature Analysis</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        h1, h2, h3 {
            color: #333;
        }

        label {
            font-weight: 700;
        }

        input[type="number"], select, input[type="text"] {
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
            width: 200px;
        }

        button {
            padding: 10px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        #result {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #fff;
        }
    </style>
    <script>
        function calculateAverage() {
            // Get user input values
            var startingYear = parseInt(document.getElementById("startingYear").value);
            var timePeriod = parseInt(document.getElementById("timePeriod").value);
            var region = document.getElementById("region").value;

            // Perform calculations and display the average temperature
            // Code for calculating the average temperature goes here

            // Display the average temperature on the page
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "Average temperature for the selected period: " + averageTemperature;
        }

        function compareAverages() {
            // Get user input values for starting years and regions
            var startingYears = [];
            var regions = [];
            // Code for getting user input values for starting years and regions goes here

            // Perform calculations and display the difference in averages
            // Code for calculating the difference in averages goes here

            // Display the difference in averages on the page
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "Difference in averages: " + differenceInAverages;
        }

        function compareRegions() {
            // Get user input values for regions
            var regions = [];
            // Code for getting user input values for regions goes here

            // Perform calculations and display the averages and difference in averages
            // Code for calculating the averages and difference in averages goes here

            // Display the averages and difference in averages on the page
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "Averages: " + averages + "<br>"
                + "Difference in averages: " + differenceInAverages;
        }

        function sortRegions() {
            // Get user input values for starting years and regions
            var startingYears = [];
            // Code for getting user input values for starting years goes here

            // Perform calculations and sort the regions by the difference in averages
            // Code for sorting the regions goes here

            // Display the sorted regions on the page
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "Sorted regions: " + sortedRegions;
        }

        function filterResults() {
            // Get user input values for population range and average temperature change
            var populationRange = document.getElementById("populationRange").value;
            var temperatureChange = document.getElementById("temperatureChange").value;

            // Perform calculations and filter the results
            // Code for filtering the results goes here

            // Display the filtered results on the page
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "Filtered results: " + filteredResults;
        }
    </script>


    <h1>Temperature Analysis</h1>

    <h2>Sub-Task A: Identify changes in temperature over extended periods</h2>

    <h3>Calculate Average Temperature</h3>
    <label for="startingYear">Starting Year:</label>
    <input type="number" id="startingYear">
    <label for="timePeriod">Time Period (in years):</label>
    <input type="number" id="timePeriod">
    <label for="region">Geographic Region:</label>
    <select id="region">
        <option value="global">Global</option>
        <option value="country">Country</option>
        <option value="state">State</option>
        <option value="city">City</option>
    </select>
    <button onclick="calculateAverage()">Calculate</button>

    <h3>Compare Averages</h3>
    <!-- Input fields for multiple starting years and regions go here -->
    <button onclick="compareAverages()">Compare</button>

    <h3>Compare Regions</h3>
    <!-- Input fields for multiple regions go here -->
    <button onclick="compareRegions()">Compare</button>

    <h3>Sort Regions</h3>
    <!-- Input fields for multiple starting years go here -->
    <button onclick="sortRegions()">Sort</button>

    <h3>Filter Results</h3>
    <label for="populationRange">Population Range:</label>
    <input type="text" id="populationRange">
    <label for="temperatureChange">Average Temperature Change:</label>
    <input type="text" id="temperatureChange">
    <button onclick="filterResults()">Filter</button>

    <div id="result"></div>


            """;

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
