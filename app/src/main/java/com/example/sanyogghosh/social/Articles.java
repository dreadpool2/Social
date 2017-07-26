package com.example.sanyogghosh.social;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class Articles extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mMessageListView;
    private ArticleAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;
    private String encodedImage;
    private String mUsername;
    public int za,y=0;
    public Uri selectedImageUri;

    private SwipeRefreshLayout aes;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private StorageReference mStorageReference;
    private FirebaseStorage mStorage;
    private String selectedImagePath="GHG";

    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        mUsername = ANONYMOUS;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Articles");


        // Initialize references to views
        mMessageListView = (ListView) findViewById(R.id.article_list);
        mMessageListView.setDivider(null);

        // Initialize message ListView and its adapter
        mMessageAdapter = new ArticleAdapter(this, new ArrayList<ArticleEvent>());
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar

        mMessageListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {



            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {



                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    za=0;
                    if(y==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.godown);
                        Animation a = AnimationUtils.loadAnimation(Articles.this, R.anim.abc_slide_out_bottom);
                        options.startAnimation(a);
                        FloatingActionButton f  = (FloatingActionButton)findViewById(R.id.add);
                        f.setVisibility(View.GONE);
                        options.setVisibility(View.GONE);
                    }

                    Log.i("SCROLLING DOWN","TRUE");
                    y++;
                }

                if(mLastFirstVisibleItem>firstVisibleItem)

                {  y=0;

                    if(za==0) {
                        LinearLayout options = (LinearLayout) findViewById(R.id.godown);
                        options.setVisibility(View.VISIBLE);

                        Animation a = AnimationUtils.loadAnimation(Articles.this, R.anim.abc_slide_in_bottom);

                        FloatingActionButton f  = (FloatingActionButton)findViewById(R.id.add);
                        f.setVisibility(View.VISIBLE);
                        options.startAnimation(a);
                    }


                    Log.i("SCROLLING UP","TRUE");
                    za++;


                          // }
                }
                mLastFirstVisibleItem=firstVisibleItem;

            }
        });


        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArticleEvent friendlyMessage = dataSnapshot.getValue(ArticleEvent.class);
                mMessageAdapter.add(friendlyMessage);
                aes = (SwipeRefreshLayout)findViewById(R.id.swipe);
                aes.setEnabled(false);
                aes.setRefreshing(false);
                ProgressBar d = (ProgressBar)findViewById(R.id.progress);
                d.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);



    }
        Point p;
        @Override
        public void onWindowFocusChanged(boolean hasFocus) {


            //Initial
        }

        // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        int popupHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        int[] location = new int[2];
        p = new Point();

        FloatingActionButton ar = (FloatingActionButton)findViewById(R.id.add);
        ar.getLocationInWindow(location);
        p.x = location[0];
        p.y = location[1];
        ListView a = (ListView)findViewById(R.id.article_list);
        // Get the x, y location and store it in the location[] array
        //
        // Inflate the popup_layout.xml
        FrameLayout viewGroup = (FrameLayout) context.findViewById(R.id.dialog_new);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.new_dialog, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.


        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        int f  = (int)(popup.getHeight()*0.5);
        int u  = (int)(popup.getWidth()*0.5);

        Log.d("aa",""+u);
        popup.setAnimationStyle(R.style.animationName);
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, (p.x)+(201*u) , (p.y)+(111*popup.getHeight()));

        TextView a1 = (TextView)layout.findViewById(R.id.pinnedpost_dialog);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout viewGroupa = (FrameLayout) context.findViewById(R.id.write);
                LayoutInflater layoutInflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View layout = layoutInflater.inflate(R.layout.write_up, viewGroupa);
// Creating the PopupWindow
//IMAGE PICKER
                ImageView d = (ImageView)layout.findViewById(R.id.image2);
                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

                    }
                });

                TextView da = (TextView) layout.findViewById(R.id.heada);
                 da.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                     }
                 });


                //DONE!



                final PopupWindow popup = new PopupWindow(context);
                popup.setContentView(layout);
                popup.setWidth((LinearLayout.LayoutParams.MATCH_PARENT));
                popup.setHeight((LinearLayout.LayoutParams.MATCH_PARENT));
                popup.setFocusable(true);

                popup.setAnimationStyle(R.style.animationName2);
                // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.


                // Clear the default translucent background
                popup.setBackgroundDrawable(new BitmapDrawable());

                FloatingActionButton fabg  = (FloatingActionButton)layout.findViewById(R.id.fabg);
                fabg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });


                popup.showAtLocation(layout, Gravity.CENTER, 0 , 0);
                FloatingActionButton fab  = (FloatingActionButton)layout.findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="";
                        try {
                            FileInputStream fileIn = openFileInput("my_username.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);

                            char[] inputBuffer = new char[100];
                            name = "";
                            int charRead;

                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                // char to string conversion
                                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                name += readstring;

                            }

                            InputRead.close();



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        EditText a = (EditText)layout.findViewById(R.id.text_entered);
                        String aa = a.getText().toString();

                        EditText ad = (EditText)layout.findViewById(R.id.heada);
                        String aad = ad.getText().toString();

                        mMessagesDatabaseReference=mFirebaseDatabase.getReference().child("Articles").child("Article "+aad+" "+name);
                        Calendar c = Calendar.getInstance();



                        String x = (String) android.text.format.DateFormat.format("dd MMMM yyyy",c.getTime());

                        ArticleEvent friendlyMessage = new ArticleEvent(name ,aad,aa,x,0,0,"PINNED-POST",0,encodedImage);
                        mMessagesDatabaseReference.setValue(friendlyMessage);
                        popup.dismiss();

                    }
                });

            }
        });




        TextView a2 = (TextView)layout.findViewById(R.id.notice_dialog);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout viewGroupa = (FrameLayout) context.findViewById(R.id.write);
                LayoutInflater layoutInflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               final View layout = layoutInflater.inflate(R.layout.write_up, viewGroupa);
