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
import com.example.helloandroid.R;
import com.example.helloandroid.RetrofitAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

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
                                                for (int i = 0; i < 5; i++) {
                                                    int finalI = i;
                                                    inGameDataObjects.add(new InGameDataObject() {{
                                                        setBlueOrRed(true);
                                                        //setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/champion/"+ sp.get(finalI).getChampionId() +".png");
                                                        //setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/11.23.1/img/champion/Poppy.png");
                                                        setNickname(sp.get(finalI).getSummonerName());
                                                        setSpell1ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ sp.get(finalI).getPerks().getPerkIds().get(0)+".png");
                                                        setSpell2ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ sp.get(finalI).getPerks().getPerkIds().get(1)+".png");

                                                        setTearText("D3");
                                                        setWinRate("55%");
                                                        setTearImageUrl("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg");

                                                    }});
                                                }

                                                for (int i = 5; i < 10; i++) {
                                                    int finalI = i;
                                                    inGameDataObjects.add(new InGameDataObject() {{
                                                        setBlueOrRed(false);
                                                        setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/champion/"+ sp.get(finalI).getChampionId() +".png");
                                                        setNickname(sp.get(finalI).getSummonerName());
                                                        setSpell1ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ sp.get(finalI).getPerks().getPerkIds().get(0)+".png");
                                                        setSpell2ImageUrl("https://ddragon.leagueoflegends.com/cdn/"+ version +"/img/spell/"+ sp.get(finalI).getPerks().getPerkIds().get(1)+".png");

                                                        setTearText("D3");
                                                        setWinRate("55%");
                                                        setTearImageUrl("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg");

                                                    }});
                                                }

                                                inGameRecyclerView.setAdapter(inGameRecyclerAdapter);
                                                inGameRecyclerAdapter.setInGameDataObjectList(inGameDataObjects);
                                                inGameRecyclerView.addItemDecoration(dividerItemDecoration);
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

