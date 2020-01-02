package dataStructure;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class DGraph implements graph, Serializable{


	public HashMap <Integer,node_data> verticesMap;
	private HashMap <Integer,HashMap<Integer,edge_data>> edgeMap;
	private int NodeCounter;
	private int edgesCounter;
	int mc;


	public DGraph() {
		verticesMap = new HashMap <Integer,node_data>();
		edgeMap = new HashMap <Integer,HashMap<Integer,edge_data>>();
		NodeCounter = 0;
		edgesCounter = 0;
		mc=0;
	}


	@Override
	public node_data getNode(int key) {
		if(verticesMap.containsKey(key)){
			return verticesMap.get(key);
		} else
			return null;
	}


	@Override
	public edge_data getEdge(int src, int dest) {
		if (edgeMap.containsKey(src) && edgeMap.get(src).containsKey(dest)) {
			return this.edgeMap.get(src).get(dest);
		}
		else {
			return null;
		}
	}


	@Override
	public void addNode(node_data n) throws RuntimeException {
		
			this.verticesMap.put(n.getKey(),n);
		
		mc++;
	}


	@Override
	public void connect(int src, int dest, double w) {
		node_data a = getNode(src);
		node_data b = getNode(dest);
		if(a != null && b!=null) {
			EdgeData e = new EdgeData(this.verticesMap.get(src), this.verticesMap.get(dest), w);
			this.edgeMap.get(src).put(dest,e);
			this.edgesCounter++;
			this.mc++;
		}


	}


	@Override
	public Collection<node_data> getV() {
		return this.verticesMap.values();
	}



	@Override
	public Collection<edge_data> getE(int node_id) {
		if(!this.verticesMap.containsKey(node_id) || this.verticesMap.get(node_id)==null) {
			return null;
		}
		return this.edgeMap.get(node_id).values();

	}


	@Override
	public node_data removeNode(int key) {
		mc++;
		return verticesMap.remove(key);
	}


	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.edgeMap.containsKey(src)) {
			edge_data ans = this.edgeMap.get(src).remove(dest);
			if (ans!= null) {
				mc++;
				this.edgesCounter--;
				
			}
			return ans;
		}
		return null;
	}


	@Override
	public int nodeSize() {
		return this.NodeCounter;
	}

	@Override
	public int edgeSize() {
		return this.edgesCounter;
	}

	@Override
	public int getMC() {
		return this.mc;
	}


}