package com.example.sanyogghosh.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignIn extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private DatabaseReference mMessagesDatabaseReference2;
    private ChildEventListener mChildEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ProgressBar a = (ProgressBar)findViewById(R.id.progress);
        a.setVisibility(View.GONE);

        Spinner spin_a = (Spinner)findViewById(R.id.spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spin_a.setAdapter(adapter);


        //username and password obtained

        TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog2);
        dialog_alert.setVisibility(View.GONE);
        TextView aa = (TextView)findViewById(R.id.Account_error);
        aa.setVisibility(View.GONE);


        mFirebaseDatabase = FirebaseDatabase.getInstance();


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.done_1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressBar a = (ProgressBar)findViewById(R.id.progress);
                a.setVisibility(View.VISIBLE);

                EditText user_name = (EditText)findViewById(R.id.username_signin);
                EditText pass_word = (EditText)findViewById(R.id.password_signin);


                final String username = user_name.getText().toString();
                final String password = pass_word.getText().toString();

                mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Profiles").child("Profile"+" "+username).child("Username");
                DatabaseReference mM2=mFirebaseDatabase.getReference().child("Profiles").child("Profile"+" "+username).child("Voted Articles").child("Wow");
                mM2.setValue(1);
                mMessagesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String a = snapshot.getValue(String.class);

                        if(a!=null){

                            TextView aa = (TextView)findViewById(R.id.Account_error);
                            aa.setVisibility(View.VISIBLE);
                            aa.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                            ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
                            aaa.setVisibility(View.GONE);


                        }

                       else {
                            if (username.equals("")) {

                                TextView dialog_alert = (TextView) findViewById(R.id.check_details_dialog2);
                                dialog_alert.setVisibility(View.VISIBLE);
                                dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));

                                ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
                                aaa.setVisibility(View.GONE);

                            }

                            if (password.equals("")) {


                                TextView dialog_alert = (TextView) findViewById(R.id.check_details_dialog2);
                                dialog_alert.setVisibility(View.VISIBLE);
                                dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));

                                ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
                                aaa.setVisibility(View.GONE);


                            } else {
                                mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Profiles").child("Profile" + " " + username).child("Username");
                                mMessagesDatabaseReference.setValue(username);

                                mMessagesDatabaseReference2 = mFirebaseDatabase.getReference().child("Profiles").child("Profile" + " " + username).child("Password");
                                mMessagesDatabaseReference2.setValue(password);

                                ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
                                aaa.setVisibility(View.GONE);

                                Intent i = new Intent(getApplicationContext(), MainSocialActivity.class);
                                startActivity(i);     }



                        }


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


            }
        });

    }








}
