package com.bridgelabz;
public class AddressBookData {
    private String FirstName;
    private String LastName;
    private String Address;
    private String City;
    private String State;
    private int Zip;
    private int Phone_Number;
    private String Email_Address;

    public AddressBookData(int id,String firstName, String lastName, String address, String city , String state,int zip, String email){
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Address = address;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        //this.Phone_Number = phoneNumber;
        this.Email_Address = email;
    }

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


    public int getPhoneNumber() {
        return Phone_Number;
    }


    public void setPhoneNumber(int phoneNumber) {
        this.Phone_Number = phoneNumber;
    }


    public String getEmail() {
        return Email_Address;
    }


    public void setEmail(String email) {
        this.Email_Address = email;
    }
}
