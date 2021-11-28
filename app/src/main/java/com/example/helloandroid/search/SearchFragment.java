package com.example.helloandroid.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.helloandroid.DataHandlerObject;
import com.example.helloandroid.R;

import java.util.ArrayList;
import java.util.List;

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
        // Inflate the layout for this fragment
        View inflateView = inflater.inflate(R.layout.fragment_search, container, false);
        ImageView userIconView = inflateView.findViewById(R.id.usericon_imageView);
        ImageView rankIconView = inflateView.findViewById(R.id.rankicon_imageView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        Glide.with(getContext()).load("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/profileicon/4529.png")
                .into(userIconView);

        Glide.with(getContext()).load("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg")
                .into(rankIconView);
        RecyclerView searchRecyclerView = inflateView.findViewById(R.id.searchRecyclerView);
        SearchRecyclerAdapter recyclerAdapter = new SearchRecyclerAdapter();
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<SearchItemObject> searchItemObjectList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            searchItemObjectList.add(new SearchItemObject() {{
                setWin(true);
                setKda("15/5/15");
                setGameTime("30:00");
                setChampion("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/champion/Shyvana.png");
                setItem(new String[]{"https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png"
                });
                setSpell(new String[]{"https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png"});

                setRunes(new String[]{
                        "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/Precision/PressTheAttack/PressTheAttack.png",
                        "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/7200_Domination.png"});

                setTotem("https://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/content/src/leagueclient/wardskinimages/wardhero_0.png");

            }});
            searchItemObjectList.add(new SearchItemObject() {{
                setWin(false);
                setKda("5/5/5");
                setGameTime("30:00");
                setChampion("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/champion/Shyvana.png");
                setItem(new String[]{"https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png",
                        "https://ddragon.leagueoflegends.com/cdn/10.6.1/img/item/3108.png"
                });
            }});
        }


        searchRecyclerView.setAdapter(recyclerAdapter);
        searchRecyclerView.addItemDecoration(dividerItemDecoration);
        recyclerAdapter.setItemObjectList(searchItemObjectList);

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