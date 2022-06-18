package com.example.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
어플리케이션이 처음 실행되었을 떄, 실행되는 자바코드
어플에서 사용될 데이터를 초기화한다.
*/
public class StartProgram extends AppCompatActivity {

    //activity_setting (관심알러지 설정화면)에 리스트뷰를 설정하기 위해 사용되는 어뎁터
    public static ListViewAdapter adapter;

    //activity_setting (관심알러지 설정화면)에 리스트뷰 검색 기능을 구현하기 위해 배열을 2개 사용
    public static List<AllergyItem> list;
    public static ArrayList<AllergyItem> arraylist;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<AllergyItem>();
        arraylist = new ArrayList<AllergyItem>();

        //리스트에 데이터를 설정하는 함수
        settingList(this);

        arraylist.addAll(list);

        //모든 데이터값을 넣고 어뎁터 객체를 생성
        adapter = new ListViewAdapter(arraylist);

        //MainActivity(메인화면) 액티비티를 실행
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    /*
        리스트에 데이터를 설정하는 함수
     */
    private void settingList(Context context) {

        list.add(new AllergyItem("메밀", false));
        list.add(new AllergyItem("밀", false));
        list.add(new AllergyItem("대두", false));
        list.add(new AllergyItem("호두", false));
        list.add(new AllergyItem("땅콩", false));
        list.add(new AllergyItem("복숭아", false));
        list.add(new AllergyItem("토마토", false));
        list.add(new AllergyItem("돼지고기", false));
        list.add(new AllergyItem("계란", false));
        list.add(new AllergyItem("우유", false));
        list.add(new AllergyItem("닭고기", false));
        list.add(new AllergyItem("쇠고기", false));
        list.add(new AllergyItem("새우", false));
        list.add(new AllergyItem("고등어", false));
        list.add(new AllergyItem("홍합", false));
        list.add(new AllergyItem("굴", false));
        list.add(new AllergyItem("조개류", false));
        list.add(new AllergyItem("게", false));
        list.add(new AllergyItem("아황산 포함식품", false));

        try {
            //파일 읽기
            FileInputStream infs = context.openFileInput("file.txt");
            byte[] txt = new byte[infs.available()];
            infs.read(txt);



            //파일 파싱하기
            String str = new String(txt);
            String[] array = str.split("\n");

            for(int i = 0; i<list.size();i++){
                for(int j = 0; j< array.length;j++){
                    if(list.get(i).name.equals(array[j])){
                        list.get(i).check = true;
                    }
                }

            }


        } catch (Exception e) {

        }

    }
}
