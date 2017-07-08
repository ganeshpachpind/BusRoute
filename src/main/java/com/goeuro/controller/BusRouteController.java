package com.goeuro.controller;

import com.goeuro.model.DirectBusRouteResponse;
import com.goeuro.model.StationRouteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class BusRouteController {

    @Autowired
    private StationRouteCache stationRouteCache;

    public BusRouteController(StationRouteCache stationRouteCache) {
        this.stationRouteCache = stationRouteCache;
    }

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    public DirectBusRouteResponse isDirectRouteExist(@RequestParam(value = "dep_sid") final int departureStationId,
                                                     @RequestParam(value = "arr_sid") final int arrivalStationId) {

        boolean isDirectBusRouteExist = stationRouteCache.isDirectBusRouteExist(departureStationId, arrivalStationId);
        return new DirectBusRouteResponse(departureStationId, arrivalStationId, isDirectBusRouteExist);
    }
}
