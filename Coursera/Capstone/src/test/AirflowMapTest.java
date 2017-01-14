package test;

import capstone.graph.*;
import capstone.utils.DataLoader;
import capstone.utils.RouteStorage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ievgenii Martynenko on 24.12.2016.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirflowMapTest {

    private long startTime;
    private long endTime;
    private final NumberFormat formatter = new DecimalFormat("#0.00000");
    private static AirflowMap mainMap;

    private static HashMap<Integer, String> airlines;
    private static HashMap<Integer, ArrayList<String>> airports;
    private static HashMap<RouteStorage, Integer> routes;


    @org.junit.Before
    public void setUp() {
        mainMap = new AirflowMap();
    }


    @org.junit.Test
    public void a_DataIntoMemory() throws Exception {

        System.out.println("Loading Airlines into memory");
        startTime = System.currentTimeMillis();

        airlines = DataLoader.loadAirlines();

        endTime = System.currentTimeMillis();
        System.out.println("Airlines loaded in : " + formatter.format((endTime - startTime) / 1000d) + " seconds.");

        assertEquals(6048,airlines.size());

        System.out.println("Loading Airports into memory");
        startTime = System.currentTimeMillis();

        airports = DataLoader.loadAirports();

        endTime = System.currentTimeMillis();
        System.out.println("Airlines loaded in : " + formatter.format((endTime - startTime) / 1000d) + " seconds.");

        assertEquals(8107,airports.size());

        System.out.println("Loading Routes into memory");

        startTime = System.currentTimeMillis();
        routes = DataLoader.loadRoutes();

        endTime = System.currentTimeMillis();
        System.out.println("Routes loaded into memory: " + formatter.format((endTime - startTime) / 1000d) + " seconds.");

        assertEquals(66548,routes.size());

    }

    @org.junit.Test
    public void b_PopulateMap () throws Exception {

        System.out.println("Loading Airlines onto Map");
        startTime = System.currentTimeMillis();

        for (Map.Entry<Integer, String> entry : airlines.entrySet()) {
            mainMap.addAirline(entry.getKey(), entry.getValue());
        }

        endTime = System.currentTimeMillis();
        System.out.println("Airlines loaded onto Map: " + formatter.format((endTime - startTime) / 1000d) + " seconds.");

        assertEquals(6048, mainMap.getNumberOfAirlines());

        System.out.println("Loading Airports onto Map");

        startTime = System.currentTimeMillis();

        for (Map.Entry<Integer, ArrayList<String>> entry : airports.entrySet()) {
            mainMap.addAirport(entry.getKey(), entry.getValue());
        }

        assertEquals(8107, mainMap.getNumberOfAirports());

        endTime = System.currentTimeMillis();
        System.out.println("Airports loaded onto Map: " + formatter.format((endTime - startTime) / 1000d) + " seconds.");


        System.out.println("Loading Routes onto Map");

        startTime = System.currentTimeMillis();

        for (Map.Entry<RouteStorage, Integer> entry : routes.entrySet()) {
            mainMap.addRoute(entry.getKey().getSourceId(), entry.getKey().getDestinationId(), entry.getValue());
        }

        endTime = System.currentTimeMillis();
        System.out.println("Routes loaded onto Map: " + formatter.format((endTime - startTime) / 1000d) + " seconds.");

        assertEquals(66548, mainMap.getNumberOfRoutes());

        System.out.println("Getting largest sets of airports");
        startTime = System.currentTimeMillis();

        AirflowMap largestSet = AirflowMapTest.mainMap.getLargestSetOfAirports();
        endTime = System.currentTimeMillis();

        assertEquals(240, largestSet.getNumberOfAirports());
        assertEquals(19240, largestSet.getNumberOfRoutes());

        System.out.println("Largest set has: " + largestSet.getNumberOfAirports() + " airports and " + largestSet.getNumberOfRoutes() + " routes.");
        System.out.println("Largest set loaded: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        System.out.println("Getting dominant set");
        startTime = System.currentTimeMillis();

        Set<Airport> dominants = mainMap.getDominantSet();
        endTime = System.currentTimeMillis();

        System.out.println("Greedy dominant set has : " + dominants.size() + " airports.");
        System.out.println("Greedy algorithm completed in : " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        assertEquals(1411,dominants.size());

        System.out.println("Getting airline route for Airline 1338");
        startTime = System.currentTimeMillis();

        Set<Route> airlineRoutes = mainMap.getAirlineRoute(1338);
        endTime = System.currentTimeMillis();

        System.out.println("Airline has : " + airlineRoutes.size() + " routes.");
        System.out.println("Algorithm completed in : " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        assertEquals(12,airlineRoutes.size());
    }


}
