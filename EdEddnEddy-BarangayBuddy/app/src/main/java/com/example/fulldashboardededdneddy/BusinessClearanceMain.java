package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.fulldashboardededdneddy.databinding.ActivityBusinessClearanceMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BusinessClearanceMain extends BaseActivity {

    ActivityBusinessClearanceMainBinding binding;
    String fullName, dateOfBirth, age, gender, nameOfBusiness, typeOfBusiness, businessAddress, documentType, businessClearancePhone;
    private EditText dateIssuedbtn;
    private DatePickerDialog picker;
    CheckBox checkboxCedula;
    private EditText birthDateBusiness;
    FirebaseDatabase db;
    FirebaseAuth auth;
    Toolbar toolbar;
    DatabaseReference reference;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessClearanceMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Business Clearance");
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

        genderRadioGroup = findViewById(R.id.BusinessgenderRadioGroup);
        birthDateBusiness = findViewById(R.id.birthDateBusiness);

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

        Spinner Duration = findViewById(R.id.businessDurationSpinner);
        Duration.setAdapter(DurationAdapter);

        //Setting up DatePicker on EditText
        birthDateBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(BusinessClearanceMain.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthDateBusiness.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        binding.submitBtnBusinessClearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    gender = selectedRadioButton.getText().toString();
                }

                fullName = binding.fullNameBusinessClearance.getText().toString().trim();
                dateOfBirth = binding.birthDateBusiness.getText().toString().trim();
                age = binding.ageBusiness.getText().toString().trim();
                String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                String selectedDuration = Duration.getSelectedItem().toString();
                nameOfBusiness = binding.businessNameOrEstablishment.getText().toString().trim();
                typeOfBusiness = binding.typeOfBusiness.getText().toString().trim();
                businessAddress = binding.businessLocation.getText().toString().trim();
                businessClearancePhone = binding.businessClearancePhoneNumber.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(fullName)) {
                    Log.d("Validation", "Full name is empty");
                    binding.fullNameBusinessClearance.setError("Please enter your full name");
                    binding.fullNameBusinessClearance.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(dateOfBirth)) {
                    Log.d("Validation", "Date of birth is empty");
                    binding.birthDateBusiness.setError("Please enter your date of birth");
                    binding.birthDateBusiness.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    Log.d("Validation", "Age is empty");
                    binding.ageBusiness.setError("Please enter your age");
                    binding.ageBusiness.requestFocus();
                    return;
                }

                if (selectedCivilStatus.equals("Choose Civil Status")) {
                    Log.d("Validation", "Civil status is not selected");
                    Toast.makeText(BusinessClearanceMain.this, "Please select your civil status", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(gender)) {
                    Log.d("Validation", "Gender is not selected");
                    Toast.makeText(BusinessClearanceMain.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nameOfBusiness)) {
                    Log.d("Validation", "Business name is empty");
                    binding.businessNameOrEstablishment.setError("Please enter the business name");
                    binding.businessNameOrEstablishment.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(typeOfBusiness)) {
                    Log.d("Validation", "Type of business is empty");
                    binding.typeOfBusiness.setError("Please enter the type of business");
                    binding.typeOfBusiness.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(businessAddress)) {
                    Log.d("Validation", "Business address is empty");
                    binding.businessLocation.setError("Please enter the business address");
                    binding.businessLocation.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(businessClearancePhone)) {
                    Log.d("Validation", "Phone Number is empty");
                    binding.businessClearancePhoneNumber.setError("Please enter your Phone Number");
                    binding.businessClearancePhoneNumber.requestFocus();
                    return;
                }

                Log.d("Validation", "All fields are filled. Proceed with submission.");

                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        String userTokenBusiness = task.getResult();

                        // Proceed with form submission
                        auth = FirebaseAuth.getInstance();
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("RequestedDocuments");
                        String businessClearanceUID = reference.child("Business Clearance").push().getKey();
                        String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                        DatabaseReference documentTypeRef = reference.child("Business Clearance").child(uid).child(businessClearanceUID);

                        BusincessClearanceRequests BusinessClearanceRequests = new BusincessClearanceRequests(fullName, dateOfBirth, age,selectedCivilStatus, selectedDuration, gender, nameOfBusiness, typeOfBusiness, businessAddress, documentType, ServerValue.TIMESTAMP, userTokenBusiness, businessClearancePhone);
                        documentTypeRef.setValue(BusinessClearanceRequests).addOnCompleteListener(task1 -> {
                            documentTypeRef.child("documentType").setValue("Business Clearance");
                            documentTypeRef.child("status").setValue("Pending");

                            // Update the checkbox state in the database
                            documentTypeRef.child("hasCedula").setValue(checkboxCedula.isChecked());


                            binding.fullNameBusinessClearance.setText("");
                            binding.ageBusiness.setText("");
                            binding.birthDateBusiness.setText("");
                            binding.businessNameOrEstablishment.setText("");
                            binding.typeOfBusiness.setText("");
                            binding.businessLocation.setText("");
                            binding.businessClearancePhoneNumber.setText("");

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
        AlertDialog.Builder builder = new AlertDialog.Builder(BusinessClearanceMain.this);
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
