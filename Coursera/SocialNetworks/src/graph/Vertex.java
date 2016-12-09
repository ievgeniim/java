package graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lagger on 08.12.2016.
 */
public class Vertex {

    private int vertexId;
    private Set<Edge> edges;

    public Vertex(int vertexId) {
        this.vertexId = vertexId;
        edges = new HashSet<Edge>();
    }

    public int getVertexId () {
        return this.vertexId;
    }

    public void addEdge(Vertex destination) {
        edges.add(new Edge(destination));
    }


    public Set<Edge> getEdges() {
        return this.edges;
    }


}
