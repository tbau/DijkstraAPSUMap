package com.tbau.dijkstraapsumap;
import java.util.*;
/**
 * Created by sbickel20 on 4/2/18.
 */

public class Graph {
    private int numVertices;
    private ArrayList<ArrayList<Integer>>Matrix;
    private static final int MAX_WEIGHT = Integer.MAX_VALUE;

    Graph(int numVertices){
        this.numVertices = numVertices;
        Matrix = new ArrayList<ArrayList<Integer>>();
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                if (row == column){
                    this.Matrix.get(row).set(column, 0);
                }else{
                    this.Matrix.get(row).set(column, this.MAX_WEIGHT);
                }
            }
        }
    }

    private void copyFrom(final Graph graph){
        this.numVertices = graph.numVertices;
        Matrix = new ArrayList<ArrayList<Integer>>(this.numVertices);
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                this.Matrix.get(row).set(column, graph.Matrix.get(row).get(column));
            }
        }
    }

    public boolean addEdge(int sourceVertex, int targetVertex, int weight){
        if (sourceVertex > (this.numVertices-1) || sourceVertex < 0 || targetVertex > (this.numVertices-1)
                || targetVertex < 0 || weight < 0){
            return false;
        }

        this.Matrix.get(sourceVertex).set(targetVertex, weight);
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
                if (this.Matrix.get(v).get(i) != 0 && this.Matrix.get(v).get(i) != this.MAX_WEIGHT
                        && toBeChecked.get(i) == true){
                    if (currDist[i] > (currDist[v] + this.Matrix.get(v).get(i))){
                        currDist[i] = currDist[v] + this.Matrix.get(v).get(i);
                        predecessor[i] = v;
                    }
                }
            }
        }
    }

    public void displayMatrix(){
        for (int row = 0; row < this.numVertices; row++){
            for (int column = 0; column < this.numVertices; column++){
                System.out.print(this.Matrix.get(row).get(column) + " ");
            }
            System.out.println();
        }
    }
}




