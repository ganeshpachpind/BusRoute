package com.goeuro.service;


import com.goeuro.model.BusRouteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class BusRouteCacheService {

    private BusRouteCache busRouteCache;

    public BusRouteCacheService(BusRouteCache busRouteCache) {
        this.busRouteCache = busRouteCache;
    }

    private static final String WHITE_SPACE_SPLIT = "\\s+";

    public BusRouteCache buildStationRouteCache(final String routesFilePath) throws IOException {
        Files.lines(Paths.get(routesFilePath))
                .skip(1)
                .map(this::convertToIntegers)
                .forEach(this::updateCache);
        return busRouteCache;
    }

    private void updateCache(Stream<Integer> integerStream) {
        List<Integer> integerList = integerStream.collect(Collectors.toList());
        final Integer routeId = integerList.get(0);
        integerList
                .stream()
                .skip(1)
                .forEach(stationId -> busRouteCache.addStationRoute(stationId, routeId));

    }

    private Stream<Integer> convertToIntegers(String routeStations) {
        return Arrays.stream(routeStations.trim().split(WHITE_SPACE_SPLIT))
                .mapToInt(Integer::parseInt)
                .boxed();
    }

    public boolean isDirectRouteExist(int departureStation, int arrivalStation) {
        return busRouteCache.isDirectBusRouteExist(departureStation, arrivalStation);
    }
}
