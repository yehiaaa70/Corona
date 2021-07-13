package com.example.side.ui.qr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;
import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckQR extends Fragment {

    TextInputEditText name, address, clean, numPatient;
    WebServices webServices;
    Button cleanBtn;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_check_q_r, container, false);

        name = v.findViewById(R.id.nameOfPlace_ed);
        address = v.findViewById(R.id.addressOfPlace_ed);
        clean = v.findViewById(R.id.isClean_ed);
        numPatient = v.findViewById(R.id.NumPatient_ed);
        cleanBtn = v.findViewById(R.id.cleanBtn);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = getArguments().getString("message");
        getDataQRCode(data);


        cleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog(id);
            }
        });

    }

    private void getDataQRCode(String data) {
        webServices = RetrofitFactory.getRetrofit().create(WebServices.class);
        Call<List<QRCodeModel>> call = webServices.QR_Code(data);
        call.enqueue(new Callback<List<QRCodeModel>>() {
            @Override
            public void onResponse(Call<List<QRCodeModel>> call, Response<List<QRCodeModel>> response) {
                id = response.body().get(0).getId();

                name.setText(response.body().get(0).getName());
                address.setText(response.body().get(0).getAddress());
                numPatient.setText(String.valueOf(response.body().get(0).getNumPositive()));

                if (response.body().get(0).isClear()) {
                    clean.setText(" clean");

                } else {
                    clean.setText(" not clean");

                }

                if (response.body().get(0).getUser_id().equals(getMac())) {
                    cleanBtn.setVisibility(View.VISIBLE);
                } else {
                    cleanBtn.setVisibility(View.INVISIBLE);
                }

                QRCodeModel qrCodeModel = new QRCodeModel(
                        response.body().get(0).getName(),
                        response.body().get(0).getAddress(),
                        response.body().get(0).getNumPositive() + 1,
                        false,
                        response.body().get(0).getUser_id()
                );
                Call<QRCodeModel> qrCall = webServices.updateNumPatient(response.body().get(0).getId(), qrCodeModel);
                qrCall.enqueue(new Callback<QRCodeModel>() {
                    @Override
                    public void onResponse(Call<QRCodeModel> call, Response<QRCodeModel> response) {
                        Toast.makeText(requireContext(), "Done Update", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<QRCodeModel> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<QRCodeModel>> call, Throwable t) {
                System.out.println("this is the massageeeee ....." + t.getMessage());
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void ShowDialog(String id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Did You Clean Your Place ?");

        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                QRCodeModel model = new QRCodeModel(0, true);
                Call<QRCodeModel> qrCall = webServices.updateNumPatient(id, model);
                qrCall.enqueue(new Callback<QRCodeModel>() {
                    @Override
                    public void onResponse(Call<QRCodeModel> call, Response<QRCodeModel> response) {
                        Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<QRCodeModel> call, Throwable t) {

                    }
                });

            }
        });

        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        dialog.show();
    }
    @org.jetbrains.annotations.Nullable
    private  String getMac(){
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

}