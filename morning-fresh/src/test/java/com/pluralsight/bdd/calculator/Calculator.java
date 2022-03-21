package com.pluralsight.bdd.calculator;

import java.sql.SQLException;

public class Calculator {
    public Integer add(Integer int1, Integer int2) throws SQLException {

        DataHandler dbHandle = new DataHandler(
                "jdbc:mysql://127.0.0.1:3306/tom", "root", "test"
        );
        System.out.println(dbHandle.countRecordsWithIdentifier("mkyong"));

        return int1 + int2;
    }
}
