package dataStructure;

import utils.Point3D;

import java.io.Serializable;
import java.util.HashMap;

public class NodeData implements node_data, Serializable {
	private int key;
	private double weight;
	private Point3D location;
	private String info;
	private int tag;



	public NodeData() {
		this.key=0;
		this.weight=0;
		this.location=null;
		this.info="";
		this.tag=0;
	}
	public NodeData(int key,double x, double y) {
		this.key = key;
		this.location = new Point3D (x,y);
	}
	
	public NodeData(int key, Point3D location) {
		this.key= key;
		this.location=location;
	}
	
	public int getKey() {
		return this.key;
	}

	
	public Point3D getLocation() {
		return this.location;
	}

	
	public void setLocation(Point3D p) {
		this.location = p;

	}

	
	public double getWeight() {
		return this.weight;
	}

	
	public void setWeight(double w) {
		this.weight = w;
	}


	public String getInfo() {
		return this.info;
	}

	
	public void setInfo(String s) {
		this.info = s;
	}

	
	public int getTag() {
		return this.tag;
	}


	public void setTag(int t) {
		this.tag = t;
	}
	
	public String toString() {
		return ""+this.getKey();
	}
}