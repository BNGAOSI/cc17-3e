package com.example.fulldashboardededdneddy;

import static androidx.core.content.ContentProviderCompat.requireContext;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarangayClearanceForm extends BaseActivity {

    ActivityBarangayclearanceformBinding binding;

    Toolbar toolbar;
    String fullName, age, dateOfBirth, presentAddress, duration, purpose, gender, documentType, phoneNumber;
    private EditText birthDate;
    private DatePickerDialog picker;
    CheckBox checkboxCedula;
    FirebaseDatabase db;
    DatabaseReference reference;

    DatabaseReference documentTypeName;
    FirebaseAuth auth;

    RadioGroup genderRadioGroup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarangayclearanceformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressBar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.appToolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Barangay Clearance");
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
        birthDate = findViewById(R.id.birthDate);

        //Spinner for civilStatus

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
                }

                fullName = binding.fullNameBarangayClearance.getText().toString().trim();
                age = binding.age.getText().toString().trim();
                String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                dateOfBirth = binding.birthDate.getText().toString().trim();
                presentAddress = binding.presentAddress.getText().toString().trim();
                String selectedDuration = Duration.getSelectedItem().toString();
                purpose = binding.purpose.getText().toString().trim();
                phoneNumber = binding.barangayClearancePhoneNumber.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(fullName)) {
                    Log.d("Validation", "Full name is empty");
                    binding.fullNameBarangayClearance.setError("Please enter your full name");
                    binding.fullNameBarangayClearance.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(dateOfBirth)) {
                    Log.d("Validation", "Date of birth is empty");
                    binding.birthDate.setError("Please enter your date of birth");
                    binding.birthDate.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(age)) {
                    Log.d("Validation", "Age is empty");
                    binding.age.setError("Please enter your age");
                    binding.age.requestFocus();
                    return;
                }

                if (selectedCivilStatus.equals("Choose Civil Status")) {
                    Log.d("Validation", "Civil status is not selected");
                    Toast.makeText(BarangayClearanceForm.this, "Please select your civil status", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(gender)) {
                    Log.d("Validation", "Gender is not selected");
                    Toast.makeText(BarangayClearanceForm.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Log.d("Validation", "Phone Number is empty");
                    binding.barangayClearancePhoneNumber.setError("Please enter your phone number");
                    binding.barangayClearancePhoneNumber.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(presentAddress)) {
                    Log.d("Validation", "Present address is empty");
                    binding.presentAddress.setError("Please enter your present address");
                    binding.presentAddress.requestFocus();
                    return;
                }

                if (selectedDuration.equals("---")) {
                    Log.d("Validation", "This field is empty");
                    Toast.makeText(BarangayClearanceForm.this, "Please select the month/year", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(purpose)) {
                    Log.d("Validation", "Purpose is empty");
                    binding.purpose.setError("Please enter the purpose of your request");
                    binding.purpose.requestFocus();
                    return;
                }


                Log.d("Validation", "All fields are filled. Proceed with submission.");

                // Show ProgressBar when button is clicked
                progressBar.setVisibility(View.VISIBLE);


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


                        BarangayClearanceRequests barangayClearanceRequests = new BarangayClearanceRequests(fullName, age, dateOfBirth, presentAddress, selectedCivilStatus, selectedDuration, purpose, gender, documentType, ServerValue.TIMESTAMP, userTokenBarangayClearance, phoneNumber);


                        documentTypeRef.setValue(barangayClearanceRequests).addOnCompleteListener(task1 -> {
                            documentTypeRef.child("documentType").setValue("Barangay Clearance");
                            documentTypeRef.child("status").setValue("Pending");
                            // Update the checkbox state in the database
                            documentTypeRef.child("hasCedula").setValue(checkboxCedula.isChecked());

                            binding.fullNameBarangayClearance.setText("");
                            binding.age.setText("");
                            binding.birthDate.setText("");
                            binding.barangayClearancePhoneNumber.setText("");
                            binding.presentAddress.setText("");
                            binding.purpose.setText("");

                            showConfirmationDialog();
                        });
                    } else {
                        String defaultToken = "default_token";
                    }

                });
                // Hide ProgressBar when operation is complete
                progressBar.setVisibility(View.GONE);

            }
        });


    }

    private void updateFirebaseDatabase(boolean isChecked) {

    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BarangayClearanceForm.this);
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
