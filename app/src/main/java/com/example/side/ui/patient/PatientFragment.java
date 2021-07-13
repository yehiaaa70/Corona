package com.example.side.ui.patient;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.side.R;
import com.example.side.databinding.FragmentPatientBinding;
import com.example.side.ui.doctor.DoctorModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PatientFragment extends Fragment implements PatientInterface {

    private PatientViewModel patientViewModel;
    RecyclerView recyclerView;
    patientAdapter adapter;
    String nursesName[] = {"Nada", "Tarek", "Noha", "Marina"};
    List<NurseModel> nurseList = new ArrayList<NurseModel>();
    FragmentPatientBinding binding;
    NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        binding = FragmentPatientBinding.inflate(inflater);
        recyclerView = binding.RecPatient;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new patientAdapter(nursesName, getActivity(), this);
        recyclerView.setAdapter(adapter);


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        nurseList.add(new NurseModel("Nada", "011472009899",
                45,
                "القاهره الجيزه",
                "450LE",
                "15/7/2021",
                "الجيزه  "));

        nurseList.add(new NurseModel("Tarek",
                "011472088899",
                45,
                "مدينة نصر",
                "450LE",
                "16/7/2021",
                "مدينة نصر "));

        nurseList.add(new NurseModel("Noha",
                "011472087799",
                60,
                "المقطم ",
                "450LE",
                "18/7/2021 ",
                "المقطم"));

        nurseList.add(new NurseModel("Marina",
                "0114720876669",
                30,
                "الفيوم ",
                "350LE",
                "19/7/2021 ",
                "الفيوم "));
    }

    @Override
    public void getPatientPosition(int position) {
        NurseModel result = nurseList.get(position);

        PatientFragmentDirections.ActionNavPatientToNurseProfile action = PatientFragmentDirections.actionNavPatientToNurseProfile(result);
        navController.navigate(action);
    }
}