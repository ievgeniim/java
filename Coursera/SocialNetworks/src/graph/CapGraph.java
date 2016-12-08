/**
 * 
 */
package graph;

import java.util.*;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	private Map<Integer,Vertex> vertices;
	private int numberOfEdges;

	public CapGraph() {
		this.numberOfEdges = 0;
		vertices = new HashMap<Integer,Vertex>();
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		this.vertices.put(num, new Vertex());
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if (checkVertices(from,to)) {
			vertices.get(from).addEdge(vertices.get(to));
			this.numberOfEdges++;
		}
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/*  Before adding edge we need to be sure that
	 *  both source and destination vertices exist
	 */
	private boolean checkVertices(int source, int destination) {
		return (this.vertices.containsKey(source) && this.vertices.containsKey(destination));
	}

	/*  Method to show graph
	 */

	public void drawGraph() {

		System.out.print("Graph has " + this.vertices.size() + " vertices and " + this.numberOfEdges + " edges");

		for (Map.Entry<Integer,Vertex> entry : vertices.entrySet()) {
			for (Edge e : entry.getValue().getEdges()) {
				System.out.print("Graph " + entry.getValue() + "is connected to " + e.getDestination());
			}
		}

	}

}
