package com.example.fulldashboardededdneddy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.fulldashboardededdneddy.databinding.ActivityBarangayclearanceformBinding;
import com.example.fulldashboardededdneddy.databinding.ActivityResidencyformBinding;

import java.util.Calendar;


public class residencyform extends AppCompatActivity {

    ActivityResidencyformBinding binding;
    private EditText birthDateresidency;

    String dateOfBirth;

    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResidencyformBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_residencyform);


    }
}