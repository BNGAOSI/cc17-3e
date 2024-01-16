package com.example.fulldashboardededdneddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fulldashboardededdneddy.databinding.ActivityBarangayclearanceformBinding;
import com.example.fulldashboardededdneddy.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangayClearanceForm extends AppCompatActivity {

    ActivityBarangayclearanceformBinding binding;
    String firstName, lastName, age, dateOfBirth, presentAddress, purpose;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarangayclearanceformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                dateOfBirth = binding.birthDate.getText().toString();
                presentAddress = binding.presentAddress.getText().toString();
                purpose = binding.purpose.getText().toString();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !dateOfBirth.isEmpty() && !presentAddress.isEmpty() && !purpose.isEmpty()){

                    BarangayClearanceRequests BarangayClearanceRequests = new BarangayClearanceRequests(firstName,lastName,age,dateOfBirth,presentAddress,purpose);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("BarangayClearanceRequests");
                    reference.child(lastName).setValue(BarangayClearanceRequests).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.firstName.setText("");
                            binding.lastName.setText("");
                            binding.age.setText("");
                            binding.birthDate.setText("");
                            binding.presentAddress.setText("");
                            binding.purpose.setText("");
                            Toast.makeText(BarangayClearanceForm.this,"Request successully submitted",Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });
    }
}