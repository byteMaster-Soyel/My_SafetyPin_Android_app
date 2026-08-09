package com.example.xsefety.First_Activity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xsefety.R;
import java.util.ArrayList;


public class Fragment_profile_account_details extends Fragment {

    View view;
    ImageView id_add_guardian_mobile_number;
    AppCompatButton edit_profile_btn;
    Button id_change_picture;


    TextView profile_name_show, profile_phone_number_show, profile_email_id_show,
            profile_guardian_phone_show, profile_pin_code_show;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_account_details, container, false);


        // Edit button clickable backend code
        edit_profile_btn = (AppCompatButton) view.findViewById(R.id.edit_profile_btn);
        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment open_edit_details_page = new Fragment_edit_profile_details();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, open_edit_details_page,null).addToBackStack(null).commit();

            }
        });

        // add guardian mobile number clickable backend code
        id_add_guardian_mobile_number = (ImageView) view.findViewById(R.id.id_add_guardian_mobile_number);
        id_add_guardian_mobile_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment add_one_guardian_number = new Fragment_bottom_sheet_add_guardian_number();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout,add_one_guardian_number,null).addToBackStack(null).commit();
            }
        });

        //SharedPreferces
        sessionManager s=new sessionManager(view.getContext());

        //   showing profile name in profile details
        profile_name_show = (TextView) view.findViewById(R.id.profile_name_show);
        MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
        ArrayList<UserModel> al = new ArrayList<>();
        al.addAll(myDbHelper.getUserAllData(s.getSessionDetails("key_session_phone")));
        profile_name_show.setText(al.get(0).getName());


        //showing profile phone number in profile details
        profile_phone_number_show = (TextView) view.findViewById(R.id.profile_phone_number_show);
        profile_phone_number_show.setText(al.get(0).getPhone());

        //showing profile email id in profile details
        profile_email_id_show = (TextView) view.findViewById(R.id.profile_email_id_show);
        profile_email_id_show.setText(al.get(0).getEmail());

        //showing profile guardian phone number in profile details
        profile_guardian_phone_show = (TextView) view.findViewById(R.id.profile_guardian_phone_show);
        ArrayList<UserModel_two> al1=new ArrayList<>();
        al1=myDbHelper.GetAllGuardianPhone();
        if(al1!=null) {
            profile_guardian_phone_show.setText(al1.get(0).getGuardian_phone());
        }
        //showing profile pin code in profile details
        profile_pin_code_show = (TextView) view.findViewById(R.id.profile_pin_code_show);
        profile_pin_code_show.setText(al.get(0).getPin());


        //change name button
        id_change_picture =(Button) view.findViewById(R.id.id_change_picture);
        id_change_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"This services not active",Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}