package com.example.side.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.side.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder>{

    List<CommentModel> modelList ;
    Context context;

    public CommentAdapter(List<CommentModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
        CommentModel model =modelList.get(position);

        holder.name.setText(model.getUsername());
        holder.comment.setText(model.getComment());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,comment;
        public Holder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.UserName_comment);
            comment=itemView.findViewById(R.id.CommentDoc_tv);
        }
    }
}
