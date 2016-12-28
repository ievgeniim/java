package capstone.graph;

import java.util.*;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class AirflowMap implements Airflow {

    private HashMap<Integer,Airport> airports;
    private HashMap<Integer,Airline> airlines;

    public AirflowMap() {

        airports = new HashMap<Integer,Airport>();
        airlines = new HashMap<Integer,Airline>();

    }

    /**
     * Add Airport into map
     * @param airportId - airport id - the first column in airports.csv
     * @param details - details - String list for all columns except last 4 in airports.csv
     */

    public void addAirport(int airportId, ArrayList<String> details) {
        this.airports.put(airportId, new Airport(airportId, details));
    }

    /**
     * Add route between airports. Airport should be already present.
     * @param sourceAirportId - the airportId the first column in airports.csv
     * @param route - instance of Route class
     * @throws NullPointerException - returned when route is added to not existing airport or airline.
     */

    public void addRoute(int sourceAirportId, Route route) throws  NullPointerException {

        if (!checkAirportIsOnMap(sourceAirportId)) {
            throw new NullPointerException("Source Airport " + sourceAirportId + " is not listed on Airflow Map");
        }

        this.airports.get(sourceAirportId).addRoute(route);
    }

    /**
     * Overload of addRoute; instead of getting instance of Route, it accepts destinationId and ownerId
     * @throws NullPointerException returned and route is added to not existing airport or airline.
     */

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

    /**
     * Added airline from file airlines.csv
     * @param airlineId
     * @param airlineName
     */

    public void addAirline(int airlineId, String airlineName) {
        this.airlines.put(airlineId, new Airline(airlineId,airlineName));
    }


    /**
     * For each airport method builds Egonet doing private calls and returns the largest Egonet (by quantity or Airports)
     * @return instance of this class as new map which largest Egonet.
     */

    public AirflowMap getLargestSetOfAirports() {

        AirflowMap largestSet = new AirflowMap();

        for (Airport airport : this.airports.values()) {

            AirflowMap airportSet = this.getAirportEgonet(airport);

            if (airportSet.getNumberOfAirports() > largestSet.getNumberOfAirports()) {
                largestSet = airportSet;
            }
        }

        return largestSet;
    }


    /**
     * @return sum of routes for all Airports on map
     */

    public int getNumberOfRoutes() {
        int routesSum = 0;

        for (Airport a : this.airports.values()) {
            routesSum += a.getRoutes().size();
        }

        return routesSum;
    }

    /**
     *
     * @return
     */

    public HashSet<Airport> getGreedyDominantSet() {

        Set<Airport> dominantAirports = new HashSet<Airport>();
        Set<Airport> airportsToCheck = convertMapToSet(this.airports.values());

        Airport largest;

        while (!airportsToCheck.isEmpty()) {

            largest = new Airport();

            for (Airport a : airportsToCheck) {
                if (a.getRoutes().size() > largest.getRoutes().size()) {
                    largest = a;
                }
            }

            dominantAirports.add(largest);
            airportsToCheck.remove(largest);

            for (Airport a : largest.getNeighbours()) {
                airportsToCheck.remove(a);
            }

        }
        return new HashSet<Airport>(dominantAirports);
    }


    public int getNumberOfAirports() {
        return this.airports.size();
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

    private HashSet<Airport> convertMapToSet(Collection<Airport> airports) {
        Set<Airport> set = new HashSet<Airport>();

        for (Airport a : this.airports.values()) {
            if (!a.getRoutes().isEmpty()) {
                set.add(a);
            }
        }
        return new HashSet<Airport>(set);
    }

}
