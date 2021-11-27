package com.example.helloandroid;

import androidx.annotation.NonNull;

import com.example.helloandroid.Fb.Game;
import com.example.helloandroid.Parser.BannedChampion;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.Participant;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SpectorParticipant;
import com.example.helloandroid.Parser.SummonerId;


public class ThreadTest extends Thread{

    public void run(){
            try {
                Thread.sleep(2000);



            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
