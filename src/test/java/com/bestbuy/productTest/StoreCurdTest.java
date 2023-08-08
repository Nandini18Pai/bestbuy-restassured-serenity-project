package com.bestbuy.productTest;

import com.bestbuy.info.StoreInfoSteps;
import com.bestbuy.testbase.StoreTestBase;
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
public class StoreCurdTest extends StoreTestBase {

    static String name = "Chinatown1" + TestUtils.getRandomValue();
    static String type = "BigBox1"+ TestUtils.getRandomValue();
    static String address = "1354 lindsay avenue1";
    static String address2 = "Yankey Road1";
    static String city = "Kingston1";
    static String state = "ZD1";
    static String zip = "856354";
    static int lat = 44;
    static int lng = -85;
    static String hours = "Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9;";
    static int storeId;

    @Steps
    StoreInfoSteps storeInfoSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {

        storeInfoSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours).statusCode(201);

    }

    @Title("Verify if the store was added to the application")
    @Test
    public void test002() {

        HashMap<String, Object> storeMap = storeInfoSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap,hasValue(name));
        storeId = (int) storeMap.get("id");


    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {
        name = name + "_updated";
        storeInfoSteps.updateStore(storeId, name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);
        HashMap<String, Object> storeMap = storeInfoSteps.getStoreInfoByName(name);
        Assert.assertThat(storeMap, hasValue(name));

    }

    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {

        storeInfoSteps.deleteStore(storeId ).statusCode(200);
        storeInfoSteps.getStoreById(storeId).statusCode(404);

    }

}
