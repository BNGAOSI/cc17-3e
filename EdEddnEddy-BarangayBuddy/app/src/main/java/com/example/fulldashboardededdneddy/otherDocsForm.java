package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.fulldashboardededdneddy.databinding.ActivityOtherDocsFormBinding;
import com.example.fulldashboardededdneddy.databinding.ActivityResidencyformBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class otherDocsForm extends AppCompatActivity {

    @NonNull ActivityOtherDocsFormBinding binding;
    private Spinner civilStatus_spinner;
    private EditText birthDateOtherDocs;
    FirebaseAuth auth;


    String dateOfBirth, fullName, age, gender, address, duration, status, documentType;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtherDocsFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        birthDateOtherDocs = findViewById(R.id.birthDateotherDocs);

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
        birthDateOtherDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(otherDocsForm.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                birthDateOtherDocs.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        binding.submitBtnOtherDocs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();

                    fullName = binding.fullNameOtherDocs.getText().toString().trim();
                    age = binding.ageOtherDocs.getText().toString().trim();
                    String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                    address = binding.OtherDocsAddress.getText().toString().trim();
                    dateOfBirth = binding.OtherDocsAddress.getText().toString().trim();
                    duration = binding.duration.getText().toString().trim();

                    // Validate input fields
                    if (TextUtils.isEmpty(fullName)) {
                        Log.d("Validation", "Full name is empty");
                        binding.fullNameOtherDocs.setError("Please enter your full name");
                        binding.fullNameOtherDocs.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(age)) {
                        Log.d("Validation", "Age is empty");
                        binding.ageOtherDocs.setError("Please enter your age");
                        binding.ageOtherDocs.requestFocus();
                        return;
                    }

                    if (selectedCivilStatus.equals("Choose Civil Status")) {
                        Log.d("Validation", "Civil status is not selected");
                        Toast.makeText(otherDocsForm.this, "Please select your civil status", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(gender)) {
                        Log.d("Validation", "Gender is not selected");
                        Toast.makeText(otherDocsForm.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(address)) {
                        Log.d("Validation", "Residential address is empty");
                        binding.OtherDocsAddress.setError("Please enter your residential address");
                        binding.OtherDocsAddress.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(dateOfBirth)) {
                        Log.d("Validation", "Date of birth is empty");
                        binding.birthDateotherDocs.setError("Please enter your date of birth");
                        binding.birthDateotherDocs.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(duration)) {
                        Log.d("Validation", "Duration is empty");
                        binding.duration.setError("Please enter the duration");
                        binding.duration.requestFocus();
                        return;
                    }

                    Log.d("Validation", "All fields are filled. Proceed with submission.");

                    // Proceed with form submission
                    auth = FirebaseAuth.getInstance();
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("RequestedDocuments");
                    String resiUID = reference.child("Others").push().getKey();
                    String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                    DatabaseReference documentTypeRef = reference.child("Others").child(uid).child(resiUID);

                    otherDocsRequests otherDocsRequests = new otherDocsRequests(fullName, age, dateOfBirth, selectedCivilStatus, gender, address, duration, documentType, ServerValue.TIMESTAMP);

                    documentTypeRef.setValue(otherDocsRequests).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            documentTypeRef.child("documentType").setValue("Other Document");

                            binding.fullNameOtherDocs.setText("");
                            binding.ageOtherDocs.setText("");
                            binding.birthDateotherDocs.setText("");
                            binding.OtherDocsAddress.setText("");
                            binding.duration.setText("");
                            Toast.makeText(otherDocsForm.this, "Form successfully submitted", Toast.LENGTH_LONG).show();
                        }

                    });
                }
            }
        });

    }
}