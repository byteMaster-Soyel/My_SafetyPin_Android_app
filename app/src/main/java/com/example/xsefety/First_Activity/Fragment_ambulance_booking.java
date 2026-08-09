package com.example.xsefety.First_Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xsefety.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Fragment_ambulance_booking extends Fragment implements TextToSpeech.OnInitListener{


    TextView set_date_text,set_time_text;
    androidx.appcompat.widget.AppCompatButton ambu_get_time_btn,ambu_get_date_btn;

    TextToSpeech tts;

    Button ambu_booking_submit_btn;

    com.google.android.material.textfield.TextInputEditText ambu_patient_name_et,ambu_phone_et,
            ambu_pin_et,ambu_email_et,ambu_district_et,ambu_village_town_et,ambu_flat_house_et,ambu_police_et,ambu_booking_time_et,ambu_booking_date_et;

    String ambu_patient_name,ambu_phone,ambu_pin,ambu_district,ambu_village_town,ambu_flat_house;

    boolean ambu_patient_name_boolean=false,ambu_phone_boolean=false,ambu_pin_boolean=false,ambu_district_boolean=false,ambu_village_town_boolean=false,ambu_flat_house_boolean=false,ambu_date_boolean=false,ambu_time_boolean=false;
    CheckBox checkbox;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_ambulance_booking, container, false);

        set_date_text=(TextView) view.findViewById(R.id.set_date_text_id);
        set_time_text=(TextView) view.findViewById(R.id.set_time_text_id);

        tts=new TextToSpeech(view.getContext(),this);

        ambu_email_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_email_et);

        ambu_police_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_police_station_et);

        //Check district validation
        ambu_district_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_district_et);
        ambu_district_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ambu_district=ambu_district_et.getText().toString();
                if (!(ambu_district.isEmpty()))
                {
                    if (ambu_district.length() >= 2 && isAlphaValid(ambu_district))
                    {
                        if (isNum_name_check(ambu_district))
                        {
                            if(isSpecial(ambu_district))
                            {
                                ambu_district_boolean= true;
                            }
                            else
                            {
                                ambu_district_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            ambu_district_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(ambu_district))
                        {
                            ambu_district_et.setError("Please enter your full name");
                        }
                        else {
                            ambu_district_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    ambu_district_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });







        //Ambu village town validation
        ambu_village_town_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_village_town_et);
        ambu_village_town_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ambu_village_town=ambu_village_town_et.getText().toString();
                if (!(ambu_village_town.isEmpty()))
                {
                    if (ambu_village_town.length() >= 2 && isAlphaValid(ambu_village_town))
                    {
                        if (isNum_name_check(ambu_village_town))
                        {
                            if(isSpecial(ambu_village_town))
                            {
                                ambu_village_town_boolean= true;
                            }
                            else
                            {
                                ambu_village_town_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            ambu_village_town_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(ambu_village_town))
                        {
                            ambu_village_town_et.setError("Please enter your full name");
                        }
                        else {
                            ambu_village_town_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    ambu_village_town_et.setError("Empty!");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







        //name validation check
        ambu_patient_name_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_name_et);
        ambu_patient_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ambu_patient_name=ambu_patient_name_et.getText().toString();
                if (!(ambu_patient_name.isEmpty()))
                {
                    if (ambu_patient_name.length() >= 2 && isAlphaValid(ambu_patient_name))
                    {
                        if (isNum_name_check(ambu_patient_name))
                        {
                            if(isSpecial(ambu_patient_name))
                            {
                                ambu_patient_name_boolean = true;
                            }
                            else
                            {
                                ambu_patient_name_et.setError("Special character not allowed.");
                            }
                        }
                        else
                        {
                            ambu_patient_name_et.setError("digit is not valid");
                        }
                    }
                    else{

                        if(isAlphaValid(ambu_patient_name))
                        {
                            ambu_patient_name_et.setError("Please enter your full name");
                        }
                        else {
                            ambu_patient_name_et.setError("digit is not valid");
                        }
                    }
                }
                else
                {
                    ambu_patient_name_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });






        // check phone number
        ambu_phone_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_phone_et);
        ambu_phone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ambu_phone=ambu_phone_et.getText().toString();

                if(ambu_phone.startsWith("+91"))
                {
                    if (validateMobile(ambu_phone.substring(3))) {
                        ambu_phone_boolean = true;
                    } else {

                        ambu_phone_et.setError("Invalid mobile number");
                    }
                }
                else
                {
                    if (validateMobile(ambu_phone)) {
                        ambu_phone_boolean = true;
                        ambu_phone="+91"+ambu_phone;
                    } else {

                        ambu_phone_et.setError("Invalid mobile number");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        // check pin number validation
        ambu_pin_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_pin_code_et);
        ambu_pin_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ambu_pin=ambu_pin_et.getText().toString();

                if(isNum_pin(ambu_pin) && ambu_pin.length()==6)
                {
                    ambu_pin_boolean=true;
                }
                else
                {
                    ambu_pin_et.setError(" ");
                    ambu_pin_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //check house_flat_validatiion
        ambu_flat_house_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.ambu_house_flat_et);
        ambu_flat_house_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ambu_flat_house_et.getText().toString().isEmpty())
                {
                    ambu_flat_house_boolean=false;
                }
                else {
                    ambu_flat_house_boolean=true;
                    ambu_flat_house=ambu_flat_house_et.getText().toString();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ambu_get_date_btn=(androidx.appcompat.widget.AppCompatButton) view.findViewById(R.id.ambu_get_date_btn);
        ambu_get_date_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datepickerdialog=new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        set_date_text.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        ambu_date_boolean=true;
                    }
                },year,month,day);
                datepickerdialog.show();

            }
        });
        ambu_get_time_btn=(androidx.appcompat.widget.AppCompatButton) view.findViewById(R.id.ambu_get_time_btn);
        ambu_get_time_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                int hour=c.get(Calendar.HOUR);
                int minute=c.get(Calendar.MINUTE);
                int second=c.get(Calendar.SECOND);
                TimePickerDialog timepickerdialog=new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        set_time_text.setText(hourOfDay+" : "+minute);
                        ambu_time_boolean=true;

                    }
                },hour,minute,false);
                timepickerdialog.show();

            }
        });



        checkbox=(CheckBox) view.findViewById(R.id.ambu_check_box);

        ambu_booking_submit_btn=(Button) view.findViewById(R.id.ambu_booking_submit_btn);
        ambu_booking_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ambu_patient_name_boolean)
                {
                    if(ambu_phone_boolean)
                    {
                        if(validateEmail(ambu_email_et.getText().toString()))
                        {
                            if(ambu_flat_house_boolean)
                            {
                                if(ambu_village_town_boolean)
                                {
                                    if(ambu_district_boolean)
                                    {
                                        if(ambu_pin_boolean)
                                        {
                                            if(ambu_date_boolean) {
                                                if(ambu_time_boolean) {
                                                    if (checkbox.isChecked()) {
                                                        startActivity(new Intent(view.getContext(), MapsActivity.class));
                                                        try {
                                                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    //Fetching location
                                                                    location_calculator location_calculator = new location_calculator();
                                                                    double lat = location_calculator.getLat();
                                                                    double lng = location_calculator.getLng();

                                                                    String ambulance_help = "+918101624709";
                                                                    SmsManager smsManager = SmsManager.getDefault();
                                                                    smsManager.sendTextMessage(ambulance_help, null, "I have been need ambulance help - name : " + ambu_patient_name + " Ph : " + ambu_phone, null, null);
                                                                    smsManager.sendTextMessage(ambulance_help, null, "Email-" + ambu_email_et.getText().toString() + " Vill,town-" + ambu_village_town + " PS-" + ambu_police_et.getText().toString() + " Dist-" + ambu_district + " Pin-" + ambu_pin+" Date-"+set_date_text.getText().toString()+" Time(24 F)-"+set_time_text.getText().toString(), null, null);
                                                                    smsManager.sendTextMessage(ambulance_help, null, "My location is :" + "http://maps.google.com/maps?z=12&t=m&q=loc:" + lat + "," + lng, null, null);
                                                                    speakOut("Ambulance booking procedure is start. We call you some time later for confirmation and get more details about patient and location");
                                                                    Toast.makeText(view.getContext(), "Wait for receiving", Toast.LENGTH_SHORT).show();

                                                                }
                                                            }, 5000);
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        Toast.makeText(view.getContext(), "Accept terms and condition", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                                else
                                                {
                                                    set_date_text.setError("Required time!");
                                                }
                                            }
                                            else
                                            {
                                                set_date_text.setError("Required date!");
                                            }

                                        }
                                        else
                                        {
                                            ambu_pin_et.setError("");
                                            ambu_pin_et.requestFocus();
                                        }

                                    }
                                    else
                                    {
                                        ambu_district_et.setError("");
                                        ambu_district_et.requestFocus();
                                    }

                                }
                                else {
                                    ambu_village_town_et.setError("");
                                    ambu_village_town_et.requestFocus();
                                }
                            }
                            else
                            {
                                ambu_flat_house_et.setError("");
                                ambu_flat_house_et.requestFocus();
                            }

                        }
                        else {
                            ambu_email_et.setError("");
                            ambu_email_et.requestFocus();
                        }
                    }
                    else
                    {
                        ambu_phone_et.setError("");
                        ambu_phone_et.requestFocus();
                    }
                }
                else
                {
                    ambu_patient_name_et.setError("");
                    ambu_patient_name_et.requestFocus();
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

    // Email validation code
    boolean validateEmail(String email)
    {
        if(email.isEmpty())
        {
            ambu_email_et.setError("Email is required. Can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            ambu_email_et.setError("Email is invalid. Enter valid email address");
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public void onDestroy(){
        //don't forget to shutdown tts!
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if(status== TextToSpeech.SUCCESS)
        {
            int result=tts.setLanguage(Locale.US);
            if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTs", "This Language is not supported");
            }
            else {
                // speak_btn.setEnabled(true);
                speakOut("");
            }
        }
        else {
            Log.e("TTS","Initilization Failed!");
        }
    }

    private void speakOut(String string) {

        tts.speak(string, TextToSpeech.QUEUE_FLUSH,null);
    }


}
