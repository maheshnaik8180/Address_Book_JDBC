package com.bridgelabz;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Enumeration;

public class AddressBook {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll?useSSL=false";
        String userName = "root";
        String passWord = "admin";
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver");

        }
        listDrivers();
        try{
            System.out.println("Connecting to database: "+jdbcURL);
            con=DriverManager.getConnection(jdbcURL,userName, passWord);
            System.out.println("Connection successfull: "+con);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void listDrivers() {
        Enumeration<Driver>driverlist=DriverManager.getDrivers();
        while(driverlist.hasMoreElements()){
            Driver driverClass=(Driver) driverlist.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}
