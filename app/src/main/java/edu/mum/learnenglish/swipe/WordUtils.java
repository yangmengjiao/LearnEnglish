package edu.mum.learnenglish.swipe;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import edu.mum.learnenglish.R;
import edu.mum.learnenglish.model.Word;

public class WordUtils {

    private static final String TAG = "Utils";

    public static List<Word> loadWords(Context context,String jsonFileName){
        try{
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JSONArray array = new JSONArray(loadJSONFromAsset(context, jsonFileName));
            List<Word> wordList = new ArrayList<>();
            for(int i=0;i<array.length();i++){
                Word word = gson.fromJson(array.getString(i), Word.class);
                wordList.add(word);
            }
            return wordList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private static String loadJSONFromAsset(Context context, String jsonFileName) {
        String json = null;
        InputStream is=null;
        try {
            AssetManager manager = context.getAssets();
            Log.d(TAG,"path "+jsonFileName);
            is = manager.open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    //get resource id
    public static int  getResource(String imageName){
        Class mipmap = R.mipmap.class;
        try {
            Field field = mipmap.getField(imageName);
            int resId = field.getInt(imageName);
            return resId;
        } catch (NoSuchFieldException e) {
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }

    }
}
