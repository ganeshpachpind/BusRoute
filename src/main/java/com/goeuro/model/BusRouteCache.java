package com.goeuro.model;


import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BusRouteCache {

    /* Key - station id , value - list of routes in which station exist */
    private final Map<Integer, ArrayList<Integer>> stationRoutes = new HashMap<>();

    /**
     * Adds Route to the given station
     * @param station
     * @param route
     */
    public void addStationRoute(final Integer station, final Integer route) {
        if (stationRoutes.containsKey(station)) {
            stationRoutes.get(station).add(route);
            return;
        }
        stationRoutes.put(station, new ArrayList<>(Arrays.asList(route)));
    }

    public boolean isDirectBusRouteExist(int departureStation, int arrivalStation) {
        if (!isStationExist(departureStation) || (!isStationExist(arrivalStation))) {
            return false;
        }
        return !Collections.disjoint(stationRoutes.get(departureStation), stationRoutes.get(arrivalStation));
    }

    private boolean isStationExist(int station) {
        return stationRoutes.containsKey(station);
    }

    public Map<Integer, ArrayList<Integer>> getStationRoutes() {
        return stationRoutes;
    }
}
