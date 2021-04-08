package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
public class AddressBookTest {

    AddressBook addressBook;

    @Before
    public void setup() {
        addressBook = new AddressBook();
    }


    @Test
    public void given_select_statement_should_return_list() throws SQLException {
        List<AddressBookData> addressBookList = addressBook.readData();
        Assert.assertEquals(8, addressBookList.size());
    }

    @Test
    public void update_table_should_return_true() throws SQLException {
        String state = "karnataka";
        int id = 3;
        addressBook.updateDataFromDatabase(state, id);
        List<AddressBookData> addressBookList = addressBook.readData();
        Assert.assertEquals(10, addressBookList.size());
    }
    @Test
    public void update_full_contact_details() throws SQLException {
        String lastname = "patil";
        String address = "vidyanagar";
        String city = "lakhnow";
        String state = "UP";
        int zip = 400091;
        int phonenumber = 781154415;
        String email = "patil@yahoo.in";
        String firstname = "Mahesh";

        addressBook.updateContactDetailsaddressbook(lastname, address, city, state, zip, phonenumber, email, firstname);
    }


    @Test
    public void return_values_for_a_particular_date_range() throws SQLException {
        String date="2019-01-01";
        List<AddressBookData> addressBookList=addressBook.returnValueInDataBaseForDateRange(date);
        Assert.assertEquals(6,addressBookList.size());
    }

    @Test
    public void count_of_contacts_in_a_city() throws SQLException {
        String city="kolhapur";
        String result=addressBook.countContactbyCityFromDtaBase(city);
        Assert.assertEquals("2",result);
    }

    @Test
    public void count_of_contacts_in_a_state() throws SQLException {
        String state="Maharashtra";
        String result=addressBook.countContactbyStateFromDtaBase(state);
        Assert.assertEquals("5",result);
    }

    @Test
    public void insert_into_address_book() throws SQLException {
        String firstname="Tejas";
        String lastname="Dev";
        String address="office";
        String city="Kolhapur";
        String state="new york";
        int zip=908818;
        String phonenumber="266155211";
        String email="tejascaptan@bl.in";
        String type="Friend";
        String addressbook="contact7";
        String entry_date="2020-11-01";

        addressBook.insertNewContactFromDatabase(firstname,lastname,address,city,state,zip,phonenumber,email,type,addressbook,entry_date);
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(11,addressBookList.size());
    }

    @Test
    public void insert_using_threads() throws SQLException {

        List<AddressBookData> addressBookDataList=new ArrayList<>();
        addressBookDataList.add(new AddressBookData("Amit","patil","karad","Satara","India",434321,"934567890","patilamit@gmail.com","Family", "contact8",Date.valueOf("2020-03-18")));
        addressBookDataList.add(new AddressBookData("ravi","patil","waghaland","Pune","Maharastra",412207,"885542911","ravi@gmail.com","Friend", "Contact9",Date.valueOf("2021-05-11")));
        Instant start=Instant.now();
        addressBook.addEmployeeToPayrollWithThreadsToDataBase(addressBookDataList);
        Instant end=Instant.now();

        System.out.println("Duration of non thread process is: "+ Duration.between(start,end));

    }
}


