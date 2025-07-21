package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
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
        // Add Div for page Content
        html = html + """
        <div class='main-slider ms2 pos-r'>
			<div class='banner-carousel owl-carousel owl-loaded'>
				
			<div class='owl-stage-outer'><div class='owl-stage' style='transform: translate3d(-3806px, 0px, 0px); transition: all 0s ease 0s; width: 9515px;'><div class='owl-item cloned' style='width: 1903px;'><div class='ms2-item' data-item='2'>
					<div class='pos-r g-table'>
						<div class='d-middle'>
							<div class='container'>
								<div class='item-inner text-center'>
									<p class='f2 c3 fw-4'>Welcome to ClimateActionHub</p>
									<div class='head-lines f1 cw mb-50'>
										<h1 class='fw-7'>Help the endangered</h1>
										<h1 class='fw-7'>species today.</h1>
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div></div><div class='owl-item cloned' style='width: 1903px;'><div class='ms2-item' data-item='2'>
					<div class='pos-r g-table'>
						<div class='d-middle'>
							<div class='container'>
								<div class='item-inner text-center'>
									<p class='f2 c3 fw-4'>Welcome to ClimateActionHub</p>
									<div class='head-lines f1 cw mb-50'>
										<h1 class='fw-7'>Unite for a Greener Future </h1>
										<h1 class='fw-7'>Join the ClimateAction Hub!</h1>
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div></div><div class='owl-item active' style='width: 1903px;'><div class='ms2-item' data-item='2'>
					<div class='pos-r g-table'>
						<div class='d-middle'>
							<div class='container'>
								<div class='item-inner text-center'>
									<p class='f2 c3 fw-4'>Welcome to ClimateActionHub</p>
									<div class='head-lines f1 cw mb-50'>
										<h1 class='fw-7'>Unite for Greener Future</h1>
										<h1 class='fw-7'>Join ClimateAction Hub!</h1>
									</div> 
								</div>
							</div>
						</div>
					</div>
				</div></div><div class='owl-item cloned' style='width: 1903px;'><div class='ms2-item' data-item='2'>
					<div class='pos-r g-table'>
						<div class='d-middle'>
							<div class='container'>
								<div class='item-inner text-center'>
									<p class='f2 c3 fw-4'>Welcome to ClimateActionHub</p>
									<div class='head-lines f1 cw mb-50'>
										<h1 class='fw-7'>Unite for a Greener Future</h1>
										<h1 class='fw-7'>species today.</h1>
									</div> <a href='#' class='thm-btn hvr-1 bg1 cw'>Explore</a>
									<a href='#' class='thm-btn hvr-2 bg3 c4'>Start Today!</a>
								</div>
							</div>
						</div>
					</div>
				</div></div><div class='owl-item cloned' style='width: 1903px;'><div class='ms2-item' data-item='2'>
					<div class='pos-r g-table'>
						<div class='d-middle'>
							<div class='container'>
								<div class='item-inner text-center'>
									<p class='f2 c3 fw-4'>Welcome to ClimateActionHub</p>
									<div class='head-lines f1 cw mb-50'>
										<h1 class='fw-7'>Help the endangered</h1>
										<h1 class='fw-7'>species today.</h1>
									</div> <a href='#' class='thm-btn hvr-1 bg1 cw'>Explore</a>
									<a href='#' class='thm-btn hvr-2 bg3 c4'>Start Today!</a>
								</div>
							</div>
						</div>
					</div>
				</div></div></div></div><div class='owl-nav disabled'><div class='owl-prev'>prev</div><div class='owl-next'>next</div></div><div class='owl-dots disabled'><div class='owl-dot active'><span></span></div></div></div>
			<div class='supporter bg2 f1'>
				<p class='fw-7 cw'><span class='c3'>47,850</span> number of users worldwide</p>
			</div>
		</div>
                    
        """;

    
        html = html + """
      <div class='page-content'>
			<div class='intro2'>
				<div class='container'>
					<div class='row'>
						<div class='col-md-6 mb-sm-50'>
							<div class='intro-left'>
							<img src = ''>
								<div class='section-header'>
									<h2 class='f2 c1'>welcome to climateActionHub!</h2>
									<h1 class='f1 fw-7 c4'>Do you care about the earth like we do?</h1>
								</div>
								<h3 class='f1 fw-6'><i class='fa fa-check-circle c1'></i> Green. That is how we would like the world to be</h3>
								<p class='txt-normal'>Discover a world of climate change data and actionable insights at ClimateActionHub. Our platform is dedicated to providing you with comprehensive information on average temperatures, city and country data, and population trends from 1750 to 2013. Join us in our mission to drive meaningful climate action by understanding the past and shaping a sustainable future.</p>	<a href='#' class='thm-btn hvr-1 bg1 cw'>Lets Explore </a>
								
							</div>
						</div>
						<div class='col-md-6'>
							<div class='intro-right pos-r'>
								<img src='climate2.jpg' alt=''>
								<div class='intro-card bg3 c4 f1'>	<i class='nivio-garden'></i>
									<h2>Making Changes Since Years</h2>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class='items-area text-center f1'>
					<div class='container'>
						<div class='row'>
							<div class='col-md-4 col-sm-6 mb-sm-50'>
								<div class='item'>	<span class='nivio-factory c1'></span>
									<p class='fw-4 c4'> Rising temperatures due to increased greenhouse gas emissions from human activities are causing significant climate change, leading to melting glaciers, sea-level rise, heatwaves, and ecological disruptions.</p>
									<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
									</a>
								</div>
							</div>
							<div class='col-md-4 col-sm-6 mb-sm-50'>
								<div class='item'>	<span class='nivio-ecological c1'></span>
									<p class='fw-4 c4'>Climate change is altering precipitation patterns, resulting in more intense rainfall and storms in some areas and droughts in others, affecting agriculture, water resources, and ecosystems.</p>
									<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
									</a>
								</div>
							</div>
							<div class='col-md-4 col-sm-6 col-sm-offset-3 col-xs-offset-0 col-md-offset-0'>
								<div class='item'>	<span class='nivio-green-energy c1'></span>
									<p class='fw-4 c4'>Climate change poses risks to human societies, including food and water shortages, displacement, increased disease prevalence, and impacts on coastal regions, while also causing ecological disruptions and loss of biodiversity. Urgent action is needed to mitigate and adapt to these challenges.</p>
									<a href='#' class='rdm'>	<i class='fa fa-angle-right'></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='event' data-enllax-ratio='2.2'>
				<div class='container'>
					<div class='row'>
						<div class='col-md-6 col-md-push-6'>
							<div class='counter-content'>
								<ul id='timer' class='list-inline text-right'>
									<li>	<span class='f1 cw days'>00</span>
										<p class='days_text f2 bg3 c4'>Days</p>
									</li>
									<li>	<span class='f1 cw hours'>00</span>
										<p class='hours_text f2 bg3 c4'>Hours</p>
									</li>
									<li>	<span class='f1 cw minutes'>00</span>
										<p class='minutes_text f2 bg3 c4'>Minutes</p>
									</li>
									<li>	<span class='f1 cw seconds'>00</span>
										<p class='seconds_text f2 bg3 c4'>Seconds</p>
									</li>
								</ul>
							</div>
						</div>
						<div class='col-md-6 col-md-pull-6'>
							<div class='event-content'>
								<div class='section-header'>
									<h2 class='f2 c3'>upcoming....</h2>
									<h1 class='f1 fw-7 cw'>Annual Environment conference</h1>
								</div>
								<p class='txt-normal cw'>ClimateActionHub is launching a dedicated section to showcase inspiring stories of grassroots initiatives and community-led climate action projects. Be inspired by the incredible work being done by individuals and local communities worldwide.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='team'>
				<div class='container'>
					
						
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
									<h1 class='f1 fw-7 c4'>Learn about how climateActionHub! works</h1>
								</div>
								<p class='txt-normal'>ClimateActionHub is an online platform that provides climate change data, actionable resources, and community engagement to drive climate action. Users can access data on temperature records, precipitation patterns, and population dynamics. The platform offers practical resources, fosters community engagement, and mobilizes individuals to take action through campaigns, events, and connections with organizations. ClimateActionHub aims to empower individuals to make sustainable choices and collectively address climate change for a better future.</p>
								<ul class='c4 f1 mt-55'>
									<li class='fw-6 mb-35 clearfix'>	<i class='nivio-checked'></i>  <span>Protect and enhance environment for future generations.</span>
									</li>
									<li class='fw-6 clearfix'>	<i class='nivio-checked'></i>  <span>Healthy tropical ecosystems are imperative to maintaining a healthy planet</span>
									</li>
								</ul>
								<div class='process-img'>
									<img src='climate3.jpg' alt=''>
									<div class='customers bg3 c4 f1'>
										<h2 class='fw-4'>8,000+</h2>
										<span class='fw-6'>Changes Made</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class='volunteer mt-30'>
				<div class='row'>
					<div class='col-lg-6'>
						<div class='gallery-carousel popupGallery owl-carousel'>
							<div class='gallery-single'>
								<a href='volunteer/01.jpg'>
									<img src='volunteer/01.jpg' alt=''>	<span><i class='nivio-add'></i></span>
								</a>
							</div>
							<div class='gallery-single'>
								<a href='volunteer/02.jpg'>
									<img src='volunteer/02.jpg' alt=''>	<span><i class='nivio-add'></i></span>
								</a>
							</div>
							<div class='gallery-single'>
								<a href='volunteer/03.jpg'>
									<img src='volunteer/03.jpg' alt=''>	<span><i class='nivio-add'></i></span>
								</a>
							</div>
						</div>
					</div>
					
				</div>
				<div class='container'>
					
				</div>
			</div>
		</div>
    
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
