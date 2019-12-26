package com.darko.userappshkoloretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.darko.userappshkoloretrofit.adapters.UserAdapter;
import com.darko.userappshkoloretrofit.datamodel.Address;
import com.darko.userappshkoloretrofit.datamodel.User;
import com.darko.userappshkoloretrofit.dataservice.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    RecyclerView recyclerView;
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<List<User>> call = service.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> userResponseList = response.body();

                for (int i = 0; i < userResponseList.size(); i++) {
                    User tempUser = userResponseList.get(i);

                    int id = tempUser.getId();
                    String name = tempUser.getName();
                    String userName = tempUser.getUsername();

                    String street = tempUser.getAddress().getStreet();
                    String city = tempUser.getAddress().getCity();
                    String zipCode = tempUser.getAddress().getZipcode();
                    Address address = new Address(street, city, zipCode);

                    User currentUser = new User();
                    currentUser.setId(id);
                    currentUser.setName(name);
                    currentUser.setUsername(userName);
                    currentUser.setAddress(address);

                    userList.add(currentUser);
                }
                UserAdapter userAdapter = new UserAdapter(userList);
                recyclerView.setAdapter(userAdapter);
                userAdapter.setListener(new UserAdapter.Listener() {
                    @Override
                    public void onUserClick(int position) {
                        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                        intent.putExtra("user",userList.get(position));
//                        intent.putParcelableArrayListExtra("user", userList);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }
}