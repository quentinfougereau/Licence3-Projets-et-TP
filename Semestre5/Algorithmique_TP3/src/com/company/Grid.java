package com.company;

import java.util.BitSet;


public class Grid {

	int width;
	int height;
	int maxVertex;
	
	Graph graph;
	
	public int abscissaOfVertex(int vertex) {
		return vertex % width;
	}
	
	public int ordinateOfVertex(int vertex) {
		return vertex / width;
	}
	
	private int vertexOfCoordinate(int abscissa, int ordinate) {
		return ordinate * width + abscissa;
	}
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		maxVertex = width * height - 1;
		graph = new Graph(maxVertex);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (i < width - 1)
					graph.addEdge(new Edge(
									vertexOfCoordinate(i,j),
									vertexOfCoordinate(i+1,j),
									0.0
							));
				if (j < height - 1)
					graph.addEdge(new Edge(
									vertexOfCoordinate(i,j),
									vertexOfCoordinate(i,j+1),
									0.0
						));
			}
		}
		
	}

	
	public boolean isHorizontal(Edge e) {
		return Math.abs(e.source - e.dest) == 1;
	}
	
	public boolean isVertical(Edge e) {
		return Math.abs(e.source - e.dest) == width;
	}
	
	
	private void drawLine(int h, BitSet right) {
		for (int i = 0; i < width - 1; i++) {
			System.out.print("o");
			if (right.get(vertexOfCoordinate(i,h))) System.out.print("--");
			else System.out.print("  ");
		}
		System.out.println("o");
	}
	
	private void drawInterline(int h, BitSet up) {
		for (int i = 0; i < width; i++) {
			if (up.get(vertexOfCoordinate(i,h))) System.out.print("|");
			else System.out.print(" ");
			if (i < width-1) System.out.print("  ");
		}
		System.out.println();
	}

	public void drawSubgrid(Iterable<Edge> edges) {
		BitSet up = new BitSet(maxVertex);
		BitSet right = new BitSet(maxVertex);
		for (Edge e : edges) {
//			System.out.println(e.fromVertex + " -- " + e.toVertex);
			if (isHorizontal(e))
				right.set(Math.min(e.source,e.dest));
			if (isVertical(e))
				up.set(Math.min(e.source,e.dest));
		}
		
		for (int j = 0; j < height; j++) {
			drawLine(j,right);
			if (j < height - 1) drawInterline(j,up);
		}
		
	}

	
}
