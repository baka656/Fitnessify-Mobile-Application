package edu.sjsu.android.fitnessify;

import static android.content.Context.MODE_PRIVATE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Profile extends Fragment {

    //declare variable
    TextInputLayout name,email,contact;
    FirebaseFirestore fire_store;
    FirebaseAuth fAuth;
    String consumer_id;
    ImageView profile_img;
    Button update_bn, sign_out_bn;
    EditText unit_kg, unit_cm;

    public Profile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // profile data method
    public void set_profile_data()
    {
        consumer_id = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
        DocumentReference reference_document = fire_store.collection("user").document(consumer_id);
        reference_document.addSnapshotListener((result, error) -> {
            assert result != null;
            // weight in kg
            unit_kg.setText(result.getString("weight"));
            // height in cm
            unit_cm.setText(result.getString("height"));
            Objects.requireNonNull(email.getEditText()).setText(result.getString("email"));
            Objects.requireNonNull(contact.getEditText()).setText(result.getString("contact"));
            Objects.requireNonNull(name.getEditText()).setText(result.getString("name"));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_profile, container, false);
        // fragment view field
        sign_out_bn = fragment_view.findViewById(R.id.sign_out_button);
        unit_kg = fragment_view.findViewById(R.id.weight_label);
        unit_cm = fragment_view.findViewById(R.id.height_label);
        name = fragment_view.findViewById(R.id.full_name_profile);
        email = fragment_view.findViewById(R.id.profile_email);
        contact = fragment_view.findViewById(R.id.contact_profile);
        update_bn = fragment_view.findViewById(R.id.update_button);
        profile_img = fragment_view.findViewById(R.id.user_image);

        fire_store = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        set_profile_data();
        update_bn.setOnClickListener(res -> {
            String profile_name = Objects.requireNonNull(name.getEditText()).getText().toString();
            String profile_email = Objects.requireNonNull(email.getEditText()).getText().toString();
            String profile_contact = Objects.requireNonNull(contact.getEditText()).getText().toString();
            String profile_weight = unit_kg.getText().toString();
            String profile_height = unit_cm.getText().toString();
            consumer_id = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
            DocumentReference reference_document = fire_store.collection("user").document(consumer_id);
            Map<String,Object> consumer = new HashMap<>();
            consumer.put(getString(R.string.l_weight),profile_weight);
            consumer.put(getString(R.string.l_height),profile_height);
            consumer.put(getString(R.string.email_id),profile_email);
            consumer.put(getString(R.string.your_name),profile_name);
            consumer.put(getString(R.string.l_contact),profile_contact);
            reference_document.set(consumer).addOnSuccessListener(result -> Toast.makeText(getActivity(), R.string.profile_updated, Toast.LENGTH_SHORT).show());
        });
        // on click listener
        sign_out_bn.setOnClickListener(res -> {
            SharedPreferences shared_Preferences = requireActivity().getSharedPreferences("consumer", MODE_PRIVATE);
            SharedPreferences.Editor edit = shared_Preferences.edit();
            edit.putString(getString(R.string.email_id), "");
            edit.apply();
            Toast.makeText(getActivity(), R.string.signed_out, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(),MainActivity.class);
            startActivity(i);
        });
        return fragment_view;
    }

}