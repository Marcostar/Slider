package com.sagycorp.slider;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private TypedArray images;
    private Button button;
    private float initialXPoint;
    int[] image = { R.drawable.district, R.drawable.machinist,
            R.drawable.monkey, R.drawable.spiderman, R.drawable.tangled,
            R.drawable.tom, R.drawable.transformer, R.drawable.xmen};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        images = getResources().obtainTypedArray(R.array.images);
        button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setAutoStart(true);
                viewFlipper.setFlipInterval(3000);
                viewFlipper.startFlipping();
            }
        });

        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(image[i]);
            viewFlipper.addView(imageView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                viewFlipper.stopFlipping();
                initialXPoint = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalx = event.getX();
                viewFlipper.stopFlipping();
                if (initialXPoint > finalx) {
                    if (viewFlipper.getDisplayedChild() == image.length)
                        break;
                    viewFlipper.showNext();
                } else {
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;
                    viewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}
