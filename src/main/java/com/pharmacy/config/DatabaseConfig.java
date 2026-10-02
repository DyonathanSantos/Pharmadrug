package com.pharmacy.config;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    private static final String URL = "jdbc:sqlite:database/pharmacy.db";

    public DatabaseConfig() {
        initializeDatabase();
    }

    private void initializeDatabase() {

        Path schemaPath = Path.of("database/schema.sql");

        try (
                Connection connection = DriverManager.getConnection(URL)
        ) {
            String schema = Files.readString(
                    schemaPath,
                    StandardCharsets.UTF_8
            );

            try (var statament = connection.createStatement()) {

                for (String sql: schema.split(";")) {
                    if (!sql.trim().isEmpty()) {
                        statament.execute(sql);
                    }
                }
            }

            System.out.println("=================================");
            System.out.println("Database initialized successfully!");
            System.out.println("=================================");

        }catch (SQLException | IOException e) {

            System.out.println("=================================");
            System.out.println("Database initialization failed!");
            System.out.println("Error: " + e.getMessage());
            System.out.println("=================================");
        }
    }

}