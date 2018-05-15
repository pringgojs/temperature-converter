package com.example.sangpemikir.firstapp.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sangpemikir.firstapp.MainActivity;
import com.example.sangpemikir.firstapp.R;

public class CameraFragment extends android.support.v4.app.Fragment {

    private static final int CAMERA_PIC_REQUEST = 0;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_camera, container,false);
        ((MainActivity) getActivity()).setActionBarTitle(getResources().getString(R.string.title_activity_camera));
        floatinButton();

        return view;
    }

    private void floatinButton() {
        FloatingActionButton fab = view.findViewById(R.id.btOpen);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            ImageView imageview = (ImageView) view.findViewById(R.id.imageView); //sets imageview as the bitmap
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setImageBitmap(image);

        }
    }
}
