package com.example.helloandroid.Parser;

public class spellInfo {
    String sp;
    public String sif(int spell){
        switch(spell){
            case 1:     //정화
                sp = "SummonerBoost";
                break;
            case 3:     //탈진
                sp = "SummonerExhaust";
                break;
            case 4:     //점멸
                sp = "SummonerFlash";
                break;
            case 6:     //유체화
                sp = "SummonerHaste";
                break;
            case 7:     //회복
                sp = "SummonerHeal";
                break;
            case 11:    //강타
                sp = "SummonerSmite";
                break;
            case 12:    //순간이동
                sp = "SummonerTeleport";
                break;
            case 13:    //총명
                sp = "SummonerMana";
                break;
            case 14:    //점화
                sp = "SummonerDot";
                break;
            case 21:    //방어막
                sp = "SummonerBarrier";
                break;
            case 32:    //표식
                sp = "SummonerSnowball";
                break;
        }
        return sp;
    }
}
