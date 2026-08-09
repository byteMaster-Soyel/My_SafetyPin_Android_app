package com.example.xsefety.First_Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xsefety.R;
import java.util.ArrayList;


public class Fragment_Account_Details extends Fragment {


    View view;
    RelativeLayout id_account_details_btn ,id_app_version_btn ,id_logout_btn ,id_update_your_app_btn , id_support_btn, id_about_us_btn;

    TextView  profile_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment__account__details, container, false);


        //required for profile name (showing name in account)
        profile_name=(TextView)view.findViewById(R.id.account_text_name);
        MyDbHelper myDbHelper=new MyDbHelper(view.getContext());
        sessionManager s=new sessionManager(view.getContext());
        ArrayList<UserModel> al=new ArrayList<>();

        al.addAll(myDbHelper.getUserAllData(s.getSessionDetails("key_session_phone")));
        if(al!=null) {
            profile_name.setText(al.get(0).getName());
        }


        //profile details clickable button backend code
        id_account_details_btn = (RelativeLayout) view.findViewById(R.id.id_account_details_btn);
        id_account_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment account_details_btn = new Fragment_profile_account_details();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, account_details_btn,null).addToBackStack(null).commit();

            }
        });

        //about us clickable button backend code
        id_about_us_btn =(RelativeLayout) view.findViewById(R.id.id_about_us_btn);
        id_about_us_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(view.getContext(),"Welcome to My SafetyPin – your trusted partner in safety and emergency response.\n" +
                        "\n" +
                        "Our mission is to provide a secure and responsive platform dedicated to protecting lives and empowering communities. Whether you're in danger, need to file a complaint, or require urgent medical help, we're here to support you—every step of the way.",Toast.LENGTH_SHORT).show();
            }
        });

        //app update clickable button backend code
        id_update_your_app_btn =(RelativeLayout) view.findViewById(R.id.id_update_your_app_btn);
        id_update_your_app_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Toast.makeText(view.getContext(), "Enter valid Phone Number & Passwrod", Toast.LENGTH_SHORT).show();

                Toast.makeText(view.getContext(),"Your App Is Up to Date",Toast.LENGTH_SHORT).show();
            }
        });

        //app Support clickable button backend code
        id_support_btn =(RelativeLayout) view.findViewById(R.id.id_support_btn);
        id_support_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     Toast.makeText(view.getContext(), "Enter valid Phone Number & Passwrod", Toast.LENGTH_SHORT).show();

                Toast.makeText(view.getContext(),"Currently Support Not Available",Toast.LENGTH_SHORT).show();
            }
        });

        //app version clickable button backend code
        id_app_version_btn =(RelativeLayout) view.findViewById(R.id.id_app_version_btn);
        id_app_version_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(view.getContext(),"You are currently on version 1.0",Toast.LENGTH_SHORT).show();
            }
        });

        //logout clickable button backend code
        id_logout_btn =(RelativeLayout) view.findViewById(R.id.id_logout_btn);
        id_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(view.getContext(), first_opening_animation.class));
                getActivity().finish();
                sessionManager s=new sessionManager(view.getContext());
                s.logoutSession();
            }
        });



        return view;
    }

}