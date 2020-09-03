package com.example.qctmanagement.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.qctmanagement.BuildConfig;


public class SharedPreferencesManager {
    private static final String PREF_FIRST_TIME_SETUP = BuildConfig.APPLICATION_ID + ".pref_first_time_setup";
    private static final String PREF_AUTH_KEY =  BuildConfig.APPLICATION_ID + ".pref_auth_key";
    private static final String PREF_BASE_URL = BuildConfig.APPLICATION_ID + ".base_url";
    private static final String PREF_USER_NAME = BuildConfig.APPLICATION_ID + ".user_name";
    private static final String PREF_PASS_WORD = BuildConfig.APPLICATION_ID + ".password";
    private static final String PREF_IS_SAVE_PASSWORD = BuildConfig.APPLICATION_ID + ".is_save_password";

    private static final String PREF_ACCOUNT_CODE = BuildConfig.APPLICATION_ID + ".account_code";
    private static final String PREF_ACCOUNT_NAME = BuildConfig.APPLICATION_ID + ".account_name";
    private static final String PREF_ACCOUNT_EMAIL = BuildConfig.APPLICATION_ID + ".account_email";
    private static final String PREF_ACCOUNT_PHONE = BuildConfig.APPLICATION_ID + ".account_phone";
    private static final String PREF_ACCOUNT_ADDRESS = BuildConfig.APPLICATION_ID + ".account_address";
    private static final String PREF_ACCOUNT_IMAGE = BuildConfig.APPLICATION_ID + ".account_image";

    private static final String PREF_ROLE_CODE = BuildConfig.APPLICATION_ID + ".role_code";

    private static SharedPreferences sPreferences;

    private SharedPreferencesManager() {
    }

    public static void init(Context context) {
        if (sPreferences == null) {
            sPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }
    public static void setFirstTimeSetup(boolean isFirstTime) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putBoolean(PREF_FIRST_TIME_SETUP, isFirstTime);
        editor.apply();
    }

    public static boolean isFirstTimeSetup() {
        return sPreferences.getBoolean(PREF_FIRST_TIME_SETUP, true);
    }
    public static void setBaseURL(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_BASE_URL,value);
        editor.commit();
    }
    public static String getBaseURL(){
        return sPreferences.getString(PREF_BASE_URL,"http://apiqct.azurewebsites.net/api/");
    }
    public static void setAuthKey(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_AUTH_KEY,value);
        editor.commit();
    }
    public static String getAuthKey(){
        return sPreferences.getString(PREF_AUTH_KEY,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefIsSavePassword(boolean value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putBoolean(PREF_IS_SAVE_PASSWORD,value);
        editor.commit();
    }
    public static boolean getPrefIsSavePassword(){
        return sPreferences.getBoolean(PREF_IS_SAVE_PASSWORD,false);
    }
    public static void setPrefUserName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_USER_NAME,value);
        editor.commit();
    }
    public static String getPrefUserName(){
        return sPreferences.getString(PREF_USER_NAME,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefRoleCode(int value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putInt(PREF_ROLE_CODE,value);
        editor.commit();
    }
    public static int getPrefRoleCode(){
        return sPreferences.getInt(PREF_ROLE_CODE,0);
    }

    public static void setPrefPassWord(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_PASS_WORD,value);
        editor.commit();
    }
    public static String getPrefPassWord(){
        return sPreferences.getString(PREF_PASS_WORD,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefAccountCode(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_CODE,value);
        editor.commit();
    }
    public static String getPrefAccountCode(){
        return sPreferences.getString(PREF_ACCOUNT_CODE,"");
    }
    public static void setPrefAccountName(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_NAME,value);
        editor.commit();
    }
    public static String getPrefAccountName(){
        return sPreferences.getString(PREF_ACCOUNT_NAME,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }
    public static void setPrefAccountAddress(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_ADDRESS,value);
        editor.commit();
    }
    public static String getPrefAccountAddress(){
        return sPreferences.getString(PREF_ACCOUNT_ADDRESS,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefAccountEmail(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_EMAIL,value);
        editor.commit();
    }
    public static String getPrefAccountEmail(){
        return sPreferences.getString(PREF_ACCOUNT_EMAIL,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefAccountPhone(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_PHONE,value);
        editor.commit();
    }
    public static String getPrefAccountPhone(){
        return sPreferences.getString(PREF_ACCOUNT_PHONE,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }

    public static void setPrefAccountImage(String value){
        SharedPreferences.Editor editor=sPreferences.edit();
        editor.putString(PREF_ACCOUNT_IMAGE,value);
        editor.commit();
    }
    public static String getPrefAccountImage(){
        return sPreferences.getString(PREF_ACCOUNT_IMAGE,"TkdVWUVOIFFVT0MgQ1VPTkc=");
    }
}
