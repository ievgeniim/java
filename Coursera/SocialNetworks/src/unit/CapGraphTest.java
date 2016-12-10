package unit;

import graph.CapGraph;
import graph.Graph;
import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by lagger on 09.12.2016.
 */
public class CapGraphTest {

    @org.junit.Test
    public void setUp() throws Exception {

        CapGraph graph = new CapGraph();

        int[] id = {18,23,25,32,44,50,65};

        for (int i : id) {
            graph.addVertex(i);
        }

        graph.addEdge(32,44);
        graph.addEdge(32,50);
        graph.addEdge(44,50);
        graph.addEdge(18,44);
        graph.addEdge(18,23);
        graph.addEdge(23,18);
        graph.addEdge(23,25);
        graph.addEdge(25,23);
        graph.addEdge(25,65);
        graph.addEdge(65,23);


        //GraphLoader.loadGraph(graph,"d:\\Git\\Coursera\\SocialNetworks\\data\\small_test_graph.txt");
        //graph.drawGraph();

        Graph ego = new CapGraph();
        ego = graph.getEgonet(23);

        HashMap<Integer,HashSet<Integer>>  roadMap = graph.exportGraph();

        for (int g : roadMap.keySet()) {
            System.out.println (g + " : " +  roadMap.get(g));
        }

        graph.getSCCs();

    }

}