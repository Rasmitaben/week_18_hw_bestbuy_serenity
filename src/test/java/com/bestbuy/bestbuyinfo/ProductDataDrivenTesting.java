package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

//@Concurrent(threads = "4x")
@UseTestDataFrom("src/test/java/resources/testdata/productinfo.csv")

@RunWith(SerenityParameterizedRunner.class)


public class ProductDataDrivenTesting extends TestBase {
    private String name;
    private String type;
    private Double price;
    private String upc;
    private Double shipping;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;

    @Steps
    ProductSteps productSteps;
    @Title("Data driven test for adding multiple products to the application")
    @Test
    public void createMutipleProducts(){
      productSteps.createANewProduct(name,type,price,upc,shipping,description,manufacturer,model,url,image).statusCode(201);

    }
}
