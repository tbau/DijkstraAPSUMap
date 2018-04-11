package com.tbau.dijkstraapsumap;
import android.graphics.Point;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.*;
/**
 * Created by sbickel20 on 4/2/18.
 */

public class Graph {
    private int numVertices;
    private int [][] Matrix;
    private static final int MAX_WEIGHT = Integer.MAX_VALUE;
    private ArrayList<Point> points;

    Graph(int numVertices, ArrayList<Point> p){
        this.numVertices = numVertices;
        Matrix = new int[numVertices][numVertices];
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                if (row == column){
                    this.Matrix[row][column]= 0;
                }else{
                    this.Matrix[row][column]= this.MAX_WEIGHT;
                }
            }
        }
        points = p;
    }

    private void copyFrom(final Graph graph){
        this.numVertices = graph.numVertices;
        Matrix = new int[numVertices][numVertices];
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                this.Matrix[row][column] = graph.Matrix[row][column];
            }
        }
    }

    public boolean addEdge(int sourceVertex, int targetVertex){
        if (sourceVertex > (this.numVertices-1) || sourceVertex < 0 || targetVertex > (this.numVertices-1)
                || targetVertex < 0){
            return false;
        }
        this.Matrix[sourceVertex][targetVertex] = (int) Math.sqrt(Math.pow(points.get(sourceVertex).x-points.get(targetVertex).x,2)+
                        Math.pow(points.get(sourceVertex).y-points.get(targetVertex).y,2));

        this.Matrix[targetVertex][sourceVertex] = (int) Math.sqrt(Math.pow(points.get(sourceVertex).x-points.get(targetVertex).x,2)+
                Math.pow(points.get(sourceVertex).y-points.get(targetVertex).y,2));
        return true;
    }

    public void shortestPaths(int sourceVertex, int currDist[], int predecessor[]){
        currDist[sourceVertex] = 0;
        for (int i = 0; i < this.numVertices; i++){
            if (i == sourceVertex)
                continue;
            currDist[i] = this.MAX_WEIGHT;
        }

        int num_checked = 0;
        int v = 0;

        boolean[] toBeChecked = new boolean[this.numVertices];

        for (int i = 0; i < this.numVertices; i++){
            toBeChecked[i]= true;
        }

        while(!(num_checked >= this.numVertices)){
            for (int i = 0; i < this.numVertices; i++){
                if (toBeChecked[i] == true){
                    v = i;
                    break;
                }
            }

            for (int i = 0; i < this.numVertices; i++){
                if (toBeChecked[i] == true && (currDist[i] < currDist[v])){
                    v = i;
                }
            }

            toBeChecked[v]= false;
            num_checked++;

            for (int i = 0 ; i < this.numVertices; i++){
                if (this.Matrix[v][i] != 0 && this.Matrix[v][i] != this.MAX_WEIGHT
                        && toBeChecked[i] == true){
                    if (currDist[i] > (currDist[v] + this.Matrix[v][i])){
                        currDist[i] = currDist[v] + this.Matrix[v][i];
                        predecessor[i] = v;
                    }
                }
            }
        }
    }

    public void displayMatrix(){
        String c = "";

        for(int i=0;i<81;i++){
            c+=String.format("%6s",String.valueOf(i));
        }
        Log.i("Column    ",c);
        String r="";
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                if(this.Matrix[row][column]==Integer.MAX_VALUE) {

                    r+=String.format("%6s","INF");
                }
                else
                r+=String.format("%6s",String.valueOf(this.Matrix[row][column]));
            }
            Log.i("Row   ",String.format("%3s",row)+r);
            r="";
        }
    }

    public void displayPath(int[] path, int k, MapView mv) {


        mv.paths.reset();
        Log.i("I","-----------------------------------------------------------------------------------------------------------------");
        Log.i("Path 0",String.valueOf(mv.selected[1]));
        mv.paths.moveTo(mv.points.get(mv.selected[1]).x * 1600 / 3200.0f, mv.points.get(mv.selected[1]).y * 840 / 1680.0f);
        mv.paths.lineTo(mv.points.get(path[0]).x * 1600 / 3200.0f, mv.points.get(path[0]).y * 840 / 1680.0f);

        for (int i = 0; i < k-1; i++) {
            Log.i("Path "+String.valueOf(i+1), String.valueOf(path[i]));
            mv.paths.moveTo(mv.points.get(path[i]).x * 1600 / 3200.0f, mv.points.get(path[i]).y * 840 / 1680.0f);
            mv.paths.lineTo(mv.points.get(path[i + 1]).x * 1600 / 3200.0f, mv.points.get(path[i + 1]).y * 840 / 1680.0f);
        }
        Log.i("Path "+String.valueOf(k-1), String.valueOf(path[k-1]));

    }
}




