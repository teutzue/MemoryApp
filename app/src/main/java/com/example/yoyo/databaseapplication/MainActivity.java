package com.example.yoyo.databaseapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.location.LocationClient;//depricated

public class MainActivity extends AppCompatActivity implements   GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener  {





    //LOcation staff
    GoogleApiClient mLocationClient;
  // GridView gridview;
    static final int  REQUEST_IMAGE_CAPTURE = 2;
    private TextView hiddenText;
    private ListView obj;
    private ListCustomAdapter adapter;
   public static final int CHECK_RIGHT_INTENT = 1;  // The request code
 //   private TextView subject_display;
    private DBHelper mydb;
    ImageView view;
    private ArrayList<Notes> array_list;

 //   private static final String TAG = "THE OBJECT IS ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newview);
      //  FeedReaderContract cv = new FeedReaderContract();


        mydb = new DBHelper(this);
//        Drawable myDrawable = getResources().getDrawable(R.drawable.me);
//        Bitmap anImage  = ((BitmapDrawable) myDrawable).getBitmap();
//        Notes note  = new Notes();
//        note.setTopic("Yoanas first entry");
//        note.setDescription(" Dano se polu4i slava tebe gospodi ");
//        mydb.insertNote(note,anImage);

        hiddenText = (TextView) findViewById(R.id.hiddentext);
        obj = (ListView)findViewById(R.id.mainListView);
        array_list = mydb.getAllNotes();
//        Toast.makeText(MainActivity.this, array_list.size(), Toast.LENGTH_SHORT).show();
       // ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.simple_list_item_1,array_list);
     //   obj.setAdapter(arrayAdapter);
       // Notes[] notes = new Notes[array_list.size()];
       // notes = array_list.toArray(notes);
        adapter = new ListCustomAdapter(this,array_list,mydb);
      // obj.setAdapter(new ListCustomAdapter(this,notes,mydb));
        obj.setAdapter(adapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
               // Toast.makeText(MainActivity.this, "The position pressed is "+position, Toast.LENGTH_SHORT).show();
                int id_Search = position+1;//because of the way they are stored into the database

                Toast.makeText(MainActivity.this, "The position pressed is "+id_Search, Toast.LENGTH_SHORT).show();

                Bundle dataBundle = new Bundle();
             //   LinearLayout lay = obj.
//                String text_id  =  (String) hiddenText.getText();
//                int _id = Integer.parseInt(text_id);


               View v = getViewByPosition(position,obj);
                if(v != null)
                {

                }


               // dataBundle.putInt("id",id_Search);
                dataBundle.putInt("id",id_Search);
                Intent intent = new Intent(getApplicationContext(),Display_Info.class);

                intent.putExtras(dataBundle);
                startActivityForResult(intent, CHECK_RIGHT_INTENT);
            }
        });



     // view = (ImageView)findViewById(R.id.image);
      //  subject_display =(TextView) findViewById(R.id.text);

//       Drawable myDrawable = getResources().getDrawable(R.drawable.but);
//       Bitmap anImage  = ((BitmapDrawable) myDrawable).getBitmap();
//        Drawable newImage = getResources().getDrawable(R.drawable.me);
//        Bitmap newbitmapImage  = ((BitmapDrawable) newImage).getBitmap();
//      // Notes note = new Notes("Teo","Lets meet right over here");
//
//      DBHelper help = new DBHelper(getApplicationContext());
//        Notes yoana = new Notes();
//        yoana.setTopic("Yoana meet Teo");
//        yoana.setDescription("Important meeting tomoorow Tursday");
//        Notes yoanawithPic  = help.insertNote(yoana,newbitmapImage);
//        String path = yoanawithPic.getImage();
//        text.setText(path);
//        Toast.makeText(MainActivity.this, path, Toast.LENGTH_LONG).show();
//        Log.d(yoanawithPic.toString(),"BLA");
//
//
//
//
//        gridview = (GridView)findViewById(R.id.gridview);
//        gridview.setAdapter(new ImageAdapter(this));
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "The position is "+position, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        text = (TextView)findViewById(R.id.text);
//
////      //  DBHelper help2 = new DBHelper(getApplicationContext());
//      Notes insert = help.insertNote(note,anImage);
//      byte[] array = note.getImage();
//
//       Bitmap bitmapreturnedfromdatabase = getPhoto(array);
//       view.setImageBitmap(bitmapreturnedfromdatabase);
      ///////// byte[] newshit = getBytes(newbitmapImage);
//        insert.setImage(newshit);
//        help.updateContact(insert);
      ////////  int numberofpeople = help.numberOfRows();
      ////////  Toast.makeText(MainActivity.this, "NUMBER OF ROWS IS "+numberofpeople, Toast.LENGTH_SHORT).show();
     //  Notes numberone = help.getLastNote();
        //Notes numberone = help.getData(3);
      //  numberone.setImage(newshit);
     //   help.updateContact(numberone);
     //   Notes updated = help.getData(3);
     //  byte[] array = updated.getImage();
