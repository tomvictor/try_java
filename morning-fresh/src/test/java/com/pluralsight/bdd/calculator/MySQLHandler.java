package com.pluralsight.bdd.calculator;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySQLHandler {

    protected String connectionUrl;
    protected String username;
    protected String password;

    public MySQLHandler(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    protected abstract Connection setupConnection();

    protected abstract void closeConnection() throws SQLException;

}
