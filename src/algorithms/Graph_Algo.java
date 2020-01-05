package algorithms;
import java.io.*;
import java.util.*;

import dataStructure.*;


public class Graph_Algo implements graph_algorithms{
	private graph _graph;
	
	//constructors
	public Graph_Algo(graph g) {
		this.init(g);
	}
	@Override
	public void init(graph g) {
		_graph =  g;	
	}
	
	@Override
	public graph copy() {
		graph copy=(graph) new DGraph();
		Collection<node_data> nodes= _graph.getV();
		for ( node_data node_data: nodes) {
			node_data temp= new NodeData(node_data.getKey(), node_data.getLocation());
			copy.addNode(temp);
			Collection<edge_data> edges= _graph.getE(node_data.getKey());
			for (edge_data edge_data : edges) {
				int src= node_data.getKey();
				int dest=edge_data.getDest();
				double weight= edge_data.getWeight();
				copy.connect(src, dest, weight);
			}
		}
		
		return copy;
	}

	@Override
	public void init(String file_name) {
		
		try
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			_graph = (graph)in.readObject();

			in.close();
			file.close();

		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}
	}

	@Override
	public void save(String file_name) {
		
		try{    
			FileOutputStream file = new FileOutputStream(new File(file_name)); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			out.writeObject(this); 
			out.close(); 
			file.close(); 
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}	
	}


	public boolean isConnected() {
		Collection <node_data> nodes= _graph.getV();
		for (node_data node_data : nodes) {
			for (node_data node_data2 : nodes) {
				if(node_data.getKey()== node_data2.getKey());
				else {
					if (!isConnected2Nodes(node_data, node_data2))return false;
				}
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		if(!this.isConnected())return -1.0;
		Collection <node_data> nodes= _graph.getV();
		for (node_data node_data : nodes) {
			node_data.setTag(0);
			node_data.setWeight(Double.POSITIVE_INFINITY);
		}
		node_data node= _graph.getNode(src);
		node.setWeight(0);
		shortestPathDistRe(src);
		double ans= _graph.getNode(dest).getWeight();
		
		return ans;
	}

	public void shortestPathDistRe(int src) {
		node_data node= _graph.getNode(src);
		if(node.getTag()==1)return;
		Collection<edge_data> edges= _graph.getE(src);
		node.setTag(1);
		for (edge_data edge_data : edges) {
			double curWeight= node.getWeight()+edge_data.getWeight();
			node_data curNode= _graph.getNode(edge_data.getDest());
			if(curNode.getWeight()>curWeight) {
				curNode.setWeight(curWeight);
				curNode.setInfo(node.getInfo() + "," + curNode.getKey());
			}
		}
		int nextKey= smallestWeight();
		if(nextKey==-1) return;
		shortestPathDistRe(nextKey);
	}
	
	public int smallestWeight() {
		Collection <node_data> nodes= _graph.getV();
		double lowestWeight=Double.POSITIVE_INFINITY;
		int ans=-1;
		for (node_data node_data : nodes) {
			if(node_data.getWeight()<lowestWeight && node_data.getTag()!=1) {
				lowestWeight= node_data.getWeight();
				ans= node_data.getKey();
			}
		}
		return ans;
	}
		
	@Override
	public List<node_data> shortestPath(int src, int dest) {
		if(!this.isConnected())return null;
		Collection <node_data> nodes= _graph.getV();
		for (node_data node_data : nodes) {
			node_data.setTag(0);
			node_data.setWeight(Double.POSITIVE_INFINITY);
			node_data.setInfo("");
		}
		node_data node= _graph.getNode(src);
		node.setWeight(0);
		node.setInfo(""+src);
		shortestPathDistRe(src);
		node_data destNode= _graph.getNode(dest);
		String keysOfNodes= destNode.getInfo();
		LinkedList<node_data> ans = new LinkedList<node_data>();
		StringTokenizer aT = new StringTokenizer(keysOfNodes);
		
		while (aT.hasMoreTokens()) {
			node_data curNod = _graph.getNode(Integer.parseInt(aT.nextToken(",")));
			ans.add(curNod);
		}
		return ans;
	}

	
	private boolean isConnected2Nodes(node_data src, node_data des) {
		Collection <node_data> nodes= _graph.getV();
		for (node_data node_data : nodes) {
			node_data.setTag(0);
		}
		
		Collection<edge_data> edges = _graph.getE(src.getKey());
		src.setTag(1);
		for (edge_data edge_data : edges) {
			if(isConnected2NodesRe(_graph.getNode(edge_data.getDest()),des))return true;
		}
		return false;
	}



	private boolean isConnected2NodesRe(node_data src, node_data des) {
		if(src.getKey()==des.getKey()) return true;
		if(src.getTag()==1) return false;
		Collection<edge_data> edges = _graph.getE(src.getKey());
		src.setTag(1);
		for (edge_data edge_data : edges) {
			if(isConnected2NodesRe(_graph.getNode(edge_data.getDest()),des))return true;
		}
		return false;
	}
	
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[]args) {
		graph graphConnected= new DGraph();
		NodeData n1= new NodeData(1,5,10);
		NodeData n2= new NodeData(5,20,0);
		NodeData n3= new NodeData(3,40,50);
		NodeData n4= new NodeData(10,3,15);
		NodeData n5= new NodeData(0,0,0);
		graphConnected.addNode(n1);
	
		graphConnected.addNode(n2);
		graphConnected.addNode(n3);
		graphConnected.addNode(n4);
		graphConnected.addNode(n5);
		graphConnected.connect(n1.getKey(), n2.getKey(), 0);
		graphConnected.connect(n2.getKey(), n3.getKey(), 0);
		graphConnected.connect(n3.getKey(), n4.getKey(), 0);
		graphConnected.connect(n4.getKey(), n5.getKey(), 0);
		graphConnected.connect(n5.getKey(), n1.getKey(), 0);
		Graph_Algo testUnit= new Graph_Algo(graphConnected);
		System.out.println("Is connedted Graph? "+ testUnit.isConnected());

	}
}