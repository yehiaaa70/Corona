package com.example.side.ui.home;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Critical_Issue extends AppCompatActivity {
    RecyclerView rvCritical;
    FloatingActionButton addPost;
    Dialog dialog;
    CriticalAdapter criticalAdapter;
    List<criticalModel> criticalModelList = new ArrayList<>();
    WebServices webServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critical_issues);
        component();

        webServices = RetrofitFactory.getRetrofit().create(WebServices.class);
        Call<List<criticalModel>> call = webServices.getPosts();

        call.enqueue(new Callback<List<criticalModel>>() {
            @Override
            public void onResponse(Call<List<criticalModel>> call, Response<List<criticalModel>> response) {
                Toast.makeText(Critical_Issue.this, "Done", Toast.LENGTH_SHORT).show();

                criticalModelList.addAll(response.body());

                criticalAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<criticalModel>> call, Throwable t) {
                Toast.makeText(Critical_Issue.this, "Error To Get Data", Toast.LENGTH_SHORT).show();
            }
        });

        setUpRecyclerView();
        Dialog();

        rvCritical.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addPost.getVisibility() == View.VISIBLE) {
                    addPost.hide();
                } else if (dy < 0 && addPost.getVisibility() != View.VISIBLE) {
                    addPost.show();
                }
            }
        });

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    private void component() {
        rvCritical = findViewById(R.id.Critical_recycler);
        addPost = findViewById(R.id.addingPost_floating);
    }

    private void setUpRecyclerView() {

        rvCritical.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        criticalAdapter = new CriticalAdapter(criticalModelList, getApplicationContext(), new CriticalAdapter.OnCommentClick() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getBaseContext(), Comments.class);
                intent.putExtra("Post ID",criticalModelList.get(position).getId());
                startActivity(intent);            }
        });
        rvCritical.setAdapter(criticalAdapter);
    }


    private void Dialog() {
        View v = getLayoutInflater().inflate(R.layout.posts, null);

        dialog = new Dialog(Critical_Issue.this, android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        dialog.setContentView(v);

        Button button = v.findViewById(R.id.Ok_btn);
        TextInputEditText tv = v.findViewById(R.id.issue_ed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criticalModel m =new criticalModel("60ea6b1235714a0015e0df39",tv.getText().toString(),0);
                Call<criticalModel> call = webServices.createPost(m);
                call.enqueue(new Callback<criticalModel>() {
                    @Override
                    public void onResponse(Call<criticalModel> call, Response<criticalModel> response) {
                        Toast.makeText(Critical_Issue.this, "Done"+response.body().getDescription(), Toast.LENGTH_SHORT).show();
                        criticalAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<criticalModel> call, Throwable t) {
                        Toast.makeText(Critical_Issue.this, "Error ", Toast.LENGTH_SHORT).show();
                        System.out.println("Error....... "+t);
                    }
                });


                dialog.dismiss();
            }
        });
    }

}