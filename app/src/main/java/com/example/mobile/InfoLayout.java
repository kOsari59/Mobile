package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class InfoLayout extends AppCompatActivity {

    AllFragment af;
    StarFragment sf;
    Button Sub_alBtn, Sub_StarBtn;
    ImageView Sub_iv;
    FragmentManager manager;

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

        manager = getSupportFragmentManager();
        af = new AllFragment();
        sf = new StarFragment();

        Sub_alBtn = (Button) findViewById(R.id.Sub_alBtn);
        Sub_StarBtn = (Button) findViewById(R.id.Sub_StarBtn);
        Sub_iv = (ImageView) findViewById(R.id.Sub_iv);


        manager.beginTransaction().replace(R.id.fm_info, af).commit();

        Sub_alBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction().replace(R.id.fm_info, af).commit();
            }
        });

        Sub_StarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction().replace(R.id.fm_info, sf).commit();
            }
        });

    }
}