package capstone.graph;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class Route {

    private Airport sourceAirport;
    private Airport destinationAirport;
    private Airline routeOwner;

    public Route(Airport sourceAirport, Airport destinationAirport, Airline routeOwner) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.routeOwner = routeOwner;
    }

    public Airline getRouteOwner() {
        return routeOwner;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    // getDistance()
}

