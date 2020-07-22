package com.example.wachia.menuFragments;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wachia.R;

public class ClientFragment extends Fragment {
    private  onClientSubmitSelected submit_listener;

    private Button submit_client;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.customer,container,false);
        submit_client = view.findViewById(R.id.submit);



        submit_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_listener.onSubmitButtonSelected();
            }
        });

         return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof onClientSubmitSelected){
            submit_listener = (onClientSubmitSelected) context;
        }
        else {
            throw new ClassCastException(context.toString()+" must implement a listener");
        }
    }

    public interface onClientSubmitSelected{
        public void onSubmitButtonSelected();
    }



}
