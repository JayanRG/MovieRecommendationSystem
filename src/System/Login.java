package System;

import java.awt.Toolkit;
import java.awt.HeadlessException;
import java.sql.*;
import java.awt.event.ItemEvent;//for passwords visibility
import java.awt.event.ItemListener;//for passwords visibility


import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
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
        */
        
        initComponents();
        
        JBTN_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Connection conn = DBConnection.getConnection();
                String sql = "SELECT * FROM users WHERE username=? and password=?";
                try {
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, JTF_Username.getText());
                    pst.setString(2, String.valueOf(JTF_Password.getPassword()));
                    ResultSet rs = pst.executeQuery();
                    if(rs.next()){
                        // Success! Username and password are correct. 
                        dispose();
                        // Now open the dashboard and close this login window
                        new Customer_Dash().setVisible(true);
                    } else {
                        // Failure. Username and password are incorrect.
                        JOptionPane.showMessageDialog(null, "Username and Password are incorrect");
                    }
                } catch(SQLException | HeadlessException ex){
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });
        
        // this clears the username and the password fields
        JBTN_CLR_Fields.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        JTF_Username.setText("");  // clear the username field
        JTF_Password.setText("");  // clear the password field
        JTF_Username.requestFocus(); // set the focus on the username field
    }
});
        //PASSWORD VISIBILTY 
        jCheckBox_ShowPassword.addItemListener(new ItemListener() {
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Checkbox has been selected
            JTF_Password.setEchoChar((char) 0); // Password is visible
        } else {
            // Checkbox has been deselected
            JTF_Password.setEchoChar('•'); // Restore default echo character or use '*' for asterisk
        }
    }
});


    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JTF_Username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        JBTN_Register = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        JBTN_CLR_Fields = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        JTF_Password = new javax.swing.JPasswordField();
        JBTN_Login = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox_ShowPassword = new javax.swing.JCheckBox();
        BG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(1366, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(1366, 768));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(JTF_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, 330, 40));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOGIN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 320, -1, -1));

        JBTN_Register.setBackground(new java.awt.Color(204, 204, 204));
        JBTN_Register.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_Register.setText("NOT A MEMEBER ?");
        JBTN_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_RegisterActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Register, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 520, 160, 40));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("HELP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 520, 160, 40));

        JBTN_CLR_Fields.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        JBTN_CLR_Fields.setText("Clear Fields");
        jPanel1.add(JBTN_CLR_Fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 460, -1, -1));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel1.add(JTF_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 330, 40));

        JBTN_Login.setBackground(new java.awt.Color(204, 204, 204));
        JBTN_Login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Login.setText("LOGIN");
        JBTN_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_LoginActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 570, 330, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("USERNAME");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PASSWORD");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, -1, -1));

        jCheckBox_ShowPassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_ShowPassword.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_ShowPassword.setText("Show Password");
        jCheckBox_ShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_ShowPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox_ShowPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 410, -1, 40));

        BG.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MovieMavenBG.jpg"))); // NOI18N
        BG.setText("jLabel1");
        jPanel1.add(BG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_RegisterActionPerformed
        // Open the Register_customer page button
        Register_Customer f2= new Register_Customer();
        f2.setVisible(true);
        
        //Hide the Current Page
        this.setVisible(false);
    }//GEN-LAST:event_JBTN_RegisterActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Open the Login Help page button
        LoginHelpPage f2= new LoginHelpPage();
        f2.setVisible(true);
        
        //Hide the Current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Open the Movie_Maven page button
        Movie_Maven f2= new Movie_Maven();//opens the Movie_Maven page
        f2.setVisible(true);

        //Hide the current Page
        this.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void JBTN_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_LoginActionPerformed
        
    }//GEN-LAST:event_JBTN_LoginActionPerformed

    private void jCheckBox_ShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_ShowPasswordActionPerformed
   
    }//GEN-LAST:event_jCheckBox_ShowPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BG;
    private javax.swing.JButton JBTN_CLR_Fields;
    private javax.swing.JButton JBTN_Login;
    private javax.swing.JButton JBTN_Register;
    private javax.swing.JPasswordField JTF_Password;
    private javax.swing.JTextField JTF_Username;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox_ShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}