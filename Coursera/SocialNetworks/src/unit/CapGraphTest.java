package unit;

import graph.CapGraph;
import graph.Graph;
import util.GraphLoader;

import static org.junit.Assert.*;

/**
 * Created by lagger on 09.12.2016.
 */
public class CapGraphTest {

    @org.junit.Test
    public void setUp() throws Exception {

        CapGraph graph = new CapGraph();
        /*
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1,2);
        graph.addVertex(2);

        graph.addEdge(1,3);
        graph.addVertex(3);
        graph.addEdge(1,3);
        graph.addEdge(3,1);

        graph.addEdge(3,2);
        graph.addEdge(2,3);

        graph.addEdge(4,3);*/

        GraphLoader.loadGraph(graph,"d:\\Git\\Coursera\\SocialNetworks\\data\\small_test_graph.txt");
        graph.drawGraph();

        Graph ego = new CapGraph();
        ego = graph.getEgonet(3);


    }

}