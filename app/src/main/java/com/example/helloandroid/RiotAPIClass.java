//작성자: 김선호
//작성일자 : 21-11-13
//클래스 목적 : 미리 작성한 인터페이스를 호출하여 RiotAPI를 활용하여 사용자

package com.example.helloandroid;


import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.MatchInfo;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SummonerId;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RiotAPIClass extends Thread{
    List<LeagueInfo> leagueInfo;
    Call<SummonerId> tmp;
    String name;
    private DatabaseReference mDatabaseRef;

    public void run() {
        findSummonerInfo();

    }

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
                                mDatabaseRef = FirebaseDatabase.getInstance().getReference();
                                leagueInfo = response.body();
                                DataHandlerObject.leagueInfos = leagueInfo;
                                findSpectorInfo(DataHandlerObject.summonerIds.getId());
                                findMatchList(DataHandlerObject.summonerIds.getPuuid());
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
                    String[] matchList = new String[20];
                    //String[] gId = new String[20];
                    mDatabaseRef.child("matchList").child(DataHandlerObject.summonerIds.getName()).setValue(DataHandlerObject.matchLists);
                    mDatabaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(int i=0;i<20;i++){
                                matchList[i] = snapshot.child("matchList").child(DataHandlerObject.summonerIds.getName()).child(Integer.toString(i)).getValue().toString();
                                findMatchInfo(matchList[i]);
                                //gId[i] = ss[i].substring(3);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
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
                    //최근전적 소환사들 정보
                    mDatabaseRef.child("matchList").child(DataHandlerObject.summonerIds.getName()).child(matchId).setValue(DataHandlerObject.matchInfos.getInfo().getParticipant());
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
                    mDatabaseRef.child("SpectorInfo").child(DataHandlerObject.summonerIds.getName()).setValue(DataHandlerObject.spector.getParticipants());
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

