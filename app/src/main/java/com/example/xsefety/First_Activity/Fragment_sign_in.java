package com.example.xsefety.First_Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xsefety.R;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Fragment_sign_in extends Fragment{

    TextView id_tv_sign_up_click;
    Button btn_login_go;
    com.google.android.material.textfield.TextInputEditText login_phone_number_et,login_password_et;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        id_tv_sign_up_click=(TextView) view.findViewById(R.id.id_tv_sign_up_click);
        btn_login_go = (Button) view.findViewById(R.id.btn_login_go);

        login_phone_number_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.login_phone_number_et);
        login_password_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.login_password_et);

        sessionManager s=new sessionManager(view.getContext());


        if(s.checkSession())
        {
            Intent intent = new Intent(getContext(), Home_page_Activity.class);
            startActivity(intent);
        }

        btn_login_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper myDbHelper = new MyDbHelper(view.getContext());
                ArrayList<UserModel> al = myDbHelper.getUserAllData("+91"+login_phone_number_et.getText().toString());
                if(al!=null)
                {
                    if (login_phone_number_et.getText().toString().startsWith("+91")) {

                        if (login_phone_number_et.getText().toString().equals(al.get(0).getPhone())) {
                            if (login_password_et.getText().toString().equals(al.get(0).getPassword())) {
                                sessionManager s = new sessionManager(view.getContext());
                                s.createSession(al.get(0).getName(), al.get(0).getPhone());

                                Intent intent = new Intent(getContext(), Home_page_Activity.class);
                                startActivity(intent);
                            } else {
                                login_password_et.setError("Wrong password");
                                login_password_et.requestFocus();
                            }
                        } else {
                            login_phone_number_et.setError("Wrong phone number");
                            login_phone_number_et.requestFocus();
                        }

                    } else {
                        String phone = login_phone_number_et.getText().toString();
                        phone = "+91" + phone;

                        if (phone.equals(al.get(0).getPhone())) {
                            if (login_password_et.getText().toString().equals(al.get(0).getPassword())) {
                                sessionManager s = new sessionManager(view.getContext());
                                s.createSession(al.get(0).getName(), al.get(0).getPhone());
                                Intent intent = new Intent(getContext(), Home_page_Activity.class);
                                startActivity(intent);
                            } else {
                                login_password_et.setError("Wrong password");
                                login_password_et.requestFocus();
                            }
                        } else {
                            login_phone_number_et.setError("Wrong phone number");
                            login_phone_number_et.requestFocus();
                        }

                    }
                }
                else
                {
                  //  login_phone_number_et.setError("Wrong phone nunber");

                   Toast.makeText(view.getContext(), "Enter valid Phone Number & Passwrod", Toast.LENGTH_SHORT).show();
                }

            }

        });

        id_tv_sign_up_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment open_sign_up = new Fragment_sign_up();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_first, open_sign_up,null).addToBackStack(null).commit() ;

            }
        });


        return view;
    }

    boolean validateMobile(String phone) {
        Pattern p = Pattern.compile("[6-9][0-9]{9}");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

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

}