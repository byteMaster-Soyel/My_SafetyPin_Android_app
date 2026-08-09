package com.example.xsefety.First_Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xsefety.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment_online_complaint_system extends Fragment {

    View view;

    //User edit text ids
    com.google.android.material.textfield.TextInputEditText id_complainant_name_et, id_complainant_father_name_et, id_complainant_mobile_et, id_complainant_email_et, id_complainant_house_et, id_complainant_villege_et, id_complainant_police_et, id_complainant_district_et, id_complainant_pin_code_et;
    String complainant_name ,complainant_father_name, complainant_mobile ,complainant_house,complainant_village,complainant_police,complainant_district,complainant_pin  ;
    boolean complainant_name_b=false,complainant_father_b=false,complainant_mobile_b=false,complainant_house_b=false,complainant_village_b=false,complainant_police_b=false,complainant_district_b=false,complainant_pin_b=false;
    //Victime edit text ids
    com.google.android.material.textfield.TextInputEditText id_complainant_victim_name_et,id_complainant_victim_number_et,id_complainant_victim_house_flat_et,id_complainant_victim_village_town_et,id_complainant_victim_police_et,id_complainant_victim_dist_et,id_complainant_victim_pin_et;
    String victim_name,victim_mobile,victim_house,victim_village,victim_police,victim_district,victim_pin;
    boolean victim_name_b=false,victim_house_b=false,victim_village_b=false,victim_police_b=false,victim_district_b=false,victim_pin_b=false;

    //incident details edit text ids
    com.google.android.material.textfield.TextInputEditText id_complainant_incident_place_et,id_complainant_incident_police_et,id_complainant_incident_district_et,id_complainant_incident_state_et,id_complainant_incident_type_et;
    String incident_place,incident_police,incident_district,incident_state,incident_type;
    boolean incident_place_b=false,incident_police_b=false,incident_district_b=false,incident_state_b=false,incident_type_b=false;

    //Submit button
    Button id_complainant_submit_button;

    // Radio button
    RadioGroup id_complainant_radio_et,id_complainant_victim_radio_et;


    androidx.appcompat.widget.AppCompatButton complain_get_date_btn,complain_get_time_btn;
    TextView com_date_text_id,com_time_text_id;
    boolean com_date_b=false,com_time_b=false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_online_complaint_system, container, false);

        //name
        id_complainant_name_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_name_et);
        id_complainant_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_name=id_complainant_name_et.getText().toString();
                if (!(complainant_name.isEmpty()))
                {
                    if (complainant_name.length() >= 2 && isAlphaValid(complainant_name))
                    {
                        if (isNum_name_check(complainant_name))
                        {
                            if(isSpecial(complainant_name))
                            {
                                complainant_name_b=true;
                            }
                            else
                            {
                                id_complainant_name_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_name_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(complainant_name))
                        {
                            id_complainant_name_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_name_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_name_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_father_name_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_father_name_et);
        id_complainant_father_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_father_name=id_complainant_father_name_et.getText().toString();
                if (!(complainant_name.isEmpty()))
                {
                    if (complainant_father_name.length() >= 2 && isAlphaValid(complainant_father_name))
                    {
                        if (isNum_name_check(complainant_father_name))
                        {
                            if(isSpecial(complainant_father_name))
                            {
                                complainant_father_b=true;
                            }
                            else
                            {
                                id_complainant_father_name_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_father_name_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(complainant_father_name))
                        {
                            id_complainant_father_name_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_father_name_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_father_name_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });
        id_complainant_mobile_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_mobile_et);
        id_complainant_mobile_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                complainant_mobile=id_complainant_mobile_et.getText().toString();

                if(complainant_mobile.startsWith("+91"))
                {
                    if (validateMobile(complainant_mobile.substring(3))) {
                        complainant_mobile_b=true;

                    } else {

                        id_complainant_mobile_et.setError("Invalid mobile number");
                    }
                }
                else
                {
                    if (validateMobile(complainant_mobile)) {

                        complainant_mobile="+91"+complainant_mobile;
                        complainant_mobile_b=true;
                    } else {

                        id_complainant_mobile_et.setError("Invalid mobile number");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        id_complainant_email_et = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_email_et);


        id_complainant_house_et = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_house_no_et);
        id_complainant_house_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_house=id_complainant_house_et.getText().toString();
                if (!(complainant_house.isEmpty()))
                {
                    complainant_house_b=true;
                }
                else
                {
                    id_complainant_house_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_villege_et = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_villege_et);
        id_complainant_villege_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_village=id_complainant_villege_et.getText().toString();
                if (!(complainant_village.isEmpty()))
                {
                    if (complainant_village.length() >= 2 && isAlphaValid(complainant_village))
                    {
                        if (isNum_name_check(complainant_village))
                        {
                            if(isSpecial(complainant_village))
                            {
                                complainant_village_b=true;
                            }
                            else
                            {
                                id_complainant_villege_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_villege_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(complainant_village))
                        {
                            id_complainant_villege_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_villege_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_villege_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_police_et = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_police_et);
        id_complainant_police_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_police=id_complainant_police_et.getText().toString();
                if (!(complainant_police.isEmpty()))
                {
                    if (complainant_police.length() >= 2 && isAlphaValid(complainant_police))
                    {
                        if (isNum_name_check(complainant_police))
                        {
                            if(isSpecial(complainant_police))
                            {
                                complainant_police_b=true;
                            }
                            else
                            {
                                id_complainant_police_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_police_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(complainant_police))
                        {
                            id_complainant_police_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_police_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_police_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_district_et = (com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_district_et);
        id_complainant_district_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                complainant_district=id_complainant_district_et.getText().toString();
                if (!(complainant_district.isEmpty()))
                {
                    if (complainant_district.length() >= 2 && isAlphaValid(complainant_district))
                    {
                        if (isNum_name_check(complainant_district))
                        {
                            if(isSpecial(complainant_district))
                            {
                                complainant_district_b=true;
                            }
                            else
                            {
                                id_complainant_district_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_district_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(complainant_district))
                        {
                            id_complainant_district_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_district_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_district_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_pin_code_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_pin_code_et);
        id_complainant_pin_code_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                complainant_pin=id_complainant_pin_code_et.getText().toString();

                if(isNum_pin(complainant_pin) && complainant_pin.length()==6)
                {
                    complainant_pin_b=true;
                }
                else
                {
                    id_complainant_pin_code_et.setError(" ");
                    id_complainant_pin_code_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //Victim

        id_complainant_victim_name_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_name_et);
        id_complainant_victim_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                victim_name=id_complainant_victim_name_et.getText().toString();
                if (!(victim_name.isEmpty()))
                {
                    if (victim_name.length() >= 2 && isAlphaValid(victim_name))
                    {
                        if (isNum_name_check(victim_name))
                        {
                            if(isSpecial(victim_name))
                            {
                                victim_name_b = true;
                            }
                            else
                            {
                                id_complainant_victim_name_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_victim_name_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(victim_name))
                        {
                            id_complainant_victim_name_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_victim_name_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_victim_name_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_victim_number_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_number_et);
        id_complainant_victim_number_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                victim_mobile=id_complainant_victim_number_et.getText().toString();

                if(victim_mobile.startsWith("+91"))
                {
                    if (validateMobile(victim_mobile.substring(3))) {

                    } else {

                        id_complainant_victim_number_et.setError("Invalid mobile number");
                    }
                }
                else
                {
                    if (validateMobile(victim_mobile)) {

                        victim_mobile="+91"+victim_mobile;
                    } else {

                        id_complainant_victim_number_et.setError("Invalid mobile number");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        id_complainant_victim_house_flat_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_House_flat_et);
        id_complainant_victim_house_flat_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                victim_house=id_complainant_victim_house_flat_et.getText().toString();
                if (!(victim_house.isEmpty()))
                {
                    victim_house_b=true;
                }
                else
                {
                    id_complainant_victim_house_flat_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_victim_village_town_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_village_town_city_et);
        id_complainant_victim_village_town_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                victim_village=id_complainant_victim_village_town_et.getText().toString();
                if (!(victim_village.isEmpty()))
                {
                    if (victim_village.length() >= 2 && isAlphaValid(victim_village))
                    {
                        if (isNum_name_check(victim_village))
                        {
                            if(isSpecial(victim_village))
                            {
                                victim_village_b=true;
                            }
                            else
                            {
                                id_complainant_victim_village_town_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_victim_village_town_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(victim_village))
                        {
                            id_complainant_victim_village_town_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_victim_village_town_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_victim_village_town_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_victim_police_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_police_station_et);
        id_complainant_victim_police_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                victim_police=id_complainant_victim_police_et.getText().toString();
                if (!(victim_police.isEmpty()))
                {
                    if (victim_police.length() >= 2 && isAlphaValid(victim_police))
                    {
                        if (isNum_name_check(victim_police))
                        {
                            if(isSpecial(victim_police))
                            {
                                victim_police_b=true;
                            }
                            else
                            {
                                id_complainant_victim_police_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_victim_police_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(victim_police))
                        {
                            id_complainant_victim_police_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_victim_police_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_victim_police_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_victim_dist_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_dist_et);
        id_complainant_victim_dist_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                victim_district=id_complainant_victim_dist_et.getText().toString();
                if (!(victim_district.isEmpty()))
                {
                    if (victim_district.length() >= 2 && isAlphaValid(victim_district))
                    {
                        if (isNum_name_check(victim_district))
                        {
                            if(isSpecial(victim_district))
                            {
                                victim_district_b=true;
                            }
                            else
                            {
                                id_complainant_victim_dist_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_victim_dist_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(victim_district))
                        {
                            id_complainant_victim_dist_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_victim_dist_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_victim_dist_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_victim_pin_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_victim_pin_et);
        id_complainant_victim_pin_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                victim_pin=id_complainant_victim_pin_et.getText().toString();

                if(isNum_pin(victim_pin) && victim_pin.length()==6)
                {
                    victim_pin_b=true;
                }
                else
                {
                    id_complainant_victim_pin_et.setError(" ");
                    id_complainant_victim_pin_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // incident
        id_complainant_incident_place_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_incident_place_et);
        id_complainant_incident_place_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                incident_place=id_complainant_incident_place_et.getText().toString();
                if (!(incident_place.isEmpty()))
                {
                    if (incident_place.length() >= 2 && isAlphaValid(incident_place))
                    {
                        if (isNum_name_check(incident_place))
                        {
                            if(isSpecial(incident_place))
                            {
                                incident_place_b = true;
                            }
                            else
                            {
                                id_complainant_incident_place_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_incident_place_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(incident_place))
                        {
                            id_complainant_incident_place_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_incident_place_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_incident_place_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_incident_state_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_incident_state_et);
        id_complainant_incident_state_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                incident_state=id_complainant_incident_state_et.getText().toString();
                if (!(incident_state.isEmpty()))
                {
                    if (incident_state.length() >= 2 && isAlphaValid(incident_state))
                    {
                        if (isNum_name_check(incident_state))
                        {
                            if(isSpecial(incident_state))
                            {
                                incident_state_b = true;
                            }
                            else
                            {
                                id_complainant_incident_state_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_incident_state_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(incident_state))
                        {
                            id_complainant_incident_state_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_incident_state_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_incident_state_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_incident_police_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_incident_police_et);
        id_complainant_incident_police_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                incident_police=id_complainant_incident_police_et.getText().toString();
                if (!(incident_police.isEmpty()))
                {
                    if (incident_police.length() >= 2 && isAlphaValid(incident_police))
                    {
                        if (isNum_name_check(incident_police))
                        {
                            if(isSpecial(incident_police))
                            {
                                incident_police_b = true;
                            }
                            else
                            {
                                id_complainant_incident_police_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_incident_police_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(incident_police))
                        {
                            id_complainant_incident_police_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_incident_police_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_incident_police_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_incident_type_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_incident_type_et);
        id_complainant_incident_type_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                incident_type=id_complainant_incident_type_et.getText().toString();
                if (!(incident_type.isEmpty()))
                {
                    incident_type_b=true;
                }
                else
                {
                    id_complainant_incident_type_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        id_complainant_incident_district_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.id_complainant_incident_district_et);
        id_complainant_incident_district_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                incident_district=id_complainant_incident_district_et.getText().toString();
                if (!(incident_district.isEmpty()))
                {
                    if (incident_district.length() >= 2 && isAlphaValid(incident_district))
                    {
                        if (isNum_name_check(incident_district))
                        {
                            if(isSpecial(incident_district))
                            {
                                incident_district_b = true;
                            }
                            else
                            {
                                id_complainant_incident_district_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            id_complainant_incident_district_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(incident_district))
                        {
                            id_complainant_incident_district_et.setError("Please enter your full name");
                        }
                        else {
                            id_complainant_incident_district_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    id_complainant_incident_district_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        com_date_text_id=(TextView) view.findViewById(R.id.com_date_text_id);
        complain_get_date_btn=(androidx.appcompat.widget.AppCompatButton) view.findViewById(R.id.complain_get_date_btn);
        complain_get_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datepickerdialog=new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        com_date_text_id.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        com_date_b=true;
                    }
                },year,month,day);
                datepickerdialog.show();

            }
        });
        com_time_text_id=(TextView) view.findViewById(R.id.com_time_text_id);
        complain_get_time_btn=(androidx.appcompat.widget.AppCompatButton) view.findViewById(R.id.complain_get_time_btn);
        complain_get_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int hour=c.get(Calendar.HOUR);
                int minute=c.get(Calendar.MINUTE);
                int second=c.get(Calendar.SECOND);
                TimePickerDialog timepickerdialog=new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        com_time_text_id.setText(hourOfDay+" : "+minute+" : "+second);
                        com_time_b=true;

                    }
                },hour,minute,false);
                timepickerdialog.show();

            }
        });




        //Radio group / gender

        id_complainant_victim_radio_et=(RadioGroup) view.findViewById(R.id.id_complainant_victim_radio_et);
        id_complainant_radio_et=(RadioGroup) view.findViewById(R.id.id_complainant_radio_et);


        // Submit button backed code
        id_complainant_submit_button=(Button) view.findViewById(R.id.id_complainant_submit_button);
        id_complainant_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(complainant_name_b)
                {
                    if(complainant_father_b)
                    {
                        if(complainant_mobile_b)
                        {
                            if(validateEmail(id_complainant_email_et.getText().toString()))
                            {
                                int selectedId=id_complainant_radio_et.getCheckedRadioButtonId();
                                if(selectedId>0)
                                {
                                    if(complainant_house_b)
                                    {
                                        if(complainant_village_b)
                                        {
                                            if(complainant_police_b)
                                            {
                                                if(complainant_district_b)
                                                {
                                                    if(complainant_pin_b)
                                                    {


                                                        //Victim constraint
                                                        if(victim_name_b)
                                                        {
                                                            int selectedId2=id_complainant_victim_radio_et.getCheckedRadioButtonId();
                                                            if(selectedId2>0)
                                                            {
                                                                if (victim_house_b)
                                                                {
                                                                    if (victim_village_b)
                                                                    {
                                                                        if (victim_police_b)
                                                                        {
                                                                            if (victim_district_b)
                                                                            {
                                                                                if (victim_pin_b)
                                                                                {


                                                                                    //Incident
                                                                                    if (incident_place_b)
                                                                                    {
                                                                                        if (incident_police_b)
                                                                                        {
                                                                                            if (incident_district_b)
                                                                                            {
                                                                                                if (incident_state_b)
                                                                                                {
                                                                                                    if(incident_type_b)
                                                                                                    {
                                                                                                        if(com_date_b)
                                                                                                        {
                                                                                                            if(com_time_b)
                                                                                                            {
                                                                                                                Calendar c=Calendar.getInstance();
                                                                                                                int year=c.get(Calendar.YEAR);
                                                                                                                int month=c.get(Calendar.MONTH);
                                                                                                                int day=c.get(Calendar.DAY_OF_MONTH);
                                                                                                                String body = complainant_name+"\n"+complainant_father_name+"\n"+complainant_house+","+complainant_village+","+complainant_police+","+complainant_district+","+complainant_pin+"\n" +
                                                                                                                       id_complainant_email_et.getText().toString()+"\n"+
                                                                                                                        complainant_mobile+"\n\n"+
                                                                                                                        day+"/"+month+"/"+year+"\n\n"
                                                                                                                        +"To,\n"+
                                                                                                                        "The Station house officer,"+"\n"+
                                                                                                                        incident_police+","+incident_district+","+incident_state+"\n\n"+
                                                                                                                        "Subject : Complaint Regarding "+incident_type+"\n\n"+
                                                                                                                        "Respected Sir/Madan,"+"\n\n"+
                                                                                                                        "I am writing to formally lodge a complaint regarding "+incident_type+". The incident occurred on "+com_date_text_id.getText().toString()+" at approximately "+com_time_text_id.getText().toString()+" in the vicinity of "+incident_place+","+incident_police+","+incident_district+","+incident_state+"."+"\n\n"+
                                                                                                                        "I would like to request your assistance in investigating this matter and taking appropriate action against the perpetrator(s). I am willing to cooperate fully with any investigation.\n\n " +
                                                                                                                        "Thank you for your attention to this matter.\n\n Sincerely,\n"+complainant_name;
                                                                                                                        ;



                                                                                                                //Toast.makeText(view.getContext(), "Hi", Toast.LENGTH_SHORT).show();
                                                                                                                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                                                                                                                String subject = incident_type;
                                                                                                                String cc = "suvajitdas325@gmail.com";
                                                                                                                String to = "alexsoyel38@gmail.com";
                                                                                                                String bcc = "alexsoyel782@gmail.com";
                                                                                                                Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + body + "&to=" + to + "&cc=" + cc + "&bcc=" + bcc);
                                                                                                                mailIntent.setData(data);
                                                                                                                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
                                                                                                            }
                                                                                                            else
                                                                                                            {
                                                                                                                com_time_text_id.setError("");
                                                                                                                com_time_text_id.requestFocus();
                                                                                                            }
                                                                                                        }
                                                                                                        else
                                                                                                        {
                                                                                                            com_date_text_id.setError("");
                                                                                                            com_date_text_id.requestFocus();
                                                                                                        }
                                                                                                    }
                                                                                                    else
                                                                                                    {
                                                                                                        id_complainant_incident_type_et.setError("");
                                                                                                        id_complainant_incident_type_et.requestFocus();
                                                                                                    }
                                                                                                }
                                                                                                else
                                                                                                {
                                                                                                    id_complainant_incident_state_et.setError("");
                                                                                                    id_complainant_incident_state_et.requestFocus();
                                                                                                }
                                                                                            }
                                                                                            else
                                                                                            {
                                                                                                id_complainant_incident_district_et.setError("");
                                                                                                id_complainant_incident_district_et.requestFocus();
                                                                                            }
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            id_complainant_incident_police_et.setError("");
                                                                                            id_complainant_incident_police_et.requestFocus();
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        id_complainant_incident_place_et.setError("");
                                                                                        id_complainant_incident_place_et.requestFocus();
                                                                                    }
                                                                                }
                                                                                else
                                                                                {
                                                                                    id_complainant_victim_pin_et.setError("");
                                                                                    id_complainant_victim_pin_et.requestFocus();
                                                                                }
                                                                            }
                                                                            else
                                                                            {
                                                                                    id_complainant_victim_dist_et.setError("");
                                                                                    id_complainant_victim_dist_et.requestFocus();
                                                                            }
                                                                        }
                                                                        else
                                                                        {
                                                                            id_complainant_victim_police_et.setError("");
                                                                            id_complainant_victim_police_et.requestFocus();
                                                                        }
                                                                    }
                                                                    else
                                                                    {
                                                                        id_complainant_victim_village_town_et.setError("");
                                                                        id_complainant_victim_village_town_et.requestFocus();
                                                                    }
                                                                }
                                                                else
                                                                {
                                                                    id_complainant_victim_house_flat_et.setError("");
                                                                    id_complainant_victim_house_flat_et.requestFocus();
                                                                }
                                                            }
                                                            else
                                                            {
                                                                Toast.makeText(view.getContext(), "Victim Gender is not entered", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                        else
                                                        {
                                                            id_complainant_victim_name_et.setError("");
                                                            id_complainant_victim_name_et.requestFocus();
                                                        }
                                                    }
                                                    else
                                                    {
                                                        id_complainant_pin_code_et.setError("");
                                                        id_complainant_pin_code_et.requestFocus();
                                                    }
                                                }
                                                else
                                                {
                                                    id_complainant_district_et.setError("");
                                                    id_complainant_district_et.requestFocus();
                                                }
                                            }
                                            else
                                            {
                                                id_complainant_police_et.setError("");
                                                id_complainant_police_et.requestFocus();
                                            }
                                        }
                                        else
                                        {
                                            id_complainant_victim_village_town_et.setError("");
                                            id_complainant_victim_village_town_et.requestFocus();
                                        }
                                    }
                                    else
                                    {
                                        id_complainant_house_et.setError("");
                                        id_complainant_house_et.requestFocus();
                                    }
                                }
                                else
                                {

                                    Toast.makeText(view.getContext(), "User gender is not entered", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                id_complainant_email_et.setError("");
                                id_complainant_email_et.requestFocus();
                            }
                        }
                        else
                        {
                            id_complainant_mobile_et.setError("");
                            id_complainant_mobile_et.requestFocus();
                        }
                    }
                    else
                    {
                        id_complainant_father_name_et.setError("");
                        id_complainant_father_name_et.requestFocus();
                    }
                }
                else
                {
                    id_complainant_name_et.setError("");
                    id_complainant_name_et.requestFocus();
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

        // check password validation function
        boolean validatePassword(String password) {
            Pattern p = Pattern.compile("^"
                    + "(?=.*[0-9])"     //minimum one number
                    + "(?=.*[a-z])"     //minimum one lower case character
                    + "(?=.*[A-Z])"     //minimum one UPPER case character
                    + "(?=.*[a-zA-Z])"      //any character
                    + "(?=.*[@#%$^&+=])"    //minimum one special character
                    + "(?=\\S+$)"           // no white spaces
                    + ".{8,}"               // on white spaces
                    + "$");             //minimum length 6 characters
            Matcher m = p.matcher(password);
            return m.matches();
        }
    //
        //check name contain digit or not
        boolean isNum_name_check(String name)
        {
            Pattern p = Pattern.compile("^"
                    + "(?=.*[0-9])"
                    + ".{2,}"               // on white spaces
                    + "$");             //minimum length  characters
            Matcher m = p.matcher(name);
            return !(m.matches());
        }

    //
        //check pin number contain only number
        boolean isNum_pin(String name)
        {
            Pattern p = Pattern.compile("^"
                    + "(?=.*[0-9])"
                    + ".{7,}"               // on white spaces
                    + "$");             //minimum length 6 characters
            Matcher m = p.matcher(name);
            return !(m.matches());
        }

    //
        // check name contain special character or not
        boolean isSpecial(String name)
        {
            Pattern p = Pattern.compile("^"
                    + "(?=.*[@#%$^&+=/?()*!_~`,.:;'\"|])"
                    + ".{2,}"               // on white spaces
                    + "$");             //minimum length 6 characters
            Matcher m = p.matcher(name);
            return !(m.matches());
        }

    //
        // check name contain only alphabetic character or not
        boolean isAlphaValid(String name)
        {
            Pattern p = Pattern.compile("^"
                    + "(?=.*[a-z])"     //minimum one lower case character
                    //       + "(?=.*[A-Z])"     //minimum one UPPER case character
                    + "(?=.*[a-zA-Z])"      //any character
                    + ".{3,}"               // on white spaces
                    + "$");             //minimum length 6 characters
            Matcher m = p.matcher(name);
            return m.matches();
        }


    //
        // Email validation code
        boolean validateEmail(String email)
        {
            if(email.isEmpty())
            {
            //    id_complainant_email_et.setError("Email is required. Can't be empty.");
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            //    id_complainant_email_et.setError("Email is invalid. Enter valid email address");
                return false;
            }
            else
            {
                return true;
            }
        }
}