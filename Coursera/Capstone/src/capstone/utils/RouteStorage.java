package capstone.utils;

/**
 * Created by Ievgenii Martynenko on 16.12.2016.
 */

public class RouteStorage {

    private int destinationId;
    private int airlineId;

    public RouteStorage(int destinationId, int airlineId) throws NumberFormatException{

        this.airlineId = airlineId;
        this.destinationId = destinationId;

    }

    public int getDestinationId() {
        return destinationId;
    }

    public int getAirlineId() {
        return airlineId;
    }

}
