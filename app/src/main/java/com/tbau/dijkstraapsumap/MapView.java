package com.tbau.dijkstraapsumap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    Path paths = new Path();

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

            points.add(0,new Point(1745, 1145));
            points.add(1,new Point(1850, 1020));
            points.add(2,new Point(1920, 685));
            points.add(3,new Point(1930, 1140));
            points.add(4,new Point(1935, 1200));
            points.add(5,new Point(1955, 1125));
            points.add(6,new Point(1970, 735));
            points.add(7,new Point(2005, 705));
            points.add(8,new Point(2015, 1060));
            points.add(9,new Point(2020, 1160));
            points.add(10,new Point(2035, 955));
            points.add(11,new Point(2075, 1140));
            points.add(12,new Point(2095, 690));
            points.add(13,new Point(2100, 1185));
            points.add(14,new Point(2120, 975));
            points.add(15,new Point(2135, 850));
            points.add(16,new Point(2140, 1260));
            points.add(17,new Point(2140, 1110));
            points.add(18,new Point(2190, 725));
            points.add(19,new Point(2225, 730));
            points.add(20,new Point(2220, 800));
            points.add(21,new Point(2220, 1085));
            points.add(22,new Point(2215, 1255));
            points.add(23,new Point(2240, 830));
            points.add(24,new Point(2245, 1225));
            points.add(25,new Point(2285, 720));
            points.add(26,new Point(2280, 830));
            points.add(27,new Point(2275, 1155));
            points.add(28,new Point(2290, 1275));
            points.add(29,new Point(2300, 965));
            points.add(30,new Point(2310, 1225));
            points.add(31,new Point(2315, 895));
            points.add(32,new Point(2330, 1125));
            points.add(33,new Point(2330, 845));
            points.add(34,new Point(2340, 1240));
            points.add(35,new Point(2360, 820));
            points.add(36,new Point(2425, 910));
            points.add(37,new Point(2420, 1170));
            points.add(38,new Point(2415, 1230));
            points.add(39,new Point(2495, 695));
            points.add(40,new Point(2485, 1115));
            points.add(41,new Point(2490, 1200));
            points.add(42,new Point(2510, 1190));
            points.add(43,new Point(2550, 780));
            points.add(44,new Point(2540, 1015));
            points.add(45,new Point(2570, 895));
            points.add(46,new Point(2575, 1110));
            points.add(47,new Point(2590, 1255));
            points.add(48,new Point(2620, 840));
            points.add(49,new Point(2635, 960));
            points.add(50,new Point(2640, 1025));
            points.add(51,new Point(2675, 740));
            points.add(52,new Point(2675, 850));
            points.add(53,new Point(2675, 1120));
            points.add(54,new Point(2680, 1230));
            points.add(55,new Point(2730, 1220));
            points.add(56,new Point(2775, 1075));
            points.add(57,new Point(2760, 1110));
            points.add(58,new Point(2790, 1130));
            points.add(59,new Point(2765, 1170));
            points.add(60,new Point(1935, 1020));
            points.add(61,new Point(1950, 1080));
            points.add(62,new Point(2020, 755));
            points.add(63,new Point(2025, 1180));
            points.add(64,new Point(2030, 1325));
            points.add(65,new Point(2115, 1070));
            points.add(66,new Point(2190, 865));
            points.add(67,new Point(2180, 995));
            points.add(68,new Point(2200, 960));
            points.add(69,new Point(2360, 855));
            points.add(70,new Point(2400, 870));
            points.add(71,new Point(2480, 1040));
            points.add(72,new Point(2490, 1300));
            points.add(73,new Point(2535, 960));
            points.add(74,new Point(2690, 1070));
            points.add(75,new Point(2430, 800));
            points.add(76,new Point(2465, 685));
            points.add(77,new Point(2580, 855));
            points.add(78,new Point(2695, 890));
            points.add(79,new Point(2590, 1180));
            points.add(80,new Point(2360, 1025));
            points.add(81,new Point(2000, 900));

            graph = new Graph(82,points);
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
            graph.addEdge(7, 62);
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
            graph.addEdge(56, 74);
            graph.addEdge(56, 57);
            graph.addEdge(57, 58);
            graph.addEdge(57, 59);
            graph.addEdge(58, 59);
            graph.addEdge(60, 61);
            graph.addEdge(60, 81);
            graph.addEdge(63, 64);
            graph.addEdge(66, 68);
            graph.addEdge(67, 68);
            graph.addEdge(68, 68);
            graph.addEdge(69, 70);
            graph.addEdge(70, 75);
            graph.addEdge(70, 80);
            graph.addEdge(71, 73);
            graph.addEdge(71, 80);
            graph.addEdge(74, 78);
            graph.addEdge(75, 76);
            graph.addEdge(75, 77);
            graph.addEdge(77, 78);
            graph.addEdge(81, 15);

        graph.displayMatrix();

            this.setOnTouchListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(MapBitmap,0,0,paint); //Draw grass path background
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(14);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(paths,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(points.get(selected[1]).x*1600/3200.0f, points.get(selected[1]).y*840/1680.0f,
                8,paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(points.get(selected[0]).x*1600/3200.0f, points.get(selected[0]).y*840/1680.0f,
                8,paint);


    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                int x = (int)event.getX();
                int y = (int)event.getY();


                for(int i = 0; i < 60; i++){
                    if(x> points.get(i).x*1600/3200.0-10
                       &&x< points.get(i).x*1600/3200+10
                       &&y> points.get(i).y*840/1680.0-10
                       &&y< points.get(i).y*840/1680.0+10){
                        if(first)
                            selected[0] = i;
                        else
                            selected[1] = i;

                    }
                }
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }

        return false;
    }
}
