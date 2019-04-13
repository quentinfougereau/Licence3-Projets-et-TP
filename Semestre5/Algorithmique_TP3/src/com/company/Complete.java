package com.company;

import java.util.ArrayList;

public class Complete {

	Graph graph;
	ArrayList<Arc> arcs = new ArrayList<>();
	
	public Complete(int order) {
		this.graph = new Graph(order);
		for(int i = 0; i < order; i++)
			for (int j = i+1; j < order; j++)
				graph.addEdge(new Edge(i,j,0));
		
	}

    public static ArrayList<Arc> generateTree(Graph graph, int root) {
        return null;
    }
	
	
	
	
}
