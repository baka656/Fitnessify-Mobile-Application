package edu.sjsu.android.fitnessify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {

    // declare variable for register page
    String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView tv;
    EditText email, pwd, name, mobile;
    Button reg;
    FirebaseFirestore fire_store;
    FirebaseAuth firebase_auth, mAuth;
    String consumer_id;
    ProgressDialog dialog;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // adding the field for user input
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        name = findViewById(R.id.name_input);
        mobile = findViewById(R.id.field_mobile);
        reg = findViewById(R.id.register_button);
        email = findViewById(R.id.field_email);
        pwd = findViewById(R.id.field_password);
        dialog = new ProgressDialog(this);
        tv = findViewById(R.id.existing_user);
        // checking for validate auth
        reg.setOnClickListener(res -> ValidateAuth());
        tv.setOnClickListener(res -> startActivity(new Intent(Register.this, MainActivity.class)));
    }

    private void navigateToActivity() {
        Intent i = new Intent(Register.this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    // validate method for email, password, name, and mobile number length
    private void ValidateAuth() {
        String email = this.email.getText().toString();
        String password = pwd.getText().toString();
        String mob = mobile.getText().toString();
        String name = this.name.getText().toString();
        if(!email.matches(pattern))
            this.email.setError(getString(R.string.enter_vemail));
        else if(password.isEmpty() || password.length()<6)
            pwd.setError(getString(R.string.enter_6_pwd));
        else if(name.matches(""))
            this.name.setError(getString(R.string.name_not_empty));
        else if(mob.length()!=10)
            mobile.setError(getString(R.string.valid_mob_num));
        else
        {
            dialog.setMessage(getString(R.string.wait_till_reg));
            dialog.setTitle(R.string.registration);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(res -> {
                if(res.isSuccessful())
                {
                    dialog.dismiss();
                    fire_store = FirebaseFirestore.getInstance();
                    firebase_auth = FirebaseAuth.getInstance();
                    consumer_id = Objects.requireNonNull(firebase_auth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fire_store.collection("user").document(consumer_id);
                    Map<String,Object> consumer = new HashMap<>();
                    consumer.put(getString(R.string.your_name),name);
                    consumer.put(getString(R.string.email_id),email);
                    consumer.put(getString(R.string.l_contact),mob);
                    consumer.put(getString(R.string.weight),"-");
                    consumer.put(getString(R.string.height),"-");
                    documentReference.set(consumer).addOnSuccessListener(task -> Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show());
                    navigateToActivity();
                    Toast.makeText(Register.this,R.string.registrationSuccessful,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dialog.dismiss();
                    Toast.makeText(Register.this,"Registration Failed due to " + res.getException(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
