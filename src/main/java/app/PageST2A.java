package app;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    public String outputTemps(String startYear, String endYear, String city) {
        String html = "<div class='container'>";
        html = html + "<div class='report-details row mt-55'><h2>Search results for " + city + " city from " + startYear + " to " + endYear +"</h2>";
		//html = html + "<h2>" + endYear + " End Year</h2>";
		//html = html + "<h2>" + city + " City</h2>";


        // Look up movies from JDBC
        ArrayList<CityTemp> listOfCityTemps= getListOfCityTemp(startYear, endYear, city);
        
        html = html + """
            
					<table class='reporttable'>
						<thead>
							<tr>
								<td>Average Temprature</td>
								<td>City</td>
                                <td>Country</td>
								
							</tr>
						</thead>
						<tr>
        """;
        for (CityTemp cT : listOfCityTemps) {
            html = html + "<tr><td>" + cT.getAveTemp() + "</td>";
			html = html + "<td>" + cT.getCityName() + "</td>";
            html = html + "<td>" + cT.getCityName() + "</td></tr>";
        }
        html = html + "</table></div></div>";        
        
        return html;
    }

	 public ArrayList<CityTemp> getListOfCityTemp(String startYear, String endYear, String city) {

		
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
            String query = "SELECT AverageTemperature, city, country,Year FROM CITY WHERE year between "+startYear +" and "+endYear+" and City=\""+city+"\"";
            System.out.println(query);
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                double aveTemp = results.getDouble("AverageTemperature");
                String cityName  = results.getString("city");
                String countryName  = results.getString("country");
                int yearOfTemp  = results.getInt("year");

                System.out.println(aveTemp);
                System.out.println("City:"+cityName);
                System.out.println(yearOfTemp);

                String cityCode = cityName;
                

                // Create a CityTemp Object
                CityTemp cityData = new CityTemp(countryName, cityName, yearOfTemp, aveTemp);

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

    @Override
    public void handle(Context context) throws Exception {

        //List<String> params = context.pathParam("name");
        //System.out.println("Getting startYear and val: " + context.req.getParameter("startYear"));
        //System.out.println("Getting endYear and val: " + context.req.getParameter("endYear"));
        //System.out.println("Getting cityInputName and val: " + context.req.getParameter("city"));

        //For GET methog
        //String startYear = context.queryParam("startYear");

        //For POST methog
        //String startYear = context.req.getParameter("startYear");
        //String endYear = context.req.getParameter("endYear");
        //String cityInputName = context.req.getParameter("cityInputName");

        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.1</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
     html = html + "<link rel='stylesheet' type='text/css' href='style.css'>";
    html = html + "<link rel='stylesheet' type='text/css' href='effect.css'>";
    html = html + "<link rel='stylesheet' type='text/css' href='responsive.css'>";
    
       
        html = html + "</head>";

        // Add the body     
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            
           <div class='page-wrapper'>
        <!-- header start -->
        <header class='header1'>
            <nav class='navbar nav-solid mb-0'>
                <div class='container-fluid'>
                    <div class='navbar-header'>
                        <button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#main-menu'> <span></span>  <span></span>  <span></span> 
                        </button>
                        <a class='navbar-brand' href='/'>
                            <img src='logo.png' alt='logo' width="200px">
                        </a>
                    </div>
                    <div class='collapse navbar-collapse navbar-right' id='main-menu'>
                        <ul class='nav navbar-nav f1 mainMenu text-capitalize'>
                            <li class='active'> <a href='/'>Home</a>
                            </li>
                            <li><a href='mission.html'>Mission</a>
                            </li>
                            <li> <a href='page2A.html'>Sub Task 2A</a>
                            </li>
                            <li><a href='page2B.html'>Sub Task 2B</a>
                            </li>
                            <li> <a href='page3A.html'>Sub Task 3A</a>
                            </li>
                            <li><a href='page3B.html'>Sub Task 3B</a>
                            </li>

                        </ul>
                        
                    </div>
                </div>
            </nav>
        </header>
        """;
       html= html+"""
               
                <div class="op-header">
			<div class="section-header text-center">
				<h2 class="f2 c3">Lets Explore....</h2>
				<h1 class="f1 fw-7 cw">Global Temperature Averages & Population</h1>
			</div>
		</div>""";
        // Add header content block
        html= html+"""
            <div class='page-content'>
            <div class='reportform-main'>
       <div class='report-form container '>
            <div class='row'>
            <form action='' method='post' target='_blank'>
                    <div class='section-header text-center'>
								<h1 class='f1 fw-7 c4'>Search Average Temprature Data</h1>
							</div>
                <div class='col-md-3 col-sm-5>
                    <label for='startYear'>Start Year:</label>
                    <input type='number' id='startYear' name='startYear' required>
                </div>
                <div class='col-md-3 col-sm-5'>
                    <label for='endYear'>End Year:</label>
                    <input type='number' id='endYear' name='endYear' required>
                </div>
                <div class='col-md-3 col-sm-5'>
                    <label for='city'>City:</label>
                    <input type='text' id='city' name='city' required>
                </div>
                <div class='col-md-3 col-sm-5'>
                    <input type='submit' value='Submit'>
                </div>
            </form>
            </div>
            </div>
            </div>
            </div>
        """;
         html = html + 
         
         """
            <h1>Population Changes</h1>

  <form id="dataForm">
    <label for="view">View by:</label>
    <select id="view">
      <option value="countries">Countries</option>
      <option value="world">World</option>
    </select>

    <label for="startYear">Start Year:</label>
    <input type="number" id="startYear">

    <label for="endYear">End Year:</label>
    <input type="number" id="endYear">

    <button type="submit">Submit</button>
  </form>

  <table id="dataTable">
    <thead>
      <tr>
        <th>Country/Region</th>
        <th>Population Change</th>
        <th>Temperature Change</th>
      </tr>
    </thead>     
                 """; 
        String startYear = context.req.getParameter("startYear");
		String endYear = context.req.getParameter("endYear");
		String city = context.req.getParameter("city");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (startYear == null || endYear == null || city == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            //html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputTemps(startYear, endYear, city);
        }
                
                

        html= html + """
                <div class='about-page'>
				<div class='intro1 pb-100'>
					<div class='container'>
						<div class='row'>
							<div class='col-md-4 col-sm-6 mb-sm-50'>
								<div class='item'>
									<div class='item-thumb'>
										<a href='#'>
											<img src='climate1.jpg' alt='intro thumb'>
										</a>
									</div>
									<div class='item-txt text-center f1'>
										<h2 class='fw-6'>Support Us....</h2>
										<p class='fw-4'>ClimateActionHub serves as a catalyst for climate action by offering a comprehensive platform that equips individuals with the knowledge, resources, and community support necessary to make a tangible impact on addressing climate change.</p>
										<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
										</a>
									</div>
								</div>
							</div>
							<div class='col-md-4 col-sm-6 mb-sm-50'>
								<div class='item'>
									<div class='item-thumb'>
										<a href='#'>
											<img src='climate5.png' alt='intro thumb'>
										</a>
									</div>
									<div class='item-txt text-center f1'>
										<h2 class='fw-6'>Safe Enviorement</h2>
										<p class='fw-4'>Through its wide range of resources, ClimateActionHub inspires and empowers individuals to adopt sustainable practices, make informed choices, and actively contribute to a more sustainable and resilient world.</p>
										<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
										</a>
									</div>
								</div>
							</div>
							<div class='col-md-4 col-sm-6 col-sm-offset-3 col-md-offset-0 col-xs-offset-0'>
								<div class='item'>
									<div class='item-thumb'>
										<a href='#'>
											<img src='climate4.png' alt='intro thumb'>
										</a>
									</div>
									<div class='item-txt text-center f1'>
										<h2 class='fw-6'>Become a Volunteer</h2>
										<p class='fw-4'>ClimateActionHub fosters a vibrant and engaged community of climate enthusiasts, researchers, and activists, creating a collaborative environment where ideas are shared, initiatives are amplified, and collective action is taken to tackle the urgent challenges of climate change.</p>
										<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='process'>
					<div class='container'>
						<div class='row pos-r'>
							<div class='col-lg-7 pr-0'>
								<div class='process-txt'>
									<div class='section-header'>
										<h2 class='f2 c1'>way of working</h2>
										<h1 class='f1 fw-7 c4'>Learn about how it works...</h1>
									</div>
									<p class='txt-normal'>ClimateActionHub operates as a centralized platform, providing climate change data, actionable resources, and community engagement to inspire and empower individuals and organizations to take meaningful climate action. By fostering knowledge exchange, mobilizing collective action, and advocating for policy changes, ClimateActionHub works towards creating a more sustainable and resilient future.</p>
									<ul class='c4 f1 mt-55'>
										<li class='fw-6 mb-35 clearfix'>	<i class='nivio-checked'></i>  <span>Protect and enhance environment for future generations.</span>
										</li>
										<li class='fw-6 clearfix'>	<i class='nivio-checked'></i>  <span>Healthy tropical ecosystems are imperative to maintaining a healthy planet</span>
										</li>
									</ul>
									<div class='process-img'>
										<img src='climate7.jpg' alt=''>
										<div class='customers bg3 c4 f1'>
											<h2 class='fw-4'>8,000</h2>
											<span class='fw-6'>Satisfied Customers</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				

				
			</div>
                """;
        
        
        // Add Div for script
        html = html + """
            <script>
            // Function to handle form submission
            function myFunction() {
                var x = document.getElementById("myForm").elements[0].value;
                document.getElementById("demo").innerHTML = x;
            }
        </script>
        """;

     
       
        html = html + """
             <!-- footer start -->
        <footer>
            <div class='footer-top'>
                <div class='container'>
                    <div class='row'>
                        <div class='col-sm-6 mb-xs-30'>
                            <a href='#'>
                                <img src='logo.png' alt=''>
                            </a>
                        </div>
                        <div class='col-sm-6'>
                           
                        </div>
                    </div>
                </div>
            </div>
            
            <div class='footer-bottom f1'>
                <div class='container'>
                    <p>Made By: Swayam Mayankkumar Patel(s3994439)</p>
                </div>
            </div>
        </footer>
             </div>
    <!-- page-wrapper end -->
    <div class='back2Top bg1'> <i class='fa fa-angle-up fa-2x cw'></i>
    </div>
        """;

        html = html + """
        <script src='jquery-1.12.4.min.js'></script>
        <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyDTPlX-43R1TpcQUyWjFgiSfL_BiGxslZU'></script>
        <script src='html5lightbox/html5lightbox.js'></script>
        <script src='bootstrap.min.js'></script>
        <script src='owl.carousel.js'></script>
        <script src='numscroller-1.0.js'></script>
        <script src='jquery.countdown.min.js'></script>
        <script src='jquery.enllax.min.js'></script>
        <script src='isotope.js'></script>
        <script src='magnific-popup.js'></script>
        <script src='main.js'></script>
         """;

        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
    
public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * Get the names of the LGAs in the database.
     */

     /*
    public ArrayList<CityTemp> getListOfCityTemp(int yearToSelect, String cityNameToSelectFor) {
        // Create the ArrayList of LGA objects to return
        ArrayList<CityTemp> listOfCityTemp = new ArrayList<CityTemp>();

        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            System.out.println("getListOfCityTemp() - connection status, closed?:"+ connection.isClosed());

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

                String s = "\"Test\"" + cityCode;
                System.out.println("s:"+s);

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


