package com.darko.userappshkoloretrofit.dataservice;
import com.darko.userappshkoloretrofit.datamodel.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<List<User>> getUsers();
    @GET("posts")
    Call<User> getPosts();
}