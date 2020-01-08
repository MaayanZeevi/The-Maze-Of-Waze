package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import algorithms.*;
import dataStructure.*;
import org.junit.jupiter.api.Test;
import utils.*;
class Graph_AlgoTest {
	
	@Test
	void testInitGraph() {
		Graph_Algo graphAlgo = new Graph_Algo();
        graph DG = new DGraph();
        NodeData n1 = new NodeData(1,2,3);
        NodeData n2 = new NodeData(1,2,3);
        NodeData n3 = new NodeData(1,2,3);
        NodeData n4 = new NodeData(1,2,3);
        NodeData n5 = new NodeData(1,2,3);
        NodeData n6 = new NodeData(1,2,3);
        DG.addNode(n1);
        DG.addNode(n2);
        DG.addNode(n3);
        DG.addNode(n4);
        DG.addNode(n5);
        DG.addNode(n6);
        DG.connect(1,2,5);
        DG.connect(1,3,5);
        DG.connect(1,4,5);
        DG.connect(6,2,5);
        DG.connect(6,5,5);
        DG.connect(6,4,5);
        graphAlgo.init(DG);
        assertEquals(DG,graphAlgo.getGraph());
	}

	@Test
	void testCopy() {
	    Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
	   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
		
		graph actual= new DGraph();
		actual.addNode(n1);
		actual.addNode(n2);
		actual.addNode(n3);
		actual.addNode(n4);
		actual.addNode(n5);
		actual.addNode(n6);
		
		actual.connect(n1.getKey(), n3.getKey(), 10);
		actual.connect(n3.getKey(), n4.getKey(), 30);
		actual.connect(n4.getKey(), n2.getKey(), 15);
		actual.connect(n2.getKey(), n6.getKey(), 1);
		actual.connect(n2.getKey(), n1.getKey(), 0);
		actual.connect(n4.getKey(), n3.getKey(), 5);
		actual.connect(n5.getKey(), n6.getKey(), 8);
		actual.connect(n6.getKey(), n1.getKey(), 2);

		Graph_Algo graphAlgo= new Graph_Algo(actual);
		graph expected= graphAlgo.copy();
		
//		Collection<node_data> nodes= expected.getV();
//		for(node_data n: nodes) {
//			System.out.println(n);
//			Collection<edge_data> edges= expected.getE(n.getKey());
//			for(edge_data edge: edges) {
//				System.out.print(edge + " , ");
//
//			}
//			System.out.println();
//		}
		assertNotEquals(expected, graphAlgo.getGraph());
	}
	
	

	@Test
	void testInitString() {
		Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
		   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
			
		graph actual= new DGraph();
		actual.addNode(n1);
		actual.addNode(n2);
		actual.addNode(n3);
		actual.addNode(n4);
		actual.addNode(n5);
		actual.addNode(n6);
		actual.connect(n1.getKey(), n3.getKey(), 10);
		actual.connect(n3.getKey(), n4.getKey(), 30);
		actual.connect(n4.getKey(), n2.getKey(), 15);
		actual.connect(n2.getKey(), n6.getKey(), 1);
		actual.connect(n2.getKey(), n1.getKey(), 0);
		actual.connect(n4.getKey(), n3.getKey(), 5);
		actual.connect(n5.getKey(), n6.getKey(), 8);
		actual.connect(n6.getKey(), n1.getKey(), 2);

		Graph_Algo graphAlgo= new Graph_Algo(actual);
		graphAlgo.save("graph");
		Graph_Algo expected= new Graph_Algo();
		expected.init("graph");
		boolean flag1= graphAlgo.isConnected() == expected.isConnected();
		boolean flag2= graphAlgo.getGraph().getEdge(1, 3).getWeight()== expected.getGraph().getEdge(1, 3).getWeight();
		assertTrue( flag1);
		assertTrue(flag2);
	}

	@Test
	void testIsConnected() {
		Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
		   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
			
		graph actual= new DGraph();
		actual.addNode(n1);
		actual.addNode(n2);
		actual.addNode(n3);
		actual.addNode(n4);
		actual.addNode(n5);
		actual.addNode(n6);
		actual.connect(n1.getKey(), n3.getKey(), 10);
		actual.connect(n3.getKey(), n4.getKey(), 30);
		actual.connect(n4.getKey(), n2.getKey(), 15);
		actual.connect(n2.getKey(), n6.getKey(), 1);
		actual.connect(n6.getKey(), n1.getKey(), 0);
		actual.connect(n4.getKey(), n3.getKey(), 5);
		actual.connect(n5.getKey(), n6.getKey(), 8);
		actual.connect(n6.getKey(), n1.getKey(), 2);

		Graph_Algo graphAlgo= new Graph_Algo(actual);
		assertFalse(graphAlgo.isConnected());
		actual.connect(n1.getKey(), n5.getKey(), 25);
		graphAlgo.init(actual);
		assertTrue(graphAlgo.isConnected());
	}

