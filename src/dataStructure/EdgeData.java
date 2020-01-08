package dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class EdgeData implements edge_data, Serializable {
	private double weight;
	private node_data src;
	private node_data dest;
	private String info;
	private int tag;
	
	/**
	 * constractor
	 */
	public EdgeData() {
		this.src = null;
		this.dest = null;
		this.weight = 0;
		this.info = "";
		this.tag = 0;
	}
	
	/**
	 * constractor
	 */
	public EdgeData(node_data src, node_data dest,double weight) {
		this.src= src;
		this.dest = dest;
		this.weight = weight;
		
	}
	/**
	 * The id of the source node of this edge.
	 * @return
	 */
	
	@Override
	public int getSrc() {
		return this.src.getKey();
	}

	/**
	 * The id of the destination node of this edge
	 * @return
	 */
	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	/**
	 * @return the weight of this edge (positive value).
	 */
	@Override
	public double getWeight() {
		return this.weight;
	}

	/**
	 * return the remark (meta data) associated with this edge.
	 * @return
	 */
	@Override
	public String getInfo() {
		return this.info;
	}
	/**
	 * Allows changing the remark (meta data) associated with this edge.
	 * @param s
	 */
	
	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	/**
	 * Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms 
	 * @return
	 */
	@Override
	public int getTag() {
		return this.tag;
	}

	/** 
	 * Allow setting the "tag" value for temporal marking an edge - common 
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag
	 */
	@Override
	public void setTag(int t) {
		this.tag = t;
	}
	

}