// Creating the PopupWindow

                ImageView d = (ImageView)layout.findViewById(R.id.image2);
                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);

                    }
                });

                final PopupWindow popup = new PopupWindow(context);
                popup.setContentView(layout);
                popup.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popup.setHeight((LinearLayout.LayoutParams.MATCH_PARENT));
                popup.setFocusable(true);

                popup.setAnimationStyle(R.style.animationName2);
                // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.


                // Clear the default translucent background
                popup.setBackgroundDrawable(new BitmapDrawable());

                popup.showAtLocation(layout, Gravity.CENTER, 0 , 0);
                FloatingActionButton fab  = (FloatingActionButton)layout.findViewById(R.id.fab);


                FloatingActionButton fabg  = (FloatingActionButton)layout.findViewById(R.id.fabg);
                fabg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });








                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="";
                        try {
                            FileInputStream fileIn = openFileInput("my_username.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);

                            char[] inputBuffer = new char[100];
                            name = "";
                            int charRead;

                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                // char to string conversion
                                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                name += readstring;

                            }

                            InputRead.close();



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        EditText a = (EditText)layout.findViewById(R.id.text_entered);
                        String aa = a.getText().toString();

                        EditText ad = (EditText)layout.findViewById(R.id.heada);
                        String aad = ad.getText().toString();

                        mMessagesDatabaseReference=mFirebaseDatabase.getReference().child("Articles").child("Article "+aad+" "+name);
                        Calendar c = Calendar.getInstance();



                        String x = (String) android.text.format.DateFormat.format("dd MMMM yyyy",c.getTime());

                        ArticleEvent friendlyMessage = new ArticleEvent( name ,aad,aa,x,0,0,"NOTICE",0,encodedImage);
                        mMessagesDatabaseReference.setValue(friendlyMessage);
                        popup.dismiss();

                    }
                });

            }
        });



        TextView a3 = (TextView)layout.findViewById(R.id.article_dialog);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrameLayout viewGroupa = (FrameLayout) context.findViewById(R.id.write);
                LayoutInflater layoutInflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View layout = layoutInflater.inflate(R.layout.write_up, viewGroupa);
// Creating the PopupWindow
                final ImageView d = (ImageView)layout.findViewById(R.id.image2);

                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       try {


                           File imgFile = new File(String.valueOf(selectedImageUri));


                           if (imgFile.exists()) {

                               Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                               ImageView myImage = (ImageView) layout.findViewById(R.id.image2);

                               myImage.setImageBitmap(myBitmap);
                               Log.d("Hey","aaaa");

                           }
                       }
                       catch (Exception e){

                           Log.d("Hey","no");
                       }
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);




                    }
                });

               if(selectedImagePath!=null){
                File imgFile = new File(selectedImagePath);

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                    ImageView myImage = (ImageView) layout.findViewById(R.id.image2);

                    myImage.setImageBitmap(myBitmap);

                }

               }
                d.setImageURI(selectedImageUri);

                final PopupWindow popup = new PopupWindow(context);
                popup.setContentView(layout);
                popup.setWidth((LinearLayout.LayoutParams.MATCH_PARENT));
                popup.setHeight((LinearLayout.LayoutParams.MATCH_PARENT));
                popup.setFocusable(true);
                popup.setAnimationStyle(R.style.animationName2);

                // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.


                // Clear the default translucent background
                popup.setBackgroundDrawable(new BitmapDrawable());

                popup.showAtLocation(layout, Gravity.CENTER, 0 , 0);
                FloatingActionButton fab  = (FloatingActionButton)layout.findViewById(R.id.fab);
                FloatingActionButton fabg  = (FloatingActionButton)layout.findViewById(R.id.fabg);
                fabg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="";
                        try {
                            FileInputStream fileIn = openFileInput("my_username.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);

                            char[] inputBuffer = new char[100];
                            name = "";
                            int charRead;

                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                // char to string conversion
                                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                name += readstring;

                            }

                            InputRead.close();



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        EditText a = (EditText)layout.findViewById(R.id.text_entered);
                        String aa = a.getText().toString();

                        EditText ad = (EditText)layout.findViewById(R.id.heada);
                        String aad = ad.getText().toString();

                        mMessagesDatabaseReference=mFirebaseDatabase.getReference().child("Articles").child("Article "+aad+" "+name);
                        Calendar c = Calendar.getInstance();



                        String x = (String) android.text.format.DateFormat.format("dd MMMM yyyy",c.getTime());


                        ArticleEvent friendlyMessage = new ArticleEvent( name ,aad,aa,x,0,0,"ARTICLE",0,encodedImage);
                        mMessagesDatabaseReference.setValue(friendlyMessage);

                        popup.dismiss();

                    }
                });

            }
        });



    }

























    public void add(View view){


        showPopup(this,p);
    }





    public void phonenumbers(View view){

        Intent i = new Intent(this, PhoneNumbers.class);
        startActivity(i);

        overridePendingTransition(R.anim.slide_down,R.anim.slide_up);

        finish();

    }

    //copied from stackoverflow! must be changed (below)




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null){
            {
                 selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
                Log.d("image",String.valueOf(selectedImageUri));

                //content://com.android.providers.media.documents/document/image%3A77023

                String path = selectedImageUri.toString();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(selectedImageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                encodedImage = encodeImage(selectedImage);
                Log.d("image",String.valueOf(path));

                // "/mnt/sdcard/FileName.mp3"

            }
        }
    }

    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor =  getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

}
