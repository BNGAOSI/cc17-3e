package com.ambiongltb.fulldashboardededdneddy;

import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ambiongltb.fulldashboardededdneddy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends BaseActivity {

    private boolean passwordShowing = false;
    private boolean conPasswordShowing = false;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        final EditText email = findViewById(R.id.emailET);

        final EditText password = findViewById(R.id.passwordET);
        final EditText conPassword = findViewById(R.id.conPasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon = findViewById(R.id.conPasswordIcon);

        final AppCompatButton signUpBtn = findViewById(R.id.signUpBtn);
        final ProgressBar progressBar = findViewById(R.id.progressBar);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checking if password is showing or not
                if (passwordShowing) {
                    passwordShowing = false;
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.eye_password_show);
                } else {
                    passwordShowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.eye_password_hide);

                }
                // move the cursor at last of the text
                password.setSelection(password.length());
            }
        });

        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checking if password is showing or not
                if (conPasswordShowing) {
                    conPasswordShowing = false;
                    conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.eye_password_show);
                } else {
                    conPasswordShowing = true;
                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    conPasswordIcon.setImageResource(R.drawable.eye_password_hide);

                }
                // move the cursor at last of the text
                conPassword.setSelection(conPassword.length());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String getEmailTxt = email.getText().toString();
                final String getPassword = password.getText().toString();
                final String getConPassword = conPassword.getText().toString();


                if (!getPassword.equals(getConPassword)) {
                    Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                signUpBtn.setVisibility(View.INVISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(getEmailTxt, getPassword)
                        .addOnCompleteListener(Register.this, task -> {
                            progressBar.setVisibility(View.GONE);
                            signUpBtn.setVisibility(View.VISIBLE);

                            if (task.isSuccessful()) {
                                // Registration successful
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                // Registration failed
                                Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}