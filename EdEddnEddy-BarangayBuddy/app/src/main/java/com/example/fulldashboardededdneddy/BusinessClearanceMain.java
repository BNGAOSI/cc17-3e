package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fulldashboardededdneddy.databinding.ActivityBusinessClearanceMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;

public class BusinessClearanceMain extends AppCompatActivity {

    ActivityBusinessClearanceMainBinding binding;
    String fullName, nameOfBusiness, typeOfBusiness, businessAddress, documentType;
    private EditText dateIssuedbtn;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    FirebaseAuth auth;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessClearanceMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.submitBtnBusinessClearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = binding.fullNameBusinessClearance.getText().toString().trim();
                nameOfBusiness = binding.businessNameOrEstablishment.getText().toString().trim();
                typeOfBusiness = binding.typeOfBusiness.getText().toString().trim();
                businessAddress = binding.businessLocation.getText().toString().trim();

                // Validate input fields
                if (TextUtils.isEmpty(fullName)) {
                    Log.d("Validation", "Full name is empty");
                    binding.fullNameBusinessClearance.setError("Please enter your full name");
                    binding.fullNameBusinessClearance.requestFocus();
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

                Log.d("Validation", "All fields are filled. Proceed with submission.");

                // Proceed with form submission
                auth = FirebaseAuth.getInstance();
                db = FirebaseDatabase.getInstance();
                reference = db.getReference("RequestedDocuments");
                String businessClearanceUID = reference.child("Business Clearance").push().getKey();
                String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "null";

                DatabaseReference documentTypeRef = reference.child("Business Clearance").child(uid).child(businessClearanceUID);

                BusincessClearanceRequests BusinessClearanceRequests = new BusincessClearanceRequests(fullName, nameOfBusiness, typeOfBusiness, businessAddress, documentType, ServerValue.TIMESTAMP);
                documentTypeRef.setValue(BusinessClearanceRequests).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        documentTypeRef.child("documentType").setValue("Business Clearance");

                        binding.fullNameBusinessClearance.setText("");
                        binding.businessNameOrEstablishment.setText("");
                        binding.typeOfBusiness.setText("");
                        binding.businessLocation.setText("");
                        Toast.makeText(BusinessClearanceMain.this, "Form successfully submitted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}