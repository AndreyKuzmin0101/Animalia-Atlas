package ru.kpfu.itis.kuzmin.animalswebapp.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionUtil {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/animals_webapp_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty1234";

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setUsername("postgres");
            hikariConfig.setPassword("qwerty1234");
            hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/animals_webapp_db");
            hikariConfig.setDriverClassName("org.postgresql.Driver");

            DataSource dataSource = new HikariDataSource(hikariConfig);

            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
