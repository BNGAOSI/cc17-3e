package com.ambiongltb.fulldashboardededdneddy;

import androidx.annotation.NonNull;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ambiongltb.fulldashboardededdneddy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerification extends BaseActivity {

    private EditText otpEt1, otpEt2, otpEt3, otpEt4, otpEt5, otpEt6;
    private PhoneAuthProvider.ForceResendingToken forceResendingToken;

    private String verificationId;
    private TextView resendBtn;

    //true after every 60 seconds
    private boolean resendEnabled = false;
    //resend time in seconds
    private int resendTime = 60;

    private int selectedETPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);


        otpEt1 = findViewById(R.id.otpET1);
        otpEt2 = findViewById(R.id.otpET2);
        otpEt3 = findViewById(R.id.otpET3);
        otpEt4 = findViewById(R.id.otpET4);
        otpEt5 = findViewById(R.id.otpET5);
        otpEt6 = findViewById(R.id.otpET6);

        resendBtn = findViewById(R.id.resendBtn);

        final Button verifyBtn = findViewById(R.id.verifyBtn);
        final TextView otpMobile = findViewById(R.id.otpMobile);
        final ProgressBar progressBar = findViewById(R.id.progressBar);


        // getting mobile from Register Activity through intent
        final String getMobile = getIntent().getStringExtra("mobile");

        // setting mobile to TextView
        otpMobile.setText(getMobile);

        otpEt1.addTextChangedListener(textWatcher);
        otpEt2.addTextChangedListener(textWatcher);
        otpEt3.addTextChangedListener(textWatcher);
        otpEt4.addTextChangedListener(textWatcher);
        otpEt5.addTextChangedListener(textWatcher);
        otpEt6.addTextChangedListener(textWatcher);

        //by default open keyboard at otpEt1
        showKeyboard(otpEt1);

        //start resend count down timer
        startCountDownTimer();

        forceResendingToken = null;

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the resend functionality is enabled
                if (resendEnabled) {
                    // Resend the OTP
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+63" + getIntent().getStringExtra("mobile"),
                            60,
                            TimeUnit.SECONDS,
                            OTPVerification.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    // Handle verification completed (auto-retrieval of OTP)
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    // Handle verification failure
                                    Toast.makeText(OTPVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                    // Handle code sent (OTP resend)
                                    verificationId = newVerificationId;
                                    forceResendingToken = token; // Save the token for resending
                                    Toast.makeText(OTPVerification.this, "OTP Resent", Toast.LENGTH_SHORT).show();
                                }
                            },
                            forceResendingToken); // Use the token for resending
                } else {
                    // Resend functionality is not enabled (usually during the countdown)
                    Toast.makeText(OTPVerification.this, "Please wait before resending", Toast.LENGTH_SHORT).show();
                }
            }
        });



        verificationId = getIntent().getStringExtra("verificationId");

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (otpEt1.getText().toString().isEmpty() || otpEt2.getText().toString().isEmpty() || otpEt3.getText().toString().isEmpty() || otpEt4.getText().toString().isEmpty() || otpEt5.getText().toString().isEmpty() || otpEt6.getText().toString().isEmpty()) {
                    Toast.makeText(OTPVerification.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                    return;
                }

                String generateOtp = otpEt1.getText().toString() + otpEt2.getText().toString() + otpEt3.getText().toString() + otpEt4.getText().toString() + otpEt5.getText().toString() + otpEt6.getText().toString();

                if (verificationId != null) {
                    progressBar.setVisibility(View.VISIBLE);
                    verifyBtn.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, generateOtp);
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            verifyBtn.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(OTPVerification.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                if (generateOtp.length() == 6) {
                    //handle your otp verification here
                }
            }
        });
    }

    private void showKeyboard(EditText otpET) {
        otpET.requestFocus();

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer() {
        resendEnabled = false;
        resendBtn.setTextColor(Color.parseColor("#99000000"));

        new CountDownTimer(resendTime * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                resendBtn.setText("Resend Code (" + (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
                resendBtn.setText("Resend Code");
                resendBtn.setTextColor(getResources().getColor(R.color.primaryTextColor));
            }
        }.start();
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.length() > 0) {

                if (selectedETPosition == 0) {
                    selectedETPosition = 1;
                    showKeyboard(otpEt2);

                } else if (selectedETPosition == 1) {
                    selectedETPosition = 2;
                    showKeyboard(otpEt3);

                } else if (selectedETPosition == 2) {
                    selectedETPosition = 3;
                    showKeyboard(otpEt4);
                } else if (selectedETPosition == 3) {
                    selectedETPosition = 4;
                    showKeyboard(otpEt5);
                } else if (selectedETPosition == 4) {
                    selectedETPosition = 5;
                    showKeyboard(otpEt6);
                }
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (selectedETPosition == 5) {
                selectedETPosition = 4;
                showKeyboard(otpEt5);
            } else if (selectedETPosition == 4) {
                selectedETPosition = 3;
                showKeyboard(otpEt4);
            } else if (selectedETPosition == 3) {

                selectedETPosition = 2;
                showKeyboard(otpEt3);
            } else if (selectedETPosition == 2) {
                selectedETPosition = 1;

                showKeyboard(otpEt2);
            } else if (selectedETPosition == 1) {
                selectedETPosition = 0;
                showKeyboard(otpEt1);
            }

            return true;
        } else {
            return super.onKeyUp(keyCode, event);
        }
    }
}