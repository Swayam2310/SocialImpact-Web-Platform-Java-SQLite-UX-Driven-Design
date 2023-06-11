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
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + """ 
  <meta name='viewport' content='width=device-width, initial-scale=1.0'>
  <title>Climate Change Homepage</title>
  <link rel='stylesheet' href='styles.css'>
  <style>
    /* Your CSS styles here */
    body {
      background-color: #f5f5f5;
      color: #333;
      font-family: Arial, sans-serif;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      padding: 20px;
    }

    h1 {
      color: #ff6600;
      font-size: 36px;
      text-align: center;
      margin-top: 0;
    }

    p {
      line-height: 1.5;
    }
    
    /* Additional CSS styles for your home page */

    .hero {
      text-align: center;
      margin-bottom: 20px;
    }

    .hero img {
      max-width: 100%;
    }
  </style>

    <meta charset='UTF-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1.0'>
    <title>Climate Change Website</title>
    <link rel='stylesheet' href='styles.css'>
    <style>
        /* Your CSS styles here */
        body {
            background-color: #f5f5f5;
            color: #333;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            color: #0077b3;
            font-size: 36px;
            text-align: center;
            margin-top: 0;
        }

        p {
            line-height: 1.5;
        }
    </style>;""";
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
                <h1>
                    <img src='logo.png' class='top-image' alt='RMIT logo' height='75'>
                    Homepage
                </h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
            <p></p>
            """;
        
        // Add HTML for the LGA list
        html = html + "<h1></h1>" + "";
        html = html + """
      
    <div class="container">
        <h1>Welcome to our Climate Change Website!</h1>
        <p>
            Explore the data and insights on global population and temperature changes over the years.
        </p>

    
    <section class="hero">
      <img src="Stormy.jpg" alt="Climate Change Hero Image">
    </section>

    <section>
      <h2>About Climate Change</h2>
      <p>Climate change refers to long-term shifts in temperature and weather patterns on Earth. It is primarily caused by human activities, such as the burning of fossil fuels, deforestation, and industrial processes. These activities release greenhouse gases into the atmosphere, trapping heat and leading to global warming.</p>
      <p>The consequences of climate change are far-reaching and include rising sea levels, extreme weather events, loss of biodiversity, and disruptions to ecosystems. It poses significant threats to human health, food security, and the overall stability of our planet.</p>
    </section>

    <section>
      <h2>Our Mission</h2>
      <p>At Our Climate Change Website, our mission is to raise awareness about climate change, educate people about its causes and effects, and empower individuals to take action to mitigate and adapt to its impacts. We believe that collective efforts and informed choices can make a difference in preserving our planet for future generations.</p>
    </section>

    <section>
      <h2>Get Involved</h2>
      <p>Join us in the fight against climate change and make a positive impact:</p>
      <ul>
        <li>Learn about the causes and effects of climate change.</li>
        <li>Adopt sustainable practices in your daily life.</li>
        <li>Support organizations working towards environmental conservation.</li>
        <li>Advocate for climate-friendly policies and practices.</li>
      </ul>
    </section>

  </div>

  <div class="hero" style ="color: orange;">
    <h2><b>“Look deep into nature, and then you will understand everything better.”</b>
    — <u> Albert Einstein</u></h2>
    <p>Take action today for a sustainable future</p>
    <a href="about.html" class="cta-button">Learn More</a>
  </div>
<div class="container">
  <div class="content" style="color: black;">
    <h2>More About Climate Change </h2>
    <p>We are dedicated to raising awareness about climate change and providing valuable insights into the global environmental challenges we face today. Our website serves as a platform for learning, sharing information, and taking action to combat climate change.</p>

<p>Climate change refers to long-term shifts in temperature and weather patterns on Earth, primarily caused by human activities such as burning fossil fuels, deforestation, and industrial processes. These activities release greenhouse gases into the atmosphere, trapping heat and leading to global warming. </p>
<p>The consequences of climate change are far-reaching and include rising sea levels, extreme weather events, loss of biodiversity, and disruptions to ecosystems. It poses significant threats to human health, food security, and the overall stability of our planet.</p>
    <p>The consequences of climate change are far-reaching and include rising sea levels, extreme weather events, loss of biodiversity, and disruptions to ecosystems. It poses significant threats to human health, food security, and the overall stability of our planet.</p>
  
  </div>
