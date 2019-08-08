package com.demo.reffappdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class InviteeGetCode extends AppCompatActivity {

    private TextView kampBilgi;
    private EditText invPhone;
    private Button sendCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;

    private String incomingCode,verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitee_get_code);

        kampBilgi = findViewById(R.id.kampBilgi);
        invPhone = findViewById(R.id.invPhone);
        sendCode = findViewById(R.id.sendCode);

        kampBilgi.setText("\""+this.getIntent().getStringExtra("kampanyaAd")+"\" \nisimli kampanyadan faydalanmak üzeresiniz.Kampanya koduna ulaşabilmek için lütfen telefon numaranızı giriniz.");
//05418969927
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupCallBack();

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+9"+invPhone.getText().toString(),90, TimeUnit.SECONDS,InviteeGetCode.this,mCallBack);
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        if (getWindowManager().getDefaultDisplay().getWidth()<getWindowManager().getDefaultDisplay().getHeight()){
            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int) (width*.85),(int) (height*.5));
            getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_button));
        }else{
            int width = dm.widthPixels;
            int height = dm.heightPixels;

            getWindow().setLayout((int) (width*.5),(int) (height*.85));
            getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corner_button));
        }
    }

    private void setupCallBack() {

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                if(!phoneAuthCredential.getSmsCode().isEmpty()){
                    incomingCode = phoneAuthCredential.getSmsCode();
                    Log.e("gelen kod",""+incomingCode);
                }
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("HATA","Hata çıktı..."+e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationId = s;
            }
        };
    }
}