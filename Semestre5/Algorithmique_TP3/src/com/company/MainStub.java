package com.company;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainStub {

	@SuppressWarnings("unused")
	private final static Random gen = new Random();
	
	public static ArrayList<Edge> genTree(Graph graph) {
		ArrayList<Edge> randomTree;
		
		// TOOO : modifier l'algorithme utiliser ici.

		//BFS
        //ArrayList<Arc> randomArcTree = BreadthFirstSearch.generateTree(graph,0);

		//AldousBroder
		ArrayList<Arc> randomArcTree = AldousBroder.generateTree(graph, 0);


		randomTree = new ArrayList<>();
		for (Arc a : randomArcTree) randomTree.add(a.support);
		
		return randomTree;
	}
	
	
	public static void main(String argv[]) throws InterruptedException {




		Grid grid = null;
		grid = new Grid(1920/11,1080/11);
		Graph graph = grid.graph;

		/*
	    //Test de la classe Graph
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

        graph.addArc(new Arc(edge1, false));
        graph.addArc(new Arc(edge2, false));
        graph.addArc(new Arc(edge3, false));
        graph.addArc(new Arc(edge4, false));
        graph.addArc(new Arc(edge5, false));
        graph.addArc(new Arc(edge6, false));
		graph.addArc(new Arc(edge7, false));
		*/



//		Graph graph = new Complete(400).graph;
		
//		Graph graph = new ErdosRenyi(1_000, 100).graph;

//		Graph graph = new Lollipop(1_000).graph;
		
		int nbrOfSamples = 10;
		int diameterSum = 0;
		double eccentricitySum = 0;
		long wienerSum = 0;
		int degreesSum[] = {0, 0, 0, 0, 0};
		int degrees[];
		
		ArrayList<Edge> randomTree = null; 
		RootedTree rooted = null;

		long startingTime = System.nanoTime();
		for (int i = 0; i < nbrOfSamples; i++) {
			randomTree= genTree(graph);

			rooted = new RootedTree(randomTree,0);
//			rooted.printStats();
			diameterSum = diameterSum + rooted.getDiameter();
			eccentricitySum = eccentricitySum + rooted.getAverageEccentricity();
			wienerSum = wienerSum + rooted.getWienerIndex();
			
			degrees = rooted.getDegreeDistribution(4);
			for (int j = 1; j < 5; j++) {
				degreesSum[j] = degreesSum[j] + degrees[j];
			}
		}		
		long delay = System.nanoTime() - startingTime;
		
		System.out.println("On " + nbrOfSamples + " samples:");
		System.out.println("Average eccentricity: "
							+ (eccentricitySum / nbrOfSamples));
		System.out.println("Average wiener index: " 
							+ (wienerSum / nbrOfSamples));
		System.out.println("Average diameter: " 
							+ (diameterSum / nbrOfSamples));
		System.out.println("Average number of leaves: " 
							+ (degreesSum[1] / nbrOfSamples));
		System.out.println("Average number of degree 2 vertices: "
							+ (degreesSum[2] / nbrOfSamples));
		System.out.println("Average computation time: " 
							+ delay / (nbrOfSamples * 1_000_000) + "ms");
		
		
		if (grid != null) showGrid(grid,rooted,randomTree);




        /*
        //Test de la classe BreadthFirstSearch
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph);
        //bfs.browseV2();
		int randomSource = (int) (Math.random() * graph.order);
		//System.out.println("Random source = " + randomSource + " & Graph order = " + graph.order);
		//bfs.browseV3(randomSource);
        ArrayList<Edge> tree = genTree(graph);
        for (Edge edge : tree) {
            System.out.println("From : " + edge.getSource() + " To : " + edge.getDest());
        }
        */
		/*
		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		graph.addEdge(edge4);
		graph.addEdge(edge5);

		//Test de la classe AldousBroder
		AldousBroder aldousBroder = new AldousBroder(graph);
		aldousBroder.browse(0);
		*/

		//Test de la classe MinimumWeight
		//MinimumWeight.test();


	}

	private static void showGrid(
			Grid grid, 
			RootedTree rooted, 
			ArrayList<Edge> randomTree
			) throws InterruptedException {
		JFrame window = new JFrame("solution");
		final Labyrinth laby = new Labyrinth(grid, rooted);

		laby.setStyleBalanced();
//		laby.setShapeBigNodes();
		laby.setShapeSmallAndFull();
		laby.setShapeSmoothSmallNodes();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(laby);
		window.pack();
		window.setLocationRelativeTo(null);


		for (final Edge e : randomTree) {
					laby.addEdge(e);
		}
		laby.drawLabyrinth();

		window.setVisible(true);
		/*
		// Pour générer un fichier image.
		try {
			laby.saveImage("resources/random.png");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		*/

	}
	
	
}
