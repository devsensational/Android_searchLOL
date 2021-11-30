package com.example.helloandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloandroid.main_view.MainFragment;
import com.example.helloandroid.main_view.MainFragment_formem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;     //파이어베이스 인증
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, metPwd;      //로그인 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");
        mEtEmail = findViewById(R.id.et_email4);
        metPwd = findViewById(R.id.et_pwd3);

        Button btn_login = findViewById(R.id.btn_login2);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = metPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail,strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            //로그인 성공
                            Intent intent = new Intent(LoginActivity.this, MainFragment_formem.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,"fail",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        Button btn_register = findViewById(R.id.btn_register2);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        Button btn_id = findViewById(R.id.btn_id2);
        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 찾기 화면으로 이동
                Intent intent = new Intent(LoginActivity.this, FindID.class);
                startActivity(intent);

            }
        });
    }
}