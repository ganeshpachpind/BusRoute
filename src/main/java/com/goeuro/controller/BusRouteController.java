package com.goeuro.controller;

import com.goeuro.model.DirectBusRouteResponse;
import com.goeuro.service.BusRouteCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class BusRouteController {

    @Autowired
    private BusRouteCacheService busRouteCacheService;

    public BusRouteController(BusRouteCacheService busRouteCacheService) {
        this.busRouteCacheService = busRouteCacheService;
    }

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    public DirectBusRouteResponse isDirectRouteExist(@RequestParam(value = "dep_sid") final int departureStationId,
                                                     @RequestParam(value = "arr_sid") final int arrivalStationId) {

        boolean isDirectBusRouteExist = busRouteCacheService.isDirectRouteExist(departureStationId, arrivalStationId);
        return new DirectBusRouteResponse(departureStationId, arrivalStationId, isDirectBusRouteExist);
    }
}
