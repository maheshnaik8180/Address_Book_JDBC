package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AddressBookTest {
    @Test
    public void given_select_statement_should_return_list(){
        AddressBook addressBook=new AddressBook();
        List<AddressBookData> addressBookList=addressBook.readData();
        Assert.assertEquals(6,addressBookList.size());
    }
}
