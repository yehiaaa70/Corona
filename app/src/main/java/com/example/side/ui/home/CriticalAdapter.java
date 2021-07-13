package com.example.side.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriticalAdapter extends RecyclerView.Adapter<CriticalAdapter.Holder> {

    List<criticalModel> criticalModelList;
    Context context;
    WebServices webServices;

    OnCommentClick onCommentClick;

    public interface OnCommentClick {
        void onItemClick(View view, int position);
    }


    public CriticalAdapter(List<criticalModel> criticalModelList, Context context, OnCommentClick onCommentClick) {
        this.criticalModelList = criticalModelList;
        this.context = context;
        this.onCommentClick = onCommentClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        criticalModel model = criticalModelList.get(position);
        webServices = RetrofitFactory.getRetrofit().create(WebServices.class);

        if (model.getWarning() >= 5) {
            Toast.makeText(context, model.getUsername() + "..." + model.getWarning(), Toast.LENGTH_SHORT).show();
        } else {

            Call<UserModel> call = webServices.getUser(model.getUsername());
            call.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    holder.Name.setText(response.body().getName());
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), "Error of user name", Toast.LENGTH_SHORT).show();
                }
            });

            holder.Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, holder.Name.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.Details.setText(model.getDescription());
            holder.Comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Comments", Toast.LENGTH_SHORT).show();
                    onCommentClick.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return criticalModelList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Details;
        TextView Comments;

        public Holder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.UserName);
            Details = itemView.findViewById(R.id.post_tv);
            Comments = itemView.findViewById(R.id.comment_tv);


        }
    }
}
