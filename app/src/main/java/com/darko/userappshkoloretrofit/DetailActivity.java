package com.darko.userappshkoloretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.darko.userappshkoloretrofit.adapters.PostAdapter;
import com.darko.userappshkoloretrofit.datamodel.User;
import com.darko.userappshkoloretrofit.datamodel.UserPosts;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerViewDetails;
    private List<UserPosts> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerViewDetails = findViewById(R.id.recyclerViewDetails);
        recyclerViewDetails.setLayoutManager(new LinearLayoutManager(this));
        postsList = new ArrayList<>();

        Intent intent = getIntent();
        User currentUser = intent.getParcelableExtra("user");

        if (currentUser != null) {
            final long parcedUserId = currentUser.getId();


            String url = "https://jsonplaceholder.typicode.com/posts";

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();

            Call call = okHttpClient.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    ResponseBody responseBody = response.body();

                    if (responseBody != null) {
                        String responseResult = responseBody.string();

                        try {
                            JSONArray postsArray = new JSONArray(responseResult);

                            for (int i = 0; i < postsArray.length(); i++) {
                                JSONObject postsObject = (JSONObject) postsArray.get(i);

                                long userId = postsObject.getLong("userId");

                                long postId = postsObject.getLong("id");
                                String title = postsObject.getString("title");
                                String body = postsObject.getString("body");


                                if (parcedUserId == userId) {
                                    postsList.add(new UserPosts(userId, postId, title, body));
                                }
                            }

                            recyclerViewDetails.post(new Runnable() {
                                @Override
                                public void run() {
                                    PostAdapter postAdapter = new PostAdapter(postsList);
                                    recyclerViewDetails.setAdapter(postAdapter);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

    }
}
