package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;


public class PageST3A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3A.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
            String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               " <title>Temperature Analysis</title>";

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
				<h2 class="f2 c3">All About....</h2>
				<h1 class="f1 fw-7 cw">Temperature Analysis</h1>
			</div>
		</div>""";
        
    
        html = html + "<div class='page-content'>";
 
        // Add HTML for the page content
        html = html + """
           

    <style>
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
    margin-top:20px;
}

button:hover {
    background-color: #45a049;
}

#result {
    margin-top: 30px;
   
    margin-bottom: 30px;
}

.sub-task {
    margin-bottom: 20px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
    margin-top: 30px;
}
.sub-task-item{margin-top: 25px}
.sub-task h3 {
    margin-top: 20px;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    padding: 8px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    font-weight: bold;
}

    </style>

    <div class="container">
        
        <div class="sub-task">
            <h2>Sub-Task A: Identify changes in temperature over extended periods</h2>

            <div class="sub-task-item">
                <h3>Calculate Average Temperature</h3>
                <form>
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
                </form>
            </div>

            <div class="sub-task-item">
                <h3>Compare Averages</h3>
                <!-- Input fields for multiple starting years and regions go here -->
                <button onclick="compareAverages()">Compare</button>
            </div>

            <div class="sub-task-item">
                <h3>Compare Regions</h3>
                <!-- Input fields for multiple regions go here -->
                <button onclick="compareRegions()">Compare</button>
            </div>

            <div class="sub-task-item">
    <h3>Sort Regions</h3>
    <label for="sortOption">Sort By:</label>
    <select id="sortOption">
        <option value="az">A-Z</option>
        <option value="highest">Highest - Lowest Temperature</option>
        <option value="lowest">Lowest - Highest Temperature</option>
    </select>
    <button onclick="sortRegions()">Sort</button>
</div>

            <div class="sub-task-item">
                <h3>Filter Results</h3>
                <form>
                    <label for="populationRange">Population Range:</label>
                    <input type="text" id="populationRange">

                    <label for="temperatureChange">Average Temperature Change:</label>
                    <input type="text" id="temperatureChange">

                    <button onclick="filterResults()">Filter</button>
                </form>
            </div>
        </div>
        
        <div id="result">
            <h3>Search Result</h3>
            <table class='reporttable'>
                <thead>
                    <tr>
                        <th>Starting Year</th>
                        <th>Time Period</th>
                        <th>Region</th>
                        <th>Average Temperature</th>
                        <!-- Add more table headers as needed -->
                    </tr>
                </thead>
                <tbody>
                    <!-- Table rows with data go here -->
                    <tr>
                        <td>2000</td>
                        <td>10</td>
                        <td>Global</td>
                        <td>25.6</td>
                    </tr>
                    <tr>
                        <td>2010</td>
                        <td>5</td>
                        <td>Country</td>
                        <td>21.2</td>
                    </tr>
                    <!-- Add more table rows as needed -->
                </tbody>
            </table>
        </div>
       
    </div>



                """;

            

        // Close Content div
        html = html + "</div>";

        // Footer
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

}
