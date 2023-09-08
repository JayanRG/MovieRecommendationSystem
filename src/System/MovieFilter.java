package System;

import java.util.List;


public class MovieFilter {
    
    public boolean filterMavenCinema;
    private boolean filterTopMavenRatings;
    private boolean filterNewReleases;
    private int yearMin;
    private int yearMax;
    private List<String> genres; // List to hold selected genres
    private float mavenRating; // float to hold the selected Maven rating
    private List<String> contentRatings; // List to hold selected content ratings
    private boolean isMavenCinemaActive;
    private int minYear;
    private int maxYear;
    private float minMavenRating;

    public MovieFilter() {
        this.filterMavenCinema = false;
    }

    public void setFilterMavenCinema(boolean value) {
        this.filterMavenCinema = value;
    }

    public boolean getFilterMavenCinema() {
        return this.filterMavenCinema;
    }
    
       public void setFilterTopMavenRatings(boolean filterTopMavenRatings) {
        this.filterTopMavenRatings = filterTopMavenRatings;
    }

    public boolean getFilterTopMavenRatings() {
        return filterTopMavenRatings;
    }
    
    public boolean getFilterNewReleases() {
        return filterNewReleases;
    }

    public void setFilterNewReleases(boolean filterNewReleases) {
        this.filterNewReleases = filterNewReleases;
    }
    // Add new getters and setters for the new properties
    public int getYearMin() {
        return yearMin;
    }

    public void setYearMin(int yearMin) {
        this.yearMin = yearMin;
    }

    public int getYearMax() {
        return yearMax;
    }

    public void setYearMax(int yearMax) {
        this.yearMax = yearMax;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public float getMavenRating() {
        return mavenRating;
    }

    public void setMavenRating(float mavenRating) {
        this.mavenRating = mavenRating;
    }

    public List<String> getContentRatings() {
        return contentRatings;
    }

    public void setContentRatings(List<String> contentRatings) {
        this.contentRatings = contentRatings;
    }

    public boolean getIsMavenCinemaActive() {
        return isMavenCinemaActive;
    }

    public void setIsMavenCinemaActive(boolean isMavenCinemaActive) {
        this.isMavenCinemaActive = isMavenCinemaActive;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMinYear(int minYear) {
        this.minYear = minYear;
    }

    public int getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(int maxYear) {
        this.maxYear = maxYear;
    }
    
    public float getMinMavenRating() {
        return minMavenRating;
    }
    
    public void setMinMavenRating(float minMavenRating) {
        this.minMavenRating = minMavenRating;
    }
    
    
}
