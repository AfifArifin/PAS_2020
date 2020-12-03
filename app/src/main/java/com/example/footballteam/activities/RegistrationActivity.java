package com.example.footballteam.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballteam.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
     long backpress;
     Toast backToast;
    EditText email, pass;
    Button btnMasuk;
    TextView btnBack;
    ProgressDialog dialog;


    private FirebaseAuth mAuth;

    private static final String TAG = "EmailPassword";

    @Override
    public void onBackPressed() {
        if (backpress + 3000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finishAffinity();
            return;
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backpress = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dialog = new ProgressDialog(RegistrationActivity.this);

        btnMasuk = findViewById(R.id.regLogin);
        btnBack = findViewById(R.id.gotoRegister);
        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        mAuth = FirebaseAuth.getInstance();

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = pass.getText().toString();
                if(TextUtils.isEmpty(password) || pass.length() < 6)
                {
                    pass.setError("You must have 6 characters in your password");
                    return;
                }else{
                    createAccount(email.getText().toString(), pass.getText().toString());

                }

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        dialog.setMessage("Sedang memproses data");
        dialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegistrationActivity.this, "Welcome "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Intent in = new Intent(RegistrationActivity.this,MainMenuActivity.class);
                            startActivity(in);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                    }
                });
        // [END create_user_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        String emaill = email.getText().toString();
        if (TextUtils.isEmpty(emaill)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }

        String password = pass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            pass.setError("Required.");
            valid = false;
        } else {
            pass.setError(null);
        }

        return valid;
    }
}
