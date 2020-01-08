package gui;

import algorithms.Graph_Algo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;





public class graphGUI extends JFrame implements ActionListener
{

	private graph graph;
	//private static final long serialVersionUID = 1L;
	//LinkedList<Point3D> points = new LinkedList<Point3D>();

	public graphGUI(){
		this.graph =null;
		InitGui();
	}

	public graphGUI(graph g)
	{
		this.graph=g;
		InitGui();
	}

	
	/**
	  we creates the menu bar of the graph 
	  this contains file(save,load) and algortihms(is connected ,tsp,shortestpast,Shortest Path Distance)
	 */
	private void InitGui() 
	{
		this.setSize(500, 300);

		MenuBar menuBar = new MenuBar();
		Menu File = new Menu("File");
		menuBar.add(File);
		Menu Graph_Algorithms = new Menu("Graph Algorithm");
		menuBar.add(Graph_Algorithms);
		this.setMenuBar(menuBar);

		MenuItem load = new MenuItem("Load");
		load.addActionListener(this);
		File.add(load);
		MenuItem save = new MenuItem("Save");
		save.addActionListener(this);
        File.add(save);
		

		

		MenuItem isGraphConnected = new MenuItem("Is Graph Connected");
		isGraphConnected.addActionListener(this);
		Graph_Algorithms.add(isGraphConnected);
		MenuItem shortestPathDist = new MenuItem("Shortest Path Distance");
		shortestPathDist.addActionListener(this);
		Graph_Algorithms.add(shortestPathDist);
		MenuItem shortestPath = new MenuItem("Shortest Path");
		shortestPath.addActionListener(this);
		Graph_Algorithms.add(shortestPath);
		MenuItem printgraph = new MenuItem("Refresh ");
		printgraph.addActionListener(this);
		Graph_Algorithms.add(printgraph);
		
		
	}

	

	public void paint(Graphics g)
	{
		super.paint(g);
		Collection<node_data> collection = graph.getV();
		
		for (node_data i : collection) {
			Point3D location = i.getLocation();
			g.setColor(Color.CYAN);

			g.drawString("" + i.getKey(), location.ix()+5, location.iy()+50);
			g.fillOval(location.ix()+12, location.iy()+52, 12, 12);
		}
		Graphics2D g1 = (Graphics2D) g;
		for (node_data i : collection) {
			
			Collection<edge_data> collection1 = graph.getE(i.getKey());
			for (edge_data j : collection1) {
				
				Point3D p1 = i.getLocation();
				Point3D p2 = this.graph.getNode(j.getDest()).getLocation();
				g1.setStroke(new BasicStroke(2));
				g.setColor(Color.black);
				g1.drawLine(p1.ix()+20, p1.iy()+60, p2.ix()+20, p2.iy()+60);
				g.drawString("" + j.getWeight(), ((p1.ix() + p2.ix())/2),  ((p1.iy() + p2.iy())/2));
				g.setColor(Color.green);
				int x = (p1.ix() - p2.ix())/2;
				int y = (p1.iy() - p2.iy())/2;
				g.fillOval(p2.ix() + x +12, p2.iy() + y +52, 12, 12);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String input = e.getActionCommand();

		switch(input) {
		case "Refresh " : repaint();
		break;
		case "Save":
	    Graph_Algo graphalgo = new Graph_Algo();
	    graphalgo.init(this.graph);
	    JFrame Frame = new JFrame();
		
		String FileName = JOptionPane.showInputDialog(Frame,"enter an  name", null);
		if(FileName == null) {
			JOptionPane.showMessageDialog(null, "is not possible");
			return;
		}
		graphalgo.save(FileName);
		
		break;
		
		
		case "Load": 
		JFrame load = new JFrame();
		String file_name = JOptionPane.showInputDialog(load,"enter an  name", null);
		if(file_name == null) {
			JOptionPane.showMessageDialog(null, "is not possible");
			return;
		}
		Graph_Algo graph_algo = new Graph_Algo();
		graph_algo.init(file_name);

		break;
		
		case "Is  Graph Connected": 
			
			Graph_Algo graph_algo2 = new Graph_Algo();
			graph_algo2.init(this.graph);
			if (graph_algo2.isConnected()) {
				JOptionPane.showMessageDialog(null, "graph is connected");
			}
			else {
				JOptionPane.showMessageDialog(null, " graph is not connected");
			}
		
		break;
		case "Shortest Path Distance": 
		JFrame Frame3 = new JFrame();
		Graph_Algo graphalgo3 = new Graph_Algo();
		graphalgo3.init(this.graph);
		String source = JOptionPane.showInputDialog(null, " enter the source Node key");
		String destination = JOptionPane.showInputDialog(null, " enter your destination Node key");
	
		try {
			int s = Integer.parseInt(source);
			int d = Integer.parseInt(destination);
		}
		catch (Exception w) {
			throw new RuntimeException("this is not an Integer");
		}
		double ans = graphalgo3.shortestPathDist(Integer.parseInt(source), Integer.parseInt(destination));
		String s = Double.toString(ans);	
		JOptionPane.showMessageDialog(Frame3, "The shortest distance  is : " + s);;
		break;
		
		case "Shortest Path": 
			JFrame Frame4 = new JFrame();
			String s1 = "";
			String source2 = JOptionPane.showInputDialog(null, " enter the source ");
			String destination2 = JOptionPane.showInputDialog(null, " enter your destination ");
			Graph_Algo graphalgo4 = new Graph_Algo();
			graphalgo4.init(this.graph);
			int src2=0;
			int dest2=0;
			
			if (!source2.equals(destination2)) {
				try {
				 src2 = Integer.parseInt(source2);
				 dest2 = Integer.parseInt(destination2);
				}
				catch (Exception z) {
					throw new RuntimeException("One of your inputs src/dest is not an Integer");
				}
				List<node_data> ans1 = graphalgo4.shortestPath(src2, dest2);
				String Ans = "";
				for (node_data i : ans1) {
					Ans = Ans + "," + i.getKey();
				}
				Ans = (String) Ans.subSequence(1,Ans.length());
				JOptionPane.showMessageDialog(null, "the shortest Path is:\n" + Ans);
			}
				break;
			

			
		
		
		
		default:
			break;
		
	
	
}}}