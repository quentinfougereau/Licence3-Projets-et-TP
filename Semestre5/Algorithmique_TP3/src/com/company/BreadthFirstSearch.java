package com.company;

import java.util.*;

public class BreadthFirstSearch {

    Graph graph;
    ArrayList<Integer> visitedVertex = new ArrayList<>();
    LinkedList<Integer> vertex = new LinkedList<>();
    ArrayList<Arc> arcs = new ArrayList<>();


    public static Graph graphV4;
    public static ArrayList<Integer> visitedVertexV4 = new ArrayList<>();
    public static LinkedList<Integer> vertexV4 = new LinkedList<>();
    public static ArrayList<Arc> arcsV4 = new ArrayList<>();


    public BreadthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        browseV4(graph, root);
        return arcsV4;
    }

    public ArrayList<Arc> generateTree(int root) {
        this.browseV3(root);
        return this.arcs;
    }

    /* Parcours en largeur */
    public void browse() {
        vertex.add(0);
        int currentVertice;
        while (!vertex.isEmpty()) {
            currentVertice = vertex.poll();
            visitedVertex.add(currentVertice);
            System.out.println(currentVertice);
            for (int i = 0; i < this.graph.outAdjacency.get(currentVertice).size(); i++) {
                if (!visitedVertex.contains(graph.outAdjacency.get(currentVertice).get(i).getDest())) {
                    vertex.add(this.graph.outAdjacency.get(currentVertice).get(i).getDest());
                    visitedVertex.add(this.graph.outAdjacency.get(currentVertice).get(i).getDest());
                }
            }
        }
    }

    /* Parcours en largeur aléatoire */
    public void browseV2() {
        vertex.add(0);
        int currentVertice;
        while (!vertex.isEmpty()) {
            currentVertice = vertex.poll();
            visitedVertex.add(currentVertice);
            System.out.println(currentVertice);
            LinkedList<Arc> shuffledOutAdjacency = shuffleList(this.graph.outAdjacency.get(currentVertice));
            for (int i = 0; i < shuffledOutAdjacency.size(); i++) {
                if (!visitedVertex.contains(shuffledOutAdjacency.get(i).getDest())) {
                    vertex.add(shuffledOutAdjacency.get(i).getDest());
                    visitedVertex.add(shuffledOutAdjacency.get(i).getDest());
                }
            }
        }
    }

    /* Parcours en largeur aléatoire avec source aléatoire */
    public void browseV3(int source) {
        vertex.add(source);
        int currentVertice;
        while (!vertex.isEmpty()) {
            currentVertice = vertex.poll();
            visitedVertex.add(currentVertice);
            //System.out.println(currentVertice);
            LinkedList<Arc> shuffledOutAdjacency = shuffleList(this.graph.outAdjacency.get(currentVertice));
            for (int i = 0; i < shuffledOutAdjacency.size(); i++) {
                if (!visitedVertex.contains(shuffledOutAdjacency.get(i).getDest())) {
                    vertex.add(shuffledOutAdjacency.get(i).getDest());
                    visitedVertex.add(shuffledOutAdjacency.get(i).getDest());
                    arcs.add(shuffledOutAdjacency.get(i));
                }
            }
        }
    }

    /* Même que V3 mais en static */
    public static void browseV4(Graph graph, int source) {
        vertexV4.add(source);
        int currentVertice;
        while (!vertexV4.isEmpty()) {
            currentVertice = vertexV4.poll();
            visitedVertexV4.add(currentVertice);
            //System.out.println(currentVertice);
            LinkedList<Arc> shuffledOutAdjacency = new LinkedList<>();
            if (graph.isVertex(currentVertice))
                shuffledOutAdjacency = shuffleListV4(graph.outAdjacency.get(currentVertice));
            for (int i = 0; i < shuffledOutAdjacency.size(); i++) {
                if (!visitedVertexV4.contains(shuffledOutAdjacency.get(i).getDest())) {
                    vertexV4.add(shuffledOutAdjacency.get(i).getDest());
                    visitedVertexV4.add(shuffledOutAdjacency.get(i).getDest());
                    arcsV4.add(shuffledOutAdjacency.get(i));
                }
            }
        }
    }

    public LinkedList<Arc> shuffleList(LinkedList<Arc> myList) {
        Collections.shuffle(myList);
        return myList;
    }

    public static LinkedList<Arc> shuffleListV4(LinkedList<Arc> myList) {
        Collections.shuffle(myList);
        return myList;
    }

}
