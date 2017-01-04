package capstone.graph;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */
public interface Airflow {

    public void addRoute(int sourceAirportId, int destinationAirportId, int airlineOwnerId);
    public void addAirport(int airportId, ArrayList<String> details);
    public void addAirline(int airlineId, String airlineName);

    public AirflowMap getLargestSetOfAirports();
    public HashSet<Airport> getDominantSet();
    public HashSet<Route> getAirlineRoute(int airlineId);

}
