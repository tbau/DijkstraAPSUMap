package com.tbau.dijkstraapsumap;
import android.graphics.Point;

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

        ArrayList<Boolean> toBeChecked = new ArrayList<>(this.numVertices);

        for (int i = 0; i < this.numVertices; i++){
            toBeChecked.set(i, true);
        }

        while(!(num_checked >= this.numVertices)){
            for (int i = 0; i < this.numVertices; i++){
                if (toBeChecked.get(i) == true){
                    v = i;
                    break;
                }
            }

            for (int i = 0; i < this.numVertices; i++){
                if (toBeChecked.get(i) == true && (currDist[i] < currDist[v])){
                    v = i;
                }
            }

            toBeChecked.set(v, false);
            num_checked++;

            for (int i = 0 ; i < this.numVertices; i++){
                if (this.Matrix[v][i] != 0 && this.Matrix[v][i] != this.MAX_WEIGHT
                        && toBeChecked.get(i) == true){
                    if (currDist[i] > (currDist[v] + this.Matrix[v][i])){
                        currDist[i] = currDist[v] + this.Matrix[v][i];
                        predecessor[i] = v;
                    }
                }
            }
        }
    }

    public void displayMatrix(){
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                System.out.print(this.Matrix[row][column] + " ");
            }
            System.out.println();
        }
    }
}




