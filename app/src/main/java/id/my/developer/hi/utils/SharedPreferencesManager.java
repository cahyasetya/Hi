package id.my.developer.hi.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by light on 22/08/2017.
 */

public class SharedPreferencesManager {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    private static final SharedPreferencesManager ourInstance = new SharedPreferencesManager();

    public static SharedPreferencesManager getInstance() {
        return ourInstance;
    }

    private SharedPreferencesManager() {
    }

    public static SharedPreferencesManager with(Context context){
        if(preferences==null)
            preferences = context.getSharedPreferences("Hi",Context.MODE_PRIVATE);
        editor = preferences.edit();
        return ourInstance;
    }

    private static void insertString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void setNickName(String nickName){
        insertString(ConfigProvider.NICKNAME, nickName);
    }

    public String getNickName(){
        return preferences.getString(ConfigProvider.NICKNAME,null);
    }
}