//
//       Bitmap bitmapreturnedfromdatabase = getPhoto(array);
//       view.setImageBitmap(bitmapreturnedfromdatabase);


//        if(numberone!=null) {
//            Toast.makeText(MainActivity.this, numberone.getTopic(), Toast.LENGTH_LONG).show();
//        }else
//        {
//            Toast.makeText(MainActivity.this, " Soory dude ", Toast.LENGTH_LONG).show();
//        }
      //  Notes numberonel = help.getData(1);
      //  byte array2[] = numberone.getImage();
      // Bitmap changed = getPhoto(array2);
       // view.setImageBitmap(changed);




//88888888888888888888888888888888888888888888888888888888888888888888888
       // text.setText(insert.toString());


//        int num = help.numberOfRows();
//        String numstring = Integer.toString(num);
//        text.setText(numstring);

//        int index = 1;
//        text.setText(help.getData(index).toString());

//     boolean isit =   help.deleteNoteByObject(insert);
//      if(isit)
//      {
//          Toast.makeText(MainActivity.this, "Yep we deleted a contact", Toast.LENGTH_LONG).show();
//      }

//       // help.onUpgrade(help2,1,2);
//        String result = "";
//        ArrayList<Notes> ar = new ArrayList<>();
//        ar = help.getAllNotes();
//        for (int i=0;i<ar.size();i++)
//        {
//          //  if(ar.get(i)!=null)
//           // {
//                Notes notet = ar.get(i);
//                result += notet.toString() + "\n";
//          //  }else
//            {
//                Toast.makeText(MainActivity.this, "Opps I found empty index at "+i, Toast.LENGTH_SHORT).show();
//            }
//        }
//        text.setText(result);


       // System.out.println(" THE OBJECT IS  "+insert);
        //Log.d(TAG,insert.toString());
        //Toast.makeText(MainActivity.this, "The object is "+note.toString(), Toast.LENGTH_SHORT).show();
    }



    // from byte array to Bitmap
//    public  Bitmap getPhoto(byte[] image)
//    {
//        return BitmapFactory.decodeByteArray(image, 0, image.length);
//    }
//
//    public  byte[] getBytes(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
//        return stream.toByteArray();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CHECK_RIGHT_INTENT)
        {
            //if(resultCode == RESULT_OK)
            //{
                //code here populates again the ListView
               // obj = (ListView) findViewById(R.id.mainListView);
                array_list.clear();
                array_list = mydb.getAllNotes();
                Toast.makeText(MainActivity.this, " onActivityResult called  and the size of the list is "+array_list, Toast.LENGTH_SHORT).show();
                //Notes[] notes = new Notes[array_list.size()];
               // notes = array_list.toArray(notes);
                adapter.updateReceiptsList(array_list);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


               // notifyDataSetChanged();

              //  obj.setAdapter(new ListCustomAdapter(this, notes, mydb));
//                obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        // Toast.makeText(MainActivity.this, "The position pressed is "+position, Toast.LENGTH_SHORT).show();
//                        int id_Search = position + 1;//because of the way they are stored into the database
//
//                        Toast.makeText(MainActivity.this, "The position pressed is " + id_Search, Toast.LENGTH_SHORT).show();
//
//                        Bundle dataBundle = new Bundle();
//                        dataBundle.putInt("id", id_Search);
//                        Intent intent = new Intent(getApplicationContext(), Display_Info.class);
//
//                        intent.putExtras(dataBundle);
//                        startActivityForResult(intent, CHECK_RIGHT_INTENT);
//                    }
//                });
            }
       // }
    }

    public View getViewByPosition(int pos, ListView listView)
    {
//        int wantedPosition = pos; // Whatever position you're looking for
//        int firstPosition = obj.getFirstVisiblePosition() - obj.getHeaderViewsCount(); // This is the same as child #0
//        int wantedChild = wantedPosition - firstPosition;
//// Say, first visible position is 8, you want position 10, wantedChild will now be 2
//// So that means your view is child #2 in the ViewGroup:
//        if (wantedChild < 0 || wantedChild >= listView.getChildCount()) {
//            Log.w("HELLO ", "Unable to get view for desired position, because it's not being displayed on screen.");
//            return;
//        }
        int wantedPosition = pos;
        int firstVisible = listView.getFirstVisiblePosition();
        int lastVisible = listView.getLastVisiblePosition();
        if(wantedPosition>=firstVisible || wantedPosition <= lastVisible)
        {
            return listView.getChildAt(wantedPosition);
        }
// Could also check if wantedPosition is between listView.getFirstVisiblePosition() and listView.getLastVisiblePosition() instead.
       return  null;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.item1:
                Bundle data = new Bundle();
                data.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(),Display_Info.class);
                intent.putExtras(data);
                //startActivity(intent);
                startActivityForResult(intent, CHECK_RIGHT_INTENT);
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }


    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

     public void Take_a_Photo(View view)
     {

     }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
