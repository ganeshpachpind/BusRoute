package com.goeuro;

import com.goeuro.model.StationRouteCache;
import com.goeuro.util.StationRouteCacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BusRouteApplicationRunner implements CommandLineRunner {

    @Autowired
    StationRouteCacheBuilder stationRouteCacheBuilder;
    @Autowired
    StationRouteCache stationRouteCache;

    @Override
    public void run(String... args) throws IOException {
        stationRouteCacheBuilder.buildStationRouteCache("data/example");
    }
}
