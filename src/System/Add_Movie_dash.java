package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.*;
import java.sql.*;


public class Add_Movie_dash extends javax.swing.JFrame {

    public Add_Movie_dash() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JBTN_Add_Movie = new javax.swing.JButton();
        JTF_Movie_Name = new javax.swing.JTextField();
        JTF_Year = new javax.swing.JTextField();
        JTF_Rating = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea_Description = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JTF_Genre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTextArea_Cast = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        JTF_IMDb_Rating = new javax.swing.JTextField();
        JTF_Rotten_Tomatoes = new javax.swing.JTextField();
        JTF_Director = new javax.swing.JTextField();
        JTF_Composer = new javax.swing.JTextField();
        JComboBox_Country = new javax.swing.JComboBox<>();
        JComboBox_Quality = new javax.swing.JComboBox<>();
        JTF_Duration = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        JCheckBox_ShowingTimes_1030AM = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        JBTN_Update = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        JComboBox_Content_Rating = new javax.swing.JComboBox<>();
        JCheckBox_ShowingTimes_0130PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_0430PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_0730PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_1030PM = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        JBTN_RefreshTable = new javax.swing.JButton();
        JCheckBox_HallNo_1 = new javax.swing.JCheckBox();
        JCheckBox_HallNo_2 = new javax.swing.JCheckBox();
        JCheckBox_HallNo_3 = new javax.swing.JCheckBox();
        JCheckBox_HallNo_4 = new javax.swing.JCheckBox();
        JCheckBox_HallNo_5 = new javax.swing.JCheckBox();
        JCheckBox_HallNo_6 = new javax.swing.JCheckBox();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1392, 768));

        jPanel1.setMinimumSize(new java.awt.Dimension(1392, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(1392, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JBTN_Add_Movie.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Add_Movie.setText("ADD MOVIE");
        JBTN_Add_Movie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_Add_MovieActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Add_Movie, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 130, 40));
        jPanel1.add(JTF_Movie_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 260, 30));
        jPanel1.add(JTF_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 100, 30));
        jPanel1.add(JTF_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 80, 30));

        JTextArea_Description.setColumns(20);
        JTextArea_Description.setRows(5);
        jScrollPane1.setViewportView(JTextArea_Description);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, 250, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Movie Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Movie Year");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rating");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 50, 30));
        jPanel1.add(JTF_Genre, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 100, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genre");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 50, 20));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Cinema Hall No."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 380, 400));

        JTextArea_Cast.setColumns(20);
        JTextArea_Cast.setRows(5);
        jScrollPane3.setViewportView(JTextArea_Cast);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cast");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));
        jPanel1.add(JTF_IMDb_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 230, 30));
        jPanel1.add(JTF_Rotten_Tomatoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 100, 230, 30));
        jPanel1.add(JTF_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, 230, 30));
        jPanel1.add(JTF_Composer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 180, 230, 30));

        JComboBox_Country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "United States of America", "England", "Australia", "Sri Lanka", "South Korea" }));
        JComboBox_Country.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox_CountryActionPerformed(evt);
            }
        });
        jPanel1.add(JComboBox_Country, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 260, 170, -1));

        JComboBox_Quality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "Digital 2D", "Digital 3D", "DOLBY ATMOS" }));
        jPanel1.add(JComboBox_Quality, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 290, 170, -1));
        jPanel1.add(JTF_Duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 320, 120, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rotten Tomatoes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("IMDb Rating");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Director");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Music Composer");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Content Rating");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Country ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 260, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quality ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 290, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Duration");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 320, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hall No");
        jLabel15.setToolTipText("");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 360, -1, -1));

        JCheckBox_ShowingTimes_1030AM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_1030AM.setText("10:30 AM");
        jPanel1.add(JCheckBox_ShowingTimes_1030AM, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Showing Times");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, -1, -1));

        JBTN_Update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Update.setText("UPDATE");
        JBTN_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_UpdateActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 130, 40));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("DELETE");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 130, 40));

        JComboBox_Content_Rating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "G", "PG", "R" }));
        jPanel1.add(JComboBox_Content_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 230, 120, -1));

        JCheckBox_ShowingTimes_0130PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0130PM.setText("01:30 PM");
        JCheckBox_ShowingTimes_0130PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox_ShowingTimes_0130PMActionPerformed(evt);
            }
        });
        jPanel1.add(JCheckBox_ShowingTimes_0130PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 470, -1, -1));

        JCheckBox_ShowingTimes_0430PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0430PM.setText("04:30 PM");
        JCheckBox_ShowingTimes_0430PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox_ShowingTimes_0430PMActionPerformed(evt);
            }
        });
        jPanel1.add(JCheckBox_ShowingTimes_0430PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 470, -1, -1));

        JCheckBox_ShowingTimes_0730PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0730PM.setText("07:30 PM");
        jPanel1.add(JCheckBox_ShowingTimes_0730PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 470, -1, -1));

        JCheckBox_ShowingTimes_1030PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_1030PM.setText("10:30 PM");
        jPanel1.add(JCheckBox_ShowingTimes_1030PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 470, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("CLEAR FIELDS");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 590, -1, -1));

        JBTN_RefreshTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_RefreshTable.setText("REFRESH TABLE");
        jPanel1.add(JBTN_RefreshTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, 140, 30));

        JCheckBox_HallNo_1.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_1.setText("1");
        jPanel1.add(JCheckBox_HallNo_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 360, -1, -1));

        JCheckBox_HallNo_2.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_2.setText("2");
        jPanel1.add(JCheckBox_HallNo_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 360, -1, -1));

        JCheckBox_HallNo_3.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_3.setText("3");
        jPanel1.add(JCheckBox_HallNo_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 360, -1, -1));

        JCheckBox_HallNo_4.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_4.setText("4");
        jPanel1.add(JCheckBox_HallNo_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 360, -1, -1));

        JCheckBox_HallNo_5.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_5.setText("5");
        jPanel1.add(JCheckBox_HallNo_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 360, -1, -1));

        JCheckBox_HallNo_6.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_6.setText("6");
        jPanel1.add(JCheckBox_HallNo_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 360, -1, -1));

        BG.setForeground(new java.awt.Color(255, 255, 255));
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG.jpg"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_Add_MovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_Add_MovieActionPerformed
    try {
        // 1. Movie Name
        String movieName = JTF_Movie_Name.getText().trim();
        if (movieName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Movie Name cannot be empty");
            return;
        }

        // 2. Movie Year
        String yearStr = JTF_Year.getText().trim();
        int year;
        try {
            year = Integer.parseInt(yearStr);
            if (year >= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)) {
                JOptionPane.showMessageDialog(null, "Year should be less than the current year");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid year");
            return;
        }

        // 3. Genre
        String genre = JTF_Genre.getText().trim();
        if (genre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Genre cannot be empty");
            return;
        }

        // 4. Movie Description
        String description = JTextArea_Description.getText().trim();
        if (description.length() <= 4) {
            JOptionPane.showMessageDialog(null, "Description should be more than 4 characters");
            return;
        }

        // 5. Movie Rating
        String ratingStr = JTF_Rating.getText().trim();
        float rating;
        try {
            rating = Float.parseFloat(ratingStr);
            if (rating < 0 || rating > 10) {
                JOptionPane.showMessageDialog(null, "Rating should be between 0 and 10");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid rating");
            return;
        }

        // 6. Movie Cast
        String cast = JTextArea_Cast.getText().trim();
        if (!cast.matches("^(\\w+\\s*,\\s*)*\\w+$")) {
            JOptionPane.showMessageDialog(null, "Wrong Cast name format. It should be 'Name1, Name2, Name3'");
            return;
        }

        // 7. IMDb Rating
        String imdbRatingStr = JTF_IMDb_Rating.getText().trim();
        float imdbRating;
        try {
            imdbRating = Float.parseFloat(imdbRatingStr);
            if (imdbRating < 0 || imdbRating > 10) {
                JOptionPane.showMessageDialog(null, "IMDb Rating should be between 0 and 10");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid IMDb rating");
            return;
        }

        // 8. Rotten Tomato rating
        String rottenTomatoStr = JTF_Rotten_Tomatoes.getText().trim();
        int rottenTomatoRating;
        try {
            rottenTomatoRating = Integer.parseInt(rottenTomatoStr);
            if (rottenTomatoRating < 0 || rottenTomatoRating > 100) {
                JOptionPane.showMessageDialog(null, "Rotten Tomato rating should be between 0% and 100%");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid Rotten Tomato rating");
            return;
        }

        // 9. Content Rating
        String contentRating = (String) JComboBox_Content_Rating.getSelectedItem();
        if ("NOT SELECTED".equals(contentRating) || !(contentRating.equals("G") || contentRating.equals("PG") || contentRating.equals("R"))) {
            JOptionPane.showMessageDialog(null, "Select Content rating");
            return;
        }

        // 10. Country
        String country = (String) JComboBox_Country.getSelectedItem();
        List<String> validCountries = Arrays.asList("United States of America", "England", "Australia", "Sri Lanka", "South Korea");
        if (!validCountries.contains(country)) {
            JOptionPane.showMessageDialog(null, "Invalid country selected");
            return;
        }

        // 11. Movie Quality
        String quality = (String) JComboBox_Quality.getSelectedItem();
        if ("NOT SELECTED".equals(quality) || !(quality.equals("Digital 2D") || quality.equals("Digital 3D") || quality.equals("DOLBY ATMOS"))) {
            JOptionPane.showMessageDialog(null, "Select Movie Quality");
            return;
        }

        // 12. Duration
        String duration = JTF_Duration.getText().trim();
        if (!duration.matches("^\\d{2}:\\d{2}$")) {
            JOptionPane.showMessageDialog(null, "The duration format is incorrect and should be in the format HH:MM");
            return;
        }

        // 13. Hall No
        List<String> halls = new ArrayList<>();
        if (JCheckBox_HallNo_1.isSelected()) halls.add("1");
        if (JCheckBox_HallNo_2.isSelected()) halls.add("2");
        // ... [Continue for other hall checkboxes]
        String hallNos = String.join(", ", halls);

        // 14. Showing Times
        List<String> times = new ArrayList<>();
        if (JCheckBox_ShowingTimes_1030AM.isSelected()) times.add("10:30 AM");
        if (JCheckBox_ShowingTimes_0130PM.isSelected()) times.add("01:30 PM");
        // ... [Continue for other time checkboxes]
        String showingTimes = String.join(", ", times);

        // If all validations pass, save the movie to the database.
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        PreparedStatement ps = con.prepareStatement("SELECT * FROM all_movies_db WHERE movie_name_db = ?");
        ps.setString(1, movieName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Movie with this name already exists!");
        } else {
            // Insert movie details into the database
            ps = con.prepareStatement(
                "INSERT INTO all_movies_db (movie_name_db, mov_year_db, mov_genre_db, mov_descrip_db, mov_rating_db, Cast_db, IMDb_Rating_db, Rotten_Tomatos_db, Director_db, Music_composed_by_db, Content_Rating_db, Country_db, Quality_db, Duration_db, Hall_No_db, Showing_Times_db) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );
            ps.setString(1, movieName);
            ps.setInt(2, year);
            ps.setString(3, genre);
            ps.setString(4, description);
            ps.setFloat(5, rating);
            ps.setString(6, cast);
            ps.setFloat(7, imdbRating);
            ps.setInt(8, rottenTomatoRating);
            // ... [Set other parameters]

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Movie Added Successfully");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Failed to save the movie. Error: " + e.getMessage());
    }
    }//GEN-LAST:event_JBTN_Add_MovieActionPerformed

    private void JComboBox_CountryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JComboBox_CountryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JComboBox_CountryActionPerformed

    private void JCheckBox_ShowingTimes_0130PMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCheckBox_ShowingTimes_0130PMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCheckBox_ShowingTimes_0130PMActionPerformed

    private void JCheckBox_ShowingTimes_0430PMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCheckBox_ShowingTimes_0430PMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCheckBox_ShowingTimes_0430PMActionPerformed

    private void JBTN_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_UpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBTN_UpdateActionPerformed

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
            java.util.logging.Logger.getLogger(Add_Movie_dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_Movie_dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_Movie_dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Movie_dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_Movie_dash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton JBTN_Add_Movie;
    private javax.swing.JButton JBTN_RefreshTable;
    private javax.swing.JButton JBTN_Update;
    private javax.swing.JCheckBox JCheckBox_HallNo_1;
    private javax.swing.JCheckBox JCheckBox_HallNo_2;
    private javax.swing.JCheckBox JCheckBox_HallNo_3;
    private javax.swing.JCheckBox JCheckBox_HallNo_4;
    private javax.swing.JCheckBox JCheckBox_HallNo_5;
    private javax.swing.JCheckBox JCheckBox_HallNo_6;
    private javax.swing.JCheckBox JCheckBox_ShowingTimes_0130PM;
    private javax.swing.JCheckBox JCheckBox_ShowingTimes_0430PM;
    private javax.swing.JCheckBox JCheckBox_ShowingTimes_0730PM;
    private javax.swing.JCheckBox JCheckBox_ShowingTimes_1030AM;
    private javax.swing.JCheckBox JCheckBox_ShowingTimes_1030PM;
    private javax.swing.JComboBox<String> JComboBox_Content_Rating;
    private javax.swing.JComboBox<String> JComboBox_Country;
    private javax.swing.JComboBox<String> JComboBox_Quality;
    private javax.swing.JTextField JTF_Composer;
    private javax.swing.JTextField JTF_Director;
    private javax.swing.JTextField JTF_Duration;
    private javax.swing.JTextField JTF_Genre;
    private javax.swing.JTextField JTF_IMDb_Rating;
    private javax.swing.JTextField JTF_Movie_Name;
    private javax.swing.JTextField JTF_Rating;
    private javax.swing.JTextField JTF_Rotten_Tomatoes;
    private javax.swing.JTextField JTF_Year;
    private javax.swing.JTextArea JTextArea_Cast;
    private javax.swing.JTextArea JTextArea_Description;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
