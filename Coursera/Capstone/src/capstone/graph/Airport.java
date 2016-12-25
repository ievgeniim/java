package capstone.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class Airport {

    private int airportId;
    private String airportName;
    private String airportCity;
    private String airportCountry;
    private String airportFfa;
    private String airportIcao;
    private float airportLattitude;
    private float airportLongtitude;

    private HashSet<Route> routes;
    private HashSet<Airport> neighbours;

    public Airport(int id, ArrayList<String> details) {

        this.routes = new HashSet<Route>();
        this.neighbours = new HashSet<Airport>();

        this.airportId = id;

        this.airportName = details.get(0);
        this.airportCity = details.get(1);
        this.airportCountry = details.get(2);
        this.airportFfa = details.get(3);
        this.airportIcao = details.get(4);

        this.airportLattitude = Float.parseFloat(details.get(5));
        this.airportLongtitude = Float.parseFloat(details.get(6));

    }

    public void addRoute(Route route) {
        this.routes.add(route);
        this.neighbours.add(route.getDestinationAirport());
    }

    public HashSet<Airport> getNeighbours() {
        return this.neighbours;
    }

    public int getAirportId() {
        return airportId;
    }

    public ArrayList<String> getAirportDetails() {

        ArrayList<String> details = new ArrayList<String>();

        details.add(this.airportName);
        details.add(this.airportCity);
        details.add(this.airportCountry);
        details.add(this.airportFfa);
        details.add(this.airportIcao);
        details.add(String.valueOf(this.airportLattitude));
        details.add(String.valueOf(this.airportLongtitude));

        return details;
    }

    /*
    public HashMap<Integer,Airport> getNeighbours() {

        HashMap<Integer,Airport> neighbours = new HashMap<Integer,Airport>();

        for (Airport n : this.neighbours) {
            neighbours.put(n.getAirportId(),n);
        }

        return new HashMap<Integer,Airport>(neighbours);

    }
    */

    public HashSet<Route> getRoutes() {
        return this.routes;
    }

    //getDistance(Airport airport)

}
