package com.eztrade.eztrade;

import android.app.Application;

import com.eztrade.eztrade.dataModel.Post;
import com.parse.Parse;

/**
 * Created by ayog on 1/14/15.
 */
public class EzTradeApplicaiton extends Application {

    private Post currentPost;
    private static EzTradeApplicaiton instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        String APPLICATION_ID = "jeJxDYfCzv07KU4LrVXq144duIvUVwIH6eVlwXgx";
        String CLIENT_KEY = "MHMTjxXBRxf2Sb8bJSYrSrvosirsG6tn50BVnHOJ";
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("sellerName", "Nitin");
//        testObject.put("SellerEmail", "nitinreddyb@gmail.com");
//        testObject.saveInBackground();

    }

    public static final EzTradeApplicaiton getApplication()
    {
        return instance;
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }
}
