package com.example.xsefety.First_Activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.xsefety.R;
import java.util.ArrayList;


public class Fragment_edit_profile_details extends Fragment {

    View view;
    ImageView id_add_guardian_mobile_number_btn ;
    androidx.appcompat.widget.AppCompatButton  save_edit_profile_btn;
    com.google.android.material.textfield.TextInputEditText show_profile_name ,show_profile_mobile_number ,
            show_profile_email_id ,show_profile_guardian_number ,show_profile_pin_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_profile_details, container, false);



        // save button backend code
        save_edit_profile_btn = (androidx.appcompat.widget.AppCompatButton) view.findViewById(R.id.save_edit_profile_btn);
        save_edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Details Saved successfully",Toast.LENGTH_SHORT).show();
            }
        });



        //add phone number btn in edit profile page backend code
        id_add_guardian_mobile_number_btn =(ImageView) view.findViewById(R.id.id_add_guardian_mobile_number_btn);
        id_add_guardian_mobile_number_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment add_one_guardian_number = new Fragment_bottom_sheet_add_guardian_number();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout,add_one_guardian_number,null).addToBackStack(null).commit();

            }
        });

        MyDbHelper myDbHelper=new MyDbHelper(view.getContext());
        sessionManager s=new sessionManager(view.getContext());
        ArrayList<UserModel> al=new ArrayList<>();
        al.addAll(myDbHelper.getUserAllData(s.getSessionDetails("key_session_phone")));


        //   showing profile name in profile details
        show_profile_name=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.show_profile_name);
         show_profile_name.setText(al.get(0).getName());

        //showing profile phone number in profile details
        show_profile_mobile_number =(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.show_profile_mobile_number);
        show_profile_mobile_number.setText(al.get(0).getPhone());

        //showing profile email id in profile details
        show_profile_email_id = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.show_profile_email_id);
        show_profile_email_id.setText(al.get(0).getEmail());

        //showing profile guardian phone number in profile details
        show_profile_guardian_number =(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.show_profile_guardian_number);
        ArrayList<UserModel_two> all=new ArrayList<>();
        try {
            all.addAll(myDbHelper.GetAllGuardianPhone());
            if(all!=null) {
                show_profile_guardian_number.setText(all.get(0).getGuardian_phone());
            }
        }catch (Exception e)
        {
            Toast.makeText(view.getContext(),"Please enter guardian phone number",Toast.LENGTH_SHORT).show();
        }

        //showing profile pin code in profile details
        show_profile_pin_code =(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.show_profile_pin_code);
        show_profile_pin_code.setText(al.get(0).getPin());

        return view;
    }
}