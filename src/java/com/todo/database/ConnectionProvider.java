package com.todo.database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author kevin
 */
public class ConnectionProvider {

    static Connection con;

    public static Connection CreateConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
