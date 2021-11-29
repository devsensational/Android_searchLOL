package com.example.helloandroid.inGame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloandroid.DataHandlerObject;
import com.example.helloandroid.MainActivity;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.Participant;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SpectorParticipant;
import com.example.helloandroid.Parser.SummonerId;
import com.example.helloandroid.Parser.runeInfo;
import com.example.helloandroid.R;
import com.example.helloandroid.RetrofitAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.example.helloandroid.Parser.championInfo;
import com.example.helloandroid.Parser.spellInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.example.helloandroid.DataHandlerObject;
import com.example.helloandroid.MainActivity;

import com.example.helloandroid.RiotAPIClass;
import com.example.helloandroid.RetrofitAPI;

/**
 * 인게임 화면을 구성하는 Fragment
 *
 * @author 고동현
 * @since 2021-11-20
 */
public class InGameFragment extends Fragment {
    String version = "11.23.1";


    public InGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View inflateView = inflater.inflate(R.layout.fragment_in_game, container, false); // 레이아웃 View
        RecyclerView inGameRecyclerView = inflateView.findViewById(R.id.in_game_recycler_view);
        inGameRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        InGameRecyclerAdapter inGameRecyclerAdapter = new InGameRecyclerAdapter();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kr.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getSummerId(DataHandlerObject.summonerName, MainActivity.apiKey).enqueue(new Callback<SummonerId>() {
            @Override
            public void onResponse(Call<SummonerId> call, Response<SummonerId> response) {
                if (response.isSuccessful()) {
                    DataHandlerObject.summonerIds = response.body();
                    retrofitAPI.getLeagueInfo(DataHandlerObject.summonerIds.getId(),MainActivity.apiKey).enqueue(new Callback<List<LeagueInfo>>() {
                        @Override
                        public void onResponse(Call<List<LeagueInfo>> call, Response<List<LeagueInfo>> response) {
                            if (response.isSuccessful()) {
                                DataHandlerObject.leagueInfos = response.body();

                                retrofitAPI.getSpector(DataHandlerObject.summonerIds.getId(),MainActivity.apiKey).enqueue(new Callback<Spector>() {
                                    @Override
                                    public void onResponse(Call<Spector> call, Response<Spector> response) {
                                        System.out.println(response.code());
                                        if(response.isSuccessful()){
                                            DataHandlerObject.spector = response.body();
                                            if(response.code() == 200){
                                                System.out.println("불러오기 완료");


                                                List<SpectorParticipant> sp = DataHandlerObject.spector.getParticipants();
                                                List<InGameDataObject> inGameDataObjects = new ArrayList<>();
                                                championInfo ci = new championInfo();
                                                spellInfo si = new spellInfo();
                                                runeInfo ri = new runeInfo();
                                                for (int i = 0; i < 10; i++) {
                                                    int finalI = i;
                                                    inGameDataObjects.add(new InGameDataObject() {{
                                                        setBlueOrRed(finalI < 5 ? true : false);
                                                        setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/champion/"+ ci.cif(sp.get(finalI).getChampionId()) +".png");
                                                        setNickname(sp.get(finalI).getSummonerName());
                                                        setSpell1ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ si.sif(sp.get(finalI).getSpell1Id())+ ".png");
                                                        setSpell2ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ si.sif(sp.get(finalI).getSpell2Id())+".png");
                                                        System.out.println("룬테스트 : " + sp.get(finalI).getPerks().getPerkIds().get(0) + " : " + sp.get(finalI).getPerks().getPerkSubStyle());
                                                        setRune1ImageUrl("https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" + ri.rif(sp.get(finalI).getPerks().getPerkIds().get(0)) +".png");
                                                        setRune2ImageUrl("https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/" + ri.rif(sp.get(finalI).getPerks().getPerkSubStyle())+ ".png");
                                                        Retrofit retrofit = new Retrofit.Builder()
                                                                .baseUrl("https://kr.api.riotgames.com")
                                                                .addConverterFactory(GsonConverterFactory.create())
                                                                .build();
                                                        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                                                        retrofitAPI.getSummerId(sp.get(finalI).getSummonerName(), MainActivity.apiKey).enqueue(new Callback<SummonerId>() {
                                                            @Override
                                                            public void onResponse(Call<SummonerId> call, Response<SummonerId> response) {
                                                                if (response.isSuccessful()) {
                                                                    SummonerId sid = response.body();
                                                                    retrofitAPI.getLeagueInfo(sid.getId(),MainActivity.apiKey).enqueue(new Callback<List<LeagueInfo>>() {
                                                                        @Override
                                                                        public void onResponse(Call<List<LeagueInfo>> call, Response<List<LeagueInfo>> response) {
                                                                            if (response.isSuccessful()) {
                                                                                if(response.code() == 200){
                                                                                    List<LeagueInfo> lf = response.body();
                                                                                    int indexNum = 0;
                                                                                    for(int j = 0 ; j < lf.size(); j++) {
                                                                                        if (lf.get(j).getQueueType().equals("RANKED_SOLO_5x5")) {
                                                                                            indexNum = j;
                                                                                        }
                                                                                        setTearText(lf.get(indexNum).getTier() + " " + lf.get(indexNum).getRank());
                                                                                        int win = lf.get(indexNum).getWins();
                                                                                        int lose = lf.get(indexNum).getLosses();
                                                                                        float avg = (float)(win)/((float)win+(float)lose) * 100;
                                                                                        setWinRate("승률 : " + String.format("%.2f",avg) + "%");
                                                                                        setTearImageUrl("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg");
                                                                                    }
                                                                                }else{
                                                                                    System.out.println("인게임 불러오기 실패");
                                                                                }
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onFailure(Call<List<LeagueInfo>> call, Throwable t) {

                                                                            System.out.println("실패  : " + t.toString());
                                                                        }
                                                                    });
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<SummonerId> call, Throwable t) {
                                                            }
                                                        });

                                                        inGameRecyclerView.setAdapter(inGameRecyclerAdapter);
                                                        inGameRecyclerAdapter.setInGameDataObjectList(inGameDataObjects);
                                                        inGameRecyclerView.addItemDecoration(dividerItemDecoration);
                                                    }});
                                                }


                                            }else{
                                                //게임 중 아님
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Spector> call, Throwable t) {
                                        System.out.println(t);

                                    }
                                });
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
        return inflateView;
    }

}

