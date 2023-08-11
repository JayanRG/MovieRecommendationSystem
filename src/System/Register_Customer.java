package System;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Register_Customer extends javax.swing.JFrame {

//Creates new form Register_Customer

    public Register_Customer() {
      /**  
//Full Screen window
        setUndecorated(true);
        
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);
        Toolkit tk= Toolkit.getDefaultToolkit();
        
        int x=(int) tk.getScreenSize().getWidth();
        int y=(int) tk.getScreenSize().getHeight();
        
        setSize(x,y);
//End of Full Screen window
*  */

        initComponents();
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
        JTF_Password = new javax.swing.JTextField();
        JBTN_Register = new javax.swing.JButton();
        BG = new javax.swing.JLabel();

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
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, 220, 30));

        SystemStatus.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        SystemStatus.setText("SYSTEM STATUS");
        jPanel1.add(SystemStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 740, -1, -1));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 0));
        jLabel1.setText(".");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 720, 40, 40));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        jLabel2.setText("PERSONAL INFORMATION");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, -1, -1));

        JTF_First_Name.setText("FIRST NAME");
        jPanel1.add(JTF_First_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 330, 40));

        JTF_Last_Name.setText("LAST NAME");
        jPanel1.add(JTF_Last_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 290, 330, 40));

        JTF_Email.setText("EMAIL");
        jPanel1.add(JTF_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 330, 40));

        JTF_Username.setText("USERNAME");
        jPanel1.add(JTF_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 330, 40));

        JTF_Password.setText("PASSWORD");
        jPanel1.add(JTF_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 330, 40));

        JBTN_Register.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Register.setText("REGISTER");
        JBTN_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_RegisterActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 550, 330, 100));

        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/RegisterBG.jpg"))); // NOI18N
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void JBTN_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_RegisterActionPerformed
    String firstName = JTF_First_Name.getText();
    String lastName = JTF_Last_Name.getText();
    String username = JTF_Username.getText();
    String email = JTF_Email.getText();
    String password = JTF_Password.getText();
    
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
            pstmt = conn.prepareStatement("INSERT INTO users(firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, username);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            
            int i = pstmt.executeUpdate();
            
            if (i > 0) {
                JOptionPane.showMessageDialog(null, "User registration successful.");
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
    private javax.swing.JTextField JTF_Email;
    private javax.swing.JTextField JTF_First_Name;
    private javax.swing.JTextField JTF_Last_Name;
    private javax.swing.JTextField JTF_Password;
    private javax.swing.JTextField JTF_Username;
    private javax.swing.JLabel SystemStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
