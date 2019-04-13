package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class RootedTree {
	
	private int getHeight(Node n) {
		return (n == null ? -1 : n.height);
	}
	
	private int getSize(Node n) {
		return (n == null ? -1 : n.size);
	}
	

	private class Node {
		int vertex;
		ArrayList<Node> sons;
		
		int height;
		int size;
		int depth;
		
		public Node(int vertex) {
			this.vertex = vertex;
			this.sons = new ArrayList<>();
			this.height = 0;
		}
		
		
		public void setHeight() {
			int maxHeight = -1;
			for (Node son : this.sons) 
				maxHeight = Math.max(maxHeight, son.height);
			this.height = maxHeight + 1;
		}
		
		
		private void setSize() {
			size = 1;
			for (Node son : this.sons) size = size + son.size;
		}
		
		private void setSonsDepth() {
			for (Node son : this.sons) son.depth = this.depth + 1;
		}
		
		private Node maxSizeSon() {
			Node maxSon = null;
			for (Node son : sons) { 
				if (son.size > getSize(maxSon)) maxSon = son;
			}
			return maxSon;
		}
		
		private Node maxHeightSon() {
			Node maxSon = null;
			for (Node son : sons) {
				if (son.height > getHeight(maxSon)) maxSon = son; 
			}
			return maxSon;
		}
		
		
		private int secondMaxHeight() {
			int maxHeight = -1;
			int secondMaxHeight = -1;
			for (Node son : sons) {
				if (son.height > secondMaxHeight) {
					secondMaxHeight = Math.min(maxHeight, son.height);
					maxHeight = Math.max(maxHeight, son.height);
				}
			}
			return secondMaxHeight;
		}

		private void print() {
			System.out.print("Node " + this.vertex + ", sons: ");
			for (Node son : this.sons) {
				System.out.print(son.vertex + " ");
			}
			System.out.println("(height: " + this.height 
							 + ", size: " + this.size
							 + ", 2nd height: " + this.secondMaxHeight()
							 + ", depth: " + this.depth
							 + ")");
		}
		
	}
	
	// to write recursive algorithms without recursion
	ArrayList<Node> inverseBfsOrder;
	ArrayList<Node> bfsOrder; 
	
	Node nodes[];
	Node root;
	int order;
	

	// Tree initialization
	
	public void computeAllHeights() {
		for(Node n : inverseBfsOrder) n.setHeight();
	}
	
	
	public void computeAllSizes() {
		for (Node n : inverseBfsOrder) n.setSize();
	}
	
	
	public void computeAllDepths() {
		root.depth = 0;
		for (Node n : bfsOrder) n.setSonsDepth();
	}
	
	
	
	// Tree invariants
	
	
	// sum of distances between all pairs of vertices.
	public long getWienerIndex() {
		long count = 0;
		for (Node n : bfsOrder) {
			if (n == root) continue;
			count = count + n.size * (order - n.size);
		}
		return count;
	}
	
	
	public int[] getDegreeDistribution(int maxDegree) {
		int maxIndex = Math.min(maxDegree,order-1);
		int[] degrees = new int[1+maxIndex];
		for(int i = 0; i <= maxIndex; i++) degrees[i] = 0;
		int degree;
		for (Node n : bfsOrder) {
			degree = n.sons.size() + (n == root ? 0 : 1);
			if (degree <= maxIndex)
				degrees[degree]++;
		}
		return degrees;
	}
	
	
	public int getRadius() {
		return root.height;
	}
	
	
	public int getDiameter() {
		return root.height + root.secondMaxHeight() + 1;
	}
	
	
	private Node getCentroidNode() {
		Node centroid = root;
		while (centroid.maxSizeSon().size * 2 > order)
			centroid = centroid.maxSizeSon();
		return centroid;
	}
	
	public int getDistanceFromCenterToCentroid() {
		return getCentroidNode().depth;
	}
	
	public double getAverageEccentricity() {
		int sumEccentricity = 0;
		for (Node n : bfsOrder)
			sumEccentricity = sumEccentricity + n.depth;
		return (double) sumEccentricity / (double) order;
	}
	
	
	// Node accessors
	
	public int getRoot() { return root.vertex; }
	
	public int getHeight(int vertex) { 
		return nodes[vertex].height;
	}
	
	public int getDepth(int vertex) {
		return nodes[vertex].depth;
	}
	
	public int getSubtreeSize(int vertex) {
		return nodes[vertex].size;
	}
	
	public int getCentroid() {
		return getCentroidNode().vertex;
	}
	
	
	// printers
	
	public void printStats() {
		System.out.println("Order: " + order);
		System.out.println("Diameter: " + getDiameter());
		System.out.println("Radius: " + getRadius());
		System.out.println("Wiener index: " + getWienerIndex());
		System.out.println("Center to centroid: " 
							+ getDistanceFromCenterToCentroid());
		System.out.println("Average eccentricity: " 
						    + getAverageEccentricity());
	}

	
	
	public void printNode(int vertex) {
		nodes[vertex].print();
	}
	
	public void printTree() {
		for (Node n : bfsOrder) n.print();
	}
	
	// Below to end: building the tree from list of arcs.
	// We want the center of the tree as root.
	// 1) createTree: Gets a tree encoded in the Node structure.
	//    This is done by using bfs algorithm on the graph of edges.
	//    From the bfs list of arcs, creates each node and attach it to father
	//    Stores each node in an array indexed by vertices.
	// 2) Computes the height of every node, in inverse bfs order.
	// 3) rerootTree: Moves root toward center.
	//    the two highest sons must have almost the same height.
	//    it detects if it is balanced,
	//    and if not moves the root to the highest son (swapRootWith)
	// 4) resetBfsOrdering : recomputes bfs and inverse bfs order.
	//    this time, a bfs on the node structure is enough
	// 5) Computes height, size and depth of every node.
	
	private void resetBfsOrdering() {
		Queue<Node> stack = new LinkedList<Node>();
		stack.offer(root);
		bfsOrder.clear();
		inverseBfsOrder.clear();
		Node current;
		while (!stack.isEmpty()) {
			current = stack.poll();
			for (Node son : current.sons) stack.offer(son);
			bfsOrder.add(current);
			inverseBfsOrder.add(current);
		}
		Collections.reverse(inverseBfsOrder);
		
	}
	
	
	private void swapRootWith(Node son) {
		root.sons.remove(son);
		root.setHeight();
		son.height = Math.max(root.height + 1, son.height);
		son.sons.add(root);
		root = son;
	}

		
	private boolean isUnbalanced() {
		return root.height > root.secondMaxHeight() + 2;
	}
	
	private void rerootTree() {
		computeAllHeights();
		while (isUnbalanced()) 
			swapRootWith(root.maxHeightSon());
		resetBfsOrdering();
	}
	
	
	private void createNode(Node nodes[], Arc arc) {
		int son = arc.getDest();
		int father = arc.getSource();
		nodes[son] = new Node(son);
		nodes[father].sons.add(nodes[son]);
	}	

	
	private void createTree(int root, ArrayList<Arc> sortedArcs) {
		this.bfsOrder = new ArrayList<>(order);
		this.inverseBfsOrder = new ArrayList<>(order);
		nodes = new Node[order];
		nodes[root] = new Node(root);

		this.bfsOrder.add(nodes[root]);
		for (Arc arc : sortedArcs) {
			createNode(nodes,arc);	
			this.bfsOrder.add(nodes[arc.getDest()]);
		}
		
		inverseBfsOrder.addAll(bfsOrder);
		Collections.reverse(inverseBfsOrder);
		this.root = nodes[root];
	}
	
			
	public RootedTree(ArrayList<Edge> edges, int root) {
		this.order = edges.size() + 1;
		Graph graph = new Graph(order);
		for (Edge e : edges) graph.addEdge(e);

		//createTree(root, BreadthFirstSearch.generateTree(graph, root));
        createTree(root, AldousBroder.generateTree(graph, root));
		
		rerootTree();
		computeAllHeights();
		computeAllSizes();
		computeAllDepths();
	}
	
		
	
	
}
