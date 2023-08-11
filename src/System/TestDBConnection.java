package System;

import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            try {
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("Connected to " + metaData.getDatabaseProductName() 
                                 + " " + metaData.getDatabaseProductVersion());
            } catch (SQLException e) {
                System.out.println("An error occurred while fetching database metadata.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}
