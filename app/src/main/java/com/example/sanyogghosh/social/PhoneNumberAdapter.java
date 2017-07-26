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
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/*
* {@link AndroidFlavorAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
* based on a data source, which is a list of {@link AndroidFlavor} objects.
* */
public class PhoneNumberAdapter extends ArrayAdapter<PhoneEvent> {

   public int variable=0;
    private  final String LOG_TAG = PhoneNumberAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public PhoneNumberAdapter(Activity context, ArrayList<PhoneEvent> androidFlavors) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        int max=0;
        Log.d("position",String.valueOf(position));
        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.phone_number_element, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        PhoneEvent currentAndroidFlavor = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView name = (TextView) listItemView.findViewById(R.id.people_name);
        final TextView ph1 = (TextView) listItemView.findViewById(R.id.people_ph1);
        final TextView ph2 = (TextView) listItemView.findViewById(R.id.people_ph2);
        TextView height = (TextView)listItemView.findViewById(R.id.height);
        name.setTextColor(Color.parseColor("#000000"));
        listItemView.setElevation(10);
        name.setTextSize(20);
        LinearLayout la = (LinearLayout)listItemView.findViewById(R.id.back);
        la.setElevation(10);
        height.setVisibility(View.VISIBLE);
        listItemView.setVisibility(View.VISIBLE);
        if(currentAndroidFlavor.name.equals("Administrative Contacts")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }
        if(currentAndroidFlavor.name.equals("Deans")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }

        if(currentAndroidFlavor.name.equals("Associate Deans")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);

        }

        if(currentAndroidFlavor.name.equals("Professor in Charge")){
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            name.setTextColor(Color.parseColor("#FFF91629"));
            listItemView.setElevation(0);

        }
        if(currentAndroidFlavor.name.equals("Faculty in Charge(s)")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setVisibility(View.GONE);
            height.setHeight(10);
            listItemView.setElevation(0);
        }


        if(currentAndroidFlavor.name.equals("Head of Department")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setVisibility(View.GONE);
            height.setHeight(10);
            listItemView.setElevation(0);
        }
        if(currentAndroidFlavor.name.equals("Centre-Specific Contacts")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }
        if(currentAndroidFlavor.name.equals("Incharge for General Functions")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }
        if(currentAndroidFlavor.name.equals("Hostel Wardens")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            name.setTextSize(30);
            la.setElevation(0);

            height.setHeight(10);
            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }
        if(currentAndroidFlavor.name.equals("Issue-Specific Contacts")){
            name.setTextColor(Color.parseColor("#FFF91629"));
            height.setHeight(10);
            name.setTextSize(30);
            la.setElevation(0);

            height.setVisibility(View.GONE);
            listItemView.setElevation(0);
        }

        if(currentAndroidFlavor.name.equals("Divider")){

            listItemView.setVisibility(View.GONE);

        }



        height.setHeight(20);



        name.setText(currentAndroidFlavor.name);
        ph1.setText(currentAndroidFlavor.phone_office);
        ph2.setText(currentAndroidFlavor.phone_personal);


        variable++;

         ph1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(Intent.ACTION_DIAL);
                 intent.setData(Uri.parse("tel:        "+ph1.getText().toString()));
                 getContext().startActivity(intent);
             }
         });
        ph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ph2.getText().toString()));
                getContext().startActivity(intent);
            }
        });
        return listItemView;
    }



}

