package com.pluralsight.letsgetlogical;

public class Main {

    public static void main(String[] args) {
        System.out.println("Java logical operators.");
        int students = 150;
        int rooms = 0;

        if (rooms!= 0 && students/rooms > 30)
            System.out.println("Crowded");
    }
}
