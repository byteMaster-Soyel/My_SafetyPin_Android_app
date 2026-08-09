package com.example.xsefety.First_Activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.xsefety.R;


public class BlankFragment_temp extends Fragment {

    View view;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_blank_temp, container, false);

       //  editText=(EditText) view.findViewById(R.id.black_edit);

         return view;
    }
}