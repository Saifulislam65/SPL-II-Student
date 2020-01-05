package com.ban.student;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 7117;
    List<AuthUI.IdpConfig> providers;
    DatabaseReference studentProfileData;
    FirebaseUser firebaseUser;
    ListStudentProfileInfo listStudentProfileInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        showSignInOptions();
        //setStatusBarColor();
    }


    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setTheme(R.style.MyAppTheme)
                        .build(), MY_REQUEST_CODE
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(MainActivity.this, " "+user.getEmail(), Toast.LENGTH_SHORT).show();
                studentProfileData = FirebaseDatabase.getInstance().getReference("Student/"+user.getUid()+"/PersonalInfo");
                System.out.println("UID "+user.getUid());
                studentProfileData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        try{
                            listStudentProfileInfo = dataSnapshot.getValue(ListStudentProfileInfo.class);
                            System.out.println("ID:" +listStudentProfileInfo.getStudentID());
                            Intent intent= new Intent(getApplicationContext(), ActivityAddCourses.class);
                            startActivity(intent);
                            finish();
                        }catch (Exception e){
                            System.out.println("in catch");
                            Intent intent= new Intent(getApplicationContext(), ActivityStudentInfo.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Ops...something is wrong!", Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Ops...something is wrong!", Toast.LENGTH_LONG);
                    }
                });

            }
            else {
                Toast.makeText(MainActivity.this, " "+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }
}
