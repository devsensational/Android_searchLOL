package com.example.helloandroid;

import android.provider.ContactsContract;

public class InGameAPIThread extends Thread{
    RiotAPIClass riotAPIClass;
    public void run(){
        riotAPIClass = new RiotAPIClass();
        riotAPIClass.setSummonerName(DataHandlerObject.summonerName);
        riotAPIClass.findSpectorAPI();

    }
}
