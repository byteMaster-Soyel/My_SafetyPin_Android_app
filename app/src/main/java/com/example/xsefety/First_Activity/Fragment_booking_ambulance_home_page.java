package com.example.xsefety.First_Activity;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xsefety.R;


public class Fragment_booking_ambulance_home_page extends Fragment {

    View view;
    CardView cardView1_emergency_booking_btn,cardView2_ambulance_booking_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_booking_ambulance_home_page, container, false);

        //open online complaint from backend code
        cardView1_emergency_booking_btn = (CardView) view.findViewById(R.id.cardView1_emergency_booking_btn);
        cardView1_emergency_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment open_ambulance_booking_form = new Fragment_emergency_ambulance_booking();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout, open_ambulance_booking_form,null).addToBackStack(null).commit();
            }
        });

        //open user guide pdf backend code
        cardView2_ambulance_booking_btn = (CardView) view.findViewById(R.id.cardView2_ambulance_booking_btn);
        cardView2_ambulance_booking_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment open_ambulance_booking = new Fragment_ambulance_booking();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.id_home_page_framlayout,open_ambulance_booking,null).addToBackStack(null).commit();

            }
        });

        return view;
    }
}