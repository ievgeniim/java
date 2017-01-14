package graph;

import graph.Vertex;

/**
 * Created by lagger on 08.12.2016.
 */
public class Edge {

    private Vertex destination;

    public Edge(Vertex destination) {
        this.destination = destination;
    }

    public Vertex getDestination() {
        return this.destination;
    }
}
