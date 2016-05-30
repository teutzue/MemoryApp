package com.example.yoyo.databaseapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Display_Info extends AppCompatActivity
{
  static final int  REQUEST_IMAGE_CAPTURE = 2;
    private  Bitmap imageBitmap;
    private TextView textTopic;
    private ImageView imgPreview;
    private TextView textDescription;
    private ImageView image;
    private Button button;
    private Button take_a_pic_button;
    private DBHelper mydb;
    private int value;// kak taka raboti
    private int id_to_delete_update;
    PackageManager packageManager; // determines if a phone got a camera

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__info);

        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        take_a_pic_button = (Button)findViewById(R.id.buttonTakeAPhoto);
        textTopic =(TextView) findViewById(R.id.editTextTopic);
        textDescription = (TextView) findViewById((R.id.editTextDescription));
        image = (ImageView)findViewById(R.id.imageView);
        button =(Button)findViewById(R.id.button);
        packageManager = this.getPackageManager();//we get the PacketManager
        // if device support camera?



        mydb = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            value = extras.getInt("id");
            Toast.makeText(Display_Info.this, "The vallues passed is "+value, Toast.LENGTH_SHORT).show();
            if(value>0)
            {


                id_to_delete_update = value;
             //   Toast.makeText(Display_Info.this, "The value to be updated is "+id_to_delete_update, Toast.LENGTH_SHORT).show();
                Log.d("My Custom Tag",  Integer.toString(id_to_delete_update));
                button.setVisibility(View.INVISIBLE);
                take_a_pic_button.setVisibility(View.INVISIBLE);
               // Toast.makeText(Display_Info.this,id_to_delete_update, Toast.LENGTH_LONG).show();
                Notes note = mydb.getData(id_to_delete_update);
                if(note!=null)
                {
                    textTopic.setText(note.getTopic());
                    textTopic.setFocusable(false);
                    textTopic.setClickable(false);
                    textDescription.setText(note.getDescription());
                    textDescription.setFocusable(false);
                    textDescription.setClickable(false);
                    Bitmap bit = mydb.getNotesPicture(note.getImage());
                    image.setImageBitmap(bit);
                }else
                {
                    Toast.makeText(Display_Info.this, "Returned null from databse", Toast.LENGTH_SHORT).show();
                }

            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if(id_to_delete_update>0)
        {
           getMenuInflater().inflate(R.menu.display_note,menu);
        }else
        {
            //getMenuInflater().inflate(R.menu.main_menu,menu);
        }
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.Edit_Contact:
                button.setVisibility(View.VISIBLE);
                take_a_pic_button.setVisibility(View.VISIBLE);
                textTopic.setEnabled(true);
                textTopic.setFocusableInTouchMode(true);
                textTopic.setClickable(true);

                textDescription.setEnabled(true);
                textDescription.setFocusableInTouchMode(true);
                textDescription.setClickable(true);



                return true;


            case R.id.Delete_Contact:



                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                mydb.deleteById(id_to_delete_update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


public void run(View view)
{
    if(value>0)
    {



        if (textTopic.getText().toString().length()>0 && textDescription.getText().toString().length()>0)
        {
            String topic  =  textTopic.getText().toString();
            String description = (String) textDescription.getText().toString();

           Notes note = mydb.getData(id_to_delete_update);
//           String stringpath = note.getImage();
//            Bitmap bit = mydb.getNotesPicture(note.getImage());
//            image.setImageBitmap(bit);
//
//            if(bit != null)
//            {
//                note.setImage(stringpath);
//
//            }
            if(imageBitmap != null )
            {
                note.setTopic(topic);
                note.setDescription(description);
                Bitmap map_to_be_saved = imageBitmap;
                mydb.updateContactwithBitmap(note,map_to_be_saved);
            }else
            {
                note.setTopic(topic);
                note.setDescription(description);
                mydb.updateContact(note);
            }

            Toast.makeText(Display_Info.this, " Updated "+topic, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
//            take_a_pic_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//
//
//                }
//            });


            finish();
        }else
        {
            Toast.makeText(Display_Info.this, "Please provide all values!!! ", Toast.LENGTH_SHORT).show();
        }
        //only visible button if we are in edit option

    }else
    {
        //should be saved
//        if((textTopic != null || !textTopic.getText().equals(""))&&(((textDescription != null || !textDescription.getText().equals("")))))
//        {
//
//        String topic  =  textTopic.getText().toString();
//        String description = (String) textDescription.getText().toString();
//            Toast.makeText(Display_Info.this, "Good job "+topic, Toast.LENGTH_SHORT).show();
//
//        }else
//        {
//            Toast.makeText(Display_Info.this, "Please enter all values!!!", Toast.LENGTH_SHORT).show();
//        }

        if (textTopic.getText().toString().length()>0 && textDescription.getText().toString().length()>0)
        {
            String topic  =  textTopic.getText().toString();
            String description = (String) textDescription.getText().toString();
            Notes note = new Notes();
            note.setTopic(topic);
            note.setDescription(description);
            //here Camera Intent wjich takes photos


            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
            {

                //yes
                take_a_pic_button.setVisibility(View.VISIBLE);
//                take_a_pic_button.setOnClickListener(new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        dispatchTakePictureIntent();
//                    }
//                });
             if( imageBitmap != null)
             {
                 mydb.insertNote(note,imageBitmap);
             }else
             {
                 mydb.insertNoteWithoutBitmap(note);
             }

                Log.i("camera", "This device has camera!");
            }else
            {
                //no
                take_a_pic_button.setVisibility(View.INVISIBLE);
            mydb.insertNoteWithoutBitmap(note);// for now I insert only without a picture
            Toast.makeText(Display_Info.this, " Note added "+topic, Toast.LENGTH_SHORT).show();
                Log.i("camera", "This device has no camera!");
            }

//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(intent);

            finish();
        }else
        {

            Toast.makeText(Display_Info.this, "Please provide all values!!! ", Toast.LENGTH_SHORT).show();
        }
    }
}

    @Override
    public void finish()
    {
        setResult(MainActivity.CHECK_RIGHT_INTENT);
        super.finish();
    }

    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void TakeAPhoto(View v){
        dispatchTakePictureIntent();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
       // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgPreview.setImageBitmap(imageBitmap);

            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);

            // CALL THIS METHOD TO GET THE ACTUAL PATH

            File finalFile = new File(getRealPathFromURI(tempUri));


            if (finalFile.exists()) {
                if (finalFile.delete()) {
                    Toast.makeText(Display_Info.this, "Gallery photo deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Display_Info.this, "Gallery photo NOT deleted", Toast.LENGTH_SHORT).show();
                }
            }
       //     System.out.println(mImageCaptureUri);


        }
    }




    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
