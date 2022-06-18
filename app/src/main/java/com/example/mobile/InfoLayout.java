package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class InfoLayout extends AppCompatActivity {

    AllFragment af;
    StarFragment sf;
    Button Sub_alBtn, Sub_StarBtn;
    ImageView Sub_iv;
    FragmentManager manager;


    //뒤로가기 버튼 클릭시 메인화면으로 이동
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_layout);

        Intent intent = getIntent();
        String ai = intent.getStringExtra("ai");
        Toast.makeText(getApplicationContext(),ai,Toast.LENGTH_SHORT).show();
        //플레그먼트 메니저를 이용해 플레그먼트 변환
        manager = getSupportFragmentManager();
        af = new AllFragment();
        sf = new StarFragment();

        Sub_alBtn = (Button) findViewById(R.id.Sub_alBtn);
        Sub_StarBtn = (Button) findViewById(R.id.Sub_StarBtn);
        Sub_iv = (ImageView) findViewById(R.id.Sub_iv);

        // 처음 나오는 플레그먼트는 AllFragment를 사용
        manager.beginTransaction().replace(R.id.fm_info, af).commit();

        // 모든 알러지 버튼을 클릭하면 AllFragment로 이동
        Sub_alBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction().replace(R.id.fm_info, af).commit();
            }
        });

        // 관심 알러지 버튼을 클릭하면 StarFragment로 이동
        Sub_StarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction().replace(R.id.fm_info, sf).commit();
            }
        });

    }
}