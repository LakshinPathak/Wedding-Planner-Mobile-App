package com.example.mos_innovative.model;
public class ThemeRequest {
    private String colors;
    private String venue;
    private String season;

    public ThemeRequest(String colors, String venue, String season) {
        this.colors = colors;
        this.venue = venue;
        this.season = season;
    }

    // Getters and Setters
    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}


//package com.example.mos_innovative.model;
//
//public class ThemeRequest {
//    private String colorPreferences; // Example field, replace with actual fields
//    private String location; // Example field, replace with actual fields
//    private String themeType; // Example field, replace with actual fields
//
//    // Constructor
//    public ThemeRequest(String colorPreferences, String location, String themeType) {
//        this.colorPreferences = colorPreferences;
//        this.location = location;
//        this.themeType = themeType;
//    }
//
//    // Getters and setters (optional, depending on your use case)
//    public String getColorPreferences() {
//        return colorPreferences;
//    }
//
//    public void setColorPreferences(String colorPreferences) {
//        this.colorPreferences = colorPreferences;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getThemeType() {
//        return themeType;
//    }
//
//    public void setThemeType(String themeType) {
//        this.themeType = themeType;
//    }
//}
