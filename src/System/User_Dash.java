package System;

import java.sql.Connection;    // DB connection
import java.sql.DriverManager; // DB connection
import java.sql.ResultSet;     // DB connection
import java.sql.SQLException;  // DB connection
import java.sql.Statement;     // DB connection
import javax.swing.ImageIcon; //fetch Image
import javax.swing.JLabel;
import java.awt.Image;                             //classes required for cropping the main image
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.List; // filter custom 
import java.util.ArrayList;
import java.util.Calendar;// to get the curreent calendar 
import javax.swing.JOptionPane;



public class User_Dash extends javax.swing.JFrame {
    //DB CONNECTION INITIALISATION 
    static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    static final String USER = "root";
    static final String PASS = "";
    
    private int totalRecords = 0;  // Define totalRecords here
    // New state variables for pagination - will help in keeping track of which set of movies to display. 
    private int currentPage = 1;  // Keeps track of the current page
    private int recordsPerPage = 35;  // Number of records to show per page
    private MovieFilter currentFilter; // pagination with filters
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);//current year for filter validation


    public User_Dash() {
        initComponents();
        
        //RESIZE THE WINDOW
        this.setSize(800, 600);
        this.setResizable(false);

        // Initialize currentPage to 1
        currentPage = 1;  // this line ensures that currentPage starts at 1 every time this class is instantiated
        
        // Fetch and display the first two movies
        MovieFilter movieFilter = new MovieFilter();
        movieFilter.setFilterMavenCinema(false); // Or true, depending on your need
        fetchAndDisplayMovies(movieFilter);
    }
    
    
    //argument moviefilter  method is used apply various filters based on the properties set in the MovieFilter object
    private void fetchAndDisplayMovies(MovieFilter movieFilter) {
        
        //method to set all labels visible
        setAllLabelsVisible();
    
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            
            // New code to count filtered records
            String countSql = "SELECT COUNT(*) FROM all_movies_db";
            if (movieFilter.getFilterMavenCinema()) {
                countSql += " WHERE Maven_Cinema_Activity = 'YES'";
        }
            ResultSet rsCount = stmt.executeQuery(countSql);
            int totalRecords = 0; // Declare a new local variable to hold the count
            if (rsCount.next()) {
                totalRecords = rsCount.getInt(1);
    }
            rsCount.close(); // Close the ResultSet for the count query
            
            // New code to adjust recordsPerPage
            if (totalRecords <= 35) {
               recordsPerPage = totalRecords;  // If the filtered records are less than or equal to 35, show them all in one page
} 
            else {
            recordsPerPage = 35; // Otherwise, stick to the original page size
}

            
            // Calculate the starting record and the ending record for each page
            int startRecord = (currentPage - 1) * recordsPerPage; // 
            
            System.out.println("Current Page: " + currentPage);
            System.out.println("Start Record: " + startRecord);
            
            // Updated the SQL query to fetch based on the pagination variables-calculation based on currentPage and recordsPerPage.
            //The LIMIT clause now has two arguments-- first argument specifies the starting point, and the second argument specifies the number ofrecords to retrieve.
            StringBuilder sql = new StringBuilder("SELECT * FROM all_movies_db WHERE 1=1");  // Always true condition to simplify appending

            
            // For Maven Cinema filter
            if (movieFilter.getFilterMavenCinema()) {
            sql.append(" AND Maven_Cinema_Activity = 'YES'");
}
// For Genre filter
if (movieFilter.getGenres() != null && !movieFilter.getGenres().isEmpty()) {
    sql.append(" AND (");
    for (int i = 0; i < movieFilter.getGenres().size(); i++) {
        sql.append("FIND_IN_SET('").append(movieFilter.getGenres().get(i)).append("', mov_genre_db) > 0");
        if (i < movieFilter.getGenres().size() - 1) {
            sql.append(" OR ");
        }
    }
    sql.append(")");
}

             // For Maven Rating filter
             if (movieFilter.getMinMavenRating() >= 0) {
                sql.append(" AND mov_rating_db >= ").append(movieFilter.getMinMavenRating());
}


            // For Content Rating filter
            if (movieFilter.getContentRatings() != null && !movieFilter.getContentRatings().isEmpty()) {
                sql.append(" AND Content_Rating_db IN (");
                for (int i = 0; i < movieFilter.getContentRatings().size(); i++) {
            sql.append("'").append(movieFilter.getContentRatings().get(i)).append("'");
            if (i < movieFilter.getContentRatings().size() - 1) {
                sql.append(", ");
        }
    }
    sql.append(")");
}

        
                       
            // New Releases filter
            if (movieFilter.getFilterNewReleases()) {
                if (sql.toString().contains("WHERE")) { 
                    sql.append(" AND mov_year_db >= 2020");
                } else {
                    sql.append(" WHERE mov_year_db >= 2020");
    }
}

// sorting by year for New Releases filter
if (movieFilter.getFilterNewReleases()) {
    sql.append(" ORDER BY mov_year_db DESC");
}

            // If the Top Maven Ratings filter is enabled, add it to the query
            if (movieFilter.getFilterTopMavenRatings()) {
                if (sql.toString().contains("WHERE")) {
                    sql.append(" AND mov_rating_db > 4.9");
            } else {
                sql.append(" WHERE mov_rating_db > 4.9");
    }
    sql.append(" ORDER BY mov_rating_db DESC");
}
            
            sql.append(" LIMIT ").append(startRecord).append(", ").append(recordsPerPage);
            
            ResultSet rs = stmt.executeQuery(sql.toString());

            int movieCounter = 0; // To keep track of which movie is processing

            while (rs.next()) {
                String movieName = rs.getString("movie_name_db");
                int movieYear = rs.getInt("mov_year_db");
                String movieGenre = rs.getString("mov_genre_db");
                float movieRating = rs.getFloat("mov_rating_db");
                float imdbRating = rs.getFloat("IMDb_Rating_db");
                float rottenTomatoRating = rs.getFloat("Rotten_Tomatos_db");
                String imagePath = rs.getString("image_path");

                switch (movieCounter) {
                    case 0:
                        populateMovieDetails(JLB_MOVIE1_Name, JLB_MOVIE1_YearGenre, JLB_MOVIE1_Rating, lblMoviePreview1, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 1:
                        populateMovieDetails(JLB_MOVIE2_Name, JLB_MOVIE2_YearGenre, JLB_MOVIE2_Rating, lblMoviePreview2, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 2:
                        populateMovieDetails(JLB_MOVIE3_Name, JLB_MOVIE3_YearGenre, JLB_MOVIE3_Rating, lblMoviePreview3, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 3:
                        populateMovieDetails(JLB_MOVIE4_Name, JLB_MOVIE4_YearGenre, JLB_MOVIE4_Rating, lblMoviePreview4, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 4:
                        populateMovieDetails(JLB_MOVIE5_Name, JLB_MOVIE5_YearGenre, JLB_MOVIE5_Rating, lblMoviePreview5, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 5:
                        populateMovieDetails(JLB_MOVIE6_Name, JLB_MOVIE6_YearGenre, JLB_MOVIE6_Rating, lblMoviePreview6, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 6:
                        populateMovieDetails(JLB_MOVIE7_Name, JLB_MOVIE7_YearGenre, JLB_MOVIE7_Rating, lblMoviePreview7, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 7:
                        populateMovieDetails(JLB_MOVIE8_Name, JLB_MOVIE8_YearGenre, JLB_MOVIE8_Rating, lblMoviePreview8, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 8:
                        populateMovieDetails(JLB_MOVIE9_Name, JLB_MOVIE9_YearGenre, JLB_MOVIE9_Rating, lblMoviePreview9, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 9:
                        populateMovieDetails(JLB_MOVIE10_Name, JLB_MOVIE10_YearGenre, JLB_MOVIE10_Rating, lblMoviePreview10, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 10:
                        populateMovieDetails(JLB_MOVIE11_Name, JLB_MOVIE11_YearGenre, JLB_MOVIE11_Rating, lblMoviePreview11, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 11:
                        populateMovieDetails(JLB_MOVIE12_Name, JLB_MOVIE12_YearGenre, JLB_MOVIE12_Rating, lblMoviePreview12, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 12:
                        populateMovieDetails(JLB_MOVIE13_Name, JLB_MOVIE13_YearGenre, JLB_MOVIE13_Rating, lblMoviePreview13, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 13:
                        populateMovieDetails(JLB_MOVIE14_Name, JLB_MOVIE14_YearGenre, JLB_MOVIE14_Rating, lblMoviePreview14, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 14:
                        populateMovieDetails(JLB_MOVIE15_Name, JLB_MOVIE15_YearGenre, JLB_MOVIE15_Rating, lblMoviePreview15, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 15:
                        populateMovieDetails(JLB_MOVIE16_Name, JLB_MOVIE16_YearGenre, JLB_MOVIE16_Rating, lblMoviePreview16, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 16:
                        populateMovieDetails(JLB_MOVIE17_Name, JLB_MOVIE17_YearGenre, JLB_MOVIE17_Rating, lblMoviePreview17, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 17:
                        populateMovieDetails(JLB_MOVIE18_Name, JLB_MOVIE18_YearGenre, JLB_MOVIE18_Rating, lblMoviePreview18, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 18:
                        populateMovieDetails(JLB_MOVIE19_Name, JLB_MOVIE19_YearGenre, JLB_MOVIE19_Rating, lblMoviePreview19, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 19:
                        populateMovieDetails(JLB_MOVIE20_Name, JLB_MOVIE20_YearGenre, JLB_MOVIE20_Rating, lblMoviePreview20, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 20:
                        populateMovieDetails(JLB_MOVIE21_Name, JLB_MOVIE21_YearGenre, JLB_MOVIE21_Rating, lblMoviePreview21, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 21:
                        populateMovieDetails(JLB_MOVIE22_Name, JLB_MOVIE22_YearGenre, JLB_MOVIE22_Rating, lblMoviePreview22, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 22:
                        populateMovieDetails(JLB_MOVIE23_Name, JLB_MOVIE23_YearGenre, JLB_MOVIE23_Rating, lblMoviePreview23, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 23:
                        populateMovieDetails(JLB_MOVIE24_Name, JLB_MOVIE24_YearGenre, JLB_MOVIE24_Rating, lblMoviePreview24, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 24:
                        populateMovieDetails(JLB_MOVIE25_Name, JLB_MOVIE25_YearGenre, JLB_MOVIE25_Rating, lblMoviePreview25, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 25:
                        populateMovieDetails(JLB_MOVIE26_Name, JLB_MOVIE26_YearGenre, JLB_MOVIE26_Rating, lblMoviePreview26, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 26:
                        populateMovieDetails(JLB_MOVIE27_Name, JLB_MOVIE27_YearGenre, JLB_MOVIE27_Rating, lblMoviePreview27, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 27:
                        populateMovieDetails(JLB_MOVIE28_Name, JLB_MOVIE28_YearGenre, JLB_MOVIE28_Rating, lblMoviePreview28, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 28:
                        populateMovieDetails(JLB_MOVIE29_Name, JLB_MOVIE29_YearGenre, JLB_MOVIE29_Rating, lblMoviePreview29, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 29:
                        populateMovieDetails(JLB_MOVIE30_Name, JLB_MOVIE30_YearGenre, JLB_MOVIE30_Rating, lblMoviePreview30, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 30:
                        populateMovieDetails(JLB_MOVIE31_Name, JLB_MOVIE31_YearGenre, JLB_MOVIE31_Rating, lblMoviePreview31, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 31:
                        populateMovieDetails(JLB_MOVIE32_Name, JLB_MOVIE32_YearGenre, JLB_MOVIE32_Rating, lblMoviePreview32, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 32:
                        populateMovieDetails(JLB_MOVIE33_Name, JLB_MOVIE33_YearGenre, JLB_MOVIE33_Rating, lblMoviePreview33, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 33:
                        populateMovieDetails(JLB_MOVIE34_Name, JLB_MOVIE34_YearGenre, JLB_MOVIE34_Rating, lblMoviePreview34, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
                    case 34:
                        populateMovieDetails(JLB_MOVIE35_Name, JLB_MOVIE35_YearGenre, JLB_MOVIE35_Rating, lblMoviePreview35, movieName, movieYear, movieGenre, movieRating, imdbRating, rottenTomatoRating, imagePath);
                        break;
               default:
        break;
}

                movieCounter++;
            }

            // HIDE UNUSED LABELS
    for (int i = movieCounter; i < 35; i++) {
        switch (i) {
            case 0:
                JLB_MOVIE1_Name.setVisible(false);
                JLB_MOVIE1_YearGenre.setVisible(false);
                JLB_MOVIE1_Rating.setVisible(false);
                lblMoviePreview1.setVisible(false);
                break;
            case 1:
                JLB_MOVIE2_Name.setVisible(false);
                JLB_MOVIE2_YearGenre.setVisible(false);
                JLB_MOVIE2_Rating.setVisible(false);
                lblMoviePreview2.setVisible(false);
                break;
            case 2:
                JLB_MOVIE3_Name.setVisible(false);
                JLB_MOVIE3_YearGenre.setVisible(false);
                JLB_MOVIE3_Rating.setVisible(false);
                lblMoviePreview3.setVisible(false);
                break;
            case 3:
                JLB_MOVIE4_Name.setVisible(false);
                JLB_MOVIE4_YearGenre.setVisible(false);
                JLB_MOVIE4_Rating.setVisible(false);
                lblMoviePreview4.setVisible(false);
                break;
            case 4:
                JLB_MOVIE5_Name.setVisible(false);
                JLB_MOVIE5_YearGenre.setVisible(false);
                JLB_MOVIE5_Rating.setVisible(false);
                lblMoviePreview5.setVisible(false);
                break;
            case 5:
                JLB_MOVIE6_Name.setVisible(false);
                JLB_MOVIE6_YearGenre.setVisible(false);
                JLB_MOVIE6_Rating.setVisible(false);
                lblMoviePreview6.setVisible(false);
                break;
            case 6:
                JLB_MOVIE7_Name.setVisible(false);
                JLB_MOVIE7_YearGenre.setVisible(false);
                JLB_MOVIE7_Rating.setVisible(false);
                lblMoviePreview7.setVisible(false);
                break;
            case 7:
                JLB_MOVIE8_Name.setVisible(false);
                JLB_MOVIE8_YearGenre.setVisible(false);
                JLB_MOVIE8_Rating.setVisible(false);
                lblMoviePreview8.setVisible(false);
                break;
            case 8:
                JLB_MOVIE9_Name.setVisible(false);
                JLB_MOVIE9_YearGenre.setVisible(false);
                JLB_MOVIE9_Rating.setVisible(false);
                lblMoviePreview9.setVisible(false);
                break;
            case 9:
                JLB_MOVIE10_Name.setVisible(false);
                JLB_MOVIE10_YearGenre.setVisible(false);
                JLB_MOVIE10_Rating.setVisible(false);
                lblMoviePreview10.setVisible(false);
                break;
                
            case 10:
                JLB_MOVIE11_Name.setVisible(false);
                JLB_MOVIE11_YearGenre.setVisible(false);
                JLB_MOVIE11_Rating.setVisible(false);
                lblMoviePreview11.setVisible(false);
                break;
            case 11:
            JLB_MOVIE12_Name.setVisible(false);
            JLB_MOVIE12_YearGenre.setVisible(false);
            JLB_MOVIE12_Rating.setVisible(false);
            lblMoviePreview12.setVisible(false);
            break;
            case 12:
            JLB_MOVIE13_Name.setVisible(false);
            JLB_MOVIE13_YearGenre.setVisible(false);
            JLB_MOVIE13_Rating.setVisible(false);
            lblMoviePreview13.setVisible(false);
            break;
            case 13:
            JLB_MOVIE14_Name.setVisible(false);
            JLB_MOVIE14_YearGenre.setVisible(false);
            JLB_MOVIE14_Rating.setVisible(false);
            lblMoviePreview14.setVisible(false);
            break;
            case 14:
            JLB_MOVIE15_Name.setVisible(false);
            JLB_MOVIE15_YearGenre.setVisible(false);
            JLB_MOVIE15_Rating.setVisible(false);
            lblMoviePreview15.setVisible(false);
            break;
            case 15:
            JLB_MOVIE16_Name.setVisible(false);
            JLB_MOVIE16_YearGenre.setVisible(false);
            JLB_MOVIE16_Rating.setVisible(false);
            lblMoviePreview16.setVisible(false);
            break;
            
            case 16:
            JLB_MOVIE17_Name.setVisible(false);
            JLB_MOVIE17_YearGenre.setVisible(false);
            JLB_MOVIE17_Rating.setVisible(false);
            lblMoviePreview17.setVisible(false);
            break;
            case 17:
            JLB_MOVIE18_Name.setVisible(false);
            JLB_MOVIE18_YearGenre.setVisible(false);
            JLB_MOVIE18_Rating.setVisible(false);
            lblMoviePreview18.setVisible(false);
            break;
            case 18:
            JLB_MOVIE19_Name.setVisible(false);
            JLB_MOVIE19_YearGenre.setVisible(false);
            JLB_MOVIE19_Rating.setVisible(false);
            lblMoviePreview19.setVisible(false);
            break;
            case 19:
            JLB_MOVIE20_Name.setVisible(false);
            JLB_MOVIE20_YearGenre.setVisible(false);
            JLB_MOVIE20_Rating.setVisible(false);
            lblMoviePreview20.setVisible(false);
            break;
            case 20:
            JLB_MOVIE21_Name.setVisible(false);
            JLB_MOVIE21_YearGenre.setVisible(false);
            JLB_MOVIE21_Rating.setVisible(false);
            lblMoviePreview21.setVisible(false);
            break;
            case 21:
            JLB_MOVIE22_Name.setVisible(false);
            JLB_MOVIE22_YearGenre.setVisible(false);
            JLB_MOVIE22_Rating.setVisible(false);
            lblMoviePreview22.setVisible(false);
            break;
            case 22:
            JLB_MOVIE23_Name.setVisible(false);
            JLB_MOVIE23_YearGenre.setVisible(false);
            JLB_MOVIE23_Rating.setVisible(false);
            lblMoviePreview23.setVisible(false);
            break;
            case 23:
            JLB_MOVIE24_Name.setVisible(false);
            JLB_MOVIE24_YearGenre.setVisible(false);
            JLB_MOVIE24_Rating.setVisible(false);
            lblMoviePreview24.setVisible(false);
            break;
            case 24:
            JLB_MOVIE25_Name.setVisible(false);
            JLB_MOVIE25_YearGenre.setVisible(false);
            JLB_MOVIE25_Rating.setVisible(false);
            lblMoviePreview25.setVisible(false);
            break;
            case 25:
            JLB_MOVIE26_Name.setVisible(false);
            JLB_MOVIE26_YearGenre.setVisible(false);
            JLB_MOVIE26_Rating.setVisible(false);
            lblMoviePreview26.setVisible(false);
            break;
            case 26:
            JLB_MOVIE27_Name.setVisible(false);
            JLB_MOVIE27_YearGenre.setVisible(false);
            JLB_MOVIE27_Rating.setVisible(false);
            lblMoviePreview27.setVisible(false);
            break;
            case 27:
            JLB_MOVIE28_Name.setVisible(false);
            JLB_MOVIE28_YearGenre.setVisible(false);
            JLB_MOVIE28_Rating.setVisible(false);
            lblMoviePreview28.setVisible(false);
            break;
            case 28:
            JLB_MOVIE29_Name.setVisible(false);
            JLB_MOVIE29_YearGenre.setVisible(false);
            JLB_MOVIE29_Rating.setVisible(false);
            lblMoviePreview29.setVisible(false);
            break;
            case 29:
            JLB_MOVIE30_Name.setVisible(false);
            JLB_MOVIE30_YearGenre.setVisible(false);
            JLB_MOVIE30_Rating.setVisible(false);
            lblMoviePreview30.setVisible(false);
            break;
            case 30:
            JLB_MOVIE31_Name.setVisible(false);
            JLB_MOVIE31_YearGenre.setVisible(false);
            JLB_MOVIE31_Rating.setVisible(false);
            lblMoviePreview31.setVisible(false);
            break;
            case 31:
            JLB_MOVIE32_Name.setVisible(false);
            JLB_MOVIE32_YearGenre.setVisible(false);
            JLB_MOVIE32_Rating.setVisible(false);
            lblMoviePreview32.setVisible(false);
            break;
            case 32:
            JLB_MOVIE33_Name.setVisible(false);
            JLB_MOVIE33_YearGenre.setVisible(false);
            JLB_MOVIE33_Rating.setVisible(false);
            lblMoviePreview33.setVisible(false);
            break;
            case 33:
            JLB_MOVIE34_Name.setVisible(false);
            JLB_MOVIE34_YearGenre.setVisible(false);
            JLB_MOVIE34_Rating.setVisible(false);
            lblMoviePreview34.setVisible(false);
            break;
            case 34:
            JLB_MOVIE35_Name.setVisible(false);
            JLB_MOVIE35_YearGenre.setVisible(false);
            JLB_MOVIE35_Rating.setVisible(false);
            lblMoviePreview35.setVisible(false);
                
            default:
                break;
        }
    }
            
            
            
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {}
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
         updatePaginationButtons();
    }

//determine the number of total pages available and will enable or disable the
    private void updatePaginationButtons() {
    int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

    JBTN_PrevPage.setEnabled(currentPage > 1);
    JBTN_NextPage.setEnabled(currentPage < totalPages);
}

    
    //POPULATE MOVIE DETAILS
    private void populateMovieDetails(JLabel nameLabel, JLabel yearGenreLabel, JLabel ratingLabel, JLabel previewLabel, String movieName, int movieYear, String movieGenre, float movieRating, float imdbRating, float rottenTomatoRating, String imagePath) {
        nameLabel.setText(movieName);
        yearGenreLabel.setText(movieYear + " | " + movieGenre);
        ratingLabel.setText("Maven:" + movieRating + " | IMDb:" + imdbRating + " | Rotten:" + rottenTomatoRating + "%");

        // Resize image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(previewLabel.getWidth(), previewLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(newImage);
        previewLabel.setIcon(finalImage);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_ButtonPanel = new javax.swing.JPanel();
        JBTN_MavenCinemaMovies = new javax.swing.JButton();
        JBTN_Search = new javax.swing.JButton();
        JBTN_Made_For_You = new javax.swing.JButton();
        JBTN_Watchlist = new javax.swing.JButton();
        JBTN_TopMavenRatings = new javax.swing.JButton();
        JBTN_NewReleases = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel_MadeForYou = new javax.swing.JPanel();
        jPanel_Filter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox_GENRE_Action = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Drama = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Mystery = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Adventure = new javax.swing.JCheckBox();
        jCheckBox_GENRE_ScienceFiction = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Romance = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Family = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Animation = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Fantasy = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Biography = new javax.swing.JCheckBox();
        jCheckBox_GENRE_FilmNoir = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Thriller = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Documentary = new javax.swing.JCheckBox();
        jCheckBox_GENRE_War = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Historical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Comedy = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Crime = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Horror = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Musical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Western = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBox_MavenRating = new javax.swing.JComboBox<>();
        jCheckBox_ContentRatingG = new javax.swing.JCheckBox();
        jCheckBox_ContentRatingPG = new javax.swing.JCheckBox();
        jCheckBox_ContentRatingR = new javax.swing.JCheckBox();
        jCheckBoxMavenCinemaActive = new javax.swing.JCheckBox();
        JBTN_ClearFields = new javax.swing.JButton();
        JBTN_FilterButton = new javax.swing.JButton();
        JTF_YearFilter_Min = new javax.swing.JTextField();
        JTF_YearFilter_Max = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMoviePreview1 = new javax.swing.JLabel();
        JLB_MOVIE1_Name = new javax.swing.JLabel();
        JLB_MOVIE1_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE1_Rating = new javax.swing.JLabel();
        lblMoviePreview2 = new javax.swing.JLabel();
        JLB_MOVIE2_Name = new javax.swing.JLabel();
        JLB_MOVIE2_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE2_Rating = new javax.swing.JLabel();
        lblMoviePreview3 = new javax.swing.JLabel();
        JLB_MOVIE3_Name = new javax.swing.JLabel();
        JLB_MOVIE3_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE3_Rating = new javax.swing.JLabel();
        lblMoviePreview4 = new javax.swing.JLabel();
        JLB_MOVIE4_Name = new javax.swing.JLabel();
        JLB_MOVIE4_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE4_Rating = new javax.swing.JLabel();
        lblMoviePreview5 = new javax.swing.JLabel();
        JLB_MOVIE5_Name = new javax.swing.JLabel();
        JLB_MOVIE5_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE5_Rating = new javax.swing.JLabel();
        lblMoviePreview6 = new javax.swing.JLabel();
        JLB_MOVIE6_Name = new javax.swing.JLabel();
        JLB_MOVIE6_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE6_Rating = new javax.swing.JLabel();
        lblMoviePreview7 = new javax.swing.JLabel();
        JLB_MOVIE7_Name = new javax.swing.JLabel();
        JLB_MOVIE7_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE7_Rating = new javax.swing.JLabel();
        lblMoviePreview8 = new javax.swing.JLabel();
        JLB_MOVIE8_Name = new javax.swing.JLabel();
        JLB_MOVIE8_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE8_Rating = new javax.swing.JLabel();
        lblMoviePreview9 = new javax.swing.JLabel();
        JLB_MOVIE9_Name = new javax.swing.JLabel();
        JLB_MOVIE9_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE9_Rating = new javax.swing.JLabel();
        lblMoviePreview10 = new javax.swing.JLabel();
        JLB_MOVIE10_Name = new javax.swing.JLabel();
        JLB_MOVIE10_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE10_Rating = new javax.swing.JLabel();
        lblMoviePreview11 = new javax.swing.JLabel();
        JLB_MOVIE11_Name = new javax.swing.JLabel();
        JLB_MOVIE11_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE11_Rating = new javax.swing.JLabel();
        lblMoviePreview12 = new javax.swing.JLabel();
        JLB_MOVIE12_Name = new javax.swing.JLabel();
        JLB_MOVIE12_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE12_Rating = new javax.swing.JLabel();
        lblMoviePreview13 = new javax.swing.JLabel();
        JLB_MOVIE13_Name = new javax.swing.JLabel();
        JLB_MOVIE13_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE13_Rating = new javax.swing.JLabel();
        lblMoviePreview14 = new javax.swing.JLabel();
        JLB_MOVIE14_Name = new javax.swing.JLabel();
        JLB_MOVIE14_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE14_Rating = new javax.swing.JLabel();
        lblMoviePreview15 = new javax.swing.JLabel();
        JLB_MOVIE15_Name = new javax.swing.JLabel();
        JLB_MOVIE15_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE15_Rating = new javax.swing.JLabel();
        lblMoviePreview16 = new javax.swing.JLabel();
        JLB_MOVIE16_Name = new javax.swing.JLabel();
        JLB_MOVIE16_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE16_Rating = new javax.swing.JLabel();
        lblMoviePreview17 = new javax.swing.JLabel();
        JLB_MOVIE17_Name = new javax.swing.JLabel();
        JLB_MOVIE17_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE17_Rating = new javax.swing.JLabel();
        lblMoviePreview18 = new javax.swing.JLabel();
        JLB_MOVIE18_Name = new javax.swing.JLabel();
        JLB_MOVIE18_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE18_Rating = new javax.swing.JLabel();
        lblMoviePreview19 = new javax.swing.JLabel();
        JLB_MOVIE19_Name = new javax.swing.JLabel();
        JLB_MOVIE19_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE19_Rating = new javax.swing.JLabel();
        lblMoviePreview20 = new javax.swing.JLabel();
        JLB_MOVIE20_Name = new javax.swing.JLabel();
        JLB_MOVIE20_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE20_Rating = new javax.swing.JLabel();
        lblMoviePreview21 = new javax.swing.JLabel();
        JLB_MOVIE21_Name = new javax.swing.JLabel();
        JLB_MOVIE21_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE21_Rating = new javax.swing.JLabel();
        lblMoviePreview22 = new javax.swing.JLabel();
        JLB_MOVIE22_Name = new javax.swing.JLabel();
        JLB_MOVIE22_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE22_Rating = new javax.swing.JLabel();
        lblMoviePreview23 = new javax.swing.JLabel();
        JLB_MOVIE23_Name = new javax.swing.JLabel();
        JLB_MOVIE23_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE23_Rating = new javax.swing.JLabel();
        lblMoviePreview24 = new javax.swing.JLabel();
        JLB_MOVIE24_Name = new javax.swing.JLabel();
        JLB_MOVIE24_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE24_Rating = new javax.swing.JLabel();
        lblMoviePreview25 = new javax.swing.JLabel();
        JLB_MOVIE25_Name = new javax.swing.JLabel();
        JLB_MOVIE25_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE25_Rating = new javax.swing.JLabel();
        lblMoviePreview26 = new javax.swing.JLabel();
        JLB_MOVIE26_Name = new javax.swing.JLabel();
        JLB_MOVIE26_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE26_Rating = new javax.swing.JLabel();
        lblMoviePreview27 = new javax.swing.JLabel();
        JLB_MOVIE27_Name = new javax.swing.JLabel();
        JLB_MOVIE27_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE27_Rating = new javax.swing.JLabel();
        lblMoviePreview28 = new javax.swing.JLabel();
        JLB_MOVIE28_Name = new javax.swing.JLabel();
        JLB_MOVIE28_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE28_Rating = new javax.swing.JLabel();
        lblMoviePreview29 = new javax.swing.JLabel();
        JLB_MOVIE29_Name = new javax.swing.JLabel();
        JLB_MOVIE29_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE29_Rating = new javax.swing.JLabel();
        lblMoviePreview30 = new javax.swing.JLabel();
        JLB_MOVIE30_Name = new javax.swing.JLabel();
        JLB_MOVIE30_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE30_Rating = new javax.swing.JLabel();
        lblMoviePreview31 = new javax.swing.JLabel();
        JLB_MOVIE31_Name = new javax.swing.JLabel();
        JLB_MOVIE31_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE31_Rating = new javax.swing.JLabel();
        lblMoviePreview32 = new javax.swing.JLabel();
        JLB_MOVIE32_Name = new javax.swing.JLabel();
        JLB_MOVIE32_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE32_Rating = new javax.swing.JLabel();
        lblMoviePreview33 = new javax.swing.JLabel();
        JLB_MOVIE33_Name = new javax.swing.JLabel();
        JLB_MOVIE33_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE33_Rating = new javax.swing.JLabel();
        lblMoviePreview34 = new javax.swing.JLabel();
        JLB_MOVIE34_Name = new javax.swing.JLabel();
        JLB_MOVIE34_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE34_Rating = new javax.swing.JLabel();
        lblMoviePreview35 = new javax.swing.JLabel();
        JLB_MOVIE35_Name = new javax.swing.JLabel();
        JLB_MOVIE35_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE35_Rating = new javax.swing.JLabel();
        JTF_Searchfield = new javax.swing.JTextField();
        JBTN_SearchButton = new javax.swing.JButton();
        JBTN_PrevPage = new javax.swing.JButton();
        JBTN_NextPage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1392, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_ButtonPanel.setBackground(new java.awt.Color(0, 0, 0));
        jPanel_ButtonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JBTN_MavenCinemaMovies.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_MavenCinemaMovies.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_MavenCinemaMovies.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_MavenCinemaMovies.setText("MAVEN CINEMA MOVIES");
        JBTN_MavenCinemaMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_MavenCinemaMoviesActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_MavenCinemaMovies, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 190, 60));

        JBTN_Search.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_Search.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_Search.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_Search.setText("PROFILE");
        JBTN_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_SearchActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 90, 60));

        JBTN_Made_For_You.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_Made_For_You.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_Made_For_You.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_Made_For_You.setText("MADE FOR YOU");
        JBTN_Made_For_You.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_Made_For_YouActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_Made_For_You, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 60));

        JBTN_Watchlist.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_Watchlist.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_Watchlist.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_Watchlist.setText("WATCHLIST");
        JBTN_Watchlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_WatchlistActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_Watchlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 190, 60));

        JBTN_TopMavenRatings.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_TopMavenRatings.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_TopMavenRatings.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_TopMavenRatings.setText("TOP MAVEN RATINGS");
        JBTN_TopMavenRatings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_TopMavenRatingsActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_TopMavenRatings, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 190, 60));

        JBTN_NewReleases.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_NewReleases.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_NewReleases.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_NewReleases.setText("NEW RELEASES");
        JBTN_NewReleases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_NewReleasesActionPerformed(evt);
            }
        });
        jPanel_ButtonPanel.add(JBTN_NewReleases, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 190, 60));

        getContentPane().add(jPanel_ButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 1040, 60));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jPanel_MadeForYou.setBackground(new java.awt.Color(0, 0, 0));
        jPanel_MadeForYou.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_Filter.setBackground(new java.awt.Color(51, 51, 51));
        jPanel_Filter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Year");
        jPanel_Filter.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 70, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genre");
        jPanel_Filter.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 50, 30));

        jCheckBox_GENRE_Action.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Action.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Action.setText("Action");
        jPanel_Filter.add(jCheckBox_GENRE_Action, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jCheckBox_GENRE_Drama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Drama.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Drama.setText("Drama");
        jPanel_Filter.add(jCheckBox_GENRE_Drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        jCheckBox_GENRE_Mystery.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Mystery.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Mystery.setText("Mystery");
        jPanel_Filter.add(jCheckBox_GENRE_Mystery, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jCheckBox_GENRE_Adventure.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Adventure.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Adventure.setText("Adventure");
        jPanel_Filter.add(jCheckBox_GENRE_Adventure, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        jCheckBox_GENRE_ScienceFiction.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_ScienceFiction.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_ScienceFiction.setText("Science Fiction");
        jPanel_Filter.add(jCheckBox_GENRE_ScienceFiction, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, -1, -1));

        jCheckBox_GENRE_Romance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Romance.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Romance.setText("Romance");
        jPanel_Filter.add(jCheckBox_GENRE_Romance, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, -1, -1));

        jCheckBox_GENRE_Family.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Family.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Family.setText("Family");
        jPanel_Filter.add(jCheckBox_GENRE_Family, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        jCheckBox_GENRE_Animation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Animation.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Animation.setText("Animation");
        jCheckBox_GENRE_Animation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_GENRE_AnimationActionPerformed(evt);
            }
        });
        jPanel_Filter.add(jCheckBox_GENRE_Animation, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jCheckBox_GENRE_Fantasy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Fantasy.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Fantasy.setText("Fantasy");
        jPanel_Filter.add(jCheckBox_GENRE_Fantasy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jCheckBox_GENRE_Biography.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Biography.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Biography.setText("Biography");
        jPanel_Filter.add(jCheckBox_GENRE_Biography, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, -1, -1));

        jCheckBox_GENRE_FilmNoir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_FilmNoir.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_FilmNoir.setText("Film Noir");
        jPanel_Filter.add(jCheckBox_GENRE_FilmNoir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jCheckBox_GENRE_Thriller.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Thriller.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Thriller.setText("Thriller");
        jPanel_Filter.add(jCheckBox_GENRE_Thriller, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        jCheckBox_GENRE_Documentary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Documentary.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Documentary.setText("Documentary");
        jPanel_Filter.add(jCheckBox_GENRE_Documentary, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jCheckBox_GENRE_War.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_War.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_War.setText("War");
        jPanel_Filter.add(jCheckBox_GENRE_War, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        jCheckBox_GENRE_Historical.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Historical.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Historical.setText("Historical");
        jPanel_Filter.add(jCheckBox_GENRE_Historical, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        jCheckBox_GENRE_Comedy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Comedy.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Comedy.setText("Comedy");
        jPanel_Filter.add(jCheckBox_GENRE_Comedy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jCheckBox_GENRE_Crime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Crime.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Crime.setText("Crime");
        jPanel_Filter.add(jCheckBox_GENRE_Crime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jCheckBox_GENRE_Horror.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Horror.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Horror.setText("Horror");
        jPanel_Filter.add(jCheckBox_GENRE_Horror, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, -1, -1));

        jCheckBox_GENRE_Musical.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Musical.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Musical.setText("Musical");
        jPanel_Filter.add(jCheckBox_GENRE_Musical, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));

        jCheckBox_GENRE_Western.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Western.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Western.setText("Western");
        jPanel_Filter.add(jCheckBox_GENRE_Western, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Maven Rating");
        jPanel_Filter.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 110, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("IMDb Rating");
        jPanel_Filter.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Content Rating");
        jPanel_Filter.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, -1, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quality ");
        jPanel_Filter.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, -1, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Active on Maven Cinemas ");
        jPanel_Filter.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Maven Rating");
        jPanel_Filter.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 110, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Content Rating");
        jPanel_Filter.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, -1, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Active on Maven Cinemas ");
        jPanel_Filter.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, -1, -1));

        jComboBox_MavenRating.setBackground(new java.awt.Color(0, 0, 0));
        jComboBox_MavenRating.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_MavenRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO SELECTION", "10", "9 AND ABOVE", "8 AND ABOVE", "7 AND ABOVE", "6 AND ABOVE", "5 AND ABOVE", "4 AND ABOVE", "3 AND ABOVE", "2 AND ABOVE", "1 AND ABOVE", "0" }));
        jPanel_Filter.add(jComboBox_MavenRating, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 160, 30));

        jCheckBox_ContentRatingG.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_ContentRatingG.setText("G");
        jPanel_Filter.add(jCheckBox_ContentRatingG, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, -1));

        jCheckBox_ContentRatingPG.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_ContentRatingPG.setText("PG");
        jCheckBox_ContentRatingPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ContentRatingPGActionPerformed(evt);
            }
        });
        jPanel_Filter.add(jCheckBox_ContentRatingPG, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        jCheckBox_ContentRatingR.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_ContentRatingR.setText("R");
        jPanel_Filter.add(jCheckBox_ContentRatingR, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, -1, -1));

        jCheckBoxMavenCinemaActive.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxMavenCinemaActive.setText("NOW SHOWING");
        jPanel_Filter.add(jCheckBoxMavenCinemaActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 130, -1));

        JBTN_ClearFields.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_ClearFields.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_ClearFields.setText("CLEAR FIELDS");
        JBTN_ClearFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_ClearFieldsActionPerformed(evt);
            }
        });
        jPanel_Filter.add(JBTN_ClearFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 260, 30));

        JBTN_FilterButton.setBackground(new java.awt.Color(255, 255, 51));
        JBTN_FilterButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_FilterButton.setText("FILTER");
        JBTN_FilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_FilterButtonActionPerformed(evt);
            }
        });
        jPanel_Filter.add(JBTN_FilterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 260, 50));
        jPanel_Filter.add(JTF_YearFilter_Min, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 150, -1));
        jPanel_Filter.add(JTF_YearFilter_Max, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 150, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("to Max:");
        jPanel_Filter.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 60, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FILTER");
        jPanel_Filter.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 280, 50));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Min:");
        jPanel_Filter.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 40, 20));

        jPanel_MadeForYou.add(jPanel_Filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 280, 630));

        lblMoviePreview1.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, 180, 270));

        JLB_MOVIE1_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE1_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE1_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 180, 20));

        JLB_MOVIE1_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE1_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 200, 30));

        JLB_MOVIE1_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE1_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE1_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 200, 30));

        lblMoviePreview2.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 180, 270));

        JLB_MOVIE2_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE2_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE2_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 180, 20));

        JLB_MOVIE2_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE2_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 200, 30));

        JLB_MOVIE2_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE2_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE2_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 200, 30));

        lblMoviePreview3.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 180, 270));

        JLB_MOVIE3_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE3_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE3_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 180, 20));

        JLB_MOVIE3_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE3_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 200, 30));

        JLB_MOVIE3_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE3_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE3_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 200, 30));

        lblMoviePreview4.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 150, 180, 270));

        JLB_MOVIE4_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE4_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE4_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 420, 180, 20));

        JLB_MOVIE4_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE4_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 430, 200, 30));

        JLB_MOVIE4_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE4_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE4_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 440, 200, 30));

        lblMoviePreview5.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview5.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 150, 180, 270));

        JLB_MOVIE5_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE5_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE5_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 420, 180, 20));

        JLB_MOVIE5_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE5_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 430, 200, 30));

        JLB_MOVIE5_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE5_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE5_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 440, 200, 30));

        lblMoviePreview6.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview6.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 180, 270));

        JLB_MOVIE6_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE6_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE6_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 750, 180, 20));

        JLB_MOVIE6_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE6_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 760, 200, 30));

        JLB_MOVIE6_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE6_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE6_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 770, 200, 30));

        lblMoviePreview7.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview7.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 180, 270));

        JLB_MOVIE7_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE7_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE7_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 750, 180, 20));

        JLB_MOVIE7_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE7_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 760, 200, 30));

        JLB_MOVIE7_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE7_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE7_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 770, 200, 30));

        lblMoviePreview8.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview8.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 180, 270));

        JLB_MOVIE8_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE8_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE8_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 750, 180, 20));

        JLB_MOVIE8_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE8_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 760, 200, 30));

        JLB_MOVIE8_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE8_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE8_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 770, 200, 30));

        lblMoviePreview9.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview9.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview9, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 480, 180, 270));

        JLB_MOVIE9_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE9_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE9_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 750, 180, 20));

        JLB_MOVIE9_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE9_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 760, 200, 30));

        JLB_MOVIE9_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE9_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE9_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 770, 200, 30));

        lblMoviePreview10.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview10.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 480, 180, 270));

        JLB_MOVIE10_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE10_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE10_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 750, 180, 20));

        JLB_MOVIE10_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE10_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 760, 200, 30));

        JLB_MOVIE10_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE10_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE10_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 770, 200, 30));

        lblMoviePreview11.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview11.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 810, 180, 270));

        JLB_MOVIE11_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE11_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE11_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1080, 180, 20));

        JLB_MOVIE11_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE11_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1090, 200, 30));

        JLB_MOVIE11_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE11_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE11_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1100, 200, 30));

        lblMoviePreview12.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview12.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 810, 180, 270));

        JLB_MOVIE12_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE12_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE12_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1080, 180, 20));

        JLB_MOVIE12_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE12_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1090, 200, 30));

        JLB_MOVIE12_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE12_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE12_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1100, 200, 30));

        lblMoviePreview13.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview13.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 810, 180, 270));

        JLB_MOVIE13_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE13_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE13_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1080, 180, 20));

        JLB_MOVIE13_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE13_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1090, 200, 30));

        JLB_MOVIE13_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE13_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE13_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1100, 200, 30));

        lblMoviePreview14.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview14.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview14, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 810, 180, 270));

        JLB_MOVIE14_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE14_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE14_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1080, 180, 20));

        JLB_MOVIE14_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE14_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1090, 200, 30));

        JLB_MOVIE14_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE14_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE14_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1100, 200, 30));

        lblMoviePreview15.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview15.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 810, 180, 270));

        JLB_MOVIE15_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE15_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE15_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1080, 180, 20));

        JLB_MOVIE15_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE15_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1090, 200, 30));

        JLB_MOVIE15_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE15_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE15_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1100, 200, 30));

        lblMoviePreview16.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview16.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1140, 180, 270));

        JLB_MOVIE16_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE16_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE16_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1410, 180, 20));

        JLB_MOVIE16_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE16_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1420, 200, 30));

        JLB_MOVIE16_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE16_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE16_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1430, 200, 30));

        lblMoviePreview17.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview17.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1140, 180, 270));

        JLB_MOVIE17_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE17_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE17_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1410, 180, 20));

        JLB_MOVIE17_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE17_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1420, 200, 30));

        JLB_MOVIE17_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE17_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE17_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1430, 200, 30));

        lblMoviePreview18.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview18.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview18, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1140, 180, 270));

        JLB_MOVIE18_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE18_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE18_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1410, 180, 20));

        JLB_MOVIE18_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE18_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1420, 200, 30));

        JLB_MOVIE18_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE18_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE18_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1430, 200, 30));

        lblMoviePreview19.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview19.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview19, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1140, 180, 270));

        JLB_MOVIE19_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE19_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE19_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1410, 180, 20));

        JLB_MOVIE19_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE19_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1420, 200, 30));

        JLB_MOVIE19_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE19_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE19_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1430, 200, 30));

        lblMoviePreview20.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview20.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1140, 180, 270));

        JLB_MOVIE20_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE20_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE20_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1410, 180, 20));

        JLB_MOVIE20_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE20_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1420, 200, 30));

        JLB_MOVIE20_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE20_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE20_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1430, 200, 30));

        lblMoviePreview21.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview21.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1480, 180, 270));

        JLB_MOVIE21_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE21_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE21_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1750, 180, 20));

        JLB_MOVIE21_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE21_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1760, 200, 30));

        JLB_MOVIE21_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE21_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE21_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1770, 200, 30));

        lblMoviePreview22.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview22.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1480, 180, 270));

        JLB_MOVIE22_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE22_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE22_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1750, 180, 20));

        JLB_MOVIE22_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE22_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1760, 200, 30));

        JLB_MOVIE22_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE22_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE22_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1770, 200, 30));

        lblMoviePreview23.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview23.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview23, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1480, 180, 270));

        JLB_MOVIE23_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE23_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE23_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1750, 180, 20));

        JLB_MOVIE23_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE23_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1760, 200, 30));

        JLB_MOVIE23_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE23_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE23_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1770, 200, 30));

        lblMoviePreview24.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview24.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview24, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1480, 180, 270));

        JLB_MOVIE24_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE24_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE24_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1750, 180, 20));

        JLB_MOVIE24_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE24_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1760, 200, 30));

        JLB_MOVIE24_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE24_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE24_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1770, 200, 30));

        lblMoviePreview25.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview25.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1480, 180, 270));

        JLB_MOVIE25_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE25_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE25_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1750, 180, 20));

        JLB_MOVIE25_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE25_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1760, 200, 30));

        JLB_MOVIE25_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE25_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE25_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1770, 200, 30));

        lblMoviePreview26.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview26.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview26, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 1810, 180, 270));

        JLB_MOVIE26_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE26_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE26_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2080, 180, 20));

        JLB_MOVIE26_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE26_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2090, 200, 30));

        JLB_MOVIE26_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE26_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE26_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2100, 200, 30));

        lblMoviePreview27.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview27.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview27, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1810, 180, 270));

        JLB_MOVIE27_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE27_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE27_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2080, 180, 20));

        JLB_MOVIE27_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE27_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2090, 200, 30));

        JLB_MOVIE27_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE27_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE27_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2100, 200, 30));

        lblMoviePreview28.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview28.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview28, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 1810, 180, 270));

        JLB_MOVIE28_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE28_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE28_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2080, 180, 20));

        JLB_MOVIE28_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE28_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2090, 200, 30));

        JLB_MOVIE28_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE28_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE28_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2100, 200, 30));

        lblMoviePreview29.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview29.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview29, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 1810, 180, 270));

        JLB_MOVIE29_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE29_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE29_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2080, 180, 20));

        JLB_MOVIE29_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE29_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2090, 200, 30));

        JLB_MOVIE29_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE29_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE29_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2100, 200, 30));

        lblMoviePreview30.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview30.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview30, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 1810, 180, 270));

        JLB_MOVIE30_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE30_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE30_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2080, 180, 20));

        JLB_MOVIE30_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE30_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2090, 200, 30));

        JLB_MOVIE30_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE30_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE30_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2100, 200, 30));

        lblMoviePreview31.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview31.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview31, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2150, 180, 270));

        JLB_MOVIE31_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE31_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE31_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2420, 180, 20));

        JLB_MOVIE31_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE31_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2430, 200, 30));

        JLB_MOVIE31_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE31_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE31_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 2440, 200, 30));

        lblMoviePreview32.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview32.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2150, 180, 270));

        JLB_MOVIE32_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE32_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE32_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2420, 180, 20));

        JLB_MOVIE32_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE32_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2430, 200, 30));

        JLB_MOVIE32_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE32_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE32_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 2440, 200, 30));

        lblMoviePreview33.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview33.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview33, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2150, 180, 270));

        JLB_MOVIE33_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE33_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE33_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2420, 180, 20));

        JLB_MOVIE33_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE33_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2430, 200, 30));

        JLB_MOVIE33_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE33_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE33_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 2440, 200, 30));

        lblMoviePreview34.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview34.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview34, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2150, 180, 270));

        JLB_MOVIE34_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE34_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE34_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2420, 180, 20));

        JLB_MOVIE34_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE34_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2430, 200, 30));

        JLB_MOVIE34_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE34_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE34_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 2440, 200, 30));

        lblMoviePreview35.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview35.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2150, 180, 270));

        JLB_MOVIE35_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE35_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE35_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2420, 180, 20));

        JLB_MOVIE35_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE35_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2430, 200, 30));

        JLB_MOVIE35_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE35_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE35_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 2440, 200, 30));

        JTF_Searchfield.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        JTF_Searchfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel_MadeForYou.add(JTF_Searchfield, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 710, 40));

        JBTN_SearchButton.setBackground(new java.awt.Color(255, 255, 51));
        JBTN_SearchButton.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        JBTN_SearchButton.setText("SEARCH");
        JBTN_SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_SearchButtonActionPerformed(evt);
            }
        });
        jPanel_MadeForYou.add(JBTN_SearchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 80, 170, 40));

        JBTN_PrevPage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_PrevPage.setText("<< Previous Page");
        JBTN_PrevPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_PrevPageActionPerformed(evt);
            }
        });
        jPanel_MadeForYou.add(JBTN_PrevPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 2510, 180, 40));

        JBTN_NextPage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_NextPage.setText("Next Page >>");
        JBTN_NextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_NextPageActionPerformed(evt);
            }
        });
        jPanel_MadeForYou.add(JBTN_NextPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 2510, 170, 40));

        jScrollPane1.setViewportView(jPanel_MadeForYou);

        jTabbedPane1.addTab("tab7", jScrollPane1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1370, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_Made_For_YouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_Made_For_YouActionPerformed
        
    }//GEN-LAST:event_JBTN_Made_For_YouActionPerformed

    private void JBTN_MavenCinemaMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_MavenCinemaMoviesActionPerformed
        // Create a MovieFilter object and set the Maven Cinema filter to true
        currentFilter = new MovieFilter(); // Create a new MovieFilter object
        currentFilter.setFilterMavenCinema(true); // Set the Maven Cinema filter to true

        // Fetch and display the movies with the new filter
        fetchAndDisplayMovies(currentFilter);

        //scroll back to the top
        jScrollPane1.getVerticalScrollBar().setValue(0);
        
        // Reset current page to the first page
        currentPage = 1;

    }//GEN-LAST:event_JBTN_MavenCinemaMoviesActionPerformed

    private void JBTN_TopMavenRatingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_TopMavenRatingsActionPerformed
         // Create a MovieFilter object and set the Top Maven Ratings filter to true
        MovieFilter movieFilter = new MovieFilter();
        movieFilter.setFilterTopMavenRatings(true);

        // Fetch and display the movies with the new filter
        fetchAndDisplayMovies(movieFilter);

    // Scroll back to the top
    jScrollPane1.getVerticalScrollBar().setValue(0);
    
    // Reset the current page to the first page
        currentPage = 1; 
    }//GEN-LAST:event_JBTN_TopMavenRatingsActionPerformed

    private void JBTN_NewReleasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NewReleasesActionPerformed
    // Create a MovieFilter object and set the New Releases filter to true
    MovieFilter movieFilter = new MovieFilter();
    movieFilter.setFilterNewReleases(true);

    // Fetch and display the movies with the new filter
    fetchAndDisplayMovies(movieFilter);

    // Scroll back to the top
    jScrollPane1.getVerticalScrollBar().setValue(0);
    
    // Reset the current page to the first page
    currentPage = 1; 
    }//GEN-LAST:event_JBTN_NewReleasesActionPerformed

    private void JBTN_WatchlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_WatchlistActionPerformed
        
    }//GEN-LAST:event_JBTN_WatchlistActionPerformed

    private void JBTN_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_SearchActionPerformed
        
    }//GEN-LAST:event_JBTN_SearchActionPerformed

    private void jCheckBox_GENRE_AnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_GENRE_AnimationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_GENRE_AnimationActionPerformed

    private void jCheckBox_ContentRatingPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ContentRatingPGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_ContentRatingPGActionPerformed

    private void setAllLabelsVisible() {//// Set all your labels to visible here
    
    JLB_MOVIE1_Name.setVisible(true);
    JLB_MOVIE1_YearGenre.setVisible(true);
    JLB_MOVIE1_Rating.setVisible(true);
    lblMoviePreview1.setVisible(true);
    
    JLB_MOVIE2_Name.setVisible(true);
    JLB_MOVIE2_YearGenre.setVisible(true);
    JLB_MOVIE2_Rating.setVisible(true);
    lblMoviePreview2.setVisible(true);
    
    JLB_MOVIE3_Name.setVisible(true);
    JLB_MOVIE3_YearGenre.setVisible(true);
    JLB_MOVIE3_Rating.setVisible(true);
    lblMoviePreview3.setVisible(true);
    
    JLB_MOVIE4_Name.setVisible(true);
    JLB_MOVIE4_YearGenre.setVisible(true);
    JLB_MOVIE4_Rating.setVisible(true);
    lblMoviePreview4.setVisible(true);
    
    JLB_MOVIE5_Name.setVisible(true);
    JLB_MOVIE5_YearGenre.setVisible(true);
    JLB_MOVIE5_Rating.setVisible(true);
    lblMoviePreview5.setVisible(true);
    
    JLB_MOVIE6_Name.setVisible(true);
    JLB_MOVIE6_YearGenre.setVisible(true);
    JLB_MOVIE6_Rating.setVisible(true);
    lblMoviePreview6.setVisible(true);
    
    JLB_MOVIE7_Name.setVisible(true);
    JLB_MOVIE7_YearGenre.setVisible(true);
    JLB_MOVIE7_Rating.setVisible(true);
    lblMoviePreview7.setVisible(true);
    
    JLB_MOVIE8_Name.setVisible(true);
    JLB_MOVIE8_YearGenre.setVisible(true);
    JLB_MOVIE8_Rating.setVisible(true);
    lblMoviePreview8.setVisible(true);
    
    JLB_MOVIE9_Name.setVisible(true);
    JLB_MOVIE9_YearGenre.setVisible(true);
    JLB_MOVIE9_Rating.setVisible(true);
    lblMoviePreview9.setVisible(true);
    
    JLB_MOVIE10_Name.setVisible(true);
    JLB_MOVIE10_YearGenre.setVisible(true);
    JLB_MOVIE10_Rating.setVisible(true);
    lblMoviePreview10.setVisible(true);
    
    JLB_MOVIE11_Name.setVisible(true);
    JLB_MOVIE11_YearGenre.setVisible(true);
    JLB_MOVIE11_Rating.setVisible(true);
    lblMoviePreview11.setVisible(true);
    
    JLB_MOVIE12_Name.setVisible(true);
    JLB_MOVIE12_YearGenre.setVisible(true);
    JLB_MOVIE12_Rating.setVisible(true);
    lblMoviePreview12.setVisible(true);
    
    JLB_MOVIE13_Name.setVisible(true);
    JLB_MOVIE13_YearGenre.setVisible(true);
    JLB_MOVIE13_Rating.setVisible(true);
    lblMoviePreview13.setVisible(true);
    
    JLB_MOVIE14_Name.setVisible(true);
    JLB_MOVIE14_YearGenre.setVisible(true);
    JLB_MOVIE14_Rating.setVisible(true);
    lblMoviePreview14.setVisible(true);
    
    JLB_MOVIE15_Name.setVisible(true);
    JLB_MOVIE15_YearGenre.setVisible(true);
    JLB_MOVIE15_Rating.setVisible(true);
    lblMoviePreview15.setVisible(true);
    
    JLB_MOVIE16_Name.setVisible(true);
    JLB_MOVIE16_YearGenre.setVisible(true);
    JLB_MOVIE16_Rating.setVisible(true);
    lblMoviePreview16.setVisible(true);
    
    JLB_MOVIE17_Name.setVisible(true);
    JLB_MOVIE17_YearGenre.setVisible(true);
    JLB_MOVIE17_Rating.setVisible(true);
    lblMoviePreview17.setVisible(true);
    
    JLB_MOVIE18_Name.setVisible(true);
    JLB_MOVIE18_YearGenre.setVisible(true);
    JLB_MOVIE18_Rating.setVisible(true);
    lblMoviePreview18.setVisible(true);
    
    JLB_MOVIE19_Name.setVisible(true);
    JLB_MOVIE19_YearGenre.setVisible(true);
    JLB_MOVIE19_Rating.setVisible(true);
    lblMoviePreview19.setVisible(true);
    
    JLB_MOVIE20_Name.setVisible(true);
    JLB_MOVIE20_YearGenre.setVisible(true);
    JLB_MOVIE20_Rating.setVisible(true);
    lblMoviePreview20.setVisible(true);
    
    JLB_MOVIE21_Name.setVisible(true);
    JLB_MOVIE21_YearGenre.setVisible(true);
    JLB_MOVIE21_Rating.setVisible(true);
    lblMoviePreview21.setVisible(true);
    
    JLB_MOVIE22_Name.setVisible(true);
    JLB_MOVIE22_YearGenre.setVisible(true);
    JLB_MOVIE22_Rating.setVisible(true);
    lblMoviePreview22.setVisible(true);
    
    JLB_MOVIE23_Name.setVisible(true);
    JLB_MOVIE23_YearGenre.setVisible(true);
    JLB_MOVIE23_Rating.setVisible(true);
    lblMoviePreview23.setVisible(true);
    
    JLB_MOVIE24_Name.setVisible(true);
    JLB_MOVIE24_YearGenre.setVisible(true);
    JLB_MOVIE24_Rating.setVisible(true);
    lblMoviePreview24.setVisible(true);
    
    JLB_MOVIE25_Name.setVisible(true);
    JLB_MOVIE25_YearGenre.setVisible(true);
    JLB_MOVIE25_Rating.setVisible(true);
    lblMoviePreview25.setVisible(true);
    
    JLB_MOVIE26_Name.setVisible(true);
    JLB_MOVIE26_YearGenre.setVisible(true);
    JLB_MOVIE26_Rating.setVisible(true);
    lblMoviePreview26.setVisible(true);
    
    JLB_MOVIE27_Name.setVisible(true);
    JLB_MOVIE27_YearGenre.setVisible(true);
    JLB_MOVIE27_Rating.setVisible(true);
    lblMoviePreview27.setVisible(true);
    
    JLB_MOVIE28_Name.setVisible(true);
    JLB_MOVIE28_YearGenre.setVisible(true);
    JLB_MOVIE28_Rating.setVisible(true);
    lblMoviePreview28.setVisible(true);
    
    JLB_MOVIE29_Name.setVisible(true);
    JLB_MOVIE29_YearGenre.setVisible(true);
    JLB_MOVIE29_Rating.setVisible(true);
    lblMoviePreview29.setVisible(true);
    
    JLB_MOVIE30_Name.setVisible(true);
    JLB_MOVIE30_YearGenre.setVisible(true);
    JLB_MOVIE30_Rating.setVisible(true);
    lblMoviePreview30.setVisible(true);
    
    JLB_MOVIE31_Name.setVisible(true);
    JLB_MOVIE31_YearGenre.setVisible(true);
    JLB_MOVIE31_Rating.setVisible(true);
    lblMoviePreview31.setVisible(true);
    
    JLB_MOVIE32_Name.setVisible(true);
    JLB_MOVIE32_YearGenre.setVisible(true);
    JLB_MOVIE32_Rating.setVisible(true);
    lblMoviePreview32.setVisible(true);
    
    JLB_MOVIE33_Name.setVisible(true);
    JLB_MOVIE33_YearGenre.setVisible(true);
    JLB_MOVIE33_Rating.setVisible(true);
    lblMoviePreview33.setVisible(true);
    
    JLB_MOVIE34_Name.setVisible(true);
    JLB_MOVIE34_YearGenre.setVisible(true);
    JLB_MOVIE34_Rating.setVisible(true);
    lblMoviePreview34.setVisible(true);
    
    JLB_MOVIE35_Name.setVisible(true);
    JLB_MOVIE35_YearGenre.setVisible(true);
    JLB_MOVIE35_Rating.setVisible(true);
    lblMoviePreview35.setVisible(true);  
    
}

    private void JBTN_NextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NextPageActionPerformed
        // add 1 to the current page
        currentPage++;
    
        // Fetch and display the movies for the new page
        fetchAndDisplayMovies(currentFilter); // Use the current filter

        jScrollPane1.getVerticalScrollBar().setValue(0); // Scroll upfetchAndDisplayMovies
        updatePaginationButtons();
    }//GEN-LAST:event_JBTN_NextPageActionPerformed

    private void JBTN_PrevPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_PrevPageActionPerformed
        // reduct the current page only if it's greater than 1
        if (currentPage > 1) {
        currentPage--;
        // Fetch and display the movies for the new page
        fetchAndDisplayMovies(currentFilter); // Use the current filter

        jScrollPane1.getVerticalScrollBar().setValue(0); // Scroll to the top
}   else {
    
            System.out.println("Already on the first page");
}
    }//GEN-LAST:event_JBTN_PrevPageActionPerformed

    private void JBTN_FilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_FilterButtonActionPerformed
