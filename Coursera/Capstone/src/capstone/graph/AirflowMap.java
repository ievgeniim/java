package capstone.graph;

import org.jetbrains.annotations.Contract;

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
     * Uses one of the implementation of Greedy Algorithm to traverse through
     * the Airports and find the dominant set
     * @return
     */

    public HashSet<Airport> getDominantSet() {

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


    /**
     *
     * @param airlineId
     * @return a set of all routes for specified Airline Id on current map
     */
    public HashSet<Route> getAirlineRoute(int airlineId) {
        return getAirlineRoute(this.airlines.get(airlineId));
    }

    /**
     * @return count of airlines on map
     */
    public int getNumberOfAirlines() {
        return this.airlines.size();
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
     * @return count of airports on map
     */
    public int getNumberOfAirports() {
        return this.airports.size();
    }

    /**
     * @param airport
     * @return a ego-map which is a submap of current with list of airports/routes/airlines for listed Airport
     */
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

    /**
     * Helper method
     * @param airportId
     * @return true if Airport is already put into the map
     */
    private boolean checkAirportIsOnMap(int airportId) {
        return this.airports.containsKey(airportId);
    }

    /**
     * Helper method
     * @param airlineId
     * @return true if Airline is already put into the map
     */
    private boolean checkAirlineIsOnMap(int airlineId) {
        return this.airlines.containsKey(airlineId);
    }

    /**
     * Helper method. Generic to convert Map (K,V) -> Set(V) for all types of object used in this class
     * @param collection
     * @param <T>
     * @return HashSet of Object Type.
     */
    private <T> HashSet<T> convertMapToSet(Collection<T> collection) {
        Set<T> set = new HashSet<T>();

        for (T obj : collection) {
            if (obj instanceof Airport) {
                if (!(((Airport) obj).getRoutes().isEmpty()))  {
                    set.add(obj);
                }
            } else {
                set.add(obj);
            }
        }

        return new <T> HashSet<T>(set);
    }

    /**
     * Helper method
     * @param airline
     * @return a set of all routes for specified Airline object on current map
     */
    @Contract("_ -> !null")
    private HashSet<Route> getAirlineRoute(Airline airline) {

        Set<Route> mapRoutes = new HashSet<Route>();
        Set<Route> airlineRoutes = new HashSet<Route>();

        for (Airport a : this.airports.values()) {
            mapRoutes.addAll(a.getRoutes());
        }

        for (Route route : mapRoutes) {
            if (route.getRouteOwner().compareTo(airline) == 0) {
                airlineRoutes.add(route);
            }
        }

        return new HashSet<Route>(airlineRoutes);
    }
}
