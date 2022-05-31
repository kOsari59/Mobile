package com.example.mobile;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    ListView listView;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listView = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter();

        adapter.addItem(new AllergyItem("메밀 알러지",false));
        adapter.addItem(new AllergyItem("밀 알러지",false));
        adapter.addItem(new AllergyItem("대두 알러지",false));
        adapter.addItem(new AllergyItem("호두 알러지",false));
        adapter.addItem(new AllergyItem("땅콩 알러지",false));

        listView.setAdapter(adapter);
    }

    public class ListViewAdapter extends BaseAdapter{

        ArrayList<AllergyItem> items = new ArrayList<>();

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


            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            Switch chip = (Switch) view.findViewById(R.id.swich_star);

            tv_name.setText(aItem.getName());
            chip.setChecked(aItem.getCheck());
            Log.d(TAG, "getView() - [ "+position+" ] "+aItem.getName());

            //각 아이템 선택 event
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, aItem.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }

}