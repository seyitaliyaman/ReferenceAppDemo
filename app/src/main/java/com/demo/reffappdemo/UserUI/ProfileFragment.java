package com.demo.reffappdemo.UserUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.reffappdemo.Model.Users;
import com.demo.reffappdemo.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView username;
    private TextView usermail;
    private TextView userno;

    private ImageButton namebut;
    private ImageButton usernamebut;
    private ImageButton emailbut;
    private ImageButton phonebut;

    String id;

    private ImageButton buts[] = new ImageButton[4];


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //user  = (Users) savedInstanceState.getSerializable("user");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*id = savedInstanceState.getString("id");
        Log.e("id",id);*/
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

        namebut = view.findViewById(R.id.namebut);
        buts[0] = namebut;
        usernamebut = view.findViewById(R.id.usernamebut);
        buts[1] = usernamebut;
        emailbut = view.findViewById(R.id.mailButton);
        buts[2] = emailbut;
        phonebut = view.findViewById(R.id.phone_button);
        buts[3] = phonebut;

        /*name.setText(user.getFullname());
        username.setText(user.getNickname());
        usermail.setText(user.getEmail());
        userno.setText(user.getPhoneNumber());*/

        String id = Home.id;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        if (id != null){
            reference.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    name.setText(dataSnapshot.child("fullname").getValue().toString());
                    username.setText(dataSnapshot.child("nickname").getValue().toString());
                    usermail.setText(dataSnapshot.child("email").getValue().toString());
                    userno.setText(dataSnapshot.child("phoneNumber").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }






        return view;
    }

    class EditButtonListener implements View.OnClickListener{

        private int index;

        EditButtonListener(int index){

            this.index=index;

        }

        @Override
        public void onClick(View view) {




        }
    }



}