// Get the current year
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    
    // Initialize movie filter
    MovieFilter movieFilter = new MovieFilter();

    // Validate and filter by year
    String minYearStr = JTF_YearFilter_Min.getText().trim();
    String maxYearStr = JTF_YearFilter_Max.getText().trim();
    
    if (!minYearStr.isEmpty()) {
        int minYear = Integer.parseInt(minYearStr);
        if (minYear < 1888 || minYear > currentYear + 2) {
            JOptionPane.showMessageDialog(this, "Minimum year should be between 1888 and " + (currentYear + 2));
            return;
        }
    }
    
    if (!maxYearStr.isEmpty()) {
        int maxYear = Integer.parseInt(maxYearStr);
        if (maxYear < 1888 || maxYear > currentYear + 2) {
            JOptionPane.showMessageDialog(this, "Maximum year should be between 1888 and " + (currentYear + 2));
            return;
        }
    }
    
    if (!minYearStr.isEmpty() && !maxYearStr.isEmpty()) {
        int minYear = Integer.parseInt(minYearStr);
        int maxYear = Integer.parseInt(maxYearStr);
        if (maxYear <= minYear) {
            JOptionPane.showMessageDialog(this, "Maximum year should be greater than minimum year.");
            return;
        }
        movieFilter.setMinYear(minYear);
        movieFilter.setMaxYear(maxYear);
    }
    
    // Genre Filter
        List<String> selectedGenres = new ArrayList<>();
        if (jCheckBox_GENRE_Action.isSelected()) selectedGenres.add("Action");
        if (jCheckBox_GENRE_Drama.isSelected()) selectedGenres.add("Drama");
        if (jCheckBox_GENRE_Mystery.isSelected()) selectedGenres.add("Mystery");
        if (jCheckBox_GENRE_Animation.isSelected()) selectedGenres.add("Animation");
        if (jCheckBox_GENRE_Family.isSelected()) selectedGenres.add("Family");
        if (jCheckBox_GENRE_Romance.isSelected()) selectedGenres.add("Romance");
        if (jCheckBox_GENRE_Fantasy.isSelected()) selectedGenres.add("Fantasy");
        if (jCheckBox_GENRE_Biography.isSelected()) selectedGenres.add("Biography");
        if (jCheckBox_GENRE_FilmNoir.isSelected()) selectedGenres.add("Film Noir");
        if (jCheckBox_GENRE_Comedy.isSelected()) selectedGenres.add("Comedy");
        if (jCheckBox_GENRE_Historical.isSelected()) selectedGenres.add("Historical");
        if (jCheckBox_GENRE_War.isSelected()) selectedGenres.add("War");
        if (jCheckBox_GENRE_Crime.isSelected()) selectedGenres.add("Crime");
        if (jCheckBox_GENRE_Horror.isSelected()) selectedGenres.add("Horror");
        if (jCheckBox_GENRE_Musical.isSelected()) selectedGenres.add("Musical");
        if (jCheckBox_GENRE_Adventure.isSelected()) selectedGenres.add("Adventure");
        if (jCheckBox_GENRE_Western.isSelected()) selectedGenres.add("Western");
        if (jCheckBox_GENRE_Thriller.isSelected()) selectedGenres.add("Thriller");
        if (jCheckBox_GENRE_Documentary.isSelected()) selectedGenres.add("Documentary");
        if (jCheckBox_GENRE_ScienceFiction.isSelected()) selectedGenres.add("Science Fiction");

