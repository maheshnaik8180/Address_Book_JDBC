package com.bridgelabz;

import java.sql.Date;

public class AddressBookData {
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private int Zip;
    private String Phone_Number;
    private String Email_Address;
    private String type;
    private String AddressBook;
    private Date entry_date;


    public AddressBookData(String firstName, String lastName, String address, String city , String state,int zip, String phoneNumber, String email,int ID,String type, String AddressBook,Date entry_date){

        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.Phone_Number = phoneNumber;
        this.Email_Address = email;
        this.type = type;
        this.AddressBook = AddressBook;
        this.entry_date = entry_date;
    }





   // public AddressBookData(int anInt, String string, String string1, String string2, String string3, String string4, int anInt1, String string5, String string6, String string7, String string8, Date date) {
     //   }

    public String getFirstName() {
        return FirstName;
    }


    public void setfirstName(String firstName) {
        this.FirstName = firstName;
    }


    public String getLastName() {
        return LastName;
    }


    public void setLastName(String lastName) {
        this.LastName = lastName;
    }


    public String getAddress() {
        return Address;
    }


    public void setAddress(String address) {
        this.Address = address;
    }


    public String getCity() {
        return City;
    }


    public void setCity(String city) {
        this.City = city;
    }


    public String getState() {
        return State;
    }


    public void setState(String state) {
        this.State = state;
    }


    public int getZip() {
        return Zip;
    }


    public void setZip(int zip) {
        this.Zip = zip;
    }


    public String getPhoneNumber() {
        return Phone_Number;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.Phone_Number = phoneNumber;
    }


    public String getEmail() {
        return Email_Address;
    }


    public void setEmail(String email) {
        this.Email_Address = email;
    }

    public Date getDate() {
        return entry_date;
    }

    public void setDate(Date date) {
        this.entry_date = entry_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressBook() {
        return AddressBook;
    }

    public void setAddressBook(String addressBook) {
        AddressBook = addressBook;
    }



    @Override
    public String toString() {
        return "AddressBookData{" +
                "firstName='" + FirstName + '\'' +
                ", lastName='" + LastName + '\'' +
                ", address='" + Address + '\'' +
                ", city='" + City + '\'' +
                ", state='" + State + '\'' +
                ", zip=" + Zip +
                ", phoneNumber=" + Phone_Number +
                ", email='" + Email_Address + '\'' +
                ", date='" + entry_date + '\'' +
                '}';
    }
}
