package com.pluralsight.bdd.calculator;

import java.sql.*;
import java.sql.Connection;
import java.time.LocalDateTime;

public class MySQLHandler {
    private final String DatabaseUrl;
    private final String User;
    private final String Password;

    public MySQLHandler(String DatabaseUrl, String User, String Password){
        this.User = User;
        this.Password = Password;
        this.DatabaseUrl = DatabaseUrl;
    }

    public int filterMetaDataCount(String UUID){
        try (Connection conn = DriverManager.getConnection(
                this.DatabaseUrl, this.User, this.Password)) {
            if (conn != null) {
                System.out.println("Connected to the database!");
//                String SQL_SELECT = "SELECT COUNT(*) as count FROM `tom`.`EMPLOYEE`;";
                String SQL_SELECT = "SELECT * FROM EMPLOYEE WHERE NAME = '" + UUID + "';";
                PreparedStatement filterMetaData = conn.prepareStatement(SQL_SELECT);
                ResultSet resultSet = filterMetaData.executeQuery();
                resultSet.last();
                return resultSet.getRow();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void insertItem(){
        // auto close connection and statement
        try (Connection conn = DriverManager.getConnection(
                this.DatabaseUrl, this.User, this.Password);
             Statement statement = conn.createStatement()) {

            System.out.println(generateInsert("mkyong", 999));

            int row = statement.executeUpdate(generateInsert("mkyong", 111));

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateInsert(String name, Integer salary) {

        return "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) " +
                "VALUES ('" + name + "','" + salary + "','" + LocalDateTime.now() + "')";

    }
}
