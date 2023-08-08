package com.bestbuy.productTest;

import com.bestbuy.info.ProductInfoSteps;
import com.bestbuy.testbase.ProductTestBase;
import com.bestbuy.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCurdTest extends ProductTestBase {


    static String name = "Tara"   + TestUtils.getRandomValue();
    static String type = "BigBox1"+ TestUtils.getRandomValue();
    static int  price =  20 ;
    static int shipping = 30;
    static String upc = "ZS" ;
    static String description = "Best Product";
    static String manufacturer = "Zullu";
    static String model= "Dell";
    static String url= "http://www.bestbuy.com/site/";
    static String image = "http://img.bbystatic.com/BestBuy_US/images/products/";
    static int productId;

    @Steps
    ProductInfoSteps productInfoSteps;

    @Title("This will create a new product ")
    @Test
    public void test(){
        productInfoSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(201) ;

    }

        @Title(" Verify  if the product was added to the application")
        @Test

        public void test002() {

            HashMap<String, Object> storeMap = productInfoSteps.getProductInfoByName(name);
            Assert.assertThat(storeMap,hasValue(name));
           productId = (int) storeMap.get("id");


        }


    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        productInfoSteps.updateProduct(productId, name,type,price,shipping,upc,description,manufacturer,model,url,image).statusCode(200);
        HashMap<String, Object> storeMap = productInfoSteps.getProductInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));

    }


    @Title("Delete the Product and verify if the product is deleted!")
    @Test
    public void test004() {

        productInfoSteps.deleteProduct(productId ).statusCode(200);
        productInfoSteps.getProductById(productId).statusCode(404);

    }



}
