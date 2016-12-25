package capstone.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Ievgenii Martynenko on 14.12.2016.
 * The class is designed for parsing files airport data,
 * routes data and airlines data
 */

public final class DataLoader {

    private static final String delim = ",";
    private static final String airportsFile = "resources/airports.csv";
    private static final String airlinesFile = "resources/airlines.csv";
    private static final String routesFile = "resources/routes.csv";

    /**
     * @desc Reads all rows and columns (except last 4) from airports files
     * @return A map with list of Airports where airportId is a key and all other values are ArrayList
     * @throws NumberFormatException
     * @throws IOException
     */

    public static HashMap<Integer,ArrayList<String>> loadAirports() throws NumberFormatException, IOException {

        HashMap<Integer,ArrayList<String>> airports = new HashMap<Integer,ArrayList<String>>();
        String line = "";
        BufferedReader bf = new BufferedReader(new FileReader(airportsFile));

        while ((line = bf.readLine()) != null) {
            String tokens[] = line.split(delim);
            int key = Integer.parseInt(tokens[0]);

            ArrayList<String> values = new ArrayList<String>();

            for (int i = 1; i < tokens.length - 4; i++) {
                values.add(tokens[i]);
            }
            airports.put(key, values);

        }

        return new HashMap<Integer,ArrayList<String>>(airports);
    }

    /**
     * @desc Reads all rows and two first columns from airlinesFile
     * @return A map with list of airlines where airlineId is a key and name is value
     * @throws NumberFormatException
     * @throws IOException
     */

    public static HashMap<Integer,String> loadAirlines() throws NumberFormatException, IOException{

        HashMap<Integer,String> airlines = new HashMap<Integer,String>();
        String line = "";
        BufferedReader bf = new BufferedReader(new FileReader(airlinesFile));

        while ((line = bf.readLine()) != null) {
            String tokens[] = line.split(delim);
            airlines.put(Integer.parseInt(tokens[0]), tokens[1]);

        }

        return new HashMap<Integer,String>(airlines);
    }

    /**
     * @desc Reads all rows and 2, 4, 6 columns from routes (which are airlineId, source and destination)
     * @return A map with list of routes, where source is key but destinationId and airportId are wrapped into Storage
     * @throws NumberFormatException
     * @throws IOException
     */

    public static HashMap<RouteStorage,Integer> loadRoutes() throws NumberFormatException, IOException {

        HashMap<RouteStorage,Integer> routes = new HashMap<RouteStorage,Integer>();
        String line = "";
        BufferedReader bf = new BufferedReader(new FileReader(routesFile));

        while ((line = bf.readLine()) != null) {
            if (!line.contains("\\N")) {
                String tokens[] = line.split(delim);
                routes.put(new RouteStorage(Integer.parseInt(tokens[3]), Integer.parseInt(tokens[5])),Integer.parseInt(tokens[1]));
            }
        }

        return new  HashMap<RouteStorage,Integer>(routes);
    }

}
