package com.demo.reffappdemo.Login;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.demo.reffappdemo.R;
import com.demo.reffappdemo.Utils.UserRegisterInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetPhoneFragment extends Fragment {


    private EditText phoneNumber;
    private Button nextButton;

    private String fullname="",username="",email="",password="",number="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_phone, container, false);

        phoneNumber = view.findViewById(R.id.phoneNumber);
        nextButton = view.findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.registerContainer,new ConfirmationFragment(),"degişti");
                transaction.addToBackStack("Confirmationd added");
                transaction.commit();
                EventBus.getDefault().postSticky(new UserRegisterInfo(fullname,username,email,password,phoneNumber.getText().toString()));


            }
        });


        return view;
    }

    @Subscribe(sticky = true)
    public void ConfirmationEvent(UserRegisterInfo registerInfo){

        fullname = registerInfo.getFullname();
        username = registerInfo.getUsername();
        email = registerInfo.getEmail();
        password = registerInfo.getPassword();
        number = registerInfo.getPhoneNumber();

        Log.e("deneme isim",""+fullname);
        Log.e("deneme nick",""+username);
        Log.e("deneme email",""+email);
        Log.e("deneme parola",""+password);
        Log.e("deneme telefon",""+number);


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