</div>


                """;

        html = html + """
           
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #012021;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1100px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            /*box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);*/
        }

        h1 {
            color: #0077b3;
            font-size: 36px;
            text-align: center;
            margin-top: 0;
        }

        p {
            line-height: 1.5;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        button[type="submit"] {
            background-color: #0077b3;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #005580;
        }

        .summary {
            margin-top: 20px;
            font-size: 16px;
            line-height: 1.5;
        }

        .summary span {
            font-weight: bold;
        }
    </style>

    <div class="container">
        <h1>Looking forward to the Webiste!</h1>
        <p>
            Explore the data and insights on global population and temperature changes over the years.
        </p>

        <h2>Data Summary</h2>
        <form>
            <p>
                Population Data:
                <input type="text" id="populationFirstYear" placeholder="First Year">
                -
                <input type="text" id="populationLastYear" placeholder="Last Year"> <br>
                <p> World Population: 5 billion - 8 billion  <br>
                Total Years: <span id="populationTotalYears"></span>
                </p>
            </p>
            <p>
                Temperature Data:
                <input type="text" id="temperatureFirstYear" placeholder="First Year">
                -
                <input type="text" id="temperatureLastYear" placeholder="Last Year"> <br>
                <p> Global Temperature: 5°C - 32°C <br>
                Total Years: <span id="temperatureTotalYears"></span>
                </p>
            </p>
            <button type="SUBMIT">Submit</button>
        </form>

        <div class="summary">
            <h3>Summary</h3>
            <p>
                Population Data: <span id="populationRange">2 Million</span> |
                World Population: <span id="worldPopulation">8 Million</span> |
                Total Years: <span id="populationYears">20 Years</span>
            </p>
            <p>
                Temperature Data: <span id="temperatureRange">10°C - 25°C</span> |
                Global Temperature: <span id="globalTemperature">20°C</span> |
                Total Years: <span id="temperatureYears">20 Years</span>
            </p>
        </div>

        <script>
            // Function to handle form submission
            function submitForm(event) {
                event.preventDefault(); // Prevent the default form submission behavior

                // Get input values
                var populationFirstYear = document.getElementById("populationFirstYear").value;
                var populationLastYear = document.getElementById("populationLastYear").value;
                var temperatureFirstYear = document.getElementById("temperatureFirstYear").value;
                var temperatureLastYear = document.getElementById("temperatureLastYear").value;

                // Perform any desired calculations or validations

                // Update the summary with the calculated values
                document.getElementById("populationRange").textContent = populationFirstYear + " - " + populationLastYear;
                document.getElementById("worldPopulation").textContent = "X billion - Y billion";
                document.getElementById("populationYears").textContent = populationLastYear - populationFirstYear;

                document.getElementById("temperatureRange").textContent = temperatureFirstYear + " - " + temperatureLastYear;
                document.getElementById("globalTemperature").textContent = "A°C - B°C";
                document.getElementById("temperatureYears").textContent = temperatureLastYear - temperatureFirstYear;

                // Clear the input fields
                document.getElementById("populationFirstYear").value = "";
                document.getElementById("populationLastYear").value = "";
                document.getElementById("temperatureFirstYear").value = "";
                document.getElementById("temperatureLastYear").value = "";
            }

            // Attach submitForm function to the form's submit event
            document.querySelector("form").addEventListener("submit", submitForm);
        </script>
    </div>



            """;
        // Get the ArrayList of Strings of all LGAs
        ArrayList<String> lgaNames = getLGAs2016();

        

        // Finally we can print out all of the LGAs
        for (String name : lgaNames) {
            html = html + "<li>" + name + "</li>";
        }

        // Finish the List HTML
       // html = html + "</ul>";

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


    /**
     * Get the names of the LGAs in the database.
     */
    public ArrayList<String> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<String> lgas = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE year='2016'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String name16  = results.getString("name");

                // Add the lga object to the array
                lgas.add(name16);
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
        return lgas;
    }
}
