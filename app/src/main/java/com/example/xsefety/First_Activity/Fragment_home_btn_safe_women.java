package com.example.xsefety.First_Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.xsefety.R;
import java.util.ArrayList;


public class Fragment_home_btn_safe_women extends Fragment {

View view;
    ImageButton sos_button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home_btn_safe_women, container, false);


        //sos button work
        sos_button=(ImageButton) view.findViewById(R.id.sos_button);
        sos_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> text=new ArrayList<>();
                ArrayList<UserModel_two> all;
                MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
                all=myDbHelper.GetAllGuardianPhone();
                if(all!=null)
                {
                    for (int i = 0; all.size() > i; i++) {
                        text.add(all.get(i).getGuardian_phone());
                    }
                    startActivity(new Intent(view.getContext(), MapsActivity.class));
                    try {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(text!=null) {
                                    location_calculator location_calculator = new location_calculator();
                                    double lat = com.example.xsefety.First_Activity.location_calculator.getLat();
                                    double lng = com.example.xsefety.First_Activity.location_calculator.getLng();

                                    SmsManager smsManager = SmsManager.getDefault();
                                    for (int i = 0; i < text.size(); i++) {

                                        smsManager.sendTextMessage(text.get(i).toString(), null, "http://maps.google.com/maps?z=12&t=m&q=loc:" + lat + "," + lng, null, null);
                                    }

                                    //mail
                                    Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                                    String subject = "Help message";
                                    String body ="I am in immediate danger/emergency and need urgent assistance.\nI'm located in below location\n"+ lat + "," + lng;
                                    String cc = "suvajitdas325@gmail.com";
                                    String to = "alexsoyel38@gmail.com";
                                    String bcc = "alexsoyel782@gmail.com";
                                    Uri data = Uri.parse("mailto:?subject=" + subject + "&body=" + body + "&to=" + to + "&cc=" + cc + "&bcc=" + bcc);
                                    mailIntent.setData(data);
                                    startActivity(Intent.createChooser(mailIntent, "Send mail..."));

//                        Intent messageIntent=new Intent(Intent.ACTION_VIEW);
//                        CharSequence location=lat+","+lng;
//                        String aEmailList[]={"suvajitdas325@gmail.com"};
//                        messageIntent.putExtra(Intent.EXTRA_EMAIL,aEmailList);
//                        messageIntent.setType("plain/text");
//                        messageIntent.putExtra(Intent.EXTRA_TEXT,"http://maps.google.comk/maps?q=loc:"+location);
//                        Intent.createChooser(messageIntent,"hi");

                                    //call
                                    Intent intent = new Intent(Intent.ACTION_CALL);
                                    intent.setData(Uri.parse(("tel: " + text.get(0).toString())));
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(view.getContext(),"Hi suvajit",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 4000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else
                {

                }



            }

        });

        return view;
    }
}