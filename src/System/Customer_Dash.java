package System;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Customer_Dash extends javax.swing.JFrame {
    
    //database parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movie_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    //end of database parameters

    public Customer_Dash() {
        initComponents();
        pack();  // Layout the components in the card(helps to rezie the image to the card)
        loadMovies();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLB_MOVIE4_Image = new javax.swing.JLabel();
        JLB_MOVIE3_Image = new javax.swing.JLabel();
        JLB_MOVIE2_Image = new javax.swing.JLabel();
        JLB_MOVIE1_Image = new javax.swing.JLabel();
        JLB_MOVIE1_Name = new javax.swing.JLabel();
        JLB_MOVIE2_Name = new javax.swing.JLabel();
        JLB_MOVIE3_Name = new javax.swing.JLabel();
        JLB_MOVIE4_Name = new javax.swing.JLabel();
        JLB_MOVIE1_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE2_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE3_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE4_YearGenre = new javax.swing.JLabel();
        JLB_MOVIE1_Rating = new javax.swing.JLabel();
        JLB_MOVIE2_Rating = new javax.swing.JLabel();
        JLB_MOVIE3_Rating = new javax.swing.JLabel();
        JLB_MOVIE4_Rating = new javax.swing.JLabel();
        jComboBox_MOVIE1_WatchStatus = new javax.swing.JComboBox<>();
        jComboBox_MOVIE2_WatchStatus = new javax.swing.JComboBox<>();
        jComboBox_MOVIE3_WatchStatus = new javax.swing.JComboBox<>();
        jComboBox_MOVIE4_WatchStatus = new javax.swing.JComboBox<>();
        Background_Image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMinimumSize(new java.awt.Dimension(1392, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLB_MOVIE4_Image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel1.add(JLB_MOVIE4_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 220, 270));

        JLB_MOVIE3_Image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel1.add(JLB_MOVIE3_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 220, 270));

        JLB_MOVIE2_Image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel1.add(JLB_MOVIE2_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 220, 270));

        JLB_MOVIE1_Image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel1.add(JLB_MOVIE1_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 220, 270));

        JLB_MOVIE1_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE1_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(JLB_MOVIE1_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 120, 30));

        JLB_MOVIE2_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE2_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(JLB_MOVIE2_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, 120, 30));

        JLB_MOVIE3_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE3_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(JLB_MOVIE3_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, 120, 30));

        JLB_MOVIE4_Name.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JLB_MOVIE4_Name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(JLB_MOVIE4_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 320, 120, 30));
        jPanel1.add(JLB_MOVIE1_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 200, 30));
        jPanel1.add(JLB_MOVIE2_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 210, 30));
        jPanel1.add(JLB_MOVIE3_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 350, 210, 30));
        jPanel1.add(JLB_MOVIE4_YearGenre, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 350, 210, 30));

        JLB_MOVIE1_Rating.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jPanel1.add(JLB_MOVIE1_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 200, 30));

        JLB_MOVIE2_Rating.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jPanel1.add(JLB_MOVIE2_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 200, 30));

        JLB_MOVIE3_Rating.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jPanel1.add(JLB_MOVIE3_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 380, 200, 30));

        JLB_MOVIE4_Rating.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jPanel1.add(JLB_MOVIE4_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, 200, 30));

        jComboBox_MOVIE1_WatchStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Watched", "Watched" }));
        jPanel1.add(jComboBox_MOVIE1_WatchStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        jComboBox_MOVIE2_WatchStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Watched", "Watched" }));
        jPanel1.add(jComboBox_MOVIE2_WatchStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 320, -1, -1));

        jComboBox_MOVIE3_WatchStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Watched", "Watched" }));
        jPanel1.add(jComboBox_MOVIE3_WatchStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, -1, -1));

        jComboBox_MOVIE4_WatchStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Watched", "Watched" }));
        jPanel1.add(jComboBox_MOVIE4_WatchStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 320, -1, -1));

        Background_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Blue BG.jpg"))); // NOI18N
        jPanel1.add(Background_Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, -80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBounds(0, 0, 1408, 776);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    
    public void loadMovies() {
    Connection conn = null;
    Statement stmt = null;
    try {
        // Register JDBC driver and open a connection
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        stmt = conn.createStatement();
        
        String sql = "SELECT * FROM all_movies_db LIMIT 4";  // Fetch the first 4 movies for now
        ResultSet rs = stmt.executeQuery(sql);
                
        // Extract data from result set and populate the movie cards
            while(rs.next()) {
            // Retrieve data from result set
            String movieName = rs.getString("movie_name_db");
            String imagePath = rs.getString("image_path");
            String year = rs.getString("mov_year_db");
            String genre = rs.getString("mov_genre_db");
            String rating = rs.getString("mov_rating_db");

            // populate your JLabels
            JLB_MOVIE1_Name.setText(movieName);


            // For the image, you will need to load the image from the path and set it to the JLabel
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(JLB_MOVIE1_Image.getWidth(), JLB_MOVIE1_Image.getHeight(), Image.SCALE_SMOOTH));
            JLB_MOVIE1_Image.setIcon(imageIcon);
        }
        rs.close();
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

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
            java.util.logging.Logger.getLogger(Customer_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Customer_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Customer_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Customer_Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_Dash().setVisible(true);
            }
            
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background_Image;
    private javax.swing.JLabel JLB_MOVIE1_Image;
    private javax.swing.JLabel JLB_MOVIE1_Name;
    private javax.swing.JLabel JLB_MOVIE1_Rating;
    private javax.swing.JLabel JLB_MOVIE1_YearGenre;
    private javax.swing.JLabel JLB_MOVIE2_Image;
    private javax.swing.JLabel JLB_MOVIE2_Name;
    private javax.swing.JLabel JLB_MOVIE2_Rating;
    private javax.swing.JLabel JLB_MOVIE2_YearGenre;
    private javax.swing.JLabel JLB_MOVIE3_Image;
    private javax.swing.JLabel JLB_MOVIE3_Name;
    private javax.swing.JLabel JLB_MOVIE3_Rating;
    private javax.swing.JLabel JLB_MOVIE3_YearGenre;
    private javax.swing.JLabel JLB_MOVIE4_Image;
    private javax.swing.JLabel JLB_MOVIE4_Name;
    private javax.swing.JLabel JLB_MOVIE4_Rating;
    private javax.swing.JLabel JLB_MOVIE4_YearGenre;
    private javax.swing.JComboBox<String> jComboBox_MOVIE1_WatchStatus;
    private javax.swing.JComboBox<String> jComboBox_MOVIE2_WatchStatus;
    private javax.swing.JComboBox<String> jComboBox_MOVIE3_WatchStatus;
    private javax.swing.JComboBox<String> jComboBox_MOVIE4_WatchStatus;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}