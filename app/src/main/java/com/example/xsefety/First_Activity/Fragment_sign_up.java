package com.example.xsefety.First_Activity;

import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xsefety.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Fragment_sign_up extends Fragment {

    View view;
    TextView id_tv_sign_up_page;

    public String  name,password,guardian_phone_number,self_phone_number,pin;
    boolean name_boolean=false,self_phone_number_boolean=false,guardian_phone_number_boolean=false,pin_number_boolean=false,password_boolean=false;
    com.google.android.material.textfield.TextInputEditText up_name_et,up_phone_et,up_pin_et,up_email_et,up_password_et,up_guardian_et;
    CheckBox up_check_box;
    Button btn_register_go;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_sign_up, container, false);

        up_check_box=(CheckBox) view.findViewById(R.id.up_check_box);

        up_email_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_email_et);
        up_guardian_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_guardian_et);
        up_name_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_name_et);
        up_pin_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_pin_code_et);
        up_phone_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_phone_et);
        up_password_et=(com.google.android.material.textfield.TextInputEditText) view.findViewById(R.id.up_password_et);


        // check name validation
        up_name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                name=up_name_et.getText().toString();
                if (!(name.isEmpty()))
                {
                    if (name.length() >= 2 && isAlphaValid(name))
                    {
                            if (isNum_name_check(name))
                            {
                                    if(isSpecial(name))
                                    {
                                        name_boolean = true;
                                    }
                                    else
                                    {
                                        up_name_et.setError("Special character not allowed.");
                                    }
                            }
                            else
                            {
                                    up_name_et.setError("digit is not valid");
                            }
                    }
                    else{

                            if(isAlphaValid(name))
                            {
                                up_name_et.setError("Please enter your full name");
                            }
                            else {
                                up_name_et.setError("digit is not valid");
                            }
                    }
                }
                else
                {
                    up_name_et.setError("Empty!");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

        });


        // check phone number
        up_phone_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                self_phone_number=up_phone_et.getText().toString();

                if(self_phone_number.startsWith("+91"))
                {
                    if (validateMobile(self_phone_number.substring(3))) {
                        self_phone_number_boolean = true;
                    } else {

                        up_phone_et.setError("Invalid mobile number");
                    }
                }
                else
                {
                    if (validateMobile(self_phone_number)) {
                        self_phone_number_boolean = true;
                        self_phone_number="+91"+self_phone_number;
                    } else {

                        up_phone_et.setError("Invalid mobile number");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // check guardian phone number
        up_guardian_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guardian_phone_number=up_guardian_et.getText().toString();
                if(!self_phone_number.contains(guardian_phone_number))
                {

                    if (guardian_phone_number.startsWith("+91"))
                    {
                        if (validateMobile(guardian_phone_number.substring(3)))
                        {
                            guardian_phone_number_boolean = true;
                        }
                        else
                        {

                            up_guardian_et.setError("Invalid mobile number");
                        }
                    }
                    else
                    {
                        if (validateMobile(guardian_phone_number)) {
                            guardian_phone_number_boolean = true;
                            guardian_phone_number = "+91" + guardian_phone_number;
                        } else {

                            up_guardian_et.setError("Invalid mobile number");
                        }
                    }
                }
                else
                {
                    up_guardian_et.setError("Enter guardian number");
                    up_guardian_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // check pin number validation
        up_pin_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pin=up_pin_et.getText().toString();

                if(isNum_pin(pin) && pin.length()==6)
                {
                    pin_number_boolean=true;
                }
                else
                {
                   up_pin_et.setError(" ");
                   up_pin_et.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        //check password validation
        up_password_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password=up_password_et.getText().toString();
                if (validatePassword(password)) {
                    password_boolean = true;
                } else {
                    up_password_et.setError("length 8\n use uppercase\nlowercase\nand special symbol");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        id_tv_sign_up_page = (TextView) view.findViewById(R.id.id_tv_sign_in_page);
        btn_register_go =(Button) view.findViewById(R.id.btn_register_go);


        // register button submit
        btn_register_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name_boolean) {
                    if (self_phone_number_boolean) {
                        if (validateEmail(up_email_et.getText().toString())){
                            if(guardian_phone_number_boolean) {
                                if(password_boolean) {
                                    if(pin_number_boolean)
                                    {
                                        if(up_check_box.isChecked()) {

                                            // Mydata mydata = new Mydata(name,phone,password);


//                            Intent intent = new Intent(register.this, otp_verification.class);
//                            intent.putExtra("phone", phone);
//                            intent.putExtra("name",name);
//                            startActivity(intent);
//                            finish();

                                            // SQLite data store
                                            MyDbHelper myDbHelper=new MyDbHelper(view.getContext());
                                            myDbHelper.InsertUserHelper(name,self_phone_number,up_email_et.getText().toString(),password,pin);
                                            myDbHelper.InsertGuardianHelper(guardian_phone_number);

                                            //SharedPreference data store
                                            sessionManager s=new sessionManager(view.getContext());
                                            s.createSession(name,self_phone_number);


                                            Intent intent = new Intent(getContext(), Home_page_Activity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(view.getContext(),"Accept terms and condition",Toast.LENGTH_SHORT).show();
                                        }
                                    }else
                                        {
                                            up_pin_et.setError(" ");
                                            up_pin_et.requestFocus();
                                        }
                                }else{
                                    up_password_et.setError(" ");
                                    up_password_et.requestFocus();
                                }
                            }else{
                                up_guardian_et.setError(" ");
                                up_guardian_et.requestFocus();
                            }



                        } else {
                            up_email_et.setError(" ");
                            up_email_et.requestFocus();
                        }
                    } else {
                        up_phone_et.setError(" ");
                        up_phone_et.requestFocus();
                    }
                } else {
                    up_name_et.setError("");
                    up_name_et.requestFocus();
                }

            }
        });

        id_tv_sign_up_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment open_sign_in = new Fragment_sign_in();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_first, open_sign_in,null).addToBackStack(null).commit() ;

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
            up_email_et.setError("Email is required. Can't be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            up_email_et.setError("Email is invalid. Enter valid email address");
            return false;
        }
        else
        {
            return true;
        }
    }
}