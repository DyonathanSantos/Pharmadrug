package com.pharmacy.config;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    public DatabaseConfig() {

        String url = "jdbc:sqlite:database/pharmacy.db";

        try (Connection connection = DriverManager.getConnection(url)){

            if(connection != null){
                System.out.println("=================================");
                System.out.println("SQLite connection successful!");
                System.out.println("=================================");
            }
        }catch (SQLException e){
            System.out.println("=================================");
            System.out.println("SQLite connection failed!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("=================================");
        }
    }
}
