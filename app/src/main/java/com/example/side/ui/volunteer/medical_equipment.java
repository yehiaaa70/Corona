package com.example.side.ui.volunteer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.side.R;
import com.example.side.databinding.FragmentMedicalEquipmentBinding;

import org.jetbrains.annotations.NotNull;


public class medical_equipment extends Fragment {


    private FragmentMedicalEquipmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMedicalEquipmentBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            medical_equipmentArgs args = medical_equipmentArgs.fromBundle(getArguments());
            binding.volunteerName.setText(args.getVolunteerProfileData().getVolunteerName());
            binding.volunteerPhone.setText(args.getVolunteerProfileData().getVolunteerPhone());
            binding.volunteerAge.setText(String.valueOf(args.getVolunteerProfileData().getVolunteerAge()));
            binding.volunteerAddress.setText(args.getVolunteerProfileData().getVolunteerAddress());
            binding.volunteeritem.setText(args.getVolunteerProfileData().getVolunteer_itemName_price());
            binding.volunteerprice.setText(args.getVolunteerProfileData().getVolunteer_itemName_price());

        }
    }
}