	@Test
	void testShortestPathDist() {
		Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
		   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
			
		graph actual= new DGraph();
		actual.addNode(n1);
		actual.addNode(n2);
		actual.addNode(n3);
		actual.addNode(n4);
		actual.addNode(n5);
		actual.addNode(n6);
		actual.connect(n3.getKey(), n1.getKey(), 10);
		actual.connect(n3.getKey(), n4.getKey(), 5);
		actual.connect(n4.getKey(), n2.getKey(), 15);
		actual.connect(n2.getKey(), n6.getKey(), 60);
		actual.connect(n6.getKey(), n1.getKey(), 0);
		actual.connect(n4.getKey(), n3.getKey(), 5);
		actual.connect(n5.getKey(), n6.getKey(), 5);
		actual.connect(n1.getKey(), n5.getKey(), 5);
		actual.connect(n6.getKey(), n1.getKey(), 2);
		actual.connect(n2.getKey(), n1.getKey(), 30);
		Graph_Algo graphAlgo= new Graph_Algo(actual);
		assertEquals(graphAlgo.shortestPathDist(n3.getKey(), n6.getKey()), 20);

	}
	

	@Test
	void testShortestPath() {
		Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
		   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
			
		graph actual= new DGraph();
		actual.addNode(n1);
		actual.addNode(n2);
		actual.addNode(n3);
		actual.addNode(n4);
		actual.addNode(n5);
		actual.addNode(n6);
		actual.connect(n3.getKey(), n1.getKey(), 10);
		actual.connect(n3.getKey(), n4.getKey(), 5);
		actual.connect(n4.getKey(), n2.getKey(), 15);
		actual.connect(n2.getKey(), n6.getKey(), 60);
		actual.connect(n6.getKey(), n1.getKey(), 0);
		actual.connect(n4.getKey(), n3.getKey(), 5);
		actual.connect(n5.getKey(), n6.getKey(), 5);
		actual.connect(n1.getKey(), n5.getKey(), 5);
		actual.connect(n6.getKey(), n1.getKey(), 2);
		actual.connect(n2.getKey(), n1.getKey(), 30);
		
		Graph_Algo graphAlgo= new Graph_Algo(actual);
		
		List<node_data> actualList= new LinkedList<node_data>();
		actualList=graphAlgo.shortestPath(n3.getKey(), n6.getKey());
		
		List<node_data> expectedList= new LinkedList<node_data>();
		expectedList.add(n3);
		expectedList.add(n1);
		expectedList.add(n5);
		expectedList.add(n6);
		assertEquals(actualList, expectedList);
		

	}

	@Test
	void testTSP() {
		Point3D p1 = new Point3D(4,1,0);
		Point3D p2 = new Point3D(-75,14,0);
	    Point3D p3 = new Point3D(80,5,0);
	    Point3D p4 = new Point3D(1,4,0);
	    Point3D p5 = new Point3D(-5,1,0);
	    Point3D p6 = new Point3D(8,3,0);
		   
		node_data n1= new NodeData(1, 20, p1);
		node_data n2= new NodeData(2, 100, p1);
		node_data n3= new NodeData(3, 30, p1);
		node_data n4= new NodeData(4, 0, p1);
		node_data n5= new NodeData(5, 15, p1);
		node_data n6= new NodeData(6, 1, p1);
		
		graph graph= new DGraph();
		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		graph.addNode(n5);
		graph.addNode(n6);
		
		graph.connect(1, 2, 4);
		graph.connect(2, 5, 10);
		graph.connect(5, 1, 4);
		graph.connect(3, 6, 1);
		graph.connect(4, 1, 7);
		graph.connect(5, 3, 2);
		graph.connect(6, 4, 20);
		Graph_Algo GA= new Graph_Algo(graph);
		 
		List<Integer> tspTest= new LinkedList<>();
		tspTest.add(1);
		tspTest.add(4);
		tspTest.add(6);
		tspTest.add(5);
		List<node_data> actual= new LinkedList<>();
		actual= GA.TSP(tspTest);
		List<node_data> expected= new LinkedList<>();
		expected.add(n1);
		expected.add(n2);
		expected.add(n5);
		expected.add(n3);
		expected.add(n6);
		expected.add(n4);

		assertEquals(expected, actual);

	}

}
