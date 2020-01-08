package dataStructure;


import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
/**
* This interface represents a directional weighted graph.
* The interface has a road-system or communication network in mind - and should support a large number of nodes (over 100,000).
* The implementation should be based on an efficient compact representation (should NOT be based on a n*n matrix).
*
*/

public class DGraph implements graph, Serializable{


	private HashMap <Integer,node_data> verticesMap;
	private HashMap <Integer,HashMap<Integer,edge_data>> edgeMap;
	private int edgesCounter;
	int mc;

	/**
	 * constractor
	 */
	public DGraph() {
		verticesMap = new HashMap <Integer,node_data>();
		edgeMap = new HashMap <Integer,HashMap<Integer,edge_data>>();
		edgesCounter = 0;
		mc = 0;
	}

	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 *
	 * erwerwe
	 */
	@Override
	public node_data getNode(int key) {
		if(verticesMap.containsKey(key)){
			return verticesMap.get(key);
		} else
			return null;
	}
	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return
	 */

	@Override
	public edge_data getEdge(int src, int dest) {
		if (edgeMap.containsKey(src) && edgeMap.get(src).containsKey(dest)) {
			return this.edgeMap.get(src).get(dest);
		}
		else {
			return null;
		}
	}

	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	@Override
	public void addNode(node_data n) throws RuntimeException {
		
			this.verticesMap.put(n.getKey(),n);
		
		mc++;
	}
	/**
	 * Connect an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 */

	@Override
	public void connect(int src, int dest, double w) {
		node_data srcNode = this.getNode(src);
		node_data destNode = this.getNode(dest);
		if(srcNode != null && destNode!=null) {
			if(!this.edgeMap.containsKey(src)) { //first edge
				edgeMap.put(src, new HashMap<Integer,edge_data>());
			}
			edge_data edgeData = new EdgeData(srcNode, destNode, w);
			this.edgeMap.get(src).put(dest, edgeData);
			this.edgesCounter++;
			this.mc++;
		}
	}
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */

	@Override
	public Collection<node_data> getV() {
		return this.verticesMap.values();
	}


	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	@Override
	public Collection<edge_data> getE(int node_id) {
		if(!this.edgeMap.containsKey(node_id) || this.edgeMap.get(node_id)==null) {
			return null;
		}
		return this.edgeMap.get(node_id).values();

	}

	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	@Override
	public node_data removeNode(int key) {
		mc++;
		return verticesMap.remove(key);
	}
	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */

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

	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	@Override
	public int nodeSize() {
		return this.verticesMap.size();
	}
	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	@Override
	public int edgeSize() {
		return this.edgesCounter;
	}
	/**
	 * return the Mode Count - for testing changes in the graph.
	 * @return
	 */
	@Override
	public int getMC() {
		return this.mc;
	}
	public int nodesSize() {
		
		return verticesMap.size();
	}

}