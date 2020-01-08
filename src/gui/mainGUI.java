package gui;

import utils.Point3D;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;

public class mainGUI {
	public static void main(String[] args) {
	Point3D p1 = new Point3D (720,350,10);
	Point3D p2 = new Point3D (500,800,9);
	Point3D p3 = new Point3D (40,80,11);
	Point3D p4 = new Point3D (500,1000,0.4);
	Point3D p5 = new Point3D (200,680,0.5);
	Point3D p6 = new Point3D (800,180,0.5);
	Point3D p7 = new Point3D (400,200,4);
	Point3D p8 = new Point3D (480,500,0.4);
	Point3D p9 = new Point3D (750,850,0.5);
	Point3D p10 = new Point3D (900,700,0.5);
	Point3D p11 = new Point3D (910,710,4);
	Point3D p12 = new Point3D (630,950,0.4);
	

	NodeData n1 = new NodeData (1, 9, p1);
	NodeData n2 = new NodeData (2, 5.5, p2);
	NodeData n3 = new NodeData (3, 5.3, p3);
	NodeData n4 = new NodeData (4, 2, p4);
	NodeData n5 = new NodeData (5, 5.7, p5);
	NodeData n6 = new NodeData (6,4,p6);
	NodeData n7 = new NodeData (7,9,p7);
	NodeData n8 = new NodeData (8, 7, p8);
	NodeData n9 = new NodeData (9, 5.2, p9);
	NodeData n10 = new NodeData (10, 1, p10);
	NodeData n11 = new NodeData (11, 3.6, p11);
	NodeData n12 = new NodeData (12, 3, p12);
	
	
	graph g = new DGraph();

	g.addNode(n1);
	g.addNode(n2);
	g.addNode(n3);
	g.addNode(n4);
	g.addNode(n5);
	g.addNode(n6);
	g.addNode(n7);
	g.addNode(n8);
	g.addNode(n9);
	g.addNode(n10);
	g.addNode(n11);
	g.addNode(n12);
	

	g.connect(1,2,3);
	g.connect(2,1,11);
	g.connect(2,3,5.9);
	g.connect(3,2,8);
	g.connect(3,4,5.2);
	g.connect(4,3,5);
	g.connect(5,4,6);
	g.connect(4,5,2.7);
	g.connect(1,6,5);
	g.connect(6,1,3.8);
	g.connect(6,5,8.6);
	g.connect(5,6,8.6);
	g.connect(7,8,3);
	g.connect(8,7,11);
	g.connect(8,9,5.9);
	g.connect(9,8,5.9);
	g.connect(9,10,8);
	g.connect(10,9,5.9);
	g.connect(10,8,5.2);
	g.connect(10,6,5);
	g.connect(10,11,5.9);
	g.connect(11,10,5.9);
	g.connect(11,6,6);
	g.connect(12,11,2.7);
	g.connect(6,12,5);
	g.connect(1,11,3.8);
	g.connect(2,13,3);
	g.connect(12,3,2);
	g.connect(12,2,2);
	g.connect(3,8,2);
	g.connect(9,11,2);
	g.connect(6, 7, 5);
	g.connect(6, 3, 2);
	
	graphGUI gui= new graphGUI(g);		
	gui.setVisible(true);
	}
}