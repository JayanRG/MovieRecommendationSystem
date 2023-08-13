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
    
    JTable1.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        JTable1MouseClicked(evt);
    }
});


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
        JBTN_Delete = new javax.swing.JButton();
        JComboBox_Content_Rating = new javax.swing.JComboBox<>();
        JCheckBox_ShowingTimes_0130PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_0430PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_0730PM = new javax.swing.JCheckBox();
        JCheckBox_ShowingTimes_1030PM = new javax.swing.JCheckBox();
        JBTN_CLR_Fields = new javax.swing.JButton();
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
        jPanel1.add(JBTN_Add_Movie, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, 130, 40));
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

        JTable1.setBackground(new java.awt.Color(0, 0, 0));
        JTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        JTable1.setForeground(new java.awt.Color(255, 255, 255));
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
        JTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable1);
        if (JTable1.getColumnModel().getColumnCount() > 0) {
            JTable1.getColumnModel().getColumn(0).setResizable(false);
            JTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 380, 630));

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
        jPanel1.add(JBTN_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, 130, 40));

        JBTN_Delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Delete.setText("DELETE");
        JBTN_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_DeleteActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 580, 130, 40));

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

        JBTN_CLR_Fields.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_CLR_Fields.setText("CLEAR FIELDS");
        JBTN_CLR_Fields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_CLR_FieldsActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_CLR_Fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 510, -1, -1));

        JBTN_RefreshTable.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_RefreshTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_RefreshTable.setForeground(new java.awt.Color(255, 255, 255));
        JBTN_RefreshTable.setText("REFRESH TABLE");
        JBTN_RefreshTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_RefreshTableActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_RefreshTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 380, 30));

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

    private boolean validateMovieFields() {

    // Validation for Movie Name (Should be unique - but this check is only for adding movies)
    if (JTF_Movie_Name.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Movie name cannot be empty.");
        return false;
    }

    // Validation for Movie Year (Should be current year + 3 or below)
    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int enteredYear;
    try {
        enteredYear = Integer.parseInt(JTF_Year.getText());
        if (enteredYear > currentYear + 3 || enteredYear < currentYear) {
            JOptionPane.showMessageDialog(null, "Invalid movie year. It should be between " + currentYear + " and " + (currentYear + 3) + ".");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid movie year format.");
        return false;
    }

    // Validation for Movie Genre (Cannot be empty)
    if (JTF_Genre.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Genre cannot be empty.");
        return false;
    }

    // Validation for Movie Description (Should be more than 4 characters)
    if (JTextArea_Description.getText().trim().length() <= 4) {
        JOptionPane.showMessageDialog(null, "Description should be more than 4 characters.");
        return false;
    }

    // Validation for Movie Rating (Should be between 0 and 10)
    try {
        float rating = Float.parseFloat(JTF_Rating.getText());
        if (rating < 0 || rating > 10) {
            JOptionPane.showMessageDialog(null, "Rating should be between 0 and 10.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid movie rating format.");
        return false;
    }

    // Validation for Movie Cast (Should be in the format: Name1, Name2, Name3)
    String[] castNames = JTextArea_Cast.getText().split(",");
    if (castNames.length < 1) {
        JOptionPane.showMessageDialog(null, "Wrong cast name format. Please use the format: Name1, Name2, Name3");
        return false;
    }

    // Validation for IMDb Rating (Should be between 0 and 10)
    try {
        float imdbRating = Float.parseFloat(JTF_IMDb_Rating.getText());
        if (imdbRating < 0 || imdbRating > 10) {
            JOptionPane.showMessageDialog(null, "IMDb Rating should be between 0 and 10.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid IMDb rating format.");
        return false;
    }

    // Validation for Rotten Tomatoes Rating (Should be between 0% and 100%)
    try {
        int rottenRating = Integer.parseInt(JTF_Rotten_Tomatoes.getText().replace("%", ""));
        if (rottenRating < 0 || rottenRating > 100) {
            JOptionPane.showMessageDialog(null, "Rotten Tomatoes rating should be between 0% and 100%.");
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid Rotten Tomatoes rating format.");
        return false;
    }

    // Validation for Director Name (Cannot be empty)
    if (JTF_Director.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Director name cannot be empty.");
        return false;
    }

    // Validation for Composer Name (Cannot be empty)
    if (JTF_Composer.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Composer name cannot be empty.");
        return false;
    }

    // Validation for Content Rating (Should be G, PG or R. "NOT SELECTED" is not applicable)
    String contentRating = JComboBox_Content_Rating.getSelectedItem().toString();
    if ("NOT SELECTED".equals(contentRating) || !(contentRating.equals("G") || contentRating.equals("PG") || contentRating.equals("R"))) {
        JOptionPane.showMessageDialog(null, "Select Content Rating (G, PG or R only).");
        return false;
    }

    // Validation for Country (Should be selected from the list, "NOT SELECTED" is not applicable)
    String country = JComboBox_Country.getSelectedItem().toString();
    if ("NOT SELECTED".equals(country)) {
        JOptionPane.showMessageDialog(null, "Select a Country.");
        return false;
    }

    // Validation for Movie Quality (Should be Digital 2D, Digital 3D or DOLBY ATMOS. "NOT SELECTED" is not applicable)
    String quality = JComboBox_Quality.getSelectedItem().toString();
    if ("NOT SELECTED".equals(quality)) {
        JOptionPane.showMessageDialog(null, "Select Movie Quality.");
        return false;
    }

    // Validation for Duration (Should be in HH:MM format)
    Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    Matcher matcher = pattern.matcher(JTF_Duration.getText());
    if (!matcher.matches()) {
        JOptionPane.showMessageDialog(null, "Invalid duration format. It should be in HH:MM format.");
        return false;
    }

    // Validation for Hall No (At least one should be selected)
    boolean isHallSelected = JCheckBox_HallNo_1.isSelected() || JCheckBox_HallNo_2.isSelected() || JCheckBox_HallNo_3.isSelected() || 
                             JCheckBox_HallNo_4.isSelected() || JCheckBox_HallNo_5.isSelected() || JCheckBox_HallNo_6.isSelected();
    if (!isHallSelected) {
        JOptionPane.showMessageDialog(null, "Select at least one Hall.");
        return false;
    }

    // Validation for Showing Times (At least one should be selected)
    boolean isTimeSelected = JCheckBox_ShowingTimes_1030AM.isSelected() || JCheckBox_ShowingTimes_0130PM.isSelected() || 
                             JCheckBox_ShowingTimes_0430PM.isSelected() || JCheckBox_ShowingTimes_0730PM.isSelected() || 
                             JCheckBox_ShowingTimes_1030PM.isSelected();
    if (!isTimeSelected) {
        JOptionPane.showMessageDialog(null, "Select at least one Showing Time.");
        return false;
    }

    return true;
}

    
    
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
            clearFields();  // Clear the fields after successfully adding the movie
            loadMoviesIntoTable();  // Refresh the JTable1 with updated data

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

    private void clearFields() {
    JTF_Movie_Name.setText("");
    JTF_Year.setText("");
    JTF_Genre.setText("");
    JTextArea_Description.setText("");
    JTF_Rating.setText("");
    JTextArea_Cast.setText("");
    JTF_IMDb_Rating.setText("");
    JTF_Rotten_Tomatoes.setText("");
    JTF_Director.setText("");
    JTF_Composer.setText("");
    JComboBox_Content_Rating.setSelectedIndex(0);  
    JComboBox_Country.setSelectedIndex(0);        
    JComboBox_Quality.setSelectedIndex(0);       
    JTF_Duration.setText("");
    JCheckBox_HallNo_1.setSelected(false);
    JCheckBox_HallNo_2.setSelected(false);
    JCheckBox_HallNo_3.setSelected(false);
    JCheckBox_HallNo_4.setSelected(false);
    JCheckBox_HallNo_5.setSelected(false);
    JCheckBox_HallNo_6.setSelected(false);
    JCheckBox_ShowingTimes_1030AM.setSelected(false);
    JCheckBox_ShowingTimes_0130PM.setSelected(false);
    JCheckBox_ShowingTimes_0430PM.setSelected(false);
    JCheckBox_ShowingTimes_0730PM.setSelected(false);
    JCheckBox_ShowingTimes_1030PM.setSelected(false);
}

    
    
    //populate movie details from table to fields
    private void populateFieldsWithMovieDetails(String movieName) {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        String query = "SELECT * FROM all_movies_db WHERE movie_name_db = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, movieName);
        rs = pst.executeQuery();
        
        if(rs.next()) {
            JTF_Movie_Name.setText(rs.getString("movie_name_db"));
            JTF_Year.setText(rs.getString("mov_year_db"));
            JTF_Genre.setText(rs.getString("mov_genre_db"));
            JTextArea_Description.setText(rs.getString("mov_descrip_db"));
            JTF_Rating.setText(rs.getString("mov_rating_db"));
            JTextArea_Cast.setText(rs.getString("Cast_db"));
            JTF_IMDb_Rating.setText(rs.getString("IMDb_Rating_db"));
            JTF_Rotten_Tomatoes.setText(rs.getString("Rotten_Tomatos_db") + "%");
            JTF_Director.setText(rs.getString("Director_db"));
            JTF_Composer.setText(rs.getString("Music_composed_by_db"));
            JComboBox_Content_Rating.setSelectedItem(rs.getString("Content_Rating_db"));
            JComboBox_Country.setSelectedItem(rs.getString("Country_db"));
            JComboBox_Quality.setSelectedItem(rs.getString("Quality_db"));
            JTF_Duration.setText(rs.getString("Duration_db"));

            // Clear all checkboxes first
            JCheckBox_HallNo_1.setSelected(false);
            JCheckBox_HallNo_2.setSelected(false);
            // ... repeat for all checkboxes ...

            String[] halls = rs.getString("Hall_No_db").split(",");
            for(String hall : halls) {
                switch(hall.trim()) {
                    case "1": JCheckBox_HallNo_1.setSelected(true); break;
                    case "2": JCheckBox_HallNo_2.setSelected(true); break;
                    case "3": JCheckBox_HallNo_3.setSelected(true); break;
                    case "4": JCheckBox_HallNo_4.setSelected(true); break;
                    case "5": JCheckBox_HallNo_5.setSelected(true); break;
                    case "6": JCheckBox_HallNo_6.setSelected(true); break;
                }
            }

            // Do the same for showing times
            // Clear all checkboxes first
            JCheckBox_ShowingTimes_1030AM.setSelected(false);
            JCheckBox_ShowingTimes_0130PM.setSelected(false);
            // ... repeat for all checkboxes ...

            String[] times = rs.getString("Showing_Times_db").split(",");
            for(String time : times) {
                switch(time.trim()) {
                    case "10:30 AM": JCheckBox_ShowingTimes_1030AM.setSelected(true); break;
                    case "01:30 PM": JCheckBox_ShowingTimes_0130PM.setSelected(true); break;
                    case "04:30 PM": JCheckBox_ShowingTimes_0430PM.setSelected(true); break;
                    case "07:30 PM": JCheckBox_ShowingTimes_0730PM.setSelected(true); break;
                    case "10:30 PM": JCheckBox_ShowingTimes_1030PM.setSelected(true); break;
                }
            }
        }
        
    } catch(SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if(rs != null) rs.close();
            if(pst != null) pst.close();
            if(conn != null) conn.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}

    
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
        // Call the validateMovieFields method
    if (!validateMovieFields()) {
        return; // Exit if validation fails
    }

    Connection conn = null;
    PreparedStatement pst = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        
        String query = "UPDATE all_movies_db SET mov_year_db=?, mov_genre_db=?, mov_descrip_db=?, mov_rating_db=?, Cast_db=?, IMDb_Rating_db=?, Rotten_Tomatos_db=?, Director_db=?, Music_composed_by_db=?, Content_Rating_db=?, Country_db=?, Quality_db=?, Duration_db=?, Hall_No_db=?, Showing_Times_db=? WHERE movie_name_db=?";
        pst = conn.prepareStatement(query);

        pst.setInt(1, Integer.parseInt(JTF_Year.getText()));
        pst.setString(2, JTF_Genre.getText());
        pst.setString(3, JTextArea_Description.getText());
        pst.setFloat(4, Float.parseFloat(JTF_Rating.getText()));
        pst.setString(5, JTextArea_Cast.getText());
        pst.setFloat(6, Float.parseFloat(JTF_IMDb_Rating.getText()));
        pst.setInt(7, Integer.parseInt(JTF_Rotten_Tomatoes.getText().replace("%", "")));
        pst.setString(8, JTF_Director.getText());
        pst.setString(9, JTF_Composer.getText());
        pst.setString(10, JComboBox_Content_Rating.getSelectedItem().toString());
        pst.setString(11, JComboBox_Country.getSelectedItem().toString());
        pst.setString(12, JComboBox_Quality.getSelectedItem().toString());
        pst.setString(13, JTF_Duration.getText());

        
        // For Hall Numbers
        StringBuilder hallNumbers = new StringBuilder();
        if(JCheckBox_HallNo_1.isSelected()) hallNumbers.append("1,");
        if(JCheckBox_HallNo_2.isSelected()) hallNumbers.append("2,");
        if(JCheckBox_HallNo_3.isSelected()) hallNumbers.append("3,");
        if(JCheckBox_HallNo_4.isSelected()) hallNumbers.append("4,");
        if(JCheckBox_HallNo_5.isSelected()) hallNumbers.append("5,");
        if(JCheckBox_HallNo_6.isSelected()) hallNumbers.append("6,");
        if(hallNumbers.length() > 0) hallNumbers.setLength(hallNumbers.length() - 1); // Remove last comma
        pst.setString(14, hallNumbers.toString());

        // For Showing Times
        StringBuilder showingTimes = new StringBuilder();
        if (JCheckBox_ShowingTimes_1030AM.isSelected()) showingTimes.append("10:30 AM,");
        if (JCheckBox_ShowingTimes_0130PM.isSelected()) showingTimes.append("01:30 PM,");
        if (JCheckBox_ShowingTimes_0430PM.isSelected()) showingTimes.append("04:30 PM,");
        if (JCheckBox_ShowingTimes_0730PM.isSelected()) showingTimes.append("07:30 PM,");
        if (JCheckBox_ShowingTimes_1030PM.isSelected()) showingTimes.append("10:30 PM,");
        if (showingTimes.length() > 0) showingTimes.setLength(showingTimes.length() - 1); // Remove last comma
        pst.setString(15, showingTimes.toString());


        pst.setString(16, JTF_Movie_Name.getText());


        int result = pst.executeUpdate();
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Movie Updated Successfully!");
            clearFields();
            loadMoviesIntoTable();
        } else {
            JOptionPane.showMessageDialog(null, "Error updating movie.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    }//GEN-LAST:event_JBTN_UpdateActionPerformed

    private void JBTN_CLR_FieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_CLR_FieldsActionPerformed
        clearFields();  // Call the clearFields() method to reset the components to their default states
    }//GEN-LAST:event_JBTN_CLR_FieldsActionPerformed

    private void JBTN_RefreshTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_RefreshTableActionPerformed
        loadMoviesIntoTable();  // Refresh the JTable1 with updated data
    }//GEN-LAST:event_JBTN_RefreshTableActionPerformed

    private void JTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable1MouseClicked
        int selectedRow = JTable1.getSelectedRow();
        String movieName = JTable1.getValueAt(selectedRow, 0).toString(); // Assuming movie name is in column 0
        populateFieldsWithMovieDetails(movieName);
    }//GEN-LAST:event_JTable1MouseClicked

    private void JBTN_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_DeleteActionPerformed
                                          
    // Get the movie name from the field
    String movieName = JTF_Movie_Name.getText();

    // Check if movieName is empty
    if (movieName.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No movie selected for deletion.");
        return;
    }

    // Confirm delete action from the user
    int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this movie?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirmDelete == JOptionPane.NO_OPTION) {
        return;
    }

    Connection conn = null;
    PreparedStatement pst = null;

    try {
        // Connect to the database
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");

        // Create the DELETE SQL statement
        String query = "DELETE FROM all_movies_db WHERE movie_name_db = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, movieName);

        // Execute the DELETE statement
        int result = pst.executeUpdate();
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Movie Deleted Successfully!");
            clearFields();  // Clear the fields
            loadMoviesIntoTable();  // Refresh the JTable1 with updated data
        } else {
            JOptionPane.showMessageDialog(null, "Error deleting movie.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    }//GEN-LAST:event_JBTN_DeleteActionPerformed

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
    private javax.swing.JButton JBTN_CLR_Fields;
    private javax.swing.JButton JBTN_Delete;
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