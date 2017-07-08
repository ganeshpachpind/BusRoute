package com.goeuro.model;

import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class StationRouteCacheTest {


    @Test
    public void shouldAddNewStationWhenStationEntryDoesNotExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);

        Map<Integer, ArrayList<Integer>> stationRoutes = stationRouteCache.stationRoutes;
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRouteCache.stationRoutes.get(1).size(), is(1));

    }

    @Test
    public void shouldUpdateStationWhenStationEntryExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);
        stationRouteCache.update(1, 11);

        Map<Integer, ArrayList<Integer>> stationRoutes = stationRouteCache.stationRoutes;
        assertThat(stationRoutes.size(), is(1));
        assertThat(stationRouteCache.stationRoutes.get(1).size(), is(2));
    }

    @Test
    public void shouldReturnTrueForTwoStationDirectBusRouteExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);
        stationRouteCache.update(2, 10);
        stationRouteCache.update(3, 11);

        assertTrue(stationRouteCache.isDirectBusRouteExist(1, 2));
    }

    @Test
    public void shouldReturnFalseForTwoStationDirectBusRouteDoeNotExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);
        stationRouteCache.update(2, 10);
        stationRouteCache.update(3, 11);

        assertFalse(stationRouteCache.isDirectBusRouteExist(2, 3));
    }

    @Test
    public void shouldReturnFalseWhenStationDoesNotExist() throws Exception {
        StationRouteCache stationRouteCache = new StationRouteCache();
        stationRouteCache.update(1, 10);
        stationRouteCache.update(2, 10);
        stationRouteCache.update(3, 11);

        assertFalse(stationRouteCache.isDirectBusRouteExist(15, 3));
    }
}