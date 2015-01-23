package com.eztrade.eztrade.Activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.eztrade.eztrade.EzTradeApplicaiton;
import com.eztrade.eztrade.dataModel.Sale;

import eztrade.eztrade.com.eztrade.R;

public class ReviewSellActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_sell);
        final TextView titleTV = (TextView) findViewById(R.id.titleTV);
        final TextView descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        final TextView emailTV = (TextView) findViewById(R.id.emailTV);
        final TextView phoneTV = (TextView) findViewById(R.id.phoneTV);

        Sale currentSale = EzTradeApplicaiton.getApplication().getCurrentSale();

        titleTV.setText(currentSale.title);
        descriptionTV.setText(currentSale.description);
        emailTV.setText(currentSale.email);
        phoneTV.setText(currentSale.phone);
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
