package test;

import capstone.graph.Airline;
import capstone.utils.DataLoader;
import capstone.utils.RouteStorage;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ievgenii Martynenko on 15.12.2016.
 */
public class DataLoaderTest {

    private long startTime;
    private long endTime;
    private final NumberFormat formatter = new DecimalFormat("#0.00000");

    @org.junit.Test
    public void setUp() throws Exception {

        HashMap<Integer,ArrayList<String>> airports = null;
        HashMap<Integer,String> airlines = null;
        HashMap<Integer,RouteStorage> routes = null;

        try {
            startTime = System.currentTimeMillis();
            airports = DataLoader.loadAirports();
            endTime = System.currentTimeMillis();

            System.out.println("Airports loaded time: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        } catch (NumberFormatException np) {
            System.err.print(np.getMessage());
        } catch (IOException io) {
            System.err.print(io.getMessage());
        }

        try {
            startTime = System.currentTimeMillis();
            airlines = DataLoader.loadAirlines();
            endTime = System.currentTimeMillis();

            System.out.println("Airlines loaded time: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        } catch (NumberFormatException np) {
            System.err.print(np.getMessage());
        } catch (IOException io) {
            System.err.print(io.getMessage());
        }

        try {
            startTime = System.currentTimeMillis();
            routes = DataLoader.loadRoutes();
            endTime = System.currentTimeMillis();

            System.out.println("Routes loaded time: " + formatter.format((endTime - startTime)/1000d) + " seconds.");

        } catch (NumberFormatException np) {
            System.err.print(np.getMessage());
        } catch (IOException io) {
            System.err.print(io.getMessage());
        }


    }

}

