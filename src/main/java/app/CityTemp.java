package app;


public class CityTemp {

   //City Code
   private String cityCode;

   // Ave Temp
   private double aveTemp;

   // City Name
   private String cityName;

   // LGA Year
   private int year;

   /**
    * Create an LGA and set the fields
    */
   public CityTemp(String cityCode, String name, int year, double aveTemp) {
      this.cityCode = cityCode;
      this.cityName = name;
      this.year = year;
      this.aveTemp = aveTemp;
   }

   public String getCityCode() {
      return cityCode;
   }

   public String getCityName() {
      return cityName;
   }

   public int getYear() {
      return year;
   }

   public double getAveTemp() {
      return aveTemp;
   }
}
