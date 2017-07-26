/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.sanyogghosh.social;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
* {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link AndroidFlavor} objects.
* */
public class ArticleAdapter extends ArrayAdapter<ArticleEvent> {

   public int variable=0;

    public String name="";

    public int as,j=0;

    public View listItemView;

    private  final String LOG_TAG = ArticleAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public ArticleAdapter(Activity context, ArrayList<ArticleEvent> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

       listItemView = convertView;
        Log.d("position",String.valueOf(position));
        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_element, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        final ArticleEvent currentAndroidFlavor = getItem(position);

        final TextView a = (TextView)listItemView.findViewById(R.id.head);
        TextView b = (TextView)listItemView.findViewById(R.id.text1);
        TextView c = (TextView)listItemView.findViewById(R.id.date);
        final TextView d = (TextView)listItemView.findViewById(R.id.upvotes);
        final TextView e = (TextView)listItemView.findViewById(R.id.downvotes);
        final TextView f = (TextView)listItemView.findViewById(R.id.author);
        TextView g = (TextView)listItemView.findViewById(R.id.article_status);
        TextView h = (TextView)listItemView.findViewById(R.id.divider);

//image
        ImageView aa = (ImageView)listItemView.findViewById(R.id.picture);
        String text = currentAndroidFlavor.image;

        byte[] decodedString = Base64.decode(text, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        aa.setImageBitmap(decodedByte);

//image done

        final TextView h1 = (TextView)listItemView.findViewById(R.id.favourite_count);

        Typeface font1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/robotoslablight.ttf");

        Typeface font2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/robotoslabbold.ttf");

        Typeface font3 = Typeface.createFromAsset(getContext().getAssets(), "fonts/robotobold.ttf");

        Typeface font4 = Typeface.createFromAsset(getContext().getAssets(), "fonts/robotoslabthin.ttf");

        Typeface font5   = Typeface.createFromAsset(getContext().getAssets(), "fonts/robotoblack.ttf");






        try {
            FileInputStream fileIn = getContext().openFileInput("my_username.txt");
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



        } catch (Exception ea) {
            ea.printStackTrace();
        }
        final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference votes=mFirebaseDatabase.getReference().child("Articles").child("Article "+currentAndroidFlavor.head+" "+currentAndroidFlavor.name).child("upvotes");
        final DatabaseReference votesd=mFirebaseDatabase.getReference().child("Articles").child("Article "+currentAndroidFlavor.head+" "+currentAndroidFlavor.name).child("downvotes");

        final ImageView h12 = (ImageView)listItemView.findViewById(R.id.up_arrow);
        final ImageView h11 = (ImageView)listItemView.findViewById(R.id.down_arrow);

        votes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                as = snapshot.getValue(int.class);




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        votesd.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                j = snapshot.getValue(int.class);




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        final DatabaseReference mM2=mFirebaseDatabase.getReference().child("Profiles").child("Profile"+" "+name).child("Voted Articles").child("Article "+currentAndroidFlavor.head+" "+currentAndroidFlavor.name);


                mM2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {

                        if(snapshot.getValue(int.class)==null){

                                    h11.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mM2.setValue(-1);
                                            votesd.setValue(j + 1);
                                            if(as==1){
                                            votes.setValue(as-1);}
                                            int y = Integer.parseInt(e.getText().toString());
                                            int g = y+1;

                                            e.setText(""+g);
                                            h11.setEnabled(false);

                                        }});
                                    h12.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            mM2.setValue(1);
                                            votes.setValue(as + 1);
                                            if(j==1){
                                            votesd.setValue(j-1);
                                            }
                                            int y = Integer.parseInt(d.getText().toString());
                                            int g = y+1;
                                            h12.setEnabled(false);
                                            d.setText(""+g);
                                        }
                                    });



                        }
                        else {
                            int a = snapshot.getValue(int.class);


                            if (a == 1) {

                                h12.setEnabled(false);

                                h11.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                                h11.setEnabled(false);

                                                mM2.setValue(-1);


                                                votesd.setValue(j + 1);

                                                int y = Integer.parseInt(e.getText().toString());
                                                int g = y+1;

                                                e.setText(""+(currentAndroidFlavor.downvotes+1));

                                                int ya = Integer.parseInt(d.getText().toString());
                                                int ga = ya-1;

                                                d.setText(""+(currentAndroidFlavor.upvotes-1));

                                                 votes.setValue(as - 1);






                                    }
                                });


                            }
                            if (a == -1) {


                                h11.setEnabled(false);

                                h12.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                                h12.setEnabled(false);

                                                mM2.setValue(1);
                                                votes.setValue(as + 1);
                                                votesd.setValue(j - 1);

                                        int y = Integer.parseInt(e.getText().toString());
                                        int g = y - 1;

                                        e.setText(""+(currentAndroidFlavor.downvotes-1));

                                        int ya = Integer.parseInt(d.getText().toString());
                                        int ga = ya + 1;

                                        d.setText("" + (currentAndroidFlavor.upvotes+1));

                                }});


                            }


                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });






        a.setText(currentAndroidFlavor.head);
        a.setTypeface(font2);
        b.setText(currentAndroidFlavor.text);
        b.setTypeface(font1);
        c.setText(currentAndroidFlavor.date);
        c.setTypeface(font5);
        d.setText(""+currentAndroidFlavor.upvotes);
        d.setTypeface(font3);
        e.setText(""+currentAndroidFlavor.downvotes);
        e.setTypeface(font3);
        f.setText(currentAndroidFlavor.name);
        f.setTypeface(font4);
        g.setText(currentAndroidFlavor.status);
        g.setTypeface(font2);


        if(currentAndroidFlavor.status.equals("NOTICE")){
            g.setBackgroundColor(Color.parseColor("#FFFF0019"));
            h.setBackgroundColor(Color.parseColor("#FFFF0019"));
        }


        if(currentAndroidFlavor.status.equals("ARTICLE")){
            g.setBackgroundColor(Color.parseColor("#FF1A1DAB"));
            h.setBackgroundColor(Color.parseColor("#FF1A1DAB"));
        }

        if(currentAndroidFlavor.status.equals("PINNED-POST")){
            g.setBackgroundColor(Color.parseColor("#FFEFE939"));
            h.setBackgroundColor(Color.parseColor("#FFEFE939"));
        }



        return listItemView;
    }



}

