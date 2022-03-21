package com.pluralsight.bdd.calculator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataHandler extends MySQLHandler {

    private Connection connection;

    public DataHandler(String connectionUrl, String username, String password) {
        super(connectionUrl, username, password);
    }

    @Override
    protected Connection setupConnection() {
        try  {
            return DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    public int countRecordsWithIdentifier(String identifier) throws SQLException {

//        var query = String.format("SELECT COUNT(*) AS COUNT FROM status_store WHERE identifier='%s'", identifier);
//        var query = String.format("SELECT COUNT(*) AS COUNT FROM EMPLOYEE WHERE NAME ='%s'", identifier);
        var query = String.format("SELECT * FROM EMPLOYEE WHERE NAME ='%s'", identifier);
//        var connection = setupConnection();
        var result = setupConnection()
                .prepareStatement(query)
                .executeQuery();
        result.last();
        return result.getRow();
    }

}