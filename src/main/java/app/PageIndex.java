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
            <p>Homepage content</p>
            """;
        
        // Add HTML for the LGA list
        html = html + "<h1>All Climate Change Data From Previous Years</h1>" + "<ul>";
        html = html + """
                <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Climate Change Homepage</title>
  <link rel="stylesheet" href="styles.css">
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
</head>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Climate Change Website</title>
    <link rel="stylesheet" href="styles.css">
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to our Climate Change Website!</h1>
        <p>
            Explore the data and insights on global population and temperature changes over the years.
        </p>
<body>
  <div class="container">
    <header>
      
    </header>
    
    <section class="hero">
      <img src="climate-change-hero.jpg" alt="Climate Change Hero Image">
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

    <footer>
      <p>&copy; 2023 Our Climate Change Website. All rights reserved.</p>
    </footer>
  </div>
</body>
</html>

  </style>
</head>

<body>
  <div class="hero" style ="color: orange;">
    <h2><b>“Look deep into nature, and then you will understand everything better.”</b>
    — <u> Albert Einstein</u></h2>
    <p>Take action today for a sustainable future</p>
    <a href="about.html" class="cta-button">Learn More</a>
  </div>

  <div class="content" style="color: black;">
    <h2>About Climate Change</h2>
    <p>Climate change refers to long-term shifts in temperature and weather patterns on Earth. It is primarily caused by human activities, such as the burning of fossil fuels, deforestation, and industrial processes. These activities release greenhouse gases into the atmosphere, trapping heat and leading to global warming.</p>
    <p>The consequences of climate change are far-reaching and include rising sea levels, extreme weather events, loss of biodiversity, and disruptions to ecosystems. It poses significant threats to human health, food security, and the overall stability of our planet.</p>
  
  </div>

  <footer>
    <p> </p>
  </footer>
</body>

</html>


                """;

        html = html + """
            <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Climate Change Website</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            color: #333;
            margin: 0;
            padding: 0;
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
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to our Climate Change Website!</h1>
        <p>
            Explore the data and insights on global population and temperature changes over the years.
        </p>

        <?php
        // Example data for demonstration purposes
        $populationData = [
            'firstYear' => 1900,
            'lastYear' => 2022,
            'totalYears' => 123
        ];

        $temperatureData = [
            'firstYear' => 1880,
            'lastYear' => 2022,
            'totalYears' => 143
        ];
        ?>

        <h2>Data Summary</h2>
        <p>
            Population Data: <?php echo $populationData['firstYear']; ?> - <?php echo $populationData['lastYear']; ?> |
            World Population: X billion - Y billion |
            Total Years: <?php echo $populationData['totalYears']; ?>
        </p>
        <p>
            Temperature Data: <?php echo $temperatureData['firstYear']; ?> - <?php echo $temperatureData['lastYear']; ?> |
            Global Temperature: A°C - B°C |
            Total Years: <?php echo $temperatureData['totalYears']; ?>
        </p>
    </div>
</body>
</html>


            """;
        // Get the ArrayList of Strings of all LGAs
        ArrayList<String> lgaNames = getLGAs2016();

        

        // Finally we can print out all of the LGAs
        for (String name : lgaNames) {
            html = html + "<li>" + name + "</li>";
        }

        // Finish the List HTML
        html = html + "</ul>";

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
