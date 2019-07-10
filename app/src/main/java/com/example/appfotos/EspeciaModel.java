package com.example.appfotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class EspeciaModel {

    String name;
    String image;
    Location location;

    public EspeciaModel(String name, Bitmap image) {
        this.name = name;
        this.image = encodeTobase64(image);
    }

    public EspeciaModel(String name, String image, Location location) {
        this.name = name;
        this.image = image;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Double getLocationLat() {
        return location.getLatitude();
    }
    public Double getLocationLong() {
        return location.getLongitude();
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
