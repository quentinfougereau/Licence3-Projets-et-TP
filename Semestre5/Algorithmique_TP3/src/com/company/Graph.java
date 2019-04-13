package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph implements Iterable<Edge>{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	int order;
	int edgeCardinality;
	
	ArrayList<LinkedList<Edge>> adjacency;
	ArrayList<LinkedList<Arc>> inAdjacency;
	ArrayList<LinkedList<Arc>> outAdjacency;

	ArrayList<Integer> vertices;
	
	public boolean isVertex(int index) {
	    return index < this.order;
	}
	
	public <T> ArrayList<LinkedList<T>> makeList(int size) {
		ArrayList<LinkedList<T>> res = new ArrayList<>(size);
		for(int i = 0; i <= size; i++) {
			res.add(null);			
		}
		return res;
	}
	
	public Graph(int upperBound) {
	    this.order = upperBound;
        this.adjacency = new ArrayList<>(upperBound);
        this.outAdjacency = new ArrayList<>(upperBound);
        this.inAdjacency = new ArrayList<>(upperBound);
	    for (int i = 0; i < this.order; i++) {
	        this.adjacency.add(new LinkedList<>());
	        this.outAdjacency.add(new LinkedList<>());
	        this.inAdjacency.add(new LinkedList<>());
        }
	}
	
	public void addVertex(int indexVertex) {
	    // à remplir
        vertices.add(indexVertex);
	}
	
	public void ensureVertex(int indexVertex) {
	    // à remplir
	}	
	
	public void addArc(Arc arc) {
	    // à remplir
		this.outAdjacency.get(arc.getSource()).add(arc);
		if (isVertex(arc.getDest()))
        	this.inAdjacency.get(arc.getDest()).add(arc);
	}
	
	public void addEdge(Edge e) {
	    // à remplir
		this.adjacency.get(e.getSource()).add(e);
		addArc(new Arc(e, false));
	}

	@Override
	public Iterator<Edge> iterator() {
		return null;
	}

    public Arc[] outNeighbours(int sommet) {
	    return null;
    }
}
