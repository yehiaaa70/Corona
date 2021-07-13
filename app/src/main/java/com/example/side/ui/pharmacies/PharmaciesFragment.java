package com.example.side.ui.pharmacies;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.side.R;
import com.example.side.databinding.FragmentPharmaciesBinding;

public class PharmaciesFragment extends Fragment implements PharmaciesInterface {

    private PharmaciesViewModel pharmaciesViewModel;
    RecyclerView recyclerView;
    PharmaciesAdapter adapter;
    String pharmaciesName[] = {"صيدلية طيبة", "صيدلية العزبي",
            "صيدلية دار مصر", "صيدلية القدس",
            "صيدلية الاندلس", "صيدلية ام المصريين",
            "صيدلية الوطن", "صيدلية النقابة",
            "صيدلية تحيا مصر", "صيدلية المنارة"};
    String pharmaciesPhone[] = {"+201012378901", "+201122378901",
            "+201212378999", "+201127858901",
            "+201111378999", "+201212378999",
            "+201222378999", "+201213338999",
            "+201014378999", "+201212378999"};


    FragmentPharmaciesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pharmaciesViewModel = new ViewModelProvider(this).get(PharmaciesViewModel.class);

        binding = FragmentPharmaciesBinding.inflate(inflater);

        recyclerView = binding.recPharmacies;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PharmaciesAdapter(pharmaciesName, requireActivity(), this);
        recyclerView.setAdapter(adapter);


        return binding.getRoot();

    }

    @Override
    public void getPharmacyPosition(int position) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + pharmaciesPhone[position]));
        requireActivity().startActivity(intent);
    }
}