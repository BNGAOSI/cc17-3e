package com.ambiongltb.fulldashboardededdneddy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ambiongltb.fulldashboardededdneddy.R;
import com.ambiongltb.fulldashboardededdneddy.databinding.ActivityResidencyformBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class residencyform extends BaseActivity {

    ActivityResidencyformBinding binding;
    Toolbar toolbar;
    CheckBox checkboxCedula;
    private EditText birthDateResidency;
    private EditText durationbtn;
    FirebaseAuth auth;


    String dateOfBirth, fullName, age, gender, address, duration, documentType, residencyPhoneNumber;
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

        //Checkbox Cedula
        checkboxCedula = findViewById(R.id.checkboxCedula);

        // Set up the click listener for the checkbox
        checkboxCedula.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Checkbox is checked, perform actions accordingly
                updateFirebaseDatabase(true);
            } else {
                // Checkbox is unchecked, perform actions accordingly
                updateFirebaseDatabase(false);
            }
        });


        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        birthDateResidency = findViewById(R.id.birthDateResidency);

        //Spinner for Civil Status
        List<String> civilStatusList = new ArrayList<>();
        civilStatusList.add(0, "Choose Civil Status");
        civilStatusList.add("Single");
        civilStatusList.add("Married");
        civilStatusList.add("Divorced");
        civilStatusList.add("Separated");
        civilStatusList.add("Widowed");

        ArrayAdapter<String> civilStatusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, civilStatusList);
        civilStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner civilStatus = findViewById(R.id.civilstatus_spinner);
        civilStatus.setAdapter(civilStatusAdapter);

        //Spinner for Duration
        List<String> DurationList = new ArrayList<>();
        DurationList.add(0, "---");
        DurationList.add("1 month");
        DurationList.add("2 months");
        DurationList.add("3 months");
        DurationList.add("4 months");
        DurationList.add("5 months");
        DurationList.add("6 months");
        DurationList.add("7 months");
        DurationList.add("8 months");
        DurationList.add("9 months");
        DurationList.add("10 months");
        DurationList.add("11 months");
        DurationList.add("1 year");
        DurationList.add("2 years");
        DurationList.add("3 years");
        DurationList.add("4 years");
        DurationList.add("5 years");
        DurationList.add("6 years");
        DurationList.add("7 years");
        DurationList.add("8 years");
        DurationList.add("9 years");
        DurationList.add("10 years");
        DurationList.add("more than 10 years");

        ArrayAdapter<String> DurationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DurationList);
        DurationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner Duration = findViewById(R.id.durationSpinner);
        Duration.setAdapter(DurationAdapter);


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


        binding.submitBtnResidency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();
                }

                fullName = binding.fullNameResidency.getText().toString().trim();
                age = binding.ageResidency.getText().toString().trim();
                String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                String selectedDuration = Duration.getSelectedItem().toString();
                address = binding.residentialAddress.getText().toString().trim();
                dateOfBirth = binding.birthDateResidency.getText().toString().trim();
                residencyPhoneNumber = binding.residencyPhoneNumber.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(fullName)) {
                    Log.d("Validation", "Full name is empty");
                    binding.fullNameResidency.setError("Please enter your full name");
                    binding.fullNameResidency.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(dateOfBirth)) {
                    Log.d("Validation", "Date of birth is empty");
                    binding.birthDateResidency.setError("Please enter your date of birth");
                    binding.birthDateResidency.requestFocus();
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
                if (TextUtils.isEmpty(residencyPhoneNumber)) {
                    Log.d("Validation", "Phone Number is empty");
                    binding.residencyPhoneNumber.setError("Please enter your phone number");
                    binding.residencyPhoneNumber.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(address)) {
                    Log.d("Validation", "Residential address is empty");
                    binding.residentialAddress.setError("Please enter your residential address");
                    binding.residentialAddress.requestFocus();
                    return;
                }



                if (selectedDuration.equals("---")) {
                    Log.d("Validation", "This field is empty");
                    Toast.makeText(residencyform.this, "Please select the month/year", Toast.LENGTH_SHORT).show();
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

                        residencyrequests residencyrequests = new residencyrequests(fullName, age, dateOfBirth, selectedCivilStatus, gender, address, selectedDuration,documentType, ServerValue.TIMESTAMP, userTokenResidency, residencyPhoneNumber);

                        documentTypeRef.setValue(residencyrequests).addOnCompleteListener(task1 -> {
                            documentTypeRef.child("documentType").setValue("Certificate of Residency");
                            documentTypeRef.child("status").setValue("Pending");

                            // Update the checkbox state in the database
                            documentTypeRef.child("hasCedula").setValue(checkboxCedula.isChecked());


                            binding.fullNameResidency.setText("");
                            binding.ageResidency.setText("");
                            binding.birthDateResidency.setText("");
                            binding.residentialAddress.setText("");
                            binding.residencyPhoneNumber.setText("");
                            showConfirmationDialog();
                        });
                    } else {
                        String defaultToken = "default_token";
                    }

                });

            }
        });

    }

    private void updateFirebaseDatabase(boolean b) {
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(residencyform.this);
        builder.setTitle("Requested Document Sent");
        builder.setMessage("Your requested document has been sent to your Barangay Officials. You can monitor updates of your document at the Document Status screen.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}