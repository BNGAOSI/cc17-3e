package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;
import java.util.Map;

public class BarangayClearanceForm extends AppCompatActivity {

    ActivityBarangayclearanceformBinding binding;
    String fullName, age, dateOfBirth, presentAddress, purpose, gender;
    private EditText birthDate;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;

    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarangayclearanceformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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

                    fullName = binding.fullNameBarangayClearance.getText().toString();
                    age = binding.age.getText().toString();
                    dateOfBirth = binding.birthDate.getText().toString();
                    presentAddress = binding.presentAddress.getText().toString();
                    purpose = binding.purpose.getText().toString();


                    if (!fullName.isEmpty() && !age.isEmpty() && !dateOfBirth.isEmpty() && !presentAddress.isEmpty() && !purpose.isEmpty() && !gender.isEmpty()) {


                        BarangayClearanceRequests BarangayClearanceRequests = new BarangayClearanceRequests(fullName, age, dateOfBirth, presentAddress, purpose, gender, ServerValue.TIMESTAMP);
                        db = FirebaseDatabase.getInstance();
                        reference = db.getReference("RequestedDocuments");
                        String barangayClearanceUID = reference.child("Barangay Clearance").push().getKey();

                        reference.child("Barangay Clearance").child(barangayClearanceUID).setValue(BarangayClearanceRequests).addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                binding.fullNameBarangayClearance.setText("");
                                binding.age.setText("");
                                binding.birthDate.setText("");
                                binding.presentAddress.setText("");
                                binding.purpose.setText("");
                                Toast.makeText(BarangayClearanceForm.this, "Request successfully submitted", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}
