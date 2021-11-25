package com.example.helloandroid;

import androidx.annotation.NonNull;

import com.example.helloandroid.Fb.Game;
import com.example.helloandroid.Parser.BannedChampion;
import com.example.helloandroid.Parser.LeagueInfo;
import com.example.helloandroid.Parser.Participant;
import com.example.helloandroid.Parser.Spector;
import com.example.helloandroid.Parser.SpectorParticipant;
import com.example.helloandroid.Parser.SummonerId;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThreadTest extends Thread{
    private DatabaseReference mDatabaseRef;
    Game game = new Game();
    int soloRankNum;
    public void run(){
            try {
                Thread.sleep(2000);
                soloRankNum = DataHandlerObject.leagueInfos.size() - 1;
                mDatabaseRef = FirebaseDatabase.getInstance().getReference();

                //검색한 게임 계정 정보
                game.setname(DataHandlerObject.summonerIds.getName());
                game.setpuuId(DataHandlerObject.summonerIds.getAccountId());
                System.out.println("Test Class : " + DataHandlerObject.leagueInfos.get(soloRankNum).getTier());
                game.settier(DataHandlerObject.leagueInfos.get(soloRankNum).getTier());
                game.setrank(DataHandlerObject.leagueInfos.get(soloRankNum).getRank());
                game.setleaguePoints(Integer.toString(DataHandlerObject.leagueInfos.get(soloRankNum).getLeaguePoints()));
                game.setwins(Integer.toString(DataHandlerObject.leagueInfos.get(soloRankNum).getWins()));
                game.setlosses(Integer.toString(DataHandlerObject.leagueInfos.get(soloRankNum).getLosses()));

                //현재 진행중인 게임
                //pg.setname(DataHandlerObject.summonerIds.getName());
                //System.out.println("Test Class : " + DataHandlerObject.spector.getGameId());
                //pg.setgameId(DataHandlerObject.spector.getGameId());
                //System.out.println("Test Class : " + DataHandlerObject.spector.getgameLength());
                //pg.setgameMode(DataHandlerObject.spector.getgameMode());
                //pg.setgameType(DataHandlerObject.spector.getgameType());
                //pg.setgameLength(DataHandlerObject.spector.getgameLength());
                //pg.setParticipants(DataHandlerObject.spector.getParticipants());

                //mDatabaseRef.child("match").setValue(DataHandlerObject.matchLists.get(0));

                mDatabaseRef.child("test").child(DataHandlerObject.summonerIds.getName()).setValue(game);
                //mDatabaseRef.child("pgtest").child(DataHandlerObject.summonerIds.getName()).setValue(pg);
                mDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String test = snapshot.child("test").child(DataHandlerObject.summonerIds.getName()).getValue().toString();
                        System.out.println(test);
                        String test2 = snapshot.child("test").child(game.getname()).child("rank").getValue().toString();
                        System.out.println(test2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //System.out.println("Test Class : " + DataHandlerObject.summonerIds.getPuuid());
                //System.out.println("Test Class : " + DataHandlerObject.leagueInfos.get(0).getTier());
                //System.out.println("Test Class : " + DataHandlerObject.leagueInfos.get(0).getRank());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
