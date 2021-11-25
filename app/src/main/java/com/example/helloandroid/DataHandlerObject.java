//작성자: 김선호
//작성일자 : 21-11-13
//클래스 목적 : 어플리케이션 전체의 데이터 핸들링을 위한 클래스

package com.example.helloandroid;

import android.widget.GridLayout;

import com.example.helloandroid.Parser.Icon;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.MatchInfo;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SummonerId;

import java.util.List;

public class DataHandlerObject {
    //필요한 데이터는 여기서 뽑아서 쓰면 됩니다
    public static List<LeagueInfo> leagueInfos; //랭크 별 정보
    public static SummonerId summonerIds; //소환사 정보
    public static List<String> matchLists; //매치 리스트
    public static MatchInfo matchInfos; //매치 정보
    public static Spector spector; //현재 진행중인 게임 정보
    public static List<Icon> icons;

    //Merge
}
