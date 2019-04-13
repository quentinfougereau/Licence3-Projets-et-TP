package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class AldousBroder {

    public static Graph graph;
    public static ArrayList<Integer> visitedVertex = new ArrayList<>();
    LinkedList<Integer> vertex = new LinkedList<>();
    LinkedList<Arc> edges = new LinkedList<>();
    public static ArrayList<Arc> arcs = new ArrayList<>();

    public AldousBroder(Graph graph) {
        graph = graph;
    }

    public static void browse(Graph graph, int currentVertice) {
        visitedVertex.add(currentVertice);
        if (visitedVertex.size() == graph.order)
            return;
        LinkedList<Arc> shuffledOutAdjacency = shuffleList(graph.outAdjacency.get(currentVertice));
        for (int i = 0; i < shuffledOutAdjacency.size(); i++) {
            if (!visitedVertex.contains(graph.outAdjacency.get(currentVertice).get(i).getDest())) {
                if (!arcs.contains(graph.outAdjacency.get(currentVertice).get(i))) {
                    arcs.add(graph.outAdjacency.get(currentVertice).get(i));
                    //System.out.println("Arcs : from " + graph.outAdjacency.get(currentVertice).get(i).getSource() + " to " + graph.outAdjacency.get(currentVertice).get(i).getDest());
                }
                if (graph.isVertex(graph.outAdjacency.get(currentVertice).get(i).getDest()))
                    browse(graph, graph.outAdjacency.get(currentVertice).get(i).getDest());
            }
        }
    }

    public static LinkedList<Arc> shuffleList(LinkedList<Arc> myList) {
        Collections.shuffle(myList);
        return myList;
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        browse(graph, root);
        return arcs;
    }

}
