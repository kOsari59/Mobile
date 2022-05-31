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


        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        ImageButton chip = (ImageButton) view.findViewById(R.id.ib_star);

        tv_name.setText(aItem.getName());

        for(int i=0;i<getCount();i++){

        }

        if(aItem.check){
            chip.setImageResource(android.R.drawable.star_big_on);
        }else {
            chip.setImageResource(android.R.drawable.star_big_off);
        }

        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aItem.check){
                    aItem.check=false;
                    chip.setImageResource(android.R.drawable.star_big_off);
                }
                else{
                    aItem.check=true;
                    chip.setImageResource(android.R.drawable.star_big_on);
                }
            }
        });
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
