package com.example.side.ui.hospital;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.ui.pharmacies.PharmaciesAdapter;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder>{

    String data[];
    Context context;
    HospitalInterface hospitalInterface;

    public HospitalAdapter(String[] data, Context context, HospitalInterface hospitalInterface) {
        this.data = data;
        this.context = context;
        this.hospitalInterface = hospitalInterface;
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.custome_design2,parent,false);
        com.example.side.ui.hospital.HospitalAdapter.ViewHolder viewHolder=new com.example.side.ui.hospital.HospitalAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull com.example.side.ui.hospital.HospitalAdapter.ViewHolder holder, final int position) {
        holder.hospitalName.setText(data[position]);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView hospitalName , hospitalAddress;
        Button callHospital;
        public ViewHolder (@NonNull View itemView){

            super(itemView);
            hospitalName=itemView.findViewById(R.id.pharmacyName);
            hospitalAddress = itemView.findViewById(R.id.pharmacyAddress);
            callHospital = itemView.findViewById(R.id.callPharmacy);

            callHospital.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hospitalInterface.getHospitalPosition(getAdapterPosition());
                }
            });

        }


    }
}
