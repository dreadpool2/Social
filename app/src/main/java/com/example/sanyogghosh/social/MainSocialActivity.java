package com.example.sanyogghosh.social;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainSocialActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private DatabaseReference mMessagesDatabaseReference2;
    private ChildEventListener mChildEventListener;
    public String username="";
    public String password="";
    public String username_received="";
    public String password_received="";
    public int k=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_social);

        ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
        aaa.setVisibility(View.GONE);

        TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
        dialog_alert.setVisibility(View.GONE);
        //username and password obtained

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.done);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressBar aaa = (ProgressBar)findViewById(R.id.progress);
                aaa.setVisibility(View.VISIBLE);

                final EditText user_name = (EditText)findViewById(R.id.username);
                final EditText pass_word = (EditText)findViewById(R.id.password);

                username = user_name.getText().toString();
                password = pass_word.getText().toString();



                mMessagesDatabaseReference = mFirebaseDatabase.getReference().child("Profiles").child("Profile" + " " + username).child("Username");
                mMessagesDatabaseReference2 = mFirebaseDatabase.getReference().child("Profiles").child("Profile"+" "+username).child("Password");



                mMessagesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        username_received = snapshot.getValue(String.class);
                        if(username_received==null){


                            TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                            dialog_alert.setVisibility(View.VISIBLE);
                            dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));


                            ProgressBar aaaaa = (ProgressBar)findViewById(R.id.progress);
                            aaaaa.setVisibility(View.GONE);


                        }
                        else{

                            k=2;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                mMessagesDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        password_received = snapshot.getValue(String.class);
                        if(password_received==null){


                            TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                            dialog_alert.setVisibility(View.VISIBLE);
                            dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                            ProgressBar aaaa = (ProgressBar)findViewById(R.id.progress);
                            aaaa.setVisibility(View.GONE);


                        }else{
                        if(!password_received.equals(password)){


                            TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                            dialog_alert.setVisibility(View.VISIBLE);
                            dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                            ProgressBar aaaa = (ProgressBar)findViewById(R.id.progress);
                            aaaa.setVisibility(View.GONE);




                        }
                                if (password_received.equals(password)) {

                                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                                    Log.d("aa", "yes");
                                    ProgressBar aaaaa = (ProgressBar) findViewById(R.id.progress);
                                    aaaaa.setVisibility(View.GONE);

                                    TextView dialog_alert = (TextView) findViewById(R.id.check_details_dialog);
                                    dialog_alert.setVisibility(View.GONE);
                                }







                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

               if(k==2){
               if(username_received!=null){

                if(username_received.equals(username)){
                    if(!password_received.equals(password)){


                        TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                        dialog_alert.setVisibility(View.VISIBLE);
                        dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                        ProgressBar aaaa = (ProgressBar)findViewById(R.id.progress);
                        aaaa.setVisibility(View.GONE);


                    }

                    if(password_received.equals(password)){
                       if(username.equals("")){

                           TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                           dialog_alert.setVisibility(View.VISIBLE);
                           dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                           ProgressBar aasa = (ProgressBar)findViewById(R.id.progress);
                           aasa.setVisibility(View.GONE);


                       }

                        if(password.equals("")){


                            TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                            dialog_alert.setVisibility(View.VISIBLE);
                            dialog_alert.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake));

                            ProgressBar aasa = (ProgressBar)findViewById(R.id.progress);
                            aasa.setVisibility(View.GONE);


                        }
                        else{
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                        Log.d("aa","yes");
                            ProgressBar aaaaa = (ProgressBar)findViewById(R.id.progress);
                            aaaaa.setVisibility(View.GONE);

                        TextView dialog_alert = (TextView)findViewById(R.id.check_details_dialog);
                        dialog_alert.setVisibility(View.GONE);
                        }
                    }



                }}}






            }
        });
          k=0;
    }

    public void signin(View view){

        Intent i = new Intent(this,SignIn.class);
        startActivity(i);




    }
}


