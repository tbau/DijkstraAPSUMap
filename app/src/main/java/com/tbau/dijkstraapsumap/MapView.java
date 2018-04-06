package com.tbau.dijkstraapsumap;

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

    ArrayList<Point> points;
    int selected[]=new int[2];
    boolean first=true;

    Graph graph;

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

        points = new ArrayList<>();

        points.add(0,new Point(1745,1145));
        points.add(1,new Point(1850,1020));

        graph = new Graph(81,points);
        graph.addEdge(0, 1);
        graph.addEdge(1, 60);
        graph.addEdge(2, 6);
        graph.addEdge(2, 7);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 63);
        graph.addEdge(5, 61);
        graph.addEdge(6, 7);
        graph.addEdge(6, 62);
        graph.addEdge(7, 12);
        graph.addEdge(8, 61);
        graph.addEdge(8, 14);
        graph.addEdge(8, 65);
        graph.addEdge(9, 63);
        graph.addEdge(10, 14);
        graph.addEdge(11, 65);
        graph.addEdge(11, 13);
        graph.addEdge(12, 62);
        graph.addEdge(12, 18);
        graph.addEdge(13, 16);
        graph.addEdge(13, 17);
        graph.addEdge(13, 63);
        graph.addEdge(14, 65);
        graph.addEdge(14, 67);
        graph.addEdge(15, 62);
        graph.addEdge(15, 66);
        graph.addEdge(16, 64);
        graph.addEdge(16, 22);
        graph.addEdge(17, 65);
        graph.addEdge(17, 21);
        graph.addEdge(18, 19);
        graph.addEdge(18, 20);
        graph.addEdge(19, 20);
        graph.addEdge(19, 25);
        graph.addEdge(20, 23);
        graph.addEdge(21, 67);
        graph.addEdge(21, 27);
        graph.addEdge(22, 24);
        graph.addEdge(22, 28);
        graph.addEdge(23, 66);
        graph.addEdge(23, 26);
        graph.addEdge(24, 27);
        graph.addEdge(24, 28);
        graph.addEdge(26, 33);
        graph.addEdge(27, 32);
        graph.addEdge(27, 30);
        graph.addEdge(28, 30);
        graph.addEdge(28, 34);
        graph.addEdge(29, 68);
        graph.addEdge(29, 31);
        graph.addEdge(29, 80);
        graph.addEdge(30, 34);
        graph.addEdge(31, 33);
        graph.addEdge(32, 80);
        graph.addEdge(33, 69);
        graph.addEdge(34, 28);
        graph.addEdge(35, 69);
        graph.addEdge(35, 75);
        graph.addEdge(36, 70);
        graph.addEdge(36, 71);
        graph.addEdge(37, 38);
        graph.addEdge(37, 40);
        graph.addEdge(38, 41);
        graph.addEdge(38, 72);
        graph.addEdge(39, 76);
        graph.addEdge(39, 43);
        graph.addEdge(40, 71);
        graph.addEdge(40, 46);
        graph.addEdge(41, 42);
        graph.addEdge(42, 79);
        graph.addEdge(43, 77);
        graph.addEdge(44, 71);
        graph.addEdge(44, 73);
        graph.addEdge(44, 49);
        graph.addEdge(44, 50);
        graph.addEdge(45, 73);
        graph.addEdge(45, 77);
        graph.addEdge(45, 49);
        graph.addEdge(46, 79);
        graph.addEdge(46, 53);
        graph.addEdge(47, 72);
        graph.addEdge(47, 54);
        graph.addEdge(47, 79);
        graph.addEdge(48, 77);
        graph.addEdge(48, 52);
        graph.addEdge(48, 51);
        graph.addEdge(49, 50);
        graph.addEdge(49, 78);
        graph.addEdge(50, 74);
        graph.addEdge(51, 52);
        graph.addEdge(52, 78);
        graph.addEdge(53, 74);
        graph.addEdge(53, 57);
        graph.addEdge(53, 54);
        graph.addEdge(54, 55);
        graph.addEdge(55, 59);
        graph.addEdge(56, 57);
        graph.addEdge(57, 58);
        graph.addEdge(57, 59);
        graph.addEdge(58, 59);

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

                Toast.makeText(getContext(), "click event", Toast.LENGTH_LONG).show();

                Log.i("Point: ",x+"  "+y);
                for(int i = 0; i < 2; i++){
                    if(x> points.get(i).x*1600/3200.0-20
                       &&x< points.get(i).x*1600/3200+20
                       &&y> points.get(i).y-20
                       &&y< points.get(i).y+20){
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
