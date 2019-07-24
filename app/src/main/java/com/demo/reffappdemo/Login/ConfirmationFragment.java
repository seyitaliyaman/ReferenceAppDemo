package com.demo.reffappdemo.Login;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.reffappdemo.HomePage;
import com.demo.reffappdemo.Model.Users;
import com.demo.reffappdemo.R;
import com.demo.reffappdemo.Utils.UserRegisterInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;


public class ConfirmationFragment extends Fragment{


    private EditText confirmationCode;
    private TextView sendNumber;
    private Button confirm;

    private String verificationId ="";
    private String incomingCode = "";

    private String fullname="";
    private String username="";
    private String email="";
    private String password="";
    private String phoneNumber="";
    private String userUID;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack;

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        confirmationCode = view.findViewById(R.id.confirmationCode);
        sendNumber = view.findViewById(R.id.sendNumber);
        confirm = view.findViewById(R.id.confirm);
        sendNumber.setText(phoneNumber);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() !=null){
            mAuth.signOut();
        }


        mRef = FirebaseDatabase.getInstance().getReference();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(confirmationCode.getText().toString().equals("123456")){

                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(),"Kullanıcı Oluşturuldu",Toast.LENGTH_LONG).show();

                                userUID = mAuth.getCurrentUser().getUid();
                                //db kayıt
                                Users user = new Users(fullname,username,email,password,phoneNumber,userUID);

                                mRef.child("Users").child(userUID).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){

                                            Toast.makeText(getContext(),"Databaseye kaydedildi",Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getContext(), HomePage.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }else{

                                            mAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(getContext(),"Kullanıcı kaydedilemedi.",Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(getContext(), RegisterActivity.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                            Log.e("hata sebebi ",""+task.getException());

                                        }
                                    }
                                });



                            }else{
                                Toast.makeText(getContext(),"Hesap Oluşturulamadı.Lütfen tekrar deneyin",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    Toast.makeText(getContext(),"Kod Doğru",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Kod Yanlış!Tekrar dene" ,Toast.LENGTH_LONG).show();
                }
            }
        });


        /*setupCallBack();


        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,90, TimeUnit.SECONDS,this.getActivity(),mCallBack);

*/

        return view;
    }





    private void setupCallBack() {

        mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                if(!phoneAuthCredential.getSmsCode().isEmpty())
                    incomingCode = phoneAuthCredential.getSmsCode();
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

    @Subscribe(sticky = true)
    public void ConfirmationEvent(UserRegisterInfo registerInfo){


        fullname = registerInfo.getFullname();
        username = registerInfo.getUsername();
        email = registerInfo.getEmail();
        password = registerInfo.getPassword();
        phoneNumber = registerInfo.getPhoneNumber();


        Log.e("confirmation fragment","-----------------------------");

        Log.e("deneme isim",""+fullname);
        Log.e("deneme nick",""+username);
        Log.e("deneme email",""+email);
        Log.e("deneme parola",""+password);
        Log.e("deneme telefon",""+phoneNumber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        EventBus.getDefault().register(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }



}
