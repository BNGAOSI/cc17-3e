package com.example.fulldashboardededdneddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fulldashboardededdneddy.R.id;
import com.example.fulldashboardededdneddy.R.layout;
import com.example.fulldashboardededdneddy.model.SuggestionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import kotlin.jvm.internal.Intrinsics;

public final class Suggest extends AppCompatActivity {
    private EditText etSugName;
    private EditText etSuggest;
    private Button btnSaveData;
    private DatabaseReference databaseReference;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_suggest);

        CheckBox ckboxHealth = findViewById(id.checkboxHealth);
        CheckBox ckboxEducation = findViewById(id.checkboxEducation);
        CheckBox ckboxSports = findViewById(id.checkboxSports);
        CheckBox ckboxBarangayImprv = findViewById(id.checkboxBarangayImprovements);
        CheckBox ckboxEtc = findViewById(id.checkboxEtc);


        ImageButton backbutton = this.findViewById(id.goback1);
        backbutton.setOnClickListener(it -> {
            Intent intent = new Intent(Suggest.this, reportAct.class);
            Suggest.this.startActivity(intent);
        });
        Button proceedSuggest1 = this.findViewById(id.btnSaveData);
        proceedSuggest1.setOnClickListener(it -> {
            Intent intent = new Intent(Suggest.this, SuggestNext.class);
            Suggest.this.startActivity(intent);
        });
        View var10001 = this.findViewById(id.etSugName);
        Intrinsics.checkNotNullExpressionValue(var10001, "findViewById(R.id.etSugName)");
        this.etSugName = (EditText) var10001;
        var10001 = this.findViewById(id.etSuggest);
        Intrinsics.checkNotNullExpressionValue(var10001, "findViewById(R.id.etSuggest)");
        this.etSuggest = (EditText) var10001;
        var10001 = this.findViewById(id.btnSaveData);
        Intrinsics.checkNotNullExpressionValue(var10001, "findViewById(R.id.btnSaveData)");
        this.btnSaveData = (Button) var10001;
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Suggestions");
        Intrinsics.checkNotNullExpressionValue(db, "FirebaseDatabase.getInst…tReference(\"Suggestions\")");
        this.databaseReference = db;
        Button btnSaveData = this.btnSaveData;
        if (btnSaveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnSaveData");
        }

        btnSaveData.setOnClickListener(it -> Suggest.this.saveSuggestionData());
    }

    private void saveSuggestionData() {
        EditText etSugName = this.etSugName;
        String sugName = etSugName != null ? etSugName.getText().toString() : "";

        EditText etSuggest = this.etSuggest;
        String suggest = etSuggest != null ? etSuggest.getText().toString() : "";

        if (suggest.isEmpty()) {
            if (etSuggest != null) {
                etSuggest.setError("Please Enter Suggestion");
            }
        } else {
            DatabaseReference suggestionReference = this.databaseReference != null ? this.databaseReference.push() : null;
            if (suggestionReference != null) {
                String sugId = suggestionReference.getKey();
                if (sugId != null) {

                    // Get checkbox states
                    CheckBox ckboxHealth = findViewById(R.id.checkboxHealth);
                    CheckBox ckboxEducation = findViewById(R.id.checkboxEducation);
                    CheckBox ckboxSports = findViewById(R.id.checkboxSports);
                    CheckBox ckboxBarangayImprv = findViewById(R.id.checkboxBarangayImprovements);
                    CheckBox ckboxEtc = findViewById(R.id.checkboxEtc);


                    EditText etEtcDetails = findViewById(R.id.etEtcDetails);
                    String etcDetails = etEtcDetails != null ? etEtcDetails.getText().toString() : "";


                    SuggestionModel suggestion = new SuggestionModel(
                            sugId,
                            sugName,
                            suggest,
                            ServerValue.TIMESTAMP,
                            ckboxHealth.isChecked(),
                            ckboxEducation.isChecked(),
                            ckboxSports.isChecked(),
                            ckboxBarangayImprv.isChecked(),
                            ckboxEtc.isChecked(),
                            etcDetails
                    );


                    suggestionReference.setValue(suggestion)
                            .addOnCompleteListener(it -> {
                                Toast.makeText(Suggest.this, "Suggestion sent successfully", Toast.LENGTH_LONG).show();
                                if (etSugName != null) etSugName.getText().clear();
                                if (etSuggest != null) etSuggest.getText().clear();
                                if (etEtcDetails != null) etEtcDetails.getText().clear();
                            })
                            .addOnFailureListener(err -> {
                                Toast.makeText(Suggest.this, "Error " + err.getMessage(), Toast.LENGTH_LONG).show();
                            });
                }
            }
        }
    }


    // $FF: synthetic method
    public static EditText access$getEtSugName$p(Suggest $this) {
        EditText var10000 = $this.etSugName;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etSugName");
        }

        return var10000;
    }

    // $FF: synthetic method
    public static EditText access$getEtSuggest$p(Suggest $this) {
        EditText var10000 = $this.etSuggest;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("etSuggest");
        }

        return var10000;
    }

}
