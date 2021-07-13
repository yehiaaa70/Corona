package com.example.side.ui.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;


public class doctorAdapter extends RecyclerView.Adapter<doctorAdapter.ViewHolder> {


    String data[];
    Context context;
    DoctorInterface doctorInterface;

    public doctorAdapter(String[] data, Context context, DoctorInterface doctorInterface) {
        this.data = data;
        this.context = context;
        this.doctorInterface = doctorInterface;
    }

    @NonNull
    @Override
    public doctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull doctorAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(data[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.volunteerName);
            imageView = itemView.findViewById(R.id.image);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "clicked on" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    doctorInterface.getAdapterPosition(getAdapterPosition());
                }
            });

        }


    }
}

