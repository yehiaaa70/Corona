package com.example.side.ui.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;


public class patientAdapter extends RecyclerView.Adapter<com.example.side.ui.patient.patientAdapter.ViewHolder> {

    String data[];
    Context context;
    PatientInterface patientInterface;

    public patientAdapter(String[] data, Context context, PatientInterface patientInterface) {
        this.data = data;
        this.context = context;
        this.patientInterface = patientInterface;
    }

    @NonNull
    @Override
    public com.example.side.ui.patient.patientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design, parent, false);
        com.example.side.ui.patient.patientAdapter.ViewHolder viewHolder = new com.example.side.ui.patient.patientAdapter.ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull com.example.side.ui.patient.patientAdapter.ViewHolder holder, int position) {
        holder.patientName.setText(data[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView patientName;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            patientName = itemView.findViewById(R.id.volunteerName);
            imageView = itemView.findViewById(R.id.image);

            patientName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    patientInterface.getPatientPosition(getAdapterPosition());
                }
            });

        }


    }
}




