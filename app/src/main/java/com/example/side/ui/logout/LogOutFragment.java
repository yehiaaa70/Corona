package com.example.side.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.side.R;
import com.example.side.ui.hospital.HospitalViewModel;

public class LogOutFragment extends Fragment {

    private LogOutViewModel LogOutViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LogOutViewModel = new ViewModelProvider(this).get(LogOutViewModel.class);
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        Button b1 = (Button) view.findViewById(R.id.sign_up);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), register.class);
                startActivity(in);
            }

        });

        Button b2 = (Button) view.findViewById(R.id.sign_in);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(getActivity(), login.class);
                startActivity(in);
            }

        });

        return view;

    }
}