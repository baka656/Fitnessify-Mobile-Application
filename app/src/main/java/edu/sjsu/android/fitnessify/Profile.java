package edu.sjsu.android.fitnessify;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
public class Profile extends Fragment {

    public static final String TAG = "TAG";
    ImageView img;
    Button btn,updatebtn, btn1;
    EditText kg,cm;
    TextInputLayout name,email,contact;
    FirebaseFirestore firestore;
    FirebaseAuth fAuth;
    String userID;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Profile() {
    }

    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btn = view.findViewById(R.id.signinbtn);
        btn1 = view.findViewById(R.id.signoutbtn);
        kg = view.findViewById(R.id.weight_label);
        cm = view.findViewById(R.id.height_label);
        name = view.findViewById(R.id.full_name_profile);
        email = view.findViewById(R.id.Email_profile);
        contact = view.findViewById(R.id.contact_profile);
        updatebtn = view.findViewById(R.id.updatebutton);
        img = view.findViewById(R.id.profile_image);

        firestore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        SharedPreferences sh = getActivity().getSharedPreferences("baka", MODE_PRIVATE);
        String s1 = sh.getString("email", "");
        if(s1.equals("")){
            btn.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.GONE);
            updatebtn.setVisibility(View.GONE);
        }
        else{
            btn1.setVisibility(View.VISIBLE);
            btn.setVisibility(View.GONE);
            setdata();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("baka", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("email", "");
                myEdit.apply();
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pname = name.getEditText().getText().toString();
                String pemail = email.getEditText().getText().toString();
                String pcontact = contact.getEditText().getText().toString();
                String pweight = kg.getText().toString();
                String pheight = cm.getText().toString();

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = firestore.collection("user").document(userID);
                Map<String,Object> user = new HashMap<>();
                user.put("name",pname);
                user.put("email",pemail);
                user.put("contact",pcontact);
                user.put("weight",pweight);
                user.put("height",pheight);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"Success"+userID);
                    }
                });
            }
        });

//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userID = fAuth.getCurrentUser().getUid();
//                DocumentReference documentReference = firestore.collection("user").document(userID);
//                documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                        kg.setText(value.getString("weight"));
//                        cm.setText(value.getString("height"));
//                        name.getEditText().setText(value.getString("name"));
//                        email.getEditText().setText(value.getString("email"));
//                        contact.getEditText().setText(value.getString("contact"));
//                    }
//                });
//            }
//        });



        return view;
    }
    public void setdata()
    {
        userID = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("user").document(userID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                kg.setText(value.getString("weight"));
                cm.setText(value.getString("height"));
                name.getEditText().setText(value.getString("name"));
                email.getEditText().setText(value.getString("email"));
                contact.getEditText().setText(value.getString("contact"));
            }
        });
    }
}