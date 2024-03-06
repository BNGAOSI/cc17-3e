package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        final TextView signInBtn = findViewById(R.id.signInBtn);

        final EditText inputMobile = findViewById(R.id.inputMobile);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMobile.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SendOTPActivity.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+63" + inputMobile.getText().toString(), 60, TimeUnit.SECONDS, SendOTPActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                        Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        buttonGetOTP.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                        intent.putExtra("mobile", inputMobile.getText().toString());
                        intent.putExtra("verificationId", verificationId);
                        startActivity(intent);
                    }
                });

            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}