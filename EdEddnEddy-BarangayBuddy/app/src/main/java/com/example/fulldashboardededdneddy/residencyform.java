package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class residencyform extends AppCompatActivity {

    ActivityResidencyformBinding binding;
    private Spinner civilStatus_spinner;
    private EditText birthDateResidency;
    private EditText durationbtn;

    String dateOfBirth, fullName, age, gender, address, duration;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;
    RadioGroup genderRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResidencyformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        genderRadioGroup =findViewById(R.id.genderRadioGroup);
        birthDateResidency = findViewById(R.id.birthDateResidency);

        List<String> civilStatusList = new ArrayList<>();
        civilStatusList.add(0,"Choose Civil Status");
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
                picker = new DatePickerDialog(residencyform.this,
                        new DatePickerDialog.OnDateSetListener() {
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
                picker = new DatePickerDialog(residencyform.this,
                        new DatePickerDialog.OnDateSetListener() {
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

                    fullName = binding.fullNameResidency.getText().toString();
                    age = binding.ageResidency.getText().toString();
                    String selectedCivilStatus = civilStatus.getSelectedItem().toString();
                    address = binding.residentialAddress.getText().toString();
                    dateOfBirth = binding.birthDateResidency.getText().toString();
                    duration = binding.duration.getText().toString();


                    if (!fullName.isEmpty() && !age.isEmpty() && !selectedCivilStatus.equals("Choose Civil Status") && !gender.isEmpty() && !address.isEmpty() && !dateOfBirth.isEmpty() && !duration.isEmpty()) {
                        residencyrequests residencyrequests = new residencyrequests(fullName, age, dateOfBirth, selectedCivilStatus,gender, address, duration, ServerValue.TIMESTAMP);
                        db = FirebaseDatabase.getInstance();

                        reference = db.getReference("RequestedDocuments");
                        String resiUID = reference.child("Residency").push().getKey();
                        reference.child("Residency").child(resiUID).setValue(residencyrequests).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                binding.fullNameResidency.setText("");
                                binding.ageResidency.setText("");
                                binding.birthDateResidency.setText("");
                                binding.residentialAddress.setText("");
                                binding.duration.setText("");
                                Toast.makeText(residencyform.this, "Form successfully submitted", Toast.LENGTH_LONG).show();

                            }

                        });
                    }


                }
            }
        });
    }
}