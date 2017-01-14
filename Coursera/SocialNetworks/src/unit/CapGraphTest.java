package unit;

import graph.CapGraph;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import util.GraphLoader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

        System.out.println("Normal graph");
        HashMap<Integer,HashSet<Integer>>  roadMap = graph.exportGraph();

        for (int g : roadMap.keySet()) {
            System.out.println (g + " : " +  roadMap.get(g));
        }

        System.out.println("Transposed graph");

        HashMap<Integer,HashSet<Integer>>  transposedMap = graph.transposeGraph().exportGraph();

        for (int g : transposedMap.keySet()) {
            System.out.println (g + " : " +  transposedMap.get(g));
        }

        System.out.println("SCCs");
        List<Graph> sccs = graph.getSCCs();

        HashMap<Integer,HashSet<Integer>>  sccMap;

        for (Graph g : sccs) {
            System.out.println("=========");
            sccMap = g.exportGraph();
            for (int i : sccMap.keySet()) {
                System.out.println (i + " : " +  sccMap.get(i));
            }
        }



    }

}