package com.example.mobile;

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
    boolean flag = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragment = inflater.inflate(R.layout.fragment_all, container, false);

        ImageButton btn = fragment.findViewById(R.id.button1);
        dialogView = getLayoutInflater().inflate(R.layout.info,null);
        Button btend = dialogView.findViewById(R.id.btnEnd);
        ImageButton btstart = dialogView.findViewById(R.id.starBtn);

        Dialog mDialog = new Dialog(this.getContext());
        mDialog.setTitle("상세 정보");
        mDialog.setContentView(dialogView);
        mDialog.setCancelable(true);
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        btstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    flag=false;
                    btstart.setImageResource(android.R.drawable.star_big_on);
                }else{
                    flag=true;
                    btstart.setImageResource(android.R.drawable.star_big_off);
                }
            }
        });

        btend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.show();
            }
        });

        return fragment;
    }
}