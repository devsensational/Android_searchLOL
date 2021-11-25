package com.example.helloandroid.search;

public class ChampionDataObject {
    private int championId;
    private String championEnglishName;
    private String championKoreanName;

    public ChampionDataObject(int championId, String championEnglishName, String championKoreanName) {
        this.championId = championId;
        this.championEnglishName = championEnglishName;
        this.championKoreanName = championKoreanName;
    }

    public int getChampionId() {
        return championId;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public String getChampionEnglishName() {
        return championEnglishName;
    }

    public void setChampionEnglishName(String championEnglishName) {
        this.championEnglishName = championEnglishName;
    }

    public String getChampionKoreanName() {
        return championKoreanName;
    }

    public void setChampionKoreanName(String championKoreanName) {
        this.championKoreanName = championKoreanName;
    }

    @Override
    public String toString() {
        return "ChampionDataObject{" +
                "championId=" + championId +
                ", championEnglishName='" + championEnglishName + '\'' +
                ", championKoreanName='" + championKoreanName + '\'' +
                '}';
    }
}
