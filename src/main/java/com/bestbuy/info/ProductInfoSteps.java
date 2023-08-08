package com.bestbuy.info;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductInfoSteps {


    @Step("Creating Product with name :{0}, type:{1}, price :{2}, shipping:{3}, upc :{4}, description :{5}, manufacturer:{6}, model:{7},url:{8},image:{9}")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model,String url,String image) {
        ProductsPojo productsPojo = new ProductsPojo();

        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(productsPojo)
                .post()
                .then();

}

    @Step("Getting the Product information with Name:{0}")
    public HashMap<String, Object> getProductInfoByName (String name) {

        String s1 = "data.findAll{it.name = '";
        String s2 = "'}.get(0)";

        return SerenityRest.given()
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);


    }





    @Step("Updating Product information with productId :{0}, name :{1}, type:{2}, price :{3}, shipping:{4}, upc :{5}, description :{6}, manufacturer:{7}, model:{8},url:{9},image:{10}")
    public ValidatableResponse updateProduct(int productId, String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model,String url,String image  ) {

        ProductsPojo productsPojo = new ProductsPojo();

        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("productID", productId)
                .when()
                .body(productsPojo)
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();

    }



    @Step("Deleting Product information with ProductId: {0}")
    public ValidatableResponse deleteProduct(int productId) {
        return SerenityRest.given()
                .pathParam("productID", productId)
                .when()
                .delete(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }



    @Step("Getting Product information with productId : {0}")
    public ValidatableResponse getProductById(int productId){
        return SerenityRest.given()
                .pathParam("productID", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }

}
