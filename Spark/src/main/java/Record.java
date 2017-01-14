import java.io.Serializable;

/**
 * Created by lagger on 12.01.2017.
 */
public class Record implements Serializable {
    int airportId;
    String name;
    String city;
    String country;
    String IATA;
    String ICAO;
    float lattitude;
    float longtitude;
    float attitude;
    float timezone;
    String dst;
    String timezoneTz;
    String type;
    String source;
}
