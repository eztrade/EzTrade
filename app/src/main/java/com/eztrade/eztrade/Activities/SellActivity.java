package com.eztrade.eztrade.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.provider.MediaStore.Images.Media;



import com.eztrade.eztrade.EzTradeApplicaiton;
import com.eztrade.eztrade.dataModel.Post;

import eztrade.eztrade.com.eztrade.R;


public class SellActivity extends Activity {
    ImageView productImageView;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap bitmap;

    String selectedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Button reviewButton = (Button)findViewById(R.id.reviewButton);
        productImageView = (ImageView)findViewById(R.id.productImageView);

        final EditText titleET = (EditText) findViewById(R.id.titleET);
        final EditText descriptionET = (EditText) findViewById(R.id.descriptionET);
        final EditText zipET = (EditText) findViewById(R.id.zipET);
        final EditText emailET = (EditText) findViewById(R.id.emailET);
        final EditText phoneET = (EditText) findViewById(R.id.phoneET);

        reviewButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSale(titleET, descriptionET, zipET,  emailET, phoneET);
                startActivity(new Intent(SellActivity.this, ReviewSellActivity.class));
            }
        });

        productImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startDialog();
            }

        });
    }

    private void saveSale(EditText titleET, EditText descriptionET, EditText zipET, EditText emailET, EditText phoneET) {
        Post currentPost = new Post();
        currentPost.title = titleET.getText().toString();
        currentPost.email = emailET.getText().toString();
        currentPost.description = descriptionET.getText().toString();
        currentPost.zip = zipET.getText().toString();
        currentPost.phone = phoneET.getText().toString();
        currentPost.image = productImageView.getDrawable();
        EzTradeApplicaiton.getApplication().setCurrentPost(currentPost);
    }

    private void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        pictureActionIntent = new Intent(
                                Intent.ACTION_GET_CONTENT, null);
                        pictureActionIntent.setType("image/*");
                        pictureActionIntent.putExtra("return-data", true);
                        startActivityForResult(pictureActionIntent,
                                GALLERY_PICTURE);
                    }
                });

        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        pictureActionIntent = new Intent(
                                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(pictureActionIntent,
                                CAMERA_REQUEST);

                    }
                });
        myAlertDialog.show();
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICTURE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    // our BitmapDrawable for the thumbnail
                    BitmapDrawable bmpDrawable = null;
                    // try to retrieve the image using the data from the intent
                    Cursor cursor = getContentResolver().query(data.getData(),
                            null, null, null, null);
                    if (cursor != null) {

                        cursor.moveToFirst();

                        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        String fileSrc = cursor.getString(idx);
                        bitmap = BitmapFactory.decodeFile(fileSrc); // load
                        // preview
                        // image
                        bitmap = Bitmap.createScaledBitmap(bitmap,
                                100, 100, false);
                        // bmpDrawable = new BitmapDrawable(bitmapPreview);
                        productImageView.setImageBitmap(bitmap);
                    } else {

                        bmpDrawable = new BitmapDrawable(getResources(), data
                                .getData().getPath());
                        productImageView.setImageDrawable(bmpDrawable);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Cancelled",
                            Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                if (data.hasExtra("data")) {

                    // retrieve the bitmap from the intent
                    bitmap = (Bitmap) data.getExtras().get("data");


                    Cursor cursor = getContentResolver()
                            .query(Media.EXTERNAL_CONTENT_URI,
                                    new String[] {
                                            Media.DATA,
                                            Media.DATE_ADDED,
                                            MediaStore.Images.ImageColumns.ORIENTATION },
                                    Media.DATE_ADDED, null, "date_added ASC");
                    if (cursor != null && cursor.moveToFirst()) {
                        do {
                            Uri uri = Uri.parse(cursor.getString(cursor
                                    .getColumnIndex(Media.DATA)));
                            selectedImagePath = uri.toString();
                        } while (cursor.moveToNext());
                        cursor.close();
                    }

                    Log.e("path of the image from camera ====> ",
                            selectedImagePath);


                    bitmap = Bitmap.createScaledBitmap(bitmap, 100,
                            100, false);
                    // update the image view with the bitmap
                    productImageView.setImageBitmap(bitmap);
                } else if (data.getExtras() == null) {

                    Toast.makeText(getApplicationContext(),
                            "No extras to retrieve!", Toast.LENGTH_SHORT)
                            .show();

                    BitmapDrawable thumbnail = new BitmapDrawable(
                            getResources(), data.getData().getPath());

                    // update the image view with the newly created drawable
                    productImageView.setImageDrawable(thumbnail);

                }

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
