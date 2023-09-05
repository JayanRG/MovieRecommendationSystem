package System;

//Import Statements
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Desktop;
import java.net.URI; //Fetching External Ratings
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import java.awt.event.ActionEvent;//required classes for fetch rating action listener 
import java.awt.event.ActionListener;
import java.awt.Image;//classes required for cropping the main image
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Toolkit;


public class Add_Movie_dash extends javax.swing.JFrame {

    public Add_Movie_dash() {
        initComponents();
        
        lblLoadingIndicator.setVisible(false); // API Loading label visibility off
        
        //Fetch Ratings Action Listener for the button
    btnFetchRatings.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Fetch the movie title and year from the text fields
        String movieTitle = JTF_Movie_Name.getText();
        String movieYear = JTF_Year.getText();
        
        // Check if both fields are filled out
        if (movieTitle.isEmpty() || movieYear.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Both movie title and year must be provided.");
            return;
        }

        // If both fields are filled out, fetch the ratings
        fetchMovieRatings(movieTitle, movieYear);
    }
});
        
        //Youtube Preview
        JBTN_Preview_Youtube_Video.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        previewYoutubeVideoActionPerformed(evt);
    }
});

        
       // if movie is shown in maven cinemas
       JCB_MavenCinema_Yes.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED) {
            
            // Enable all Hall and Time checkboxes
            JCheckBox_HallNo_1.setEnabled(true);
            JCheckBox_HallNo_2.setEnabled(true);
            JCheckBox_HallNo_3.setEnabled(true);
            JCheckBox_HallNo_4.setEnabled(true);
            JCheckBox_HallNo_5.setEnabled(true);
            JCheckBox_HallNo_6.setEnabled(true);
            JCheckBox_ShowingTimes_1030AM.setEnabled(true);
            JCheckBox_ShowingTimes_0130PM.setEnabled(true);
            JCheckBox_ShowingTimes_0430PM.setEnabled(true);
            JCheckBox_ShowingTimes_0730PM.setEnabled(true);
            JCheckBox_ShowingTimes_1030PM.setEnabled(true);

            // Unselect and disable the No checkbox
            JCB_MavenCinema_No.setSelected(false);
        }
    }
});
        
        //If Movie is not shown in maven cinemas
        JCB_MavenCinema_No.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        // Check or uncheck based on the state of JCB_MavenCinema_No
        boolean disable = e.getStateChange() == ItemEvent.SELECTED; // True if selected, False otherwise

        // Disable or Enable Hall Number checkboxes
        JCheckBox_HallNo_1.setEnabled(!disable);
        JCheckBox_HallNo_2.setEnabled(!disable);
        JCheckBox_HallNo_3.setEnabled(!disable);
        JCheckBox_HallNo_4.setEnabled(!disable);
        JCheckBox_HallNo_5.setEnabled(!disable);
        JCheckBox_HallNo_6.setEnabled(!disable);

        // Disable or Enable Showing Times checkboxes
        JCheckBox_ShowingTimes_1030AM.setEnabled(!disable);
        JCheckBox_ShowingTimes_0130PM.setEnabled(!disable);
        JCheckBox_ShowingTimes_0430PM.setEnabled(!disable);
        JCheckBox_ShowingTimes_0730PM.setEnabled(!disable);
        JCheckBox_ShowingTimes_1030PM.setEnabled(!disable);

        // Clear the checkboxes if they are disabled
        if(disable) {
            JCB_MavenCinema_Yes.setSelected(false);

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
    }
});

        pack();  // Layout the components in the card(helps to rezie the image to the card)
        
    loadMoviesIntoTable();
    
    JTable1.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        JTable1MouseClicked(evt);
    }
});


    }
    // FETCH MOVIE RATINGS (EXTERNAL)
    public void fetchMovieRatings(String movieTitle, String movieYear) {
    lblLoadingIndicator.setVisible(true);  // Show loading indicator
    
    String apiKey = "935a933f"; // Your OMDb API key
    String urlString = "http://www.omdbapi.com/?t=" + movieTitle + "&y=" + movieYear + "&apikey=" + apiKey;
    
    /*movieYear: This is the year of release for the movie. We'll get this from the UI and pass it to our function.
    &y=: This is how you specify the year in the OMDb API.*/
    
    try {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        // Optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        if (responseCode == 200) { // Success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            // Parse JSON
            JSONObject jsonObj = new JSONObject(response.toString());
            
            // Check if movie was found and keys exist
            if (jsonObj.has("Response") && jsonObj.getString("Response").equals("True")) {
                if (jsonObj.has("imdbRating") && jsonObj.has("Ratings")) {
                    String imdbRating = jsonObj.getString("imdbRating");
                    String rottenTomatoesRating = jsonObj.getJSONArray("Ratings").getJSONObject(1).getString("Value").replace("%", "");

                    // Update the text fields
                    JTF_IMDb_Rating.setText(imdbRating);
                    JTF_Rotten_Tomatoes.setText(rottenTomatoesRating);
                } else {
                    // Show a message if keys are missing
                    JOptionPane.showMessageDialog(this, "Could not find ratings for the movie.");
                }
            } else {
                // Movie not found
                JOptionPane.showMessageDialog(this, "Movie not found.");
            }
            // Hide loading indicator
            lblLoadingIndicator.setVisible(false);

        } 
        else {
            // Handle other errors
            System.out.println("GET request failed. Input Ratings Manually.  Response Code : " + responseCode);
            lblLoadingIndicator.setVisible(false); // Hide loading indicator even if it fails
        }
    } catch (Exception e) {
        e.printStackTrace();
        lblLoadingIndicator.setVisible(false); // Hide loading indicator in case of an exception
    }
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
        jCheckBox_GENRE_Action = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Adventure = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Animation = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Biography = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Comedy = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Crime = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Documentary = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Drama = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Family = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Fantasy = new javax.swing.JCheckBox();
        jCheckBox_GENRE_FilmNoir = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Historical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Horror = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Musical = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Mystery = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Romance = new javax.swing.JCheckBox();
        jCheckBox_GENRE_ScienceFiction = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Thriller = new javax.swing.JCheckBox();
        jCheckBox_GENRE_War = new javax.swing.JCheckBox();
        jCheckBox_GENRE_Western = new javax.swing.JCheckBox();
        lblImagePreview = new javax.swing.JLabel();
        btnChooseImage = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        JBTN_Back = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        JCB_MavenCinema_Yes = new javax.swing.JCheckBox();
        JCB_MavenCinema_No = new javax.swing.JCheckBox();
        JBTN_Preview_Youtube_Video = new javax.swing.JButton();
        JTF_Youtube_URL = new javax.swing.JTextField();
        lblLoadingIndicator = new javax.swing.JLabel();
        btnFetchRatings = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        JTF_MainImage_URL = new javax.swing.JTextField();
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
        jPanel1.add(JBTN_Add_Movie, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 520, 130, 40));
        jPanel1.add(JTF_Movie_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 260, 30));
        jPanel1.add(JTF_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 110, 30));
        jPanel1.add(JTF_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 60, 30));

        JTextArea_Description.setColumns(20);
        JTextArea_Description.setRows(5);
        jScrollPane1.setViewportView(JTextArea_Description);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 300, 160));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Movie Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Movie Year");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Description");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Maven Rating");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 110, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Genre");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 50, 30));

        JTable1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
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

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 380, 230));

        JTextArea_Cast.setColumns(20);
        JTextArea_Cast.setRows(5);
        jScrollPane3.setViewportView(JTextArea_Cast);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 300, 90));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cast");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, -1, -1));
        jPanel1.add(JTF_IMDb_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 50, 30));
        jPanel1.add(JTF_Rotten_Tomatoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 50, 30));
        jPanel1.add(JTF_Director, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 230, 30));
        jPanel1.add(JTF_Composer, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 230, 30));

        JComboBox_Country.setBackground(new java.awt.Color(0, 0, 0));
        JComboBox_Country.setForeground(new java.awt.Color(255, 255, 255));
        JComboBox_Country.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "United States of America", "England", "Australia", "Sri Lanka", "South Korea" }));
        JComboBox_Country.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JComboBox_CountryActionPerformed(evt);
            }
        });
        jPanel1.add(JComboBox_Country, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 230, 30));

        JComboBox_Quality.setBackground(new java.awt.Color(0, 0, 0));
        JComboBox_Quality.setForeground(new java.awt.Color(255, 255, 255));
        JComboBox_Quality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "Digital 2D", "Digital 3D", "DOLBY ATMOS" }));
        jPanel1.add(JComboBox_Quality, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 230, 30));
        jPanel1.add(JTF_Duration, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rotten Tomatoes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 230, -1, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("IMDb Rating");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Director");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Music Composer");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, -1, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Content Rating");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, -1, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Country ");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quality ");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, -1, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Duration");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 480, -1, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Hall No");
        jLabel15.setToolTipText("");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 560, -1, 30));

        JCheckBox_ShowingTimes_1030AM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_1030AM.setText("10:30 AM");
        jPanel1.add(JCheckBox_ShowingTimes_1030AM, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 610, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Showing Times");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 600, -1, -1));

        JBTN_Update.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Update.setText("UPDATE");
        JBTN_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_UpdateActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 520, 130, 40));

        JBTN_Delete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        JBTN_Delete.setText("DELETE");
        JBTN_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_DeleteActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 520, 130, 40));

        JComboBox_Content_Rating.setBackground(new java.awt.Color(0, 0, 0));
        JComboBox_Content_Rating.setForeground(new java.awt.Color(255, 255, 255));
        JComboBox_Content_Rating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOT SELECTED", "G", "PG", "R" }));
        jPanel1.add(JComboBox_Content_Rating, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 230, 30));

        JCheckBox_ShowingTimes_0130PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0130PM.setText("01:30 PM");
        JCheckBox_ShowingTimes_0130PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox_ShowingTimes_0130PMActionPerformed(evt);
            }
        });
        jPanel1.add(JCheckBox_ShowingTimes_0130PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 610, -1, -1));

        JCheckBox_ShowingTimes_0430PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0430PM.setText("04:30 PM");
        JCheckBox_ShowingTimes_0430PM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox_ShowingTimes_0430PMActionPerformed(evt);
            }
        });
        jPanel1.add(JCheckBox_ShowingTimes_0430PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 610, -1, -1));

        JCheckBox_ShowingTimes_0730PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_0730PM.setText("07:30 PM");
        jPanel1.add(JCheckBox_ShowingTimes_0730PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 640, -1, -1));

        JCheckBox_ShowingTimes_1030PM.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_ShowingTimes_1030PM.setText("10:30 PM");
        jPanel1.add(JCheckBox_ShowingTimes_1030PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 640, -1, -1));

        JBTN_CLR_Fields.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_CLR_Fields.setText("CLEAR FIELDS");
        JBTN_CLR_Fields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_CLR_FieldsActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_CLR_Fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 490, -1, -1));

        JBTN_RefreshTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_RefreshTable.setText("REFRESH TABLE");
        JBTN_RefreshTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_RefreshTableActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_RefreshTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 380, 30));

        JCheckBox_HallNo_1.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_1.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_1.setText("1");
        jPanel1.add(JCheckBox_HallNo_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 560, 50, 30));

        JCheckBox_HallNo_2.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_2.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_2.setText("2");
        JCheckBox_HallNo_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCheckBox_HallNo_2ActionPerformed(evt);
            }
        });
        jPanel1.add(JCheckBox_HallNo_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 560, 50, 30));

        JCheckBox_HallNo_3.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_3.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_3.setText("3");
        jPanel1.add(JCheckBox_HallNo_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 50, 30));

        JCheckBox_HallNo_4.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_4.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_4.setText("4");
        jPanel1.add(JCheckBox_HallNo_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 560, 50, 30));

        JCheckBox_HallNo_5.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_5.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_5.setText("5");
        jPanel1.add(JCheckBox_HallNo_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, 40, 30));

        JCheckBox_HallNo_6.setBackground(new java.awt.Color(0, 0, 0));
        JCheckBox_HallNo_6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCheckBox_HallNo_6.setForeground(new java.awt.Color(255, 255, 255));
        JCheckBox_HallNo_6.setText("6");
        jPanel1.add(JCheckBox_HallNo_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 50, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("%");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 230, 30, 30));

        jCheckBox_GENRE_Action.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Action.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Action.setText("Action");
        jPanel1.add(jCheckBox_GENRE_Action, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, -1, -1));

        jCheckBox_GENRE_Adventure.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Adventure.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Adventure.setText("Adventure");
        jPanel1.add(jCheckBox_GENRE_Adventure, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));

        jCheckBox_GENRE_Animation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Animation.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Animation.setText("Animation");
        jCheckBox_GENRE_Animation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_GENRE_AnimationActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox_GENRE_Animation, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        jCheckBox_GENRE_Biography.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Biography.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Biography.setText("Biography");
        jPanel1.add(jCheckBox_GENRE_Biography, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, -1, -1));

        jCheckBox_GENRE_Comedy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Comedy.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Comedy.setText("Comedy");
        jPanel1.add(jCheckBox_GENRE_Comedy, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        jCheckBox_GENRE_Crime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Crime.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Crime.setText("Crime");
        jPanel1.add(jCheckBox_GENRE_Crime, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        jCheckBox_GENRE_Documentary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Documentary.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Documentary.setText("Documentary");
        jPanel1.add(jCheckBox_GENRE_Documentary, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 490, -1, -1));

        jCheckBox_GENRE_Drama.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Drama.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Drama.setText("Drama");
        jPanel1.add(jCheckBox_GENRE_Drama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, -1, -1));

        jCheckBox_GENRE_Family.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Family.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Family.setText("Family");
        jPanel1.add(jCheckBox_GENRE_Family, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, -1, -1));

        jCheckBox_GENRE_Fantasy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Fantasy.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Fantasy.setText("Fantasy");
        jPanel1.add(jCheckBox_GENRE_Fantasy, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));

        jCheckBox_GENRE_FilmNoir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_FilmNoir.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_FilmNoir.setText("Film Noir");
        jPanel1.add(jCheckBox_GENRE_FilmNoir, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, -1, -1));

        jCheckBox_GENRE_Historical.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Historical.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Historical.setText("Historical");
        jPanel1.add(jCheckBox_GENRE_Historical, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, -1, -1));

        jCheckBox_GENRE_Horror.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Horror.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Horror.setText("Horror");
        jPanel1.add(jCheckBox_GENRE_Horror, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, -1, -1));

        jCheckBox_GENRE_Musical.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Musical.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Musical.setText("Musical");
        jPanel1.add(jCheckBox_GENRE_Musical, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 510, -1, -1));

        jCheckBox_GENRE_Mystery.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Mystery.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Mystery.setText("Mystery");
        jPanel1.add(jCheckBox_GENRE_Mystery, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 430, -1, -1));

        jCheckBox_GENRE_Romance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Romance.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Romance.setText("Romance");
        jPanel1.add(jCheckBox_GENRE_Romance, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, -1, -1));

        jCheckBox_GENRE_ScienceFiction.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_ScienceFiction.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_ScienceFiction.setText("Science Fiction");
        jPanel1.add(jCheckBox_GENRE_ScienceFiction, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, -1, -1));

        jCheckBox_GENRE_Thriller.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Thriller.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Thriller.setText("Thriller");
        jPanel1.add(jCheckBox_GENRE_Thriller, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, -1, -1));

        jCheckBox_GENRE_War.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_War.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_War.setText("War");
        jPanel1.add(jCheckBox_GENRE_War, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, -1, -1));

        jCheckBox_GENRE_Western.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox_GENRE_Western.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox_GENRE_Western.setText("Western");
        jPanel1.add(jCheckBox_GENRE_Western, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, -1, -1));

        lblImagePreview.setForeground(new java.awt.Color(255, 255, 255));
        lblImagePreview.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(153, 153, 153)));
        jPanel1.add(lblImagePreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 40, 180, 270));

        btnChooseImage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChooseImage.setText("Browse");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 80, 250, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("YouTube URL");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 320, 100, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Main Image");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 40, -1, 30));

        JBTN_Back.setBackground(new java.awt.Color(0, 0, 0));
        JBTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back.png"))); // NOI18N
        JBTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_BackActionPerformed(evt);
            }
        });
        jPanel1.add(JBTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Active on Maven Cinemas ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 520, -1, -1));

        JCB_MavenCinema_Yes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCB_MavenCinema_Yes.setForeground(new java.awt.Color(255, 255, 255));
        JCB_MavenCinema_Yes.setText("YES");
        jPanel1.add(JCB_MavenCinema_Yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 520, 60, -1));

        JCB_MavenCinema_No.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JCB_MavenCinema_No.setForeground(new java.awt.Color(255, 255, 255));
        JCB_MavenCinema_No.setText("NO");
        jPanel1.add(JCB_MavenCinema_No, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 520, 60, -1));

        JBTN_Preview_Youtube_Video.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JBTN_Preview_Youtube_Video.setText("Preview");
        jPanel1.add(JBTN_Preview_Youtube_Video, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 350, 110, 30));
        jPanel1.add(JTF_Youtube_URL, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 350, 310, 30));

        lblLoadingIndicator.setFont(new java.awt.Font("Segoe UI", 3, 13)); // NOI18N
        lblLoadingIndicator.setForeground(new java.awt.Color(255, 255, 255));
        lblLoadingIndicator.setText("Loading...");
        jPanel1.add(lblLoadingIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 200, 80, 30));

        btnFetchRatings.setText("FETCH RATINGS");
        jPanel1.add(btnFetchRatings, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 190, 170, 50));

        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("Always Fetch Rating for Accurate Results*");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 246, 240, 20));
        jPanel1.add(JTF_MainImage_URL, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 120, 250, 30));

        BG.setForeground(new java.awt.Color(255, 255, 255));
        BG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/blur.jpg"))); // NOI18N
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

    //VALIDATING THE MOVIE INFORMATION
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
    boolean isGenreSelected = jCheckBox_GENRE_Action.isSelected() ||
    jCheckBox_GENRE_Adventure.isSelected() ||
    jCheckBox_GENRE_Animation.isSelected() ||
    jCheckBox_GENRE_Biography.isSelected() ||
    jCheckBox_GENRE_Comedy.isSelected() ||
    jCheckBox_GENRE_Crime.isSelected() ||
    jCheckBox_GENRE_Documentary.isSelected() ||
    jCheckBox_GENRE_Drama.isSelected() ||
    jCheckBox_GENRE_Family.isSelected() ||
    jCheckBox_GENRE_Fantasy.isSelected() ||
    jCheckBox_GENRE_FilmNoir.isSelected() ||
    jCheckBox_GENRE_Historical.isSelected() ||
    jCheckBox_GENRE_Horror.isSelected() ||
    jCheckBox_GENRE_Musical.isSelected() ||
    jCheckBox_GENRE_Mystery.isSelected() ||
    jCheckBox_GENRE_Romance.isSelected() ||
    jCheckBox_GENRE_ScienceFiction.isSelected() ||
    jCheckBox_GENRE_Thriller.isSelected() ||
    jCheckBox_GENRE_War.isSelected() ||
    jCheckBox_GENRE_Western.isSelected();

    if (!isGenreSelected) {
    JOptionPane.showMessageDialog(null, "Select at least one Genre.");
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
    //VALIDATE YOUTUBE URL
private void previewYoutubeVideoActionPerformed(java.awt.event.ActionEvent evt) {  //This method is invoked when the "Preview" button is clicked
    String youtubeUrl = JTF_Youtube_URL.getText(); //fetches the YouTube URL entered
    if (isValidYoutubeUrl(youtubeUrl)) {  //checks if the entered URL is a valid YouTube URL with the helper method 'isValidYoutubeUrl'.
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(youtubeUrl));
//above checks if the Desktop class is supported in the current environment and if the BROWSE action is supported. If so, open the URL in the default web browser.
            }
        } catch (Exception e) {  //exceptions are caught and printed to the console,
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to open the URL.");
        }
    } else {
        //dialog inform the user that it's not a valid YouTube URL
        JOptionPane.showMessageDialog(this, "Not a valid YouTube URL.");
    }
}

