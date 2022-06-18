package com.example.mobile;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/*
    리스트뷰 구현을 위해 사용되는 어댑터
*/
public class ListViewAdapter extends BaseAdapter {

    public ArrayList<AllergyItem> items;

    public ListViewAdapter(ArrayList<AllergyItem> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(AllergyItem item) {
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

        if (view == null) {
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
        if (aItem.check) {
            chip.setImageResource(android.R.drawable.star_big_on);
        } else {
            chip.setImageResource(android.R.drawable.star_big_off);
        }
        //즐겨찾기 버튼에 클릭이벤트 리스너 넣음
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //만약 즐겨찾기 변수가 참이면 거짓으로 바꾸고 꺼진 별 이미지 삽입
                //거짓이면 참으로 바꾸고 켜진 별 이미지 삽입
                if (aItem.getCheck()) {
                    aItem.check = false;
                    chip.setImageResource(android.R.drawable.star_big_off);

                    stardel(aItem, context);
                } else {
                    aItem.check = true;
                    chip.setImageResource(android.R.drawable.star_big_on);

                    staradd(aItem, context);
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

    //선택한 항목 파일에 추가
    public void staradd(AllergyItem a, Context context) {
        //파일 읽어서 내용물 있으면 그거랑 새로운 값이랑 합쳐주세요 없으면 파일 세로 만들어요
        try {
            //파일 읽기
            FileInputStream infs = context.openFileInput("file.txt");
            byte[] txt = new byte[infs.available()];
            infs.read(txt);
            String str = new String(txt);

            //파일 저장
            FileOutputStream outfs = context.openFileOutput("file.txt", Context.MODE_PRIVATE);
            outfs.write((str + "\n" + a.getName()).getBytes(StandardCharsets.UTF_8));
            outfs.close();
        } catch (Exception e) {
            //파일 없으면 그냥 만들어요
            try {
                FileOutputStream outfs = context.openFileOutput("file.txt", Context.MODE_PRIVATE);
                outfs.write((a.getName()).getBytes(StandardCharsets.UTF_8));
                outfs.close();
            } catch (Exception e2) {

            }

        }
    }

    //선택한 항목 파일에서 삭제
    public void stardel(AllergyItem a, Context context) {
        //파일 읽어서 그거랑 같은 내용물 그거 삭제할 거임
        try {
            //파일 읽기
            FileInputStream infs = context.openFileInput("file.txt");
            byte[] txt = new byte[infs.available()];
            infs.read(txt);
            String str = new String(txt);

            //읽어온 파일 \n으로 나눌거임
            String[] array = str.split("\n");


            //나눈거 다 뒤져서 똑같은 거 있는 지 확인 후 그 위치 저장
            int index = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(a.getName())) {
                    index = i ;

                    break;
                }
            }

            String stroutput = "";
            for (int i = 0; i < array.length; i++) {
                if (i == index) {

                    continue;
                }
                if(i==0){
                    stroutput = array[0];

                    continue;
                }
                stroutput = stroutput+"\n"+array[i];

            }

            //파일 저장
            FileOutputStream outfs = context.openFileOutput("file.txt", Context.MODE_PRIVATE);
            outfs.write(stroutput.getBytes(StandardCharsets.UTF_8));
            outfs.close();

        } catch (Exception e) {

        }
    }
}
