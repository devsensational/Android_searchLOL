package com.example.helloandroid.inGame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helloandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 인게임 화면을 구성하는 Fragment
 *
 * @author 고동현
 * @since 2021-11-20
 */
public class InGameFragment extends Fragment {


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

        List<InGameDataObject> inGameDataObjects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            inGameDataObjects.add(new InGameDataObject() {{
                setBlueOrRed(true);
                setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/champion/Shyvana.png");
                setNickname("닉네임칸");
                setSpell1ImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png");
                setSpell2ImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png");

                setTearText("D3");
                setWinRate("55%");
                setTearImageUrl("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg");

            }});
        }

        for (int i = 5; i < 10; i++) {
            inGameDataObjects.add(new InGameDataObject() {{
                setBlueOrRed(false);
                setChampionImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/champion/Shyvana.png");
                setNickname("닉네임칸");
                setSpell1ImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png");
                setSpell2ImageUrl("https://ddragon.leagueoflegends.com/cdn/10.6.1/img/spell/SummonerBoost.png");

                setTearText("D3");
                setWinRate("55%");
                setTearImageUrl("https://opgg-com-image.akamaized.net/attach/images/20190916020813.596917.jpg");

            }});
        }

        inGameRecyclerView.setAdapter(inGameRecyclerAdapter);
        inGameRecyclerAdapter.setInGameDataObjectList(inGameDataObjects);
        inGameRecyclerView.addItemDecoration(dividerItemDecoration);
        return inflateView;
    }

}