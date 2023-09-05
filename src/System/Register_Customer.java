package System;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.ArrayList;

public class Register_Customer extends javax.swing.JFrame {

//Creates new form Register_Customer

    public Register_Customer() {
     
        initComponents();
        //PASSWORD VISIBILITY
        JCheckBox_ViewPassword.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the checkbox is selected
        if (JCheckBox_ViewPassword.isSelected()) {
            // If selected, set the echo character to '\0' (NULL) to display the password
            JTF_Password.setEchoChar((char) 0);
        } else {
            // If not selected, set the echo character to '*' to hide the password
            JTF_Password.setEchoChar('*');
        }
    }
});
        
        
        // Get the size of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // Set the JFrame to full screen
        this.setSize(screenSize.width, screenSize.height);

        // Make the JFrame non-resizable
        this.setResizable(false);

        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        SystemStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JTF_First_Name = new javax.swing.JTextField();
        JTF_Last_Name = new javax.swing.JTextField();
        JTF_Email = new javax.swing.JTextField();
        JTF_Username = new javax.swing.JTextField();
        JBTN_Register = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JCheckBox_ViewPassword = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        jCheckBox_GENRE_Historical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_War = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Comedy = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Crime = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Horror = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Musical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Western = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox_TimePref = new javax.swing.JComboBox<>();
        jComboBox_RatingPref = new javax.swing.JComboBox<>();
        JTF_Password = new javax.swing.JPasswordField();
        BG = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));

        jPanel1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Already Registered ?");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 220, 30));

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Back to Main Menu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 220, 30));

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("HELP");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 220, 30));

        SystemStatus.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        SystemStatus.setForeground(new java.awt.Color(255, 255, 255));
        SystemStatus.setText("SYSTEM STATUS");
        jPanel1.add(SystemStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 700, -1, -1));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 0));
        jLabel1.setText(".");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 680, 40, 40));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel2.setText("MOVIE PREFERENCE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, -1, -1));
        jPanel1.add(JTF_First_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 330, 40));
        jPanel1.add(JTF_Last_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 260, 330, 40));
        jPanel1.add(JTF_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 330, 40));
        jPanel1.add(JTF_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 330, 40));

        JBTN_Register.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Register.setText("REGISTER");
        JBTN_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_RegisterActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 560, 330, 100));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("RATINGS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 410, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("LAST NAME");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("USERNAME");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("EMAIL");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("PASSWORD");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("HINT");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 630, 400, -1));

        jLabel9.setText("Password should be more than 6 characters and less than 18 characters.");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 690, 510, -1));

        jLabel10.setText("Username and Email Should be Unique");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 670, 400, -1));

        jLabel11.setText("FOR THE BEST EXPERIENCE PICK ONE");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 490, 230, -1));

        JCheckBox_ViewPassword.setText("Show Password");
        jPanel1.add(JCheckBox_ViewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 120, 40));

        jLabel12.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel12.setText("PERSONAL INFORMATION");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("FIRST NAME");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));

        jLabel14.setText("All Personal Feilds are Mandatory ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 650, 400, -1));

        jCheckBox_GENRE_Action.setText("Action");
        jPanel1.add(jCheckBox_GENRE_Action, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 200, -1, -1));

        jCheckBox_GENRE_Drama.setText("Drama");
        jPanel1.add(jCheckBox_GENRE_Drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, -1, -1));

        jCheckBox_GENRE_Mystery.setText("Mystery");
        jPanel1.add(jCheckBox_GENRE_Mystery, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 200, -1, -1));

        jCheckBox_GENRE_Adventure.setText("Adventure");
        jPanel1.add(jCheckBox_GENRE_Adventure, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 200, -1, -1));

        jCheckBox_GENRE_ScienceFiction.setText("Science Fiction");
        jPanel1.add(jCheckBox_GENRE_ScienceFiction, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 220, -1, -1));

        jCheckBox_GENRE_Romance.setText("Romance");
        jPanel1.add(jCheckBox_GENRE_Romance, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 220, -1, -1));

        jCheckBox_GENRE_Family.setText("Family");
        jPanel1.add(jCheckBox_GENRE_Family, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 220, -1, -1));

        jCheckBox_GENRE_Animation.setText("Animation");
        jCheckBox_GENRE_Animation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_GENRE_AnimationActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox_GENRE_Animation, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 220, -1, -1));

        jCheckBox_GENRE_Fantasy.setText("Fantasy");
        jPanel1.add(jCheckBox_GENRE_Fantasy, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 240, -1, -1));

        jCheckBox_GENRE_Biography.setText("Biography");
        jPanel1.add(jCheckBox_GENRE_Biography, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 240, -1, -1));

        jCheckBox_GENRE_FilmNoir.setText("Film Noir");
        jPanel1.add(jCheckBox_GENRE_FilmNoir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 240, -1, -1));

        jCheckBox_GENRE_Thriller.setText("Thriller");
        jPanel1.add(jCheckBox_GENRE_Thriller, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 240, -1, -1));

        jCheckBox_GENRE_Documentary.setText("Documentary");
        jPanel1.add(jCheckBox_GENRE_Documentary, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 260, -1, -1));

        jCheckBox_GENRE_Historical.setText("Historical");
        jPanel1.add(jCheckBox_GENRE_Historical, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 260, -1, -1));

        jCheckBox_GENRE_War.setText("War");
        jPanel1.add(jCheckBox_GENRE_War, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 260, -1, -1));

        jCheckBox_GENRE_Comedy.setText("Comedy");
        jPanel1.add(jCheckBox_GENRE_Comedy, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 260, -1, -1));

        jCheckBox_GENRE_Crime.setText("Crime");
        jPanel1.add(jCheckBox_GENRE_Crime, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 280, -1, -1));

        jCheckBox_GENRE_Horror.setText("Horror");
        jPanel1.add(jCheckBox_GENRE_Horror, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 280, -1, -1));

        jCheckBox_GENRE_Musical.setText("Musical");
        jPanel1.add(jCheckBox_GENRE_Musical, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 280, -1, -1));

        jCheckBox_GENRE_Western.setText("Western");
        jPanel1.add(jCheckBox_GENRE_Western, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 280, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("GENRE PREFERENCE");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 170, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("TIME PREFERENCE");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 340, -1, -1));

        jLabel17.setText("FOR THE BEST EXPERIENCE PICK THREE TO FIVE GENRE'S");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 310, 390, -1));

        jComboBox_TimePref.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "OLDER", "RECENT", "NO PREFERENCE" }));
        jPanel1.add(jComboBox_TimePref, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 370, 320, 30));

        jComboBox_RatingPref.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "7 & ABOVE", "5 & ABOVE", "NO PREFERENCE" }));
        jPanel1.add(jComboBox_RatingPref, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 440, 320, 30));
        jPanel1.add(JTF_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 330, 40));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RegisterBG.jpg"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPasswordField2.setText("jPasswordField2");
        jPanel1.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 520, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Open the Movie_Maven page button
        Movie_Maven f2= new Movie_Maven();//opens the Movie_Maven page
        f2.setVisible(true);

        //Hide the current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Open the Movie_Maven page button
        Movie_Maven f2= new Movie_Maven();
        f2.setVisible(true);

        //Hide the current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Open the hELP page button
        LoginHelpPage f2= new LoginHelpPage();
        f2.setVisible(true);

        //Hide the current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Open the Login page button
        Login f2= new Login();
        f2.setVisible(true);
        
        //Hide the Current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    //REGISTER BUTTON
    private void JBTN_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_RegisterActionPerformed
    String firstName = JTF_First_Name.getText();
    String lastName = JTF_Last_Name.getText();
    String username = JTF_Username.getText();
    String email = JTF_Email.getText();
    String password = JTF_Password.getText();

    ArrayList<String> selectedGenres = new ArrayList<>();
    if (jCheckBox_GENRE_Action.isSelected()) {
    selectedGenres.add("Action");
}
    if (jCheckBox_GENRE_Drama.isSelected()) {
    selectedGenres.add("Drama");
}
    if (jCheckBox_GENRE_Mystery.isSelected()) {
    selectedGenres.add("Mystery");
}
    if (jCheckBox_GENRE_Adventure.isSelected()) {
    selectedGenres.add("Adventure");
}
    if (jCheckBox_GENRE_Animation.isSelected()) {
    selectedGenres.add("Animation");
}
    if (jCheckBox_GENRE_Family.isSelected()) {
    selectedGenres.add("Family");
}
    if (jCheckBox_GENRE_Romance.isSelected()) {
    selectedGenres.add("Romance");
}
    if (jCheckBox_GENRE_ScienceFiction.isSelected()) {
    selectedGenres.add("Science Fiction");
}
    if (jCheckBox_GENRE_Fantasy.isSelected()) {
    selectedGenres.add("Fantasy");
}
    if (jCheckBox_GENRE_Biography.isSelected()) {
    selectedGenres.add("Biography");
}
    if (jCheckBox_GENRE_FilmNoir.isSelected()) {
    selectedGenres.add("Film Noir");
}
    if (jCheckBox_GENRE_Thriller.isSelected()) {
    selectedGenres.add("Thriller");
}
    if (jCheckBox_GENRE_Comedy.isSelected()) {
    selectedGenres.add("Comedy");
}
    if (jCheckBox_GENRE_Historical.isSelected()) {
    selectedGenres.add("Historical");
}
    if (jCheckBox_GENRE_War.isSelected()) {
    selectedGenres.add("War");
}
    if (jCheckBox_GENRE_Documentary.isSelected()) {
    selectedGenres.add("Documentary");
}
    if (jCheckBox_GENRE_Crime.isSelected()) {
    selectedGenres.add("Crime");
}
    if (jCheckBox_GENRE_Horror.isSelected()) {
    selectedGenres.add("Horror");
}
    if (jCheckBox_GENRE_Musical.isSelected()) {
    selectedGenres.add("Musical");
}
    if (jCheckBox_GENRE_Western.isSelected()) {
    selectedGenres.add("Western");
}
    if (selectedGenres.size() < 3 || selectedGenres.size() > 5) {
    JOptionPane.showMessageDialog(null, "Please select between 3 to 5 genres.");
    return;
}
    String joinedGenres = String.join(",", selectedGenres);
    String timePreference = (String) jComboBox_TimePref.getSelectedItem();
    String ratingPreference = (String) jComboBox_RatingPref.getSelectedItem();

    //VALIDATION FOR TIME PREFERENCE
    if ("NOT SELECTED".equals(timePreference)) {
    JOptionPane.showMessageDialog(null, "Please select a valid time preference.");
    return;
}

    //VALIDATE RATING
    if ("NOT SELECTED".equals(ratingPreference)) {
    JOptionPane.showMessageDialog(null, "Please select a valid rating preference.");
    return;
}

    
    if (password.length() < 6 || password.length() > 18) {
        JOptionPane.showMessageDialog(null, "Password should be more than 6 characters and less than 18 characters.");
        return;
    }
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        pstmt = conn.prepareStatement("SELECT * FROM users WHERE username=? OR email=?");
        pstmt.setString(1, username);
        pstmt.setString(2, email);
        rs = pstmt.executeQuery();
        
        if (rs.next()) {
            if (username.equals(rs.getString("username"))) {
                JOptionPane.showMessageDialog(null, "Username already exists.");
            } 
            if (email.equals(rs.getString("email"))) {
                JOptionPane.showMessageDialog(null, "Email already exists.");
            } 
        } else {
            pstmt = conn.prepareStatement("INSERT INTO users(firstname, lastname, username, email, password, DB_IntiPref_Genre, DB_IntiPref_Time, DB_IntiPref_Rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, joinedGenres);
            pstmt.setString(7, timePreference);
            pstmt.setString(8, ratingPreference);
            
            int i = pstmt.executeUpdate();
            
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "User registration successful.");
                new Login().setVisible(true); // Open the Login window
                this.dispose(); // Close the current Register_Customer window
            } else {
                JOptionPane.showMessageDialog(null, "User registration failed.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_JBTN_RegisterActionPerformed

    private void jCheckBox_GENRE_AnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_GENRE_AnimationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_GENRE_AnimationActionPerformed

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
            java.util.logging.Logger.getLogger(Register_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register_Customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton JBTN_Register;
    private javax.swing.JCheckBox JCheckBox_ViewPassword;
    private javax.swing.JTextField JTF_Email;
    private javax.swing.JTextField JTF_First_Name;
    private javax.swing.JTextField JTF_Last_Name;
    private javax.swing.JPasswordField JTF_Password;
    private javax.swing.JTextField JTF_Username;
    private javax.swing.JLabel SystemStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JComboBox<String> jComboBox_RatingPref;
    private javax.swing.JComboBox<String> jComboBox_TimePref;
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
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables
}
