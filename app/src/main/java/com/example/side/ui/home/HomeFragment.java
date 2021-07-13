package com.example.side.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.side.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


     View i2 =(View) view.findViewById(R.id.critical2) ;
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in2=new Intent(getActivity(), Critical_Issue.class);
                startActivity(in2);
            }

        });



        return view;
    }
}