package com.example.mobile;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/*
    리스트뷰 구현을 위해 사용되는 어댑터
*/
public class ListViewAdapter extends BaseAdapter {

    public ArrayList<AllergyItem> items;

    public ListViewAdapter(ArrayList<AllergyItem> items) {
        this.items=items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(AllergyItem item){
        items.add(item);
    }


    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final AllergyItem aItem = items.get(position);

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_list_item, viewGroup, false);

        } else {
            View v = new View(context);
            v = (View) view;
        }

        //리스트뷰 하나하나 칸의 있는 위젯 변수 (알러지 이름 텍스트뷰, 즐겨찾기 버튼)
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageButton chip = (ImageButton) view.findViewById(R.id.ib_star);

        //알러지 이름 입력
        tv_name.setText(aItem.getName());

        //즐겨찾기 버튼
        if(aItem.check){
            chip.setImageResource(android.R.drawable.star_big_on);
        }else {
            chip.setImageResource(android.R.drawable.star_big_off);
        }
        //즐겨찾기 버튼에 클릭이벤트 리스너 넣음
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //만약 즐겨찾기 변수가 참이면 거짓으로 바꾸고 꺼진 별 이미지 삽입
                //거짓이면 참으로 바꾸고 켜진 별 이미지 삽입
                if(aItem.getCheck()){
                    aItem.check=false;
                    chip.setImageResource(android.R.drawable.star_big_off);
                }
                else{
                    aItem.check=true;
                    chip.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });

        //각 아이템 선택 event
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, aItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
