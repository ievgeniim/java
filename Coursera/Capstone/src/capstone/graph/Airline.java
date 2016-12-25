package capstone.graph;

/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 */

public class Airline {

    private int airlineId;
    private String airlineName;

    public Airline(int airlineId, String airlineName) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }
}
