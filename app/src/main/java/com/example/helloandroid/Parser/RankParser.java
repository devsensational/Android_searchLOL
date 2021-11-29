package com.example.helloandroid.Parser;

public class RankParser{
    public String rankParser(String str){
        if(str.equals("DIAMOND")){
            return "https://z.fow.kr/img/emblem/diamond.png";
        }else if(str.equals("CHALLENGER")){
            return "https://z.fow.kr/img/emblem/challenger.png";
        }else if(str.equals("GRANDMASTER")){
            return "https://z.fow.kr/img/emblem/grandmaster.png";

        }else if(str.equals("MASTER")){
            return "https://z.fow.kr/img/emblem/master.png";

        }else if(str.equals("PLATINUM")){
            return "https://z.fow.kr/img/emblem/platinum.png";

        }else if(str.equals("GOLD")){
            return "https://z.fow.kr/img/emblem/gold.png";

        }else if(str.equals("SILVER")){
            return "https://z.fow.kr/img/emblem/silver.png";

        }else if(str.equals("BRONZE")){
            return "https://z.fow.kr/img/emblem/bronze.png";

        }else if(str.equals("IRON")){
            return "https://z.fow.kr/img/emblem/iron.png";

        }else{
            return "";
        }
    }
}