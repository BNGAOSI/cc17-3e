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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fulldashboardededdneddy.databinding.ActivityBarangayclearanceformBinding;
import com.example.fulldashboardededdneddy.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BarangayClearanceForm extends AppCompatActivity {

    ActivityBarangayclearanceformBinding binding;

    Toolbar toolbar;
    String fullName, age, dateOfBirth, presentAddress, purpose, gender, documentType;
    private EditText birthDate;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;

    DatabaseReference documentTypeName;
    FirebaseAuth auth;

    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarangayclearanceformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Barangay Clearance");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        birthDate = findViewById(R.id.birthDate);


        //Setting up DatePicker on EditText
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //Date Picker Dialog
                picker = new DatePickerDialog(BarangayClearanceForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();

                    fullName = binding.fullNameBarangayClearance.getText().toString().trim();
                    age = binding.age.getText().toString().trim();
                    dateOfBirth = binding.birthDate.getText().toString().trim();
                    presentAddress = binding.presentAddress.getText().toString().trim();
                    purpose = binding.purpose.getText().toString().trim();

                    // Validate input fields
                    if (TextUtils.isEmpty(fullName)) {
                        Log.d("Validation", "Full name is empty");
                        binding.fullNameBarangayClearance.setError("Please enter your full name");
                        binding.fullNameBarangayClearance.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(age)) {
                        Log.d("Validation", "Age is empty");
                        binding.age.setError("Please enter your age");
                        binding.age.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(dateOfBirth)) {
                        Log.d("Validation", "Date of birth is empty");
                        binding.birthDate.setError("Please enter your date of birth");
                        binding.birthDate.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(presentAddress)) {
                        Log.d("Validation", "Present address is empty");
                        binding.presentAddress.setError("Please enter your present address");
                        binding.presentAddress.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(purpose)) {
                        Log.d("Validation", "Purpose is empty");
                        binding.purpose.setError("Please enter the purpose of your request");
                        binding.purpose.requestFocus();
                        return;
                    }

                    // Check if gender is selected
                    if (TextUtils.isEmpty(gender)) {
                        Log.d("Validation", "Gender is not selected");
                        Toast.makeText(BarangayClearanceForm.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Log.d("Validation", "All fields are filled. Proceed with submission.");

                    FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null) {
                            String userTokenBarangayClearance = task.getResult();

                            // Proceed with form submission
                            auth = FirebaseAuth.getInstance();
                            db = FirebaseDatabase.getInstance();
                            reference = db.getReference("RequestedDocuments");
                            documentTypeName = db.getReference("RequestedDocuments");

                            String barangayClearanceUID = reference.child("Barangay Clearance").push().getKey();
                            String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                            DatabaseReference documentTypeRef = reference.child("Barangay Clearance").child(uid).child(barangayClearanceUID);


                            BarangayClearanceRequests barangayClearanceRequests = new BarangayClearanceRequests(fullName, age, dateOfBirth, presentAddress, purpose, gender, documentType, ServerValue.TIMESTAMP, userTokenBarangayClearance);

                            documentTypeRef.setValue(barangayClearanceRequests).addOnCompleteListener(task1 -> {
                                documentTypeRef.child("documentType").setValue("Barangay Clearance");

                                binding.fullNameBarangayClearance.setText("");
                                binding.age.setText("");
                                binding.birthDate.setText("");
                                binding.presentAddress.setText("");
                                binding.purpose.setText("");
                                Toast.makeText(BarangayClearanceForm.this, "Request successfully submitted", Toast.LENGTH_SHORT).show();
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
