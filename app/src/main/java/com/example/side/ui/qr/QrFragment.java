package com.example.side.ui.qr;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;
import com.google.zxing.Result;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrFragment extends Fragment {

    private CodeScanner mCodeScanner;
    Button generateBtn,checkBtn;
    WebServices webServices;
    CodeScannerView scannerView;
    String resultData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qr, container, false);
         scannerView = view.findViewById(R.id.scanner_view);
         generateBtn=view.findViewById(R.id.GeneratePage_btn);
         checkBtn=view.findViewById(R.id.CheckPage_btn);

    return view;

        }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_nav_QRCode_to_generateQR);

            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("message",resultData);
                Navigation.findNavController(view).navigate(R.id.action_nav_QRCode_to_checkQR,bundle);

            }
        });


        mCodeScanner = new CodeScanner(requireContext(), scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull @NotNull Result result) {
               resultData=result.getText();

            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
                Toast.makeText(requireContext(), "click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();

    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }



}



