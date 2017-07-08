package com.goeuro.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectBusRouteResponse {
    @JsonProperty(value = "dep_sid")
    private int departureStationId;

    @JsonProperty(value = "arr_sid")
    private int arrivalStationId;

    @JsonProperty(value = "direct_bus_route")
    private boolean isDirectBusRoute;

    public DirectBusRouteResponse(int departureStationId, int arrivalStationId, boolean isDirectBusRoute) {
        this.departureStationId = departureStationId;
        this.arrivalStationId = arrivalStationId;
        this.isDirectBusRoute = isDirectBusRoute;
    }
}
