package com.example.side.ui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.databinding.FragmentDoctorBinding;
import com.example.side.ui.logout.register;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoctorFragment extends Fragment implements DoctorInterface {

    private DoctorViewModel DoctorViewModel;
    RecyclerView recyclerView;
    doctorAdapter adapter;

    FragmentDoctorBinding binding;
    NavController navController;


    String name[] = {"Ali", "Hisham", "Mahdi", "Merna", "Mohamed", "Abe El-Mageed"};
    List<DoctorModel> doctorProfile = new ArrayList<DoctorModel>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDoctorBinding.inflate(inflater);
        DoctorViewModel = new ViewModelProvider(this).get(DoctorViewModel.class);


        recyclerView = binding.rec;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new doctorAdapter(name, getActivity(), this);
        recyclerView.setAdapter(adapter);

//        NavHostFragment navHostFragment =
//                (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        navController = navHostFragment.getNavController();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        doctorProfile.add(new DoctorModel("Ali",
                "01000481000",
                35,
                "الجيزه الدقي",
                "300LE",
                "Day .........",
                "الجيزة الدقي",
                "الطب"));

        doctorProfile.add(new DoctorModel("Hisham",
                "0112000000",
                35,
                "مدينه نصر",
                "350LE",
                "Day .........",
                "مدينه نصر الزهراء",
                "الطب"));

        doctorProfile.add(new DoctorModel("Mahdi",
                "0121400000",
                50,
                "الشرقية",
                "500LE",
                "Day .........",
                "الزقازيق",
                "الطب"));

        doctorProfile.add(new DoctorModel("Merna",
                "011472000000",
                40,
                "الغربية طنطا",
                "400LE",
                "Day .........",
                "طنطا",
                "الطب"));

        doctorProfile.add(new DoctorModel("Mohamed",
                "010000000000",
                48,
                "الجيزه الدقي",
                "300LE",
                "Day .........",
                "الجيزة الدقي",
                "الطب"));

        doctorProfile.add(new DoctorModel("Abe El-Mageed",
                "011023000000",
                37,
                "الاسكندرية",
                "450LE",
                "Day .........",
                "سيدي جابر",
                "الطب"));
    }

    @Override
    public void getAdapterPosition(int position) {
        Toast.makeText(requireActivity(), position + "", Toast.LENGTH_LONG).show();

        DoctorModel result = doctorProfile.get(position);
        Log.i("DoctorTest", result + "");

        DoctorFragmentDirections.ActionNavDoctorToDoctorProfile action = DoctorFragmentDirections.actionNavDoctorToDoctorProfile(result);
        navController.navigate(action);
    }
}