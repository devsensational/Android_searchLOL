
package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final static String apiKey = "RGAPI-d9babf10-fb50-4b39-bf8e-bee271691f97";
    //public static DataHandelObject dataHandelObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RiotAPIClass riotAPIClass = new RiotAPIClass();
        ThreadTest tt = new ThreadTest();
        riotAPIClass.setSummonerName("전자화폐"); //전적갱신 예시
        riotAPIClass.start();
        ThreadTest th1 = new ThreadTest(); //UI예시
        th1.start();
    }
}
