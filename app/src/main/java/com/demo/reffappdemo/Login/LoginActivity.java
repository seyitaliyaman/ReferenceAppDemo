package com.demo.reffappdemo.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.demo.reffappdemo.UserUI.Home;
import com.demo.reffappdemo.Model.Users;
import com.demo.reffappdemo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database. DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameInput,passwordInput;
    private Button loginButton;
    private TextView createAccount,help;


    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        loginButton = (Button) findViewById(R.id.loginButton);
        createAccount = (TextView) findViewById(R.id.createAccount);
        help = (TextView) findViewById(R.id.help);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();

        setupAuthListener();


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser(usernameInput.getText().toString(),passwordInput.getText().toString());
            }

        });
    }

    private void checkUser(final String username, final String password) {

        mRef.child("Users").orderByChild("nickname").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Users readUser = ds.getValue(Users.class);

                    if(readUser.getNickname().equals(username)){

                        logIn(readUser,password);
                        break;

                    }else if (readUser.getPhoneNumber().equals(username)){

                        logIn(readUser,password);
                        break;
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void logIn(final Users readUser, String password) {

        mAuth.signInWithEmailAndPassword(readUser.getEmail(),password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Log.e("giriş yapan uid",""+mAuth.getCurrentUser().getUid());
                    bundle.putSerializable("user",readUser);
                    Toast.makeText(getApplicationContext(),"Giriş Yapıldı"+mAuth.getCurrentUser().getUid(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı veya şifre yanlış. Tekrar deneyin!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setupAuthListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){

                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    intent.putExtra("id",mAuth.getCurrentUser().getUid());
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else{

                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
