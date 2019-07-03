package com.downloader.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Nobel on 14/06/17.
 */

public class DataUtil {

    static DataUtil dataUtil=null;
    static SharedPreferences prefs;

    private DataUtil(Context context){
        prefs=PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void init(Context context){
        if(dataUtil==null)
            dataUtil=new DataUtil(context);
    }
    public static DataUtil getInstance(){
        if(dataUtil==null)throw new IllegalStateException("DataUtil is null make sure to call init");
        return dataUtil;
    }
    public void saveData( String variable, String data) {
         prefs.edit().putString(variable, data).apply();
    }

    public  String getData( String variable,
                                 String defaultValue) {
        String data = prefs.getString(variable, defaultValue);

        return data;
    }

    public  void saveData( String variable, boolean data) {
        prefs.edit().putBoolean(variable, data).apply();
    }

    public  boolean getData( String variable,
                                  boolean defaultValue) {
        boolean data = prefs.getBoolean(variable, defaultValue);

        return data;
    }


    public  void saveData( String variable, int data) {
        prefs.edit().putInt(variable, data).apply();
    }

    public  int getData( String variable, int defaultValue) {
        int data = prefs.getInt(variable, defaultValue);

        return data;
    }

    public  void saveData( String variable, long data) {
        prefs.edit().putLong(variable, data).apply();
    }

    public  void remove(String variable) {
        prefs.edit().remove(variable).apply();
    }

    public  long getData( String variable, long defaultValue) {
        long data = prefs.getLong(variable, defaultValue);

        return data;
    }


}
