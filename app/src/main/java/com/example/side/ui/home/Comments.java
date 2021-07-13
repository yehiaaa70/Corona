package com.example.side.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.side.R;
import com.example.side.ui.Retrofit.RetrofitFactory;
import com.example.side.ui.Retrofit.WebServices;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comments extends AppCompatActivity {
    RecyclerView rvComments;
    CommentAdapter commentAdapter;
    List<CommentModel> commentModelList = new ArrayList<>();
    ImageButton send;
    TextInputEditText comment_ed;
    WebServices webServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        rvComments = findViewById(R.id.Comment_recycler);
        send = findViewById(R.id.send_btn);
        comment_ed = findViewById(R.id.comment_ed);


        webServices = RetrofitFactory.getRetrofit().create(WebServices.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentModel model = new CommentModel(
                        "000",
                        "60ea41a7505b3c0015342d68",
                        comment_ed.getText().toString());

                Toast.makeText(Comments.this, comment_ed.getText().toString(), Toast.LENGTH_SHORT).show();

                Call<CommentModel> call = webServices.createComment(model);
                call.enqueue(new Callback<CommentModel>() {
                    @Override
                    public void onResponse(Call<CommentModel> call, Response<CommentModel> response) {

                        Toast.makeText(Comments.this, response.body().getPostId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CommentModel> call, Throwable t) {
                        Toast.makeText(Comments.this, "Failed to Comment", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        rvComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        commentAdapter = new CommentAdapter(commentModelList, getApplicationContext());
        rvComments.setAdapter(commentAdapter);


    }
}