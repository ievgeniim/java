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
		this.vertices.put(num, new Vertex(num));
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

		List<Vertex> egoVertices = new ArrayList<Vertex>();
		Graph egonet = new CapGraph();

		egonet.addVertex(center);
		egoVertices.add(vertices.get(center));

		for (Edge e : this.vertices.get(center).getEdges()) {
			egoVertices.add(e.getDestination());
			egonet.addVertex(e.getDestination().getVertexId());
		}

		for (Vertex v : egoVertices) {
			egonet.addVertex(v.getVertexId());

			for (Edge e : v.getEdges()) {
				if (egoVertices.contains(e.getDestination())) {
					egonet.addEdge(v.getVertexId(),e.getDestination().getVertexId());
				}
			}
		}

		return egonet;
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

		System.out.println("Graph has " + this.getSize() + " vertices and " + this.numberOfEdges + " edges");

		for (Map.Entry<Integer,Vertex> entry : vertices.entrySet()) {
			for (Edge e : entry.getValue().getEdges()) {
				System.out.println("	Vertex " + entry.getKey() + " is connected to " + e.getDestination().getVertexId());
			}
		}

	}

	public int getSize() {
		return  this.vertices.size();
	}

}
