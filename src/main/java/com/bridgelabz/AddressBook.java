package com.bridgelabz;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddressBook {
    private List<AddressBook> addressBookList;

    private Connection getConnection() throws SQLException {
        String jdbcurl="jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String usernam="root";
        String  password="admin";
        Connection con;
        System.out.println("Connecting to database: "+jdbcurl);
        con= DriverManager.getConnection(jdbcurl,usernam,password);
        System.out.println("Connection successfull: "+con);
        return con;
    }

    public List<AddressBookData> readData() {
        String sql_query="Select * from addressbook; ";
        List<AddressBookData> addressBookList=new ArrayList<>();
        try {
            Connection connection=this.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql_query);
            while (resultSet.next()){
                int ID=resultSet.getInt(9);
                String First_Name=resultSet.getString(1);
                String Last_Name=resultSet.getString(2);
                String Address=resultSet.getString(3);
                String City=resultSet.getString(4);
                String State=resultSet.getString(5);
                int Zip=resultSet.getInt(6);
               // int Phone_Number=resultSet.getInt(7);
                String Email_Address=resultSet.getString(8);
                System.out.println("-----------------------------");
                System.out.println("Id: "+ID);
                System.out.println("FirstName: "+First_Name);
                System.out.println("LastName: "+Last_Name);
                System.out.println("Adress: "+Address);
                System.out.println("City: "+City);
                System.out.println("State: "+State);
                System.out.println("Zip: "+Zip);
              //  System.out.println("PhoneNumber: "+Phone_Number);
                System.out.println("Email: "+Email_Address);

                AddressBookData addressBook=new AddressBookData(
                        resultSet.getInt(9),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        //resultSet.getInt(7),
                        resultSet.getString(8));

                addressBookList.add(addressBook);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return addressBookList;
    }

    public static void listdrivers(){
        Enumeration<Driver> driverlist=DriverManager.getDrivers();
        while(driverlist.hasMoreElements()){
            Driver driverClass=(Driver) driverlist.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }

}
