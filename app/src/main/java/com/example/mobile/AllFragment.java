package com.example.mobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AllFragment extends Fragment {
    View dialogView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragment = inflater.inflate(R.layout.fragment_all, container, false);

        Button btn = fragment.findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogView = getLayoutInflater().inflate(R.layout.info,null);
                Dialog mDialog = new Dialog(v.getContext());
                mDialog.setTitle("상세 정보");
                mDialog.setContentView(dialogView);
                mDialog.setCancelable(true);
                WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.MATCH_PARENT;
                mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
                mDialog.show();
            }
        });

        return fragment;
    }
}