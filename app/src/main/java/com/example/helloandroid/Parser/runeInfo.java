package com.example.helloandroid.Parser;

public class runeInfo {
    String rune;
    public String rif(int runenum){
        switch (runenum){
                //정밀
            case 8005:  //집중공격
                rune = "Precision/PressTheAttack/PressTheAttack";
                break;
            case 8008:  //치명적 속도
                rune = "Precision/LethalTempo/LethalTempoTemp";
                break;
            case 8021:  //기민한 발놀림
                rune = "Precision/FleetFootwork/FleetFootwork";
                break;
            case 8010:  //정복자
                rune = "Precision/Conqueror/Conqueror";
                break;

                //지배
            case 8112:  //감전
                rune = "Domination/Electrocute/Electrocute";
                break;
            case 8124:  //포식자
                rune = "Domination/Predator/Predator";
                break;
            case 8128:  //어둠의 수확
                rune = "Domination/DarkHarvest/DarkHarvest";
                break;
            case 9923:  //칼날비
                rune = "Domination/HailOfBlades/HailOfBlades";
                break;

                //마법
            case 8214:  //콩콩이소환환
               rune = "Sorcery/SummonAery/SummonAery";
                break;
            case 8229:  //신비로운 유성
                rune = "Sorcery/ArcaneComet/ArcaneComet";
                break;
            case 8230:  //난입
                rune = "Sorcery/PhaseRush/PhaseRush";
                break;

                //결의
            case 8437:  //착취의 손아귀
                rune = "Resolve/GraspOfTheUndying/GraspOfTheUndying";
                break;
            case 8439:  //여진
                rune = "Resolve/VeteranAftershock/VeteranAftershock";
                break;
            case 8465:  //수호자
                rune = "Resolve/Guardian/Guardian";
                break;

                //영감
            case 8351:  //빙결 강화
                rune = "Inspiration/GlacialAugment/GlacialAugment";
                break;
            case 8360:  //봉인 풀린 주문서
                rune = "Inspiration/UnsealedSpellbook/UnsealedSpellbook";
                break;
            case 8369:  //선제공격
                rune = "Inspiration/FirstStrike/FirstStrike";
                break;

        }
        return rune;
    }
}
