package com.example.fulldashboardededdneddy;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
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

import com.example.fulldashboardededdneddy.databinding.ActivityIndigencyCertificateFormBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IndigencyCertificateForm extends BaseActivity {

    ActivityIndigencyCertificateFormBinding binding;

    Toolbar toolbar;
    CheckBox checkboxCedula;

    private EditText birthDateIndigency;
    private EditText durationbtn;
    FirebaseAuth auth;

    String dateOfBirth, fullName, age, gender, address, duration, documentType, indigencyPhoneNumber;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;
    RadioGroup genderRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indigency_certificate_form);
        binding = ActivityIndigencyCertificateFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Indigency Certificate");
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
        birthDateIndigency = findViewById(R.id.birthDateIndigency);

        //Spinner for CivilStatus
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
        birthDateIndigency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(IndigencyCertificateForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDateIndigency.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });




        binding.submitBtnIndigency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();
                }

                fullName = binding.fullNameIndigency.getText().toString().trim();
                age = binding.ageIndigency.getText().toString().trim();
                String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                address = binding.residentialAddress.getText().toString().trim();
                dateOfBirth = binding.birthDateIndigency.getText().toString().trim();
                String selectedDuration = Duration.getSelectedItem().toString();
                indigencyPhoneNumber = binding.indigencyPhoneNumber.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(fullName)) {
                    Log.d("Validation", "Full name is empty");
                    binding.fullNameIndigency.setError("Please enter your full name");
                    binding.fullNameIndigency.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(dateOfBirth)) {
                    Log.d("Validation", "Date of birth is empty");
                    binding.birthDateIndigency.setError("Please enter your date of birth");
                    binding.birthDateIndigency.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    Log.d("Validation", "Age is empty");
                    binding.ageIndigency.setError("Please enter your age");
                    binding.ageIndigency.requestFocus();
                    return;
                }

                if (selectedCivilStatus.equals("Choose Civil Status")) {
                    Log.d("Validation", "Civil status is not selected");
                    Toast.makeText(IndigencyCertificateForm.this, "Please select your civil status", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(gender)) {
                    Log.d("Validation", "Gender is not selected");
                    Toast.makeText(IndigencyCertificateForm.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(indigencyPhoneNumber)) {
                    Log.d("Validation", "Phone Number is empty");
                    binding.indigencyPhoneNumber.setError("Please enter your phone number");
                    binding.indigencyPhoneNumber.requestFocus();
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
                    Toast.makeText(IndigencyCertificateForm.this, "Please select the month/year", Toast.LENGTH_SHORT).show();
                    return;
                }


                Log.d("Validation", "All fields are filled. Proceed with submission.");

                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        String userTokenIndigency = task.getResult();

                        // Proceed with form submission
                        auth = FirebaseAuth.getInstance();
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("RequestedDocuments");
                        String resiUID = reference.child("Indigency").push().getKey();
                        String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                        DatabaseReference documentTypeRef = reference.child("Indigency").child(uid).child(resiUID);

                        IndigencyRequests IndigencyRequests = new IndigencyRequests(fullName, age, dateOfBirth, selectedCivilStatus, gender, address, selectedDuration, documentType, ServerValue.TIMESTAMP, userTokenIndigency, indigencyPhoneNumber);

                        documentTypeRef.setValue(IndigencyRequests).addOnCompleteListener(task1 -> {
                            documentTypeRef.child("documentType").setValue("Certificate of Indigency");
                            documentTypeRef.child("status").setValue("Pending");

                            // Update the checkbox state in the database
                            documentTypeRef.child("hasCedula").setValue(checkboxCedula.isChecked());


                            binding.fullNameIndigency.setText("");
                            binding.ageIndigency.setText("");
                            binding.birthDateIndigency.setText("");
                            binding.residentialAddress.setText("");
                            binding.indigencyPhoneNumber.setText("");
                            Toast.makeText(IndigencyCertificateForm.this, "Form successfully submitted", Toast.LENGTH_LONG).show();
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
}