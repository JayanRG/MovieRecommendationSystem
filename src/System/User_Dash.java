package System;

import java.sql.Connection;    // DB connection
import java.sql.DriverManager; // DB connection
import java.sql.ResultSet;     // DB connection
import java.sql.SQLException;  // DB connection
import java.sql.Statement;     // DB connection



public class User_Dash extends javax.swing.JFrame {
    //DB CONNECTION INITIALISATION 
    static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    static final String USER = "root";
    static final String PASS = "";

    public User_Dash() {
        initComponents();
        
        // Fetch and display the first two movies
        fetchAndDisplayMovies();
    }
    
    private void fetchAndDisplayMovies() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver and open a connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM all_movies_db LIMIT 2"; // Fetch first 2 rows <<<<<<<<<<<<<<<<<<<<<<<<<<< should be changed in the future
            ResultSet rs = stmt.executeQuery(sql);

            // Extract data from result set
            while(rs.next()){
                // Retrieve data by column name
                String movieName = rs.getString("movie_name_db");
                int movieYear = rs.getInt("mov_year_db");
                String movieGenre = rs.getString("mov_genre_db");
                float movieRating = rs.getFloat("mov_rating_db");
                float imdbRating = rs.getFloat("IMDb_Rating_db");
                float rottenTomatoRating = rs.getFloat("Rotten_Tomatos_db");
                String imagePath = rs.getString("image_path");

                // Display values (We'll implement this part next)
            }
            rs.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se2) {}
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
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
        jScrollPane_MadeForYou = new javax.swing.JScrollPane();
        jPanel_MadeForYou = new javax.swing.JPanel();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane_WatchList = new javax.swing.JScrollPane();
        jPanel_WatchList = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

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
        JBTN_Search.setText("SEARCH");
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

        jPanel_MadeForYou.setBackground(new java.awt.Color(0, 0, 0));
        jPanel_MadeForYou.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMoviePreview1.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview1.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 180, 270));

        JLB_MOVIE1_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE1_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE1_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 180, 20));

        JLB_MOVIE1_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE1_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 200, 30));

        JLB_MOVIE1_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE1_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE1_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 200, 30));

        lblMoviePreview2.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 180, 270));

        JLB_MOVIE2_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE2_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE2_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 180, 20));

        JLB_MOVIE2_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE2_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 200, 30));

        JLB_MOVIE2_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE2_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE2_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 200, 30));

        lblMoviePreview3.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, 180, 270));

        JLB_MOVIE3_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE3_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE3_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 180, 20));

        JLB_MOVIE3_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE3_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 200, 30));

        JLB_MOVIE3_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE3_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE3_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, 200, 30));

        lblMoviePreview4.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview4.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 80, 180, 270));

        JLB_MOVIE4_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE4_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE4_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 350, 180, 20));

        JLB_MOVIE4_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE4_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, 200, 30));

        JLB_MOVIE4_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE4_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE4_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 200, 30));

        lblMoviePreview5.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview5.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 180, 270));

        JLB_MOVIE5_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE5_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE5_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 350, 180, 20));

        JLB_MOVIE5_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE5_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 360, 200, 30));

        JLB_MOVIE5_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE5_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE5_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 370, 200, 30));

        lblMoviePreview6.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview6.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 180, 270));

        JLB_MOVIE6_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE6_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE6_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 680, 180, 20));

        JLB_MOVIE6_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE6_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 690, 200, 30));

        JLB_MOVIE6_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE6_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE6_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 700, 200, 30));

        lblMoviePreview7.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview7.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, 180, 270));

        JLB_MOVIE7_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE7_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE7_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 680, 180, 20));

        JLB_MOVIE7_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE7_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 690, 200, 30));

        JLB_MOVIE7_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE7_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE7_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 700, 200, 30));

        lblMoviePreview8.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview8.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 180, 270));

        JLB_MOVIE8_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE8_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE8_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 680, 180, 20));

        JLB_MOVIE8_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE8_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 690, 200, 30));

        JLB_MOVIE8_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE8_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE8_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 700, 200, 30));

        lblMoviePreview9.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview9.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview9, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, 180, 270));

        JLB_MOVIE9_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE9_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE9_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 680, 180, 20));

        JLB_MOVIE9_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE9_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 690, 200, 30));

        JLB_MOVIE9_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE9_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE9_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 700, 200, 30));

        lblMoviePreview10.setForeground(new java.awt.Color(255, 255, 255));
        lblMoviePreview10.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel_MadeForYou.add(lblMoviePreview10, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 410, 180, 270));

        JLB_MOVIE10_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE10_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_MadeForYou.add(JLB_MOVIE10_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 680, 180, 20));

        JLB_MOVIE10_YearGenre.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE10_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 690, 200, 30));

        JLB_MOVIE10_Rating.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JLB_MOVIE10_Rating.setForeground(new java.awt.Color(204, 204, 204));
        jPanel_MadeForYou.add(JLB_MOVIE10_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 700, 200, 30));

        jScrollPane_MadeForYou.setViewportView(jPanel_MadeForYou);

        jTabbedPane1.addTab("tab7", jScrollPane_MadeForYou);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("5");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 188, 55, 48));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG-2 blur.jpg"))); // NOI18N
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 750));

        jScrollPane4.setViewportView(jPanel6);

        jTabbedPane1.addTab("tab7", jScrollPane4);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("6");
        jPanel7.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 188, 55, 48));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG-2 blur.jpg"))); // NOI18N
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 750));

        jScrollPane5.setViewportView(jPanel7);

        jTabbedPane1.addTab("tab7", jScrollPane5);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("3");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 188, 55, 48));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG-2 blur.jpg"))); // NOI18N
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 750));

        jScrollPane2.setViewportView(jPanel4);

        jTabbedPane1.addTab("tab7", jScrollPane2);

        jPanel_WatchList.setBackground(new java.awt.Color(0, 0, 0));
        jPanel_WatchList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane_WatchList.setViewportView(jPanel_WatchList);

        jTabbedPane1.addTab("tab7", jScrollPane_WatchList);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("2");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(616, 188, 55, 48));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG-2 blur.jpg"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 750));

        jScrollPane3.setViewportView(jPanel3);

        jTabbedPane1.addTab("tab7", jScrollPane3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 1370, 780));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG-2 blur.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_Made_For_YouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_Made_For_YouActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_JBTN_Made_For_YouActionPerformed

    private void JBTN_MavenCinemaMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_MavenCinemaMoviesActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_JBTN_MavenCinemaMoviesActionPerformed

    private void JBTN_TopMavenRatingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_TopMavenRatingsActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_JBTN_TopMavenRatingsActionPerformed

    private void JBTN_NewReleasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NewReleasesActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_JBTN_NewReleasesActionPerformed

    private void JBTN_WatchlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_WatchlistActionPerformed
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_JBTN_WatchlistActionPerformed

    private void JBTN_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_SearchActionPerformed
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_JBTN_SearchActionPerformed

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
    private javax.swing.JButton JBTN_Made_For_You;
    private javax.swing.JButton JBTN_MavenCinemaMovies;
    private javax.swing.JButton JBTN_NewReleases;
    private javax.swing.JButton JBTN_Search;
    private javax.swing.JButton JBTN_TopMavenRatings;
    private javax.swing.JButton JBTN_Watchlist;
    private javax.swing.JLabel JLB_MOVIE10_Name;
    private javax.swing.JLabel JLB_MOVIE10_Rating;
    private javax.swing.JLabel JLB_MOVIE10_YearGenre;
    private javax.swing.JLabel JLB_MOVIE1_Name;
    private javax.swing.JLabel JLB_MOVIE1_Rating;
    private javax.swing.JLabel JLB_MOVIE1_YearGenre;
    private javax.swing.JLabel JLB_MOVIE2_Name;
    private javax.swing.JLabel JLB_MOVIE2_Rating;
    private javax.swing.JLabel JLB_MOVIE2_YearGenre;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel_ButtonPanel;
    private javax.swing.JPanel jPanel_MadeForYou;
    private javax.swing.JPanel jPanel_WatchList;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane_MadeForYou;
    private javax.swing.JScrollPane jScrollPane_WatchList;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblMoviePreview1;
    private javax.swing.JLabel lblMoviePreview10;
    private javax.swing.JLabel lblMoviePreview2;
    private javax.swing.JLabel lblMoviePreview3;
    private javax.swing.JLabel lblMoviePreview4;
    private javax.swing.JLabel lblMoviePreview5;
    private javax.swing.JLabel lblMoviePreview6;
    private javax.swing.JLabel lblMoviePreview7;
    private javax.swing.JLabel lblMoviePreview8;
    private javax.swing.JLabel lblMoviePreview9;
    // End of variables declaration//GEN-END:variables
}
