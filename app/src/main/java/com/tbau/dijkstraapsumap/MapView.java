package com.tbau.dijkstraapsumap;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.camera2.TotalCaptureResult;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Thomas on 3/26/2018.
 */

public class MapView extends View implements View.OnTouchListener {

    Bitmap MapBitmap;
    int currentWidth;    //Width of View
    int currentHeight;   //Height of View

    Paint paint;         //Used to draw on Canvas

    ArrayList<Point> clickablePoints;
    int selected[]=new int[2];
    boolean first=true;

    public MapView(Context context) {
        super(context);
        setUp(null);
    }

    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(attrs);
    }

    public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(attrs);
    }
    public void setUp(AttributeSet attrs){

        paint = new Paint();
        paint.setColor(0xff000000);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);


        MapBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.apsu_map); // Load bitmap for some allies
        MapBitmap = Bitmap.createScaledBitmap(MapBitmap,1600,840,false);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        //Get current width and height of screen
        currentWidth=dm.widthPixels;
        currentHeight= dm.heightPixels;

        clickablePoints = new ArrayList();

        clickablePoints.add(0,new Point(1745,1145));
        clickablePoints.add(1,new Point(1850,1020));

        this.setOnTouchListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(MapBitmap,0,0,paint); //Draw grass path background
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                int x = (int)event.getX();
                int y = (int)event.getY();

                Toast.makeText(getContext(), "click event", Toast.LENGTH_LONG);

                Log.i("Point: ",x+"  "+y);
                for(int i=0; i<clickablePoints.size();i++){
                    if(x>clickablePoints.get(i).x*1600/3200.0-20
                       &&x<clickablePoints.get(i).x*1600/3200+20
                       &&y>clickablePoints.get(i).y-20
                       &&y<clickablePoints.get(i).y+20){
                        if(first)
                            selected[0] = i;
                        else
                            selected[1] = i;


                        Log.i("Index: ",i+" ");
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }

        return false;
    }
}
