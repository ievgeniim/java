package capstone.utils;

/**
 * Created by Ievgenii Martynenko on 16.12.2016.
 */

public class RouteStorage {

    private int sourceId;
    private int destinationId;

    public RouteStorage(int sourceId, int destinationId) throws NumberFormatException{

        this.sourceId = sourceId;
        this.destinationId = destinationId;

    }

    public int getSourceId() {
        return sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

}
