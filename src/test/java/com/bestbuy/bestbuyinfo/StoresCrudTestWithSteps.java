package com.bestbuy.bestbuyinfo;

import com.bestbuy.storesinfo.StoreSteps;
import com.bestbuy.testbase.TestBaseStores;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(SerenityRunner.class)
public class StoresCrudTestWithSteps extends TestBaseStores {

    static    String name = "Ram";
    static String type= "Bhagwan";
    static String address = "Ayodhya Temple";
    static String address2 = "Nagari";
    static String city = "Ayodhya";
    static String state = "UttarPradesh";
    static String zip= "382345";

    @Steps
    StoreSteps steps;
    static int id;

    @Title("This will create new stores")
    @Test()
public void test001(){
        ValidatableResponse response = steps.createStore(name,type,address,address2,city,state,zip);
response.statusCode(201);
       id = response.extract().jsonPath().getInt("id");
        System.out.println(id);
}
@Title("This will verify that store is added to application")
@Test
public void test002(){
    String storelist = steps.getStoreInfoByName(id);

    Assert.assertEquals(storelist,name);
 }
 @Title("This will update and verify the store application ")
 @Test
    public void test003(){
        name = name + "_updated";



HashMap<Object,Object> storeMap = new HashMap<>();
steps.updateStore(id,name,type,address,address2,city,state,zip).statusCode(200);
String storesMap = steps.getStoreInfoByName(id);
     Assert.assertEquals(name,storesMap);
 }

 @Title("This will delete the store and verify that store is deleted")
 @Test
 public void test004(){
       steps.deleteStore(id).statusCode(200);
         steps.getStoreId(id).statusCode(404);
 }

}
