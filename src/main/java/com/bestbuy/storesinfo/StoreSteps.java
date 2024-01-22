package com.bestbuy.storesinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class StoreSteps {

    @Step("Creating stores with Name : {0}, Type : {1}, Address : {2}, Address2 : {3}, City : {4}, State : {5}, Zip")
    public ValidatableResponse createStore(String name, String type, String address, String address2, String city, String state, String zip) {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post()
                .then().log().all();


    }

    @Step
    public String getStoreInfoByName(int id) {

        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_ALL_STORES_DATA)
                .then().log().all().statusCode(200)
                .extract().path( "name" );

    }

    @Step
    public ValidatableResponse updateStore(int id ,String name,String type,String address, String address2,String city,String state,String zip) {

        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id",id)
                .body(storePojo)
                .when()
                .put(EndPoints.UPDATE_STORES_DATA_BY_ID)
                .then().log().all();
    }

    public ValidatableResponse  deleteStore(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .delete(EndPoints.DELETE_STORES_DATA_BY_ID)
                .then().log().all();
    }

    public ValidatableResponse getStoreId(int id){
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_DATA_BY_ID)
                .then();
    }
}