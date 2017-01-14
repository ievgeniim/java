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
	private Set<Vertex> visited;

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


	/*
	* Another method to do that from Graph -> Graph
	 */
	private void addVertex(Vertex v) {
		this.vertices.put(v.getVertexId(),v);
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

		List<Graph> sccs = new ArrayList<Graph>();

		// create DQ from existing list of vertices
		Deque<Vertex> vertices = new ArrayDeque<Vertex>();
		for (Vertex v : this.vertices.values()) {
			vertices.add(v);
		}

		//initialize visited list and run first travel
		visited = new HashSet<Vertex>();
		Deque<Vertex> finished = dfs(vertices);

		//create new transposed graph based on current
		CapGraph transposedGraph = this.dequeToGraph(new ArrayDeque<Vertex>(finished) {
		}).transposeGraph();

		//clear visited list
		visited.clear();

		//go second cycle using order from finished DQ and Transposed Vertices
		while (!finished.isEmpty()) {
			Vertex vertex = finished.poll();
			//exclude those that are visited - we need to do that by ID as these are new Objects
			if (!isVisited(vertex.getVertexId())) {
				//create new Q from all items in finished sequentially
				ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();
				q.add(transposedGraph.vertices.get(vertex.getVertexId()));
				//get DQ as SCC
				Deque<Vertex> scc = dfs(q);
				//puc DQ Vetrices into new graph and add that to List
				sccs.add(dequeToGraph(scc));
			}
		}

		return sccs;
	}

	/*
	 * we need to maintane visited as global, to easier splitting into
	 * scc at the final processing
	 */

	private Deque<Vertex> dfs(Deque<Vertex> vertices) {

		Deque<Vertex> finished = new ArrayDeque<Vertex>();

		Vertex v;
		while (!vertices.isEmpty()) {
			v = vertices.pop();
			if (!visited.contains(v)) {
				dfsVisit(v,visited,finished);
			}
		}

		return finished;
	}


	private void dfsVisit (Vertex v, Set<Vertex> visited, Deque<Vertex> finished) {
		visited.add(v);

		for (Edge e: v.getEdges()) {
			if (!visited.contains(e.getDestination())) {
				dfsVisit (e.getDestination(), visited, finished);
			}
		}

		finished.push(v);
	}

	/*	Transform DQ list of vertices into Graph
	 *
	 */

	private CapGraph dequeToGraph(Deque<Vertex> vertices) {

		CapGraph graph = new CapGraph();

		while (!vertices.isEmpty()) {
			graph.addVertex(vertices.poll());
		}

		return graph;
	}

	/*	Method returns new Graph which is created
	 *	based calling graph vertices;
	 */

	public CapGraph transposeGraph() {

		CapGraph transposed = new CapGraph();

		for (Vertex v : this.vertices.values()) {
			transposed.addVertex(v.getVertexId());
		}

		for (Vertex v : this.vertices.values()) {
			for (Edge e: v.getEdges()) {
				transposed.addEdge(e.getDestination().getVertexId(),v.getVertexId());
			}
		}

		return transposed;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {

		HashMap<Integer,HashSet<Integer>> graph = new HashMap<Integer,HashSet<Integer>>();

		for (Map.Entry<Integer, Vertex> v : this.vertices.entrySet()) {
			HashSet<Integer> neighbours = new HashSet<Integer>();

			for(Edge e : v.getValue().getEdges()) {
				neighbours.add(e.getDestination().getVertexId());
			}

			graph.put(v.getKey(),neighbours);
		}

		return graph;
	}

	/*  Before adding edge we need to be sure that
	 *  both source and destination vertices exist
	 */
	private boolean checkVertices(int source, int destination) {
		return (this.vertices.containsKey(source) && this.vertices.containsKey(destination));
	}


	/*	Convert this graph map of vertices to
	 *	deque, to easier ordering
	 */
	private Deque<Vertex> getDequeVertices() {

		Deque<Vertex> vertices = new ArrayDeque<Vertex>();

		for (Vertex v: this.vertices.values()) {
			vertices.push(v);
		}

		return  vertices;
	}

	/*
	*  Check whether Vertex is visited by it's rather than Object
	* */
	private boolean isVisited(int id) {
		for (Vertex v: this.visited) {
			if (v.getVertexId() == id) {
				return true;
			}
		}
		return false;
	}



}
