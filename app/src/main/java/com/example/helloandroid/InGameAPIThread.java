package com.example.helloandroid;

public class InGameAPIThread extends Thread{
    RiotAPIClass riotAPIClass;
    public void run(){
        riotAPIClass = new RiotAPIClass();
        riotAPIClass.setSummonerName(DataHandlerObject.summonerName);

    }
}
