package test;

import capstone.graph.Airflow;
import capstone.graph.AirflowMap;
import capstone.graph.Airport;
import capstone.utils.DataLoader;
import capstone.utils.RouteStorage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ievgenii Martynenko on 24.12.2016.
 */

public class AirflowMapTest {

    private long startTime;
    private long endTime;
    private final NumberFormat formatter = new DecimalFormat("#0.00000");

    @org.junit.Test
    public void setUp() throws Exception {

        AirflowMap mainMap = new AirflowMap();

        HashMap<Integer,String> airlines = DataLoader.loadAirlines();

        System.out.println("Loading Airlines onto Map");

        startTime = System.currentTimeMillis();

        for (Map.Entry<Integer,String> entry : airlines.entrySet()) {
            mainMap.addAirline(entry.getKey(),entry.getValue());
        }

        endTime = System.currentTimeMillis();
        System.out.println("Airlines loaded onto Map: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        HashMap<Integer,ArrayList<String>> airports = DataLoader.loadAirports();

        System.out.println("Loading Airports onto Map");

        startTime = System.currentTimeMillis();

        for (Map.Entry<Integer,ArrayList<String>> entry : airports.entrySet()) {
            mainMap.addAirport(entry.getKey(),entry.getValue());
        }

        endTime = System.currentTimeMillis();
        System.out.println("Airports loaded onto Map: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        HashMap<RouteStorage,Integer> routes = DataLoader.loadRoutes();

        System.out.println("Loading Routes onto Map");

        startTime = System.currentTimeMillis();

        for (Map.Entry<RouteStorage,Integer> entry : routes.entrySet()) {
            mainMap.addRoute(entry.getKey().getSourceId(),entry.getKey().getDestinationId(),entry.getValue());
        }

        endTime = System.currentTimeMillis();
        System.out.println("Routes loaded onto Map: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        System.out.println("Getting largest sets of airports");
        startTime = System.currentTimeMillis();

        AirflowMap largestSet = mainMap.getLargestSetOfAirports();
        endTime = System.currentTimeMillis();

        System.out.println("Largest set has: " + largestSet.getNumberOfAirports() + " airports and " + largestSet.getNumberOfRoutes() + " routes.");
        System.out.println("Largest set loaded: " + formatter.format((endTime - startTime)/1000d) + " seconds.");


        System.out.println("Getting Greedy dominant set");
        startTime = System.currentTimeMillis();

        Set<Airport> dominants = mainMap.getGreedyDominantSet();
        endTime = System.currentTimeMillis();

        System.out.println("Greedy dominant set has : " + dominants.size() + " airports.");
        System.out.println("Greedy algorithm completed in : " + formatter.format((endTime - startTime)/1000d) + " seconds.");

    }
}
