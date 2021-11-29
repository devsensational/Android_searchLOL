package com.example.helloandroid.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloandroid.DataHandlerObject;
import com.example.helloandroid.InGameAPIThread;
import com.example.helloandroid.MainActivity;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.MatchInfo;
import com.example.helloandroid.Parser.Participant;
import com.example.helloandroid.Parser.SummonerId;
import com.example.helloandroid.R;
import com.example.helloandroid.RetrofitAPI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.helloandroid.Parser.spellInfo;
import com.example.helloandroid.Parser.championInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 전적 화면 구성 Fragment
 *
 * @author 고동현
 * @since 2021-11-20
 */
public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String gameVersion = "11.23.1";
        // Inflate the layout for this fragment

        View inflateView = inflater.inflate(R.layout.fragment_search, container, false);
        ImageView userIconView = inflateView.findViewById(R.id.usericon_imageView);
        ImageView rankIconView = inflateView.findViewById(R.id.rankicon_imageView);
        TextView sName = (TextView) inflateView.findViewById(R.id.nickname_textview);
        TextView winRate = (TextView) inflateView.findViewById(R.id.winRate_textview);
        TextView rPoint = (TextView) inflateView.findViewById(R.id.rankPoint_textview);
        TextView rType = (TextView) inflateView.findViewById(R.id.ranktype_textview);
        TextView sLevel = (TextView) inflateView.findViewById(R.id.sLevel_textview);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kr.api.riotgames.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getSummerId(DataHandlerObject.summonerName, MainActivity.apiKey).enqueue(new Callback<SummonerId>() {
            @Override
            public void onResponse(Call<SummonerId> call, Response<SummonerId> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) { //소환사 아이디 서치 성공
                        System.out.println("TEST : 소환사 아이디 서치 성공");
                        DataHandlerObject.summonerIds = response.body();
                        retrofitAPI.getLeagueInfo(DataHandlerObject.summonerIds.getId(), MainActivity.apiKey).enqueue(new Callback<List<LeagueInfo>>() {
                            @Override
                            public void onResponse(Call<List<LeagueInfo>> call, Response<List<LeagueInfo>> response) {
                                if (response.isSuccessful()) {
                                    if (response.code() == 200) { //리그 인포 서치 성공
                                        int indexNum = 0;
                                        System.out.println("TEST : 리그인포 서치 성공");
                                        DataHandlerObject.leagueInfos = response.body();
                                        System.out.println("TEST!! : 리그인포 사이즈 = " + DataHandlerObject.leagueInfos.size());
                                        sName.setText(DataHandlerObject.summonerIds.getName());
                                        for(int i = 0 ; i < DataHandlerObject.leagueInfos.size(); i++){
                                            if(DataHandlerObject.leagueInfos.get(i).getQueueType().equals("RANKED_SOLO_5x5"))
                                                indexNum = i;
                                        }
                                        sLevel.setText("레벨 : " + DataHandlerObject.summonerIds.getSummonerLevel().toString());
                                        rType.setText(DataHandlerObject.leagueInfos.get(indexNum).getTier()
                                        + " "+ DataHandlerObject.leagueInfos.get(indexNum).getRank());
                                        rPoint.setText(DataHandlerObject.leagueInfos.get(indexNum).getLeaguePoints().toString());
                                        int win = DataHandlerObject.leagueInfos.get(indexNum).getWins();
                                        int lose = DataHandlerObject.leagueInfos.get(indexNum).getLosses();
                                        float avg = ((float)(win)/((float)win+(float)lose)) * 100;
                                        winRate.setText(Integer.toString(win) + "승 " + Integer.toString(lose) + "패 ("+ String.format("%.2f", avg)+"%)");
                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl("https://asia.api.riotgames.com")
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();
                                        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                                        retrofitAPI.getList(DataHandlerObject.summonerIds.getPuuid(), 0, 20, MainActivity.apiKey).enqueue(new Callback<List<String>>() {
                                            @Override
                                            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                                                if (response.isSuccessful()) {
                                                    DataHandlerObject.matchLists = response.body();
                                                    if (response.code() == 200) {

                                                        System.out.println("TEST : 매치리스트 서치 성공");
                                                        Glide.with(getContext()).load("https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/profileicon/" + DataHandlerObject.summonerIds.getProfileIconId() + ".png")
                                                                .into(userIconView); //유저 아이콘
                                                        Glide.with(getContext()).load("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg")
                                                                .into(rankIconView); //랭크 아이콘 이게 jpg로 바뀌어야 함
                                                        RecyclerView searchRecyclerView = inflateView.findViewById(R.id.searchRecyclerView);
                                                        SearchRecyclerAdapter recyclerAdapter = new SearchRecyclerAdapter();
                                                        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                                        List<SearchItemObject> searchItemObjectList = new ArrayList<>();
                                                        for (int i = 0; i < 20; i++) {
                                                            retrofitAPI.getMatchInfo(DataHandlerObject.matchLists.get(i), MainActivity.apiKey).enqueue(new Callback<MatchInfo>() {
                                                                @Override
                                                                public void onResponse(Call<MatchInfo> call, Response<MatchInfo> response) {
                                                                    if (response.isSuccessful()) {
                                                                        if (response.code() == 200) {
                                                                            System.out.println("TEST : 매치인포");
                                                                            DataHandlerObject.matchInfos = response.body();
                                                                            spellInfo si = new spellInfo();
                                                                            championInfo ci = new championInfo();
                                                                            for (int j = 0; j < 10; j++) {
                                                                                System.out.println("TEST : Participants 찾기");
                                                                                Participant pa = DataHandlerObject.matchInfos.getInfo().getParticipant().get(j);
                                                                                if (pa.getSummonerName().equals(DataHandlerObject.summonerName)) {
                                                                                    System.out.println("TEST : Participants 찾았다!!!");
                                                                                    searchItemObjectList.add(new SearchItemObject() {{
                                                                                        setWin(pa.getWin());
                                                                                        setKda(pa.getKills().toString() + "/" + pa.getDeaths().toString() + "/" + pa.getAssists().toString());
                                                                                        int hour = pa.getTimePlayed() / 60;
                                                                                        int min = pa.getTimePlayed() % 60;
                                                                                        //setGameTime(Integer.toString(hour) + ":" + Integer.toString(min));
                                                                                        setChampion("https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/champion/"+ ci.cif(pa.getChampionId()) +".png"); //챔피언 아이콘

                                                                                        setItem(new String[]{
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem0() + ".png",
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem1() + ".png",
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem2() + ".png",
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem3() + ".png",
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem4() + ".png",
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem5() + ".png"
                                                                                        });
                                                                                        System.out.println("SPELL : " + pa.getSummoner2Id() + " + " + si.sif(pa.getSummoner2Id()));
                                                                                        setSpell(new String[]{"https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/spell/"+ si.sif(pa.getSummoner1Id()) +".png", //스펠
                                                                                                "https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/spell/"+  si.sif(pa.getSummoner2Id()) +".png"});

                                                                                        setRunes(new String[]{
                                                                                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png", //룬
                                                                                                "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/7200_Domination.png"});

                                                                                        setTotem("https://ddragon.leagueoflegends.com/cdn/" + gameVersion + "/img/item/" + pa.getItem6() + ".png");

                                                                                    }});

                                                                                    searchRecyclerView.setAdapter(recyclerAdapter);
                                                                                    searchRecyclerView.addItemDecoration(dividerItemDecoration);
                                                                                    recyclerAdapter.setItemObjectList(searchItemObjectList);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<MatchInfo> call, Throwable t) {

                                                                }
                                                            });
                                                        }
                                                    } else {
                                                        //매치리스트 불러오기 실패
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<List<String>> call, Throwable t) {
                                            }
                                        });
                                    } else {
                                        //리그 인포 실패 시
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<List<LeagueInfo>> call, Throwable t) {

                                System.out.println(t.toString());
                            }
                        });
                    } else {
                        //소환사 서치 실패
                    }
                }
            }

            @Override
            public void onFailure(Call<SummonerId> call, Throwable t) {
            }
        });


        android.widget.Button predictionButton = inflateView.findViewById(R.id.prediction_button);// 승리 예측 버튼
        predictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(inflateView).navigate(R.id.action_searchFragment_to_predictionFragment);
            }
        });

        android.widget.Button inGameButton = inflateView.findViewById(R.id.ingame_button); // 인게임 버튼
        inGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(inflateView).navigate(R.id.action_searchFragment_to_inGameFragment);
            }
        });
        return inflateView;
    }
}

/*


 */