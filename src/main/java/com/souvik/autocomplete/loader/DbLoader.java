package com.souvik.autocomplete.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Loads names from a text file into the file-based H2 database.
 *
 * This utility is executed once (outside the Spring Boot app) to populate
 * the 'names' table before the application starts. The main application
 * never reads the file directly, which follows the assignment requirement.
 */
public class DbLoader {
    public static void main(String[] args) throws Exception {

        // Expecting a single argument: path to names.txt
        if (args.length == 0) {
            System.err.println("Usage: java -cp jar-file com.souvik.autocomplete.loader.DbLoader names.txt");
            System.exit(1);
        }

        String filepath = args[0];
        List<String> lines = Files.readAllLines(Path.of(filepath));

        // File-based H2 DB stored under /data/namesdb
        String jdbc = "jdbc:h2:file:./data/namesdb;AUTO_SERVER=TRUE";

        try (Connection conn = DriverManager.getConnection(jdbc, "sa", "")) {
            conn.setAutoCommit(false);

            // Create table if it doesn't exist
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS names (" +
                            "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                            "name VARCHAR(255) UNIQUE NOT NULL)"
            );

            // Insert or update names
            try (PreparedStatement ps = conn.prepareStatement(
                    "MERGE INTO names(name) KEY(name) VALUES(?)")) {

                for (String name : lines) {
                    name = name.trim();
                    if (!name.isEmpty()) {
                        ps.setString(1, name);
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
            }

            conn.commit();
        }

        System.out.println("Names loaded successfully!");
    }
}
