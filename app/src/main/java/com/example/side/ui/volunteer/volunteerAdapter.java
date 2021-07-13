package com.example.side.ui.volunteer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;

public class volunteerAdapter extends RecyclerView.Adapter<volunteerAdapter.ViewHolder> {

    String data[];
    Context context;
    VolunteerInterface volunteerInterface;

    public volunteerAdapter(String[] data, Context context, VolunteerInterface volunteerInterface) {
        this.data = data;
        this.context = context;
        this.volunteerInterface = volunteerInterface;
    }

    @NonNull
    @Override
    public volunteerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custome_design, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull volunteerAdapter.ViewHolder holder, int position) {
        holder.volunteerName.setText(data[position]);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView volunteerName;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            volunteerName = itemView.findViewById(R.id.volunteerName);

            volunteerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    volunteerInterface.getVolunteerPosition(getAdapterPosition());
                }
            });


        }


    }
}

