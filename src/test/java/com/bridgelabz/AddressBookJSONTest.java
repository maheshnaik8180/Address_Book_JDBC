package com.bridgelabz;


import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;


public class AddressBookJSONTest {
    @Before
    public void setup(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=4000;
    }
    public AddressBookData[] getContactList() {
        Response response = RestAssured.get("/contacts");
        System.out.println("Data in json is: \n" + response.asString());
        AddressBookData[] restAssureBookData = new Gson().fromJson(response.asString(), AddressBookData[].class);
        return restAssureBookData;
    }

    public Response addContactToJsonServer(AddressBookData restAssureBookData){
        String contacts=new Gson().toJson(restAssureBookData);
        RequestSpecification requestSpecification=RestAssured.given();
        requestSpecification.header("Content-Type","application/json");
        requestSpecification.body(contacts);
        return requestSpecification.post("/contacts");
    }

    @Test
    public void given_contact_Date_Retrieve_ServerData() {
        AddressBookData[] restAssureBookData=getContactList();
        System.out.println(restAssureBookData);
        Assert.assertEquals(4,restAssureBookData.length);

    }

    @Test
    public void whenNewContact_isAdded_Sholdreturn201ResponseCode(){
        AddressBookData[] jsonServerBookData=getContactList();

        AddressBookData jsonServerBookData1=new AddressBookData("santosh","naik","nesari","gad","maharashtra",2343234,"998292981","santosh@gmail.com","family","contact1", Date.valueOf("23-4-2021"));
        Response response=addContactToJsonServer(jsonServerBookData1);
        int statusCode= response.statusCode();

        Assert.assertEquals(201,statusCode);
    }


}
