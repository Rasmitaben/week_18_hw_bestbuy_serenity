package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCRUDTestWithSteps extends TestBase {

    static String name = "PrimUser" + TestUtils.getRandomValue();
    static String type = "textBox" + TestUtils.getRandomValue();
    static String address = "B1";
    static String address2 = "wood street";
    static String city = "Harrow";
    static String state = "London";
    static String zip = "546734";
    static Double lat = 12.875;
    static Double lng = 34.56748;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;


    @Steps
    StoreSteps storeSteps;

    @WithTags({
            @WithTag("storefeature:SMOKE"),
            @WithTag("storefeature:SANITY"),
            @WithTag("storefeature:REGRESSION")
    })
    @Title("This will create a new Store")
    @Test
    public void test001(){
        ValidatableResponse response = storeSteps.createNewStore(name,type,address,address2,city,state,zip,lat,lng, hours);
        storeId = response.log().all().extract().path("id");
        response.log().all().statusCode(201);

    }
    @WithTags({
            @WithTag("storefeature:SANITY"),
            @WithTag("storefeature:REGRESSION")
    })

    @Title("Verify if the store was added to the application  ")
    @Test
    public void test002(){
        HashMap<String, Object> storeMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap, hasValue(storeId));
        System.out.println(storeMap);

    }
    @WithTags({
            @WithTag("storefeature:SMOKE"),
            @WithTag("storefeature:REGRESSION")
    })

    @Title("Update the store information and verify the updated information")
    @Test
    public void test003(){
        name = name + "_updated";
        storeSteps.updateStoreById(storeId,name,type,address,address2,city,state,zip,lat,lng, hours);
        HashMap<String,Object> storeMap = storeSteps.getStoreInfoById(storeId);
        Assert.assertThat(storeMap,hasValue(storeId));

    }
    @WithTag("storefeature:REGRESSION")

    @Title("Deleting store information and verify if the store is deleted")
    @Test
    public void test004(){
        storeSteps.deleteStore(storeId)
                .statusCode(200);
    }


}
