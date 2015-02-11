package com.eztrade.eztrade.Activities;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import eztrade.eztrade.com.eztrade.R;

public class BuyQueryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_query);
        EditText keyword = (EditText) findViewById(R.id.keywordET);
        EditText zip = (EditText) findViewById(R.id.zipET);
        Button searchBtn = (Button) findViewById(R.id.searchBtn);   
        ListView resultsList = (ListView) findViewById(R.id.resultsList);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            objectsWereRetrievedSuccessfully(objects);
                        } else {
                            //objectRetrievalFailed();
                        }
                    }
                });
            }
        });

    }

    private void objectsWereRetrievedSuccessfully(List<ParseObject> objects) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_buy_query, menu);
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
