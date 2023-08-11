package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.*;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.table.DefaultTableModel;





public class Add_Movie_dash extends javax.swing.JFrame {

    public Add_Movie_dash() {
        initComponents();
        
    loadMoviesIntoTable();

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
        JTable1 = new javax.swing.JTable();
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
        jLabel17 = new javax.swing.JLabel();
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

        JTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "movie_name_db", "Hall_No_db"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(JTable1);
        if (JTable1.getColumnModel().getColumnCount() > 0) {
            JTable1.getColumnModel().getColumn(0).setResizable(false);
            JTable1.getColumnModel().getColumn(1).setResizable(false);
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
        jPanel1.add(JTF_Rotten_Tomatoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 100, 70, 30));
        jPanel1.add(JTF_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, 230, 30));
        jPanel1.add(JTF_Composer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 180, 230, 30));

        JComboBox_Country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "United States of America", "England", "Australia", "Sri Lanka", "South Korea" }));
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

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("%");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 100, 30, 30));

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
    // Database connection setup
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        
        // Check if movie name exists in the database
        String query = "SELECT * FROM all_movies_db WHERE movie_name_db = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, JTF_Movie_Name.getText());
        rs = pst.executeQuery();
        
        String sql = "INSERT INTO `all_movies_db` (`movie_name_db`, `mov_year_db`, `mov_genre_db`, `mov_descrip_db`, `mov_rating_db`, `Cast_db`, `IMDb_Rating_db`, `Rotten_Tomatos_db`, `Director_db`, `Music_composed_by_db`, `Content_Rating_db`, `Country_db`, `Quality_db`, `Duration_db`, `Hall_No_db`, `Showing_Times_db`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        
        if(rs.next()) {
            JOptionPane.showMessageDialog(null, "Movie Name already Exists");
            return;
        }

        // Validation for Movie Name (Should be unique - we have already checked this above)
        if (JTF_Movie_Name.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Movie name cannot be empty.");
        return;
}

        // Validation for Movie Year (Should be current year + 3 or below)
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int enteredYear;
try {
    enteredYear = Integer.parseInt(JTF_Year.getText());
    if (enteredYear > currentYear + 3 || enteredYear < currentYear) {
        JOptionPane.showMessageDialog(null, "Invalid movie year. It should be between " + currentYear + " and " + (currentYear + 3) + ".");
        return;
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid movie year format.");
    return;
}

// Validation for Movie Genre (Cannot be empty)
if (JTF_Genre.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Genre cannot be empty.");
    return;
}

// Validation for Movie Description (Should be more than 4 characters)
if (JTextArea_Description.getText().trim().length() <= 4) {
    JOptionPane.showMessageDialog(null, "Description should be more than 4 characters.");
    return;
}

// Validation for Movie Rating (Should be between 0 and 10)
try {
    float rating = Float.parseFloat(JTF_Rating.getText());
    if (rating < 0 || rating > 10) {
        JOptionPane.showMessageDialog(null, "Rating should be between 0 and 10.");
        return;
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid movie rating format.");
    return;
}

// Validation for Movie Cast (Should be in the format: Name1, Name2, Name3)
String[] castNames = JTextArea_Cast.getText().split(",");
if (castNames.length < 1) {
    JOptionPane.showMessageDialog(null, "Wrong cast name format. Please use the format: Name1, Name2, Name3");
    return;
}

// Validation for IMDb Rating (Should be between 0 and 10)
try {
    float imdbRating = Float.parseFloat(JTF_IMDb_Rating.getText());
    if (imdbRating < 0 || imdbRating > 10) {
        JOptionPane.showMessageDialog(null, "IMDb Rating should be between 0 and 10.");
        return;
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid IMDb rating format.");
    return;
}

// Validation for Rotten Tomatoes Rating (Should be between 0% and 100%)
try {
    int rottenRating = Integer.parseInt(JTF_Rotten_Tomatoes.getText().replace("%", ""));
    if (rottenRating < 0 || rottenRating > 100) {
        JOptionPane.showMessageDialog(null, "Rotten Tomatoes rating should be between 0% and 100%.");
        return;
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Invalid Rotten Tomatoes rating format.");
    return;
}

// Validation for Director Name (Cannot be empty)
if (JTF_Director.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Director name cannot be empty.");
    return;
}

// Validation for Composer Name (Cannot be empty)
if (JTF_Composer.getText().trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Composer name cannot be empty.");
    return;
}

// Validation for Content Rating (Should be G, PG or R. "NOT SELECTED" is not applicable)
String contentRating = JComboBox_Content_Rating.getSelectedItem().toString();
if ("NOT SELECTED".equals(contentRating) || !(contentRating.equals("G") || contentRating.equals("PG") || contentRating.equals("R"))) {
    JOptionPane.showMessageDialog(null, "Select Content Rating (G, PG or R only).");
    return;
}

// Validation for Country (Should be selected from the list, "NOT SELECTED" is not applicable)
String country = JComboBox_Country.getSelectedItem().toString();
if ("NOT SELECTED".equals(country)) {
    JOptionPane.showMessageDialog(null, "Select a Country.");
    return;
}

// Validation for Movie Quality (Should be Digital 2D, Digital 3D or DOLBY ATMOS. "NOT SELECTED" is not applicable)
String quality = JComboBox_Quality.getSelectedItem().toString();
if ("NOT SELECTED".equals(quality)) {
    JOptionPane.showMessageDialog(null, "Select Movie Quality.");
    return;
}

// Validation for Duration (Should be in HH:MM format)
Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
Matcher matcher = pattern.matcher(JTF_Duration.getText());
if (!matcher.matches()) {
    JOptionPane.showMessageDialog(null, "Invalid duration format. It should be in HH:MM format.");
    return;
}

// Validation for Hall No (At least one should be selected)
boolean isHallSelected = JCheckBox_HallNo_1.isSelected() || JCheckBox_HallNo_2.isSelected() || JCheckBox_HallNo_3.isSelected() || 
                         JCheckBox_HallNo_4.isSelected() || JCheckBox_HallNo_5.isSelected() || JCheckBox_HallNo_6.isSelected();
if (!isHallSelected) {
    JOptionPane.showMessageDialog(null, "Select at least one Hall.");
    return;
}

// Validation for Showing Times (At least one should be selected)
boolean isTimeSelected = JCheckBox_ShowingTimes_1030AM.isSelected() || JCheckBox_ShowingTimes_0130PM.isSelected() || 
                         JCheckBox_ShowingTimes_0430PM.isSelected() || JCheckBox_ShowingTimes_0730PM.isSelected() || 
                         JCheckBox_ShowingTimes_1030PM.isSelected();
if (!isTimeSelected) {
    JOptionPane.showMessageDialog(null, "Select at least one Showing Time.");
    return;
}


        // Insert data if validation passes
        query = "INSERT INTO all_movies_db (movie_name_db, mov_year_db, mov_genre_db, mov_descrip_db, mov_rating_db, Cast_db, IMDb_Rating_db, Rotten_Tomatos_db, Director_db, Music_composed_by_db, Content_Rating_db, Country_db, Quality_db, Duration_db, Hall_No_db, Showing_Times_db) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setString(1, JTF_Movie_Name.getText());
        pst.setInt(2, Integer.parseInt(JTF_Year.getText()));
        pst.setString(3, JTF_Genre.getText());
        pst.setString(4, JTextArea_Description.getText());
        pst.setFloat(5, Float.parseFloat(JTF_Rating.getText()));
        pst.setString(6, JTextArea_Cast.getText());
        pst.setFloat(7, Float.parseFloat(JTF_IMDb_Rating.getText()));
        pst.setInt(8, Integer.parseInt(JTF_Rotten_Tomatoes.getText().replace("%", "")));
        pst.setString(9, JTF_Director.getText());
        pst.setString(10, JTF_Composer.getText());
        pst.setString(11, JComboBox_Content_Rating.getSelectedItem().toString());
        pst.setString(12, JComboBox_Country.getSelectedItem().toString());
        pst.setString(13, JComboBox_Quality.getSelectedItem().toString());
        pst.setString(14, JTF_Duration.getText());
        
        StringBuilder hallNumbers = new StringBuilder();
        if(JCheckBox_HallNo_1.isSelected()) hallNumbers.append("1,");
        if(JCheckBox_HallNo_2.isSelected()) hallNumbers.append("2,");
        if(JCheckBox_HallNo_3.isSelected()) hallNumbers.append("3,");
        if(JCheckBox_HallNo_4.isSelected()) hallNumbers.append("4,");
        if(JCheckBox_HallNo_5.isSelected()) hallNumbers.append("5,");
        if(JCheckBox_HallNo_6.isSelected()) hallNumbers.append("6,");
        if(hallNumbers.length() > 0) hallNumbers.setLength(hallNumbers.length() - 1); // Remove last comma

        pst.setString(15, hallNumbers.toString());
        
        StringBuilder showingTimes = new StringBuilder();
        if (JCheckBox_ShowingTimes_1030AM.isSelected()) showingTimes.append("10:30 AM,");
        if (JCheckBox_ShowingTimes_0130PM.isSelected()) showingTimes.append("01:30 PM,");
        if (JCheckBox_ShowingTimes_0430PM.isSelected()) showingTimes.append("04:30 PM,");
        if (JCheckBox_ShowingTimes_0730PM.isSelected()) showingTimes.append("07:30 PM,");
        if (JCheckBox_ShowingTimes_1030PM.isSelected()) showingTimes.append("10:30 PM,");
        if (showingTimes.length() > 0) showingTimes.setLength(showingTimes.length() - 1); // Remove last comma

        pst.setString(16, showingTimes.toString());



        int result = pst.executeUpdate();
        if(result > 0) {
            JOptionPane.showMessageDialog(null, "Movie Added Successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error adding movie.");
        }

    } catch(SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if(pst != null) pst.close();
            if(conn != null) conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    
    }//GEN-LAST:event_JBTN_Add_MovieActionPerformed

    private void loadMoviesIntoTable() {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel model = null;

    try {
        // Step 1: Establish a connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");

        // Step 2: Retrieve the data
        String query = "SELECT movie_name_db, Hall_No_db FROM all_movies_db";
        pst = conn.prepareStatement(query);
        rs = pst.executeQuery();

        // Step 3: Populate the JTable
        model = (DefaultTableModel) JTable1.getModel();
        model.setRowCount(0); // Clear the current data

        while (rs.next()) {
            String movieName = rs.getString("movie_name_db");
            String hallNo = rs.getString("Hall_No_db");
            model.addRow(new Object[]{movieName, hallNo});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

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
    private javax.swing.JTable JTable1;
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
    private javax.swing.JLabel jLabel17;
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
    // End of variables declaration//GEN-END:variables
}