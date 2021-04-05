package com.bridgelabz;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBook {
    private List<AddressBook> addressBookList;
    private Object insertNewCon;

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

    public void updateDataFromDatabase(String state, int id) throws SQLException {
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




    public void updateContactDetailsaddressbook(String lastname, String address, String city, String state, int zip, int phonenumber, String email, String firstname) throws SQLException {
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
    public List<AddressBookData> returnValueInDataBaseForDateRange(String date) throws SQLException {
        List<AddressBookData> addressBookList=new ArrayList<>();
        Connection connection=this.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement= connection.prepareStatement("Select * from addressbook where entry_date>=? ; ");
            preparedStatement.setDate(1,Date.valueOf(date));
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                AddressBookData addressBook=new AddressBookData(resultSet.getString(1)
                        ,resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
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

    public String countContactbyCityFromDtaBase(String city) throws SQLException {
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

    public String countContactbyStateFromDtaBase(String state) throws SQLException {
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
    public void insertNewContactFromDatabase(String firstname,String lastname,String address,String city,String state,int zip,String phonenumber,String email,String type, String addressbook, String entry_date) throws SQLException {
        Connection connection=this.getConnection();
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement=connection.prepareStatement("insert into addressbook(First_Name,Last_Name," +
                    "address,city,state,zip,phone_number,email_address,type,addressbook,entry_date) values(?,?,?,?,?,?,?,?,?,?,?); ");
            preparedStatement.setString(1,firstname);
            preparedStatement.setString(2,lastname);
            preparedStatement.setString(3,address);
            preparedStatement.setString(4,city);
            preparedStatement.setString(5,state);
            preparedStatement.setInt(6,zip);
            preparedStatement.setString(7,phonenumber);
            preparedStatement.setString(8,email);
            preparedStatement.setString(9,type);
            preparedStatement.setString(10,addressbook);
            preparedStatement.setDate(11,Date.valueOf(entry_date));
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("name"+ firstname+ "success");
        }catch (SQLException throwables){
            throwables.printStackTrace();
            connection.rollback();
        }
    }
    public void addEmployeeToPayrollWithThreadsToDataBase(List<AddressBookData> addressBookList) {
        Map<Integer, Boolean> addressBook = new HashMap<>();
        addressBookList.forEach(addressBookData -> {
            Runnable task = () -> {
                addressBook.put(addressBookList.hashCode(),  false);
                System.out.println("Employee being added : " + Thread.currentThread().getName());
                try {
                    this.insertNewContactFromDatabase(addressBookData.getFirstName(), addressBookData.getLastName(), addressBookData.getAddress(), addressBookData.getCity(),
                            addressBookData.getState(), addressBookData.getZip(), addressBookData.getPhoneNumber(), addressBookData.getEmail(),addressBookData.getType(),addressBookData.getAddressBook(), String.valueOf((addressBookData.getDate())));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                addressBook.put(addressBookList.hashCode(), true);
                System.out.println("Employee added : " + Thread.currentThread().getName());
            };
            Thread thread = new Thread(task, addressBookData.getFirstName());
            thread.start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        while (addressBook.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("" + this.addressBookList);
    }

}
