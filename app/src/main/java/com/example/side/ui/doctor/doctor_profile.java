package com.example.side.ui.doctor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.side.R;
import com.example.side.databinding.FragmentDoctorProfileBinding;

import org.jetbrains.annotations.NotNull;


public class doctor_profile extends Fragment {

    FragmentDoctorProfileBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDoctorProfileBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            doctor_profileArgs args = doctor_profileArgs.fromBundle(getArguments());
            binding.doctorNameDoctorProfile.setText(args.getDoctorProfileData().name);
            binding.doctorPhoneDoctorProfile.setText(args.getDoctorProfileData().phone);
            binding.doctorAgeDoctorProfile.setText(String.valueOf(args.getDoctorProfileData().age));
            binding.doctorAddressDoctorProfile.setText(args.getDoctorProfileData().address);
            binding.doctorPriceDoctorProfile.setText(args.getDoctorProfileData().price);
            binding.bookingDoctorProfile.setText(args.getDoctorProfileData().booking);
            binding.clincksAddressDoctorProfile.setText(args.getDoctorProfileData().clincksAddress);
            binding.certificationsDoctorProfile.setText(args.getDoctorProfileData().certifications);
        }
    }
}