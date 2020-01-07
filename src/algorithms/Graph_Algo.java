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
//		if(!isConnected2Nodes(_graph.getNode(src), _graph.getNode(dest))) {
//			return Double.POSITIVE_INFINITY;
//		}
		Collection <node_data> nodes= _graph.getV();
		for (node_data currNode : nodes) {
			currNode.setTag(0);
			currNode.setWeight(Double.POSITIVE_INFINITY);
		}
		node_data srcNode= _graph.getNode(src);
		srcNode.setWeight(0);
		shortestPathDistRe(src);
		System.out.println("bla");
		double ans= _graph.getNode(dest).getWeight();
		
		return ans;
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
		if(!isConnected2Nodes(_graph.getNode(src), _graph.getNode(dest)))return null;
		Collection <node_data> nodes= _graph.getV();
		for (node_data currNode : nodes) {
			currNode.setTag(0);
			currNode.setWeight(Double.POSITIVE_INFINITY);
			currNode.setInfo("");
		}
		node_data srcNode= _graph.getNode(src);
		srcNode.setWeight(0);
		srcNode.setInfo(""+src);
		shortestPathDistRe(src);
		node_data destNode= _graph.getNode(dest);
		String keysOfNodes= destNode.getInfo();
		List<node_data> ans = new LinkedList<node_data>();
		StringTokenizer tokenizer = new StringTokenizer(keysOfNodes);
		
		while (tokenizer.hasMoreTokens()) {
			node_data curNod = _graph.getNode(Integer.parseInt(tokenizer.nextToken(",")));
			ans.add(curNod);
		}
		return ans;
	}

	public void shortestPathDistRe(int src) {
		node_data srcNode= _graph.getNode(src);
		if(srcNode.getTag()==1)return;
		if( _graph.getE(src)==null) {
			srcNode.setTag(1);
			return;
		}
		Collection<edge_data> edges= _graph.getE(src);
		srcNode.setTag(1);
		for (edge_data currEdge : edges) {
			System.out.println(currEdge);
			double curWeight= srcNode.getWeight()+currEdge.getWeight();
			node_data nextNode= _graph.getNode(currEdge.getDest());
			System.out.println(nextNode);
			if(nextNode.getWeight()>curWeight) {
				nextNode.setWeight(curWeight);
				nextNode.setInfo(srcNode.getInfo() + "," + nextNode.getKey());
			}
			System.out.println(nextNode+ " weight:"+ nextNode.getWeight());
		}
		int nextKey= smallestWeight();
		if(nextKey==-1) return;
		shortestPathDistRe(nextKey);
	}
	
	private boolean isConnected2Nodes(node_data src, node_data des) {
		if(_graph.getE(src.getKey())==null) {
			return false;
		}
		
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
		
		if(_graph.getE(src.getKey()) == null) {
			src.setTag(1);
			return false;
		}
		
		Collection<edge_data> edges = _graph.getE(src.getKey());
		src.setTag(1);
		for (edge_data edge_data : edges) {
			if(isConnected2NodesRe(_graph.getNode(edge_data.getDest()),des))return true;
		}
		return false;
	}
	
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if(! isAllTragetsConnected(targets)) {
			return null;
		}
		List<node_data> ans= new LinkedList<>();
		node_data currNode= _graph.getNode(targets.get(0)); //the first node in the list
		while(!targets.isEmpty()) {
			
		}
		
		return null;
	}
	
	public node_data mostCloseNode (List<node_data> list, node_data curNode ) {
		node_data ans=new NodeData();
		double minWeight= Double.POSITIVE_INFINITY;
		for(node_data nextNode: list) {
			double curWeight=Double.POSITIVE_INFINITY;
			if(curNode.getKey() != nextNode.getKey()) {
				curWeight= this.shortestPathDist(curNode.getKey(), nextNode.getKey());
			}
			if(curWeight<minWeight) {
				minWeight=curWeight;
				ans=nextNode;
			}
		}
		return ans;
	}
	//this method checking if all targets are connected 
	public boolean isAllTragetsConnected(List<Integer> targets) {
		for(int i=0; i<targets.size()-1; i++) {
			for(int j=i+1; j<targets.size();  j++ ) {
				if(! isConnected2Nodes(_graph.getNode(targets.get(i)), _graph.getNode(targets.get(j)))) {
					return false;
				}
			}
		}
		return true;
	}
	public graph getGraph() {
		return this._graph;
	}
	
	public static void main(String[]args) {
		graph graphConnected= new DGraph();
		NodeData n1= new NodeData(1,5,10);
		NodeData n2= new NodeData(5,20,0);
		NodeData n3= new NodeData(3,40,50);
		NodeData n4= new NodeData(10,3,15);
		NodeData n5= new NodeData(0,0,0);
		NodeData n6= new NodeData(20,5,3);
		graphConnected.addNode(n1);
	
		graphConnected.addNode(n2);
		graphConnected.addNode(n3);
		graphConnected.addNode(n4);
		graphConnected.addNode(n5);
		graphConnected.addNode(n5);
		graphConnected.addNode(n6);

		graphConnected.connect(n1.getKey(), n2.getKey(), 2);
		graphConnected.connect(n2.getKey(), n3.getKey(), 5);
		graphConnected.connect(n3.getKey(), n4.getKey(), 2);
		graphConnected.connect(n4.getKey(), n5.getKey(), 4);
		graphConnected.connect(n5.getKey(), n6.getKey(), 2);
		graphConnected.connect(n4.getKey(), n6.getKey(), 3);

		Graph_Algo testUnit= new Graph_Algo(graphConnected);
		System.out.println(testUnit.isConnected2Nodes(n1, n4));
//		System.out.print(graphConnected.getNode(n6.getKey()).getWeight());

//		List<node_data> test= testUnit.shortestPath(n1.getKey(), n5.getKey());
//		List<Integer> keysList= new LinkedList<>();
//		for(int i=0; i<test.size(); i++) {
//			keysList.add(test.get(i).getKey());
//		}
//		System.out.println(""+testUnit.isAllTragetsConnected(keysList));
//		graph newtest= new DGraph();
//		
//		newtest.addNode(n1);
//		newtest.addNode(n2);
//		newtest.addNode(n3);
//		newtest.addNode(n4);
//		newtest.addNode(n5);
//		newtest.connect(n1.getKey(), n2.getKey(), 9);
//		newtest.connect(n4.getKey(), n5.getKey(), 7);
//		List<Integer> testList= new LinkedList<>();
//		testList.add(n1.getKey());
//		testList.add(n2.getKey());
//		testList.add(n3.getKey());
//		System.out.println(""+testUnit.isAllTragetsConnected(testList));
//		List<node_data> list= new LinkedList<>();
//		list.add(n2);
//		list.add(n5);
//		list.add(n6);
//		System.out.println(testUnit.isConnected2Nodes(n1, n6));
//		System.out.println(testUnit.mostCloseNode(list, n2));
		
		
	}
}