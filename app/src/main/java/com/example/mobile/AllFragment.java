package com.example.mobile;

import static com.example.mobile.Item.NameArray;
import static com.example.mobile.Item.imageArray;
import static com.example.mobile.MainActivity.api;
import static com.example.mobile.StartProgram.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllFragment extends Fragment {
    View dialogView;
    //AllergyItem item = (AllergyItem) adapter.getItem(7);
    boolean flag = false;
    int length = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayList<ImageView> buttons = new ArrayList<>();
        ArrayList<LinearLayout> LLayout = new ArrayList<>();
        String[] array = api.code.split(", ");
        length = array.length - 1;
        array[length] = array[length].substring(0, array[length].length() - 2);


        View fragment = inflater.inflate(R.layout.fragment_all, container, false); //플레그먼트 뷰로 받아오기


        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT); //레이아웃파라미터 생성
        pm.gravity = Gravity.CENTER; //버튼의 Gravity를 지정
        pm.weight = 1f;
        pm.setMargins(10,10,10,10);

        LinearLayout layout = fragment.findViewById(R.id.layoutid);
        dialogView = getLayoutInflater().inflate(R.layout.info, null); //다이어로그 생성
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

        Button btend = dialogView.findViewById(R.id.btnEnd); //버튼 생성
        ImageButton btstart = dialogView.findViewById(R.id.starBtn); //이미지 버튼 생성
        ImageView iv = dialogView.findViewById(R.id.imageView);
        TextView tv = dialogView.findViewById(R.id.textView);

        int L_index = -1;
        int L_count = 1;
        for (int i = 0; i < array.length; i++) {
            int index = 0;
            if (L_count == 1) {
                LinearLayout LL = new LinearLayout(getContext());
                LinearLayout.LayoutParams Lpm = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,400); //레이아웃파라미터 생성
                pm.weight = 1;
                LL.setLayoutParams(Lpm);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LLayout.add(LL);
                L_count = 0;
                L_index = L_index + 1;
            } else {
                L_count++;
            }

            //버튼 생성부분
            buttons.add(new ImageView(getContext()));
            for (int x = 0; x < NameArray.length; x++) {
                if (array[i].equals(NameArray[x])) {
                    index = x;
                    break;
                }
            }

            final int in = index;
            buttons.get(i).setImageResource(imageArray[index]); //버튼 이미지를 지정(int)
            buttons.get(i).setLayoutParams(pm);
            buttons.get(i).setScaleType(ImageView.ScaleType.FIT_CENTER);
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setText(NameArray[in]);
                    iv.setImageResource(imageArray[in]);
                    mDialog.show();
                    Toast.makeText(getContext(), NameArray[in], Toast.LENGTH_SHORT).show();
                }
            }); //버튼에 OnClickListener를 지정(OnClickListener)

            LLayout.get(L_index).addView(buttons.get(i)); //지정된 뷰에 셋팅완료된 mButton을 추가
        }

        for(int i =0; i<LLayout.size();i++){
            layout.addView(LLayout.get(i));
        }
        layout.setBackgroundResource(R.drawable.border);
        fragment.invalidate();

        btstart.setImageResource(android.R.drawable.star_big_off); // 버튼 클릭시 즐겨찾기 버튼 활성화


        // 버튼 클릭시 즐겨찾기 버튼 설정
        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    flag = false;
                    btstart.setImageResource(android.R.drawable.star_big_off);
                    Toast.makeText(getContext(),tv.getText().toString()+"이(가) 관심 알러지에서 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                } else {
                    flag = true;
                    btstart.setImageResource(android.R.drawable.star_big_on);
                    Toast.makeText(getContext(),tv.getText().toString()+"이(가) 관심 알러지로 등록되었습니다.",Toast.LENGTH_SHORT).show();
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

        return fragment;
    }
}