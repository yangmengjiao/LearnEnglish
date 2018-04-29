package edu.mum.learnenglish.helper;

import android.app.Activity;
import android.content.Context;

public class SettingManager {

    private static final String KEY_LIGHT_SENSOR_FLAG = "lightSensorFlag";
    private static final int defaultSensorValue = 0;

    private static final String KEY_ENGLISH_ONLY_FLAG = "englishOnly";
    private static final int defaultEnglishOnlyValue = 0;

    private static final String KEY_PLAY_TIME = "playTime";
    private static final String defaultKEYPLAYTIME = "forever";//for ever loop

    private static final String KEY_SHAKE_FLAG = "shakeFlag";
    private static final int defaultShakeFlag= 0;

    public static float getLightSensorFlag(Context context) {

        return PrefUtils.getInt(context, KEY_LIGHT_SENSOR_FLAG, defaultSensorValue);
    }

    public static void setLightSensorFlag(Context context,int value) {
        PrefUtils.setInt(context, KEY_LIGHT_SENSOR_FLAG, value);
    }

    public static float getEnglishOnlyFlag(Context context) {

        return PrefUtils.getInt(context, KEY_ENGLISH_ONLY_FLAG, defaultEnglishOnlyValue);
    }

    public static void setEnglishOnlyFlag(Context context,int value) {
        PrefUtils.setInt(context, KEY_ENGLISH_ONLY_FLAG, value);
    }

    public static String getPlayTime(Context context) {

        return PrefUtils.getString(context, KEY_PLAY_TIME, defaultKEYPLAYTIME);
    }

    public static void setPlayTime(Context context,String value) {
        PrefUtils.setString(context, KEY_PLAY_TIME, value);
    }

    public static int getShakeFlag(Context context) {

        return PrefUtils.getInt(context, KEY_SHAKE_FLAG, defaultShakeFlag);
    }

    public static void setShakeFlag(Context context,int value) {
        PrefUtils.setInt(context, KEY_SHAKE_FLAG, value);
    }


}
