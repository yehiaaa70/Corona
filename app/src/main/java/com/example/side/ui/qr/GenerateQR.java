package com.example.side.ui.qr;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.WriterException;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenerateQR extends Fragment {

    TextInputEditText plaName,plaAddress;
    Button generateBtn;
    ImageView qrImage;
    WebServices webServices;
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_generate_q_r, container, false);
        plaName =v.findViewById(R.id.QRName_ed);
        plaAddress =v.findViewById(R.id.QRAddress_ed);
        generateBtn =v.findViewById(R.id.generate_btn);
        qrImage =v.findViewById(R.id.QRCodeImage);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeModel qrCodeModel=new QRCodeModel(
                        plaName.getText().toString(),
                        plaAddress.getText().toString(),
                        0,
                        true,
                        getMac()
                );

                webServices = RetrofitFactory.getRetrofit().create(WebServices.class);
                Call<QRCodeModel> call=webServices.CreateQR(qrCodeModel);
                call.enqueue(new Callback<QRCodeModel>() {
                    @Override
                    public void onResponse(Call<QRCodeModel> call, Response<QRCodeModel> response) {
                        CreateImgQR();
                    }

                    @Override
                    public void onFailure(Call<QRCodeModel> call, Throwable t) {

                    }
                });

                Toast.makeText(requireContext(),getMac(), Toast.LENGTH_SHORT).show();


            }
        });

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

    private void CreateImgQR(){

        QRGEncoder qrgEncoder =new QRGEncoder(getMac(),null, QRGContents.Type.TEXT,500);

        Bitmap qrBits = qrgEncoder.getBitmap();
        qrImage.setImageBitmap(qrBits);

        ContextWrapper wrapper = new ContextWrapper(getActivity());
        File file = wrapper.getDir("Images",Context.MODE_PRIVATE);
        file = new File(file, "UniqueFileName"+".jpg");

        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            qrBits.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();

        }catch (IOException e) // Catch the exception
        {
            e.printStackTrace();
        }
        Uri savedImageURI = Uri.parse(file.getAbsolutePath());



        // Display saved image uri to TextView
        Toast.makeText(requireContext(), "url : "+savedImageURI , Toast.LENGTH_SHORT).show();






    }

}