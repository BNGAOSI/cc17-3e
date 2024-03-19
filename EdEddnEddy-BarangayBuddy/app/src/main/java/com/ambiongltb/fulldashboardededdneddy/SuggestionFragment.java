package com.ambiongltb.fulldashboardededdneddy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ambiongltb.fulldashboardededdneddy.model.SuggestionModel;
import com.ambiongltb.fulldashboardededdneddy.R;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;


public class SuggestionFragment extends Fragment {

    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_suggest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null && getActivity().getActionBar() != null) {
            getActivity().getActionBar().hide();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Suggestions");

        view.findViewById(R.id.btnSaveData).setOnClickListener(v -> saveSuggestionData());
    }

    private void saveSuggestionData() {
        EditText etSugName = requireView().findViewById(R.id.etSugName);
        EditText etSuggest = requireView().findViewById(R.id.etSuggest);

        String sugName = etSugName.getText().toString();
        String suggest = etSuggest.getText().toString();

        if (suggest.isEmpty()) {
            etSuggest.setError("Please Enter Suggestion");
        } else {
            DatabaseReference suggestionReference = databaseReference.push();
            String sugId = suggestionReference.getKey();

            if (sugId != null) {
                // Get checkbox states
                CheckBox ckboxHealth = requireView().findViewById(R.id.checkboxHealth);
                CheckBox ckboxEducation = requireView().findViewById(R.id.checkboxEducation);
                CheckBox ckboxSports = requireView().findViewById(R.id.checkboxSports);
                CheckBox ckboxBarangayImprv = requireView().findViewById(R.id.checkboxBarangayImprovements);
                CheckBox ckboxEtc = requireView().findViewById(R.id.checkboxEtc);

                EditText etEtcDetails = requireView().findViewById(R.id.etEtcDetails);
                String etcDetails = etEtcDetails.getText().toString();

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
                        .addOnCompleteListener(task -> {
                            Toast.makeText(requireContext(), "Suggestion sent successfully", Toast.LENGTH_LONG).show();
                            etSugName.getText().clear();
                            etSuggest.getText().clear();
                            etEtcDetails.getText().clear();
                        })
                        .addOnFailureListener(err -> {
                            Toast.makeText(requireContext(), "Error " + err.getMessage(), Toast.LENGTH_LONG).show();
                        });
            }
        }
    }
}