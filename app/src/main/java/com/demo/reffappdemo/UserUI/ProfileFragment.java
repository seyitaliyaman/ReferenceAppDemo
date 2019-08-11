package com.demo.reffappdemo.UserUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    private EditText name;
    private EditText username;
    private EditText usermail;
    private EditText userno;

    private ImageButton namebut;
    private ImageButton usernamebut;
    private ImageButton emailbut;
    private ImageButton phonebut;

    String id;

    EditText edts[] = new EditText[4];
    ImageButton buts[] = new ImageButton[4];


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
        id = Home.id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        name = view.findViewById(R.id.name);
        edts[0] = name;
        username = view.findViewById(R.id.username);
        edts[1] = username;
        usermail = view.findViewById(R.id.usermail);
        edts[2] = usermail;
        userno = view.findViewById(R.id.userno);
        edts[3] = userno;

        namebut = view.findViewById(R.id.namebut);
        buts[0] = namebut;
        usernamebut = view.findViewById(R.id.usernamebut);
        buts[1] = usernamebut;
        emailbut = view.findViewById(R.id.mailButton);
        buts[2] = emailbut;
        phonebut = view.findViewById(R.id.phone_button);
        buts[3] = phonebut;

        for (int i=0;i<buts.length;i++){
            buts[i].setOnClickListener(new EditButtonListener(i));
        }

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
        private String field;


        EditButtonListener(int index){

            this.index=index;

            switch (index){
                case 0:
                    field = "fullname";
                    break;
                case 1:
                    field = "nickname";
                    break;


                case 2:
                    field = "email";
                    break;

                case 3:
                    field = "phoneNumber";
                    break;
            }

        }

        @Override
        public void onClick(View view) {

            //if(edts[index].isCursorVisible()){

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Kaydet");
            builder.setMessage("Yapılan değişiklikleri kaydetmek istiyor musunuz?");
            builder.setNegativeButton("Hayır", null);
            builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    String data = edts[index].getText().toString();
                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference().child("Users").child(id);
                    dr.child(field).setValue(data);
                    Toast.makeText(getContext(),"Kaydedildi!",Toast.LENGTH_LONG).show();
                }
            });
            builder.show();

                //edts[index].setFocusable(false);
                //edts[index].setCursorVisible(false);
                //edts[index].setHighlightColor(Color.WHITE);
           /* }
            else{
                Log.e(""+index,"okkey");
                //edts[index].setFocusable(true);
                edts[index].setCursorVisible(true);
                edts[index].setHighlightColor(Color.RED);
            }*/


        }
    }



}
