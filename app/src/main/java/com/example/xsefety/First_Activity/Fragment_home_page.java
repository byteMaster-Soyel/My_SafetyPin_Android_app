package com.example.xsefety.First_Activity;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.xsefety.R;


public class Fragment_home_page extends Fragment{

    View view;
    CardView cardView1_safe_women;
    CardView cardView2_online_complain;
    CardView cardView3_ambulance;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_page, container, false);





//   Safe women clickable button backend code
        cardView1_safe_women =(CardView) view.findViewById(R.id.cardView1_safe_women);
        cardView1_safe_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment open_sign_up = new Fragment_home_btn_safe_women();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, open_sign_up,null).addToBackStack(null).commit() ;

            }
        });

//    online complaint clickable button backend code
        cardView2_online_complain=(CardView) view.findViewById(R.id.cardView2_online_complain);
        cardView2_online_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment open_sign_up = new Fragment_online_complaint_system();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, open_sign_up,null).addToBackStack(null).commit() ;

            }
        });


//    ambulance booking  clickable button backend code
        cardView3_ambulance =(CardView) view.findViewById(R.id.cardView3_ambulance);
        cardView3_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment open_sign_up = new Fragment_booking_ambulance_home_page();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, open_sign_up,null).addToBackStack(null).commit() ;

            }
        });

        return view;
    }
}