package com.example.appfotos.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appfotos.ApplicationPreferences;
import com.example.appfotos.MainActivity;
import com.example.appfotos.R;
import com.example.appfotos.model.EspeciaModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FotoFragment extends Fragment {

    private ImageView imageView;
    private Button photoButton;
    private Button saveButton;
    private EditText nameText;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;


    public FotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_foto, container, false);

        imageView = v.findViewById(R.id.imgFoto);
        photoButton = v.findViewById(R.id.btn_camera);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Es una versión mayor que la 6.0
                    if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });
        nameText = v.findViewById(R.id.editText);
        saveButton = v.findViewById(R.id.btn_save);
        saveButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bitmap bitmap;
                        bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                        String name = nameText.getText().toString();
                        Location localizacion = ((MainActivity) getActivity()).localizacion;
                        EspeciaModel especia = new EspeciaModel(name, bitmap, localizacion);
                        especia.save();
                    }
                }
        );

        loadEspecia();

        return v;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permisos de la cámara concedidos", Toast.LENGTH_LONG).show();

                // LANZO LA CÁMARA DE FORMA NATIVA
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else {

                Toast.makeText(getContext(), "Permisos de la cámara denegados", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");  // CREO UN BITMAP A PARTIR DE LOS DATOS RECOGIDOS POR LA CÁMARA
            imageView.setImageBitmap(photo);
        }
    }

    public void loadEspecia() {
        ArrayList<EspeciaModel> lista = ApplicationPreferences.readEspeciaList();
        if(lista != null) {
            imageView.setImageBitmap(lista.get(0).getImageBase64());
            nameText.setText(lista.get(0).getName());
        }

    }

}
