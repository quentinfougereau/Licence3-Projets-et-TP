package com.company;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Labyrinth extends JPanel {
	
	private static final long serialVersionUID = 2192694920147985L;
	int halfSide = 5;
	int vertexMargin = 1;
	int corridorMargin = 2;
	int corridorLength = 2 * halfSide;
	int side;
	int vertexRadius;
	int vertexWidth;
	int corridorWidth;
	int corridorStartShift;
	int colorGradientCycleLength = 150; // > 0
	int brightnessSlope= 3; // 0 <= x <= 100
	int minBrightness = 40; // 0 <= x <= 100
	Color backgroundColor = Color.black;

	BufferedImage img;

	private void recomputeDefaultValues() {
		side =  2 * halfSide + 1;
		vertexWidth = side - 2 * vertexMargin;
		corridorWidth = side - 2 * corridorMargin;
		corridorStartShift = side / 2 + 1;
		vertexRadius = halfSide - vertexMargin;
	}
	
	Grid grid;
	RootedTree tree;
	ArrayList<Edge> edges;
	
	public void setStyleBright() {
		colorGradientCycleLength = 150;
		brightnessSlope = 0;
		minBrightness = 100;
		backgroundColor = Color.black;
	}
	
	public void setStyleInverted() {
		colorGradientCycleLength = 150;
		brightnessSlope = 2;
		minBrightness = 10;
		backgroundColor = Color.gray;
	}
	
	public void setStyleBalanced() {
		colorGradientCycleLength = 150;
		brightnessSlope = 3;
		minBrightness = 40;
		backgroundColor = Color.black;
	}
	
	public void setShapeBigNodes() {
		halfSide = 10;
		vertexMargin = 1;
		corridorMargin = 5;
		recomputeDefaultValues();
	}
	
	public void setShapeSmoothSmallNodes() {
		halfSide = 5;
		vertexMargin = 1;
		corridorMargin = 1;
		recomputeDefaultValues();
	}
	
	public void setShapeSmallAndFull() {
		halfSide = 5;
		vertexMargin = 0;
		corridorMargin = 0;
		recomputeDefaultValues();
	}
	
	public Labyrinth(Grid g, RootedTree tree) {
		this.grid = g;
		this.tree = tree;
		edges = new ArrayList<>();
		recomputeDefaultValues();
		setPreferredSize(new Dimension(side * grid.width,side*grid.height));
		img = new BufferedImage(
					side * grid.width,
					side * grid.height,
					BufferedImage.TYPE_3BYTE_BGR
		);
	}
	
	public void addEdge(Edge e) {
		edges.add(e);
	}

	private Color getVertexColor(int vertex) {
		if (tree == null) return Color.white;
		int depth = tree.getDepth(vertex);
		int height = tree.getHeight(vertex) + 1;
		float hue = (float) 
				(depth % colorGradientCycleLength) / colorGradientCycleLength;
		float saturation = (float) 0.7;
		float brightness = (float)
				Math.min(100, brightnessSlope * height + minBrightness) / 100;
		Color col = Color.getHSBColor(hue, saturation, brightness);
		return col;
		
	}
	
	private void drawVerticalEdge(Graphics2D g, Edge e) {
		int source = Math.min(e.source, e.dest);
		int dest = Math.max(e.source,e.dest);
		int xMin = side * grid.abscissaOfVertex(source) + corridorMargin;
		int yMin = side * grid.ordinateOfVertex(source) + corridorStartShift;
		Rectangle rect = new Rectangle(xMin,yMin,corridorWidth, 2*halfSide);
		GradientPaint gradient;
		gradient = new GradientPaint(
				xMin, yMin + vertexRadius - 1, getVertexColor(source),
				xMin, yMin + 2*halfSide - vertexRadius, getVertexColor(dest)
				);
		g.setPaint(gradient);
		g.fill(rect);
	}
	
	private void drawHorizontalEdge(Graphics2D g, Edge e) {
		int source = Math.min(e.source, e.dest);
		int dest = Math.max(e.source,e.dest);
		int xMin = side * grid.abscissaOfVertex(source) + corridorStartShift;
		int yMin = side * grid.ordinateOfVertex(source) + corridorMargin;
		Rectangle rect = new Rectangle(xMin, yMin, 2*halfSide, corridorWidth);
		GradientPaint gradient = new GradientPaint(
				xMin + vertexRadius - 1, yMin, getVertexColor(source),
				xMin + 2*halfSide - vertexRadius, yMin, getVertexColor(dest)
				);
		g.setPaint(gradient);
		g.fill(rect);
	}
	
	private void drawVertex(Graphics2D g, int vertex) {
		int xMin = side * grid.abscissaOfVertex(vertex) + vertexMargin;
		int yMin = side * grid.ordinateOfVertex(vertex) + vertexMargin;
		Shape ell = new Ellipse2D.Float(xMin,yMin,vertexWidth,vertexWidth); 
		g.setPaint(getVertexColor(vertex));
		g.fill(ell);
	}
	
	private void drawRoot(Graphics2D g, int vertex) {
		int i = grid.abscissaOfVertex(vertex);
		int j = grid.ordinateOfVertex(vertex);
		g.setColor(Color.white);
		g.fillRect(side * i, side * j, side, side);
		
	}
	
	private void drawBackground(Graphics2D g) {
		super.setBackground(backgroundColor);
		super.paintComponent(g);		
	}
	
	
	
	public void drawLabyrinth() {
		Graphics2D g = img.createGraphics();
		RenderingHints rh = new RenderingHints(
						RenderingHints.KEY_ANTIALIASING, 
						RenderingHints.VALUE_ANTIALIAS_ON
						);
		g.setRenderingHints(rh);
		drawBackground(g);
				
		g.setColor(Color.white);
		for (Edge e : edges) {
			if (grid.isHorizontal(e)) drawHorizontalEdge(g,e);
			else drawVerticalEdge(g,e);
		}
		for (int i = 0; i < grid.graph.order; i++) {
			drawVertex(g,i);
		}		
		if (tree != null) drawRoot(g,tree.getRoot());

		g.dispose();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.drawImage(img,0,0,null);		
	}
	
	public void saveImage(String path) throws IOException {
		ImageIO.write((RenderedImage) img,"PNG", new File(path));
	}
	
}
