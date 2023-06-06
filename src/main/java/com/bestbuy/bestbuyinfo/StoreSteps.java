package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoreSteps {

    @Step("Creating new store with name : {0}, type : {1}, address : {2}, address : {3}, city : {4}, state : {5}, zip :{6}, lat :{7},lng : {8}, hours : {9} ")
    public ValidatableResponse createNewStore(String name, String type, String address, String address2, String city, String state,
                                              String zip, double lat, double lng, String hours) {
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip, lat, lng, hours);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                //.contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post(EndPoints.CREATE_STORE)
                .then();

    }

    @Step("Getting the store information with single storeID : {0}")
    public HashMap<String, Object> getStoreInfoById(int storeId) {

        HashMap<String, Object> storeMap = SerenityRest.given().log().all()
                .when()
                .pathParam("storeID", storeId)
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("");
        return storeMap;
    }
    @Step("Updating the store with storeId : {0}, name : {1}, type : {2}, address : {3}, address : {4}, city : {5}, state : {6}, zip :{7}, lat :{8},lng : {9}, hours : {10} ")

    public ValidatableResponse updateStoreById(int storeId, String name, String type, String address, String address2, String city, String state,
                                           String zip, double lat, double lng, String hours) {
        StorePojo storePojo = StorePojo.getStorePojo(name, type, address, address2, city, state, zip, lat, lng, hours);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("storeID", storeId)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();
    }
    @Step("Deleting the store information with storeId : {0}")

    public ValidatableResponse deleteStore(int storeId){
        return SerenityRest.given()
                .pathParam("storeID", storeId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all().statusCode(200);
    }

}

