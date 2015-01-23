package com.eztrade.eztrade.Activities;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.eztrade.eztrade.dataModel.Seller;
import com.google.common.collect.Lists;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import eztrade.eztrade.com.eztrade.R;

public class ParseTestActivity extends ListActivity {

    private List<Seller> sellerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_test);

        sellerList = Lists.newArrayList();
        ArrayAdapter<Seller> adapter = new ArrayAdapter<Seller>(this, R.layout.list_item_layout,sellerList);
        setListAdapter(adapter);
        
        refreshSellerList();
    }

    private void refreshSellerList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Seller");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e== null) {
                    sellerList.clear();
                    for (ParseObject seller : parseObjects) {
                        Seller s = new Seller(seller.getObjectId(),
                                seller.getString("name"),
                                seller.getString("email"),
                                seller.getString("phone"),
                                seller.getString("zip"));

                        sellerList.add(s);
                    }
                    ((ArrayAdapter<Seller>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getApplication().getPackageCodePath(),"Error "+ e );
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_test, menu);
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
