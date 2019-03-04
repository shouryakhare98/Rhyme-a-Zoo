package com.example.shouryakhare.rhyme_a_zoo;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
            json = new String(buffer, StandardCharsets.UTF_8);
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

    public String[] getQuestions(int index) {
        try {
            JSONObject data = this.obj.getJSONObject(String.valueOf(index));
            JSONArray temp = data.getJSONArray("questions");
            String[] questions = new String[temp.length()];

            for (int i = 0; i < questions.length; i++) {
                questions[i] = temp.getString(i);
            }

            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[][] getOptions(int index) {
        try {
            JSONObject data = this.obj.getJSONObject(String.valueOf(index));
            JSONArray temp = data.getJSONArray("options");
            String[][] questions = new String[temp.length()][temp.getJSONArray(0).length()];

            for (int i = 0; i < questions.length; i++) {
                for (int j = 0; j < questions[0].length; j++) {
                    questions[i][j] = temp.getJSONArray(i).getString(j);
                }
            }

            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
