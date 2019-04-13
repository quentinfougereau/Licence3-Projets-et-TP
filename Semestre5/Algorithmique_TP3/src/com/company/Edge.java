package com.company;

public class Edge implements Comparable<Edge> {

	protected int source;
	protected int dest;
	double weight;
	
	public Edge(int source, int dest, double weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	
	public int compareTo(Edge e) {
		if (this.weight == e.weight) return 0;
		if (this.weight < e.weight) return -1;
		return 1;
	}
	
	public int oppositeExtremity(int vertex) {
		return (dest == vertex ? source : dest);
	}
	
	public int getSource() {
		return this.source;
	}
	
	public int getDest() {
		return this.dest;
	}
	
}
