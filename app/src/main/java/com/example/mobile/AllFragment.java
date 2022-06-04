package com.example.mobile;

import static com.example.mobile.StartProgram.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AllFragment extends Fragment {
    View dialogView;
    AllergyItem item = (AllergyItem)adapter.getItem(7);
    boolean flag = item.check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragment = inflater.inflate(R.layout.fragment_all, container, false); //플레그먼트 뷰로 받아오기

        ImageButton btn = fragment.findViewById(R.id.button1); //플레그먼트에 버튼 설정
        dialogView = getLayoutInflater().inflate(R.layout.info,null); //다이어로그 생성
        Button btend = dialogView.findViewById(R.id.btnEnd); //버튼 생성
        ImageButton btstart = dialogView.findViewById(R.id.starBtn); //이미지 버튼 생성
        
        // 생성하지마자 버튼을 활성화 할지 않할지 결정할 코드
        if(item.check){
            btstart.setImageResource(android.R.drawable.star_big_on); // 버튼 클릭시 즐겨찾기 버튼 활성화
        }else{
            btstart.setImageResource(android.R.drawable.star_big_off); //버튼 클릭시 즐겨찾기 버튼 끄기
        }

        Dialog mDialog = new Dialog(this.getContext());//바이어로그 샡성
        mDialog.setTitle("상세 정보"); //다이어로그 타이틀 생성
        mDialog.setContentView(dialogView); //다이어로그의 레이아웃 지정
        mDialog.setCancelable(true); //다이어로그 설정
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes(); //다이어로그 크기를 위해 크기 지정

        //다이어로그를 전체 화면 사이즈로
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;

        //다이어로그 사이즈 적용
        mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        // 버튼 클릭시 즐겨찾기 버튼 설정
        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.check){
                    item.check=false;
                    btstart.setImageResource(android.R.drawable.star_big_off);
                }else{
                    item.check=true;
                    btstart.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });
        //종료 버튼 눌렀을 시 다이이로그창 끄기
        btend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        //아이콘 클릭시 다이어로그 출력
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.show();
            }
        });

        return fragment;
    }
}