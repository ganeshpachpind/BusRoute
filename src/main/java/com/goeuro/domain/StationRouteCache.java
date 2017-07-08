package com.goeuro.domain;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class StationRouteCache {

    Map<Integer,ArrayList<Integer>> stationRoutes = new HashMap<>();

    public void update(final Integer stationId, final Integer routeId){
        if (stationRoutes.containsKey(stationId)) {
            stationRoutes.get(stationId).add(routeId);
            return;
        }
        stationRoutes.put(stationId, new ArrayList<>(Arrays.asList(routeId)));
    }
}
