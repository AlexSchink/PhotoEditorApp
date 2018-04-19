package com.example.alexanderschink.photoeditorapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int SELECT_IMAGE = 1;
    private ImageView image;
    private Button button;
    private MyView myView;
    private PointF[] points;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);
        myView = (MyView) findViewById(R.id.myView);

        points = new PointF[10];
        for (int i = 0; i < points.length; i++) {
            points[i] = new PointF();
        }

        myView.setPoints(points);






                myView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        System.out.println("" + event.getX() + event.getY());

                        for (int i = 1; i < points.length; ++i){
                            points[i-1].x = points[i].x;
                            points[i-1].y = points[i].y;
                        }

                        points[points.length - 1].x = event.getX();
                        points[points.length - 1].y =event.getY();

                        myView.invalidate();

                        return true;
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE);

            }
        });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri dataUri = data.getData();

            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(), dataUri);
                image.setImageBitmap(img);

            } catch (IOException e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}