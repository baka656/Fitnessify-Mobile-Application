package edu.sjsu.android.fitnessify;

import static android.content.Context.MODE_PRIVATE;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    TextView create_acc;
    EditText input_email, input_pwd;
    Button login_bn;
    String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progress_dialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_login, container, false);
        input_email = fragment_view.findViewById(R.id.field_email);
        input_pwd = fragment_view.findViewById(R.id.field_password);
        login_bn = fragment_view.findViewById(R.id.login_button);
        progress_dialog = new ProgressDialog(getActivity());
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        create_acc = fragment_view.findViewById(R.id.create_account);
        create_acc.setOnClickListener(res -> startActivity(new Intent(getActivity(),Register.class)));
        login_bn.setOnClickListener(res -> check_authentication());
        return fragment_view;
    }

    private void navigateToActivity() {
        Intent task = new Intent(getActivity(),MainActivity.class);
        task.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(task);
    }

    private void check_authentication() {
        String email = input_email.getText().toString();
        String password = input_pwd.getText().toString();
        if(!email.matches(email_pattern))
            input_email.setError(getString(R.string.enter_valid_email));
        else if(password.isEmpty() || password.length()<6)
            input_pwd.setError(getString(R.string.enter_valid_pwd));
        else {
            progress_dialog.setMessage(getString(R.string.wait_till_login));
            progress_dialog.setTitle(getString(R.string.logged_in_success));
            progress_dialog.setCanceledOnTouchOutside(false);
            progress_dialog.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    progress_dialog.dismiss();
                    SharedPreferences preferences_shared = requireActivity().getSharedPreferences(getString(R.string.consumer), MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences_shared.edit();
                    editor.putString(getString(R.string.email_id), email);
                    editor.apply();
                    Toast.makeText(getActivity(),R.string.logged_in,Toast.LENGTH_SHORT).show();
                    navigateToActivity();
                }else
                {
                    progress_dialog.dismiss();
                    Toast.makeText(getActivity(),R.string.invalid_credentials,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
