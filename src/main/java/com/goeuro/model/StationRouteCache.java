package com.goeuro.model;


import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StationRouteCache {

    Map<Integer, ArrayList<Integer>> stationRoutes = new HashMap<>();

    public void update(final Integer stationId, final Integer routeId) {
        if (stationRoutes.containsKey(stationId)) {
            stationRoutes.get(stationId).add(routeId);
            return;
        }
        stationRoutes.put(stationId, new ArrayList<>(Arrays.asList(routeId)));
    }

    public boolean isDirectBusRouteExist(int departureStaionId, int arrivalStationId) {
        if ((!stationRoutes.containsKey(departureStaionId)) || (!stationRoutes.containsKey(arrivalStationId))) {
            return false;
        }
        return !Collections.disjoint(stationRoutes.get(departureStaionId), stationRoutes.get(arrivalStationId));
    }
}
