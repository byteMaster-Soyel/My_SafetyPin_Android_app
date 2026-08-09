package com.example.xsefety.First_Activity;

import android.content.Context;
import android.content.SharedPreferences;

public class sessionManager {

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private  final String PREF_FILE_NAME="Shopping";
    private  final int PRIVATE_MODE=0;

    private final String KEY_NAME="key_session_name";
    private final String KEY_PHONE="key_session_phone";

    String KEY_IF_LOGIN = "false";

    public sessionManager(Context context)
    {
        this.context=context;
        sp=context.getSharedPreferences(PREF_FILE_NAME,PRIVATE_MODE);
        editor=sp.edit();
    }

    public boolean checkSession()
    {
        if(sp.contains(KEY_IF_LOGIN))
        {
            return true;
        }
        else {
            return false;
        }
    }
    public void createSession(String name,String phone)
    {
        editor.clear();
        editor.commit();
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_PHONE,phone);
        editor.putBoolean(KEY_IF_LOGIN,true);
        editor.commit();
    }
    public String getSessionDetails(String key)
    {
        return sp.getString(key,"");
    }
    public void logoutSession()
    {
        editor.clear();
        editor.commit();
    }
}

