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

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    public String outputTemps(String startYear, String endYear, String city) {
        String html = "";
        html = html + "<h2>" + startYear + " Start Year</h2>";
		html = html + "<h2>" + endYear + " End Year</h2>";
		html = html + "<h2>" + city + " City</h2>";


        // Look up movies from JDBC
        ArrayList<CityTemp> listOfCityTemps= getListOfCityTemp(startYear, endYear, city);
        
        // Add HTML for the movies list
        html = html + "<ul>";
        for (CityTemp cT : listOfCityTemps) {
            html = html + "<li>" + cT.getAveTemp() + "</li>";
			html = html + "<li>" + cT.getCityCode() + "</li>";
			html = html + "<li>" + cT.getCityName() + "</li>";
        }
        html = html + "</ul>";        
        
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
            String query = "SELECT AverageTemperature, city,Year FROM CITY WHERE year between "+startYear +" and "+endYear+" and City=\""+city+"\"";
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

    @Override
    public void handle(Context context) throws Exception {

        //List<String> params = context.pathParam("name");
        System.out.println("Getting startYear and val: " + context.req.getParameter("startYear"));
        System.out.println("Getting endYear and val: " + context.req.getParameter("endYear"));
        System.out.println("Getting cityInputName and val: " + context.req.getParameter("city"));

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
        html = html + "<link rel='stylesheet' type='text/css' href='style.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='effect.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='responsive.css'/>";
        html = html + "<link rel='stylesheet' type='text/css' href='responsive.css'/>";
    
        html = html + "<link href='bootstrap-datepicker.css' rel='stylesheet' type='text/css' />";    
        html = html + " <link href='bootstrap-datepicker3.css' rel='stylesheet' type='text/css'>";
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
                <h1>Subtask 2.A</h1>
            </div>
        """;

        String startYear = context.req.getParameter("startYear");
		String endYear = context.req.getParameter("endYear");
		String city = context.req.getParameter("city");
        // String movietype_drop = context.queryParam("movietype_drop");
        if (startYear == null || endYear == null || city == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputTemps(startYear, endYear, city);
        }
        
        /*
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            System.out.println("connection status, closed?:"+ connection.isClosed());

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT AverageTemperature, city,Year FROM CITY WHERE year="+startYear+" and City="+ cityInputName +" ";
            System.out.println("sql:"+query);
            // Get Result
            ResultSet results = statement.executeQuery(query);
				
            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                double aveTemp = results.getDouble("AverageTemperature");
                String cityName  = results.getString("city");
                int yearOfTemp  = results.getInt("year");

                System.out.println("aveTemp:"+aveTemp);
                System.out.println("cityName:"+cityName);
                System.out.println("yearOfTemp:"+yearOfTemp);

                String cityCode = cityName;


                // Create a CityTemp Object
                CityTemp cityData = new CityTemp(cityCode, cityName, yearOfTemp, aveTemp);

                String s = "\"+Test:\""+cityName;
                System.out.println("Gaurang:"+s);
                html = html + "<div class='header'><h1>"+cityName+
                            "</h1><h1>"+yearOfTemp+"</h1><h1>"+aveTemp+
                            "Gaurang"+
                            "</h1><p id=\"demo\"></p></div>";
                
                // Add the lga object to the array
                //listOfCityTemp.add(cityData);
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

         */

        // Add Div for script
        html = html + """
            <div><script>
            // Function to handle form submission
            function myFunction() {
                var x = document.getElementById("myForm").elements[0].value;
                document.getElementById("demo").innerHTML = x;
            }
        </script></div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        html = html + """
				<form action="" method="post" target="_blank">
  				<label for="startYear">Start Year:</label>
  				<input type="number" id="startYear" name="startYear" required><br><br>
  				<label for="endYear">End Year:</label>
  				<input type="number" id="endYear" name="endYear" required><br><br>
                <label for="city">City:</label>
  				<input type="text" id="city" name="city" required><br><br>
  				<input type="submit" value="Submit">
				</form>

				""";
        
            /* 
        html = html + "<form action ='/page2A.html' method='post'>";
        html = html + " <div class='form-group'>";
        html = html + "     <label for='cityTemp > Select the City :</label>";
        html = html + "input class='date' id='cityTemp' name='cityTemp'>";                
               
        html = html + "<select id ='cityTemp' name ='cityTemp'>";
        html = html + "         <option> City</option>";
        html = html + "         <option> World</option>";
        html = html + "      </select>";
        html = html + "   </div>";
        
*/
        html = html + "   <div class='form-group'>";
       // html = html + "      <label for='movietype_textbox'>Select the type Movie Type (Textbox)</label>";
      //  html = html + "      <input class='form-control' id='movietype_textbox' name='movietype_textbox'>";
        html = html + "   </div>";
        html = html + "<br>";
          html = html + "<br>";
        html = html + "   <button type='submit' class='btn btn-primary'>Get climate change data!</button>";

        html = html + "</form>";
        html = html + "</form>";

        
        
        html = html + """                 
    
    <!-- page-wrapper start -->
    <div class='page-wrapper'>
        <!-- header start -->
        
        <div class='op-header'>
            <div class='section-header text-center'>
                <h1 class='f1 fw-7 cw'>Reports</h1>
            </div>
        </div>
        <div class='page-content mb-55'>
        
            <style>
             body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f7f7f7;
        margin: 0;
        padding: 20px;
    }
                table.reporttable  {
                  border-collapse: collapse;
                  width: 100%;
                }
                
                .reporttable th, .reporttable td {
                  text-align: left;
                  padding: 8px;
                }
                
                .reporttable tr:nth-child(even){background-color: #f2f2f2}
                .reporttable thead tr {
                    background-color: #6abe52;
                    color: #ffffff;
                    text-align: left;
                }
                .reporttable th {
                  background-color: #6abe52;
                  color: white;
                }

                .reporttable tr {
                    border-bottom: 1px solid #dddddd;
                }

                .reporttable tr:nth-of-type(even) {
                    background-color: #f3f3f3;
                }

                .reporttable tr:last-of-type {
                    border-bottom: 2px solid #6abe52;
                }
                </style>
            <div class='report-content container mt-55 mb-55'>
                <div class='row'>
                    <div class='col-lg-3 col-md-3 col-sm-12 form-group' style='display: inline-block;'>
                        <label for=''>Start Date<span>*</span></label>
                      <input type='text' class='form-control datepicker' name='start_date' id='start_date' placeholder='Start date (DD/MM/YEAR)'>
                    </div>
                    <div class='col-lg-3 col-md-3 col-sm-12 form-group' style='display: inline-block;'>
                        <label for=''>End Date<span>*</span></label>
                      <input type='text' class='form-control datepicker' name='end_date' id='end_date' placeholder='Start date (DD/MM/YEAR)'>
                    </div>
                    <div class='col-lg-3 col-md-3 col-sm-12' style='display: inline-block;'>
                        <label for=''>Report By</label>
                        <select class='form-control'>
                            <option value=''>Select Report By</option>
                            <option value='Country'>Country</option>
                            <option value='World'>World</option>
                        </select>
                    </div>
                    <div class='col-lg-3 col-md-3 col-sm-12' style='display: inline-block;'>
                        <label for=''>Select Country</label>
                        <select class='form-control'>
                            <option value=''>country</option>
                            <option value='Afghanistan'>Afghanistan</option>
                            <option value='Aland Islands'>Aland Islands</option>
                            <option value='Albania'>Albania</option>
                            <option value='Algeria'>Algeria</option>
                            <option value='American Samoa'>American Samoa</option>
                            <option value='Andorra'>Andorra</option>
                            <option value='Angola'>Angola</option>
                            <option value='Anguilla'>Anguilla</option>
                            <option value='Antarctica'>Antarctica</option>
                            <option value='Antigua and Barbuda'>Antigua and Barbuda</option>
                            <option value='Argentina'>Argentina</option>
                            <option value='Armenia'>Armenia</option>
                            <option value='Aruba'>Aruba</option>
                            <option value='Australia'>Australia</option>
                            <option value='Austria'>Austria</option>
                            <option value='Azerbaijan'>Azerbaijan</option>
                            <option value='Bahamas'>Bahamas</option>
                            <option value='Bahrain'>Bahrain</option>
                            <option value='Bangladesh'>Bangladesh</option>
                            <option value='Barbados'>Barbados</option>
                            <option value='Belarus'>Belarus</option>
                            <option value='Belgium'>Belgium</option>
                            <option value='Belize'>Belize</option>
                            <option value='Benin'>Benin</option>
                            <option value='Bermuda'>Bermuda</option>
                            <option value='Bhutan'>Bhutan</option>
                            <option value='Bolivia'>Bolivia</option>
                            <option value='Bonaire, Sint Eustatius and Saba'>Bonaire, Sint Eustatius and Saba</option>
                            <option value='Bosnia and Herzegovina'>Bosnia and Herzegovina</option>
                            <option value='Botswana'>Botswana</option>
                            <option value='Bouvet Island'>Bouvet Island</option>
                            <option value='Brazil'>Brazil</option>
                            <option value='British Indian Ocean Territory'>British Indian Ocean Territory</option>
                            <option value='Brunei Darussalam'>Brunei Darussalam</option>
                            <option value='Bulgaria'>Bulgaria</option>
                            <option value='Burkina Faso'>Burkina Faso</option>
                            <option value='Burundi'>Burundi</option>
                            <option value='Cambodia'>Cambodia</option>
                            <option value='Cameroon'>Cameroon</option>
                            <option value='Canada'>Canada</option>
                            <option value='Cape Verde'>Cape Verde</option>
                            <option value='Cayman Islands'>Cayman Islands</option>
                            <option value='Central African Republic'>Central African Republic</option>
                            <option value='Chad'>Chad</option>
                            <option value='Chile'>Chile</option>
                            <option value='China'>China</option>
                            <option value='Christmas Island'>Christmas Island</option>
                            <option value='Cocos (Keeling) Islands'>Cocos (Keeling) Islands</option>
                            <option value='Colombia'>Colombia</option>
                            <option value='Comoros'>Comoros</option>
                            <option value='Congo'>Congo</option>
                            <option value='Congo, Democratic Republic of the Congo'>Congo, Democratic Republic of the Congo</option>
                            <option value='Cook Islands'>Cook Islands</option>
                            <option value='Costa Rica'>Costa Rica</option>
                            <option value='Cote DIvoire'>Cote DIvoire</option>
                            <option value='Croatia'>Croatia</option>
                            <option value='Cuba'>Cuba</option>
                            <option value='Curacao'>Curacao</option>
                            <option value='Cyprus'>Cyprus</option>
                            <option value='Czech Republic'>Czech Republic</option>
                            <option value='Denmark'>Denmark</option>
                            <option value='Djibouti'>Djibouti</option>
                            <option value='Dominica'>Dominica</option>
                            <option value='Dominican Republic'>Dominican Republic</option>
                            <option value='Ecuador'>Ecuador</option>
                            <option value='Egypt'>Egypt</option>
                            <option value='El Salvador'>El Salvador</option>
                            <option value='Equatorial Guinea'>Equatorial Guinea</option>
                            <option value='Eritrea'>Eritrea</option>
                            <option value='Estonia'>Estonia</option>
                            <option value='Ethiopia'>Ethiopia</option>
                            <option value='Falkland Islands (Malvinas)'>Falkland Islands (Malvinas)</option>
                            <option value='Faroe Islands'>Faroe Islands</option>
                            <option value='Fiji'>Fiji</option>
                            <option value='Finland'>Finland</option>
                            <option value='France'>France</option>
                            <option value='French Guiana'>French Guiana</option>
                            <option value='French Polynesia'>French Polynesia</option>
                            <option value='French Southern Territories'>French Southern Territories</option>
                            <option value='Gabon'>Gabon</option>
                            <option value='Gambia'>Gambia</option>
                            <option value='Georgia'>Georgia</option>
                            <option value='Germany'>Germany</option>
                            <option value='Ghana'>Ghana</option>
                            <option value='Gibraltar'>Gibraltar</option>
                            <option value='Greece'>Greece</option>
                            <option value='Greenland'>Greenland</option>
                            <option value='Grenada'>Grenada</option>
                            <option value='Guadeloupe'>Guadeloupe</option>
                            <option value='Guam'>Guam</option>
                            <option value='Guatemala'>Guatemala</option>
                            <option value='Guernsey'>Guernsey</option>
                            <option value='Guinea'>Guinea</option>
                            <option value='Guinea-Bissau'>Guinea-Bissau</option>
                            <option value='Guyana'>Guyana</option>
                            <option value='Haiti'>Haiti</option>
                            <option value='Heard Island and Mcdonald Islands'>Heard Island and Mcdonald Islands</option>
                            <option value='Holy See (Vatican City State)'>Holy See (Vatican City State)</option>
                            <option value='Honduras'>Honduras</option>
                            <option value='Hong Kong'>Hong Kong</option>
                            <option value='Hungary'>Hungary</option>
                            <option value='Iceland'>Iceland</option>
                            <option value='India'>India</option>
                            <option value='Indonesia'>Indonesia</option>
                            <option value='Iran, Islamic Republic of'>Iran, Islamic Republic of</option>
                            <option value='Iraq'>Iraq</option>
                            <option value='Ireland'>Ireland</option>
                            <option value='Isle of Man'>Isle of Man</option>
                            <option value='Israel'>Israel</option>
                            <option value='Italy'>Italy</option>
                            <option value='Jamaica'>Jamaica</option>
                            <option value='Japan'>Japan</option>
                            <option value='Jersey'>Jersey</option>
                            <option value='Jordan'>Jordan</option>
                            <option value='Kazakhstan'>Kazakhstan</option>
                            <option value='Kenya'>Kenya</option>
                            <option value='Kiribati'>Kiribati</option>
                            <option value='Korea, Democratic Peoples Republic of'>Korea, Democratic Peoples Republic of</option>
                            <option value='Korea, Republic of'>Korea, Republic of</option>
                            <option value='Kosovo'>Kosovo</option>
                            <option value='Kuwait'>Kuwait</option>
                            <option value='Kyrgyzstan'>Kyrgyzstan</option>
                            <option value='Lao Peoples Democratic Republic'>Lao Peoples Democratic Republic</option>
                            <option value='Latvia'>Latvia</option>
                            <option value='Lebanon'>Lebanon</option>
                            <option value='Lesotho'>Lesotho</option>
                            <option value='Liberia'>Liberia</option>
                            <option value='Libyan Arab Jamahiriya'>Libyan Arab Jamahiriya</option>
                            <option value='Liechtenstein'>Liechtenstein</option>
                            <option value='Lithuania'>Lithuania</option>
                            <option value='Luxembourg'>Luxembourg</option>
                            <option value='Macao'>Macao</option>
                            <option value='Macedonia, the Former Yugoslav Republic of'>Macedonia, the Former Yugoslav Republic of</option>
                            <option value='Madagascar'>Madagascar</option>
                            <option value='Malawi'>Malawi</option>
                            <option value='Malaysia'>Malaysia</option>
                            <option value='Maldives'>Maldives</option>
                            <option value='Mali'>Mali</option>
                            <option value='Malta'>Malta</option>
                            <option value='Marshall Islands'>Marshall Islands</option>
                            <option value='Martinique'>Martinique</option>
                            <option value='Mauritania'>Mauritania</option>
                            <option value='Mauritius'>Mauritius</option>
                            <option value='Mayotte'>Mayotte</option>
                            <option value='Mexico'>Mexico</option>
                            <option value='Micronesia, Federated States of'>Micronesia, Federated States of</option>
                            <option value='Moldova, Republic of'>Moldova, Republic of</option>
                            <option value='Monaco'>Monaco</option>
                            <option value='Mongolia'>Mongolia</option>
                            <option value='Montenegro'>Montenegro</option>
                            <option value='Montserrat'>Montserrat</option>
                            <option value='Morocco'>Morocco</option>
                            <option value='Mozambique'>Mozambique</option>
                            <option value='Myanmar'>Myanmar</option>
                            <option value='Namibia'>Namibia</option>
                            <option value='Nauru'>Nauru</option>
                            <option value='Nepal'>Nepal</option>
                            <option value='Netherlands'>Netherlands</option>
                            <option value='Netherlands Antilles'>Netherlands Antilles</option>
                            <option value='New Caledonia'>New Caledonia</option>
                            <option value='New Zealand'>New Zealand</option>
                            <option value='Nicaragua'>Nicaragua</option>
                            <option value='Niger'>Niger</option>
                            <option value='Nigeria'>Nigeria</option>
                            <option value='Niue'>Niue</option>
                            <option value='Norfolk Island'>Norfolk Island</option>
                            <option value='Northern Mariana Islands'>Northern Mariana Islands</option>
                            <option value='Norway'>Norway</option>
                            <option value='Oman'>Oman</option>
                            <option value='Pakistan'>Pakistan</option>
                            <option value='Palau'>Palau</option>
                            <option value='Palestinian Territory, Occupied'>Palestinian Territory, Occupied</option>
                            <option value='Panama'>Panama</option>
                            <option value='Papua New Guinea'>Papua New Guinea</option>
                            <option value='Paraguay'>Paraguay</option>
                            <option value='Peru'>Peru</option>
                            <option value='Philippines'>Philippines</option>
                            <option value='Pitcairn'>Pitcairn</option>
                            <option value='Poland'>Poland</option>
                            <option value='Portugal'>Portugal</option>
                            <option value='Puerto Rico'>Puerto Rico</option>
                            <option value='Qatar'>Qatar</option>
                            <option value='Reunion'>Reunion</option>
                            <option value='Romania'>Romania</option>
                            <option value='Russian Federation'>Russian Federation</option>
                            <option value='Rwanda'>Rwanda</option>
                            <option value='Saint Barthelemy'>Saint Barthelemy</option>
                            <option value='Saint Helena'>Saint Helena</option>
                            <option value='Saint Kitts and Nevis'>Saint Kitts and Nevis</option>
                            <option value='Saint Lucia'>Saint Lucia</option>
                            <option value='Saint Martin'>Saint Martin</option>
                            <option value='Saint Pierre and Miquelon'>Saint Pierre and Miquelon</option>
                            <option value='Saint Vincent and the Grenadines'>Saint Vincent and the Grenadines</option>
                            <option value='Samoa'>Samoa</option>
                            <option value='San Marino'>San Marino</option>
                            <option value='Sao Tome and Principe'>Sao Tome and Principe</option>
                            <option value='Saudi Arabia'>Saudi Arabia</option>
                            <option value='Senegal'>Senegal</option>
                            <option value='Serbia'>Serbia</option>
                            <option value='Serbia and Montenegro'>Serbia and Montenegro</option>
                            <option value='Seychelles'>Seychelles</option>
                            <option value='Sierra Leone'>Sierra Leone</option>
                            <option value='Singapore'>Singapore</option>
                            <option value='Sint Maarten'>Sint Maarten</option>
                            <option value='Slovakia'>Slovakia</option>
                            <option value='Slovenia'>Slovenia</option>
                            <option value='Solomon Islands'>Solomon Islands</option>
                            <option value='Somalia'>Somalia</option>
                            <option value='South Africa'>South Africa</option>
                            <option value='South Georgia and the South Sandwich Islands'>South Georgia and the South Sandwich Islands</option>
                            <option value='South Sudan'>South Sudan</option>
                            <option value='Spain'>Spain</option>
                            <option value='Sri Lanka'>Sri Lanka</option>
                            <option value='Sudan'>Sudan</option>
                            <option value='Suriname'>Suriname</option>
                            <option value='Svalbard and Jan Mayen'>Svalbard and Jan Mayen</option>
                            <option value='Swaziland'>Swaziland</option>
                            <option value='Sweden'>Sweden</option>
                            <option value='Switzerland'>Switzerland</option>
                            <option value='Syrian Arab Republic'>Syrian Arab Republic</option>
                            <option value='Taiwan, Province of China'>Taiwan, Province of China</option>
                            <option value='Tajikistan'>Tajikistan</option>
                            <option value='Tanzania, United Republic of'>Tanzania, United Republic of</option>
                            <option value='Thailand'>Thailand</option>
                            <option value='Timor-Leste'>Timor-Leste</option>
                            <option value='Togo'>Togo</option>
                            <option value='Tokelau'>Tokelau</option>
                            <option value='Tonga'>Tonga</option>
                            <option value='Trinidad and Tobago'>Trinidad and Tobago</option>
                            <option value='Tunisia'>Tunisia</option>
                            <option value='Turkey'>Turkey</option>
                            <option value='Turkmenistan'>Turkmenistan</option>
                            <option value='Turks and Caicos Islands'>Turks and Caicos Islands</option>
                            <option value='Tuvalu'>Tuvalu</option>
                            <option value='Uganda'>Uganda</option>
                            <option value='Ukraine'>Ukraine</option>
                            <option value='United Arab Emirates'>United Arab Emirates</option>
                            <option value='United Kingdom'>United Kingdom</option>
                            <option value='United States'>United States</option>
                            <option value='United States Minor Outlying Islands'>United States Minor Outlying Islands</option>
                            <option value='Uruguay'>Uruguay</option>
                            <option value='Uzbekistan'>Uzbekistan</option>
                            <option value='Vanuatu'>Vanuatu</option>
                            <option value='Venezuela'>Venezuela</option>
                            <option value='Viet Nam'>Viet Nam</option>
                            <option value='Virgin Islands, British'>Virgin Islands, British</option>
                            <option value='Virgin Islands, U.s.'>Virgin Islands, U.s.</option>
                            <option value='Wallis and Futuna'>Wallis and Futuna</option>
                            <option value='Western Sahara'>Western Sahara</option>
                            <option value='Yemen'>Yemen</option>
                            <option value='Zambia'>Zambia</option>
                            <option value='Zimbabwe'>Zimbabwe</option>
                        </select>
                    </div>
                  </div>

                  <div class='report-details row mt-55'> <br>
                  <br>
                    <table class='reporttable'>
                        <thead>
                            <tr>
                                <td>Year</td>
                                <td>Population</td>
                                <td>Average Temprature</td>
                            </tr>
                        </thead>
                        <tr>
                            <td>2013</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2014</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2015</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2016</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2017</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2018</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2019</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2020</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr><td>2021</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr>
                            <td>2022</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                        <tr>
                            <td>2023</td>
                            <td>9157465</td>
                            <td>12.013</td>
                        </tr>
                    </table> 
                    
                    <div class='report-details row mt-55'> <br>
                    <br>
                    <br>
                        <table class='reporttable'>
                            <thead><tr><td>Year</td><td>Population</td><td>Average Temprature</td><td>Country/Region</td></tr></thead>
                            <tr>
                                <td>2013</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2014</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2015</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2016</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2017</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2018</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2019</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2020</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr><td>2021</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr>
                                <td>2022</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                            <tr>
                                <td>2023</td>
                                <td>9157465</td>
                                <td>12.013</td>
                                <td>Country/Region</td>
                            </tr>
                        </table>

                  </div>
            </div>
        </div>
        <!-- page-content end -->
        
    </div>
    <!-- page-wrapper end -->
    <div class='back2Top bg1'> <i class='fa fa-angle-up fa-2x cw'></i>
    </div>
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
    <script src='bootstrap-datepicker.min.js'></script>
    <script>
        $(document).ready(function(){

        $('.datepicker').datepicker({
        format: 'dd/mm/yyyy',
        });
    });
        
    </script>
                """;;

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


