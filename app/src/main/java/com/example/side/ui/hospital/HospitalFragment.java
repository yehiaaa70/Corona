package com.example.side.ui.hospital;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.databinding.FragmentHospitalBinding;
import com.example.side.ui.pharmacies.PharmaciesAdapter;
import com.example.side.ui.pharmacies.PharmaciesViewModel;

public class HospitalFragment extends Fragment implements HospitalInterface {

    private HospitalViewModel HospitalViewModel;
    RecyclerView recyclerView;
    HospitalAdapter adapter;
    String hospitalsName[] = {"مستشفي ابن سيناء", "مستشفي الغندور",
            "مستشفي الدفاع الجوي", "مستشفي التحرير",
            "مستشفي القصر العيني", "مستشفي مجدي يعقوب للقلب",
            "المستشفي التخصصي الدولي", "مستشفي 57357",
            "مستشفي العين الجارية", "مستشفي الامل"};
    String hospitalsPhone[] = {"+201012378901", "+201122378901",
            "+201212378999", "+201127858901",
            "+201111378999", "+201212378999",
            "+201222378999", "+201213338999",
            "+201014378999", "+201212378999"};
    FragmentHospitalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HospitalViewModel = new ViewModelProvider(this).get(HospitalViewModel.class);

        binding = FragmentHospitalBinding.inflate(inflater);

        recyclerView = binding.recHospital;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HospitalAdapter(hospitalsName, requireActivity(), this);
        recyclerView.setAdapter(adapter);


        return binding.getRoot();

    }

    @Override
    public void getHospitalPosition(int position) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + hospitalsPhone[position]));
        requireActivity().startActivity(intent);
    }
}