if (!selectedGenres.isEmpty()) {
    movieFilter.setGenres(selectedGenres);
}

    // Maven Rating Filter
    String mavenRatingSelection = (String) jComboBox_MavenRating.getSelectedItem();
    if (!"NO SELECTION".equals(mavenRatingSelection)) {
        // ... do something to set the Maven Rating filter, e.g. parse the selection to a float value
        float minRating = Float.parseFloat(mavenRatingSelection.split(" ")[0]);  // Assuming the format is "5 AND ABOVE"
        movieFilter.setMinMavenRating(minRating);
    }

    // Content Rating Filter
    List<String> selectedContentRatings = new ArrayList<>();
    if (jCheckBox_ContentRatingG.isSelected()) selectedContentRatings.add("G");
    if (jCheckBox_ContentRatingPG.isSelected()) selectedContentRatings.add("PG");
    if (jCheckBox_ContentRatingR.isSelected()) selectedContentRatings.add("R");
    if (!selectedContentRatings.isEmpty()) {
        movieFilter.setContentRatings(selectedContentRatings);
    }

    // Maven Cinema Filter
    if (jCheckBoxMavenCinemaActive.isSelected()) {
        movieFilter.setFilterMavenCinema(true);
    }

    // Fetch and display the movies with the new filter
    fetchAndDisplayMovies(movieFilter);
    }//GEN-LAST:event_JBTN_FilterButtonActionPerformed

    private void JBTN_ClearFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_ClearFieldsActionPerformed
        // Clear year filter fields
    JTF_YearFilter_Min.setText("");
    JTF_YearFilter_Max.setText("");

    // Clear genre checkboxes
    jCheckBox_GENRE_Action.setSelected(false);
    jCheckBox_GENRE_Drama.setSelected(false);
    jCheckBox_GENRE_Mystery.setSelected(false);
    jCheckBox_GENRE_Animation.setSelected(false);
    jCheckBox_GENRE_Family.setSelected(false);
    jCheckBox_GENRE_Romance.setSelected(false);
    jCheckBox_GENRE_Fantasy.setSelected(false);
    jCheckBox_GENRE_Biography.setSelected(false);
    jCheckBox_GENRE_FilmNoir.setSelected(false);
    jCheckBox_GENRE_Comedy.setSelected(false);
    jCheckBox_GENRE_Historical.setSelected(false);
    jCheckBox_GENRE_War.setSelected(false);
    jCheckBox_GENRE_Crime.setSelected(false);
    jCheckBox_GENRE_Horror.setSelected(false);
    jCheckBox_GENRE_Musical.setSelected(false);
    jCheckBox_GENRE_Adventure.setSelected(false);
    jCheckBox_GENRE_Western.setSelected(false);
    jCheckBox_GENRE_Thriller.setSelected(false);
    jCheckBox_GENRE_Documentary.setSelected(false);
    jCheckBox_GENRE_ScienceFiction.setSelected(false);

    // Reset Maven Rating drop-down
    jComboBox_MavenRating.setSelectedIndex(0);

    // Clear rating checkboxes
    jCheckBox_ContentRatingG.setSelected(false);
    jCheckBox_ContentRatingPG.setSelected(false);
    jCheckBox_ContentRatingR.setSelected(false);

    // Clear Maven Cinema checkbox
    jCheckBoxMavenCinemaActive.setSelected(false);
    }//GEN-LAST:event_JBTN_ClearFieldsActionPerformed

    private void JBTN_SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_SearchButtonActionPerformed
        
    }//GEN-LAST:event_JBTN_SearchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Dash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTN_ClearFields;
    private javax.swing.JButton JBTN_FilterButton;
    private javax.swing.JButton JBTN_Made_For_You;
    private javax.swing.JButton JBTN_MavenCinemaMovies;
    private javax.swing.JButton JBTN_NewReleases;
    private javax.swing.JButton JBTN_NextPage;
    private javax.swing.JButton JBTN_PrevPage;
    private javax.swing.JButton JBTN_Search;
    private javax.swing.JButton JBTN_SearchButton;
    private javax.swing.JButton JBTN_TopMavenRatings;
    private javax.swing.JButton JBTN_Watchlist;
    private javax.swing.JLabel JLB_MOVIE10_Name;
    private javax.swing.JLabel JLB_MOVIE10_Rating;
    private javax.swing.JLabel JLB_MOVIE10_YearGenre;
    private javax.swing.JLabel JLB_MOVIE11_Name;
    private javax.swing.JLabel JLB_MOVIE11_Rating;
    private javax.swing.JLabel JLB_MOVIE11_YearGenre;
    private javax.swing.JLabel JLB_MOVIE12_Name;
    private javax.swing.JLabel JLB_MOVIE12_Rating;
    private javax.swing.JLabel JLB_MOVIE12_YearGenre;
    private javax.swing.JLabel JLB_MOVIE13_Name;
    private javax.swing.JLabel JLB_MOVIE13_Rating;
    private javax.swing.JLabel JLB_MOVIE13_YearGenre;
    private javax.swing.JLabel JLB_MOVIE14_Name;
    private javax.swing.JLabel JLB_MOVIE14_Rating;
    private javax.swing.JLabel JLB_MOVIE14_YearGenre;
    private javax.swing.JLabel JLB_MOVIE15_Name;
    private javax.swing.JLabel JLB_MOVIE15_Rating;
    private javax.swing.JLabel JLB_MOVIE15_YearGenre;
    private javax.swing.JLabel JLB_MOVIE16_Name;
    private javax.swing.JLabel JLB_MOVIE16_Rating;
    private javax.swing.JLabel JLB_MOVIE16_YearGenre;
    private javax.swing.JLabel JLB_MOVIE17_Name;
    private javax.swing.JLabel JLB_MOVIE17_Rating;
    private javax.swing.JLabel JLB_MOVIE17_YearGenre;
    private javax.swing.JLabel JLB_MOVIE18_Name;
    private javax.swing.JLabel JLB_MOVIE18_Rating;
    private javax.swing.JLabel JLB_MOVIE18_YearGenre;
    private javax.swing.JLabel JLB_MOVIE19_Name;
    private javax.swing.JLabel JLB_MOVIE19_Rating;
    private javax.swing.JLabel JLB_MOVIE19_YearGenre;
    private javax.swing.JLabel JLB_MOVIE1_Name;
    private javax.swing.JLabel JLB_MOVIE1_Rating;
    private javax.swing.JLabel JLB_MOVIE1_YearGenre;
    private javax.swing.JLabel JLB_MOVIE20_Name;
    private javax.swing.JLabel JLB_MOVIE20_Rating;
    private javax.swing.JLabel JLB_MOVIE20_YearGenre;
    private javax.swing.JLabel JLB_MOVIE21_Name;
    private javax.swing.JLabel JLB_MOVIE21_Rating;
    private javax.swing.JLabel JLB_MOVIE21_YearGenre;
    private javax.swing.JLabel JLB_MOVIE22_Name;
    private javax.swing.JLabel JLB_MOVIE22_Rating;
    private javax.swing.JLabel JLB_MOVIE22_YearGenre;
    private javax.swing.JLabel JLB_MOVIE23_Name;
    private javax.swing.JLabel JLB_MOVIE23_Rating;
    private javax.swing.JLabel JLB_MOVIE23_YearGenre;
    private javax.swing.JLabel JLB_MOVIE24_Name;
    private javax.swing.JLabel JLB_MOVIE24_Rating;
    private javax.swing.JLabel JLB_MOVIE24_YearGenre;
    private javax.swing.JLabel JLB_MOVIE25_Name;
    private javax.swing.JLabel JLB_MOVIE25_Rating;
    private javax.swing.JLabel JLB_MOVIE25_YearGenre;
    private javax.swing.JLabel JLB_MOVIE26_Name;
    private javax.swing.JLabel JLB_MOVIE26_Rating;
    private javax.swing.JLabel JLB_MOVIE26_YearGenre;
    private javax.swing.JLabel JLB_MOVIE27_Name;
    private javax.swing.JLabel JLB_MOVIE27_Rating;
    private javax.swing.JLabel JLB_MOVIE27_YearGenre;
    private javax.swing.JLabel JLB_MOVIE28_Name;
    private javax.swing.JLabel JLB_MOVIE28_Rating;
    private javax.swing.JLabel JLB_MOVIE28_YearGenre;
    private javax.swing.JLabel JLB_MOVIE29_Name;
    private javax.swing.JLabel JLB_MOVIE29_Rating;
    private javax.swing.JLabel JLB_MOVIE29_YearGenre;
    private javax.swing.JLabel JLB_MOVIE2_Name;
    private javax.swing.JLabel JLB_MOVIE2_Rating;
    private javax.swing.JLabel JLB_MOVIE2_YearGenre;
    private javax.swing.JLabel JLB_MOVIE30_Name;
    private javax.swing.JLabel JLB_MOVIE30_Rating;
    private javax.swing.JLabel JLB_MOVIE30_YearGenre;
    private javax.swing.JLabel JLB_MOVIE31_Name;
    private javax.swing.JLabel JLB_MOVIE31_Rating;
    private javax.swing.JLabel JLB_MOVIE31_YearGenre;
    private javax.swing.JLabel JLB_MOVIE32_Name;
    private javax.swing.JLabel JLB_MOVIE32_Rating;
    private javax.swing.JLabel JLB_MOVIE32_YearGenre;
    private javax.swing.JLabel JLB_MOVIE33_Name;
    private javax.swing.JLabel JLB_MOVIE33_Rating;
    private javax.swing.JLabel JLB_MOVIE33_YearGenre;
    private javax.swing.JLabel JLB_MOVIE34_Name;
    private javax.swing.JLabel JLB_MOVIE34_Rating;
    private javax.swing.JLabel JLB_MOVIE34_YearGenre;
    private javax.swing.JLabel JLB_MOVIE35_Name;
    private javax.swing.JLabel JLB_MOVIE35_Rating;
    private javax.swing.JLabel JLB_MOVIE35_YearGenre;
    private javax.swing.JLabel JLB_MOVIE3_Name;
    private javax.swing.JLabel JLB_MOVIE3_Rating;
    private javax.swing.JLabel JLB_MOVIE3_YearGenre;
    private javax.swing.JLabel JLB_MOVIE4_Name;
    private javax.swing.JLabel JLB_MOVIE4_Rating;
    private javax.swing.JLabel JLB_MOVIE4_YearGenre;
    private javax.swing.JLabel JLB_MOVIE5_Name;
    private javax.swing.JLabel JLB_MOVIE5_Rating;
    private javax.swing.JLabel JLB_MOVIE5_YearGenre;
    private javax.swing.JLabel JLB_MOVIE6_Name;
    private javax.swing.JLabel JLB_MOVIE6_Rating;
    private javax.swing.JLabel JLB_MOVIE6_YearGenre;
    private javax.swing.JLabel JLB_MOVIE7_Name;
    private javax.swing.JLabel JLB_MOVIE7_Rating;
    private javax.swing.JLabel JLB_MOVIE7_YearGenre;
    private javax.swing.JLabel JLB_MOVIE8_Name;
    private javax.swing.JLabel JLB_MOVIE8_Rating;
    private javax.swing.JLabel JLB_MOVIE8_YearGenre;
    private javax.swing.JLabel JLB_MOVIE9_Name;
    private javax.swing.JLabel JLB_MOVIE9_Rating;
    private javax.swing.JLabel JLB_MOVIE9_YearGenre;
    private javax.swing.JTextField JTF_Searchfield;
    private javax.swing.JTextField JTF_YearFilter_Max;
    private javax.swing.JTextField JTF_YearFilter_Min;
    private javax.swing.JCheckBox jCheckBoxMavenCinemaActive;
    private javax.swing.JCheckBox jCheckBox_ContentRatingG;
    private javax.swing.JCheckBox jCheckBox_ContentRatingPG;
    private javax.swing.JCheckBox jCheckBox_ContentRatingR;
    private javax.swing.JCheckBox jCheckBox_GENRE_Action;
    private javax.swing.JCheckBox jCheckBox_GENRE_Adventure;
    private javax.swing.JCheckBox jCheckBox_GENRE_Animation;
    private javax.swing.JCheckBox jCheckBox_GENRE_Biography;
    private javax.swing.JCheckBox jCheckBox_GENRE_Comedy;
    private javax.swing.JCheckBox jCheckBox_GENRE_Crime;
    private javax.swing.JCheckBox jCheckBox_GENRE_Documentary;
    private javax.swing.JCheckBox jCheckBox_GENRE_Drama;
    private javax.swing.JCheckBox jCheckBox_GENRE_Family;
    private javax.swing.JCheckBox jCheckBox_GENRE_Fantasy;
    private javax.swing.JCheckBox jCheckBox_GENRE_FilmNoir;
    private javax.swing.JCheckBox jCheckBox_GENRE_Historical;
    private javax.swing.JCheckBox jCheckBox_GENRE_Horror;
    private javax.swing.JCheckBox jCheckBox_GENRE_Musical;
    private javax.swing.JCheckBox jCheckBox_GENRE_Mystery;
    private javax.swing.JCheckBox jCheckBox_GENRE_Romance;
    private javax.swing.JCheckBox jCheckBox_GENRE_ScienceFiction;
    private javax.swing.JCheckBox jCheckBox_GENRE_Thriller;
    private javax.swing.JCheckBox jCheckBox_GENRE_War;
    private javax.swing.JCheckBox jCheckBox_GENRE_Western;
    private javax.swing.JComboBox<String> jComboBox_MavenRating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel_ButtonPanel;
    private javax.swing.JPanel jPanel_Filter;
    private javax.swing.JPanel jPanel_MadeForYou;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMoviePreview1;
    private javax.swing.JLabel lblMoviePreview10;
    private javax.swing.JLabel lblMoviePreview11;
    private javax.swing.JLabel lblMoviePreview12;
    private javax.swing.JLabel lblMoviePreview13;
    private javax.swing.JLabel lblMoviePreview14;
    private javax.swing.JLabel lblMoviePreview15;
    private javax.swing.JLabel lblMoviePreview16;
    private javax.swing.JLabel lblMoviePreview17;
    private javax.swing.JLabel lblMoviePreview18;
    private javax.swing.JLabel lblMoviePreview19;
    private javax.swing.JLabel lblMoviePreview2;
    private javax.swing.JLabel lblMoviePreview20;
    private javax.swing.JLabel lblMoviePreview21;
    private javax.swing.JLabel lblMoviePreview22;
    private javax.swing.JLabel lblMoviePreview23;
    private javax.swing.JLabel lblMoviePreview24;
    private javax.swing.JLabel lblMoviePreview25;
    private javax.swing.JLabel lblMoviePreview26;
    private javax.swing.JLabel lblMoviePreview27;
    private javax.swing.JLabel lblMoviePreview28;
    private javax.swing.JLabel lblMoviePreview29;
    private javax.swing.JLabel lblMoviePreview3;
    private javax.swing.JLabel lblMoviePreview30;
    private javax.swing.JLabel lblMoviePreview31;
    private javax.swing.JLabel lblMoviePreview32;
    private javax.swing.JLabel lblMoviePreview33;
    private javax.swing.JLabel lblMoviePreview34;
    private javax.swing.JLabel lblMoviePreview35;
    private javax.swing.JLabel lblMoviePreview4;
    private javax.swing.JLabel lblMoviePreview5;
    private javax.swing.JLabel lblMoviePreview6;
    private javax.swing.JLabel lblMoviePreview7;
    private javax.swing.JLabel lblMoviePreview8;
    private javax.swing.JLabel lblMoviePreview9;
    // End of variables declaration//GEN-END:variables
}
