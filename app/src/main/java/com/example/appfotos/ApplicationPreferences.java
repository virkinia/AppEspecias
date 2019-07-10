package com.example.appfotos;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationPreferences {
    static final String KEYNOTES = "ESPECIAS";



    private  static SharedPreferences mSharedPref;

    private ApplicationPreferences() {}

    public static void init(Context context) {
        if(mSharedPref == null) {
            mSharedPref = context.getSharedPreferences(KEYNOTES, Activity.MODE_PRIVATE);
        }
    }





    public static void saveEspecia (EspeciaModel especia) {

        ArrayList<EspeciaModel> especiasList = readEspeciaList();
        if(especiasList == null) {
            ArrayList<EspeciaModel> nuevaLista = new ArrayList<EspeciaModel>();
            nuevaLista.add(especia);
            saveEspeciaList(nuevaLista);
        } else {
            especiasList.add(especia);
            saveEspeciaList(especiasList);
        }

    }

    public static void saveEspeciaList(ArrayList<EspeciaModel> especiasList) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(especiasList);

        prefsEditor.putString(KEYNOTES, json);
        prefsEditor.apply();
        //prefsEditor.commit();
    }

    public static ArrayList<EspeciaModel> readEspeciaList() {

        Gson gson = new Gson();
        String json = mSharedPref.getString(KEYNOTES, null);
        Type type = new TypeToken<ArrayList<EspeciaModel>>(){}.getType();
        return gson.fromJson(json, type);

    }


}
