package com.example.xsefety.First_Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.xsefety.R;

import java.util.ArrayList;
import java.util.Locale;


public class Fragment_emergency_ambulance_booking extends Fragment implements TextToSpeech.OnInitListener{

    ImageButton emergency_ambu_booking_btn;
    View view;
    TextToSpeech tts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_emergency_ambulance_booking, container, false);
        tts=new TextToSpeech(view.getContext(),  this);

        emergency_ambu_booking_btn=(ImageButton) view.findViewById(R.id.emergency_ambu_booking_btn);
        emergency_ambu_booking_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(view.getContext(), MapsActivity.class));
                try {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable()
                    {
                        @Override
                        public void run() {
                            //Fetching location
                            location_calculator location_calculator = new location_calculator();
                            double lat = com.example.xsefety.First_Activity.location_calculator.getLat();
                            double lng = com.example.xsefety.First_Activity.location_calculator.getLng();

                            //Shared preference object
                            sessionManager s = new sessionManager(view.getContext());

                            // sqlite object
                            MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
                            ArrayList<UserModel> al = new ArrayList<>();
                            al.addAll(myDbHelper.getUserAllData(s.getSessionDetails("key_session_phone")));
                            if (al != null) {

                                String ambulance_help = "+918101624709";
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(ambulance_help, null, "I have been need ambulance  . Please help me immediately! My name is "+al.get(0).getName().toString()+"  My phone number is "+al.get(0).getPhone().toString()+" call me for more information", null, null);
                                smsManager.sendTextMessage(ambulance_help, null, "My location is bellow!\n"+"http://maps.google.com/maps?z=12&t=m&q=loc:" + lat + "," + lng, null, null);
                                speakOut("Waait  help is on the waay.  We don't wast time.  As soon as possible we take care");
                                Toast.makeText(view.getContext(),"Wait for response minutes help in on the way",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(view.getContext(),"Some problem occur, please try again",Toast.LENGTH_SHORT).show();
                            }
                        }
                    },3000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return view;
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