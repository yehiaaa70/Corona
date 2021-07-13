package com.example.side.ui.volunteer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.databinding.FragmentVolunteerBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class VolunteerFragment extends Fragment implements VolunteerInterface {

    private VolunteerViewModel volunteerViewModel;
    private RecyclerView recyclerView;
    private volunteerAdapter adapter;
    private String volunteersName[] = {"oxygen tank", "mask ", "oxygen tank", "mask"};
    private List<VolunteerModel> volunteerList = new ArrayList<VolunteerModel>();

    private FragmentVolunteerBinding binding;
    private NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        volunteerViewModel =
                new ViewModelProvider(this).get(VolunteerViewModel.class);
        binding = FragmentVolunteerBinding.inflate(inflater);

        recyclerView = binding.recVolunteer;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new volunteerAdapter(volunteersName, requireActivity(), this);

        recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        volunteerList.add(new VolunteerModel("Noha",
                "011472087799",
                60,
                "المقطم ",
                "450LE  انبوبة اكسجين "));

        volunteerList.add(new VolunteerModel("Ahmed ",
                "0114720876669",
                30,
                "الفيوم ",
                " جهاز تنفس  350LE"));

        volunteerList.add(new VolunteerModel("Marina",
                "0114720876669",
                30,
                "الغربيه  ",
                "350LE انبوبة اكسجين "));

        volunteerList.add(new VolunteerModel("Tarek",
                "011472088899",
                45,
                "مدينة نصر",
                "450LE  جهاز تنفس"));
    }

    @Override
    public void getVolunteerPosition(int position) {

        VolunteerModel result = volunteerList.get(position);

        VolunteerFragmentDirections.ActionNavVolunteerToMedicalEquipment action = VolunteerFragmentDirections.actionNavVolunteerToMedicalEquipment(result);
        navController.navigate(action);
    }
}



