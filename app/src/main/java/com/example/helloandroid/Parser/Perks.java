//작성자: 김선호
//작성일자 : 21-11-20
//클래스 목적 : 실시간 플레이중인 게임 중 Perk 정보 파싱을 위한 데이터셋

package com.example.helloandroid.Parser;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Perks {

    @SerializedName("perkIds")
    @Expose
    private List<Integer> perkIds = null;
    @SerializedName("perkStyle")
    @Expose
    private Integer perkStyle;
    @SerializedName("perkSubStyle")
    @Expose
    private Integer perkSubStyle;

    @SerializedName("statPerks")
    @Expose
    private StatPerks statPerks;
    @SerializedName("styles")
    @Expose
    private List<Style> styles = null;

    public StatPerks getStatPerks() {
        return statPerks;
    }

    public void setStatPerks(StatPerks statPerks) {
        this.statPerks = statPerks;
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }
    public List<Integer> getPerkIds() {
        return perkIds;
    }

    public void setPerkIds(List<Integer> perkIds) {
        this.perkIds = perkIds;
    }

    public Integer getPerkStyle() {
        return perkStyle;
    }

    public void setPerkStyle(Integer perkStyle) {
        this.perkStyle = perkStyle;
    }

    public Integer getPerkSubStyle() {
        return perkSubStyle;
    }

    public void setPerkSubStyle(Integer perkSubStyle) {
        this.perkSubStyle = perkSubStyle;
    }



}
