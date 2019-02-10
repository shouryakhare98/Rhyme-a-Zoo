package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JSONReader {

    private JSONObject obj;
    private Context mContext;

    public JSONReader(Context c) {
        this.mContext = c;

        String json = null;

        try {
            InputStream is = this.mContext.getResources().openRawResource(R.raw.rhyme_data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTitle(int index) {
        try {
            JSONObject data = this.obj.getJSONObject(String.valueOf(index));
            return data.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRhyme(int index) {
        try {
            JSONObject data = this.obj.getJSONObject(String.valueOf(index));
            return data.getString("rhyme");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String getQuestions(int index) {
//        try {
//            JSONObject data = this.obj.getJSONObject(String.valueOf(index));
//            return data.getString("title");
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
