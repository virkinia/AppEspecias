package com.example.appfotos.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Base64;
import android.util.Log;

import com.example.appfotos.ApplicationPreferences;

import java.io.ByteArrayOutputStream;

public class EspeciaModel {

    String name;
    String image;
    Double latitud;



    Double longitud;

    public EspeciaModel(String name, Bitmap image) {
        this.name = name;
        this.image = encodeTobase64(image);
    }

    public EspeciaModel(String name, Bitmap image, Location location) {
        this.name = name;
        this.image = encodeTobase64(image);
        this.latitud = location.getLatitude();
        this.longitud = location.getLongitude();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getImage() {
        return image;
    }

    public Bitmap getImageBase64() {
        byte[] decodedByte = Base64.decode(image, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Location getLocation() {
        Location loc = new Location("dummyprovider");

        loc.setLatitude(this.latitud);
        loc.setLongitude(this.longitud);

        return loc;
    }

    public void setLocation(Location location) {
        this.latitud = location.getLatitude();
        this.longitud = location.getLongitude();

    }



    public void save() {
        ApplicationPreferences.saveEspecia(this);
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }







}
