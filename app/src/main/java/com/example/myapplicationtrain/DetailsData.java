package com.example.myapplicationtrain;


public class DetailsData {
    String date;

    String maxtemp_c;
    String mintemp_c;

    String maxwind_mph;
    String maxwind_kph;

    String totalprecip_mm;
    String totalprecip_in;

    String avgvis_km;
    String avgvis_miles;

    String avghumidity;

    String sunrise;
    String sunset;

    public DetailsData (String date, String maxtemp_c, String mintemp_c, String maxwind_mph,
                         String maxwind_kph, String totalprecip_mm, String totalprecip_in,
                         String avgvis_km, String avgvis_miles, String avghumidity,String sunrise,
                         String sunset){

        this.date = date;

        this.maxtemp_c = maxtemp_c;
        this.mintemp_c = mintemp_c;

        this.maxwind_mph = maxwind_mph;
        this.maxwind_kph = maxwind_kph;

        this.totalprecip_mm = totalprecip_mm;
        this.totalprecip_in = totalprecip_in;

        this.avgvis_km = avgvis_km;
        this.avgvis_miles = avgvis_miles;

        this.avghumidity = avghumidity;

        this.sunrise = sunrise;
        this.sunset = sunset;
    }

}
