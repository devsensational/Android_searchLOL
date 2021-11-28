//작성자: 김선호
//작성일자 : 21-11-13
//클래스 목적 : 미리 작성한 인터페이스를 호출하여 RiotAPI를 활용하여 사용자

package com.example.helloandroid;

import android.provider.ContactsContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.MatchInfo;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SummonerId;

import java.util.List;

public class RiotAPIClass{
    List<LeagueInfo> leagueInfo;
    Call<SummonerId> tmp;
    String name;

    public void findSummonerInfo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kr.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getSummerId(name, MainActivity.apiKey).enqueue(new Callback<SummonerId>() {
            @Override
            public void onResponse(Call<SummonerId> call, Response<SummonerId> response) {
                if (response.isSuccessful()) {
                    DataHandlerObject.summonerIds = response.body();

                    retrofitAPI.getLeagueInfo(DataHandlerObject.summonerIds.getId(),MainActivity.apiKey).enqueue(new Callback<List<LeagueInfo>>() {
                        @Override
                        public void onResponse(Call<List<LeagueInfo>> call, Response<List<LeagueInfo>> response) {
                            if (response.isSuccessful()) {
                                leagueInfo = response.body();
                                DataHandlerObject.leagueInfos = leagueInfo;
                                findSpectorInfo(DataHandlerObject.summonerIds.getId());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<LeagueInfo>> call, Throwable t) {

                            System.out.println(t.toString());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<SummonerId> call, Throwable t) {
            }
        });
    }


    protected void findMatchList(String puuid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asia.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getList(puuid,0,20,MainActivity.apiKey).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {

                System.out.println(response.code());
                if(response.isSuccessful()){
                    DataHandlerObject.matchLists = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
            }
        });

    }

    protected void findMatchInfo(String matchId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asia.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getMatchInfo(matchId,MainActivity.apiKey).enqueue(new Callback<MatchInfo>() {
            @Override
            public void onResponse(Call<MatchInfo> call, Response<MatchInfo> response) {
                if(response.isSuccessful()){
                    DataHandlerObject.matchInfos = response.body();
                }
            }
            @Override
            public void onFailure(Call<MatchInfo> call, Throwable t) {

            }
        });
    }

    protected void findSpectorInfo(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kr.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getSpector(id,MainActivity.apiKey).enqueue(new Callback<Spector>() {
            @Override
            public void onResponse(Call<Spector> call, Response<Spector> response) {
                System.out.println(response.code());
                if(response.isSuccessful()){
                    DataHandlerObject.spector = response.body();
                }
            }
            @Override
            public void onFailure(Call<Spector> call, Throwable t) {
                System.out.println(t);

            }
        });
    }
    public void setSummonerName(String name){
        this.name = name;
    }

}

