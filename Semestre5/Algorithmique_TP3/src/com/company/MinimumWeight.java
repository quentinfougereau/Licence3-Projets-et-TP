package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class MinimumWeight {

    public static ArrayList<Arc> arcs = new ArrayList<>();
    public static ArrayList<Integer> visitedVertex = new ArrayList<>();

    public static ArrayList<Arc> browse(Graph graph, int root) {
        //Attribution d'un poid aléatoire
        for (LinkedList<Edge> edges : graph.adjacency) {
            for (Edge edge : edges) {
                edge.weight = Math.random();
            }
        }

        Arc minWeightArc = null;
        for (int i = 0; i < graph.adjacency.size(); i++) {
            double minWeight = 1.0;
            for (int j = 0; j < graph.adjacency.get(i).size(); j++) {
                if (!visitedVertex.contains(i))
                    visitedVertex.add(i);
                if (!visitedVertex.contains(graph.adjacency.get(i).get(j).getDest()) && graph.adjacency.get(i).get(j).weight < minWeight) {
                    minWeight = graph.adjacency.get(i).get(j).weight;
                    minWeightArc = new Arc(graph.adjacency.get(i).get(j), false);
                }
                arcs.add(minWeightArc);
            }
        }
        return arcs;
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        return MinimumWeight.browse(graph, root);
    }

    public static void test() {
        Graph graph = new Graph(6);
        Edge edge1 = new Edge(0,1, 2);
        Edge edge2 = new Edge(0,3, 1);
        Edge edge3 = new Edge(1,2, 1);
        Edge edge4 = new Edge(2,0, 3);
        Edge edge5 = new Edge(2,3, 2);
        Edge edge6 = new Edge(2,4, 2);
        Edge edge7 = new Edge(3,5, 2);
        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        generateTree(graph, 0);
        for(Arc arc : arcs) {
            System.out.println("From : " + arc.getSource() + " To : " + arc.getDest() + " Weight : " + arc.support.weight);
        }
    }

}
