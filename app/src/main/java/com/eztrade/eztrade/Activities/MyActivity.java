package com.eztrade.eztrade.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ui.ParseLoginBuilder;

import eztrade.eztrade.com.eztrade.R;


public class MyActivity extends Activity {
    private static final int LOGIN_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button buyButton = (Button)findViewById(R.id.buyButton);
        Button sellButton = (Button)findViewById(R.id.sellButton);
        Button login_button = (Button)findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseLoginBuilder loginBuilder = new ParseLoginBuilder(
                        MyActivity.this);
                startActivityForResult(loginBuilder.build(), LOGIN_REQUEST);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyIntent = new Intent(MyActivity.this, BuyQueryActivity.class);
                startActivity(buyIntent);
            }
        });

        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellIntent = new Intent(MyActivity.this, SellActivity.class);
                startActivity(sellIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
