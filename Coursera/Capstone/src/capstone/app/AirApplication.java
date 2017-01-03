package capstone.app;

import capstone.graph.AirflowMap;
import capstone.graph.Airport;
import capstone.graph.Route;
import capstone.utils.DataLoader;
import capstone.utils.RouteStorage;

import java.io.IOException;
import java.util.*;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class AirApplication {

    private static  AirflowMap map = new AirflowMap();

    public static void main (String args[]) {

        populateMapWithData();

        AirflowMap largestSet = map.getLargestSetOfAirports();
        System.out.println("Largest set has: " + largestSet.getNumberOfAirports() + " airports and " + largestSet.getNumberOfRoutes() + " routes.");

        Set<Airport> dominants = map.getDominantSet();

        System.out.println("Greedy dominant set has : " + dominants.size() + " airports.");

        if (args.length > 0 && args[0].equals("verbose")) {
            System.out.println("List of Airports :");
            for (Airport a : dominants) {
                System.out.println(a.getAirportDetails().get(0));
            }
        }

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Airline Id (1-6048) : ");
        int airlineId = Integer.parseInt(reader.next());

        Set<Route> airlineRoutes = map.getAirlineRoute(airlineId);

        System.out.println("Airline " + airlineId + " has " + airlineRoutes.size() + " routes");
    }

    private static void populateMapWithData(){

        HashMap<Integer, String> airlines;
        HashMap<Integer, ArrayList<String>> airports;
        HashMap<RouteStorage, Integer> routes;

        try {
            airlines = DataLoader.loadAirlines();
            airports = DataLoader.loadAirports();
            routes = DataLoader.loadRoutes();

            for (Map.Entry<Integer, String> entry : airlines.entrySet()) {
                map.addAirline(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<Integer, ArrayList<String>> entry : airports.entrySet()) {
                map.addAirport(entry.getKey(), entry.getValue());
            }

            for (Map.Entry<RouteStorage, Integer> entry : routes.entrySet()) {
                map.addRoute(entry.getKey().getSourceId(), entry.getKey().getDestinationId(), entry.getValue());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
