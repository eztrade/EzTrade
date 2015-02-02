package com.eztrade.eztrade.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eztrade.eztrade.EzTradeApplicaiton;
import com.eztrade.eztrade.dataModel.Post;
import com.parse.ParseFile;
import com.parse.ParseObject;

import eztrade.eztrade.com.eztrade.R;

public class ReviewSellActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_sell);
        final ImageView productImageView = (ImageView)findViewById(R.id.productImageView);
        final TextView titleTV = (TextView) findViewById(R.id.titleTV);
        final TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        final TextView emailTV = (TextView) findViewById(R.id.emailTV);
        final TextView phoneTV = (TextView) findViewById(R.id.phoneTV);
        final Button postButton = (Button) findViewById(R.id.postButton);

        final Post currentPost = EzTradeApplicaiton.getApplication().getCurrentPost();

        titleTV.setText(currentPost.title);
        descriptionTV.setText(currentPost.description);
        emailTV.setText(currentPost.email);
        phoneTV.setText(currentPost.phone);
        productImageView.setImageDrawable(currentPost.image);



        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCurrentPost(currentPost);
            }
        });
    }

    private void sendCurrentPost(Post post)
    {
        ParseObject parsePostObject = new ParseObject("Post");
        parsePostObject.put("title",post.title);
        parsePostObject.put("description",post.description);
        parsePostObject.put("zip",post.zip);
        parsePostObject.put("email",post.email);
        parsePostObject.put("phone",post.phone);

        ParseFile parseFile = new ParseFile("postImage",post.image.toString().getBytes());
        parsePostObject.put("image",parseFile);

        parsePostObject.saveInBackground();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_review_sell, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
