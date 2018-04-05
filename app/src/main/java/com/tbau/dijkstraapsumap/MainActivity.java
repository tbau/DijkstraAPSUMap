package com.tbau.dijkstraapsumap;

import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MapView mv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.start_button);
        Button end = (Button) findViewById(R.id.end_button);
        Button calc = (Button) findViewById(R.id.calc_button);

        mv = (MapView) this.findViewById(R.id.mapView);

        start.setOnClickListener(this);
        end.setOnClickListener(this);
        calc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.start_button){
            mv.first = true;
        }
        else if(view.getId()==R.id.end_button){
            mv.first = false;
        }
        else if(view.getId()==R.id.calc_button){
            if(mv.selected[0]!=mv.selected[1]){
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle("Points")
                        .setMessage("Your two points are ("
                                +mv.points.get(mv.selected[0]).x+", "
                                +mv.points.get(mv.selected[0]).y
                                +mv.points.get(mv.selected[1]).x+", "
                                +mv.points.get(mv.selected[1]).y)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        }
    }
}