// Validate a YouTube URL
private boolean isValidYoutubeUrl(String url) {  //This is a helper method that checks if a given URL is a valid YouTube URL
    //check the format of the URL
    return url != null && url.startsWith("https://www.youtube.com/"); // line checks if the URL is not null and if it starts with the string "https://www.youtube.com/"
}

    //ADD BUTTON FUNCTION
    private void JBTN_Add_MovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_Add_MovieActionPerformed
    // Database connection
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
        
        // SQL query
        String sql = "INSERT INTO `all_movies_db` (`movie_name_db`, `mov_year_db`, `mov_genre_db`, `mov_descrip_db`, `mov_rating_db`, `Cast_db`, `IMDb_Rating_db`, `Rotten_Tomatos_db`, `Director_db`, `Music_composed_by_db`, `Content_Rating_db`, `Country_db`, `Quality_db`, `Duration_db`, `Hall_No_db`, `Showing_Times_db`, `Maven_Cinema_Activity`,  `Youtube_URL`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Validation for Movie Name (Should be unique)
        if(rs.next()) {
            JOptionPane.showMessageDialog(null, "Movie Name already Exists");
            return;
        }

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
if (!jCheckBox_GENRE_Action.isSelected() && 
    !jCheckBox_GENRE_Adventure.isSelected() &&
    !jCheckBox_GENRE_Animation.isSelected() &&
    !jCheckBox_GENRE_Biography.isSelected() &&
    !jCheckBox_GENRE_Comedy.isSelected() &&
    !jCheckBox_GENRE_Crime.isSelected() &&
    !jCheckBox_GENRE_Documentary.isSelected() &&
    !jCheckBox_GENRE_Drama.isSelected() &&
    !jCheckBox_GENRE_Family.isSelected() &&
    !jCheckBox_GENRE_Fantasy.isSelected() &&
    !jCheckBox_GENRE_FilmNoir.isSelected() &&
    !jCheckBox_GENRE_Historical.isSelected() &&
    !jCheckBox_GENRE_Horror.isSelected() &&
    !jCheckBox_GENRE_Musical.isSelected() &&
    !jCheckBox_GENRE_Mystery.isSelected() &&
    !jCheckBox_GENRE_Romance.isSelected() &&
    !jCheckBox_GENRE_ScienceFiction.isSelected() &&
    !jCheckBox_GENRE_Thriller.isSelected() &&
    !jCheckBox_GENRE_War.isSelected() &&
    !jCheckBox_GENRE_Western.isSelected()) {

    JOptionPane.showMessageDialog(null, "At least one genre must be selected.");
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

// Validation for Rotten Tomatoes Rating (Should be between 0 and 100)
try {
    int rottenRating = Integer.parseInt(JTF_Rotten_Tomatoes.getText().replace("%", ""));
    if (rottenRating < 0 || rottenRating > 100) {
        JOptionPane.showMessageDialog(null, "Rotten Tomatoes rating should be between 0 and 100");
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

boolean isMavenCinemaNo = JCB_MavenCinema_No.isSelected();

// If Maven Cinema is not set to "NO", then go with the regular validation
if (!isMavenCinemaNo) {
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
}



        // Insert data if validation passes
        query = "INSERT INTO all_movies_db (movie_name_db, mov_year_db, mov_genre_db, mov_descrip_db, mov_rating_db, Cast_db, IMDb_Rating_db, Rotten_Tomatos_db, Director_db, Music_composed_by_db, Content_Rating_db, Country_db, Quality_db, Duration_db, Hall_No_db, Showing_Times_db, image_path, Maven_Cinema_Activity, Youtube_URL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        pst = conn.prepareStatement(query);
        pst.setString(1, JTF_Movie_Name.getText());
        pst.setInt(2, Integer.parseInt(JTF_Year.getText()));
        pst.setString(3, constructGenreString());
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
        
        StringBuilder showingTimes = new StringBuilder();
        if (JCheckBox_ShowingTimes_1030AM.isSelected()) showingTimes.append("10:30 AM,");
        if (JCheckBox_ShowingTimes_0130PM.isSelected()) showingTimes.append("01:30 PM,");
        if (JCheckBox_ShowingTimes_0430PM.isSelected()) showingTimes.append("04:30 PM,");
        if (JCheckBox_ShowingTimes_0730PM.isSelected()) showingTimes.append("07:30 PM,");
        if (JCheckBox_ShowingTimes_1030PM.isSelected()) showingTimes.append("10:30 PM,");
        if (showingTimes.length() > 0) showingTimes.setLength(showingTimes.length() - 1); // Remove last comma

        String mavenCinemaActivity = JCB_MavenCinema_Yes.isSelected() ? "YES" : "NO";
     
        pst.setString(16, showingTimes.toString());
        pst.setString(15, hallNumbers.toString());
        pst.setString(17, imagePath);
        pst.setString(18, JCB_MavenCinema_No.isSelected() ? "NO" : "YES");
        String youtubeUrl = JTF_Youtube_URL.getText();
        pst.setString(19, youtubeUrl);
        
        StringBuilder genres = new StringBuilder();
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Action,");
        if (jCheckBox_GENRE_Adventure.isSelected()) genres.append("Adventure,");
        if (jCheckBox_GENRE_Animation.isSelected()) genres.append("Animation,");
        if (jCheckBox_GENRE_Biography.isSelected()) genres.append("Biography,");
        if (jCheckBox_GENRE_Comedy.isSelected()) genres.append("Comedy,");
        if (jCheckBox_GENRE_Crime.isSelected()) genres.append("Crime,");
        if (jCheckBox_GENRE_Documentary.isSelected()) genres.append("Documentary,");
        if (jCheckBox_GENRE_Drama.isSelected()) genres.append("Drama,");
        if (jCheckBox_GENRE_Family.isSelected()) genres.append("Family,");
        if (jCheckBox_GENRE_Fantasy.isSelected()) genres.append("Fantasy,");
        if (jCheckBox_GENRE_FilmNoir.isSelected()) genres.append("FilmNoir,");
        if (jCheckBox_GENRE_Historical.isSelected()) genres.append("Historical,");
        if (jCheckBox_GENRE_Horror.isSelected()) genres.append("Horror,");
        if (jCheckBox_GENRE_Musical.isSelected()) genres.append("Musical,");
        if (jCheckBox_GENRE_Mystery.isSelected()) genres.append("Mystery,");
        if (jCheckBox_GENRE_Romance.isSelected()) genres.append("Romance,");
        if (jCheckBox_GENRE_ScienceFiction.isSelected()) genres.append("ScienceFiction,");
        if (jCheckBox_GENRE_Thriller.isSelected()) genres.append("Thriller,");
        if (jCheckBox_GENRE_War.isSelected()) genres.append("War,");
        if (jCheckBox_GENRE_Western.isSelected()) genres.append("Western,");
        
        // Remove last comma
        if (genres.length() > 0) genres.setLength(genres.length() - 1); 

        int genreIndex = 3; 
        pst.setString(genreIndex, genres.toString());



        int result = pst.executeUpdate();
        if(result > 0) {
            JOptionPane.showMessageDialog(null, "Movie Added Successfully!");
            clearFields();  // Clear the fields after successfully adding the movie
            lblImagePreview.setIcon(null);
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating movie. Details: " + e.getMessage());
        }
}
    
    }//GEN-LAST:event_JBTN_Add_MovieActionPerformed

    //CLEARING FIELDS
    private void clearFields() {
    JTF_Movie_Name.setText("");
    JTF_Year.setText("");
    jCheckBox_GENRE_Action.setSelected(false);
    jCheckBox_GENRE_Adventure.setSelected(false);
    jCheckBox_GENRE_Animation.setSelected(false);
    jCheckBox_GENRE_Biography.setSelected(false);
    jCheckBox_GENRE_Comedy.setSelected(false);
    jCheckBox_GENRE_Crime.setSelected(false);
    jCheckBox_GENRE_Documentary.setSelected(false);
    jCheckBox_GENRE_Drama.setSelected(false);
    jCheckBox_GENRE_Family.setSelected(false);
    jCheckBox_GENRE_Fantasy.setSelected(false);
    jCheckBox_GENRE_FilmNoir.setSelected(false);
    jCheckBox_GENRE_Historical.setSelected(false);
    jCheckBox_GENRE_Horror.setSelected(false);
    jCheckBox_GENRE_Musical.setSelected(false);
    jCheckBox_GENRE_Mystery.setSelected(false);
    jCheckBox_GENRE_Romance.setSelected(false);
    jCheckBox_GENRE_ScienceFiction.setSelected(false);
    jCheckBox_GENRE_Thriller.setSelected(false);
    jCheckBox_GENRE_War.setSelected(false);
    jCheckBox_GENRE_Western.setSelected(false);
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
    lblImagePreview.setIcon(null);
}

    //populateGenreCheckboxes
    private void populateGenreCheckboxes(String genreString) {
    List<String> genres = Arrays.asList(genreString.split(","));
    jCheckBox_GENRE_Action.setSelected(genres.contains("Action"));
    jCheckBox_GENRE_Adventure.setSelected(genres.contains("Adventure"));
    
    // ... (repeat for all genre checkboxes) ...
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
            populateGenreCheckboxes(rs.getString("mov_genre_db"));
            JTextArea_Description.setText(rs.getString("mov_descrip_db"));
            JTF_Rating.setText(rs.getString("mov_rating_db"));
            JTextArea_Cast.setText(rs.getString("Cast_db"));
            JTF_IMDb_Rating.setText(rs.getString("IMDb_Rating_db"));
            JTF_Rotten_Tomatoes.setText(rs.getString("Rotten_Tomatos_db"));
            JTF_Director.setText(rs.getString("Director_db"));
            JTF_Composer.setText(rs.getString("Music_composed_by_db"));
            JComboBox_Content_Rating.setSelectedItem(rs.getString("Content_Rating_db"));
            JComboBox_Country.setSelectedItem(rs.getString("Country_db"));
            JComboBox_Quality.setSelectedItem(rs.getString("Quality_db"));
            JTF_Duration.setText(rs.getString("Duration_db"));
            String imagePath = rs.getString("image_path");

           //[Main Image]This checks if the Main Image is empty or not
            if (imagePath != null && !imagePath.trim().isEmpty()) {
            ImageIcon icon = new ImageIcon(imagePath);
            lblImagePreview.setIcon(icon);
                } else {
            lblImagePreview.setIcon(null);  // Clear the label if no image is found
            }


            // Clear all checkboxes first
            JCheckBox_HallNo_1.setSelected(false);
            JCheckBox_HallNo_2.setSelected(false);
            JCheckBox_HallNo_3.setSelected(false);
            JCheckBox_HallNo_4.setSelected(false);
            JCheckBox_HallNo_5.setSelected(false);
            JCheckBox_HallNo_6.setSelected(false);

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

            
            // Clear all checkboxes first
            JCheckBox_ShowingTimes_1030AM.setSelected(false);
            JCheckBox_ShowingTimes_0130PM.setSelected(false);
            JCheckBox_ShowingTimes_0430PM.setSelected(false);
            JCheckBox_ShowingTimes_0730PM.setSelected(false);
            JCheckBox_ShowingTimes_1030PM.setSelected(false);
            
            String[] genres = rs.getString("mov_genre_db").split(",");
            for (String genre : genres) {
            switch (genre.trim()) {
        case "Action": jCheckBox_GENRE_Action.setSelected(true); break;
        case "Adventure": jCheckBox_GENRE_Adventure.setSelected(true); break;
        case "Animation": jCheckBox_GENRE_Animation.setSelected(true); break;
        case "Biography": jCheckBox_GENRE_Biography.setSelected(true); break;
        case "Comedy": jCheckBox_GENRE_Comedy.setSelected(true); break;
        case "Crime": jCheckBox_GENRE_Crime.setSelected(true); break;
        case "Documentary": jCheckBox_GENRE_Documentary.setSelected(true); break;
        case "Drama": jCheckBox_GENRE_Drama.setSelected(true); break;
        case "Family": jCheckBox_GENRE_Family.setSelected(true); break;
        case "Fantasy": jCheckBox_GENRE_Fantasy.setSelected(true); break;
        case "FilmNoir": jCheckBox_GENRE_FilmNoir.setSelected(true); break;
        case "Historical": jCheckBox_GENRE_Historical.setSelected(true); break;
        case "Horror": jCheckBox_GENRE_Horror.setSelected(true); break;
        case "Musical": jCheckBox_GENRE_Musical.setSelected(true); break;
        case "Mystery": jCheckBox_GENRE_Mystery.setSelected(true); break;
        case "Romance": jCheckBox_GENRE_Romance.setSelected(true); break;
        case "ScienceFiction": jCheckBox_GENRE_ScienceFiction.setSelected(true); break;
        case "Thriller": jCheckBox_GENRE_Thriller.setSelected(true); break;
        case "War": jCheckBox_GENRE_War.setSelected(true); break;
        case "Western": jCheckBox_GENRE_Western.setSelected(true); break;
    }
}



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
} // POPULATE MOVIE DETAIL FROM TABLE TO FIELDS

    //load movies into table
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
    //Resize the Main image according to the label
    private void displayImage(File file) {
    try {
        BufferedImage originalImage = ImageIO.read(file);

        // JLabel dimensions
        int labelWidth = lblImagePreview.getWidth();
        int labelHeight = lblImagePreview.getHeight();

        // Original image dimensions
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // Calculate aspect ratios
        double originalAspect = (double) originalWidth / originalHeight;
        double labelAspect = (double) labelWidth / labelHeight;

        // Initialize dimensions for scaling
        int newWidth, newHeight;

        // Compare aspect ratios to determine how to fit the JLabel
        if (originalAspect >= labelAspect) {
            // If the original aspect ratio is greater or equal, scale height to fit and crop width
            newHeight = labelHeight;
            newWidth = (int) (labelHeight * originalAspect);
        } else {
            // If the original aspect ratio is smaller, scale width to fit and crop height
            newWidth = labelWidth;
            newHeight = (int) (labelWidth / originalAspect);
        }

        // Create a new scaled image
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage bufferedScaled = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedScaled.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        // Calculate cropping dimensions
        int cropX = (newWidth - labelWidth) / 2;
        int cropY = (newHeight - labelHeight) / 2;

        // Perform crop operation
        BufferedImage croppedImage = bufferedScaled.getSubimage(cropX, cropY, labelWidth, labelHeight);

        // Display the final cropped image in the JLabel
        lblImagePreview.setIcon(new ImageIcon(croppedImage));

        // Store the path for database insertion
        imagePath = file.getAbsolutePath();
        
        // Display the URL in the text field
        JTF_MainImage_URL.setText(imagePath);

        // Disable the text field
        JTF_MainImage_URL.setEnabled(false);
        
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error loading image.");
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
        //String query = "UPDATE all_movies_db SET mov_year_db=?, mov_genre_db=?, mov_descrip_db=?, mov_rating_db=?, Cast_db=?, IMDb_Rating_db=?, Rotten_Tomatos_db=?, Director_db=?, Music_composed_by_db=?, Content_Rating_db=?, Country_db=?, Quality_db=?, Duration_db=?, Hall_No_db=?, Showing_Times_db=?, image_path=? WHERE movie_name_db=?";
        pst = conn.prepareStatement(query);

        pst.setInt(1, Integer.parseInt(JTF_Year.getText()));
        pst.setString(3, constructGenreString());
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

        StringBuilder genres = new StringBuilder();
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Action,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Adventure,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Animation,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Biography,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Comedy,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Crime,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Documentary,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Drama,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Family,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Fantasy,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("FilmNoir,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Historical,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Horror,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Musical,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Mystery,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Romance,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("ScienceFiction,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Thriller,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("War,");
        if (jCheckBox_GENRE_Action.isSelected()) genres.append("Western,");
        if (genres.length() > 0) genres.setLength(genres.length() - 1); // Remove last comma
        pst.setString(2, genres.toString()); // Assuming genre is the second column in the update SQL query


        pst.setString(16, JTF_Movie_Name.getText());
        
        int result = pst.executeUpdate();
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Movie Updated Successfully!");
            clearFields();
            lblImagePreview.setIcon(null);
            loadMoviesIntoTable();
        } else {
            JOptionPane.showMessageDialog(null, "Error updating movies."); //this is the code which causes the update error 
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating movie. Details: " + e.getMessage());
    } finally {
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating movie. Details: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_JBTN_UpdateActionPerformed

    private String constructGenreString() {
    StringBuilder genres = new StringBuilder();
    if (jCheckBox_GENRE_Action.isSelected()) genres.append("Action,");
    if (jCheckBox_GENRE_Adventure.isSelected()) genres.append("Adventure,");
    if (jCheckBox_GENRE_Animation.isSelected()) genres.append("Animation,");
    if (jCheckBox_GENRE_Biography.isSelected()) genres.append("Biography,");
    if (jCheckBox_GENRE_Comedy.isSelected()) genres.append("Comedy,");
    if (jCheckBox_GENRE_Crime.isSelected()) genres.append("Crime,");
    if (jCheckBox_GENRE_Documentary.isSelected()) genres.append("Documentary,");
    if (jCheckBox_GENRE_Drama.isSelected()) genres.append("Drama,");
    if (jCheckBox_GENRE_Family.isSelected()) genres.append("Family,");
    if (jCheckBox_GENRE_Fantasy.isSelected()) genres.append("Fantasy,");
    if (jCheckBox_GENRE_FilmNoir.isSelected()) genres.append("FilmNoir,");
    if (jCheckBox_GENRE_Historical.isSelected()) genres.append("Historical,");
    if (jCheckBox_GENRE_Horror.isSelected()) genres.append("Horror,");
    if (jCheckBox_GENRE_Musical.isSelected()) genres.append("Musical,");
    if (jCheckBox_GENRE_Mystery.isSelected()) genres.append("Mystery,");
    if (jCheckBox_GENRE_Romance.isSelected()) genres.append("Romance,");
    if (jCheckBox_GENRE_ScienceFiction.isSelected()) genres.append("ScienceFiction,");
    if (jCheckBox_GENRE_Thriller.isSelected()) genres.append("Thriller,");
    if (jCheckBox_GENRE_War.isSelected()) genres.append("War,");
    if (jCheckBox_GENRE_Western.isSelected()) genres.append("Western,");
    
    // Remove last comma
    if (genres.length() > 0) genres.setLength(genres.length() - 1);
    
    return genres.toString();
}
/*
    //If a user wants to update the movie details but not the image, you don't want to accidentally overwrite the image path with a null value
    private String getExistingImagePathForMovie(String movieName) {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String imagePath = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        String query = "SELECT image_path FROM all_movies_db WHERE movie_name_db = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, movieName);

        rs = pst.executeQuery();
        if (rs.next()) {
            imagePath = rs.getString("image_path");
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

    return imagePath;
}

    */
    private void JBTN_CLR_FieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_CLR_FieldsActionPerformed
        clearFields();  // Call the clearFields() method to reset the components to their default states
        lblImagePreview.setIcon(null);
    }//GEN-LAST:event_JBTN_CLR_FieldsActionPerformed

    private void JBTN_RefreshTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_RefreshTableActionPerformed
        loadMoviesIntoTable();  // Refresh the JTable1 with updated data
    }//GEN-LAST:event_JBTN_RefreshTableActionPerformed

    private void JTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable1MouseClicked
        int selectedRow = JTable1.getSelectedRow();
        String movieName = JTable1.getValueAt(selectedRow, 0).toString();
        populateFieldsWithMovieDetails(movieName);
    }//GEN-LAST:event_JTable1MouseClicked

    //DELETING MOVIES
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
            lblImagePreview.setIcon(null);
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

      
    private void JCheckBox_HallNo_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCheckBox_HallNo_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCheckBox_HallNo_2ActionPerformed

    private void jCheckBox_GENRE_AnimationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_GENRE_AnimationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox_GENRE_AnimationActionPerformed

    //MOVIE IMAGE
    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        displayImage(selectedFile);
        imagePath = selectedFile.getAbsolutePath();
}

    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void JBTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_BackActionPerformed
        // Open the Admin dash
        Admin_Dash f2= new Admin_Dash();
        f2.setVisible(true);
        
        //Hide the Current Page
        this.setVisible(false);
    }//GEN-LAST:event_JBTN_BackActionPerformed

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
    private javax.swing.JButton JBTN_Back;
    private javax.swing.JButton JBTN_CLR_Fields;
    private javax.swing.JButton JBTN_Delete;
    private javax.swing.JButton JBTN_Preview_Youtube_Video;
    private javax.swing.JButton JBTN_RefreshTable;
    private javax.swing.JButton JBTN_Update;
    private javax.swing.JCheckBox JCB_MavenCinema_No;
    private javax.swing.JCheckBox JCB_MavenCinema_Yes;
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
    private javax.swing.JTextField JTF_IMDb_Rating;
    private javax.swing.JTextField JTF_MainImage_URL;
    private javax.swing.JTextField JTF_Movie_Name;
    private javax.swing.JTextField JTF_Rating;
    private javax.swing.JTextField JTF_Rotten_Tomatoes;
    private javax.swing.JTextField JTF_Year;
    private javax.swing.JTextField JTF_Youtube_URL;
    private javax.swing.JTable JTable1;
    private javax.swing.JTextArea JTextArea_Cast;
    private javax.swing.JTextArea JTextArea_Description;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnFetchRatings;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JLabel lblImagePreview;
    private javax.swing.JLabel lblLoadingIndicator;
    // End of variables declaration//GEN-END:variables
    // User-defined variables
    private String imagePath;
}