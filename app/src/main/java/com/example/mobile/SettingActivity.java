package com.example.mobile;

import static android.content.ContentValues.TAG;

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
    List<AllergyItem> list;
    ArrayList<AllergyItem> arraylist;
    ListView listView;
    ListViewAdapter adapter;
    EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listView = (ListView) findViewById(R.id.listview);
        et_search = (EditText) findViewById(R.id.et_search);

        list = new ArrayList<AllergyItem>();

        settingList();

        arraylist = new ArrayList<AllergyItem>();
        arraylist.addAll(list);

        adapter = new ListViewAdapter(arraylist);

        /*adapter.addItem(new AllergyItem("메밀 알러지",false));
        adapter.addItem(new AllergyItem("밀 알러지",false));
        adapter.addItem(new AllergyItem("대두 알러지",false));
        adapter.addItem(new AllergyItem("호두 알러지",false));
        adapter.addItem(new AllergyItem("땅콩 알러지",false));
        adapter.addItem(new AllergyItem("복숭아 알러지",false));
        adapter.addItem(new AllergyItem("토마토 알러지",false));
        adapter.addItem(new AllergyItem("돼지고기 알러지",false));
        adapter.addItem(new AllergyItem("계란 알러지",false));
        adapter.addItem(new AllergyItem("우유 알러지",false));
        adapter.addItem(new AllergyItem("닭고기 알러지",false));
        adapter.addItem(new AllergyItem("쇠고기 알러지",false));
        adapter.addItem(new AllergyItem("새우 알러지",false));
        adapter.addItem(new AllergyItem("고등어 알러지",false));
        adapter.addItem(new AllergyItem("홍합 알러지",false));
        adapter.addItem(new AllergyItem("굴 알러지",false));
        adapter.addItem(new AllergyItem("조개류 알러지",false));
        adapter.addItem(new AllergyItem("게 알러지",false));
        adapter.addItem(new AllergyItem("아황산 포함식품 알러지",false));*/

        listView.setAdapter(adapter);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = et_search.getText().toString();
                Log.d("시발", text);
                search(text);
            }
        });
    }

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


