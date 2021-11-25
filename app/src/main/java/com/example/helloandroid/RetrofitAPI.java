//작성자: 김선호
//작성일자 : 21-11-13
//인터페이스 목적 : RetrofitAPI를 이용하여 RiotAPI를 호출하기 위한 인터페이스

package com.example.helloandroid;

import com.example.helloandroid.Parser.Icon;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.MatchInfo;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SummonerId;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    Call<SummonerId> getSummerId(
                @Path("summonerName") String summonerName,
                @Query("api_key") String api_key
        );

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    Call<List<LeagueInfo>> getLeagueInfo(
            @Path("encryptedSummonerId") String encryptedSummonerId,
            @Query("api_key") String api_key
    );

    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    Call<List<String>> getList(
            @Path("puuid") String puuidStr,
            @Query("start") int startNum,
            @Query("count") int countNUm,
            @Query("api_key") String api_key
    );


    @GET("/lol/match/v5/matches/{matchId}")
    Call<MatchInfo> getMatchInfo(
            @Path("matchId") String matchId,
            @Query("api_key") String api_key
    );

    @GET("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    Call<Spector> getSpector(
            @Path("encryptedSummonerId") String encryptedSummonerId,
            @Query("api_key") String api_key
    );

    @GET("/cdn/10.6.1/data/en_US/runesReforged.json")
    Call<List<Icon>> getIcon();
}
