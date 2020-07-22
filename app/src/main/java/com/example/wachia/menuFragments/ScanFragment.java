package com.example.wachia.menuFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wachia.Activity.Scan;
import com.example.wachia.R;
import com.google.zxing.integration.android.IntentIntegrator;

public class ScanFragment extends Fragment {
   private  onFragmentScanButtonSelected scan_button_listener;
    private Button scan_button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_scanner,container,false);



        scan_button = view.findViewById(R.id.scan);
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan_button_listener.onScanButtonselected();

            }
        });
        return  view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentScanButtonSelected){
            scan_button_listener = (onFragmentScanButtonSelected) context;
        }else {
            throw new ClassCastException(context.toString()+ " must implement listener");
        }

    }

    public interface onFragmentScanButtonSelected{
        public void onScanButtonselected();

    }
}

