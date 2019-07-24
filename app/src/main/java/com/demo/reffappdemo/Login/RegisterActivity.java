package com.demo.reffappdemo.Login;

import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.demo.reffappdemo.R;
import com.demo.reffappdemo.Utils.UserRegisterInfo;

import org.greenrobot.eventbus.EventBus;


public class RegisterActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {


    private EditText fullname,username,password,email;
    private Button nextButton;

    private ConstraintLayout registerRoot;
    private FrameLayout registerContainer;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        registerRoot = findViewById(R.id.registerRoot);
        registerContainer = findViewById(R.id.registerContainer);


        nextButton = findViewById(R.id.nextButton);

        manager = getSupportFragmentManager();
        manager.addOnBackStackChangedListener(this);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerRoot.setVisibility(View.GONE);
                registerContainer.setVisibility(View.VISIBLE);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.registerContainer,new GetPhoneFragment(),"değişti");
                transaction.addToBackStack("Phone added");
                transaction.commit();

                Log.e("getCount",""+manager.getBackStackEntryCount());

                EventBus.getDefault().postSticky(new UserRegisterInfo(fullname.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString(),null));

            }
        });

    }





    @Override
    public void onBackStackChanged() {
        if(manager.getBackStackEntryCount()==0){
            registerRoot.setVisibility(View.VISIBLE);
            //registerContainer.setVisibility(View.GONE);
        }
    }
}
