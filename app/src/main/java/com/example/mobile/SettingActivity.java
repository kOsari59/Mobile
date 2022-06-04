package com.example.mobile;

import static android.content.ContentValues.TAG;

import static com.example.mobile.StartProgram.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    //레이아웃의 리스트뷰와 검색에 사용될 에딧텍스트 변수
    ListView listView;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //각각 위젯과 매핑
        listView = (ListView) findViewById(R.id.listview);
        et_search = (EditText) findViewById(R.id.et_search);

        //StartProgram에서 생성한 어뎁터 객체를 리스트뷰에 설정정
        listView.setAdapter(adapter);

        //에딧텍스트에 텍스트변경 리스너를 추가
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //에딧 텍스트의 텍스트값이 변경된 후에, 해당 텍스트 값을 가지고 리스트뷰 검색 메소드 실행
            @Override
            public void afterTextChanged(Editable editable) {

                String text = et_search.getText().toString();
                search(text);
            }
        });
    }
    /*
        리스트뷰 검색하는 메소드
    */
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        arraylist.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            arraylist.addAll(list);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < list.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (list.get(i).name.toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    arraylist.add(list.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }
}


