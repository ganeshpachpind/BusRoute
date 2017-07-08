package com.goeuro.util;


import com.goeuro.domain.StationRouteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StationRouteCacheBuilder {


    @Autowired
    private StationRouteCache stationRouteCache;

    public StationRouteCacheBuilder(StationRouteCache stationRouteCache) {
        this.stationRouteCache = stationRouteCache;
    }

    private static final String WHITE_SPACE_SPLIT = "\\s+";

    public void buildStationRouteCache(final String routesFilePath) throws IOException {
        Files.lines(Paths.get(routesFilePath))
                .skip(1)
                .map(this::convertToIntegers)
                .forEach(this::updateCache);
    }

    private void updateCache(Stream<Integer> integerStream) {
        List<Integer> integerList = integerStream.collect(Collectors.toList());
        final Integer routeId = integerList.get(0);
        integerList
                .stream()
                .skip(1)
                .forEach(stationId -> stationRouteCache.update(stationId, routeId));

    }

    private Stream<Integer> convertToIntegers(String routeStations) {
        return Arrays.stream(routeStations.trim().split(WHITE_SPACE_SPLIT))
                .mapToInt(Integer::parseInt)
                .boxed();
    }
}
