//프로젝트 메인 클래스
//선문대학교 모바일SW프로젝트 13분반
//프로젝트 명 : RiotAPI를 이용한 전적검색 및 승리예측
//조: 9조
//프로젝트 시작일 : 2021/09/08
//조장 : 2016244078 김선호
//조원
// 2016244101 김희목
// 2017243055 최백균
// 2017243065 이종영
// 2018243004 고동현

package com.example.helloandroid;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String apiKey = "RGAPI-d9babf10-fb50-4b39-bf8e-bee271691f97"; //API 키
    //public static DataHandelObject dataHandelObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}