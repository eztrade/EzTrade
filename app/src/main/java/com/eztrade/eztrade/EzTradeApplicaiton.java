package com.eztrade.eztrade;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by ayog on 1/14/15.
 */
public class EzTradeApplicaiton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        String APPLICATION_ID = "jeJxDYfCzv07KU4LrVXq144duIvUVwIH6eVlwXgx";
        String CLIENT_KEY = "MHMTjxXBRxf2Sb8bJSYrSrvosirsG6tn50BVnHOJ";
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("sellerName", "Nitin");
        testObject.put("SellerEmail", "nitinreddyb@gmail.com");
        testObject.saveInBackground();

    }
}
