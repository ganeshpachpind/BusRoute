package com.goeuro.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class BusRouteCacheTest {

    @Test
    public void shouldAddNewStationWhenStationEntryDoesNotExist() throws Exception {
        BusRouteCache busRouteCache = new BusRouteCache();
        busRouteCache.addStationRoute(1, 10);

        Map<Integer, ArrayList<Integer>> stationRoutes = busRouteCache.getStationRoutes();
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRoutes.get(1).size(), is(1));

    }

    @Test
    public void shouldUpdateStationWhenStationEntryExist() throws Exception {
        BusRouteCache busRouteCache = new BusRouteCache();
        busRouteCache.addStationRoute(1, 10);
        busRouteCache.addStationRoute(1, 11);

        Map<Integer, ArrayList<Integer>> stationRoutes = busRouteCache.getStationRoutes();
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRoutes.get(1).size(), is(2));
    }

    @Test
    public void shouldReturnTrueForTwoStationDirectBusRouteExist() throws Exception {
        BusRouteCache busRouteCache = new BusRouteCache();
        busRouteCache.addStationRoute(1, 10);
        busRouteCache.addStationRoute(2, 10);
        busRouteCache.addStationRoute(3, 11);

        assertTrue(busRouteCache.isDirectBusRouteExist(1, 2));
    }

    @Test
    public void shouldReturnFalseForTwoStationDirectBusRouteDoeNotExist() throws Exception {
        BusRouteCache busRouteCache = new BusRouteCache();
        busRouteCache.addStationRoute(1, 10);
        busRouteCache.addStationRoute(2, 10);
        busRouteCache.addStationRoute(3, 11);

        assertFalse(busRouteCache.isDirectBusRouteExist(2, 3));
    }

    @Test
    public void shouldReturnFalseWhenStationDoesNotExist() throws Exception {
        BusRouteCache busRouteCache = new BusRouteCache();
        busRouteCache.addStationRoute(1, 10);
        busRouteCache.addStationRoute(2, 10);
        busRouteCache.addStationRoute(3, 11);

        assertFalse(busRouteCache.isDirectBusRouteExist(15, 3));
    }
}