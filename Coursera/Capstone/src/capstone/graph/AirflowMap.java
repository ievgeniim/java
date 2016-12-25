package capstone.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class AirflowMap implements Airflow {

    private HashMap<Integer,Airport> airports;
    private HashMap<Integer,Airline> airlines;
    private HashSet<Airport> visited;

    public AirflowMap() {

        airports = new HashMap<Integer,Airport>();
        airlines = new HashMap<Integer,Airline>();

    }

    public void addAirport(int airportId, ArrayList<String> details) {
        this.airports.put(airportId, new Airport(airportId, details));
    }

    public void addAirport(Airport airport) {
        this.airports.put(airport.getAirportId(),airport);
    }

    public void addRoute(int sourceAirportId, Route route) throws  NullPointerException {

        if (!checkAirportIsOnMap(sourceAirportId)) {
            throw new NullPointerException("Source Airport " + sourceAirportId + " is not listed on Airflow Map");
        }

        this.airports.get(sourceAirportId).addRoute(route);
    }


    public void addRoute(int sourceAirportId, int destinationAirportId, int airlineOwnerId) throws NullPointerException {

        if (!checkAirportIsOnMap(sourceAirportId) || !checkAirportIsOnMap(destinationAirportId)) {
            throw new NullPointerException("Source " +  sourceAirportId + " or Destination " + destinationAirportId + " Airport are not listed on Airflow Map");
        }

        if (!checkAirlineIsOnMap(airlineOwnerId)) {
            throw new NullPointerException("Airline " + airlineOwnerId  + " is not listed on Airflow Map");
        }

        Route route = new Route(this.airports.get(sourceAirportId),this.airports.get(destinationAirportId), this.airlines.get(airlineOwnerId));
        this.airports.get(sourceAirportId).addRoute(route);

    }

    public void addAirline(int airlineId, String airlineName) {
        this.airlines.put(airlineId, new Airline(airlineId,airlineName));
    }


    public AirflowMap getAirportEgonet(int airportId) {
        return this.getAirportEgonet(this.airports.get(airportId));
    }


    private AirflowMap getAirportEgonet(Airport airport) {

        Set<Airport> airports = new HashSet<Airport>();
        AirflowMap airportEgonet = new AirflowMap();

        airportEgonet.addAirport(airport.getAirportId(),airport.getAirportDetails());
        airports.add(airport);

        // get all the neighbours of the ego Airport without routes;
        for (Airport neighbours : airport.getNeighbours()) {
            airports.add(neighbours);
            airportEgonet.addAirport(neighbours.getAirportId(),neighbours.getAirportDetails());
        }


        //traverse through the set of the airports and add only interconnected routes to new AirflowMap
        for (Airport a : airports) {
            for (Route r : a.getRoutes()) {
                if (airports.contains(r.getSourceAirport()) && airports.contains(r.getDestinationAirport())) {
                    airportEgonet.addRoute(r.getSourceAirport().getAirportId(),r);
                }
            }
        }

        return airportEgonet;
    }



    private boolean checkAirportIsOnMap(int airportId) {
        return this.airports.containsKey(airportId);
    }

    private boolean checkAirlineIsOnMap(int airlineId) {
        return this.airlines.containsKey(airlineId);
    }

}
