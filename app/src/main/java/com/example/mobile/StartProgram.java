package com.example.mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class StartProgram extends AppCompatActivity {


    public static ListViewAdapter adapter;

    public static List<AllergyItem> list;
    //데이터 저장
    public static ArrayList<AllergyItem> arraylist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        list = new ArrayList<AllergyItem>();

        settingList();

        arraylist = new ArrayList<AllergyItem>();
        arraylist.addAll(list);

        adapter= new ListViewAdapter(arraylist);


        /*adapter.addItem(new AllergyItem("메밀",false));
        adapter.addItem(new AllergyItem("밀",false));
        adapter.addItem(new AllergyItem("대두",false));
        adapter.addItem(new AllergyItem("호두",false));
        adapter.addItem(new AllergyItem("땅콩",false));
        adapter.addItem(new AllergyItem("복숭아",false));
        adapter.addItem(new AllergyItem("토마토",false));
        adapter.addItem(new AllergyItem("돼지고기",false));
        adapter.addItem(new AllergyItem("계란",false));
        adapter.addItem(new AllergyItem("우유",false));
        adapter.addItem(new AllergyItem("닭고기",false));
        adapter.addItem(new AllergyItem("쇠고기",false));
        adapter.addItem(new AllergyItem("새우",false));
        adapter.addItem(new AllergyItem("고등어",false));
        adapter.addItem(new AllergyItem("홍합",false));
        adapter.addItem(new AllergyItem("굴",false));
        adapter.addItem(new AllergyItem("조개류",false));
        adapter.addItem(new AllergyItem("게",false));
        adapter.addItem(new AllergyItem("아황산 포함식품",false));*/



        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void settingList(){

        list.add(new AllergyItem("메밀",false));
        list.add(new AllergyItem("밀",false));
        list.add(new AllergyItem("대두",false));
        list.add(new AllergyItem("호두",false));
        list.add(new AllergyItem("땅콩",false));
        list.add(new AllergyItem("복숭아",false));
        list.add(new AllergyItem("토마토",false));
        list.add(new AllergyItem("돼지고기",false));
        list.add(new AllergyItem("계란",false));
        list.add(new AllergyItem("우유",false));
        list.add(new AllergyItem("닭고기",false));
        list.add(new AllergyItem("쇠고기",false));
        list.add(new AllergyItem("새우",false));
        list.add(new AllergyItem("고등어",false));
        list.add(new AllergyItem("홍합",false));
        list.add(new AllergyItem("굴",false));
        list.add(new AllergyItem("조개류",false));
        list.add(new AllergyItem("게",false));
        list.add(new AllergyItem("아황산 포함식품",false));

    }
}
