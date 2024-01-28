package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fulldashboardededdneddy.databinding.ActivityBusinessClearanceMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Calendar;

public class BusinessClearanceMain extends AppCompatActivity {

    ActivityBusinessClearanceMainBinding binding;
    String fullName, nameOfBusiness, dateIssued, businessAddress;
    private EditText dateIssuedbtn;
    private DatePickerDialog picker;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessClearanceMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dateIssuedbtn = findViewById(R.id.dateIssuedbtn);

        //Setting up DatePicker on EditText
        dateIssuedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePicker Dialog
                picker = new DatePickerDialog(BusinessClearanceMain.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateIssuedbtn.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                picker.show();
            }
        });

        binding.submitBtnBusinessClearance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullName = binding.fullNameBusinessClearance.getText().toString();
                nameOfBusiness = binding.businessNameOrEstablishment.getText().toString();
                dateIssued = binding.dateIssuedbtn.getText().toString();
                businessAddress = binding.businessLocation.getText().toString();

                if(!fullName.isEmpty() && !nameOfBusiness.isEmpty() && !dateIssued.isEmpty() && !businessAddress.isEmpty()){
                    BusincessClearanceRequests BusinessClearanceRequests = new BusincessClearanceRequests(fullName, nameOfBusiness, dateIssued, businessAddress, ServerValue.TIMESTAMP);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("RequestedDocuments");
                    reference.child("Business Clearance").child(fullName).setValue(BusinessClearanceRequests).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.fullNameBusinessClearance.setText("");
                            binding.businessNameOrEstablishment.setText("");
                            binding.dateIssuedbtn.setText("");
                            binding.businessLocation.setText("");
                            Toast.makeText(BusinessClearanceMain.this,"Form successfully submitted",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}