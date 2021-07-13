package com.example.side.ui.logout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.side.R;
import com.example.side.databinding.ActivityRegisterPatientBinding;
import com.example.side.ui.MainActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class register_patient extends AppCompatActivity {

    private final int LOCATION_REQUEST_CODE = 10;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final String TAG = "UsersLocations";

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    ActivityRegisterPatientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_patient);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        binding.patientRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAnonymouslyInFirebase();
            }
        });
    }

    private void askForPermission() {
        if (ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            getLastLocation();
        } else {
            askForPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        binding.progressBar.setVisibility(View.VISIBLE);
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    addUserLocationToFirebase(location);

                    Log.i(TAG, "onSuccess: " + location.toString());

                    Log.i(TAG, "onSuccess lat: " + location.getLatitude());
                    Log.i(TAG, "onSuccess lng: " + location.getLongitude());

                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    Log.i(TAG, "onSuccess lng: Location was null ");
                    binding.progressBar.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                String errorMessage = e.getMessage();
                Log.i(TAG, "onFailure: " + errorMessage);
                Toast.makeText(register_patient.this, errorMessage, Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void signInAnonymouslyInFirebase() {
        binding.progressBar.setVisibility(View.VISIBLE);
        auth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.i(TAG, "onSuccess: " + authResult.toString());
                Toast.makeText(register_patient.this, authResult.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(register_patient.this, "Login Successful", Toast.LENGTH_LONG).show();
                binding.progressBar.setVisibility(View.GONE);
                getLastLocation();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.i(TAG, "onFailure: " + e.getMessage());
                        Toast.makeText(register_patient.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void addUserLocationToFirebase(Location location) {
        binding.progressBar.setVisibility(View.VISIBLE);

        Map<String, Object> usersDate = new HashMap<>();
        usersDate.put("Latitude", location.getLatitude());
        usersDate.put("Longitude", location.getLongitude());
        usersDate.put("User_id", auth.getUid());
        if (auth.getUid() != null) {

            firebaseFirestore.collection("UsersLocation")
                    .document(auth.getUid())
                    .set(usersDate).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(register_patient.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    Log.i(TAG, "onFailure: " + e.getMessage());
                }
            });
        }
    }
}