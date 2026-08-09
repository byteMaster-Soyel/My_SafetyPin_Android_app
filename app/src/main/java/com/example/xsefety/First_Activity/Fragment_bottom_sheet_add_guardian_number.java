package com.example.xsefety.First_Activity;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.xsefety.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_bottom_sheet_add_guardian_number extends BottomSheetDialogFragment {

    View view;

    String guardian_phone;
    boolean b=false;
    ListView list_view;
    Button add_another_guardian_btn,btn_guardian_show,btn_guardian_delete;
    com.google.android.material.textfield.TextInputEditText add_guardian_mobile_et;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_bottom_sheet_add_guardian_number, container, false);

        add_guardian_mobile_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.add_guardian_mobile_et);
        add_guardian_mobile_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                guardian_phone = add_guardian_mobile_et.getText().toString();
                if (guardian_phone.startsWith("+91")) {
                    if (validateMobile(guardian_phone.substring(3))) {
                        b = true;
                    } else
                    {
                        add_guardian_mobile_et.setError("Invalid mobile number");
                    }
                } else {
                    if (validateMobile(guardian_phone)) {
                        b = true;
                        guardian_phone = "+91" + guardian_phone;
                    } else
                    {
                        add_guardian_mobile_et.setError("Invalid mobile number");
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        ArrayList<String> text=new ArrayList<>();

        btn_guardian_show=(Button) view.findViewById(R.id.btn_guardian_show);
        btn_guardian_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
                ArrayList<UserModel_two> al = myDbHelper.GetAllGuardianPhone();
                ArrayList<String> text = new ArrayList<>();
                if (al != null) {
                    for (int i = 0; i < al.size(); i++) {
                        text.add(al.get(i).getGuardian_phone());
                    }
                }
                list_view = (ListView) view.findViewById(R.id.list_view_main);
                ArrayAdapter<String> myadaptor = new ArrayAdapter<String>(view.getContext(), R.layout.ui_layout, R.id.text_view, text);
                list_view.setAdapter(myadaptor);
            }
        });

        text.clear();
        add_another_guardian_btn=(Button) view.findViewById(R.id.add_another_guardian_btn);
        add_another_guardian_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (b) {
                        MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
                        ArrayList<UserModel_two> al = myDbHelper.GetAllGuardianPhone();
                        if (al !=null) {
                            text.clear();
                            if(al.size()<4) {
                                for (int i = 0; i < al.size(); i++) {
                                    text.add(al.get(i).getGuardian_phone());
                                }
                                if(!text.contains(guardian_phone)) {
                                    if (myDbHelper.InsertGuardianHelper(guardian_phone)) {
                                        al = myDbHelper.GetAllGuardianPhone();
                                        ArrayList<String> text = new ArrayList<>();
                                        if (al != null) {
                                            for (int i = 0; i < al.size(); i++) {
                                                text.add(al.get(i).getGuardian_phone());
                                            }
                                        }
                                        list_view = (ListView) view.findViewById(R.id.list_view_main);
                                        ArrayAdapter<String> myadaptor = new ArrayAdapter<String>(view.getContext(), R.layout.ui_layout, R.id.text_view, text);
                                        list_view.setAdapter(myadaptor);

                                        Toast.makeText(view.getContext(), "Insert successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(view.getContext(), "Some problem occur please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(view.getContext(), "Insert same guardian phone number again", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(view.getContext(), "Only four gurdian's phone number is store possible", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {

                            if (myDbHelper.InsertGuardianHelper(guardian_phone)) {
                                al = myDbHelper.GetAllGuardianPhone();
                                ArrayList<String> text = new ArrayList<>();
                                if (al != null) {
                                    for (int i = 0; i < al.size(); i++) {
                                        text.add(al.get(i).getGuardian_phone());
                                    }
                                }
                                list_view = (ListView) view.findViewById(R.id.list_view_main);
                                ArrayAdapter<String> myadaptor = new ArrayAdapter<String>(view.getContext(), R.layout.ui_layout, R.id.text_view, text);
                                list_view.setAdapter(myadaptor);
                                text.clear();
                                Toast.makeText(view.getContext(), "Insert successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(view.getContext(), "Some problem occur", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Enter valid phone number", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        btn_guardian_delete=(Button) view.findViewById(R.id.btn_guardian_delete);
        btn_guardian_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper myDbHelper=new MyDbHelper(view.getContext());
                if (guardian_phone!=null)
                {
                    if (myDbHelper.deleteHelper(guardian_phone))
                    {
                        Toast.makeText(view.getContext(), "Delete successfully", Toast.LENGTH_SHORT).show();
                        ArrayList<UserModel_two> al = myDbHelper.GetAllGuardianPhone();
                        ArrayList<String> text = new ArrayList<>();
                        if (al != null) {
                            for (int i = 0; i < al.size(); i++) {
                                text.add(al.get(i).getGuardian_phone());
                            }
                        }
                        list_view = (ListView) view.findViewById(R.id.list_view_main);
                        ArrayAdapter<String> myadaptor = new ArrayAdapter<String>(view.getContext(), R.layout.ui_layout, R.id.text_view, text);
                        list_view.setAdapter(myadaptor);
                    }
                    else
                    {
                        Toast.makeText(view.getContext(), "invalid  phone number", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        return view;
    }
    boolean validateMobile(String phone) {
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}