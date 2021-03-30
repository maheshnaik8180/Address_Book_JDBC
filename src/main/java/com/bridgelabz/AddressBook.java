package com.bridgelabz;
import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class AddressBook {
    private List<AddressBook> addressBookList;

    private Connection getConnection() throws SQLException {
        String jdbcurl="jdbc:mysql://localhost:3306/address_book?useSSL=false";
        String username="root";
        String  password="admin";
        Connection con;
        System.out.println("Connecting to database: "+jdbcurl);
        con= DriverManager.getConnection(jdbcurl,username,password);
        System.out.println("Connection successfull: "+con);
        return con;
    }

    public List<AddressBookData> readData() throws SQLException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();


        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from addressbook; ");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){


                AddressBookData addressBook=new AddressBookData(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getDate(12));

                addressBookList.add(addressBook);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }

    public void updateData(String state,int id) throws SQLException {
        Connection connection=this.getConnection();
        try {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement=connection.prepareStatement("Update addressbook set state=? where id=? ; ");
        preparedStatement.setString(1,state);
        preparedStatement.setInt(2,3);
        preparedStatement.executeUpdate();
        connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        }



    public void updateContactDetails(String lastname,String address,String city,String state,int zip,int phonenumber,String email,String firstname) throws SQLException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement=connection.prepareStatement("Update addressbook set Last_Name=?,address=?,city=?,state=?,zip=?,Phone_Number=?,Email_Address=? where First_Name=? ; ");
            preparedStatement.setString(1,lastname);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,city);
            preparedStatement.setString(4,state);
            preparedStatement.setInt(5,zip);
            preparedStatement.setInt(6,phonenumber);
            preparedStatement.setString(7,email);
            preparedStatement.setString(8,firstname);

            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }
    public List<AddressBookData> returnValuesForApaticularDateRange(String date) throws SQLException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from addressbook where entry_date>=? ; ");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                AddressBookData addressBook=new AddressBookData(resultSet.getString(1)
                        ,resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),
                        resultSet.getString(5),resultSet.getInt(6),resultSet.getString(7),
                        resultSet.getString(8),resultSet.getInt(9),resultSet.getString(10),resultSet.getString(11),resultSet.getDate(12));

                addressBookList.add(addressBook);
                connection.commit();
            }
            System.out.println(addressBookList.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            connection.rollback();
        }
        return addressBookList;
    }

    public String countofContactbyCity(String city) throws SQLException {
        Connection connection=this.getConnection();
        String result=null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("select count(*) from addressbook where city=? ; ");
            preparedStatement.setString(1,city);
            ResultSet resultSet=preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                result=resultSet.getString(1);
                System.out.println(resultSet.getString(1));
            }
            return result;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
        return result;
    }

    public String countByContactbyState(String state) throws SQLException {
        Connection connection=this.getConnection();
        String result=null;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("select count(*) from addressbook where state=? ; ");
            preparedStatement.setString(1,state);
            ResultSet resultSet=preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                result=resultSet.getString(1);
                System.out.println(resultSet.getString(1));
            }
            return result;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
        return result;

    }


}
