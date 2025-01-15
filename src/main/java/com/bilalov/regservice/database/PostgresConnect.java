package com.bilalov.regservice.database;

import com.bilalov.regservice.models.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresConnect {

    // Данные для подключения к БД
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public List<Car> getCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM service_data");

            while (resultSet.next()) {
                Car car = new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("number"),
                        resultSet.getString("gosnomer"),
                        resultSet.getString("company"),
                        resultSet.getString("inn"),
                        resultSet.getString("place"),
                        resultSet.getString("name"),
                        resultSet.getString("comment")
                );
                cars.add(car);
            }
        }
        return cars;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

    }
}
