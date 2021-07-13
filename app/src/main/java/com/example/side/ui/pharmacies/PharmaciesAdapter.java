package com.example.side.ui.pharmacies;

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


public class PharmaciesAdapter extends RecyclerView.Adapter<PharmaciesAdapter.ViewHolder> {

    String data[];
    Context context;
    PharmaciesInterface pharmaciesInterface;

    public PharmaciesAdapter(String[] data, Context context, PharmaciesInterface pharmaciesInterface) {
        this.data = data;
        this.context = context;
        this.pharmaciesInterface = pharmaciesInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design2, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pharmacyName.setText(data[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pharmacyName, pharmacyAddress;
        Button callPharmacy;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            pharmacyName = itemView.findViewById(R.id.pharmacyName);
            pharmacyAddress = itemView.findViewById(R.id.pharmacyAddress);
            callPharmacy = itemView.findViewById(R.id.callPharmacy);
            imageView = itemView.findViewById(R.id.image);

            callPharmacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pharmaciesInterface.getPharmacyPosition(getAdapterPosition());
                }
            });
        }


    }
}

