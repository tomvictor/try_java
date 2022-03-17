package com.pluralsight.bdd.calculator;

public class Calculator {
    public Integer add(Integer int1, Integer int2) {

        MySQLHandler dbHandle = new MySQLHandler(
                "jdbc:mysql://127.0.0.1:3306/tom", "root", "test"
        );
        System.out.println(dbHandle.filterMetaDataCount("Don"));
        
        return int1 + int2;
    }
}
