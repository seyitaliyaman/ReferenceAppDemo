package com.demo.reffappdemo.UserUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.reffappdemo.Model.Users;
import com.demo.reffappdemo.R;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView username;
    private TextView usermail;
    private TextView userno;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.name);
        username = view.findViewById(R.id.username);
        usermail = view.findViewById(R.id.usermail);
        userno = view.findViewById(R.id.userno);

        /*Users user  = (Users) savedInstanceState.getSerializable("user");
        name.setText(user.getFullname());
        username.setText(user.getNickname());
        usermail.setText(user.getEmail());
        userno.setText(user.getPhoneNumber());*/




        return view;
    }



}
