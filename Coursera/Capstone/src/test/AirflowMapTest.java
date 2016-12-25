package test;

import capstone.graph.AirflowMap;
import capstone.utils.DataLoader;
import capstone.utils.RouteStorage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


        mainMap.getAirportEgonet(1);


    }
}
