package com.example.side.ui.patient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.side.R;
import com.example.side.databinding.FragmentNurseProfileBinding;

import org.jetbrains.annotations.NotNull;


public class nurse_profile extends Fragment {


    FragmentNurseProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNurseProfileBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            nurse_profileArgs args = nurse_profileArgs.fromBundle(getArguments());
            binding.nurseNameNurseProfile.setText(args.getNurseModel().getNurseName());
            binding.nursePhoneNurseProfile.setText(args.getNurseModel().getNursePhone());
            binding.nurseAgeNurseProfile.setText(String.valueOf(args.getNurseModel().getNurseAge()));
            binding.nursePriceNurseProfile.setText(args.getNurseModel().getNursePrice());
            binding.bookingNurseProfile.setText(args.getNurseModel().getNurseBooking());
            binding.hospitalAddressNurseProfile.setText(args.getNurseModel().getNurseHospitalAddress());

        }
    }
}