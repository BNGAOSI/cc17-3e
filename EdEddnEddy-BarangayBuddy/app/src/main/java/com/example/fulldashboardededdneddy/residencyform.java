package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fulldashboardededdneddy.databinding.ActivityBarangayclearanceformBinding;
import com.example.fulldashboardededdneddy.databinding.ActivityResidencyformBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class residencyform extends AppCompatActivity {

    ActivityResidencyformBinding binding;
    private Spinner civilStatus_spinner;
    Toolbar toolbar;
    private EditText birthDateResidency;
    private EditText durationbtn;
    FirebaseAuth auth;


    String dateOfBirth, fullName, age, gender, address, duration, status, documentType;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResidencyformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Residency Certificate");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        birthDateResidency = findViewById(R.id.birthDateResidency);

        List<String> civilStatusList = new ArrayList<>();
        civilStatusList.add(0, "Choose Civil Status");
        civilStatusList.add("Single");
        civilStatusList.add("Married");
        civilStatusList.add("Divorced");
        civilStatusList.add("Widowed");

        ArrayAdapter<String> civilStatusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, civilStatusList);
        civilStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner civilStatus = findViewById(R.id.civilstatus_spinner);
        civilStatus.setAdapter(civilStatusAdapter);


        //Setting up DatePicker on EditText
        birthDateResidency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(residencyform.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDateResidency.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        durationbtn = findViewById(R.id.duration);
        durationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(residencyform.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        durationbtn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        binding.submitBtnResidency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();

                    fullName = binding.fullNameResidency.getText().toString().trim();
                    age = binding.ageResidency.getText().toString().trim();
                    String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                    address = binding.residentialAddress.getText().toString().trim();
                    dateOfBirth = binding.birthDateResidency.getText().toString().trim();
                    duration = binding.duration.getText().toString().trim();

                    // Validate input fields
                    if (TextUtils.isEmpty(fullName)) {
                        Log.d("Validation", "Full name is empty");
                        binding.fullNameResidency.setError("Please enter your full name");
                        binding.fullNameResidency.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(age)) {
                        Log.d("Validation", "Age is empty");
                        binding.ageResidency.setError("Please enter your age");
                        binding.ageResidency.requestFocus();
                        return;
                    }

                    if (selectedCivilStatus.equals("Choose Civil Status")) {
                        Log.d("Validation", "Civil status is not selected");
                        Toast.makeText(residencyform.this, "Please select your civil status", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(gender)) {
                        Log.d("Validation", "Gender is not selected");
                        Toast.makeText(residencyform.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(address)) {
                        Log.d("Validation", "Residential address is empty");
                        binding.residentialAddress.setError("Please enter your residential address");
                        binding.residentialAddress.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(dateOfBirth)) {
                        Log.d("Validation", "Date of birth is empty");
                        binding.birthDateResidency.setError("Please enter your date of birth");
                        binding.birthDateResidency.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(duration)) {
                        Log.d("Validation", "Duration is empty");
                        binding.duration.setError("Please enter the duration");
                        binding.duration.requestFocus();
                        return;
                    }

                    Log.d("Validation", "All fields are filled. Proceed with submission.");

                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            String userTokenResidency = task.getResult();

                            // Proceed with form submission
                            auth = FirebaseAuth.getInstance();
                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("RequestedDocuments");
                            String resiUID = reference.child("Residency").push().getKey();
                            String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                            DatabaseReference documentTypeRef = reference.child("Residency").child(uid).child(resiUID);

                            residencyrequests residencyrequests = new residencyrequests(fullName, age, dateOfBirth, selectedCivilStatus, gender, address, duration, documentType, ServerValue.TIMESTAMP, userTokenResidency);

                            documentTypeRef.setValue(residencyrequests).addOnCompleteListener(task1 -> {
                                documentTypeRef.child("documentType").setValue("Certificate of Residency");

                                binding.fullNameResidency.setText("");
                                binding.ageResidency.setText("");
                                binding.birthDateResidency.setText("");
                                binding.residentialAddress.setText("");
                                binding.duration.setText("");
                                Toast.makeText(residencyform.this, "Form successfully submitted", Toast.LENGTH_LONG).show();
                            });
                        } else {
                            String defaultToken = "default_token";
                        }

                    });
                }
            }
        });

    }
}