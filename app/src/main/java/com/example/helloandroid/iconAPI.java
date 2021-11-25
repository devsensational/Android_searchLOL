package com.example.helloandroid;

import com.example.helloandroid.Parser.Icon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class iconAPI extends Thread{
    protected void findIconData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ddragon.leagueoflegends.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getIcon().enqueue(new Callback<List<Icon>>() {
            @Override
            public void onResponse(Call<List<Icon>> call, Response<List<Icon>> response) {

                System.out.println(response.code());
                if(response.isSuccessful()){
                    DataHandlerObject.icons = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Icon>> call, Throwable t) {
            }
        });

    }
}
