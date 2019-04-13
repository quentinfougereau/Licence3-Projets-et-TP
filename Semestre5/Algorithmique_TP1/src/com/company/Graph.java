package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;
        public int end;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }

        /*
        public Edge(Vertex from, Vertex to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
        */

    }

    /*
    public class Vertex implements Comparable<Vertex> {
        public int value;
        public Integer endDate;
        public String color;

        public Vertex(int value, int endDate, String color) {
            this.value = value;
            this.endDate = endDate;
            this.color = color;
        }

        @Override
        public int compareTo(Vertex vertex) {
            return this.endDate.compareTo(vertex.endDate);
        }
    }
    */

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;
    public int date;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size+1);
        for (int i = 0;i<cardinal;i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
        date = 0;
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        //incidency.get(source).addLast(new Edge(source,dest,label));
        incidency.get(source).addLast(new Edge(source, dest, label));
    }

    public String toString() {
        String result = new String("");
        result.concat(cardinal + "\n");
        for (int i = 0; i<cardinal;i++) {
            for (Edge e : incidency.get(i)) {
                result.concat(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;

    }

    public ArrayList<LinkedList<Edge>> getIncidency() {
        return incidency;
    }

    public int code(int var, int varNumbers) {
        int result = 0;
        if (var > 0) {
            result = var - 1;
        } else if (var < 0) {
            result = - var + varNumbers - 1;
        }
        return result;
    }

    // Parcours en profondeur
    public void depthFirstSearch(int source, boolean[] visited) {
        //for (LinkedList<Edge> edges : this.incidency) {
            if (visited[source])
                return;
            visited[source] = true;

        System.out.print(source);
            for (Edge edge : this.incidency.get(source)) {
                if (edge.label == "false") {
                    edge.label = (Label) "true";
                    depthFirstSearch(edge.destination, visited);
                }
            }
        //}
    }

    /*
    public void function(int source) {
        for (Edge edge : this.incidency.get(source)) {
            if (edge.label == "false") {
                edge.label = (Label) "true";
                function(edge.source);
            }
        }
        //stack.push(source.value);
    }
    */


    // Empile les sommets par date de fin (dernier de la pile = date de fin la plus grande)
    public void pileByEndDate(Stack stack) {
        for (int i = 0; i < this.order(); i++) {
            //Edge edge = edges.get(index);
                //if (edge.label == "false") {
                    //edge.label = (Label) "true";
                    doPile(i, stack);
                //}
        }

    }

    public void doPile(int source, Stack stack) {
        for (Edge edge : this.incidency.get(source)) {
            if (edge.label == "false") {
                edge.label = (Label) "true";
                doPile(edge.destination, stack);
            }
        }
        if (stack.contains(source))
            stack.removeElement(source);
        stack.push(source);
    }



}
